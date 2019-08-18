package comp3350.amate.business;

import java.util.List;

import comp3350.amate.application.Services;
import comp3350.amate.objects.Order;
import comp3350.amate.objects.ShoppingCart;
import comp3350.amate.persistence.OrderPersistence;

public class AccessOrders {
    private OrderPersistence orders;
    private Order order;

    public AccessOrders() {
        orders = Services.getOrderPersistence();
        order = null;
    }//end of constructor

    public AccessOrders(OrderPersistence op) {
        this();
        orders = op;
    }

    public void addOrder(ShoppingCart cart) {
        orders.updateTotalOrders();
        order = new Order(cart);
        orders.addOrder(order);
    }

    public Order findOrder(int orderID) {
        return orders.findOrder(orderID);
    }

    public List<Order> getOrders() {
        return orders.getOrders();
    }
}
