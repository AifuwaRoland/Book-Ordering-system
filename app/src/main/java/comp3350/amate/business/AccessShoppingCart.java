package comp3350.amate.business;

import comp3350.amate.objects.Book;
import comp3350.amate.objects.ShoppingCart;

public class AccessShoppingCart {
    private static ShoppingCart shoppingCart;

    public AccessShoppingCart() {
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
    }

    public AccessShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getCart() {
        return shoppingCart;
    }

    public void addToCart(Book book) {
        shoppingCart.addToCart(book);
    }

    public void removeFromCart(Book book) {
        shoppingCart.removeFromCart(book);
    }

    public void clearCart() {
        shoppingCart = new ShoppingCart();
    }
}
