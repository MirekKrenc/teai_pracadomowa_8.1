package krenc.mirek.temperature.grabber.tools;

import krenc.mirek.temperature.grabber.model.TemperatureCollection;
import krenc.mirek.temperature.grabber.repository.TemperatureDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/*
klasa do generacji dummy danych na potzreby testów rysowania wykersu
 */
@Component
public class ChartDummyDataGenerator implements TemperatureDAO {

    private final Double TEMP_RANGE_MIN = -20.0;
    private final Double TEMP_RANGE_MAX = 35.0;
    private final int PROBE_COUNT = 100;

    private Double getRandowDoubleValue()
    {
        Random r = new Random();
        double randomValue = TEMP_RANGE_MIN + (TEMP_RANGE_MAX - TEMP_RANGE_MIN) * r.nextDouble();
        return Double.valueOf(randomValue);
    }

    private LocalDateTime getTiemstamp(long hours)
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.minusHours(hours);
        return localDateTime;
    }

    @Override
    public List<TemperatureCollection> getChartTemperatureData()     {
        List<TemperatureCollection> list = new ArrayList<>();
        for (int i=PROBE_COUNT; i>0; i--)
        {
            list.add(new TemperatureCollection(getRandowDoubleValue(), getTiemstamp(i)));
        }
        return list;
    }
}
