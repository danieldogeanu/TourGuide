package com.danieldogeanu.android.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Class that initializes the Main Activity for the app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach Activities for Each Button
        Utils.openCategoriesActivity(MainActivity.this, R.id.museums_btn, 0);
        Utils.openCategoriesActivity(MainActivity.this, R.id.parks_btn, 1);
        Utils.openCategoriesActivity(MainActivity.this, R.id.palaces_btn, 2);
        Utils.openCategoriesActivity(MainActivity.this, R.id.monuments_btn, 3);
    }

}
