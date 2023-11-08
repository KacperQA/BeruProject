package abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPO {

    private static final String HOME_PAGE_URL = "https://recruitment.gobasic.dk/test";
    public static final String ACCEPT_COOKIES_CLASS_NAME = "accept-cookies";
    private static final int MAXIMUM_WAIT_TIME_SECONDS = 5;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(MAXIMUM_WAIT_TIME_SECONDS));
    }

//    public void waitForElementToAppear(By findBy) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated((findBy)));
//    }

    public void navigateToHomePage() {
        driver.get(HOME_PAGE_URL);
    }

    public void acceptCookiesButton() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ACCEPT_COOKIES_CLASS_NAME)));
        driver.findElement(By.className(ACCEPT_COOKIES_CLASS_NAME)).click();
    }
}
