package comp3350.amate.presentation;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.view.LayoutInflater;

import comp3350.amate.R;
import comp3350.amate.business.AccessBooks;
import comp3350.amate.business.AccessShoppingCart;
import comp3350.amate.objects.Book;

import java.util.List;

public class Item extends BaseAdapter implements ListAdapter {
    private final List<String> list;
    private final Context context;

    public Item(List<String> list, Context context) {
        this.list = list;
        this.context = context;
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
            view = inflater.inflate(R.layout.activity_item, null);
        }

        TextView listItemText = (TextView) view.findViewById(R.id.item);
        listItemText.setText(list.get(position));

        Button deleteBtn = (Button) view.findViewById(R.id.removecart);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessShoppingCart cart = new AccessShoppingCart();
                AccessBooks books = new AccessBooks();
                Book book = books.getBook(list.get(position));
                cart.removeFromCart(book);
                notifyDataSetChanged();
            }
        });
        return view;
    }


}