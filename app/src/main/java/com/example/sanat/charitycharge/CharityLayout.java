package com.example.sanat.charitycharge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by david on 1/27/18.
 */

public class CharityLayout extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> titles, descriptions;
    private final ArrayList<String> icons;

    public CharityLayout(Context context, ArrayList<String> titles, ArrayList<String> descriptions, ArrayList<String> icons) {
        super(context, -1, titles);
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.icons = icons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_charity_row, parent, false);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        TextView title = (TextView) rowView.findViewById(R.id.firstLine);
        TextView description = (TextView) rowView.findViewById(R.id.secondLine);
        title.setText(titles.get(position));
        description.setText(descriptions.get(position));
        Glide.with(context).load(icons.get(position)).into(icon);
        return rowView;
    }

}
