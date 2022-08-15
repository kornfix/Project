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
        List<ClientData> result =  defaultClientService.getAllObjects();

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals(clientData,result.get(0));
    }

    @Test
    void testGetClientById() {
        ClientData result = defaultClientService.getObjectById(1L);

        assertNotNull(result);
        assertEquals(clientData,result);
    }

    @Test
    void testSave() {
        Long result = defaultClientService.saveObject(clientData);

        assertNotNull(result);
        assertEquals(clientData.getId(),result);
    }
}