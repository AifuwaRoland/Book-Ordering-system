package comp3350.amate.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.business.AccessBooks;
import comp3350.amate.business.AccessReview;
import comp3350.amate.objects.Account;
import comp3350.amate.objects.Book;
import comp3350.amate.objects.Review;

public class BookActivity extends AppCompatActivity {
    private static final int REVIEW_BOOK_REQUEST = 1;
    private AccessBooks bookList;
    private AccessReview reviewList;
    private String id;
    private Account currAccount;
    private AccessAccount accounts;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        accounts = new AccessAccount();
        bookList = new AccessBooks();
        reviewList = new AccessReview();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");  //title of bookmark

        bookList = new AccessBooks();
        book = bookList.getBook(id);

        //Display book details
        ArrayList<String> bookDetails = new ArrayList<>();
        bookDetails.add(String.format("Title: %s", book.getBookTitle()));
        bookDetails.add(String.format("Author: %s", book.getBookAuthor()));
        bookDetails.add(String.format("Published on: %s", book.getBookReleaseDate()));
        bookDetails.add(String.format("Description: %s", book.getBookDescription()));
        bookDetails.add(String.format("Rating: %d.%d", book.getBookRating() / 10, book.getBookRating() % 10));
        bookDetails.add(String.format("Total Ratings: %d", book.getTotalRatings()));
        bookDetails.add(String.format("Available as: %s", book.getFormat()));
        bookDetails.add(String.format("ISBN: %d", book.getISBN()));
        bookDetails.add(String.format("Price: $%d.%d", book.getBookPrice() / 100, book.getBookPrice() % 100));
        Date arrival = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(arrival);
        c.add(Calendar.DATE, 10);
        arrival = c.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        bookDetails.add(String.format("Book will arrive 10 days from today (on %s)", dateFormat.format(arrival)));

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookDetails);
        ListView listView1 = (ListView) findViewById(R.id.bookInfo);
        listView1.setAdapter(arrayAdapter1);

        //Display reviews
        List<Review> bookReviews = reviewList.getReviews(id);
        ArrayList<String> reviews = new ArrayList<>();
        for (int i = 0; i < bookReviews.size(); i++)
            reviews.add("Rating: " + Integer.toString(bookReviews.get(i).getNumStars()) + "\n" + bookReviews.get(i).getWrittenReview());

        ListView listView2 = (ListView) findViewById(R.id.Reviews);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviews);
        listView2.setAdapter(arrayAdapter2);

        final Button review = (Button) findViewById(R.id.reviewButton);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent k = new Intent(getApplicationContext(), ReviewActivity.class);
                    k.putExtra("id", id);
                    startActivityForResult(k, REVIEW_BOOK_REQUEST);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Button addcart = (Button) findViewById(R.id.purchaseButton);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent test = new Intent(BookActivity.this, CartActivity.class);
                    test.putExtra("title", id);
                    BookActivity.this.startActivity(test);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Button addBookmark = (Button) findViewById(R.id.addBookmarkButton);
        addBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currAccount = accounts.getSignedInAccount();
                    if (currAccount != null) {
                        if (currAccount.getBookmarkList().contains(id)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
                            builder.setTitle("Error");
                            builder.setMessage("This book is already bookmarked");
                            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            builder.show();
                        } else {
                            accounts.addBookmark(id);
                            accounts.updateUserBookmarks(currAccount.getBookmarkList());
                            AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
                            builder.setTitle("Success");
                            builder.setMessage("Added to bookmarks");
                            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            builder.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage("You must be signed in to use this feature");
                        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Button recommendBookSameAuthor = (Button) findViewById(R.id.bookRecommendAuthor);
        recommendBookSameAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent test = new Intent(BookActivity.this, BookListActivity.class);
                    test.putExtra("query", book.getBookAuthor());
                    String searchType = "author";
                    test.putExtra("type", searchType);
                    BookActivity.this.startActivity(test);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REVIEW_BOOK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String review = data.getStringExtra("review");
                String rating = data.getStringExtra("rating");
                String title = data.getStringExtra("id");
                Review r = new Review(title, review, Integer.parseInt(rating));
                reviewList.addReviewDB(r);
                bookList.rateBook(title, Integer.parseInt(rating));

                Intent refresh = new Intent(this, BookActivity.class);
                refresh.putExtra("id", title);
                startActivity(refresh);
                this.finish();
            }
        }
    }
}