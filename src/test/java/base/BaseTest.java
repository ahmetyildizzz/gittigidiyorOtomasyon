package base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public Actions action;
    private Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");

        driver = new ChromeDriver(options);
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie cookie : allCookies) {
            driver.manage().deleteCookieNamed(cookie.getName());
        }
        driver.get("https://www.gittigidiyor.com/");
        wait = new WebDriverWait(driver, 15, 1000);
        action = new Actions(driver);

    }

    @After
    public void afterTest()  {

        driver.quit();
    }
}