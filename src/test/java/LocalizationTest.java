import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LocalizationTest {

    private String russiaLoc = "RUSSIA";
    private String otherLoc = "BRAZIL";

    @Mock
    LocalizationServiceImpl localizationTest;

    @Test
    public void checkRusLocale() {
        Mockito.when(localizationTest.locale(Country.valueOf(russiaLoc))).thenReturn("Добро пожаловать");

        String actual = localizationTest.locale(Country.valueOf(russiaLoc));
        String expected = "Добро пожаловать";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkOtherLocale() {
        Mockito.when(localizationTest.locale(Country.valueOf(otherLoc))).thenReturn("Welcome");

        String actual = localizationTest.locale(Country.valueOf(otherLoc));
        String expected = "Welcome";

        Assert.assertEquals(expected, actual);
    }
}
