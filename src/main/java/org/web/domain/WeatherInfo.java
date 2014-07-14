package org.web.domain;

import java.io.Serializable;

public class WeatherInfo implements Serializable{	
	
	private static final long serialVersionUID = 4878690721619803402L;
	private String timeFrom, timeTo,weatherDescription,precipitation,
		windDirection, windSpeed, windSpeedDescription, temperature, pressureUnit, pressureValue,
		city, country,district, timeZone;

	public WeatherInfo(){
		
	}
	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindSpeedDescription() {
		return windSpeedDescription;
	}

	public void setWindSpeedDescription(String windSpeedDescription) {
		this.windSpeedDescription = windSpeedDescription;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getPressureUnit() {
		return pressureUnit;
	}

	public void setPressureUnit(String pressureUnit) {
		this.pressureUnit = pressureUnit;
	}

	public String getPressureValue() {
		return pressureValue;
	}

	public void setPressureValue(String pressureValue) {
		this.pressureValue = pressureValue;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}		
	
}
