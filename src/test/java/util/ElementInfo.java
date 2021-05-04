package util;

import org.openqa.selenium.By;

public class ElementInfo {

    public String assertDesc;
    public By by;

    public ElementInfo(String aciklama, By by) {
        super();
        this.assertDesc = aciklama;
        this.by = by;
    }


    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }
}
