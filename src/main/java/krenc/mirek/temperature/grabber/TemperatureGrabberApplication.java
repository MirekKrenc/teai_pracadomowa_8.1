package krenc.mirek.temperature.grabber;

import krenc.mirek.temperature.grabber.model.TemperatureCollection;
import krenc.mirek.temperature.grabber.repository.TemperatureCustomRepository;
import krenc.mirek.temperature.grabber.repository.TemperatureJpaRepository;
import krenc.mirek.temperature.grabber.service.RestService;
import krenc.mirek.temperature.grabber.tools.ChartDummyDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class TemperatureGrabberApplication {

    @Autowired
    private ChartDummyDataGenerator chartDummyDataGenerator;
    @Autowired
    private TemperatureCustomRepository temperatureCustomRepository;
    @Autowired
    private TemperatureJpaRepository temperatureJpaRepository;
    @Autowired
    private RestService restService;


    public static void main(String[] args) {
        SpringApplication.run(TemperatureGrabberApplication.class, args);

    }

    @Profile("dev")
    @Bean
    public void testData()
    {
        chartDummyDataGenerator.getChartTemperatureData().stream()
                .forEach(t -> temperatureJpaRepository.save(t));

        List<TemperatureCollection> temperatureCollection = new ArrayList<>();
        temperatureCollection = temperatureCustomRepository.getChartTemperatureData();

        temperatureCollection.stream()
                .sorted(Comparator.comparing(TemperatureCollection::getTimestamp))
                .forEach(System.out::println);

        restService.getWeatherData();
    }

}
