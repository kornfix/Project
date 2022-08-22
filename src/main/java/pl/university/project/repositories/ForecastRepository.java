package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.university.project.models.Forecast;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long>{
}
