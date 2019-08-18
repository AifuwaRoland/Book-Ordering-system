package comp3350.amate.persistence.HSQL;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.amate.objects.Account;
import comp3350.amate.persistence.AccountPersistence;

public class AccountPersistenceHSQLDB implements AccountPersistence {
    private final String dbPath;

    public AccountPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Account fromResultSet(final ResultSet rs) throws SQLException {
        final String userID = rs.getString("userID");
        final String username = rs.getString("username");
        final String password = rs.getString("password");
        final String email = rs.getString("email");
        final String address = rs.getString("address");
        final String phoneNumber = rs.getString("phoneNum");

        //Rebuild bookmarks
        Array array = rs.getArray("bookmarks");
        Object o[] = (Object[]) array.getArray();
        List<String> bookmarks = new ArrayList<>();
        for (int i = 0; i < o.length; i++)
            bookmarks.add((String) o[i]);

        //Rebuild placed orders
        array = rs.getArray("placedOrders");
        o = (Object[]) array.getArray();
        List<Integer> orders = new ArrayList<>();
        for (int i = 0; i < o.length; i++)
            orders.add((Integer) o[i]);
        final int userType = rs.getInt("userType");

        return new Account.AccountBuilder(userID)
                .buildUserName(username)
                .buildUserPassword(password)
                .buildUserAddr(address)
                .buildUserNum(phoneNumber)
                .buildUserEmail(email)
                .buildUserBookmarks(bookmarks)
                .buildUserOrders(orders)
                .buildUserAccountType(userType)
                .build();
    }

    @Override
    public List<Account> getAccountSequential() {
        final List<Account> accounts = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM account");
            while (rs.next()) {
                final Account user = fromResultSet(rs);
                accounts.add(user);
            }
            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return accounts;
    }

    @Override
    public Account insertAccount(final Account newUser) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO Account VALUES(?,?,?,?,?,?,?,?,?)");
            st.setString(1, newUser.getUserID());
            st.setString(2, newUser.getUserName());
            st.setString(3, newUser.getUserPassword());
            st.setString(4, newUser.getUserEmail());
            st.setString(5, newUser.getUserAddr());
            st.setString(6, newUser.getUserNum());

            List<String> bookmarks = newUser.getBookmarkList();
            Array a = c.createArrayOf("VARCHAR", bookmarks.toArray());
            st.setArray(7, a);

            List<Integer> orders = newUser.getOrderList();
            a = c.createArrayOf("INT", orders.toArray());
            st.setArray(8, a);

            st.setInt(9, newUser.getType());

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return newUser;
    }

    @Override
    public void updateUserBookmarks(String userID, List<String> bookmarks) throws PersistenceException {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE account SET bookmarks = ? WHERE userID = ?");
            Array a = c.createArrayOf("VARCHAR", bookmarks.toArray());
            st.setArray(1, a);
            st.setString(2, userID);

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Account getAccount(String userID) {
        Account result = null;
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM account WHERE userID = ?");
            st.setString(1, userID);
            final ResultSet rs = st.executeQuery();

            if (rs.next())
                result = fromResultSet(rs);

            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return result;
    }

    public void addOrderToUser(String userID, int orderID) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE account SET placedOrders = placedOrders || ARRAY[CAST (? AS INTEGER)] WHERE userID = ?");
            st.setInt(1, orderID);
            st.setString(2, userID);

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
