package com.example.demo.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.example.demo.models.Example;
import com.example.demo.models.ForecastWeather;
import com.example.demo.models.Main;
import com.example.demo.models.Time;
import com.example.demo.models.Today;
import com.example.demo.models.TodaysWeather;
import com.example.demo.models.Weather;
import com.example.demo.sharedmodels.LangLat;
import com.example.demo.sharedmodels.daily.Daily;
import com.example.demo.sharedmodels.daily.OneWeather;
import com.example.demo.sharedmodels.daily.Temp;
import com.example.demo.RestTemplateClass;
import com.example.demo.models.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class WeatherService extends MappingJackson2HttpMessageConverter   {

	@Autowired   
	RestTemplateClass restTemplate;
	
	@Value("${openweathermap.forecast.path}")
	private String openweathermapfpath;
	
	@Value("${openweathermap.weather.path}")
	private String openweathermapwpath;
	
	@Value("${openweathermap.onecall.path}")
	private String openweathermapopath;
	
	@Value("${openweathermap.apikey}")
	private String openweathermapapikey;
	
	private final WeatherDroolsService weatherdroolservice;

	@Autowired
	public WeatherService(WeatherDroolsService weatherdroolservice) {
		this.weatherdroolservice = weatherdroolservice;
	}
	
    private static WeatherService ourInstance = new WeatherService();
       
    public static WeatherService getInstance() {
        return ourInstance;
    }

    private WeatherService() {
    	this.weatherdroolservice = null;
		setPrettyPrint(true);
    }

    public List<Example> getWeatherForFive(String city) throws ParseException {
       
    	String websiteResponse = openweathermapfpath+"?q=" + city + "&mode=json&appid="+openweathermapapikey+"&units=metric";
    	
    	RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(websiteResponse, String.class);	    	   	    	        
        
        String description = null;
        double temp=0;
        int pressure=0;
        int humidity = 0;
        int temp_min=0;
        int temp_max=0;
        int temp_kf=0;
        int sea_level=0;
        int grnd_level=0;
        
        java.util.Date date1 = null;
        
        String date = null;
       
        String icon=null;
        String WeatherCondition=null;
        int id=0;
        
        List<Example> weatherList = new ArrayList<>();

        JSONObject root = null;
		try {
			root = new JSONObject(result);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        SimpleDateFormat dfoutput2 = new SimpleDateFormat("HH");
        SimpleDateFormat dfoutput1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dfinput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        JSONArray weatherObject = null;
		try {
			weatherObject = root.getJSONArray("list");
			
			for(int i = 0; i < weatherObject.length(); i++) {
              	
	            JSONObject arrayElement = weatherObject.getJSONObject(i);
	            
	            JSONObject main = arrayElement.getJSONObject("main");
	            temp = (int) main.getInt("temp");
	            pressure =  main.getInt("pressure");
	            humidity = main.getInt("humidity");
	            temp_min = main.getInt("temp_min");
	            temp_max = main.getInt("temp_max");
	            temp_kf = main.getInt("temp_kf");
	            sea_level = main.getInt("sea_level");
	            grnd_level = main.getInt("grnd_level");
	            
	            description = arrayElement.getJSONArray("weather").getJSONObject(0).getString("description");
	            icon = arrayElement.getJSONArray("weather").getJSONObject(0).getString("icon");
	            WeatherCondition = arrayElement.getJSONArray("weather").getJSONObject(0).getString("main");
	            id = arrayElement.getJSONArray("weather").getJSONObject(0).getInt("id");
	                     
	                   date = arrayElement.getString("dt_txt");
	            	
	                   date1 = dfinput.parse(date);
	                  
	                   
	                 ForecastWeather fw=new ForecastWeather();
	                 Date dt=new Date();
	                 Main mn=new Main();
	                 Example e = new Example();
	                
	                 Time t = new Time();
	                 Weather w = new Weather();
	                 
	           
	                   mn.setTemp(temp);
	                   mn.setPressure((int) pressure/10);
	                   mn.setHumidity(humidity);
	                   mn.setGrndLevel(grnd_level);
	                   mn.setSeaLevel(sea_level);
	                   mn.setTempKf(temp_kf);
	                 
	                   mn.setTempMax(temp_max);
	                   mn.setTempMin(temp_min);
	                 
	                   w.setDescription(description);
	                   w.setIcon(icon);
	                   w.setId(id);
	                   w.setMain(WeatherCondition);
	                 
	                   t.setTime(dfoutput2.format(date1));
	                   t.setMain(mn);
	                   t.setWeather(w);
	                                                                                        
	                   dt.setAdditionalProperty(dfoutput2.format(date1), t);
	                   
	                   fw.setAdditionalProperty(dfoutput1.format(date1), dt);
	                  							                                				                                
	                   e.setForecastWeather(fw);
	                   
	                weatherList.add(e);              
	           }
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        
    
        return weatherList;
    }

    
    public List<Example> getWeather(String city) {
   
    	String websiteResponse = openweathermapwpath+"?q="+ city + "&mode=json&appid="+openweathermapapikey+"&units=metric";
    	
    	RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(websiteResponse, String.class);	 
    	
        String description = null;
        String WeatherCondition = null;
        double temp;
        double temp_min;
        double temp_max;
        double pressure;
        int humidity;
       
        
        List<Example> weatherList = new ArrayList<>();
              
        JSONObject root;
		try {
			root = new JSONObject(result);
			
			JSONArray weatherObject = root.getJSONArray("weather");

	        for (int i = 0; i < weatherObject.length(); i++) {
	            JSONObject elementInArray = weatherObject.getJSONObject(i);
	            description = elementInArray.getString("description");
	            WeatherCondition = elementInArray.getString("main");
	        }

	        JSONObject main = root.getJSONObject("main");
	       
	        temp = (int) main.getInt("temp");
	        pressure = main.getInt("pressure");
	        humidity = main.getInt("humidity");
	        temp_min= (int) main.getInt("temp_min");
	        temp_max= (int) main.getInt("temp_max");
	        
	        TodaysWeather tw=new TodaysWeather();
            Example e=new Example();
            Today t=new Today();
            
            t.setDescription(description);
            
            t.setHumidity(humidity);
            t.setMain(WeatherCondition);
            t.setPressure(pressure);
           
            t.setTemp(temp);
            t.setTempMax(temp_max);
            t.setTempMin(temp_min);
             
            tw.setToday(t);  
            e.setTodaysWeather(tw);
            weatherList.add(e);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return weatherList;
    }
     
    
    
    public List<WeatherPredicInfo> getDailyWeatherInfo(double longitude,double latitude) {
    	   
    	String websiteResponse = openweathermapopath+"?lat="+latitude+"&lon="+longitude+"&exclude=minutely,current,alerts,hourly&units=metric&appid="+openweathermapapikey;
    	List<WeatherPredicInfo> weatherList = new ArrayList<>();
    	
    	ResponseEntity<OneWeather> responseEntity = 
				  restTemplate.exchange(
				    websiteResponse,
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<OneWeather>() {}
				  );
    	
				OneWeather oneweatherlist = responseEntity.getBody();	 
    				
					List<Daily> dailyweatherlsist = oneweatherlist.getDaily();
					
					for (Iterator iterator2 = dailyweatherlsist.iterator(); iterator2.hasNext();) {
						Daily daily = (Daily) iterator2.next();
						
						WeatherPredicInfo weatherpredinfo = new WeatherPredicInfo();
						ForecastWeatherPredicInfo forecastweater = new ForecastWeatherPredicInfo();
						
						long unixSeconds = daily.getDt();
						java.util.Date date = new java.util.Date(unixSeconds*1000L); 
						SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
						
						sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4")); 
						String formattedDate = sdf.format(date);
						//System.out.println(formattedDate);
						
						forecastweater.setdate(formattedDate);
						
						forecastweater.setWindspeed(daily.getWindSpeed());
						
						Temp dailytemplist =  daily.getTemp();
						forecastweater.setMaxtemp(dailytemplist.getMax());
						forecastweater.setMintemp(dailytemplist.getMin());
						
						List<com.example.demo.sharedmodels.daily.Weather> weatherurrent = daily.getWeather();
						
						for (Iterator iterator = weatherurrent.iterator(); iterator.hasNext();) {
							com.example.demo.sharedmodels.daily.Weather weather = (com.example.demo.sharedmodels.daily.Weather) iterator.next();
							
							forecastweater.setMain(weather.getMain());
							
						}
						
						weatherdroolservice.getWeatherDecision(forecastweater);
						
						weatherpredinfo.setForecastWeather(forecastweater);
						
						weatherList.add(weatherpredinfo);
						
					}
				
		
        
        return weatherList;
    
    }    
} 
