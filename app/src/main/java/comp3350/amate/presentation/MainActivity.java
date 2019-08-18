package comp3350.amate.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.amate.R;
import comp3350.amate.application.Main;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.business.AccessBooks;
import comp3350.amate.objects.Account;
import comp3350.amate.objects.Book;

public class MainActivity extends AppCompatActivity {
    private static boolean setDatabase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseToDevice();


        final Button bestSeller = (Button) findViewById(R.id.getBestSeller);
        bestSeller.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    AccessBooks accessBooks = new AccessBooks();
                    Book bestSeller = accessBooks.getBestSeller();

                    Intent getBestSellerIntent = new Intent(MainActivity.this, BookActivity.class);
                    getBestSellerIntent.putExtra("id", bestSeller.getBookTitle());
                    MainActivity.this.startActivity(getBestSellerIntent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Button searchOrder = (Button) findViewById(R.id.searchOrderButtonMain);
        searchOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                try {
                    Intent k = new Intent(MainActivity.this, OrderSearchActivity.class);
                    MainActivity.this.startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        final Button searchBooks = (Button) findViewById(R.id.search_title);
        searchBooks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    EditText editText = (EditText) findViewById(R.id.search_view);
                    String query = editText.getText().toString();
                    String type = "title";

                    Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                    intent.putExtra("query", query);
                    intent.putExtra("type", type);
                    MainActivity.this.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Button searchBooksByAuthor = (Button) findViewById(R.id.search_author);
        searchBooksByAuthor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    EditText editText = (EditText) findViewById(R.id.search_view);
                    String query = editText.getText().toString();
                    String type = "author";

                    Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                    intent.putExtra("query", query);
                    intent.putExtra("type", type);
                    MainActivity.this.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Button showAllBooks = (Button) findViewById(R.id.showAllBooksButton);
        showAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                intent.putExtra("query", "showAll");
                MainActivity.this.startActivity(intent);
            }
        });

        final Button signIn = (Button) findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent signInIntent = new Intent(MainActivity.this, AccountActivity.class);
                    MainActivity.this.startActivity(signInIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        final Button bookmarks = (Button) findViewById(R.id.bookmark);
        bookmarks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent k = new Intent(MainActivity.this, BookmarkActivity.class);
                    AccessAccount aa = new AccessAccount();
                    Account account = aa.getSignedInAccount();
                    if (account != null) {
                        MainActivity.this.startActivity(k);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

        final Button shoppingCart = (Button) findViewById(R.id.cartButton);
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

        final Button accountProfile = (Button) findViewById(R.id.profile);
        accountProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AccessAccount aa = new AccessAccount();
                Account account = aa.getSignedInAccount();
                if (account != null) {
                    Intent i = new Intent(MainActivity.this, AccountInfoActivity.class);
                    i.putExtra("userID", account.getUserID());
                    i.putExtra("userName", account.getUserName());
                    i.putExtra("userNum", account.getUserNum());
                    i.putExtra("userAddress", account.getUserAddr());
                    i.putExtra("userEmail", account.getUserEmail());
                    MainActivity.this.startActivity(i);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("You are not signed in");
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                }

            }
        });

    }

    private void copyDatabaseToDevice() {
        if (!setDatabase) {
            final String DB_PATH = "db";

            String[] assetNames;
            Context context = getApplicationContext();
            File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
            AssetManager assetManager = getAssets();

            try {
                assetNames = assetManager.list(DB_PATH);
                for (int i = 0; i < assetNames.length; i++) {
                    assetNames[i] = DB_PATH + "/" + assetNames[i];
                }

                copyAssetsToDirectory(assetNames, dataDirectory);

                Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());
                setDatabase = true;

            } catch (final IOException ioe) {
                Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
            }
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
