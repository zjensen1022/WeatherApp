package com.example.weatherapp;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class TempHandler extends Handler{

    private static final String TAG = "TempHandler";
    private static final String SITE = "https://api.openweathermap.org/data/2.5/weather?q=";

    public TempHandler(Activity uiActivity, String city) {
        super(uiActivity, city);
        url = SITE + city + UNITS + KEY;
    }

    @Override
    public void run() {

        Log.d(TAG,"Getting temperature for " + city + ".");

        try{
            WeatherConditions conditions = WeatherConditions.getConditions(url);

            System.out.println(conditions);

            String message = String.format(Locale.US,"Current temp for %s: %.1f\u00B0F", conditions.getName(), conditions.getTemp());

            uiActivity.get().runOnUiThread(() -> {
                Toast.makeText(uiActivity.get(), message, Toast.LENGTH_LONG).show();
            });

        } catch (FileNotFoundException e) {
            String message = String.format(Locale.US, "Couldn't find a city with the name: \"%s\".", city);

            uiActivity.get().runOnUiThread(() -> {
                Toast.makeText(uiActivity.get(), message, Toast.LENGTH_LONG).show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
