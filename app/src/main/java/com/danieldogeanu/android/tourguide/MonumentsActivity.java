package com.danieldogeanu.android.tourguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        categoryTitle.setText(getText(R.string.monuments));

        // Add Back (Up) Functionality
        ImageButton backButton = (ImageButton) findViewById(R.id.category_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<Landmark> monuments = new ArrayList<>();

        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));
        monuments.add(new Landmark("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", R.drawable.history_museum));

        ListView listView = (ListView) findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(MonumentsActivity.this, monuments, listView);
        listView.setAdapter(adapter);
    }
}
