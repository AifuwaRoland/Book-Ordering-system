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

import java.util.List;

import comp3350.amate.R;

public class BookListEntryAdapter extends BaseAdapter implements ListAdapter {
    private final List<String> list;
    private final Context context;

    public BookListEntryAdapter(List<String> newList, Context newContext) {
        list = newList;
        context = newContext;
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
            view = inflater.inflate(R.layout.activity_book_entry, null);
        }

        final TextView listItemText = (TextView) view.findViewById(R.id.bookEntryText);
        listItemText.setText(list.get(position));

        Button moreInfo = (Button) view.findViewById(R.id.bookEntryButton);
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
