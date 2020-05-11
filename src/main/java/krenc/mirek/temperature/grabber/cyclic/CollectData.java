package krenc.mirek.temperature.grabber.cyclic;

import krenc.mirek.temperature.grabber.model.TemperatureCollection;
import krenc.mirek.temperature.grabber.repository.TemperatureJpaRepository;
import krenc.mirek.temperature.grabber.service.RestService;
import krenc.mirek.temperature.grabber.tools.TemperatureConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class CollectData {

    private Logger logger = LoggerFactory.getLogger(CollectData.class);
    private RestService restService;
    private TemperatureJpaRepository temperatureJpaRepository;

    public CollectData(RestService restService, TemperatureJpaRepository temperatureJpaRepository) {
        this.restService = restService;
        this.temperatureJpaRepository = temperatureJpaRepository;
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void getAndSaveTemperature()
    {
        Double temperatureKelvn = restService.getWeatherData();
        if (temperatureKelvn != Double.MIN_VALUE) {
            LocalDateTime localDateTime = LocalDateTime.now();
            temperatureJpaRepository.save(new TemperatureCollection(TemperatureConverter.fromKelvinToCelsius(temperatureKelvn), localDateTime));
            logger.info("Cron executed at " + localDateTime);
        } else {
            logger.info("Error during invoke weather service");
        }

    }
}
