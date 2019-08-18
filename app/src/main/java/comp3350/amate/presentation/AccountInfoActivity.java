package comp3350.amate.presentation;

import comp3350.amate.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountInfoActivity extends Activity {
    private String userID;
    private String userName;
    private String userNum;
    private String userAddr;
    private String userEmail;
    private int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountinfo);

        userID = getIntent().getStringExtra("userID");
        TextView tuserID = (TextView) findViewById(R.id.accountinfoUserID);
        tuserID.setText(userID);

        userName = getIntent().getStringExtra("userName");
        TextView tuserName = (TextView) findViewById(R.id.accountinfoUserName);
        tuserName.setText(userName);

        userNum = getIntent().getStringExtra("userNum");
        TextView tuserNum = (TextView) findViewById(R.id.accountinfoUserNum);
        tuserNum.setText(userNum);

        userAddr = getIntent().getStringExtra("userAddress");
        TextView tuserAddr = (TextView) findViewById(R.id.accountinfoUserAddress);
        tuserAddr.setText(userAddr);

        userEmail = getIntent().getStringExtra("userEmail");
        TextView tuserEmail = (TextView) findViewById(R.id.accountinfoUserEmail);
        tuserEmail.setText(userEmail);

        userType = getIntent().getIntExtra("userType", -1);

        final Button adminOrderList = (Button) findViewById(R.id.adminListOrders);
        adminOrderList.setVisibility(View.GONE);

        final Button adminUserList = (Button) findViewById(R.id.adminListUsers);
        adminUserList.setVisibility(View.GONE);

        if (userType == 1) {
            adminOrderList.setVisibility(View.VISIBLE);
            adminOrderList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(AccountInfoActivity.this, AdminListActivity.class);
                        i.putExtra("list", "orders");
                        AccountInfoActivity.this.startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            adminUserList.setVisibility(View.VISIBLE);
            adminUserList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(AccountInfoActivity.this, AdminListActivity.class);
                        i.putExtra("list", "users");
                        AccountInfoActivity.this.startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void buttonHomeOnClick(View v) {
        Intent i = new Intent(AccountInfoActivity.this, MainActivity.class);
        AccountInfoActivity.this.startActivity(i);
    }

    public void buttonListOrders(View v) {
        Intent i = new Intent(AccountInfoActivity.this, OrderListActivity.class);
        i.putExtra("userID", userID);
        AccountInfoActivity.this.startActivity(i);
    }
}
