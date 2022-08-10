package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.university.project.models.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long>{
}
