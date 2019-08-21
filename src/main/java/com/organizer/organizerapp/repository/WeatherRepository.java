package com.organizer.organizerapp.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.organizer.organizerapp.entity.Weather;

public interface WeatherRepository {

	public Optional<Weather> getByDateAndTime(LocalDateTime localDateTime);
}
