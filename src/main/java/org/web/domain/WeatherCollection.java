package org.web.domain;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.web.domain.WeatherInfo;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class WeatherCollection implements Serializable {	
	
	private static final long serialVersionUID = -2669558347198579172L;
	public ArrayList<WeatherInfo> weatherInfoList = new ArrayList<WeatherInfo>();	
	
	public ArrayList<WeatherInfo> getWeatherInfoList() {
		return weatherInfoList;
	}
	public void setWeatherInfoList(ArrayList<WeatherInfo> weatherInfoList) {
		this.weatherInfoList = weatherInfoList;
	}
	
	private String city, country,timeZone;
	
	
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
	
	
	public void addWeatherInfoObject(WeatherInfo weatherInfo){
		this.weatherInfoList.add(weatherInfo);
	}
	public void clearWeatherInfoList(){
		this.weatherInfoList.clear();
	}
	
}
