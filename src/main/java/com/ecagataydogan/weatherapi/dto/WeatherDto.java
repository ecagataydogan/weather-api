package com.ecagataydogan.weatherapi.dto;

import com.ecagataydogan.weatherapi.model.Weather;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime updatedTime
) {
    public static WeatherDto convert(Weather weather) {
        return new WeatherDto(weather.getCityName(), weather.getCountry(), weather.getTemperature(),weather.getUpdatedTime());
    }
}
