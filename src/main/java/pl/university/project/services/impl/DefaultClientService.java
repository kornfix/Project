package pl.university.project.services.impl;

import org.springframework.stereotype.Service;
import pl.university.project.converters.impl.CampaignForecastConverter;
import pl.university.project.converters.impl.ClientConverter;
import pl.university.project.converters.impl.ClientReversConverter;
import pl.university.project.models.Client;
import pl.university.project.odata.CampaignData;
import pl.university.project.odata.ClientData;
import pl.university.project.repositories.ClientRepository;
import pl.university.project.services.DefaultService;
import pl.university.project.utils.PropertyUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service("clientService")
public class DefaultClientService implements DefaultService<ClientData, Long> {

    @Resource
    private ClientReversConverter clientReversConverter;

    @Resource
    private ClientConverter clientConverter;

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private CampaignForecastConverter campaignForecastConverter;

    @Override
    public Collection<ClientData> getAllObjects() {
        return clientConverter.convertAll(clientRepository.findAll());
    }

    @Override
    public ClientData getObjectById(Long id) {
        Client client = getClientById(id);
        if (client == null) {
            return null;
        }
        return clientConverter.convert(client);
    }

    @Override
    public Long saveObject(ClientData clientData) {
        if (clientData == null) {
            return null;
        }
        Client client = clientRepository.saveAndFlush(clientReversConverter.convert(clientData));
        return client.getId();
    }

    @Override
    public Long updateObject(ClientData clientData) {
        Client client = getClientById(clientData.getId());
        if (client == null) {
            return null;
        }
        clientReversConverter.convert(clientData, client);
        clientRepository.saveAndFlush(client);
        return client.getId();
    }

    public Collection<ClientData> filterOutClientsByIds(Collection<Long> campaignsClientIds) {
        return getAllObjects().stream()
                .filter(PropertyUtil::validateClient)
                .filter(clientData -> PropertyUtil.clientNotWithIDs(clientData, campaignsClientIds))
                .collect(Collectors.toList());
    }

    public Integer countAvailableClientsForCampaign(Collection<Long> campaignsClientIds) {
        return filterOutClientsByIds(campaignsClientIds).size();
    }

    public boolean hasAnyAvailableClientsForCampaign(Collection<Long> campaignsClientIds) {
        return countAvailableClientsForCampaign(campaignsClientIds)>0;
    }

    public Collection<CampaignData> getCampaignIdForClientId(Long id) {
        Client client = getClientById(id);
        if (client == null) {
            return Collections.emptyList();
        }
        return campaignForecastConverter.convertAll(new ArrayList<>(client.getClientCampaigns()));
    }

    @Override
    public void deleteObject(Long id) {
        clientRepository.deleteById(id);
    }

    private Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}