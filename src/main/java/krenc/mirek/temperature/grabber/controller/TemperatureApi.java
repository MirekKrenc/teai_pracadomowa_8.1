package krenc.mirek.temperature.grabber.controller;

import krenc.mirek.temperature.grabber.model.TemperatureCollection;
import krenc.mirek.temperature.grabber.repository.TemperatureCustomRepository;
import krenc.mirek.temperature.grabber.repository.TemperatureDAO;
import krenc.mirek.temperature.grabber.tools.ChartDummyDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
public class TemperatureApi {

    private TemperatureDAO temperatureDAO;
    private EntityManager entityManager;

    @Autowired
    public TemperatureApi(EntityManager entityManager) {
        this.temperatureDAO = new TemperatureCustomRepository(entityManager);
    }

    @GetMapping("/temperature")
    public String showTemperatureData(Model model)
    {
        List<TemperatureCollection> temperatureCollection = temperatureDAO.getChartTemperatureData();

        Map<String, Double> sortedMap = new TreeMap<>();
        for (TemperatureCollection tc: temperatureCollection)
        {
            sortedMap.put(tc.getTimestampAsString(), tc.getTemperature());
        }

        model.addAttribute("temperatureMap", sortedMap);
        model.addAttribute("temperatureCollection", temperatureCollection);
        return "temperature";
    }
}
