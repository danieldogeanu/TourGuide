package com.danieldogeanu.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Class that initializes the Main Activity for the app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach Activities for Each Button
        openActivity(R.id.museums_btn, MuseumsActivity.class);
        openActivity(R.id.parks_btn, ParksActivity.class);
        openActivity(R.id.palaces_btn, PalacesActivity.class);
        openActivity(R.id.monuments_btn, MonumentsActivity.class);
    }

    /**
     * Method to attach the onClickListener to the buttons in the Main Activity screen and launch the selected Activity Intent.
     * @param buttonId The ID of the button that the click listener is going to be attached.
     * @param activity The activity to launch when the button is clicked.
     */
    private void openActivity(int buttonId, final Class activity) {
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
