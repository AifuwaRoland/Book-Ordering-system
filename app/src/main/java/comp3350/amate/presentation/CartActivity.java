package comp3350.amate.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.amate.R;
import comp3350.amate.business.AccessBooks;
import comp3350.amate.business.AccessShoppingCart;
import comp3350.amate.objects.ShoppingCart;
import comp3350.amate.objects.Book;

public class CartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        final AccessShoppingCart accessSC = new AccessShoppingCart();

        Book book;
        if (title != null) {
            AccessBooks accessBooks = new AccessBooks();
            book = accessBooks.getBook(title);
            accessSC.addToCart(book);
        }

        //Display shopping cart details
        ShoppingCart cart = accessSC.getCart();
        List<String> cartInfo = cart.getBooksInCart();

        TextView price = (TextView) findViewById(R.id.cartTotal);
        price.setText(String.format("Price: $%d.%d", cart.getPrice() / 100, cart.getPrice() % 100));
        TextView totalItems = (TextView) findViewById(R.id.itemTotal);
        totalItems.setText(String.format("Total items: %d", cart.getTotalItems()));

        Item itemadapter = new Item(cartInfo, this);
        ListView listView = (ListView) findViewById(R.id.cartlist);
        listView.setAdapter(itemadapter);

        final Button placeOrder = (Button) findViewById(R.id.btn_placeorder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (accessSC.getCart().getTotalItems() != 0) {
                    Intent place = new Intent(CartActivity.this, CaOneActivity.class);
                    CartActivity.this.startActivity(place);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("You have no books in the cart to purchase");
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            }
        });
    }
}

