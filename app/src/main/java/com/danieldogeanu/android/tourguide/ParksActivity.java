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
        String[] parksMapUris = {
                "https://www.google.com/maps/dir//Zoo+Bucharest,+Strada+Vadul+Moldovei+4,+Bucure%C8%99ti+077190/@44.5172694,26.1044899,15z/data=!4m16!1m6!3m5!1s0x0:0x23f987bbc405cd2a!2sZoo+Bucharest!8m2!3d44.5172694!4d26.1044899!4m8!1m0!1m5!1m1!1s0x40b202dab1f665f5:0x23f987bbc405cd2a!2m2!1d26.1044899!2d44.5172694!3e3",
                "https://www.google.com/maps/dir//King+Michael+I+Park,+Bucure%C8%99ti/@44.4702015,26.0827527,15z/data=!4m16!1m6!3m5!1s0x0:0x57f60ea89ec15526!2sKing+Michael+I+Park!8m2!3d44.4702015!4d26.0827527!4m8!1m0!1m5!1m1!1s0x40b20213bb079091:0x57f60ea89ec15526!2m2!1d26.0827527!2d44.4702015!3e3",
                "https://www.google.com/maps/dir//Ci%C8%99migiu+Park,+Bulevardul+Regina+Elisabeta,+Bucure%C8%99ti+030167/@44.4369687,26.0909837,15z/data=!4m16!1m6!3m5!1s0x0:0x3e98ab275166423b!2sCi%C8%99migiu+Park!8m2!3d44.4369687!4d26.0909837!4m8!1m0!1m5!1m1!1s0x40b1ff438f7e6fcf:0x3e98ab275166423b!2m2!1d26.0909837!2d44.4369687!3e3",
                "https://www.google.com/maps/dir//Youth+Park,+Bucure%C8%99ti/@44.4004916,26.1084371,15z/data=!4m16!1m6!3m5!1s0x0:0x9c229d7528e44f8e!2sYouth+Park!8m2!3d44.4004916!4d26.1084371!4m8!1m0!1m5!1m1!1s0x40b1ffaad2d5dcff:0x9c229d7528e44f8e!2m2!1d26.1084371!2d44.4004916!3e3",
                "https://www.google.com/maps/dir//Alexandru+Ioan+Cuza+Park,+Bucure%C8%99ti/@44.4255117,26.1535332,15z/data=!4m16!1m6!3m5!1s0x0:0xf0489031c3cf5127!2sAlexandru+Ioan+Cuza+Park!8m2!3d44.4255117!4d26.1535332!4m8!1m0!1m5!1m1!1s0x40b1feb663d02dd3:0xf0489031c3cf5127!2m2!1d26.1535332!2d44.4255117!3e3",
                "https://www.google.com/maps/dir//Carol+Park,+Bulevardul+M%C4%83r%C4%83%C8%99e%C8%99ti,+Bucure%C8%99ti/@44.4186655,26.0965164,15z/data=!4m16!1m6!3m5!1s0x0:0xbd8b38c1281359f0!2sCarol+Park!8m2!3d44.4186655!4d26.0965164!4m8!1m0!1m5!1m1!1s0x40b1ff0e5e8c275b:0xbd8b38c1281359f0!2m2!1d26.0965164!2d44.4186655!3e3",
                "https://www.google.com/maps/dir//Drumul+Taberei+Park,+Drumul+Taberei,+Bucure%C8%99ti/@44.4208643,26.0313024,15z/data=!4m16!1m6!3m5!1s0x0:0x8772154e0f9b91ea!2sDrumul+Taberei+Park!8m2!3d44.4208643!4d26.0313024!4m8!1m0!1m5!1m1!1s0x40b20036c2aff6b1:0x8772154e0f9b91ea!2m2!1d26.0313024!2d44.4208643!3e3",
                "https://www.google.com/maps/dir//Botanic+Garden,+%C8%98oseaua+Cotroceni+32,+Bucure%C8%99ti/@44.4372286,26.0626771,15z/data=!4m16!1m6!3m5!1s0x0:0x301135551489fb73!2sBotanic+Garden!8m2!3d44.4372286!4d26.0626771!4m8!1m0!1m5!1m1!1s0x40b201dd97d58297:0x301135551489fb73!2m2!1d26.0626771!2d44.4372286!3e3",
                "https://www.google.com/maps/dir//Kiseleff+Park,+Bucure%C8%99ti/@44.4576517,26.0836661,15z/data=!4m16!1m6!3m5!1s0x0:0x2fe176e5d7d1b1ca!2sKiseleff+Park!8m2!3d44.4576517!4d26.0836661!4m8!1m0!1m5!1m1!1s0x40b20203c31f6dab:0x2fe176e5d7d1b1ca!2m2!1d26.0836661!2d44.4576517!3e3",
        };
        int[] parksImages = {
                R.drawable.baneasa_zoo,
                R.drawable.herastrau_park,
                R.drawable.cismigiu_gardens,
                R.drawable.tineretului_park,
                R.drawable.alexandru_ioan_cuza_park,
                R.drawable.carol_park,
                R.drawable.drumul_taberei_park,
                R.drawable.bucharest_botanical_garden,
                R.drawable.kiseleff_park
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> parks = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < parksNames.length; i++) {
            parks.add(new Landmark(
                    parksNames[i],
                    parksDescriptions[i],
                    parksAdresses[i],
                    parksHours[i],
                    addPrefix(parksPhones[i]),
                    parksMapUris[i],
                    parksImages[i]));
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

    private String addPrefix(String phone) {
        if (!phone.isEmpty()) {
            return "+40 " + phone;
        } else {
            return phone;
        }
    }
}
