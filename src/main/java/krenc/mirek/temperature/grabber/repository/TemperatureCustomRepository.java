package krenc.mirek.temperature.grabber.repository;

import krenc.mirek.temperature.grabber.model.TemperatureCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class TemperatureCustomRepository implements TemperatureDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TemperatureCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //get last 24 data for chart drawing
    @Override
    public List<TemperatureCollection> getChartTemperatureData()
    {
        return entityManager.createQuery("SELECT t from TemperatureCollection t ORDER BY t.timestamp desc", TemperatureCollection.class)
                .setMaxResults(24).getResultList();
    }


}
