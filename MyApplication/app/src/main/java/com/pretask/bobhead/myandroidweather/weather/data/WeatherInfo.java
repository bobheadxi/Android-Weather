package com.pretask.bobhead.myandroidweather.weather.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WeatherInfo {
    private String cityName;
    private double[] temperatures;
    private String description;

    // Holds data about current weather of a city
    public WeatherInfo(String cityName,
                       double[] temps,
                       String description) {
        this.cityName = cityName;
        this.temperatures = temps;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public List<String> getTemperatures() {
        List<String> temps = new ArrayList<>();
        for (double t : temperatures) {
            temps.add(toCelsius(t));
        }
        return temps;
    }

    public String getDescription() {
        return description;
    }

    private String toCelsius(double kelvin) {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(kelvin - 273.15);
    }
}
