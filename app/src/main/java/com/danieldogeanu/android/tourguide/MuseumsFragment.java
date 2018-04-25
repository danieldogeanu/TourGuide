package com.danieldogeanu.android.tourguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Class that initializes the Museums Category screen.
 */
public class MuseumsFragment extends Fragment {

    public MuseumsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get String Array Resources from the Strings File
        String[] museumsNames = getResources().getStringArray(R.array.museums_names);
        String[] museumsAddresses = getResources().getStringArray(R.array.museums_addresses);
        String[] museumsDescriptions = getResources().getStringArray(R.array.museums_descriptions);
        String[] museumsHours = getResources().getStringArray(R.array.museums_hours);
        String[] museumsPhones = getResources().getStringArray(R.array.museums_phones);

        // Add Google Maps URIs
        String[] museumsMapUris = {
                "https://www.google.com/maps/dir//The+National+Museum+of+Art+of+Romania,+Calea+Victoriei+49-53,+Bucure%C8%99ti+010063/@44.4393668,26.095874,15z/data=!4m16!1m6!3m5!1s0x0:0xdea7c86f153f2330!2sThe+National+Museum+of+Art+of+Romania!8m2!3d44.4393668!4d26.095874!4m8!1m0!1m5!1m1!1s0x40b1ff45a2c1486f:0xdea7c86f153f2330!2m2!1d26.095874!2d44.4393668!3e3",
                "https://www.google.com/maps/dir//Muzeul+Satului+Dimitrie+Gusti,+%C8%98oseaua+Pavel+Dimitrievici+Kiseleff,+sector+1+28-30,+Bucure%C8%99ti+011347/@44.4723586,26.0765852,15z/data=!4m16!1m6!3m5!1s0x0:0x82df7091a7a741a3!2sMuzeul+Satului+Dimitrie+Gusti!8m2!3d44.4723586!4d26.0765852!4m8!1m0!1m5!1m1!1s0x40b202037c613773:0x82df7091a7a741a3!2m2!1d26.0765852!2d44.4723586!3e3",
                "https://www.google.com/maps/dir//Antipa+Museum,+%C8%98oseaua+Pavel+Dimitrievici+Kiseleff+1,+Bucure%C8%99ti+011341/@44.4531131,26.0846382,15z/data=!4m16!1m6!3m5!1s0x0:0x120b912fe44ec81d!2sAntipa+Museum!8m2!3d44.4531131!4d26.0846382!4m8!1m0!1m5!1m1!1s0x40b201ff7eb6eee5:0x120b912fe44ec81d!2m2!1d26.0846382!2d44.4531131!3e3",
                "https://www.google.com/maps/dir//Museum+of+the+Romanian+Peasant,+%C8%98oseaua+Pavel+Dimitrievici+Kiseleff+3,+Bucure%C8%99ti+011341/@44.4544198,26.0836002,15z/data=!4m16!1m6!3m5!1s0x0:0xddcebd3aef3114e3!2sMuseum+of+the+Romanian+Peasant!8m2!3d44.4544198!4d26.0836002!4m8!1m0!1m5!1m1!1s0x40b20201893a4cd1:0xddcebd3aef3114e3!2m2!1d26.0836002!2d44.4544198!3e3",
                "https://www.google.com/maps/dir//National+Museum+of+Romanian+History,+Calea+Victoriei+12,+Bucure%C8%99ti+030026/@44.431447,26.0974538,15z/data=!4m16!1m6!3m5!1s0x0:0x50934f855fca87e9!2sNational+Museum+of+Romanian+History!8m2!3d44.431447!4d26.0974538!4m8!1m0!1m5!1m1!1s0x40b1ff40166ffb91:0x50934f855fca87e9!2m2!1d26.0974538!2d44.431447!3e3",
                "https://www.google.com/maps/dir//Curtea+Veche,+Strada+Francez%C4%83+25,+Bucure%C8%99ti+030167/@44.4301472,26.1009639,15z/data=!4m16!1m6!3m5!1s0x0:0xd53f908e1cf2db66!2sCurtea+Veche!8m2!3d44.4301472!4d26.1009639!4m8!1m0!1m5!1m1!1s0x40b1ff3fd305aeed:0xd53f908e1cf2db66!2m2!1d26.1009639!2d44.4301472!3e3",
                "https://www.google.com/maps/dir//National+Museum+of+Contemporary+Art,+Bucure%C8%99ti+030167/@44.4281238,26.0865004,15z/data=!4m16!1m6!3m5!1s0x0:0xb4b9a8d0644c2ca!2sNational+Museum+of+Contemporary+Art!8m2!3d44.4281238!4d26.0865004!4m8!1m0!1m5!1m1!1s0x40b1ff6e98722e47:0xb4b9a8d0644c2ca!2m2!1d26.0865004!2d44.4281238!3e3",
                "https://www.google.com/maps/dir//Templul+Unirea+Sf%C3%A2nt%C4%83,+Intrarea+M%C4%83mulari+3,+Bucure%C8%99ti+030167/@44.4283435,26.1080229,15z/data=!4m16!1m6!3m5!1s0x0:0x95a373390ee74fe!2sTemplul+Unirea+Sf%C3%A2nt%C4%83!8m2!3d44.4283435!4d26.1080229!4m8!1m0!1m5!1m1!1s0x40b1ff3d795bfb25:0x95a373390ee74fe!2m2!1d26.1080229!2d44.4283435!3e3",
                "https://www.google.com/maps/dir//National+Military+Museum,+Strada+Mircea+Vulc%C4%83nescu+125-127,+Bucure%C8%99ti+010819/@44.4407516,26.076598,15z/data=!4m16!1m6!3m5!1s0x0:0x7a32aefdf0d8b116!2sNational+Military+Museum!8m2!3d44.4407516!4d26.076598!4m8!1m0!1m5!1m1!1s0x40b201e2102a641b:0x7a32aefdf0d8b116!2m2!1d26.076598!2d44.4407516!3e3",
                "https://www.google.com/maps/dir//Muzeu+CFR,+Strada+G%C4%83rii+de+Nord,+Bucure%C8%99ti/@44.447968,26.0731395,15z/data=!4m16!1m6!3m5!1s0x0:0x376e0682d84587c1!2sMuzeu+CFR!8m2!3d44.447968!4d26.0731395!4m8!1m0!1m5!1m1!1s0x40b201fb1a0e7f69:0x376e0682d84587c1!2m2!1d26.0731395!2d44.447968!3e3"
        };

        // Add Images Resource IDs
        int[] museumsImages = {
                R.drawable.national_art_museum_of_romania,
                R.drawable.dimitrie_gusti_national_village_museum,
                R.drawable.grigore_antipa_national_museum_of_natural_history,
                R.drawable.romanian_peasant_museum,
                R.drawable.national_museum_of_romanian_history,
                R.drawable.curtea_veche,
                R.drawable.national_museum_of_contemporary_art,
                R.drawable.jewish_museum,
                R.drawable.national_military_museum,
                R.drawable.museum_of_the_romanian_railways
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> museums = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < museumsNames.length; i++) {
            museums.add(new Landmark(
                    museumsNames[i],
                    museumsDescriptions[i],
                    museumsAddresses[i],
                    museumsHours[i],
                    Utils.addPrefix(museumsPhones[i]),
                    museumsMapUris[i],
                    museumsImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) rootView.findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), museums, listView);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark Object at Current Position
                Landmark museum = museums.get(position);

                // Start Intent and Send Landmark Object to DetailActivity
                Utils.openDetailActivity(getContext(), museum);

            }
        });

        return rootView;
    }
}
