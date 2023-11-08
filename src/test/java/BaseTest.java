import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeAll
    public static void init() {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void shutdown() {
        driver.quit();
    }
}
