package comp3350.amate.persistence;

import java.util.List;

import comp3350.amate.objects.Order;

public interface OrderPersistence {
    void addOrder(Order order);

    Order findOrder(int orderID);

    void updateTotalOrders();

    List<Order> getOrders();
}
