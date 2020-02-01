package online.propaans.goodr.goodr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Selllist extends ArrayAdapter<Sell>{

    private Activity context;
    List<Sell> sells;

    public Selllist(Activity context, List<Sell> sells) {
        super(context, R.layout.layout_crop_list, sells);
        this.context = context;
        this.sells = sells;

    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_crop_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.sellViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.sellViewGenre);
        TextView textViewNumber = (TextView)listViewItem.findViewById(R.id.sellViewNumber);
        TextView textViewQauntity = (TextView)listViewItem.findViewById(R.id.sellViewQuantity);
        TextView textViewAddress = (TextView)listViewItem.findViewById(R.id.sellViewAddress);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.sellViewPrice);

        Sell sell = sells.get(position);
        textViewName.setText("Name" + " -  "  + sell.getSelluserName());
        textViewGenre.setText("Food Type" + " -  "  +sell.getSellfood());
        textViewNumber.setText("Number" + " -  "  +sell.getSelluserNumber());
        textViewAddress.setText("Address" + " -  " +sell.getSelluserAddress());
        textViewQauntity.setText("Weight" + " -  " +sell.getSelluserQuantity()+ "Kg");
        textViewPrice.setText("Price" + "_" + "₹"+sell.getSellPrice());

        return listViewItem;
    }
}