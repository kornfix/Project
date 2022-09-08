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

public class CampaignReversConverter implements Converter<CampaignData, Campaign> {

    private List<Populator> populators;

    @Override
    public Campaign convert(CampaignData source) {
        Campaign target = new Campaign();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public void convert(CampaignData source, Campaign target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
    }

    @Override
    public Collection<Campaign> convertAll(Collection<CampaignData> source) {
        Collection<Campaign> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
