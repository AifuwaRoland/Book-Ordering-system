package comp3350.amate.presentation;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;
import comp3350.amate.objects.Account;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_info);
        AccessAccount accounts = new AccessAccount();

        Account account = accounts.getSignedInAccount();
        List<String> books = account.getBookmarkList();
        BookmarkEntryAdapter adapter = new BookmarkEntryAdapter((ArrayList<String>) books, this);
        ListView listView = (ListView) findViewById(R.id.bookmarkInfo);
        listView.setAdapter(adapter);
    }
}