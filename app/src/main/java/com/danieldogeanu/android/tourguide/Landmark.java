package com.danieldogeanu.android.tourguide;

public class Landmark {

    private String mLandmark;
    private String mAddress;
    private int mImageResourceId;
    private int mColorResourceId;

    public Landmark(String landmark, String address, int image, int color) {
        mLandmark = landmark;
        mAddress = address;
        mImageResourceId = image;
        mColorResourceId = color;
    }

    public String getLandmark() {
        return mLandmark;
    }

    public String getAddress() {
        return mAddress;
    }

    public int getImageId() {
        return mImageResourceId;
    }

    public int getColorId() {
        return mColorResourceId;
    }

    @Override
    public String toString() {
        return "Landmark { " +
                "mLandmark='" + mLandmark + "', " +
                "mAddress='" + mAddress + "', " +
                "mImageResourceId='" + mImageResourceId + "', " +
                "mColorResoirceId='" + mColorResourceId + "' }";
    }
}
