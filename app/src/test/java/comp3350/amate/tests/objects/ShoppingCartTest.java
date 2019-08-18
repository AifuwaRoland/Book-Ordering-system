package comp3350.amate.tests.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.amate.objects.Book;
import comp3350.amate.objects.BookBuilder;
import comp3350.amate.objects.ShoppingCart;

public class ShoppingCartTest {
    ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    public void testCartConstruction() {
        System.out.println("Starting testCartConstruction\n");

        assertNotNull(cart);
        assertEquals((long) 0, (long) cart.getBooksInCart().size());
        assertEquals((long) 0, (long) cart.getPrice());
        assertEquals((long) 0, (long) cart.getTotalItems());

        System.out.println("Finished testCartConstruction\n");
    }

    @Test
    public void testCartAdd() {
        System.out.println("Starting testCartAdd\n");

        Book book = new BookBuilder()
                .setNewTitle("The Inferno")
                .setNewPrice(536)
                .createBook();
        cart.addToCart(book); // add book to cart
        assertEquals((long) 1, (long) cart.getBooksInCart().size());
        assertEquals((long) 536, (long) cart.getPrice());
        assertEquals((long) 1, (long) cart.getTotalItems());

        System.out.println("Finished testCartAdd\n");

    }

    @Test
    public void testCartRemove() {
        System.out.println("Starting testCartRemove\n");

        Book book = new BookBuilder()
                .setNewTitle("The Inferno")
                .setNewPrice(536)
                .createBook();
        boolean exceptionFound = false;


        exceptionFound = false;
        cart.addToCart(book);
        try {
            cart.removeFromCart(book);
        } catch (Exception e) {
            exceptionFound = true;
        }
        assertFalse(exceptionFound);
        assertEquals((long) 0, (long) cart.getBooksInCart().size());
        assertEquals((long) 0, (long) cart.getPrice());
        assertEquals((long) 0, (long) cart.getTotalItems());


        System.out.println("Finished testCartRemove\n");
    }

}
