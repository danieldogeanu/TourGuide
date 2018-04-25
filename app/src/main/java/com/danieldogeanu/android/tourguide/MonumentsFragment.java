package com.danieldogeanu.android.tourguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * MonumentsFragment is the Class that displays the Monuments Tab in the Categories Activity.
 */
public class MonumentsFragment extends Fragment {

    public MonumentsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get String Array Resources from the Strings File
        String[] monumentsNames = getResources().getStringArray(R.array.monuments_names);
        String[] monumentsAddresses = getResources().getStringArray(R.array.monuments_addresses);
        String[] monumentsDescriptions = getResources().getStringArray(R.array.monuments_descriptions);
        String[] monumentsHours = getResources().getStringArray(R.array.monuments_hours);
        String[] monumentsPhones = getResources().getStringArray(R.array.monuments_phones);

        // Add Google Maps URIs
        String[] monumentsMapUris = {
                "https://www.google.com/maps/dir//The+Arch+Of+Triumph,+Pia%C8%9Ba+Arcul+de+Triumf,+Bucure%C8%99ti/@44.4671777,26.078116,15z/data=!4m16!1m6!3m5!1s0x0:0x57ae7c6e837bc61b!2sThe+Arch+Of+Triumph!8m2!3d44.4671777!4d26.078116!4m8!1m0!1m5!1m1!1s0x40b202172654ca11:0x57ae7c6e837bc61b!2m2!1d26.078116!2d44.4671777!3e3",
                "https://www.google.com/maps/dir//Rebirth+Memorial,+Pia%C8%9Ba+Revolu%C8%9Biei,+Bucure%C8%99ti+030167/@44.4388942,26.097453,15z/data=!4m16!1m6!3m5!1s0x0:0xd2ec9ba0c7456177!2sRebirth+Memorial!8m2!3d44.4388942!4d26.097453!4m8!1m0!1m5!1m1!1s0x40b1ff45dcb30619:0xd2ec9ba0c7456177!2m2!1d26.097453!2d44.4388942!3e3",
                "https://www.google.com/maps/dir//Equestrian+Statue+of+Carol+I,+Bucure%C8%99ti+030167/@44.4396476,26.0970161,15z/data=!4m16!1m6!3m5!1s0x0:0x1ff3885d06d59e62!2sEquestrian+Statue+of+Carol+I!8m2!3d44.4396476!4d26.0970161!4m8!1m0!1m5!1m1!1s0x40b1ff45931aafa5:0x1ff3885d06d59e62!2m2!1d26.0970161!2d44.4396476!3e3",
                "https://www.google.com/maps/dir//Kilometre+Zero,+Bulevardul+Ion+C.+Br%C4%83tianu,+Bucure%C8%99ti+030167/@44.4327025,26.1040494,15z/data=!4m17!1m7!3m6!1s0x0:0x4ad53314f3dcaebf!2sKilometre+Zero,+Bulevardul+Ion+C.+Br%C4%83tianu,+Bucure%C8%99ti+030167!3b1!8m2!3d44.4327025!4d26.1040494!4m8!1m0!1m5!1m1!1s0x40b1ff3edff6735f:0x4ad53314f3dcaebf!2m2!1d26.1040494!2d44.4327025!3e3",
                "https://www.google.com/maps/dir//Statue+of+George+Enescu,+Bulevardul+Mihail+Kog%C4%83lniceanu,+Bucure%C8%99ti/@44.4347229,26.0793548,15z/data=!4m16!1m6!3m5!1s0x0:0x93c7f0ac3cfa3bd7!2sStatue+of+George+Enescu!8m2!3d44.4347229!4d26.0793548!4m8!1m0!1m5!1m1!1s0x40b1ff6756b5300d:0x93c7f0ac3cfa3bd7!2m2!1d26.0793548!2d44.4347229!3e3",
                "https://www.google.com/maps/dir//Nation's+Heroes+Memorial,+Strada+General+Candiano+Popescu+105,+Bucure%C8%99ti/@44.41127,26.0968789,15z/data=!4m16!1m6!3m5!1s0x0:0x3e7635bc09da2!2sNation's+Heroes+Memorial!8m2!3d44.41127!4d26.0968789!4m8!1m0!1m5!1m1!1s0x40b1ff07f2ee63ad:0x3e7635bc09da2!2m2!1d26.0968789!2d44.41127!3e3",
        };

        // Add Images Resource IDs
        int[] monumentsImages = {
                R.drawable.arcul_de_triumf,
                R.drawable.memorial_of_rebirth,
                R.drawable.carol_i_statue,
                R.drawable.kilometre_zero,
                R.drawable.george_enescu_statue,
                R.drawable.nations_heroes_memorial
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> monuments = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < monumentsNames.length; i++) {
            monuments.add(new Landmark(
                    monumentsNames[i],
                    monumentsDescriptions[i],
                    monumentsAddresses[i],
                    monumentsHours[i],
                    Utils.addPrefix(monumentsPhones[i]),
                    monumentsMapUris[i],
                    monumentsImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) rootView.findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), monuments);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark Object at Current Position
                Landmark monument = monuments.get(position);

                // Start Intent and Send Landmark Object to DetailActivity
                Utils.openDetailActivity(getContext(), monument);

            }
        });

        return rootView;
    }
}
