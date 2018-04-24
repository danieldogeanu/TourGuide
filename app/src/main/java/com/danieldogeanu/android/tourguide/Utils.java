package com.danieldogeanu.android.tourguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Utils {

    /**
     * Method to attach the onClickListener to buttons and launch the selected Activity Intent.
     * @param currentActivity The Activity from which this method is called.
     * @param buttonId The ID of the button that the click listener is going to be attached.
     * @param activityToOpen The activity to launch when the button is clicked.
     */
    public static void openActivity(final Activity currentActivity, int buttonId, final Class activityToOpen) {
        LinearLayout button = (LinearLayout) currentActivity.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thisIntent = new Intent(currentActivity, activityToOpen);
                currentActivity.startActivity(thisIntent);
            }
        });
    }

    /**
     * Method to add text to specified TextView.
     * @param activity The Activity from which this method is called.
     * @param id The ID of the TextView.
     * @param text The text to set to the TextView.
     */
    public static void fillText(Activity activity, int id, String text) {
        TextView thisTextView = (TextView) activity.findViewById(id);
        thisTextView.setText(text);
    }

    /**
     * Method to add text to specified TextView.
     * @param view The View from which this method is called.
     * @param id The ID of the TextView.
     * @param text The text to set to the TextView.
     */
    public static void fillText(View view, int id, String text) {
        TextView thisTextView = (TextView) view.findViewById(id);
        thisTextView.setText(text);
    }

    /**
     * Method to add image to specified ImageView.
     * @param activity The Activity from which this method is called.
     * @param id The ID of ImageView to set the Image.
     * @param image The ID of the Image Resource to set.
     */
    public static void fillImage(Activity activity, int id, int image) {
        ImageView thisImageView = (ImageView) activity.findViewById(id);
        thisImageView.setImageResource(image);
    }

    /**
     * Method to add image to specified ImageView.
     * @param view The View from which this method is called.
     * @param id The ID of ImageView to set the Image.
     * @param image The ID of the Image Resource to set.
     */
    public static void fillImage(View view, int id, int image) {
        ImageView thisImageView = (ImageView) view.findViewById(id);
        thisImageView.setImageResource(image);
    }

    /**
     * Method to hide the specified view from the layout.
     * This is useful when certain fields from the Landmark object are empty.
     * @param activity The Activity from which this method is called.
     * @param id The ID of the View to be hidden.
     */
    public static void hideView(Activity activity, int id) {
        View thisView = (View) activity.findViewById(id);
        thisView.setVisibility(View.GONE);
    }

}
