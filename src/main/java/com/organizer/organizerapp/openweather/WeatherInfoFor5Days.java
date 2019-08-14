package com.organizer.organizerapp.openweather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class WeatherInfoFor5Days {

	@JsonProperty("list")
	private List<WeatherInfoForHour> weatherInfoFor5Days;

	public WeatherInfoFor5Days() {
	}

	public WeatherInfoFor5Days(List<WeatherInfoForHour> weatherInfoFor5Days) {
		this.weatherInfoFor5Days = weatherInfoFor5Days;
	}

	public List<WeatherInfoForHour> getWeatherInfoFor5Days() {
		return weatherInfoFor5Days;
	}

	public void setWeatherInfoFor5Days(List<WeatherInfoForHour> weatherInfoFor5Days) {
		this.weatherInfoFor5Days = weatherInfoFor5Days;
	}

	@Override
	public String toString() {
		return "WeatherInfoFor5Days [weatherInfoFor5Days=" + weatherInfoFor5Days + "]";
	}
	
}
