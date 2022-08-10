package pl.university.project.services.impl;

import pl.university.project.converters.impl.ClientConverter;
import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.repositories.ClientRepository;

import javax.annotation.Resource;
import java.util.List;

public class DefaultClientService {

    @Resource
    private ClientConverter clientConverter;

    @Resource
    private ClientRepository clientRepository;

    public List<ClientData> getAllClients() {
        return clientConverter.convertAll(clientRepository.findAll());
    }


    public ClientData getClientById(String id) {
        Client client = new Client();
        Long userId = Long.valueOf(id);
        return clientConverter.convert(clientRepository.findById(userId).orElse(null));
    }

    public ClientData save(Client user) {
        clientRepository.save(user);
        return clientConverter.convert(user);
    }
}
