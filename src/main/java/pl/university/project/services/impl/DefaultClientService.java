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
public class DefaultClientService implements DefaultService<ClientData,Long> {

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
        Client client = getClientById(id);
        if (client == null) {
            return null;
        }
        return clientConverter.convert(getClientById(id));
    }

    @Override
    public Long saveObject(ClientData clientData) {
        if (clientData == null) {
            return null;
        }
        Client client = clientRepository.saveAndFlush(clientReversConverter.convert(clientData));
        return client.getId();
    }

    @Override
    public Long updateObject(ClientData clientData) {
        Client client = getClientById(clientData.getId());
        if (client == null) {
            return null;
        }
        clientReversConverter.convert(clientData, client);
        clientRepository.saveAndFlush(client);
        return client.getId();
    }

    @Override
    public void deleteObject(Long id) {
        clientRepository.deleteById(id);
    }

    private Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}