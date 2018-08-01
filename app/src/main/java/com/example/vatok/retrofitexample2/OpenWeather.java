package com.example.vatok.retrofitexample2;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OpenWeather extends Application {

    private static OpenWeatherApi openWeatherApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openWeatherApi = retrofit.create(OpenWeatherApi.class);
    }

    public static OpenWeatherApi getApi() {
        return openWeatherApi;
    }
}