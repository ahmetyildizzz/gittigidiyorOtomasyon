package Pages;

import Contants.SearchPageContants;
import base.BasePage;
import base.BaseTest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class SearchPage extends BasePage implements SearchPageContants {
    String arama = "bilgisayar";
    static String firstPriceText;

    private Logger LOGGER = LogManager.getLogger(SearchPage.class);
    protected WebDriver driver = BaseTest.driver;
    private WebDriverWait wait = new WebDriverWait(driver, 15, 1000);

    public void AramaSenaryo() {
        waitForElementAndSendKeys(aramaAlani, arama);
        LOGGER.info("Arama alanına bılgısayar yazıldı.");
        driver.findElement(By.xpath("//input[@placeholder='Keşfetmeye Bak']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//ul[@class='clearfix']/li/a[.='2']")).sendKeys(Keys.DOWN);
        waitForElementAndClick(twoPage);
        Assert.assertTrue("2.sayfada olduğunuz doğrulanamadı.", isElementVisible(secondPageActiveControl, 10));
        LOGGER.info("2.sayfaya geçıldı");
        chooseRandomProduct();
        LOGGER.info("Arama alanına bılgısayar yazıldı.");
        javaScriptClicker(sepetEkleButton);
        goToBasketAndControlPrice();
        waitSeconds(1);
        waitForElementAndClick(urunAlani);
        waitSeconds(1);
        waitForElementAndClick(urunAlanSec);
        LOGGER.info("Ürün arttırma gerçekleştırıldı.");
        waitForElementAndClick(urunSil);
        LOGGER.info("Sepettekı ürün temızlendı.");
        waitSeconds(3);

        Assert.assertTrue("Ürün sepeti boş değil.", isElementVisible(sepetControl, 5));
        LOGGER.info("Sepetın boş olduğu doğrulandı.");

    }

    public void goToBasketAndControlPrice() {


        javaScriptClicker(sepetimButton);
        WebElement secondByPrice = driver.findElement(By.xpath("//div[@class='new-price-box']//p"));
        String secondPriceText = secondByPrice.getText();
        LOGGER.info("Sepettekı ürün fıyatı alındı.");
        System.out.println("Second price :" + secondPriceText);
        Assert.assertEquals("Ürün karşıltırlması yapılmadı", firstPriceText, secondPriceText);
        LOGGER.info("Ürün ve sepettekı fıyat eşıt olduğu doğrulandı");
    }

    public void chooseRandomProduct() {


        Random random = new Random();
        List<WebElement> imgSrcList = driver.findElements(By.xpath("//ul[@class='catalog-view clearfix products-container']//p/img"));
        int randomImg = random.nextInt(imgSrcList.size());
        By by = By.xpath("(//a[@class='product-link']//p/img)[" + randomImg + "]");
        System.out.println("Random boutique by : " + by);
        System.out.println("Random boutique by : " + by);
        System.out.println("Random image number : " + randomImg);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        waitSeconds(3);
        driver.findElement(by).click();
        LOGGER.info("Rastgele bır ürün seçıldı.");
        WebElement byPrice = driver.findElement(By.xpath("//div[@id='sp-price-container']//div[2]"));
        firstPriceText = byPrice.getText();
        LOGGER.info("Ürün sayfasından fıyat alındı");
        System.out.println("First price :" + firstPriceText);

    }

}
