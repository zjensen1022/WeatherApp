package com.example.weatherapp;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class ForecastHandler extends Handler{

    private static final String TAG = "ForecastHandler";
    private static final String SITE = "https://api.openweathermap.org/data/2.5/forecast?q=";

    public ForecastHandler(Activity uiActivity, String city) {
        super(uiActivity, city);
        url = SITE + city + UNITS + KEY;
    }

    @Override
    public void run() {

        Log.d(TAG,"Getting forecast for " + city + ".");

        try {
            WeatherForecast forecast = new WeatherForecast(url);

            System.out.println(forecast);

            uiActivity.get().runOnUiThread(() -> {

                ArrayAdapter<WeatherForecastItem> adapter = new ArrayAdapter<>(
                        uiActivity.get(),
                        android.R.layout.simple_list_item_1,
                        forecast.getForecastItems());

                ListView container = uiActivity.get().findViewById(R.id.forecastContainer);
                container.setAdapter(adapter);
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
