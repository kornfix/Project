package pl.university.project.services.impl;

import pl.university.project.converters.impl.CampaignConverter;
import pl.university.project.converters.impl.CampaignReversConverter;
import pl.university.project.models.Campaign;
import pl.university.project.odata.CampaignData;
import pl.university.project.repositories.CampaignRepository;
import pl.university.project.services.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service("campaignService")
public class DefaultCampaignService implements Service<CampaignData,Long> {

    @Resource
    private CampaignReversConverter campaignReversConverter;

    @Resource
    private CampaignConverter campaignConverter;

    @Resource
    private CampaignRepository campaignRepository;

    @Override
    public Collection<CampaignData> getAllObjects() {
        return campaignConverter.convertAll(campaignRepository.findAll());
    }

    @Override
    public CampaignData getObjectById(Long id) {
        Campaign campaign = getCampaignById(id);
        if (campaign == null) {
            return null;
        }
        return campaignConverter.convert(campaign);
    }

    @Override
    public Long saveObject(CampaignData campaignData) {
        if (campaignData == null) {
            return null;
        }
        Campaign campaign = campaignRepository.saveAndFlush(campaignReversConverter.convert(campaignData));
        return campaign.getId();
    }


    public Collection<Long> getClientIsForCampaignId(Long id) {
        Campaign campaign = getCampaignById(id);
        if (campaign == null) {
            return Collections.emptyList();
        }
        return campaign.getClientCampaigns().stream()
                .map(clientCampaign -> clientCampaign.getClient().getId()).collect(Collectors.toList());
    }


    @Override
    public Long updateObject(CampaignData campaignData) {
        Campaign campaign = getCampaignById(campaignData.getId());
        if (campaign == null) {
            return null;
        }
        campaignReversConverter.convert(campaignData, campaign);
        campaignRepository.saveAndFlush(campaign);
        return campaign.getId();
    }

    @Override
    public void deleteObject(Long id) {
        campaignRepository.deleteById(id);
    }

    private Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id).orElse(null);
    }

}