package com.ecagataydogan.weatherapi.dto;

public record WeatherResponse(
        Request request,
        Location location,
        Current current
) {
}
