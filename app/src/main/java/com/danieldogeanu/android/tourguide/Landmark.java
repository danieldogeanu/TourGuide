package com.danieldogeanu.android.tourguide;

import java.io.Serializable;

/**
 * Landmark Class creates the objects to populate both ListView Layouts and Detail Layouts with necessary information.
 */
public class Landmark implements Serializable {

    private String mLandmark;
    private String mDescription;
    private String mAddress;
    private String mHours;
    private String mPhone;
    private String mMapUri;
    private int mImageResourceId;

    /**
     * Landmark Object Constructor. Takes 7 fields; all required.
     * @param landmark Landmark Name
     * @param description Landmark Short Description
     * @param address Landmark Address
     * @param hours Landmark Business Hours
     * @param phone Landmark Contact Phone
     * @param mapUri Landmark Map URI
     * @param image Landmark Drawable Resource ID
     */
    public Landmark(String landmark, String description, String address, String hours, String phone, String mapUri, int image) {
        mLandmark = landmark;
        mDescription = description;
        mAddress = address;
        mHours = hours;
        mPhone = phone;
        mMapUri = mapUri;
        mImageResourceId = image;
    }

    /** @return Returns the Landmark Name. */
    public String getLandmarkName() {
        return mLandmark;
    }

    /** @return Returns the Landmark Address. */
    public String getAddress() {
        return mAddress;
    }

    /** @return Returns the Landmark Short Description. */
    public String getDescription() {
        return mDescription;
    }

    /** @return Returns the Business Hours for the Landmark. */
    public String getHours() {
        return mHours;
    }

    /** @return Returns the Contact Phone Number for the Landmark. */
    public String getPhone() {
        return mPhone;
    }

    /** @return Returns the Map URI for the Landmark. */
    public String getMapUri() {
        return mMapUri;
    }

    /** @return Returns the Drawable Image Resource ID for the Landmark. */
    public int getImageId() {
        return mImageResourceId;
    }

    /**
     * Overrides the toString() method for debug purposes.
     * @return Returns a concatenated string with all the fields contents.
     */
    @Override
    public String toString() {
        return "Landmark { " +
                "mLandmark='" + mLandmark + "', " +
                "mDescription='" + mDescription + "', " +
                "mAddress='" + mAddress + "', " +
                "mHours='" + mHours + "', " +
                "mPhone='" + mPhone + "', " +
                "mMapUri='" + mMapUri + "', " +
                "mImageResourceId='" + mImageResourceId + "' }";
    }
}
