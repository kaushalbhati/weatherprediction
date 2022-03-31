package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.RestTemplateClass;
import com.example.demo.sharedmodels.LangLat;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LocationInformation {
	
	@Autowired   
	RestTemplateClass restTemplate;
	
	@Value("${openweathermap.path}")
	private String openweathermappath;
	
	@Value("${openweathermap.apikey}")
	private String openweathermapapikey;
	
	public List<LangLat> getLangLatBasedOnName(String cityName) {
		
		String uri = openweathermappath+"?q="+cityName+"&limit=1&appid="+openweathermapapikey;
		ResponseEntity<List<LangLat>> responseEntity = 
				  restTemplate.exchange(
				    uri,
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<LangLat>>() {}
				  );
				List<LangLat> langlatlist = responseEntity.getBody();
				
	    
		return langlatlist;
		
	}

}
