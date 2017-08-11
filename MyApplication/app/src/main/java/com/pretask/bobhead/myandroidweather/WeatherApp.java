package com.pretask.bobhead.myandroidweather;

import android.app.Application;

import com.pretask.bobhead.myandroidweather.dagger.ApiComponent;
import com.pretask.bobhead.myandroidweather.dagger.ApiModule;
import com.pretask.bobhead.myandroidweather.dagger.DaggerApiComponent;

public class WeatherApp extends Application {
    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Instantiate Dagger component and modules
        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
