package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.populators.Populator;
import pl.university.project.populators.impl.DefaultClientPopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientConverter implements Converter<Client, ClientData> {

    //    @Resource
    private DefaultClientPopulator defaultClientPopulator = new DefaultClientPopulator();

    //    @Qualifier("userPopulators")
    private List<Populator> clientPopulators = List.of(defaultClientPopulator);

    @Override
    public ClientData convert(Client source) {
        ClientData target = new ClientData();
        if (source != null) {
            clientPopulators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public List<ClientData> convertAll(List<Client> source) {
        List<ClientData> clientDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            clientDataList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return clientDataList;
    }
}
