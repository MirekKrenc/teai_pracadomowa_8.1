package krenc.mirek.temperature.grabber.repository;


import krenc.mirek.temperature.grabber.model.TemperatureCollection;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureDAO {

    public List<TemperatureCollection> getChartTemperatureData();

}
