package online.propaans.goodr.goodr;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CausesList extends ArrayAdapter<Causes>{

    private Activity context;
    List<Causes> causes;

    public CausesList(Activity context, List<Causes> causes) {
        super(context, R.layout.layout_causes_list, causes);
        this.context = context;
        this.causes = causes;

    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_causes_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.c_name);
        TextView textViewPeople = (TextView) listViewItem.findViewById(R.id.c_people);
        TextView textViewState = (TextView)listViewItem.findViewById(R.id.c_state);


        return listViewItem;
    }
}