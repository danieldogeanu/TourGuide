package com.danieldogeanu.android.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

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
}
