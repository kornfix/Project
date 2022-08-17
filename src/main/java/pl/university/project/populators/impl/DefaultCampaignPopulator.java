package pl.university.project.populators.impl;

import pl.university.project.models.Campaign;
import pl.university.project.odata.CampaignData;
import pl.university.project.populators.Populator;

public class DefaultCampaignPopulator implements Populator<Campaign, CampaignData> {
    @Override
    public void populate(Campaign source, CampaignData target) {
        target.setId(source.getId());
        target.setCampaignEndDate(source.getCampaignEndDate());
        target.setCampaignStartDate(source.getCampaignStartDate());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setCreationTime(source.getCreationTime());
    }
}
