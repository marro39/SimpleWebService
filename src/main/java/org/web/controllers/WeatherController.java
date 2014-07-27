package org.web.controllers;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.core.WeatherXmlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web.domain.WeatherInfo;
import org.xml.sax.SAXException;

@Controller
public class WeatherController {
	
	String country, discrict, city;
	//@Autowired	
	//private WeatherCollection weatherCollection ;
	//@Autowired
	private WeatherXmlHandler weatherXmlHandler;
	private ArrayList<WeatherInfo> weatherDataList = new ArrayList<WeatherInfo>();
	private int counter;
	private ArrayList<Integer> posOfEle = new ArrayList<Integer>();
	
	@RequestMapping(value="", method=RequestMethod.GET)
	//@ResponseBody
	public String getHomePage(){
		return "home";		
	}	
	
	@RequestMapping(value="/weather", method=RequestMethod.GET)	
	public @ResponseBody ArrayList<WeatherInfo> getWeatherFromCity(@RequestParam(value="country", required=false, defaultValue="Sweden") String country,
			@RequestParam(value="district", required=false, defaultValue="Västra_Götaland") String district,
			@RequestParam(value="city", required=false, defaultValue="Trollhättan") String city	) throws ParserConfigurationException, SAXException, IOException{
		
		this.country=URLEncoder.encode(country, "UTF-8");   //country;
		this.discrict=URLEncoder.encode(district, "UTF-8");  //district;
		this.city=URLEncoder.encode(city, "UTF-8");   //city;
		
		weatherXmlHandler=new WeatherXmlHandler(this.country, this.discrict, this.city);
		weatherXmlHandler.startParse();	
		
		weatherDataList=weatherXmlHandler.getWeatherInfoList();
		
		for (WeatherInfo weatherInf : weatherDataList) {
			
			if(weatherInf.getTimeZone()==null && weatherInf.getTimeFrom()==null){
				//weatherDataList.remove(counter);
				System.out.println("TIMEZONE AND TIMEFROM ARE NULL");
				counter=weatherDataList.indexOf(weatherInf);
				posOfEle.add(counter);
				System.out.println("POSITION OF THIS OBJECT ARE: " + counter);							
			}			
		}	
		//Element 1,2,3,4,5 are always emtpy = remove them!
		weatherDataList.remove(1);
		weatherDataList.remove(1);
		weatherDataList.remove(1);
		weatherDataList.remove(1);
		weatherDataList.remove(1);		
		
		return weatherDataList;
	}

	public WeatherXmlHandler getWeatherXmlHandler() {
		return weatherXmlHandler;
	}

	public void setWeatherXmlHandler(WeatherXmlHandler weatherXmlHandler) {
		this.weatherXmlHandler = weatherXmlHandler;
	}
	
	
}
