package comp3350.amate.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import comp3350.amate.R;
import comp3350.amate.R.id;

public class ReviewActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Intent i = getIntent();
        final String title = i.getStringExtra("id");

        final Button submitReview = (Button) findViewById(id.submitReview);
        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rateGroup = (RadioGroup) findViewById(id.rateGroup);
                int selectedRate = rateGroup.getCheckedRadioButtonId();

                if (selectedRate != -1) {
                    EditText reviewText = (EditText) findViewById(id.reviewText);
                    String review = reviewText.getText().toString();

                    RadioButton rateNumber = (RadioButton) findViewById(selectedRate);
                    String rating = rateNumber.getText().toString();

                    Intent result = new Intent();
                    result.putExtra("review", review);
                    result.putExtra("rating", rating);
                    result.putExtra("id", title);
                    setResult(RESULT_OK, result);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                    builder.setTitle("No rating selected");
                    builder.setMessage("Please select a number rating for this review.");
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(ReviewActivity.this, "Select a number rating", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    public void buttonSubmitOnClick(View v) {
        Intent review = new Intent(ReviewActivity.this, AccountActivity.class);
        ReviewActivity.this.startActivity(review);
    }
}
