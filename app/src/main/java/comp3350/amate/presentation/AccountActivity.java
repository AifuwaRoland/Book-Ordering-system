package comp3350.amate.presentation;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.objects.Account;
import comp3350.amate.business.InvalidSigninException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AccountActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void buttonSigninOnClick(View v) {
        final EditText userID = (EditText) findViewById(R.id.signinUserID);
        final EditText password = (EditText) findViewById(R.id.signinPassword);
        final String userid = userID.getText().toString();
        final String userpassword = password.getText().toString();

        Account signinAccount = new Account.AccountBuilder(userid)
                .buildUserPassword(userpassword)
                .build();
        Account currAccount;

        AccessAccount accountList = new AccessAccount();
        try {
            currAccount = accountList.signIn(signinAccount);

            Intent secondActivity = new Intent(AccountActivity.this, AccountInfoActivity.class);
            secondActivity.putExtra("userID", currAccount.getUserID());
            secondActivity.putExtra("userName", currAccount.getUserName());
            secondActivity.putExtra("userNum", currAccount.getUserNum());
            secondActivity.putExtra("userAddress", currAccount.getUserAddr());
            secondActivity.putExtra("userEmail", currAccount.getUserEmail());
            secondActivity.putExtra("userType", currAccount.getType());
            AccountActivity.this.startActivity(secondActivity);
        } catch (InvalidSigninException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
            builder.setTitle("Error:");
            builder.setMessage(e.getMessage());
            builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(AccountActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }

    }

    public void buttonSignupOnClick(View v) {
        Intent secondActivity = new Intent(AccountActivity.this, RegisterActivity.class);
        AccountActivity.this.startActivity(secondActivity);
    }
}
