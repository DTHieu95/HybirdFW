package pageObject.bankGuru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.bankGuru.dashboardPageUI;

public class customizeStatementPO extends BasePage {
    WebDriver driver;

    public customizeStatementPO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isMsgWelcomeDisplayed(String msg){
        waitElementVisible(driver , dashboardPageUI.WELCOME_MSG , msg);
        return isElementDisplayed(driver , dashboardPageUI.WELCOME_MSG , msg);
    }



}
