package com.pretask.bobhead.myandroidweather.dagger;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pretask.bobhead.myandroidweather.weather.OpenWeatherApi;
import com.pretask.bobhead.myandroidweather.weather.WeatherManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    WeatherManager provideWeatherManager(OpenWeatherApi api) {
        return new WeatherManager(api);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /*
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        */
        return builder.build();
    }

    @Provides
    @Singleton
    OpenWeatherApi provideOpenWeatherApi(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(OpenWeatherApi.BASEURL)
                .build();
        return retrofit.create(OpenWeatherApi.class);
    }

}
