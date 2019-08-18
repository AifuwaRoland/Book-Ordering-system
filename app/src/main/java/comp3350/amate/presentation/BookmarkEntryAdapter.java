package comp3350.amate.presentation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import comp3350.amate.R;
import comp3350.amate.business.AccessAccount;

import java.util.ArrayList;


public class BookmarkEntryAdapter extends BaseAdapter implements ListAdapter {
    private final ArrayList<String> list;  //titles
    private final Context context;
    private final AccessAccount accessAccounts;


    public BookmarkEntryAdapter(ArrayList<String> newList, Context newContext) {
        list = newList;
        context = newContext;
        accessAccounts = new AccessAccount();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_bookmark_entry, null);
        }

        final TextView listItemText = (TextView) view.findViewById(R.id.bookmarkEntryText);
        listItemText.setText(list.get(position));

        Button remove = (Button) view.findViewById(R.id.bookmarkEntryButton);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);  //also removes from signed in account's bookmarks
                accessAccounts.updateUserBookmarks(list);
                notifyDataSetChanged();
            }
        });

        Button moreInfo = (Button) view.findViewById(R.id.bookmarkMoreInfoButton);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BookActivity.class);
                i.putExtra("id", listItemText.getText());
                context.startActivity(i);
            }
        });

        return view;
    }
}
