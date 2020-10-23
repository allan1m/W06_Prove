package com.example.w06_prove;

import android.util.Log;

//Part II, Step 01: Create a class with a method that will run on a background thread.
// This method will eventually get the current temperature for a city.

public class GetWeatherForecastAsync implements Runnable {
    //MainActivity is required to have access to UI thread
    //city is required we can get weather conditions
    private MainActivity activity;
    private String city;

    //Part II, Step 02: You might consider passing the name of the city to your class
    // in its constructor and having it store it as a member variable. Then, later in
    // a run method, it can access the name of that city from that member variable.

    public GetWeatherForecastAsync(MainActivity activity, String city) {
        this.activity = activity;
        this.city = city;
    }

    //Part II, Step 03: At this point, have your run method produce a debug message that
    // says it is getting the temperature for that city on a background thread.

    @Override
    public void run() {
        // This is the function that will be run on the background thread.
        WeatherDataLoader loader = new WeatherDataLoader();

        // Now, call the function that will get the results from the API and then when it is done,
        // it will call the "handleResult" function on this new WeatherConditionsResultHandler
        // object that we are giving it.

        final WeatherConditions conditions = loader.getWeatherAndPostResults(city);

        //Part II, Step 04: Create a new thread and have it invoke your method above.
        // Verify that the debug message is shown correctly.

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This is code that will now run on the UI thread. Call the function in
                // MainActivity that will update the UI correctly.
                activity.handleWeatherConditionsResult(conditions);
            }
        });
    }
}
