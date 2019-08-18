package comp3350.amate.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.business.AccessOrders;
import comp3350.amate.objects.Account;
import comp3350.amate.objects.Order;

public class AdminListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        Intent intent = getIntent();
        String listType = intent.getStringExtra("list");

        List<String> list = new ArrayList<>();

        if (listType.equals("orders")) {
            TextView textView = (TextView) findViewById(R.id.adminListHeader);
            textView.setText("List of Order IDs");

            AccessOrders ao = new AccessOrders();
            List<Order> orders = ao.getOrders();

            for (int i = 0; i < orders.size(); i++)
                list.add(String.valueOf(orders.get(i).getOrderID()));
        } else if (listType.equals("users")) {
            TextView textView = (TextView) findViewById(R.id.adminListHeader);
            textView.setText("List of User IDs");

            AccessAccount aa = new AccessAccount();
            List<Account> accounts = aa.getAccounts();

            for (int i = 0; i < accounts.size(); i++)
                list.add(String.valueOf(accounts.get(i).getUserID()));
        }

        ListView listView = (ListView) findViewById(R.id.adminList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }
}
