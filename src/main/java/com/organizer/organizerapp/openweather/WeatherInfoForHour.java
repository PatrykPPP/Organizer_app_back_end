package com.organizer.organizerapp.openweather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherInfoForHour {
	
	private LocalDateTime localDateTime;
	
	private double tempInK;
	
	private String weatherIcon;

	public WeatherInfoForHour() {
	}
	
	public WeatherInfoForHour(LocalDateTime localDateTime, double tempInK, String weatherIcon) {
		this.localDateTime = localDateTime;
		this.tempInK = tempInK;
		this.weatherIcon = weatherIcon;
	}

	@JsonCreator
	public WeatherInfoForHour(@JsonProperty("dt_txt") String stringLocalDateTime,
						@JsonProperty("main") WeatherConditions weatherContidions,
						@JsonProperty("weather") List<WeatherDescription> weatherDescriptions) {
		
		this.setTempInK(weatherContidions.getTempInK());
		
		this.setWeatherIcon(weatherDescriptions.get(0).getIcon());
		
		this.setLocalDateTime(stringLocalDateTime);		
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public void setLocalDateTime(String stringLocalDateTime) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		this.setLocalDateTime(LocalDateTime.parse(stringLocalDateTime, formatter));
	}

	public double getTempInK() {
		return tempInK;
	}

	public void setTempInK(double tempInK) {
		this.tempInK = tempInK;
	}
	
	public double getTempInC() {
		
		double tempInC = this.getTempInK() - 273.15;
		
		return tempInC;
	}
	
	public void setTempInC(double tempInC) {
		
		this.setTempInK(tempInC + 273.15);
	}

	public String getWeatherIcon() {
		return weatherIcon;
	}

	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	@Override
	public String toString() {
		return "WeatherInfo [localDateTime=" + localDateTime + ", tempInK=" + tempInK + ", weatherIcon=" + weatherIcon
				+ "]";
	}
	
}

class WeatherConditions{
	
	@JsonProperty("temp")
	private double tempInK;

	public double getTempInK() {
		return tempInK;
	}

	public void setTempInK(double tempInK) {
		this.tempInK = tempInK;
	}
}

class WeatherDescription{
	
	@JsonProperty("icon")
	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
