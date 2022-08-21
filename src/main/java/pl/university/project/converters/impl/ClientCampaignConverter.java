package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClientCampaignConverter implements Converter<ClientCampaign, ClientCampaignData> {

    private List<Populator> populators;

    @Override
    public ClientCampaignData convert(ClientCampaign source) {
        ClientCampaignData target = new ClientCampaignData();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public ClientCampaignData convert(ClientCampaign source, ClientCampaignData target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public Collection<ClientCampaignData> convertAll(Collection<ClientCampaign> source) {
        Collection<ClientCampaignData> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    @Override
    public Collection<ClientCampaignData> convertAll(Collection<ClientCampaign> source, Collection<ClientCampaignData> target) {
        if (CollectionUtils.isNotEmpty(source)) {
            target.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return target;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
