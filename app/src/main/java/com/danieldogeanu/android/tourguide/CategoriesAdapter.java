package com.danieldogeanu.android.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * The CategoriesAdapter Class creates the Tabs for the ViewPager in the Categories Activity.
 */
public class CategoriesAdapter extends FragmentPagerAdapter {

    // Set the number of pages and initiate the Tab Titles Array
    private final int PAGE_COUNT = 4;
    private String[] tabTitles = new String[PAGE_COUNT];

    /**
     * The CategoriesAdapter Constructor. Takes 2 parameters.
     * @param fragmentManager The FragmentManager.
     * @param context The Context.
     */
    public CategoriesAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);

        // Set Tab Titles
        tabTitles[0] = context.getString(R.string.cat_museums);
        tabTitles[1] = context.getString(R.string.cat_parks);
        tabTitles[2] = context.getString(R.string.cat_palaces);
        tabTitles[3] = context.getString(R.string.cat_monuments);
    }

    /** @return Returns the number of pages. */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    /** @return Returns the Fragment associated with specific page. */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MuseumsFragment();
            case 1:
                return new ParksFragment();
            case 2:
                return new PalacesFragment();
            case 3:
                return new MonumentsFragment();
        }
        return null;
    }

    /** @return Returns the Tab Title at certain position. */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
