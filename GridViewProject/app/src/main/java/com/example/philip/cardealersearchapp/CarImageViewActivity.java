/*
 * Copyright (c) 2017.
 * Author: Philip Joseph Thomas
 */

package com.example.philip.cardealersearchapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Image view activity to display high resolution car image
 */
public class CarImageViewActivity extends AppCompatActivity {

    // the item mPosition
    protected int mPosition;
    // the view to display car image
    protected ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // get the intent from parent activity
        Intent intent = getIntent();
        // get reference to the image view
        mImageView = new ImageView(getApplicationContext());
        Log.i("ContextItem", "mPosition in setimgresource = " + intent.getIntExtra(CarsGridViewMainActivity.EXTRA_RES_ID, 0));
        // get the item position
        mPosition = intent.getIntExtra(CarsGridViewMainActivity.EXTRA_RES_POS, 0);
        // set the image resource id for image view
        mImageView.setImageResource(intent.getIntExtra(CarsGridViewMainActivity.EXTRA_RES_ID, 0));


        // set the content view
        setContentView(mImageView);
        // set the listener for when image is clicked
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the list of maker webpage urls
                String[] makerPages = getResources().getStringArray(R.array.maker_pages);
                // create implicit intent to view the url
                Intent makerPgIntent = new Intent();
                makerPgIntent.setAction(Intent.ACTION_VIEW);
                // set the intent data with url for maker at respective position
                makerPgIntent.setData(Uri.parse(makerPages[mPosition]));
                // start browsable activity
                startActivity(makerPgIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // de-reference the image drawable, so that the garbage collector frees the memory
        mImageView.setImageDrawable(null);
    }
}
