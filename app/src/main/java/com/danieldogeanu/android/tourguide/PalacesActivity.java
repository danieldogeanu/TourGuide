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

/**
 * Class that initializes the Palaces Category screen.
 */
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

        // Get String Array Resources from the Strings File
        String[] palacesNames = getResources().getStringArray(R.array.palaces_names);
        String[] palacesAdresses = getResources().getStringArray(R.array.palaces_addresses);
        String[] palacesDescriptions = getResources().getStringArray(R.array.palaces_descriptions);
        String[] palacesHours = getResources().getStringArray(R.array.palaces_hours);
        String[] palacesPhones = getResources().getStringArray(R.array.palaces_phones);

        // Add Google Maps URIs
        String[] palacesMapUris = {
                "https://www.google.com/maps/dir//Palace+of+Parliament,+Strada+Izvor+2-4,+Bucure%C8%99ti/@44.4275035,26.0873506,15z/data=!4m16!1m6!3m5!1s0x0:0x2b1089f802abaddc!2sPalace+of+Parliament!8m2!3d44.4275035!4d26.0873506!4m8!1m0!1m5!1m1!1s0x40b1ff427bee28c1:0x2b1089f802abaddc!2m2!1d26.0873506!2d44.4275035!3e3",
                "https://www.google.com/maps/dir//Cotroceni+National+Museum,+Bulevardul+Geniului+1,+Bucure%C8%99ti/@44.4352431,26.0633577,15z/data=!4m16!1m6!3m5!1s0x0:0xeca7ba35fbc72d4d!2sCotroceni+National+Museum!8m2!3d44.4352431!4d26.0633577!4m8!1m0!1m5!1m1!1s0x40b201dbbe0b8639:0xeca7ba35fbc72d4d!2m2!1d26.0633577!2d44.4352431!3e3",
                "https://www.google.com/maps/dir//Palatul+Kretzulescu,+Strada+%C8%98tirbei+Vod%C4%83,+Bucure%C8%99ti/@44.4388497,26.0882959,16z/data=!4m16!1m6!3m5!1s0x0:0xf1103bf41f5a939a!2sPalatul+Kretzulescu!8m2!3d44.4395493!4d26.0886683!4m8!1m0!1m5!1m1!1s0x40b1ff5b1a42ff77:0xf1103bf41f5a939a!2m2!1d26.0886678!2d44.4395493!3e3",
                "https://www.google.com/maps/dir//Mogo%C5%9Foaia+Palace,+Strada+Valea+Parcului+1,+Mogo%C8%99oaia/@44.5276502,25.9926423,15z/data=!4m16!1m6!3m5!1s0x0:0x828d1b6f02c12e92!2sMogo%C5%9Foaia+Palace!8m2!3d44.5276502!4d25.9926423!4m8!1m0!1m5!1m1!1s0x40b204efa0ac3e7d:0x828d1b6f02c12e92!2m2!1d25.9926423!2d44.5276502!3e3",
                "https://www.google.com/maps/dir//Cantacuzino+Palace,+Calea+Victoriei+141,+Bucure%C8%99ti+010071/@44.448696,26.0883367,15z/data=!4m17!1m7!3m6!1s0x0:0x3e94de9f067b7471!2sCantacuzino+Palace,+Calea+Victoriei+141,+Bucure%C8%99ti+010071!3b1!8m2!3d44.448696!4d26.0883367!4m8!1m0!1m5!1m1!1s0x40b1ff55ca55e137:0x3e94de9f067b7471!2m2!1d26.0883367!2d44.448696!3e3",
        };

        // Add Images Resource IDs
        int[] palacesImages = {
                R.drawable.palace_of_parliament,
                R.drawable.cotroceni_palace,
                R.drawable.cretulescu_palace,
                R.drawable.mogosoaia_palace,
                R.drawable.cantacuzino_palace
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> palaces = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < palacesNames.length; i++) {
            palaces.add(new Landmark(
                    palacesNames[i],
                    palacesDescriptions[i],
                    palacesAdresses[i],
                    palacesHours[i],
                    addPrefix(palacesPhones[i]),
                    palacesMapUris[i],
                    palacesImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(PalacesActivity.this, palaces, listView);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark Object at Current Position
                Landmark palace = palaces.get(position);

                // Start Intent and Send Landmark Object to DetailActivity
                Intent detailActivity = new Intent(PalacesActivity.this, DetailActivity.class);
                detailActivity.putExtra("serialize_data", palace);
                startActivity(detailActivity);

            }
        });
    }

    /**
     * Add Prefix for Phone Numbers or return empty String for later use in the DetailActivity.
     * This is required in order to figure out if the related views are shown or hidden from the layout.
     * @param phone The Phone Number as it comes from the Strings File.
     * @return Returns the Phone Number with country prefix, or an Empty String.
     */
    private String addPrefix(String phone) {
        if (!phone.isEmpty()) {
            return "+40 " + phone;
        } else {
            return phone;
        }
    }
}
