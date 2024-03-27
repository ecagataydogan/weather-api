package com.ecagataydogan.weatherapi.service;

import com.ecagataydogan.weatherapi.dto.WeatherDto;
import com.ecagataydogan.weatherapi.dto.WeatherResponse;
import com.ecagataydogan.weatherapi.model.Weather;
import com.ecagataydogan.weatherapi.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "weathers")
public class WeatherService {
    private static final String API_URL ="http://api.weatherstack.com/current?access_key=96f0c2810b2aa460379dff2a14e4838a&query=";
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Cacheable(key = "#city")
    public WeatherDto getWeatherByCityName(String city) {
        Optional<Weather> weatherOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);
        if (weatherOptional.isEmpty()) {
            return WeatherDto.convert(getWeatherFromWeatherStack(city));
        }
        if(weatherOptional.get().getUpdatedTime().isBefore(LocalDateTime.now().minusMinutes(10))) {
            return WeatherDto.convert(getWeatherFromWeatherStack(city));
        }

        return WeatherDto.convert(weatherOptional.get());


    }

    private Weather getWeatherFromWeatherStack(String city) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL + city ,String.class);
        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeather(city,weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private Weather saveWeather(String city,WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Weather weather = new Weather(city
                ,weatherResponse.location().name()
                ,weatherResponse.location().country()
                ,weatherResponse.current().temperature()
                ,LocalDateTime.now()
                ,LocalDateTime.parse(weatherResponse.location().localtime(),dateTimeFormatter));

        return weatherRepository.save(weather);
    }

    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "10000")
    public void clearCache() {

    }
}
