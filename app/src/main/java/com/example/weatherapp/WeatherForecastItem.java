package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class WeatherForecastItem {

    private String dt_txt;
    @SerializedName("main") private HashMap<String, Float> measurements;
    private HashMap<String, Float> wind;
    private List<HashMap<String, String>> weather;

    @Override
    public String toString() {
        return String.format(Locale.US,
                "\nTime: %s\nTemp: %.1f F\nDescription: %s\nWind Speed: %.1f MPH",
                dt_txt,
                measurements.get("temp"),
                weather.get(0).get("description"),
                wind.get("speed"));
    }

    public float getTemp() {
        return measurements.get("temp");
    }

    public float getWindSpeed() {
        return wind.get("speed");
    }

}
