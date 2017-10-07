package com.example.android.sunshine.data.network.openweather.api;

import android.util.Log;

import com.example.android.sunshine.data.network.openweather.pojo.OpenWeatherMap;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ech0s7r
 */
public class OpenWeather {


    private Retrofit retrofit;
    private OpenWeatherAPI api;

    private static final int HTTP_CODE_OK = 200;

    private static final OpenWeather sOpenWeather = new OpenWeather();

    private OpenWeather() {
        retrofit = new Retrofit.Builder()
                .baseUrl(OpenWeatherAPI.ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(OpenWeatherAPI.class);
    }

    public static OpenWeather getInstance() {
        return sOpenWeather;
    }


    public OpenWeatherMap getWeather(String city) {
        OpenWeatherMap weatherMap = null;
        try {
            Call<OpenWeatherMap> call = api.getWeather(city, 15, OpenWeatherAPI.API_KEY);
            Response<OpenWeatherMap> resp = call.execute();
            if (resp.code() == HTTP_CODE_OK) {
                weatherMap = resp.body();
            }
        } catch (Exception e) {
            //
            Log.e("Sunshine", "", e);
        }
        return weatherMap;
    }


}
