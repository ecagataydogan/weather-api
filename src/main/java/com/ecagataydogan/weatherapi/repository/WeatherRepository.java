package com.ecagataydogan.weatherapi.repository;

import com.ecagataydogan.weatherapi.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long> {

    Optional<Weather> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);
}
