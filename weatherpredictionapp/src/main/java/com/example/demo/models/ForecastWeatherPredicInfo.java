package com.example.demo.models;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"date",
"maxtemp",
"mintemp",
"main",
"decision",
"raindecision",
"winddecision"
})

public class ForecastWeatherPredicInfo {

@JsonProperty("maxtemp")
private double maxtemp;

@JsonProperty("mintemp")
private double mintemp;

@JsonProperty("windspeed")
private double windspeed;

@JsonProperty("main")
private String main;

@JsonProperty("date")
private String date;

@JsonProperty("decision")
private String decision;

@JsonProperty("raindecision")
private String raindecision;

@JsonProperty("winddecision")
private String winddecision;


@JsonProperty("date")
public String getdate() {
return date;
}

@JsonProperty("date")
public void setdate(String object) {
this.date= object;
}

@JsonProperty("maxtemp")
public double getMaxtemp() {
	return maxtemp;
}

@JsonProperty("maxtemp")
public void setMaxtemp(double maxtemp) {
	this.maxtemp = maxtemp;
}

@JsonProperty("mintemp")
public double getMintemp() {
	return mintemp;
}

@JsonProperty("mintemp")
public void setMintemp(double mintemp) {
	this.mintemp = mintemp;
}

@JsonProperty("main")
public String getMain() {
	return main;
}

@JsonProperty("main")
public void setMain(String string) {
	this.main = string;
}

@JsonProperty("decision")
public String getDecision() {
	return decision;
}

@JsonProperty("decision")
public void setDecision(String decision) {
	this.decision = decision;
}

@JsonProperty("windspeed")
public double getWindspeed() {
	return windspeed;
}

@JsonProperty("windspeed")
public void setWindspeed(double windspeed) {
	this.windspeed = windspeed;
}

@JsonProperty("raindecision")
public String getRaindecision() {
	return raindecision;
}

@JsonProperty("raindecision")
public void setRaindecision(String raindecision) {
	this.raindecision = raindecision;
}

@JsonProperty("winddecision")
public String getWinddecision() {
	return winddecision;
}

@JsonProperty("winddecision")
public void setWinddecision(String winddecision) {
	this.winddecision = winddecision;
}



}