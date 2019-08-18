package comp3350.amate.tests.business;

import org.junit.Before;
import org.junit.Test;

import comp3350.amate.business.AccessShoppingCart;
import comp3350.amate.objects.Book;
import comp3350.amate.objects.BookBuilder;
import comp3350.amate.objects.ShoppingCart;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccessShoppingCartUT {
    private AccessShoppingCart accessSC;
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = mock(ShoppingCart.class);
        accessSC = new AccessShoppingCart(shoppingCart);
    }

    @Test
    public void testAccessShoppingCart() {
        System.out.println("Starting testAccessShoppingCart\n");

        assertNotNull(accessSC);
        assertNotNull(accessSC.getCart());

        System.out.println("Finished testAccessShoppingCart\n");
    }

    @Test
    public void addToCart_TEST() {
        System.out.println("Starting test addToCart_TEST\n");

        Book book = new BookBuilder()
                .setNewTitle("The Inferno")
                .createBook();
        accessSC.addToCart(book);
        verify(shoppingCart).addToCart(book);

        System.out.println("Finished test addToCart_TEST\n");
    }

    @Test
    public void removeFromCart_TEST() {
        System.out.println("Starting test removeFromCart_TEST\n");

        Book book = new BookBuilder()
                .setNewTitle("The Inferno")
                .createBook();
        accessSC.removeFromCart(book);
        verify(shoppingCart).removeFromCart(book);

        System.out.println("Finished test removeFromCart_TEST\n");
    }
}
