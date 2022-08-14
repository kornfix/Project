package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientReversConverter implements Converter<ClientData, Client> {

    private List<Populator> populators;

    @Override
    public Client convert(ClientData source) {
        Client target = new Client();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public Client convert(ClientData source, Client target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public List<Client> convertAll(List<ClientData> source) {
        List<Client> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    @Override
    public List<Client> convertAll(List<ClientData> source, List<Client> target) {
        if (CollectionUtils.isNotEmpty(source)) {
            target.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return target;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
