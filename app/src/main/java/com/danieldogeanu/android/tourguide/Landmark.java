package com.danieldogeanu.android.tourguide;

public class Landmark {

    private String mLandmark;
    private String mAddress;
    private int mImageResourceId;

    public Landmark(String landmark, String address, int image) {
        mLandmark = landmark;
        mAddress = address;
        mImageResourceId = image;
    }

    public String getLandmarkName() {
        return mLandmark;
    }

    public String getAddress() {
        return mAddress;
    }

    public int getImageId() {
        return mImageResourceId;
    }

    @Override
    public String toString() {
        return "Landmark { " +
                "mLandmark='" + mLandmark + "', " +
                "mAddress='" + mAddress + "', " +
                "mImageResourceId='" + mImageResourceId + "' }";
    }
}
