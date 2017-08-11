package com.pretask.bobhead.myandroidweather;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;

import com.pretask.bobhead.myandroidweather.weather.data.WeatherInfo;
import com.pretask.bobhead.myandroidweather.weather.WeatherManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {
    @Inject WeatherManager weatherManager;
    private String cityId = "4699066"; // Houston
    Button weatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherButton = (Button) findViewById(R.id.button);

        // Provide WeatherManager
        ((WeatherApp) getApplication()).getApiComponent().inject(this);

        // Gets weather on button press, because fetching on app open is confusing behaviour
        // Ideally would not do it here on main thread but yolo
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        weatherButton.setOnClickListener(view -> getWeather());
    }

    // Gets stuff
    private void getWeather() {
        weatherManager.getWeather(cityId)
                .observeOn(Schedulers.io())
                .subscribe(response -> {
                    runOnUiThread(() -> displayWeather(response));
                });
    }

    // Populates TextView
    private void displayWeather(WeatherInfo info) {
        List<String> temps = info.getTemperatures();
        TextView weatherDisplay = (TextView) findViewById(R.id.weather_display);
        weatherDisplay.setText("The temperature is " + temps.get(1)
                + "°C with a high of " + temps.get(2)
                + "°C and a low of " + temps.get(0)
                + "°C. Condition: " + info.getDescription() + "."
        );
    }
}
