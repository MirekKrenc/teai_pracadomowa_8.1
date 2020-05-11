package krenc.mirek.temperature.grabber.model;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Entity
@Table(name ="temperature_collection")
public class TemperatureCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double temperature;
    private LocalDateTime timestamp;

    public TemperatureCollection() {
    }

    public TemperatureCollection(double temperature, LocalDateTime timestamp) {
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(temperature));
    }

    public String getTimestampAsString(){

        String ts = new StringBuilder().append(timestamp.getYear())
                .append(timestamp.getMonthValue() < 10 ? "0" + timestamp.getMonthValue() : timestamp.getMonthValue())
                .append(timestamp.getDayOfMonth() < 10 ? "0" + timestamp.getDayOfMonth() : timestamp.getDayOfMonth())
                .append(timestamp.getHour() < 10 ? "0" + timestamp.getHour() : timestamp.getHour())
                .append(timestamp.getMinute() <10 ? "0" + timestamp.getMinute() : timestamp.getMinute() )
                .toString();

        return ts;
    }

    public String getTimeAsString()
    {
        String hour = String.valueOf(timestamp.getHour());
        String minute = String.valueOf(timestamp.getMinute());
        StringBuilder time = new StringBuilder();
        time.append(hour).append(minute);
        return time.toString();
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TempeartureCollection{" +
                "temerature=" + temperature +
                ", timestamp=" + timestamp +
                '}';
    }
}
