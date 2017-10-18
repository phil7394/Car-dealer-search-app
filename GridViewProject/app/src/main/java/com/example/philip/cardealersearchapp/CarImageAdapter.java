/*
 * Copyright (c) 2017.
 * Author: Philip Joseph Thomas
 */

package com.example.philip.cardealersearchapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Image adapter for CarGridViewMainActivity grid view
 */

public class CarImageAdapter extends BaseAdapter {

    // the application context
    private Context mContext;
    // the list of thumbnail resource ids
    private List<Bitmap> mThumbIds;

    /**
     * @param c
     * @param ids
     */
    public CarImageAdapter(Context c, List<Bitmap> ids) {
        mContext = c;
        this.mThumbIds = ids;
    }


    @Override
    public int getCount() {

        return mThumbIds.size();
    }


    @Override
    public Object getItem(int position) {

        return mThumbIds.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // represents a single item in the grid
        View gridItem;
        // recycle existing view
        if (convertView == null) {
            Log.i("CarImageAdapter", "mPosition = " + position);
            // get the inflater from context
            LayoutInflater inflater = LayoutInflater.from(mContext);
            // inflate the item view with layout
            gridItem = inflater.inflate(R.layout.grid_imagetext_view, parent, false);
        } else {
            gridItem = convertView;
        }
        // get the array of car maker strings
        String[] carMakers = mContext.getResources().getStringArray(R.array.car_makers);
        // get the reference to the text view of the item
        TextView textView = (TextView) gridItem.findViewById(R.id.grid_text);
        // get the reference to the image view of the item
        ImageView imageView = (ImageView) gridItem.findViewById(R.id.grid_image);
        // set text with the car maker string at respective mPosition
        textView.setText(carMakers[position]);
        // set the image view with the resized bitmap
        imageView.setImageBitmap(mThumbIds.get(position));

        return gridItem;
    }
}

