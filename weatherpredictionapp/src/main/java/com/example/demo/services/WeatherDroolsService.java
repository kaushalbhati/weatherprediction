package com.example.demo.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ForecastWeatherPredicInfo;

@Service
public class WeatherDroolsService {
	private final KieContainer kieContainer;

	@Autowired
	public WeatherDroolsService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public ForecastWeatherPredicInfo getWeatherDecision(ForecastWeatherPredicInfo product) {
		//get the stateful session
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(product);
		kieSession.setGlobal("WeatherDroolsService", this);
		kieSession.fireAllRules();
		kieSession.dispose();
		return product;
	}
	
	public void printMsg() {
		System.out.println("dsf");
	}


}
	
