package com.danieldogeanu.android.tourguide;

import java.io.Serializable;

public class Landmark implements Serializable {

    private String mLandmark;
    private String mDescription;
    private String mAddress;
    private String mHours;
    private String mPhone;
    private int mImageResourceId;

    public Landmark(String landmark, String description, String address, String hours, String phone, int image) {
        mLandmark = landmark;
        mDescription = description;
        mAddress = address;
        mHours = hours;
        mPhone = phone;
        mImageResourceId = image;
    }

    public String getLandmarkName() {
        return mLandmark;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getHours() {
        return mHours;
    }

    public String getPhone() {
        return mPhone;
    }

    public int getImageId() {
        return mImageResourceId;
    }

    @Override
    public String toString() {
        return "Landmark { " +
                "mLandmark='" + mLandmark + "', " +
                "mDescription='" + mDescription + "', " +
                "mAddress='" + mAddress + "', " +
                "mHours='" + mHours + "', " +
                "mPhone='" + mPhone + "', " +
                "mImageResourceId='" + mImageResourceId + "' }";
    }
}
