package pl.university.project.populators.impl;

import pl.university.project.converters.impl.CampaignConverter;
import pl.university.project.converters.impl.ClientConverter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.populators.Populator;

import javax.annotation.Resource;

public class DefaultClientCampaignPopulator implements Populator<ClientCampaign, ClientCampaignData> {

    @Resource
    private ClientConverter clientConverter;

    @Resource
    private CampaignConverter campaignConverter;

    @Override
    public void populate(ClientCampaign source, ClientCampaignData target) {
        target.setClientCampaignId(source.getCampaignParticipantId());
        if (source.getClient() != null) {
            target.setClient(clientConverter.convert(source.getClient()));
        }
        if (source.getCampaign() != null) {
            target.setCampaign(campaignConverter.convert(source.getCampaign()));
        }
        if (source.getOldCamping() != null) {
            target.setOldCamping(campaignConverter.convert(source.getOldCamping()));
        }
        target.setCommunicationForm(source.getCommunicationForm());
        target.setCallDurationInSeconds(source.getCallDurationInSeconds());
        target.setLastContactDayOfYear(source.getLastContactDayOfYear());
        target.setNumberOfCalls(source.getNumberOfCalls());
        target.setNumberOfContacts(source.getNumberOfContacts());
    }
}