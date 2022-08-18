package pl.university.project.services.impl;

import org.springframework.stereotype.Service;
import pl.university.project.converters.impl.ClientCampaignConverter;
import pl.university.project.converters.impl.ClientCampaignReversConverter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.ClientCampaignId;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.repositories.ClientCampaignRepository;
import pl.university.project.services.DefaultService;

import javax.annotation.Resource;
import java.util.List;

@Service("clientCampaignService")
public class DefaultClientCampaignService implements DefaultService<ClientCampaignData, ClientCampaignId> {

    @Resource
    private ClientCampaignReversConverter clientCampaignReversConverter;

    @Resource
    private ClientCampaignConverter clientCampaignConverter;

    @Resource
    private ClientCampaignRepository clientCampaignRepository;

    @Override
    public List<ClientCampaignData> getAllObjects() {
        return clientCampaignConverter.convertAll(clientCampaignRepository.findAll());
    }

    @Override
    public ClientCampaignData getObjectById(ClientCampaignId id) {
        ClientCampaign clientCampaign = getClientCampaignById(id);
        if (clientCampaign == null) {
            return null;
        }
        return clientCampaignConverter.convert(getClientCampaignById(id));
    }

    @Override
    public ClientCampaignId saveObject(ClientCampaignData clientCampaignData) {
        if (clientCampaignData == null) {
            return null;
        }
        ClientCampaign clientCampaign = clientCampaignRepository.saveAndFlush(clientCampaignReversConverter.
                convert(clientCampaignData));
        return clientCampaign.getCampaignParticipantId();
    }

    @Override
    public ClientCampaignId updateObject(ClientCampaignData clientCampaignData) {
        ClientCampaign clientCampaign = getClientCampaignById(clientCampaignData.getClientCampaignId());
        if (clientCampaign == null) {
            return null;
        }
        clientCampaignReversConverter.convert(clientCampaignData, clientCampaign);
        clientCampaignRepository.saveAndFlush(clientCampaign);
        return clientCampaign.getCampaignParticipantId();
    }

    @Override
    public void deleteObject(ClientCampaignId id) {
        clientCampaignRepository.deleteById(id);
    }

    private ClientCampaign getClientCampaignById(ClientCampaignId id) {
        return clientCampaignRepository.findById(id).orElse(null);
    }

}