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
//        String[] museumsMapUris = {}; // TODO: Add String Array of Map URIs for Museums
        String mapUri = "https://www.google.com/maps/dir//Palace+of+Parliament,+Strada+Izvor+2-4,+Bucure%C8%99ti/@44.4275035,26.0873506,15z/data=!4m16!1m6!3m5!1s0x0:0x2b1089f802abaddc!2sPalace+of+Parliament!8m2!3d44.4275035!4d26.0873506!4m8!1m0!1m5!1m1!1s0x40b1ff427bee28c1:0x2b1089f802abaddc!2m2!1d26.0873506!2d44.4275035!3e3";
        int[] museumsImages = {
                R.drawable.national_art_museum_of_romania,
                R.drawable.dimitrie_gusti_national_village_museum,
                R.drawable.grigore_antipa_national_museum_of_natural_history,
                R.drawable.romanian_peasant_museum,
                R.drawable.national_museum_of_romanian_history,
                R.drawable.curtea_veche,
                R.drawable.national_museum_of_contemporary_art,
                R.drawable.jewish_museum,
                R.drawable.national_military_museum,
                R.drawable.museum_of_the_romanian_railways
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> museums = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < museumsNames.length; i++) {
            museums.add(new Landmark(
                    museumsNames[i],
                    museumsDescriptions[i],
                    museumsAdresses[i],
                    museumsHours[i],
                    addPrefix(museumsPhones[i]),
//                    museumsMapUris[i],
                    mapUri,
                    museumsImages[i]));
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

    private String addPrefix(String phone) {
        if (!phone.isEmpty()) {
            return "+40 " + phone;
        } else {
            return phone;
        }
    }
}
