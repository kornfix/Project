package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientCampaignReversConverter implements Converter<ClientCampaignData, ClientCampaign> {

    private List<Populator> populators;

    @Override
    public ClientCampaign convert(ClientCampaignData source) {
        ClientCampaign target = new ClientCampaign();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public ClientCampaign convert(ClientCampaignData source, ClientCampaign target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public List<ClientCampaign> convertAll(List<ClientCampaignData> source) {
        List<ClientCampaign> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    @Override
    public List<ClientCampaign> convertAll(List<ClientCampaignData> source, List<ClientCampaign> target) {
        if (CollectionUtils.isNotEmpty(source)) {
            target.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return target;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
