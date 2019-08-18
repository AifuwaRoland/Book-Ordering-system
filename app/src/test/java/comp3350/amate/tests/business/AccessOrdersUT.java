package comp3350.amate.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import comp3350.amate.business.AccessOrders;
import comp3350.amate.objects.Order;
import comp3350.amate.objects.ShoppingCart;
import comp3350.amate.persistence.OrderPersistence;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessOrdersUT {
    private OrderPersistence orderPersistence;
    private AccessOrders accessOrders;

    @Before
    public void setUp() {
        orderPersistence = mock(OrderPersistence.class);
        accessOrders = new AccessOrders(orderPersistence);
    }

    @Test
    public void addOrder_TEST() {
        System.out.println("Starting test addOrder\n");

        ShoppingCart shoppingCart = new ShoppingCart();
        accessOrders.addOrder(shoppingCart);
        verify(orderPersistence).updateTotalOrders();

        System.out.println("Finished test addOrder\n");
    }

    @Test
    public void findOrder_TEST() {
        System.out.println("Starting test AccessOrder.insertOrder\n");

        ShoppingCart testShoppingCart = new ShoppingCart();
        Order testOrder = new Order(123, testShoppingCart, new Date(1), new Date(2));
        when(orderPersistence.findOrder(123)).thenReturn(testOrder);//getOrderSequential

        Order order = accessOrders.findOrder(123);
        verify(orderPersistence).findOrder(123);

        assertNotNull("first sequential order should not be null", order);
        assertTrue(123 == (order.getOrderID()));

        System.out.println("Finished test AccessOrder.insertOrder\n");
    }

    @Test
    public void getOrders_TEST() {
        System.out.println("Starting test getOrders\n");

        accessOrders.getOrders();
        verify(orderPersistence).getOrders();

        System.out.println("Finished test getOrders\n");


    }
}
