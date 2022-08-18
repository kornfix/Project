package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.ClientCampaignId;

@Repository
public interface ClientCampaignRepository extends JpaRepository<ClientCampaign, ClientCampaignId> {
}
