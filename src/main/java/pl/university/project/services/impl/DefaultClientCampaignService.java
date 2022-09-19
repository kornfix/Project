package pl.university.project.services.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.impl.ClientCampaignConverter;
import pl.university.project.converters.impl.ClientCampaignReversConverter;
import pl.university.project.converters.impl.ForecastConverter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.ClientCampaignId;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.odata.ForecastData;
import pl.university.project.repositories.ClientCampaignRepository;
import pl.university.project.services.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service("clientCampaignService")
public class DefaultClientCampaignService implements Service<ClientCampaignData, ClientCampaignId> {

    @Resource
    private ClientCampaignReversConverter clientCampaignReversConverter;

    @Resource
    private ClientCampaignConverter clientCampaignConverter;

    @Resource
    private ForecastConverter forecastConverter;

    @Resource
    private ClientCampaignRepository clientCampaignRepository;

    @Resource
    private DefaultForecastService forecastService;

    @Resource
    private EntityManager entityManager;

    @Override
    public Collection<ClientCampaignData> getAllObjects() {
        return clientCampaignConverter.convertAll(clientCampaignRepository.findAll());
    }

    public Collection<ClientCampaignData> getAllClientsCampaignsByCampaignID(Long campaignId) {
        return clientCampaignConverter.convertAll(clientCampaignRepository.findAll().stream().filter(clientCampaign ->
                clientCampaign.getClientCampaignId().getCampaignId().equals(campaignId)).collect(Collectors.toList()));
    }

    public Collection<Long> getAllParticipantsIDs() {
        return clientCampaignRepository.findAll().stream().map(e -> e.getClient().getId()).collect(Collectors.toList());
    }

    @Override
    public ClientCampaignData getObjectById(ClientCampaignId id) {
        ClientCampaign clientCampaign = getClientCampaignById(id);
        if (clientCampaign == null) {
            return null;
        }
        return clientCampaignConverter.convert(clientCampaign);
    }

    @Override
    public ClientCampaignId saveObject(ClientCampaignData clientCampaignData) {
        if (clientCampaignData == null) {
            return null;
        }
        ClientCampaign clientCampaign = clientCampaignRepository.saveAndFlush(clientCampaignReversConverter.
                convert(clientCampaignData));
        forecastService.createNewForecast(clientCampaign);
        return clientCampaign.getClientCampaignId();
    }


    public void createNewForecast(ClientCampaignId clientCampaignId) {
        ClientCampaign clientCampaign = getClientCampaignById(clientCampaignId);
        if (clientCampaign != null) {
            forecastService.createNewForecast(clientCampaign);
        }
    }

    @Override
    public ClientCampaignId updateObject(ClientCampaignData clientCampaignData) {
        ClientCampaign clientCampaign = clientCampaignRepository.findById(clientCampaignData.getClientCampaignId()).orElse(null);
        if (clientCampaign == null) {
            return null;
        }
        clientCampaignReversConverter.convert(clientCampaignData, clientCampaign);
        clientCampaignRepository.saveAndFlush(clientCampaign);
        forecastService.createNewForecast(clientCampaign);
        return clientCampaign.getClientCampaignId();
    }

    public ClientCampaignId changeNumberOfContactsDuringCampaign(ClientCampaignId clientCampaignID, Long valueToAppend) {
        ClientCampaign clientCampaign = getClientCampaignById(clientCampaignID);
        if (clientCampaign == null) {
            return null;
        }
        clientCampaign.setNumberOfContactsDuringCampaign(clientCampaign.getNumberOfContactsDuringCampaign() + valueToAppend);
        clientCampaignRepository.saveAndFlush(clientCampaign);
        return clientCampaign.getClientCampaignId();
    }

    public List<ForecastData> getAllForecasts(ClientCampaignId clientCampaignID) {
        ClientCampaign clientCampaign = getClientCampaignById(clientCampaignID);
        if (clientCampaign == null || CollectionUtils.isEmpty(clientCampaign.getForecasts())) {
            return Collections.emptyList();
        }
        return (List<ForecastData>) forecastConverter.convertAll(clientCampaign.getForecasts());
    }

    @Override
    public void deleteObject(ClientCampaignId id) {
        clientCampaignRepository.deleteById(id);
    }

    private ClientCampaign getClientCampaignById(ClientCampaignId id) {
        return clientCampaignRepository.findById(id).orElse(null);
    }

}