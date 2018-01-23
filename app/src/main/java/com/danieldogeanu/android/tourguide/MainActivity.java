package com.danieldogeanu.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFancyClickListener(R.id.museums_btn, MuseumsActivity.class);
        setFancyClickListener(R.id.parks_btn, ParksActivity.class);
        setFancyClickListener(R.id.palaces_btn, PalacesActivity.class);
        setFancyClickListener(R.id.monuments_btn, MonumentsActivity.class);
    }

    private void setFancyClickListener(int buttonId, final Class activity) {
        LinearLayout button = (LinearLayout) findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thisIntent = new Intent(MainActivity.this, activity);
                startActivity(thisIntent);
            }
        });
    }

}
