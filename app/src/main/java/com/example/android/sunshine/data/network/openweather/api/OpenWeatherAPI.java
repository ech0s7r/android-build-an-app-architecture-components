package com.example.android.sunshine.data.network.openweather.api;


import com.example.android.sunshine.data.network.openweather.pojo.OpenWeatherMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author ech0s7r
 */
public interface OpenWeatherAPI {

    String ENDPOINT_URL = "http://api.openweathermap.org";
    String API_KEY = "bd5e378503939ddaee76f12ad7a97608";

    @GET("/data/2.5/forecast/daily?units=metric")
    Call<OpenWeatherMap> getWeather(@Query("q") String cityName,
                                    @Query("cnt") int dayCount,
                                    @Query("APPID") String apiKey);

}
