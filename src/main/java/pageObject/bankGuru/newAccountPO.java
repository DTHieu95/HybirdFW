package pageObject.bankGuru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class newAccountPO extends BasePage {
    WebDriver driver;

    public newAccountPO(WebDriver driver){
        this.driver = driver;
    }
}
