package com.example.w06_prove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeather(View view){
        EditText txtCity = findViewById(R.id.editCity);
        String city = txtCity.getText().toString();

        Log.i("MainActivity", "Getting weather for: " + city);

        GetWeatherAsync getWeatherAsync = new GetWeatherAsync(this, city);

        Thread t = new Thread(getWeatherAsync);

        t.start();
    }

    //Part IV: Now that you have retrieved the current temperature on a background thread,
    // you need to communicate this information to the UI and inform the user
    // via a Toast message.

    void handleWeatherConditionsResult(WeatherConditions conditions){

        if (conditions == null){
            Toast.makeText(this, "An error occurred when retrieving weather", Toast.LENGTH_LONG).show();
        }
        else{
            Float temp = conditions.getMeasurements().get("temp");

            Toast.makeText(this, "It is currently " + temp + " degrees.", Toast.LENGTH_LONG).show();
        }
    }

    public void getWeatherForecast(View view){
        EditText txtCity = findViewById(R.id.editCity);
        String city = txtCity.getText().toString();

        Log.i("MainActivity", "Getting weather forecast for: " + city);

        GetWeatherForecastAsync getWeatherForecastAsync = new GetWeatherForecastAsync(this, city);

        Thread t = new Thread(getWeatherForecastAsync);

        t.start();
    }
}