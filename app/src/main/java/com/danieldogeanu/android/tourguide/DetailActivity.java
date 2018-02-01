package com.danieldogeanu.android.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

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

        // Set Detail Title
        fillText(R.id.detail_title, thisLandmark.getLandmarkName());

        // Set Header Image
        fillImage(R.id.detail_header_image, thisLandmark.getImageId());

        // Set About Section Text
        fillText(R.id.about_section_text, thisLandmark.getDescription());

        // Set Address
        if (!thisLandmark.getAddress().isEmpty()) {
            fillText(R.id.details_address_content, thisLandmark.getAddress());

            // Attach Intent to Get Directions Button
            String mapUri = "https://www.google.com/maps/dir//Palace+of+Parliament,+Strada+Izvor+2-4,+Bucure%C8%99ti/@44.4275035,26.0873506,15z/data=!4m16!1m6!3m5!1s0x0:0x2b1089f802abaddc!2sPalace+of+Parliament!8m2!3d44.4275035!4d26.0873506!4m8!1m0!1m5!1m1!1s0x40b1ff427bee28c1:0x2b1089f802abaddc!2m2!1d26.0873506!2d44.4275035!3e3";
            attachClickListener(R.id.detail_directions_btn, MAPS, mapUri);
        } else {
            hideView(R.id.details_address_container);
        }

        // Set Hours
        if (!thisLandmark.getHours().isEmpty()) {
            fillText(R.id.details_hours_content, thisLandmark.getHours());
        } else {
            hideView(R.id.details_hours_container);
        }

        // Set Phone
        if (!thisLandmark.getPhone().isEmpty()) {
            fillText(R.id.details_phone_content, thisLandmark.getPhone());
        } else {
            hideView(R.id.details_phone_container);
            hideView(R.id.detail_call_btn_frame);
        }

    }

    private void fillText(int id, String text) {
        TextView thisTextView = (TextView) findViewById(id);
        thisTextView.setText(text);
    }

    private void fillImage(int id, int image) {
        ImageView thisImageView = (ImageView) findViewById(id);
        thisImageView.setImageResource(image);
    }

    private void hideView(int id) {
        View thisView = (View) findViewById(id);
        thisView.setVisibility(View.GONE);
    }

    private void attachClickListener(int buttonId, final int action, final String data) {
        Button button = (Button) findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action == MAPS) {
                    launchMaps(data);
                }
            }
        });
    }

    private void launchMaps(String uri) {
        Uri gmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

}
