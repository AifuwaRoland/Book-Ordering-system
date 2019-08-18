package comp3350.amate.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comp3350.amate.R;

public class OrderSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search);

        Button button = (Button) findViewById(R.id.searchOrderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.orderSearch);
                if (!(editText.getText().toString().isEmpty())) {
                    int orderID = Integer.parseInt(editText.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), OrderInfoActivity.class);
                    intent.putExtra("orderID", orderID);
                    startActivity(intent);
                }
            }
        });
    }
}
