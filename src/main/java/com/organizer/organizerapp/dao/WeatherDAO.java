package com.organizer.organizerapp.dao;

import java.time.LocalDateTime;

import com.organizer.organizerapp.entity.Weather;

public interface WeatherDAO {

	public Weather getByDateAndTime(LocalDateTime localDateTime);
}
