import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MessageTest {

    private static final String IP_ADDRESS_HEADER = "x-real-ip";
    private String usaIp = "96.44.183.149";
    private String russiaIp = "172.00.29.255";

    @Mock
    GeoServiceImpl geoService;
    @Mock
    LocalizationServiceImpl localizationService;
    MessageSenderImpl messageSender;

    @Before
    public void setup() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void checkUsaIp() {
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, usaIp);

        Mockito.when(geoService.byIp(Mockito.eq(usaIp))).thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(geoService.byIp(usaIp).getCountry())).thenReturn("Welcome");

        String expected = "Welcome";
        String actual = messageSender.send(headers);
        Assert.assertEquals(expected, actual);
        System.out.println();
    }

    @Test
    public void checkRussiaIp() {
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, russiaIp);

        Mockito.when(geoService.byIp(Mockito.eq(russiaIp))).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(geoService.byIp(russiaIp).getCountry())).thenReturn("Добро пожаловать");

        String expected = "Добро пожаловать";
        String actual = messageSender.send(headers);
        Assert.assertEquals(expected, actual);
        System.out.println();
    }

}
