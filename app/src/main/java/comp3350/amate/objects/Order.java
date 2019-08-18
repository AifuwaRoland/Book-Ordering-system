package comp3350.amate.objects;

import java.util.Calendar;
import java.util.Date;

public class Order {
    private static int totalOrders = 1;

    private final int orderID;
    private final ShoppingCart cart;
    private final Date orderPlaced;
    private final Date orderArrival;

    //for testing
    public Order() {
        orderID = 0;
        cart = new ShoppingCart();
        orderPlaced = new Date();
        orderArrival = new Date();
    }

    public Order(ShoppingCart newCart) {
        orderID = totalOrders++;
        cart = newCart;
        orderPlaced = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(orderPlaced);
        c.add(Calendar.DATE, 10);
        orderArrival = c.getTime();
    }

    //Rebuilding from database
    public Order(int newID, ShoppingCart newCart, Date newOrderPlaced, Date newOrderArrival) {
        orderID = newID;
        cart = newCart;
        orderPlaced = newOrderPlaced;
        orderArrival = newOrderArrival;
    }

    //Accessors
    public int getOrderID() {
        return orderID;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Date getOrderPlaced() {
        return orderPlaced;
    }

    public Date getOrderArrival() {
        return orderArrival;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void updateTotalOrders(int update) {
        totalOrders = update;
    }
}
