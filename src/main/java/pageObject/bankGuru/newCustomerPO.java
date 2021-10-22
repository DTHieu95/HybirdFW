package pageObject.bankGuru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.bankGuru.adminInfoPageUI;

public class newCustomerPO extends BasePage {
    WebDriver driver;

    public newCustomerPO(WebDriver driver){
        this.driver = driver;
    }

    public String getUserID(){
        waitElementVisible(driver , adminInfoPageUI.USER_ID);
        return getElementText(driver , adminInfoPageUI.USER_ID);
    }

    public String getPassword(){
        waitElementVisible(driver , adminInfoPageUI.PASS);
        return getElementText(driver , adminInfoPageUI.PASS);
    }


}
