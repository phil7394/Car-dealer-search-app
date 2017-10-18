/*
 * Copyright (c) 2017.
 * Author: Philip Joseph Thomas
 */

package com.example.philip.cardealersearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The list view activity to display list of dealers
 */
public class DealerListViewActivity extends AppCompatActivity {

    // the list of dealers for each car in a list
    List<List<Dealer>> dealers = new ArrayList<List<Dealer>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // parse and load the dealer data
            loadDealers();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // set the layout view
        setContentView(R.layout.activity_list_view);
        // get the reference to the list view
        ListView listView = (ListView) findViewById(R.id.listView);
        // get the intent
        Intent intent = getIntent();
        // get the position of the gridItem
        int gridPos = intent.getIntExtra(CarsGridViewMainActivity.EXTRA_RES_POS, 0);
        // set the adapter fot list view
        listView.setAdapter(new DealerListAdapter(this, dealers.get(gridPos)));


    }

    /**
     * parse and load dealer data in JSON format from strings.xml
     *
     * @throws JSONException
     */
    private void loadDealers() throws JSONException {
        // get the list of all dealers for all the types of cars
        String[] dealersArray = getResources().getStringArray(R.array.dealers);
        String name;
        String address;
        // for each car type
        for (int j = 0; j < dealersArray.length; j++) {
            // get the JSON object by parsing the string
            JSONObject brandJson = new JSONObject(dealersArray[j]);
            // get all the dealers for that car type
            JSONArray brandDealerJsonList = brandJson.getJSONArray("dealerList");
            List<Dealer> dealerList = new ArrayList<>();
            // for each dealer
            for (int k = 0; k < brandDealerJsonList.length(); k++) {
                // get the JSON objecct
                JSONObject jo = brandDealerJsonList.getJSONObject(k);
                // get the name
                name = jo.getString("name");
                // get the address
                address = jo.getString("address");
                Log.i("Dealers", "name = " + name + " address = " + address);
                // create dealer with the name and address
                Dealer carDealer = new Dealer(name, address);
                // add Dealer to dealerList
                dealerList.add(carDealer);

            }
            // add the dealerList for each car type to dealers
            dealers.add(dealerList);
        }

    }

}
