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

public class MuseumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set Category Title
        TextView categoryTitle = (TextView) findViewById(R.id.category_title);
        categoryTitle.setText(getText(R.string.museums_title));

        // Add Back (Up) Functionality
        ImageButton backButton = (ImageButton) findViewById(R.id.category_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get String Array Resources
        String[] museumsNames = getResources().getStringArray(R.array.museums_names);
        String[] museumsAdresses = getResources().getStringArray(R.array.museums_addresses);
        String[] museumsDescriptions = getResources().getStringArray(R.array.museums_descriptions);
        String[] museumsHours = getResources().getStringArray(R.array.museums_hours);
        String[] museumsPhones = getResources().getStringArray(R.array.museums_phones);
        int[] museumsImages = {};

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> museums = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < museumsNames.length; i++) {
            museums.add(new Landmark(
                    museumsNames[i],
                    museumsDescriptions[i],
                    museumsAdresses[i],
                    museumsHours[i],
                    "+40 " + museumsPhones[i],
                    R.drawable.history_museum));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(MuseumsActivity.this, museums, listView);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark object at current position
                Landmark museum = museums.get(position);

                // Start intent and send Landmark object to DetailActivity
                Intent detailActivity = new Intent(MuseumsActivity.this, DetailActivity.class);
                detailActivity.putExtra("serialize_data", museum);
                startActivity(detailActivity);

            }
        });
    }
}
