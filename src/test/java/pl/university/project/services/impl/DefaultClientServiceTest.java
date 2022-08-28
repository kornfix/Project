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
import pl.university.project.utils.PropertyUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        Collection<ClientData> result =  defaultClientService.getAllObjects();

        assertNotNull(result);
        assertEquals(1,result.size());
//        assertEquals(clientData,result.iterator.ne(0));
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


    @Test
    void aaa() throws ParseException {
        String test = "00d 00m 20s";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd'd' mm'm' ss's'");
        Date reference = simpleDateFormat.parse("00d 00m 00s");
        Date date = simpleDateFormat.parse(test);
        long seconds = (date.getTime() - reference.getTime()) / 1000L;

//        PeriodFormatter formatter = new PeriodFormatterBuilder()
//                .appendDays().appendSuffix("d").appendSeparatorIfFieldsAfter(" ")
//                .appendHours().appendSuffix("h").appendSeparatorIfFieldsAfter(" ")
//                .appendMinutes().appendSuffix("m").appendSeparatorIfFieldsAfter(" ")
//                .appendMinutes().appendSuffix("s")
//                .toFormatter();
//
//        Period p = formatter.parsePeriod("2d 5h 30s");
//        p.getSeconds();

        ;
        Duration d = Duration.ofSeconds(PropertyUtil.parsePeriod("30m 30s"));
        TimeUnit.SECONDS.toHours(PropertyUtil.parsePeriod("30m 30s"));

//        String timeInHHMMSS = DurationFormatUtils.formatDuration(*1000, "HH'h' MM'm' ss's'", true);
    }
}