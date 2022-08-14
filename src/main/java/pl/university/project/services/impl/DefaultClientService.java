package pl.university.project.services.impl;

import org.springframework.stereotype.Service;
import pl.university.project.converters.impl.ClientConverter;
import pl.university.project.converters.impl.ClientReversConverter;
import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.repositories.ClientRepository;
import pl.university.project.services.DefaultService;

import javax.annotation.Resource;
import java.util.List;

@Service("clientService")
public class DefaultClientService implements DefaultService<ClientData> {

    @Resource
    private ClientReversConverter clientReversConverter;

    @Resource
    private ClientConverter clientConverter;

    @Resource
    private ClientRepository clientRepository;

    @Override
    public List<ClientData> getAllObjects() {
        return clientConverter.convertAll(clientRepository.findAll());
    }

    @Override
    public ClientData getObjectById(Long id) {
        return clientConverter.convert(getClientById(id));
    }

    @Override
    public ClientData saveObject(ClientData clientData) {
        return clientConverter.convert(clientRepository.saveAndFlush(clientReversConverter.convert(clientData)));
    }

    @Override
    public ClientData updateObject(ClientData clientData) {
        Client client = getClientById(clientData.getId());
        if (client == null) {
            return null;
        }
        return clientConverter.convert(clientRepository.saveAndFlush(clientReversConverter.convert(clientData, client)));
    }

    @Override
    public void deleteObject(Long id) {
        Client client = getClientById(id);
        if (client != null) {
            clientRepository.delete(client);
        }
    }

    private Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}