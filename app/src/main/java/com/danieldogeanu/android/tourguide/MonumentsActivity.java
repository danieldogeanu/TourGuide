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

public class MonumentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set Category Title
        TextView categoryTitle = (TextView) findViewById(R.id.category_title);
        categoryTitle.setText(getText(R.string.monuments_title));

        // Add Back (Up) Functionality
        ImageButton backButton = (ImageButton) findViewById(R.id.category_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get String Array Resources
        String[] monumentsNames = getResources().getStringArray(R.array.monuments_names);
        String[] monumentsAdresses = getResources().getStringArray(R.array.monuments_addresses);
        String[] monumentsDescriptions = getResources().getStringArray(R.array.monuments_descriptions);
        String[] monumentsHours = getResources().getStringArray(R.array.monuments_hours);
        String[] monumentsPhones = getResources().getStringArray(R.array.monuments_phones);
//        String[] monumentsMapUris = {}; // TODO: Add String Array of Map URIs for Monuments
        String mapUri = "https://www.google.com/maps/dir//Palace+of+Parliament,+Strada+Izvor+2-4,+Bucure%C8%99ti/@44.4275035,26.0873506,15z/data=!4m16!1m6!3m5!1s0x0:0x2b1089f802abaddc!2sPalace+of+Parliament!8m2!3d44.4275035!4d26.0873506!4m8!1m0!1m5!1m1!1s0x40b1ff427bee28c1:0x2b1089f802abaddc!2m2!1d26.0873506!2d44.4275035!3e3";
        int[] monumentsImages = {
                R.drawable.arcul_de_triumf,
                R.drawable.memorial_of_rebirth,
                R.drawable.carol_i_statue,
                R.drawable.kilometre_zero,
                R.drawable.george_enescu_statue,
                R.drawable.nations_heroes_memorial
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> monuments = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < monumentsNames.length; i++) {
            monuments.add(new Landmark(
                    monumentsNames[i],
                    monumentsDescriptions[i],
                    monumentsAdresses[i],
                    monumentsHours[i],
                    addPrefix(monumentsPhones[i]),
//                    monumentsMapUris[i],
                    mapUri,
                    monumentsImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(MonumentsActivity.this, monuments, listView);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark object at current position
                Landmark monument = monuments.get(position);

                // Start intent and send Landmark object to DetailActivity
                Intent detailActivity = new Intent(MonumentsActivity.this, DetailActivity.class);
                detailActivity.putExtra("serialize_data", monument);
                startActivity(detailActivity);

            }
        });
    }

    private String addPrefix(String phone) {
        if (!phone.isEmpty()) {
            return "+40 " + phone;
        } else {
            return phone;
        }
    }
}
