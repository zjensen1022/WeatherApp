package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button currentTempBtn = findViewById(R.id.currentTempButton);
        Button forecastBtn = findViewById(R.id.forecastButton);

        currentTempBtn.setOnClickListener(this::getCurrentTemp);
        forecastBtn.setOnClickListener(this::getForecast);
    }

    public void getCurrentTemp(View view) {
        Thread thread = new Thread(new TempHandler(this, getStringFromEditText()));
        thread.start();
    }

    public void getForecast(View view) {
        Thread thread = new Thread(new ForecastHandler(this, getStringFromEditText()));
        thread.start();
    }

    public String getStringFromEditText() {
        EditText city = findViewById(R.id.cityEditText);

        return city.getText().toString();
    }
}