package comp3350.amate.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import comp3350.amate.R;
import comp3350.amate.business.AccessCard;
import comp3350.amate.business.CardValidation;
import comp3350.amate.objects.Card;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.objects.Account;
import comp3350.amate.business.CardException;

public class CaOneActivity extends Activity {
    private AccessCard accessCard;
    private Card credit;
    private Account signedInAccount;
    private int orderID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcard);
        Intent intent = getIntent();
        orderID = intent.getIntExtra("orderID", -1);

        AccessAccount accessAccount = new AccessAccount();
        signedInAccount = accessAccount.getSignedInAccount();
        accessCard = new AccessCard();

        if (signedInAccount != null) {
            credit = accessCard.getCard(signedInAccount.getUserID());
        }

        if (credit != null) {
            Card oneCard = credit;
            EditText numText = (EditText) findViewById(R.id.editText);
            EditText holdText = (EditText) findViewById(R.id.editText3);
            EditText dateText = (EditText) findViewById(R.id.editText4);
            EditText cvvText = (EditText) findViewById(R.id.editText5);

            numText.setText(Long.toString(oneCard.getCardNumber()));
            holdText.setText(oneCard.getCardHolder());
            dateText.setText(oneCard.getExpiryDate());
            cvvText.setText(Integer.toString(oneCard.getCVV()));
        }
    }

    public void buttonChangeOnClick(View v) {
        EditText editCardNum = (EditText) findViewById(R.id.editText);
        EditText editHolder = (EditText) findViewById(R.id.editText3);
        EditText editDate = (EditText) findViewById(R.id.editText4);
        EditText editCVV = (EditText) findViewById(R.id.editText5);
        CardValidation test = new CardValidation();

        long num = 0L;
        if (editCardNum.getText().toString().length() != 0) {
            num = Long.parseLong(editCardNum.getText().toString());
        }
        String date = editDate.getText().toString();
        String name = editHolder.getText().toString();
        int cvv = 0;
        if (editCVV.getText().toString().length() != 0) {
            cvv = Integer.parseInt(editCVV.getText().toString());
        }

        Card newCard = new Card(num, name, date, cvv, "Guest");
        if (signedInAccount != null) {
            if (accessCard.getCard(signedInAccount.getUserID()) == null) {
                try {
                    newCard = new Card(num, name, date, cvv, signedInAccount.getUserID());
                    accessCard.insertCard(newCard);
                } catch (CardException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CaOneActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage(e.getMessage());
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }
            } else {
                newCard = new Card(num, name, date, cvv, signedInAccount.getUserID());
            }
        }

        try {
            Card hold = accessCard.updateCard(newCard);
            Intent carder = new Intent(CaOneActivity.this, CaTwoActivity.class);
            carder.putExtra("orderID", orderID);
            CaOneActivity.this.startActivity(carder);

        } catch (Exception e) {
            Messages.warning(this, e.toString());
        }
    }

}
