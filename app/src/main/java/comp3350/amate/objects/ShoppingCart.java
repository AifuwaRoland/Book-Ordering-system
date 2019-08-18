package comp3350.amate.objects;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<String> cart;
    private int totalPayment;
    private int totalItems;

    public ShoppingCart() {
        cart = new ArrayList<>();
        totalPayment = 0;
        totalItems = 0;
    }

    //Accessors
    public List<String> getBooksInCart() {
        return cart;
    }

    public int getPrice() {
        return totalPayment;
    }

    public int getTotalItems() {
        return totalItems;
    }

    //Mutators
    public void addToCart(Book book) {
        cart.add(book.getBookTitle());
        totalPayment += book.getBookPrice();
        totalItems++;
    }

    public void removeFromCart(Book book) {
        boolean removed = false;
        for (int i = 0; i < cart.size() && !removed; i++) {
            if (cart.get(i).equals(book.getBookTitle())) {
                cart.remove(i);
                totalPayment -= book.getBookPrice();
                totalItems--;
                removed = true;
            }
        }
    }
}
