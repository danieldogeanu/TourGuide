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

public class PalacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set Category Title
        TextView categoryTitle = (TextView) findViewById(R.id.category_title);
        categoryTitle.setText(getText(R.string.palaces_title));

        // Add Back (Up) Functionality
        ImageButton backButton = (ImageButton) findViewById(R.id.category_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get String Array Resources
        String[] palacesNames = getResources().getStringArray(R.array.palaces_names);
        String[] palacesAdresses = getResources().getStringArray(R.array.palaces_addresses);
        String[] palacesDescriptions = getResources().getStringArray(R.array.palaces_descriptions);
        String[] palacesHours = getResources().getStringArray(R.array.palaces_hours);
        String[] palacesPhones = getResources().getStringArray(R.array.palaces_phones);
        int[] palacesImages = {};

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> palaces = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < palacesNames.length; i++) {
            palaces.add(new Landmark(
                    palacesNames[i],
                    palacesDescriptions[i],
                    palacesAdresses[i],
                    palacesHours[i],
                    "+40 " + palacesPhones[i],
                    R.drawable.history_museum));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(PalacesActivity.this, palaces, listView);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark object at current position
                Landmark palace = palaces.get(position);

                // Start intent and send Landmark object to DetailActivity
                Intent detailActivity = new Intent(PalacesActivity.this, DetailActivity.class);
                detailActivity.putExtra("serialize_data", palace);
                startActivity(detailActivity);

            }
        });
    }
}
