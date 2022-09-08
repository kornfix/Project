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
    public void convert(ClientCampaignData source, ClientCampaign target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
    }

    @Override
    public Collection<ClientCampaign> convertAll(Collection<ClientCampaignData> source) {
        Collection<ClientCampaign> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
