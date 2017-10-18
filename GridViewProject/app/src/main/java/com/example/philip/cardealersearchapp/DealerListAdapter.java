/*
 * Copyright (c) 2017.
 * Author: Philip Joseph Thomas
 */

package com.example.philip.cardealersearchapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * The list adapter for Dealer list view
 */

public class DealerListAdapter extends BaseAdapter {

    // application context
    private Context mListCtx;
    // the list of dealers
    private List<Dealer> dealers;

    public DealerListAdapter(Context c, List<Dealer> dealers) {
        mListCtx = c;
        this.dealers = dealers;

    }

    @Override
    public int getCount() {

        return dealers.size();
    }

    @Override
    public Object getItem(int position) {

        return dealers.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // the single item in the list view
        View listItem;

        // recycle existing views
        if (convertView == null) {
            Log.i("CarImageAdapter", "mPosition = " + position);
            // get the layout inflater
            LayoutInflater inflater = LayoutInflater.from(mListCtx);
            // inflate the list item view
            listItem = inflater.inflate(R.layout.list_item_view, parent, false);
        } else {
            listItem = convertView;
        }
        // get the reference to the name text view
        TextView nameView = (TextView) listItem.findViewById(R.id.dealer_name);
        // get the reference to the address text view
        TextView addressView = (TextView) listItem.findViewById(R.id.dealer_address);
        //set the text with dealer name at respective position
        nameView.setText(dealers.get(position).getName());
        //set the text with dealer address at respective position
        addressView.setText(dealers.get(position).getAddress());

        return listItem;
    }
}
