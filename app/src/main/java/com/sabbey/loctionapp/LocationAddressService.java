package com.sabbey.loctionapp;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.ResultReceiver;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationAddressService extends IntentService {

    public LocationAddressService() {
        super("LocationAddressService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        Location location = intent.getParcelableExtra("location");
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (Exception e) {

            MainActivity.res = "Something went wrong";
        }

        if (addresses == null || addresses.size() == 0) {
            MainActivity.res = "No address found";
        } else {

            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<>();

            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++)
            {
               addressFragments.add(address.getAddressLine(i));
            }

            MainActivity.res = TextUtils.join(System.getProperty("line.separator"), addressFragments);

        }

    }


}

