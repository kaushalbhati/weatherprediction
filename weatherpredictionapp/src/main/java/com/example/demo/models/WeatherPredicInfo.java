package com.example.demo.models;


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
"forecast_weather"
})
public class WeatherPredicInfo {

@JsonProperty("forecast_weather")
private ForecastWeatherPredicInfo forecastWeather;

@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("forecast_weather")
public ForecastWeatherPredicInfo getForecastWeather() {
return forecastWeather;
}

@JsonProperty("forecast_weather")
public void setForecastWeather(ForecastWeatherPredicInfo forecastWeather) {
this.forecastWeather = forecastWeather;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
