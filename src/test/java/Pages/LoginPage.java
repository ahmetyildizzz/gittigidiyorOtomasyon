package Pages;

import Contants.LoginPageContants;
import Contants.SearchPageContants;
import base.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;


public class LoginPage extends BasePage implements LoginPageContants {

    private Logger LOGGER = LogManager.getLogger(LoginPage.class);
    String email="ahmetyildiz3406@gmail.com";
    String sifre="AhMeT3406*";

    public void GirisSenaryo(){

        waitForElementAndClick(login);
        waitForElementAndClick(loginbutton);
        waitForElementAndSendKeys(emailAlani,email);
        waitForElementAndSendKeys(sifreAlani,sifre);
        waitForElementAndClick(girisYap);
        Assert.assertTrue("Login islemi başarısız.",isElementVisible(submitKontrol,10));
        LOGGER.info("Basarılı giris yapıldı.");
    }

}
