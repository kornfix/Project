package pl.university.project.populators.impl;

import pl.university.project.models.Campaign;
import pl.university.project.odata.CampaignData;
import pl.university.project.populators.Populator;

public class DefaultCampaignReversPopulator implements Populator<CampaignData, Campaign> {
    @Override
    public void populate(CampaignData source, Campaign target) {
        target.setId(source.getId());
        target.setCampaignEndDate(source.getCampaignEndDate());
        target.setCampaignStartDate(source.getCampaignStartDate());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setCreationTime(source.getCreationTime());
    }
}
