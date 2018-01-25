package com.danieldogeanu.android.tourguide;

import android.app.Activity;
import android.graphics.ColorFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LandmarkAdapter extends ArrayAdapter<Landmark> {

    public static final String LOG_TAG = LandmarkAdapter.class.getSimpleName();

    private ListView mLandmarkListView;

    public LandmarkAdapter(Activity context, ArrayList<Landmark> landmarks, ListView view) {
        super(context, 0, landmarks);
        mLandmarkListView = view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_category_item, parent, false);
        }

        Landmark currentLandmark = getItem(position);

        TextView catItemTitle = (TextView) listItemView.findViewById(R.id.cat_item_title);
        catItemTitle.setText(currentLandmark.getLandmarkName());

        TextView catItemAddress = (TextView) listItemView.findViewById(R.id.cat_item_address);
        catItemAddress.setText(currentLandmark.getAddress());

        ImageView catBgImage = (ImageView) listItemView.findViewById(R.id.cat_bg_image);
        int color = ContextCompat.getColor(getContext(), currentLandmark.getColorId());
        catBgImage.setImageResource(currentLandmark.getImageId());
        catBgImage.setColorFilter(color);

        return listItemView;
    }
}
