package org.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.web.domain.WeatherInfo;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

public class WeatherXmlHandler extends DefaultHandler2  {
	
	private String country, district, city;
	//@Autowired
	//private WeatherCollection weatherCollection;
	private WeatherInfo weatherInfo = null;
	private WeatherInfo weatherInfoDescription = null;	
	private ArrayList<WeatherInfo> weatherInfoList;	
	
	private String textTmpVal;
	//private StringBuffer stringBuffer;
	private URL linkURL;
	private String link;
	private URLConnection urlConnection;
	private BufferedReader bufferedReader;
	private InputSource inputSource;
	
	private SAXParserFactory parserFactor;
	private SAXParser parser;	
	
	public WeatherXmlHandler(String country1, String district1, String city1){		
		
		this.country = country1;
		this.district = district1;
		this.city = city1;
		
		parserFactor = SAXParserFactory.newInstance();
		try {
			parser = parserFactor.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAILED ON CREATING NEW PARSER FACTORY!!!");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAILED ON CREATING NEW PARSER!!!");
		}
		link="http://www.yr.no/place/" + this.country + "/" + this.district + "/" + this.city + "/forecast_hour_by_hour.xml";
		//String link="http://www.yr.no/place/Sweden/Västra_Götaland/Trollhättan/forecast.xml";
		
		try {
			linkURL = new URL(link);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAILED ON CREATING NEW URLLINK!!!");
		}
		try {
			urlConnection=linkURL.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAILED ON OPEN URLCONNECTION!!!");
		}
		urlConnection.setConnectTimeout(120000);
		urlConnection.setReadTimeout(120000);
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAILED ON CREATING NEW BUFFEREDREDAER!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAILED ON CREATING NEW BUFFEREDREDAER!!!");
		}
		inputSource=new InputSource(bufferedReader);		
		weatherInfoList = new ArrayList<WeatherInfo>();
	}
	/*---------------------------------START GETTERS AND SETTERS------------------------------------------------------------*/
	public ArrayList<WeatherInfo> getWeatherInfoList() {
		return weatherInfoList;
	}
	public void setWeatherInfoList(ArrayList<WeatherInfo> weatherInfoList) {
		this.weatherInfoList = weatherInfoList;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	/*---------------------------------/STOP GETTERS AND SETTERS------------------------------------------------------------*/
	
	
	// A start tag is encountered.
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {			
		
		switch (qName) {
			//Time is first element for instance of class Weatherinfo so create an object			
			case "location":{
				weatherInfoDescription = null;
				weatherInfoDescription = new WeatherInfo();
			}
			case "timezone":{
				weatherInfoDescription.setTimeZone(attributes.getValue("id"));				
			}				
			case "time":{					
				weatherInfo=null;
				weatherInfo = new WeatherInfo();
				weatherInfo.setTimeFrom(attributes.getValue("from"));
				weatherInfo.setTimeTo(attributes.getValue("to"));
				/*
				System.out.println("Checking if weatherinfo gets a value!");
				System.out.println(weatherInfo.getTimeFrom());
				System.out.println(attributes.getValue("from"));
				System.out.println(attributes.getValue("to"));
				*/				
			}			
			case "symbol":{
				weatherInfo.setWeatherDescription(attributes.getValue("name"));
			}
			case "precipitation":{
				weatherInfo.setPrecipitation(attributes.getValue("value"));
			}
			case "windDirection":{
				weatherInfo.setWindDirection(attributes.getValue("name"));
			}
			case "windSpeed":{
				weatherInfo.setWindSpeed(attributes.getValue("mps"));
				weatherInfo.setWindSpeedDescription(attributes.getValue("name"));
			}			
			case "temperature":{
				weatherInfo.setTemperature(attributes.getValue("value"));
			}
			case "pressure":{
				weatherInfo.setPressureUnit(attributes.getValue("unit"));
				weatherInfo.setPressureValue(attributes.getValue("value"));
			}			
		}	
	}		
	@Override
	public void characters(char[] ac, int i, int j) throws SAXException {
		textTmpVal = new String(ac, i, j);
    }

	@Override	
     public void endElement(String uri, String localName, String qName) throws SAXException {	
          switch (qName) {	
               case "name": {	
            	   weatherInfoDescription.setCity(textTmpVal);
            	   // The end tag of name was encountered, so add the weatherinfo to the list.               	   
            	   //weatherCollection.addWeatherInfoObject(weatherInfo);
            	   
            	   //System.out.println("Size of weatherinfoList is: ");
            	   //System.out.println(weatherInfoList.size());
            	   //Clear wheatherinfo for a new one in next lap!
            	   //weatherInfo=null;
                   break;	
               }	
               case "country":{
            	   weatherInfoDescription.setCountry(textTmpVal);							
               }
               case "location":{
            	   weatherInfoList.add(weatherInfoDescription);
            	   //weatherInfo=null;
               }   
               case "time":{
            	   weatherInfoList.add(weatherInfo);
            	   //weatherInfo=null;
               }
          }	
     }		
	public void startParse() throws SAXException, IOException{
		parser.parse(inputSource,this);
	}
}
