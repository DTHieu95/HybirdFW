package pageObject.bankGuru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.bankGuru.adminInfoPageUI;
import pageUIs.bankGuru.dashboardPageUI;

public class dashboardPO extends BasePage {
    WebDriver driver;

    public dashboardPO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isMsgWelcomeDisplayed(String msg){
        waitElementVisible(driver , dashboardPageUI.WELCOME_MSG , msg);
        return isElementDisplayed(driver , dashboardPageUI.WELCOME_MSG , msg);
    }



}
