package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.university.project.models.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
}
