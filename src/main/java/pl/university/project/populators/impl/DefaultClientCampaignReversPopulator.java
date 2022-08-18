package pl.university.project.populators.impl;

import pl.university.project.converters.impl.CampaignReversConverter;
import pl.university.project.converters.impl.ClientReversConverter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.populators.Populator;

import javax.annotation.Resource;

public class DefaultClientCampaignReversPopulator implements Populator<ClientCampaignData, ClientCampaign> {

    @Resource
    private ClientReversConverter clientReversConverter;

    @Resource
    private CampaignReversConverter campaignReversConverter;

    @Override
    public void populate(ClientCampaignData source, ClientCampaign target) {
        target.setCampaignParticipantId(source.getClientCampaignId());
        if (source.getClient() != null) {
            target.setClient(clientReversConverter.convert(source.getClient()));
        }
        if (source.getCampaign() != null) {
            target.setCampaign(campaignReversConverter.convert(source.getCampaign()));
        }
        if (source.getOldCamping() != null) {
            target.setOldCamping(campaignReversConverter.convert(source.getOldCamping()));
        }
        target.setCommunicationForm(source.getCommunicationForm());
        target.setCallDurationInSeconds(source.getCallDurationInSeconds());
        target.setLastContactDayOfYear(source.getLastContactDayOfYear());
        target.setNumberOfCalls(source.getNumberOfCalls());
        target.setNumberOfContacts(source.getNumberOfContacts());
    }
}
