package pl.university.project.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.university.project.converters.impl.ClientConverter;
import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.repositories.ClientRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class DefaultClientServiceTest {

    @InjectMocks
    private DefaultClientService defaultClientService;

    @Mock
    private ClientConverter clientConverter;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private Client client;

    @Mock
    private ClientData clientData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Mockito.when(clientConverter.convert(Mockito.any())).thenReturn(clientData);
        Mockito.when(clientConverter.convertAll(Mockito.any())).thenReturn(Collections.singletonList(clientData));
    }

    @Test
    void testGetAllClients() {
        List<ClientData> result =  defaultClientService.getAllClients();

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(clientData,result.get(0));
    }

    @Test
    void testGetClientById() {
        ClientData result = defaultClientService.getClientById("1");

        assertNotNull(result);
        assertEquals(clientData,result);
    }

    @Test
    void testSave() {
        ClientData result = defaultClientService.save(client);

        assertNotNull(result);
        assertEquals(clientData,result);
    }
}