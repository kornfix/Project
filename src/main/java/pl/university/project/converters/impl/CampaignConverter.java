package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.Campaign;
import pl.university.project.odata.CampaignData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignConverter implements Converter<Campaign, CampaignData> {

    private List<Populator> populators;

    @Override
    public CampaignData convert(Campaign source) {
        CampaignData target = new CampaignData();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public CampaignData convert(Campaign source, CampaignData target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public Collection<CampaignData> convertAll(Collection<Campaign> source) {
        Collection<CampaignData> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    @Override
    public Collection<CampaignData> convertAll(Collection<Campaign> source, Collection<CampaignData> target) {
        if (CollectionUtils.isNotEmpty(source)) {
            target.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return target;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
