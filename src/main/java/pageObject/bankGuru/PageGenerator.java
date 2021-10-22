package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

public class PageGenerator {

    private PageGenerator(){

    }

    public static loginPO getLoginPage(WebDriver driver){
        return new loginPO(driver);
    }

    public static emailPO getEmailPage(WebDriver driver){
        return new emailPO(driver);
    }

    public static adminInfoPO getAdminInfoPage(WebDriver driver){
        return new adminInfoPO(driver);
    }

    public static dashboardPO getDashboardPage(WebDriver driver){
        return new dashboardPO(driver);
    }

    public static newCustomerPO getNewCustomerPage(WebDriver driver){
        return new newCustomerPO(driver);
    }

    public static editCustomerPO getEditCustomerPage(WebDriver driver){
        return new editCustomerPO(driver);
    }

    public static deleteCustomerPO getDeleteCustomerPage(WebDriver driver){
        return new deleteCustomerPO(driver);
    }

    public static newAccountPO getNewAccountPage(WebDriver driver){
        return new newAccountPO(driver);
    }

    public static editAccountPO getEditAccountPage(WebDriver driver){
        return new editAccountPO(driver);
    }

    public static deleteAccountPO getDeleteAccountPage(WebDriver driver){
        return new deleteAccountPO(driver);
    }

    public static fundTransferPO getFundTransferPage(WebDriver driver){
        return new fundTransferPO(driver);
    }

    public static miniStatementPO getMiniStatementPage(WebDriver driver){
        return new miniStatementPO(driver);
    }

    public static customizeStatementPO getCustomizeStatementPage(WebDriver driver){
        return new customizeStatementPO(driver);
    }

    public static changePWPO getChangePWPage(WebDriver driver){
        return new changePWPO(driver);
    }
}
