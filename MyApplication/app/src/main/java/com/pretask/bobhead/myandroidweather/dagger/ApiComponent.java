package com.pretask.bobhead.myandroidweather.dagger;

import com.pretask.bobhead.myandroidweather.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity activity);
}
