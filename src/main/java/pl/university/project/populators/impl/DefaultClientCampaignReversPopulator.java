package pl.university.project.populators.impl;

import pl.university.project.converters.impl.CampaignReversConverter;
import pl.university.project.converters.impl.ClientReversConverter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.populators.Populator;
import pl.university.project.utils.PropertyUtil;

import javax.annotation.Resource;

public class DefaultClientCampaignReversPopulator implements Populator<ClientCampaignData, ClientCampaign> {

    @Resource
    private ClientReversConverter clientReversConverter;

    @Resource
    private CampaignReversConverter campaignReversConverter;

    @Override
    public void populate(ClientCampaignData source, ClientCampaign target) {
        target.setClientCampaignId(source.getClientCampaignId());
        if (source.getClient() != null) {
            target.setClient(clientReversConverter.convert(source.getClient()));
        }
        if (source.getCampaign() != null) {
            target.setCampaign(campaignReversConverter.convert(source.getCampaign()));
        }
        if (source.getCampaignOutcome() != null) {
            target.setCampaignOutcome(source.getCampaignOutcome());
        }
        target.setCallDurationInSeconds(PropertyUtil.parsePeriod(source.getCallDurationInSeconds()));
        target.setLastContactDate(source.getLastContactDate());
        target.setNumberOfContactsDuringCampaign(source.getNumberOfContactsDuringCampaign());
    }
}
