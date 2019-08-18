package comp3350.amate.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.amate.R;
import comp3350.amate.business.AccessBooks;
import comp3350.amate.objects.Book;

public class BookListActivity extends AppCompatActivity {
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Intent intent = getIntent();
        String searchText = intent.getStringExtra("query");
        AccessBooks accessBooks = new AccessBooks();
        List<String> bookTitles = new ArrayList<>();

        if (searchText.equals("showAll")) {
            bookList = accessBooks.getBookSequential();
            for (int i = 0; i < bookList.size(); i++)
                bookTitles.add(bookList.get(i).getBookTitle());

            BookListEntryAdapter adapter = new BookListEntryAdapter(bookTitles, this);
            ListView listView = (ListView) findViewById(R.id.bookList);
            listView.setAdapter(adapter);
        } else {
            String type = intent.getStringExtra("type");
            if (type.equals("title"))
                bookList = accessBooks.searchTitles(searchText);
            else if (type.equals("author"))
                bookList = accessBooks.searchAuthor(searchText);

            if (bookList.size() > 0) {
                bookTitles = new ArrayList<>();
                Book book;
                for (int i = 0; i < bookList.size(); i++) {
                    book = bookList.get(i);
                    bookTitles.add(book.getBookTitle());
                }
                BookListEntryAdapter adapter = new BookListEntryAdapter(bookTitles, this);
                ListView listView = (ListView) findViewById(R.id.bookList);
                listView.setAdapter(adapter);
            } else {
                TextView textView = (TextView) findViewById(R.id.bookListError);
                textView.setText("Could not find books related books");
            }
        }
    }
}
