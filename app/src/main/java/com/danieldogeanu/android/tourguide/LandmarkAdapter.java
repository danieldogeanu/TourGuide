package com.danieldogeanu.android.tourguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * LandmarkAdapter Class is a custom ArrayAdapter for the Landmark objects.
 * It's used to build the list of items to display in the Category Screen.
 */
public class LandmarkAdapter extends ArrayAdapter<Landmark> {

    /**
     * LandmarkAdapter Constructor. Accepts 3 parameters.
     * @param context The Activity on which this adapter will be run.
     * @param landmarks The ArrayList with all the Landmarks.
     */
    public LandmarkAdapter(Activity context, ArrayList<Landmark> landmarks) {
        super(context, 0, landmarks);
    }

    /**
     * Overrides the getView() method to display a custom layout for each item in the ListView.
     * @param position The position in the ListView.
     * @param convertView The view for the Category Item in the ListView.
     * @param parent The ViewGroup parent view.
     * @return Returns the assembled Category Item to display in the ListView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Get or Inflate the Category Item View
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        // Get current Landmark object from the ArrayList
        Landmark currentLandmark = getItem(position);

        // Set the Category Item Title
        Utils.fillText(listItemView, R.id.cat_item_title, currentLandmark.getLandmarkName());

        // Set the Category Item Address
        Utils.fillText(listItemView, R.id.cat_item_address, currentLandmark.getAddress());

        // Set the Category Item Background Image
        Utils.fillImage(listItemView, R.id.cat_bg_image, currentLandmark.getImageId());

        // Return the Fully Assembled Category Item
        return listItemView;
    }
}
