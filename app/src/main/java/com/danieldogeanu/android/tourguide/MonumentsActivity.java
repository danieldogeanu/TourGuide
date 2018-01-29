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

        final ArrayList<Landmark> monuments = new ArrayList<>();

        monuments.add(new Landmark("Herastrau Park", "Bucharest", R.drawable.herastrau_park));
        monuments.add(new Landmark("Palace of the Parliament", "Strada Izvor 2-4, Bucharest", R.drawable.casa_poporului));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Military Museum", "Strada Mircea Vulcănescu 125-127, Bucharest", R.drawable.military_museum));
        monuments.add(new Landmark("Museum of the Romanian Railways", "Strada Gării de Nord, Bucharest", R.drawable.railroad_museum));

        ListView listView = (ListView) findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(MonumentsActivity.this, monuments, listView);
        listView.setAdapter(adapter);

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
}
