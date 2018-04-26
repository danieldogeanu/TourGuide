package com.danieldogeanu.android.tourguide;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Class that initializes the Categories Activity for the app.
 */
public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Add Back (Up) Functionality
        Utils.activateBackBtn(CategoriesActivity.this, R.id.category_back_btn);

        // Initiate the ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.category_view_pager);
        CategoriesAdapter adapter = new CategoriesAdapter(getSupportFragmentManager(), CategoriesActivity.this);
        viewPager.setAdapter(adapter);

        // Set Tab Titles
        TabLayout tabLayout = (TabLayout) findViewById(R.id.category_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Select Tab from Intent
        TabLayout.Tab tab = tabLayout.getTabAt(getIntent().getIntExtra("selected_index", 0));
        if (tab != null) tab.select();
    }
}
