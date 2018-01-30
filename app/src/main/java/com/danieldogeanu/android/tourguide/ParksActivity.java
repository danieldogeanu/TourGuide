package com.danieldogeanu.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ParksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set Category Title
        TextView categoryTitle = (TextView) findViewById(R.id.category_title);
        categoryTitle.setText(getText(R.string.parks_title));

        // Add Back (Up) Functionality
        ImageButton backButton = (ImageButton) findViewById(R.id.category_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get String Array Resources
        String[] parksNames = getResources().getStringArray(R.array.parks_names);
        String[] parksAdresses = getResources().getStringArray(R.array.parks_addresses);
        String[] parksDescriptions = getResources().getStringArray(R.array.parks_descriptions);
        String[] parksHours = getResources().getStringArray(R.array.parks_hours);
        String[] parksPhones = getResources().getStringArray(R.array.parks_phones);
        int[] parksImages = {};

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> parks = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < parksNames.length; i++) {
            parks.add(new Landmark(
                    parksNames[i],
                    parksDescriptions[i],
                    parksAdresses[i],
                    parksHours[i],
                    "+40 " + parksPhones[i],
                    R.drawable.history_museum));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(ParksActivity.this, parks, listView);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark object at current position
                Landmark park = parks.get(position);

                // Start intent and send Landmark object to DetailActivity
                Intent detailActivity = new Intent(ParksActivity.this, DetailActivity.class);
                detailActivity.putExtra("serialize_data", park);
                startActivity(detailActivity);

            }
        });
    }
}
