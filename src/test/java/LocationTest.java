import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LocationTest {

    private String usaIp = "96.28.33.223";
    private String russiaIp = "172.39.33.222";

    @Mock
    GeoServiceImpl geoService;

    @Test
    public void checkUsaLocation() {
        Mockito.when(geoService.byIp(Mockito.eq(usaIp))).thenReturn(new Location("New York", Country.USA, null, 0));

        String actual = geoService.byIp(usaIp).getCity();
        String expected = "New York";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkRussiaLocation() {
        Mockito.when(geoService.byIp(Mockito.eq(russiaIp))).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        String actual = geoService.byIp(russiaIp).getCity();
        String expected = "Moscow";

        Assert.assertEquals(expected, actual);

    }

}
