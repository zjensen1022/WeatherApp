package com.example.weatherapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class WeatherConditions {

    private int id;
    private String name;
    @SerializedName("main") private HashMap<String, Float> measurements;

    static final String CHARSET = StandardCharsets.UTF_8.name();

    //used in place of traditional constructor;
    public static WeatherConditions getConditions(String url) throws IOException{

        String responseBody = getResponse(url);
        Gson gson = new Gson();
        return gson.fromJson(responseBody, WeatherConditions.class);
    }

    public static String getResponse(String url) throws IOException{

        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", CHARSET);
        InputStream response = connection.getInputStream();

        Scanner scanner = new Scanner(response);
        return scanner.useDelimiter("\\a").next();
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "\nid: %d\nname: %s\nmeasurements:", id, name)
                + measurements.toString();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Float> getMeasurements() {
        return measurements;
    }

    public Float getTemp() {
        return measurements.get("temp");
    }
}
