package com.danieldogeanu.android.tourguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
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

        // Set Address
        fillText(R.id.details_address_content, thisLandmark.getAddress());
    }

    private void fillText(int id, String text) {
        TextView thisTextView = (TextView) findViewById(id);
        thisTextView.setText(text);
    }

    private void fillImage(int id, int image) {
        ImageView thisImageView = (ImageView) findViewById(id);
        thisImageView.setImageResource(image);
    }

}
