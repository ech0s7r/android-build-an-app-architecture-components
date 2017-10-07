package com.example.android.sunshine.data.network.openweather;

import com.example.android.sunshine.data.database.WeatherEntry;
import com.example.android.sunshine.data.network.openweather.pojo.GeneralWeather;
import com.example.android.sunshine.data.network.openweather.pojo.OpenWeatherMap;
import com.example.android.sunshine.utilities.SunshineDateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author ech0s7r
 */
public class OpenWeatherUtils {

    public static WeatherEntry[] newOpenWeather(OpenWeatherMap openWeatherMap) {
        ArrayList<WeatherEntry> weatherEntries = new ArrayList<>();
        GeneralWeather[] generalWeathers = openWeatherMap.list;
        if (generalWeathers != null) {
            long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcMsForToday();
            for (int i = 0; i < generalWeathers.length; i++) {
                GeneralWeather generalWeather = generalWeathers[i];
                long dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * i;
                Scanner s = new Scanner(generalWeather.weather[0].icon).useDelimiter("\\D");
                int icon = s.nextInt();
                WeatherEntry e = new WeatherEntry(
                        icon,
                        new Date(dateTimeMillis),
                        generalWeather.temp.min,
                        generalWeather.temp.max,
                        generalWeather.humidity,
                        generalWeather.pressure,
                        generalWeather.speed,
                        generalWeather.deg);
                weatherEntries.add(e);
            }
        }
        return weatherEntries.toArray(new WeatherEntry[weatherEntries.size()]);
    }

}
