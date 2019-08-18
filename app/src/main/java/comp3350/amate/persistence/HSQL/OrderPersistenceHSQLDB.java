package comp3350.amate.persistence.HSQL;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import comp3350.amate.business.AccessBooks;
import comp3350.amate.objects.Order;
import comp3350.amate.objects.ShoppingCart;
import comp3350.amate.persistence.OrderPersistence;

public class OrderPersistenceHSQLDB implements OrderPersistence {
    private final String dbPath;

    public OrderPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Order fromResultSet(final ResultSet rs) throws SQLException {
        final int orderID = rs.getInt("orderID");

        //rebuild order's shopping cart
        Array array = rs.getArray("cartBooks");
        //array.getArray returns type Object without cast
        //Casting to String[] gives ClassCastException
        //Safer to cast as Object[] and cast each entry to the type you need
        Object[] o = (Object[]) array.getArray();
        ShoppingCart cart = new ShoppingCart();
        AccessBooks ab = new AccessBooks();
        String s;
        for (int i = 0; i < o.length; i++) {
            s = (String) o[i];
            cart.addToCart(ab.getBook(s));
        }

        //Rebuild date order was placed
        array = rs.getArray("orderPlaced");
        o = (Object[]) array.getArray();
        Calendar c = Calendar.getInstance();
        c.set((int) o[0], (int) o[1], (int) o[2]);  //yyyy/mm/dd
        Date orderPlaced = c.getTime();

        //Rebuild date order arrival
        array = rs.getArray("orderArrival");
        o = (Object[]) array.getArray();
        c.set((int) o[0], (int) o[1], (int) o[2]);  //yyyy/mm/dd
        Date orderArrival = c.getTime();

        return new Order(orderID, cart, orderPlaced, orderArrival);
    }

    @Override
    public void addOrder(Order order) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO orders VALUES(?,?,?,?)");
            st.setInt(1, order.getOrderID());  //add orderID

            //add cart
            ShoppingCart cart = order.getCart();
            List<String> list = cart.getBooksInCart();
            Array a = c.createArrayOf("VARCHAR", list.toArray());
            st.setArray(2, a);

            //add orderPlaced
            List<Integer> date = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(order.getOrderPlaced());
            date.add(calendar.get(Calendar.YEAR));
            date.add(calendar.get(Calendar.MONTH));
            date.add(calendar.get(Calendar.DATE));
            a = c.createArrayOf("INT", date.toArray());
            st.setArray(3, a);

            //add orderPlaced
            date = new ArrayList<>();
            calendar.setTime(order.getOrderArrival());
            date.add(calendar.get(Calendar.YEAR));
            date.add(calendar.get(Calendar.MONTH));
            date.add(calendar.get(Calendar.DATE));
            a = c.createArrayOf("INT", date.toArray());
            st.setArray(4, a);

            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            //throw new PersistenceException(e);
        }
    }

    @Override
    public Order findOrder(int orderID) {
        Order result = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM orders WHERE orderID = ?");
            st.setInt(1, orderID);
            final ResultSet rs = st.executeQuery();

            while (rs.next())
                result = fromResultSet(rs);

            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return result;
    }

    @Override
    public void updateTotalOrders() {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT MAX(orderID) AS Total FROM orders");
            final ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("Total");
                Order o = new Order();
                o.updateTotalOrders(id + 1);
            }
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Order> getOrders() {
        List<Order> result = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM orders");
            final ResultSet rs = st.executeQuery();

            while (rs.next())
                result.add(fromResultSet(rs));

            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return result;
    }
}
