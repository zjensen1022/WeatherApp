package com.example.weatherapp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class WeatherForecast {

    String city;
    WeatherForecastItem[] forecastItems;

    public WeatherForecast(String url) throws IOException {

        String response = WeatherConditions.getResponse(url);
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(response, JsonObject.class);

        forecastItems = gson.fromJson(object.get("list"), WeatherForecastItem[].class);
        city = object.getAsJsonObject("city").get("name").getAsString();
    }

    public String getCity() {
        return city;
    }

    public WeatherForecastItem[] getForecastItems() {
        return forecastItems;
    }

    public void display() {
        for(WeatherForecastItem item: forecastItems){
            System.out.println(item);
        }
    }

    private float findMaxTemp() {
        float maxTemp = forecastItems[0].getTemp();

        for (WeatherForecastItem item: forecastItems) {
            if (item.getTemp() > maxTemp)
                maxTemp = item.getTemp();
        }
        return maxTemp;
    }

    private float findMaxWind() {
        float maxWind = forecastItems[0].getWindSpeed();

        for (WeatherForecastItem item: forecastItems) {
            if (item.getWindSpeed() > maxWind)
                maxWind = item.getWindSpeed();
        }
        return maxWind;
    }
}
