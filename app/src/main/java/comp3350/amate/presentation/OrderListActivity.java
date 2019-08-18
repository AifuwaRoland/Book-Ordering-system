package comp3350.amate.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.objects.Account;

public class OrderListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        //Get account
        AccessAccount aa = new AccessAccount();
        List<Account> accounts = aa.getAccounts();
        Account account = null;
        for (int i = 0; i < accounts.size() && account == null; i++) {
            if (accounts.get(i).getUserID().equals(userID))
                account = accounts.get(i);
        }

        //Get account's orders
        List<Integer> userOrders = account.getOrderList();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < userOrders.size(); i++)
            result.add(userOrders.get(i).toString());

        OrderListEntryAdapter adapter = new OrderListEntryAdapter(result, this);
        ListView listView = (ListView) findViewById(R.id.orderList);
        listView.setAdapter(adapter);
    }
}
