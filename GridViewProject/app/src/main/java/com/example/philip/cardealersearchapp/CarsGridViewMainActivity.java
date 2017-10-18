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
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * The main activity to display car images in a grid view
 */
public class CarsGridViewMainActivity extends AppCompatActivity {

    protected static final String EXTRA_RES_ID = "ID";
    protected static final String EXTRA_RES_POS = "POS";

    // list of all image resource ids
    private ArrayList<Integer> mCarsHDImgIds = new ArrayList<Integer>(
            Arrays.asList(R.drawable.audi_hd,
                    R.drawable.bmw_hd,
                    R.drawable.cadillac_hd,
                    R.drawable.chevrolet_hd,
                    R.drawable.dodge_hd,
                    R.drawable.ford_hd,
                    R.drawable.honda_hd,
                    R.drawable.landrover_hd,
                    R.drawable.lexus_hd,
                    R.drawable.mercedesbenz_hd,
                    R.drawable.nissan_hd,
                    R.drawable.porsche_hd,
                    R.drawable.toyota_hd,
                    R.drawable.volkswagen_hd));


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the main layout
        setContentView(R.layout.activity_main);
        // get reference to gridView
        GridView gridView = (GridView) findViewById(R.id.gridView);

        // if the resized bitmaps list is empty
        if (ImageResizer.resizedBmsList.isEmpty()) {
            Log.i("ImageResizer", "resizedBmsList is empty, call resizeImages()...");
            // resize and load the images in the static list, so that resizing occurs only when
            // the app loads for the first time and reuses the same reference on subsequent onCreate() calls.
            // By doing this, there is minimum delay whenever this activity is created.
            ImageResizer.resizeImages(getApplicationContext(), mCarsHDImgIds);
        }

        // set the image adapter for the gridView
        gridView.setAdapter(new CarImageAdapter(this, ImageResizer.resizedBmsList));
        // register gridView for a context menu
        registerForContextMenu(gridView);
        // Set a listener when an item is clicked on the gridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //Create an Intent to start the CarImageViewActivity
                Intent intent = new Intent(CarsGridViewMainActivity.this,
                        CarImageViewActivity.class);
                Log.i("ContextItem", "onShortClickPosition = " + id);
                // Add the ID of the thumbnail to display, as an Intent Extra
                intent.putExtra(EXTRA_RES_ID, mCarsHDImgIds.get(position));
                // Add the mPosition of grid item, as an Intent extra
                intent.putExtra(EXTRA_RES_POS, position);
                // Start the CarImageViewActivity
                startActivity(intent);
            }
        });
    }


    /**
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // get the inflater for menu
        MenuInflater inflater = getMenuInflater();
        // inflate the menu layout for the context menu
        inflater.inflate(R.menu.cars_ctx_menu, menu);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // get the menu info for selected item
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // get the item id
        long id = info.id;
        // get the item's mPosition
        int position = info.position;
        Log.i("ContextItem", "info.mPosition onCtxItemSelected = " + position);
        switch (item.getItemId()) {
            // start CarImageViewActivity using explicit intent
            case R.id.viewImage:
                Intent viewImgIntent = new Intent(CarsGridViewMainActivity.this, CarImageViewActivity.class);
                viewImgIntent.putExtra(EXTRA_RES_ID, mCarsHDImgIds.get(position));
                viewImgIntent.putExtra(EXTRA_RES_POS, position);
                startActivity(viewImgIntent);
                return true;
            // start a browser activity to show maker webpage using implicit intent
            case R.id.makerPage:
                String[] makerPages = getResources().getStringArray(R.array.maker_pages);
                Intent makerPgIntent = new Intent();
                makerPgIntent.setAction(Intent.ACTION_VIEW);
                makerPgIntent.setData(Uri.parse(makerPages[position]));
                startActivity(makerPgIntent);
                return true;
            // start DealerListActivity to list of dealers using explicit intent
            case R.id.carDealers:
                Intent listDealersIntent = new Intent(CarsGridViewMainActivity.this, DealerListViewActivity.class);
                listDealersIntent.putExtra(EXTRA_RES_POS, position);
                startActivity(listDealersIntent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

