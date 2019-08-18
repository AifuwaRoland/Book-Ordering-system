package comp3350.amate.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.business.AccessCard;
import comp3350.amate.business.AccessShoppingCart;
import comp3350.amate.objects.Card;
import comp3350.amate.objects.Account;
import comp3350.amate.business.AccessOrders;
import comp3350.amate.objects.Order;
import comp3350.amate.objects.ShoppingCart;

public class CaTwoActivity extends Activity {
    private AccessAccount accessAccount;
    private AccessShoppingCart accessShoppingCart;
    private Account acc;
    private AccessOrders orders;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        accessAccount = new AccessAccount();
        acc = accessAccount.getSignedInAccount();
        AccessCard accessCard = new AccessCard();
        Card credit;
        if (acc == null)
            credit = accessCard.getCard("Guest");
        else
            credit = accessCard.getCard(acc.getUserID());
        accessShoppingCart = new AccessShoppingCart();

        Intent intent = getIntent();
        int orderID = intent.getIntExtra("orderID", -1);
        orders = new AccessOrders();

        if (orderID != -1 && orders.findOrder(orderID) != null) {
            ShoppingCart cart = accessShoppingCart.getCart();
            List<String> orderInfo = cart.getBooksInCart();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderInfo);
            ListView listView = (ListView) findViewById(R.id.dynamic);
            listView.setAdapter(arrayAdapter);
        }

        if (credit != null) {
            TextView show = (TextView) findViewById(R.id.textView4);
            show.setText(String.format("Visa ending in %d", credit.getCardNumber() % 10000));
        }
        if (acc != null) {
            EditText addr = (EditText) findViewById(R.id.editText7);
            addr.setText(acc.getUserAddr());

        }
    }

    public void buttonFinalOnClick(View v) {
        orders.addOrder(accessShoppingCart.getCart());
        Order o = new Order();
        int currentTotalOrder = o.getTotalOrders() - 1;

        if (acc != null) {
            accessAccount.addOrderToUser(acc.getUserID(), currentTotalOrder);
        }

        accessShoppingCart.clearCart();

        AlertDialog.Builder builder = new AlertDialog.Builder(CaTwoActivity.this);
        builder.setTitle("Success");
        builder.setMessage(String.format("Your order ID is %d", currentTotalOrder));
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent fun = new Intent(getApplicationContext(), MainActivity.class);
                CaTwoActivity.this.startActivity(fun);
            }
        });
        builder.show();
    }
}
