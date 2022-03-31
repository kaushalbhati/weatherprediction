import axios from 'axios';

const weather_API_BASE_URL = "http://localhost:9090/oneweather/";

class weatherService {

    getWeatherByCity(cityname){
        
        const token = localStorage.getItem("token");  
        const curreurl = "http://localhost:9090/oneweather/"+cityname;
        //return axios.get(curreurl);
        
        return axios.get(curreurl,{
            headers: {
              'Authorization': `token ${token}`
            }
          });
          
    }

    createweather(weather){
        return axios.post(weather_API_BASE_URL, weather);
    }

    getweatherById(weatherId){
        return axios.get(weather_API_BASE_URL + '/' + weatherId);
    }

    updateweather(weather, weatherId){
        return axios.put(weather_API_BASE_URL + '/' + weatherId, weather);
    }

    deleteweather(weatherId){
        return axios.delete(weather_API_BASE_URL + '/' + weatherId);
    }
}

export default new weatherService()