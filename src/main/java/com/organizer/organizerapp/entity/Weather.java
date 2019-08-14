package com.organizer.organizerapp.entity;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Weather {
	
	private LocalDateTime localDateTime;
	
	private int tempInC;
	
	private String weatherIcon;
	
	public Weather() {
	}

	public Weather(LocalDateTime localDateTime, int tempInC, String weatherIcon) {
		this.localDateTime = localDateTime;
		this.tempInC = tempInC;
		this.weatherIcon = weatherIcon;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public int getTempInC() {
		return tempInC;
	}

	public void setTempInC(int tempInC) {
		this.tempInC = tempInC;
	}

	public String getWeatherIcon() {
		return weatherIcon;
	}

	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	@Override
	public String toString() {
		return "Weather [localDateTime=" + localDateTime + ", tempInC=" + tempInC + ", weatherIcon=" + weatherIcon
				+ "]";
	}
}
