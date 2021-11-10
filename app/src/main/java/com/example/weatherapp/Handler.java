package com.example.weatherapp;

import android.app.Activity;

import java.lang.ref.WeakReference;

abstract class Handler implements Runnable{

    static final String UNITS = "&units=imperial";
    static final String KEY = "&apiKey="; //key here

    WeakReference<Activity> uiActivity;
    String city;
    String url;

    public Handler(Activity uiActivity, String city) {
        this.uiActivity = new WeakReference<>(uiActivity);
        this.city = city;
    }
}
