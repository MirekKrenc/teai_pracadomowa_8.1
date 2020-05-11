package krenc.mirek.temperature.grabber.repository;

import krenc.mirek.temperature.grabber.model.TemperatureCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureJpaRepository extends JpaRepository<TemperatureCollection, Long> {
}
