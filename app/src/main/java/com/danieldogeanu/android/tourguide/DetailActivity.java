package com.danieldogeanu.android.tourguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Class that initializes the Detail Activity screen.
 */
public class DetailActivity extends AppCompatActivity {

    // Constants to Differentiate Actions
    private static final int CALL = 243247;
    private static final int MAPS = 892343;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Add Back (Up) Functionality
        ImageButton backButton = (ImageButton) findViewById(R.id.detail_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get Landmark Object
        Landmark thisLandmark = (Landmark) getIntent().getSerializableExtra("serialize_data");

        // Set City Name
        fillText(R.id.detail_city, getString(R.string.city_name));

        // Set Detail Title (Landmark Name)
        fillText(R.id.detail_title, thisLandmark.getLandmarkName());

        // Set Header Image
        fillImage(R.id.detail_header_image, thisLandmark.getImageId());

        // Set About Section Text
        fillText(R.id.about_section_text, thisLandmark.getDescription());

        // Set Address
        if (!thisLandmark.getAddress().isEmpty()) {
            fillText(R.id.details_address_content, thisLandmark.getAddress());
        } else {
            hideView(R.id.details_address_container);
        }

        // Set Business Hours
        if (!thisLandmark.getHours().isEmpty()) {
            fillText(R.id.details_hours_content, thisLandmark.getHours());
        } else {
            hideView(R.id.details_hours_container);
        }

        // Set Phone Number & Attach Intent to Dial the Number
        if (!thisLandmark.getPhone().isEmpty()) {
            fillText(R.id.details_phone_content, thisLandmark.getPhone());
            attachClickListener(R.id.detail_call_btn, CALL, thisLandmark.getPhone());
        } else {
            hideView(R.id.details_phone_container);
            hideView(R.id.detail_call_btn_frame);
        }

        // Attach Intent to Get Directions Button
        if (!thisLandmark.getMapUri().isEmpty()) {
            attachClickListener(R.id.detail_directions_btn, MAPS, thisLandmark.getMapUri());
        } else {
            hideView(R.id.detail_directions_btn_frame);
        }

        // Hide Buttons Container if No Action for Both
        if (thisLandmark.getPhone().isEmpty() && thisLandmark.getMapUri().isEmpty()) {
            hideView(R.id.detail_cta_container);
        }

    }

    /**
     * Method to add text to specified TextView.
     * @param id The ID of the TextView.
     * @param text The text to set to the TextView.
     */
    private void fillText(int id, String text) {
        TextView thisTextView = (TextView) findViewById(id);
        thisTextView.setText(text);
    }

    /**
     * Method to add image to the Header.
     * @param id The ID of ImageView to set the Image.
     * @param image The ID of the Image Resource to set.
     */
    private void fillImage(int id, int image) {
        ImageView thisImageView = (ImageView) findViewById(id);
        thisImageView.setImageResource(image);
    }

    /**
     * Method to hide the specified view from the layout.
     * This is useful when certain fields from the Landmark object are empty.
     * @param id The ID of the View to be hidden.
     */
    private void hideView(int id) {
        View thisView = (View) findViewById(id);
        thisView.setVisibility(View.GONE);
    }

    /**
     * Method to attach a click listener for the specified button.
     * @param buttonId The ID of the button to attach the click listener.
     * @param action The selected action to be performed when the button is clicked.
     * @param data The data to be sent to the Intent that will be executed, for the action selected.
     */
    private void attachClickListener(int buttonId, final int action, final String data) {
        Button button = (Button) findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (action) {
                    case MAPS:
                        launchMaps(data);
                        break;
                    case CALL:
                        launchCall(data);
                        break;
                }
            }
        });
    }

    /**
     * This method launches a Google Maps Intent to get directions to the specified Landmark address.
     * @param uri The URI for which to launch the Intent.
     */
    private void launchMaps(String uri) {
        Uri gmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    /**
     * This method launches Phone Dialer app to call the specified Landmark Contact Phone.
     * @param phone The Phone Number to dial.
     */
    private void launchCall(String phone) {
        Uri phoneNumber = Uri.parse("tel:" + phone);
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(phoneNumber);
        if ((dialIntent.resolveActivity(getPackageManager()) != null) &&
                        (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)) {
            startActivity(dialIntent);
        }
    }

}
