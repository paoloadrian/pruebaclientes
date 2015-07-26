package android.hmkcode.com.yomeseroclientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alex on 4/11/15.
 */
public class ItemsArrayAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private ArrayList<Item> items;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();

    public ItemsArrayAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.item_list, items);
        this.context = context;
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            names.add("Nombre: "+items.get(i).item_name);
            descriptions.add("Tipo: "+items.get(i).item_type+" - Precio: Bs. "+items.get(i).item_price);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
        textView.setText(names.get(position));
        textView2.setText(descriptions.get(position));
        TextView quantity = (TextView)rowView.findViewById(R.id.cant);
        Button plus = (Button) rowView.findViewById(R.id.plusbutton);
        Button minus = (Button) rowView.findViewById(R.id.minusbutton);
        ButtonClickListener listener = new ButtonClickListener(quantity);
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);

        // change the icon for Windows and iPhone
        return rowView;
    }

    private class ButtonClickListener implements View.OnClickListener {
        private TextView textView;
        public ButtonClickListener(TextView tv) {
            textView = tv;
        }

        @Override
        public void onClick(View v) {
            int quantity = Integer.parseInt(textView.getText().toString());
            if (v.getId() == R.id.plusbutton) {
                textView.setText(Integer.toString(quantity + 1));
            }
            else {
                if(quantity > 0)
                    textView.setText(Integer.toString(quantity - 1));
            }
        }
    }
}
