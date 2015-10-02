package com.korwin.rangers.korwinrangers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Sharow on 2015-10-01.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    private static class ViewHolder {
        private TextView itemView;
    }
    public EventAdapter(Context context, ArrayList<Event> items) {
        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Event event = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvValue);
        // Populate the data into the template view using the data object
        tvName.setText(event.getTitle());
        tvHome.setText(event.getDescription());

        return convertView;
    }
}
