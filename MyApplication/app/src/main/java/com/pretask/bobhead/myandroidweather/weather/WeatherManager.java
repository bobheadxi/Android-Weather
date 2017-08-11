package com.pretask.bobhead.myandroidweather.weather;

import com.pretask.bobhead.myandroidweather.weather.data.WeatherInfo;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WeatherManager {
    private OpenWeatherApi openWeatherApi;
    private String appId = "02bfe6d8ea1c22058d4619b532951a50"; // should probably extract

    public WeatherManager(OpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    // Make API call
    public Observable<WeatherInfo> getWeather(String cityId) {
        return openWeatherApi.getWeather(cityId, appId)
                .observeOn(Schedulers.io())
                .map(this::MakeWeatherInfo);
    }

    // Convert API response into more relevant format
    private WeatherInfo MakeWeatherInfo(OpenWeatherApi.Response response) {
        return new WeatherInfo(response.name,
                new double[]{response.main.temp_min, response.main.temp, response.main.temp_max},
                response.weather.get(0).description);
    }
}
