package comp3350.amate.presentation;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.business.InvalidRegisterException;
import comp3350.amate.objects.Account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void buttonRegisterOnClick(View v) {
        CheckBox customer = (CheckBox) findViewById(R.id.customerMode);
        CheckBox administrator = (CheckBox) findViewById(R.id.administratorMode);
        Boolean isCustomer = customer.isChecked();
        Boolean isAdministrator = administrator.isChecked();
        if (isCustomer && isAdministrator) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Error:");
            builder.setMessage("Can not select both customer and administrator!");
            builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(RegisterActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        } else if (!isCustomer && !isAdministrator) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Error:");
            builder.setMessage("you need to select at least one of customer or administrator!");
            builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(RegisterActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        } else {
            final EditText userID = (EditText) findViewById(R.id.signupUserID);
            final EditText userName = (EditText) findViewById(R.id.signupUserName);
            final EditText userAddress = (EditText) findViewById(R.id.signupUserAddress);
            final EditText userNum = (EditText) findViewById(R.id.signupUserNum);
            final EditText userEmail = (EditText) findViewById(R.id.signupUserEmail);
            final EditText password = (EditText) findViewById(R.id.signupPassword);

            AccessAccount accountList = new AccessAccount();
            final String userid = userID.getText().toString();
            final String username = userName.getText().toString();
            final String useraddr = userAddress.getText().toString();
            final String usernum = userNum.getText().toString();
            final String useremail = userEmail.getText().toString();
            final String userpassword = password.getText().toString();
            final int userAccountType;
            if (isAdministrator) {
                userAccountType = 1;
            } else {
                userAccountType = 0;
            }

            Account signupAccount = new Account.AccountBuilder(userid)
                    .buildUserName(username)
                    .buildUserAddr(useraddr)
                    .buildUserNum(usernum)
                    .buildUserEmail(useremail)
                    .buildUserPassword(userpassword)
                    .buildUserAccountType(userAccountType)
                    .build();
            try {
                accountList.register(signupAccount);
                Intent secondActivity = new Intent(RegisterActivity.this, AccountActivity.class);
                RegisterActivity.this.startActivity(secondActivity);
            } catch (InvalidRegisterException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Error:");
                builder.setMessage(e.getMessage());
                builder.setPositiveButton("check again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(RegisterActivity.this, "try again", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        }
    }

    public void buttonCancelOnClick(View v) {
        Intent prev = new Intent(RegisterActivity.this, AccountActivity.class);
        RegisterActivity.this.startActivity(prev);
    }
}



