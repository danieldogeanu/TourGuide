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
        Utils.openActivity(MainActivity.this, R.id.museums_btn, CategoriesActivity.class);
        Utils.openActivity(MainActivity.this, R.id.parks_btn, CategoriesActivity.class);
        Utils.openActivity(MainActivity.this, R.id.palaces_btn, CategoriesActivity.class);
        Utils.openActivity(MainActivity.this, R.id.monuments_btn, CategoriesActivity.class);

        // TODO: Find a way to select the TAB associated with the button that user clicks.
    }



}
