package com.danieldogeanu.android.tourguide;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
        Utils.activateBackBtn(DetailActivity.this, R.id.detail_back_btn);

        // Add Background on Scroll
        addActionBarBackground();

        // Get Landmark Object
        Landmark thisLandmark = (Landmark) getIntent().getSerializableExtra("serialize_data");

        // Set City Name
        Utils.fillText(DetailActivity.this, R.id.detail_city, getString(R.string.city_name));

        // Set Detail Title (Landmark Name)
        Utils.fillText(DetailActivity.this, R.id.detail_title, thisLandmark.getLandmarkName());

        // Set Header Image
        Utils.fillImage(DetailActivity.this, R.id.detail_header_image, thisLandmark.getImageId());

        // Set About Section Text
        Utils.fillText(DetailActivity.this, R.id.about_section_text, thisLandmark.getDescription());

        // Set Address
        if (!thisLandmark.getAddress().isEmpty()) {
            Utils.fillText(DetailActivity.this, R.id.details_address_content, thisLandmark.getAddress());
        } else {
            Utils.hideView(DetailActivity.this, R.id.details_address_container);
        }

        // Set Business Hours
        if (!thisLandmark.getHours().isEmpty()) {
            Utils.fillText(DetailActivity.this, R.id.details_hours_content, thisLandmark.getHours());
        } else {
            Utils.hideView(DetailActivity.this, R.id.details_hours_container);
        }

        // Set Phone Number & Attach Intent to Dial the Number
        if (!thisLandmark.getPhone().isEmpty()) {
            Utils.fillText(DetailActivity.this, R.id.details_phone_content, thisLandmark.getPhone());
            attachAction(R.id.detail_call_btn, CALL, thisLandmark.getPhone());
        } else {
            Utils.hideView(DetailActivity.this, R.id.details_phone_container);
            Utils.hideView(DetailActivity.this, R.id.detail_call_btn_frame);
        }

        // Attach Intent to Get Directions Button
        if (!thisLandmark.getMapUri().isEmpty()) {
            attachAction(R.id.detail_directions_btn, MAPS, thisLandmark.getMapUri());
        } else {
            Utils.hideView(DetailActivity.this, R.id.detail_directions_btn_frame);
        }

        // Hide Buttons Container if No Action for Both
        if (thisLandmark.getPhone().isEmpty() && thisLandmark.getMapUri().isEmpty()) {
            Utils.hideView(DetailActivity.this, R.id.detail_cta_container);
        }

    }

    /**
     * Method to attach a click listener for the specified button.
     * @param buttonId The ID of the button to attach the click listener.
     * @param action The selected action to be performed when the button is clicked.
     * @param data The data to be sent to the Intent that will be executed, for the action selected.
     */
    private void attachAction(int buttonId, final int action, final String data) {
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

    /**
     * Add Background to the Action Bar, on Scroll, so that the Back Button is visible.
     */
    private void addActionBarBackground() {
        final ScrollView detailScrollView = (ScrollView) findViewById(R.id.detail_scroll_view);
        final LinearLayout detailActionBar = (LinearLayout) findViewById(R.id.detail_action_bar);

        detailScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            boolean colorChanged = false;

            @Override
            public void onScrollChanged() {
                int scrollY = detailScrollView.getScrollY();
                if ((scrollY > 100) && (!colorChanged)) {
                    animateColorBackground(detailActionBar, android.R.color.transparent, R.color.Yellow);
                    colorChanged = true;
                } else if ((scrollY < 100) && (colorChanged)) {
                    animateColorBackground(detailActionBar, R.color.Yellow, android.R.color.transparent);
                    colorChanged = false;
                }
            }
        });
    }

    /**
     * Animate the Color Background of the selected view.
     * @param view The view to animate.
     * @param startColorId The start color Resource ID.
     * @param endColorId The end color Resource ID.
     */
    private void animateColorBackground(final View view, int startColorId, int endColorId) {
        int startColor = getResources().getColor(startColorId);
        int endColor = getResources().getColor(endColorId);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);

        colorAnimation.setDuration(300);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setBackgroundColor((int) valueAnimator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }

}
