package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementInfo;

import java.util.List;

public class BasePage {
    protected WebDriver driver = BaseTest.driver;
    private WebDriverWait wait = new WebDriverWait(driver, 15, 1000);
    protected Actions action = new Actions(driver);
    private Logger LOGGER = Logger.getLogger(BasePage.class);

    protected WebElement waitForElement(ElementInfo info) {
      isElementVisible(info,10);
      isElementClickable(info,10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(info.getBy()));
    }

    protected void waitForElementAndClick(ElementInfo elementInfo) {

        waitForElement(elementInfo).click();
    }

    protected void waitForElementAndSendKeys(ElementInfo elementInfo, String text) {
        waitForElement(elementInfo).sendKeys(text);
    }
    protected void waitSeconds(int seconds) {
        try {
            LOGGER.info(seconds + " saniye kadar bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }
    }
    protected void waitVisibilityOfElementLocatedBy(By by) {
        try {
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(by));
        }catch (Exception e ){

        }
    }
    protected void waitClickableOfElementLocatedBy(By by) {
        try {
            wait.until(ExpectedConditions
                    .elementToBeClickable(by));
        }catch (Exception e ){
          //  this.logger.error( by + "---> clickableOfElementLocated error. " + e.getStackTrace().toString());
        }
    }
    public boolean isElementClickable(ElementInfo elementInfo, int timeout){

        try{
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            waitClickableOfElementLocatedBy(elementInfo.getBy());
            return true;
        }catch (Exception e){
           // logger.info(key +" not visible");
            return false;
        }
    }
    public boolean isElementVisible(ElementInfo elementInfo, int timeout){

        try{
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            waitVisibilityOfElementLocatedBy(elementInfo.getBy());
            return true;
        }catch (Exception e){
          //  logger.info(key +" not visible");
            return false;
        }
    }

    public void javaScriptClicker(ElementInfo elementInfo){
       isElementClickable(elementInfo,10);
       isElementVisible(elementInfo,10);
        WebElement element = driver.findElement(elementInfo.getBy());
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}

