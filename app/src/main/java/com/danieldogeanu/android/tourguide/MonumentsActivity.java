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
        String[] monumentsMapUris = {
                "https://www.google.com/maps/dir//The+Arch+Of+Triumph,+Pia%C8%9Ba+Arcul+de+Triumf,+Bucure%C8%99ti/@44.4671777,26.078116,15z/data=!4m16!1m6!3m5!1s0x0:0x57ae7c6e837bc61b!2sThe+Arch+Of+Triumph!8m2!3d44.4671777!4d26.078116!4m8!1m0!1m5!1m1!1s0x40b202172654ca11:0x57ae7c6e837bc61b!2m2!1d26.078116!2d44.4671777!3e3",
                "https://www.google.com/maps/dir//Rebirth+Memorial,+Pia%C8%9Ba+Revolu%C8%9Biei,+Bucure%C8%99ti+030167/@44.4388942,26.097453,15z/data=!4m16!1m6!3m5!1s0x0:0xd2ec9ba0c7456177!2sRebirth+Memorial!8m2!3d44.4388942!4d26.097453!4m8!1m0!1m5!1m1!1s0x40b1ff45dcb30619:0xd2ec9ba0c7456177!2m2!1d26.097453!2d44.4388942!3e3",
                "https://www.google.com/maps/dir//Equestrian+Statue+of+Carol+I,+Bucure%C8%99ti+030167/@44.4396476,26.0970161,15z/data=!4m16!1m6!3m5!1s0x0:0x1ff3885d06d59e62!2sEquestrian+Statue+of+Carol+I!8m2!3d44.4396476!4d26.0970161!4m8!1m0!1m5!1m1!1s0x40b1ff45931aafa5:0x1ff3885d06d59e62!2m2!1d26.0970161!2d44.4396476!3e3",
                "https://www.google.com/maps/dir//Kilometre+Zero,+Bulevardul+Ion+C.+Br%C4%83tianu,+Bucure%C8%99ti+030167/@44.4327025,26.1040494,15z/data=!4m17!1m7!3m6!1s0x0:0x4ad53314f3dcaebf!2sKilometre+Zero,+Bulevardul+Ion+C.+Br%C4%83tianu,+Bucure%C8%99ti+030167!3b1!8m2!3d44.4327025!4d26.1040494!4m8!1m0!1m5!1m1!1s0x40b1ff3edff6735f:0x4ad53314f3dcaebf!2m2!1d26.1040494!2d44.4327025!3e3",
                "https://www.google.com/maps/dir//Statue+of+George+Enescu,+Bulevardul+Mihail+Kog%C4%83lniceanu,+Bucure%C8%99ti/@44.4347229,26.0793548,15z/data=!4m16!1m6!3m5!1s0x0:0x93c7f0ac3cfa3bd7!2sStatue+of+George+Enescu!8m2!3d44.4347229!4d26.0793548!4m8!1m0!1m5!1m1!1s0x40b1ff6756b5300d:0x93c7f0ac3cfa3bd7!2m2!1d26.0793548!2d44.4347229!3e3",
                "https://www.google.com/maps/dir//Nation's+Heroes+Memorial,+Strada+General+Candiano+Popescu+105,+Bucure%C8%99ti/@44.41127,26.0968789,15z/data=!4m16!1m6!3m5!1s0x0:0x3e7635bc09da2!2sNation's+Heroes+Memorial!8m2!3d44.41127!4d26.0968789!4m8!1m0!1m5!1m1!1s0x40b1ff07f2ee63ad:0x3e7635bc09da2!2m2!1d26.0968789!2d44.41127!3e3",
        };
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
                    monumentsMapUris[i],
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
