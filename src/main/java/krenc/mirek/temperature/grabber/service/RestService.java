package krenc.mirek.temperature.grabber.service;

import krenc.mirek.temperature.grabber.service.json.Main;
import krenc.mirek.temperature.grabber.service.json.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private Logger logger = LoggerFactory.getLogger(RestService.class);
    private RestTemplate restTemplate;
    private final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl&APPID=fac4034bf4e3aaf3be1a2382e2e9572b";

    public RestService() {
        this.restTemplate = new RestTemplate();
    }

    public Double getWeatherData()
    {
        WeatherData weatherData = restTemplate.getForObject(URL, WeatherData.class);

        if (weatherData != null)
        {
            Main main = weatherData.getMain();
            Double temperature = main.getTemp();
            logger.info("Temperatura = " + temperature);
            return temperature;
        }
        else
        {
            logger.error("Can not get temperature from weather API");
            return Double.MIN_VALUE;
        }
    }
}
