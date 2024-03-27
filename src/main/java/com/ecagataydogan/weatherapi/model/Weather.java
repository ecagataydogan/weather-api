package com.ecagataydogan.weatherapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.time.LocalDateTime;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestedCityName;
    private String cityName;
    private String country;
    private Integer temperature;
    private LocalDateTime updatedTime;
    private LocalDateTime responseLocalTime;

    public Weather(Long id, String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }

    public Weather(String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }

    public Weather() {

    }

    public Long getId() {
        return id;
    }

    public String getRequestedCityName() {
        return requestedCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getCountry() {
        return country;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }
}
