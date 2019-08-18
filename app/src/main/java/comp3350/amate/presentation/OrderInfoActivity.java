package comp3350.amate.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comp3350.amate.R;
import comp3350.amate.business.AccessOrders;
import comp3350.amate.objects.Order;
import comp3350.amate.objects.ShoppingCart;

public class OrderInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);

        Intent intent = getIntent();
        int orderID = intent.getIntExtra("orderID", -1);
        AccessOrders orders = new AccessOrders();
        Order order = orders.findOrder(orderID);

        //Display order details
        if (orderID != -1 && order != null) {
            List<String> orderInfo = new ArrayList<>();
            orderInfo.add(String.format("Order ID: %d", order.getOrderID()));

            //Display books that were ordered
            orderInfo.add("Items in Shopping Cart:");
            ShoppingCart cart = order.getCart();
            List<String> shoppingCartList = cart.getBooksInCart();

            for (int i = 0; i < cart.getTotalItems(); i++)
                orderInfo.add(shoppingCartList.get(i));

            orderInfo.add(String.format("Total items: %d", cart.getTotalItems()));
            orderInfo.add(String.format("Price: $%d.%d", cart.getPrice() / 100, cart.getPrice() % 100));
            orderInfo.add(String.format
                    ("Order Placed: %s", dateFormat.format(order.getOrderPlaced())));
            orderInfo.add(String.format
                    ("Order Arrival on: %s", dateFormat.format(order.getOrderArrival())));

            long diff = order.getOrderArrival().getTime() - (new Date()).getTime();
            int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            if (diffDays > 0) {
                orderInfo.add(String.format("Order will arrive in %d days", diffDays));
            } else {
                orderInfo.add("Order arrival date has passed");
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                    (this, android.R.layout.simple_list_item_1, orderInfo);
            ListView listView = (ListView) findViewById(R.id.orderInfo);
            listView.setAdapter(arrayAdapter);
        } else {
            TextView textView = (TextView) findViewById(R.id.noOrder);
            textView.setText(R.string.noOrder);
        }
    }
}
