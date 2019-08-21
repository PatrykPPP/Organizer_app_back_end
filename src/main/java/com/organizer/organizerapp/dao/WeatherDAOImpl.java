package com.organizer.organizerapp.dao;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.organizer.organizerapp.entity.Weather;
import com.organizer.organizerapp.openweather.WeatherInfoFor5Days;
import com.organizer.organizerapp.openweather.WeatherInfoForHour;

@Repository
public class WeatherDAOImpl implements WeatherDAO {

	@Override
	public Weather getByDateAndTime(LocalDateTime localDateTime) {

		List<WeatherInfoForHour> weatherInfoForHours = this.getWeatherInfoForHours();

		Weather weather = this.getApproximatedWeather(weatherInfoForHours, localDateTime);
		
		String weatherIcon = "http://openweathermap.org/img/wn/"  + weather.getWeatherIcon() + "@2x.png";
		
		weather.setWeatherIcon(weatherIcon);

		return weather;
	}

	private List<WeatherInfoForHour> getWeatherInfoForHours() {

		RestTemplate restTemplate = new RestTemplate();

		try {
			
		} catch(Exception exc){
			
		}
		
		WeatherInfoFor5Days weatherInfoFor5Days = restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/forecast?q=warsaw,pl&appid=a783f1f518edaa5e1c966dcafce24183",
				WeatherInfoFor5Days.class);

		List<WeatherInfoForHour> weathers = weatherInfoFor5Days.getWeatherInfoFor5Days();

		return weathers;
	}

	private double getAbsTimeBetweenDatesInMinutes(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {

		double absTimeBetweenInMinutes = Math.abs(localDateTime1.until(localDateTime2, ChronoUnit.MINUTES));

		return absTimeBetweenInMinutes;
	}

	private double getTimeBetweenDatesInMinutes(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {

		double absTimeBetweenInMinutes = localDateTime1.until(localDateTime2, ChronoUnit.MINUTES);

		return absTimeBetweenInMinutes;
	}

	private Weather getApproximatedWeather(List<WeatherInfoForHour> weatherInfoForHours, LocalDateTime localDateTime) {

		WeatherInfoForHour weatherInfoForHour1 = weatherInfoForHours.get(0);

		for (WeatherInfoForHour weatherInfoForHour : weatherInfoForHours) {

			if (getAbsTimeBetweenDatesInMinutes(localDateTime, weatherInfoForHour1.getLocalDateTime()) 
					> getAbsTimeBetweenDatesInMinutes(localDateTime, weatherInfoForHour.getLocalDateTime())) {

				weatherInfoForHour1 = weatherInfoForHour;

			}

		}

		WeatherInfoForHour weatherInfoForHour2;

		if (weatherInfoForHours.get(0) == weatherInfoForHour1) {
			weatherInfoForHour2 = weatherInfoForHours.get(1);
		} else {
			weatherInfoForHour2 = weatherInfoForHours.get(0);
		}

		for (WeatherInfoForHour weatherInfoForHour : weatherInfoForHours) {

			if (getAbsTimeBetweenDatesInMinutes(localDateTime, weatherInfoForHour2.getLocalDateTime()) 
					> getAbsTimeBetweenDatesInMinutes(localDateTime, weatherInfoForHour.getLocalDateTime())) {

				if (weatherInfoForHour != weatherInfoForHour1) {
					weatherInfoForHour2 = weatherInfoForHour;
				}

			}
		}

		double tempInC1 = weatherInfoForHour1.getTempInC();

		double tempInC2 = weatherInfoForHour2.getTempInC();

		LocalDateTime localDateTime1 = weatherInfoForHour1.getLocalDateTime();

		LocalDateTime localDateTime2 = weatherInfoForHour2.getLocalDateTime();

		double tempInC = tempInC1
				+ ((tempInC1 - tempInC2) / getTimeBetweenDatesInMinutes(localDateTime1, localDateTime2))
						* getTimeBetweenDatesInMinutes(localDateTime, localDateTime1);

		tempInC = Math.round(tempInC);

		Weather weather = new Weather();

		weather.setLocalDateTime(localDateTime);
		weather.setTempInC((int) tempInC);

		if (getAbsTimeBetweenDatesInMinutes(localDateTime,
				localDateTime1) > getAbsTimeBetweenDatesInMinutes(localDateTime, localDateTime2)) {
			weather.setWeatherIcon(weatherInfoForHour2.getWeatherIcon());
		} else {
			weather.setWeatherIcon(weatherInfoForHour1.getWeatherIcon());
		}
		
		return weather;
		
	}
	

}
