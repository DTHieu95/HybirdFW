package pageObject.bankGuru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class emailPO extends BasePage {
    WebDriver driver;

    public emailPO(WebDriver driver){
        this.driver = driver;
    }
}
