package pl.university.project.populators.impl;

import pl.university.project.models.Campaign;
import pl.university.project.odata.CampaignData;
import pl.university.project.populators.Populator;

import java.sql.Timestamp;
import java.time.Instant;

public class CampaignReversPopulator implements Populator<CampaignData, Campaign> {
    @Override
    public void populate(CampaignData source, Campaign target) {
        target.setId(source.getId());
        if (source.getId() == null) {
            target.setCreationTime(Timestamp.from(Instant.now()));
        }
        target.setCampaignEndDate(source.getCampaignEndDate());
        target.setCampaignStartDate(source.getCampaignStartDate());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
    }
}
