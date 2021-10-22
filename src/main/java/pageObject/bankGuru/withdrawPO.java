package pageObject.bankGuru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.bankGuru.adminInfoPageUI;

public class withdrawPO extends BasePage {
    WebDriver driver;

    public withdrawPO(WebDriver driver){
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
