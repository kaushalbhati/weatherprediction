package com.example.demo.controller;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Example;
import com.example.demo.models.WeatherPredicInfo;
import com.example.demo.services.LocationInformation;
import com.example.demo.services.WeatherDroolsService;
import com.example.demo.services.WeatherService;
import com.example.demo.sharedmodels.LangLat;

@CrossOrigin(origins = "*")
@RestController(value="/bhati")
public class WeatherController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/test")
	public String sayHello() {
		return "Swagger Hello World";
	}
	
	@Autowired
	private LocationInformation locationinfo;
	
	@Autowired
    private WeatherService weatherService;

	private final WeatherDroolsService weatherdroolservice;

	@Autowired
	public WeatherController(WeatherDroolsService weatherdroolservice) {
		this.weatherdroolservice = weatherdroolservice;
	}
	
    /*
    @Autowired
    public WeatherController(WeatherService weatherService) {
       this.weatherService = weatherService;
    }
    */

	/*
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    */
	@RequestMapping(method = RequestMethod.GET, value = "/forecast/{fcurrentcity}")
    //@RequestMapping(path="/forecast/{fcurrentcity}",method = RequestMethod.GET)
	public List<Example> getWeatherForFive(
			@PathVariable String fcurrentcity) throws ParseException {
		return this.weatherService.getWeatherForFive(fcurrentcity);		
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/weather/{wcurrentcity}")
    //@RequestMapping(path="/weather/{wcurrentcity}", method = RequestMethod.GET)
	public List<Example> getWeather(
			@PathVariable String wcurrentcity) {
		return this.weatherService.getWeather(wcurrentcity);		
	}
	
    //@RequestMapping(path="/oneweather/{ocurrentcity}", method = RequestMethod.GET)
    @RequestMapping(method = RequestMethod.GET, value = "/oneweather/{ocurrentcity}")
	public List<WeatherPredicInfo> getOneWeather(
			@PathVariable String ocurrentcity) {
		
		double longitude = 0;
		double latitutde = 0;
		
		List<LangLat> langlat = locationinfo.getLangLatBasedOnName(ocurrentcity);
		
		System.out.println("langlat"+langlat);
		for (Iterator iterator = langlat.iterator(); iterator.hasNext();) {
			LangLat langLat2 = (LangLat) iterator.next();
			longitude = langLat2.getLon();
			latitutde = langLat2.getLat();
		}
		System.out.println("longitude"+longitude);
		System.out.println("latitutde"+latitutde);
		
		return this.weatherService.getDailyWeatherInfo(longitude,latitutde);		
	}
}