package comp3350.amate.tests.objects;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import comp3350.amate.objects.Book;
import comp3350.amate.objects.BookBuilder;
import comp3350.amate.objects.Order;
import comp3350.amate.objects.ShoppingCart;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;

    @Before
    public void setUp() {
        ShoppingCart cart = new ShoppingCart();
        Book book = new BookBuilder()
                .setNewTitle("The Inferno")
                .setNewPrice(536)
                .createBook();
        cart.addToCart(book);
        order = new Order(cart);
    }

    @Test
    public void testOrderConstruction() {
        System.out.println("Starting testOrderConstruction\n");

        assertNotNull(order);
        assertEquals((long) 1, order.getOrderID());
        assertNotNull(order.getCart());
        Calendar o = Calendar.getInstance();
        o.setTime(order.getOrderPlaced());
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        assertEquals((long) o.get(Calendar.YEAR), (long) c.get(Calendar.YEAR));
        assertEquals((long) o.get(Calendar.MONTH), (long) c.get(Calendar.MONTH));
        assertEquals((long) o.get(Calendar.DATE), (long) c.get(Calendar.DATE));
        o.setTime(order.getOrderArrival());
        c.add(Calendar.DATE, 10);
        assertEquals((long) o.get(Calendar.YEAR), (long) c.get(Calendar.YEAR));
        assertEquals((long) o.get(Calendar.MONTH), (long) c.get(Calendar.MONTH));
        assertEquals((long) o.get(Calendar.DATE), (long) c.get(Calendar.DATE));

        System.out.println("Finished testOrderConstruction\n");
    }
}
