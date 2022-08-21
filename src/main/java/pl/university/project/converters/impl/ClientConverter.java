package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClientConverter implements Converter<Client, ClientData> {

    private List<Populator> populators;

    @Override
    public ClientData convert(Client source) {
        ClientData target = new ClientData();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public ClientData convert(Client source, ClientData target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public Collection<ClientData> convertAll(Collection<Client> source) {
        Collection<ClientData> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    @Override
    public Collection<ClientData> convertAll(Collection<Client> source, Collection<ClientData> target) {
        if (CollectionUtils.isNotEmpty(source)) {
            target.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return target;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
