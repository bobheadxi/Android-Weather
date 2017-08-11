package com.pretask.bobhead.myandroidweather.weather;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Describes OpenWeatherMap's API
public interface OpenWeatherApi {
    String BASEURL = "https://api.openweathermap.org/data/2.5/";

    // OpenWeatherMap's API endpoints, in this case only need weather
    @GET("weather")
    Observable<Response> getWeather(@Query("id") String cityId,
                                    @Query("appid") String appId);

    // Fields are named according to API response
    class Response {
        ArrayList<Weather> weather;
        Data main;
        Wind wind;
        Clouds clouds;
        String name;

        static class Weather {
            String description;
        }

        static class Data {
            double temp;
            double humidity;
            double temp_min;
            double temp_max;
        }

        static class Wind {
            double speed;
        }

        static class Clouds {
            int all;
        }
    }
}
