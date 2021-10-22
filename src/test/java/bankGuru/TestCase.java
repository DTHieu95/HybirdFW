package bankGuru;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.bankGuru.*;
import sun.jvm.hotspot.debugger.Page;

public class TestCase extends BaseTest {
    WebDriver driver;
    loginPO loginPage;
    emailPO emailPage;
    adminInfoPO adminInfoPage;
    dashboardPO dashboardPage;
    newCustomerPO newCustomerPage;
    editCustomerPO editCustomerPage;
    deleteCustomerPO deleteCustomerPage;
    newAccountPO newAccountPage;
    editAccountPO editAccountPage;
    deleteAccountPO deleteAccountPage;
    miniStatementPO miniStatementPage;
    customizeStatementPO customizeStatementPage;
    fundTransferPO fundTransferPage;
    changePWPO changePWPage;
    depositPO depositPage;
    withdrawPO withdrawPage;
    balancePO balancePage;
    String email , userID , pass , customerName , customerID , dob , addr , city , state , pin , mobile , mail , password , accountID ;
    String deposit , accountType , date;
    String addEdit , stateEdit , cityEdit , pinEdit ,mobileEdit , emailEdit;
    String customerName1 , customerID1 , dob1 , addr1 , city1 , state1 , pin1 , mobile1 , mail1 , password1 , accountID1 ;
    String depositNum , withdrawNum ,transferNum , numAfterDeposit , numAfterWithdraw , numAfterTransfer;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        // Payee
        customerName = "payee";
        dob = "10/06/1995";
        addr = "NTT";
        city = "HN";
        state = "VN";
        pin =  "123456";
        mobile = "0962509547";
        mail = "hoanp@gmail.com";
        password = "123456";
        deposit = "50000";
        accountType = "Savings";

        addEdit = "HD";
        stateEdit = "EN";
        cityEdit = "NY";
        pinEdit = "123987";
        mobileEdit = "1111111111";
        emailEdit = "hoanp95@gmail.com";

        //Payer
        customerName1 = "Payer";
        dob1 = "19/05/1995";
        addr1 = "Ha Dong";
        city1 = "Ha Noi";
        state1 = "VietNam";
        pin1 = "100000";
        mobile1 = "0968430665";
        mail1 = "payer@gmail.com";
        password1 = "100000";
        deposit = "50000";

        depositNum = "5000";
        withdrawNum = "15000";
        transferNum = "10000";

        numAfterDeposit = String.valueOf(Integer.valueOf(deposit) + Integer.valueOf(depositNum));
        numAfterWithdraw = String.valueOf(Integer.valueOf(numAfterDeposit) - Integer.valueOf(withdrawNum));
        numAfterTransfer = String.valueOf(Integer.valueOf(numAfterDeposit) - Integer.valueOf(transferNum));


    }

    @Test
    public void TC_01(){
        loginPage = PageGenerator.getLoginPage(driver);

        loginPage.clickToLink(driver , "here");

        emailPage = PageGenerator.getEmailPage(driver);

        emailPage.sendValueToField(driver , "emailid" , email);

        emailPage.clickToButton(driver ,"btnLogin");

        adminInfoPage = PageGenerator.getAdminInfoPage(driver);

        userID = adminInfoPage.getUserID();

        pass = adminInfoPage.getPassword();
    }

    @Test
    public void TC_02(){
        driver.get("http://demo.guru99.com/v4/");

        loginPage = PageGenerator.getLoginPage(driver);

        loginPage.sendValueToField(driver , "uid" , userID);

        loginPage.sendValueToField(driver , "password" , pass);

        loginPage.clickToButton(driver , "btnLogin");

        dashboardPage = PageGenerator.getDashboardPage(driver);

        verifyTrue(dashboardPage.isMsgWelcomeDisplayed("Welcome To Manager's Page of Guru99 Bank"));
    }

    @Test
    public void TC_03(){
        dashboardPage.clickToLink(driver , "New Customer");

        newCustomerPage = PageGenerator.getNewCustomerPage(driver);

        verifyTrue(newCustomerPage.isPageTitleCorrect(driver , "Add New Customer"));

        log.info("Leave field blank");
        newCustomerPage.sendValueToField(driver , "name" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver ,"message", "Customer name must not be blank"));

        log.info("Input field name is number");
        newCustomerPage.sendValueToField(driver , "name" , "123");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver ,"message", "Numbers are not allowed"));

        log.info("Input field name is special char");
        newCustomerPage.sendValueToField(driver , "name" , "aa@@");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver ,"message", "Special characters are not allowed"));

       log.info("Input field name is blank space");
        newCustomerPage.sendValueToField(driver , "name" , " aa");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver ,"message", "First character can not have space"));

    }

    @Test
    public void TC_04(){
        log.info("Leave address field blank");
        newCustomerPage.sendValueToField(driver , "addr" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver, "message3" , "Address Field must not be blank"));

        log.info("Input field add blank space");
        newCustomerPage.sendValueToField(driver , "addr" , " aa");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver, "message3" , "First character can not have space"));
    }

    @Test
    public void TC_05(){
        log.info("Leave city blank");
        newCustomerPage.sendValueToField(driver  ,"city" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver  ,"message4" , "City Field must not be blank"));

        log.info("Input city numeric");
        newCustomerPage.sendValueToField(driver  ,"city" , "123");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver  ,"message4" , "Numbers are not allowed"));

        log.info("Input city special char");
        newCustomerPage.sendValueToField(driver  ,"city" , "dd@@");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver  ,"message4" , "Special characters are not allowed"));

        log.info("Input city blank space");
        newCustomerPage.sendValueToField(driver  ,"city" , " fd");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver  ,"message4" , "First character can not have space"));
    }

    @Test
    public void TC_06(){
        log.info("Leave field state blank");
        newCustomerPage.sendValueToField(driver , "state" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message5" , "State must not be blank"));

        log.info("Input field state is number");
        newCustomerPage.sendValueToField(driver , "state" , "12");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message5" , "Numbers are not allowed"));

        log.info("Input field state is special char");
        newCustomerPage.sendValueToField(driver , "state" , "a@@");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message5" , "Special characters are not allowed"));

        log.info("Input field state has blank space");
        newCustomerPage.sendValueToField(driver , "state" , " a");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message5" , "First character can not have space"));
    }

    @Test
    public void TC_07(){
        log.info("Input PIN field is not number");
        newCustomerPage.sendValueToField(driver , "pinno" , "aaa");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message6" , "Characters are not allowed"));

        log.info("Leave PIN field blank");
        newCustomerPage.sendValueToField(driver , "pinno" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message6" , "PIN Code must not be blank"));

        log.info("Input PIN field 1 number");
        newCustomerPage.sendValueToField(driver , "pinno" , "1");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message6" , "PIN Code must have 6 Digits"));

        log.info("Input PIN field is special char");
        newCustomerPage.sendValueToField(driver , "pinno" , "12@@");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message6" , "Special characters are not allowed"));

        log.info("Input PIN field has space in the first");
        newCustomerPage.sendValueToField(driver , "pinno" , " 1");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message6" , "First character can not have space"));

        log.info("Input PIN field has space");
        newCustomerPage.sendValueToField(driver , "pinno" , "123 12");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message6" , "Characters are not allowed"));

    }

    @Test
    public void TC_08(){
        log.info("Leave field tele blank");
        newCustomerPage.sendValueToField(driver , "telephoneno" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message7" , "Mobile no must not be blank"));

        log.info("Input field tele has first space ");
        newCustomerPage.sendValueToField(driver , "telephoneno" , " 1");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message7" , "First character can not have space"));

        log.info("Input field tele has blank space");
        newCustomerPage.sendValueToField(driver , "telephoneno" , "123 123");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message7" , "Characters are not allowed"));

        log.info("Input field tele is special char");
        newCustomerPage.sendValueToField(driver , "telephoneno" , "123@");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message7" , "Special characters are not allowed"));
    }

    @Test
    public void TC_09(){
        log.info("Leave email field empty");
        newCustomerPage.sendValueToField(driver , "emailid" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message9" , "Email-ID must not be blank"));

        log.info("Input email is incorrect");
        newCustomerPage.sendValueToField(driver , "emailid" , "aaa");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message9" , "Email-ID is not valid"));

        log.info("Input email has space");
        newCustomerPage.sendValueToField(driver , "emailid" , "aa @gmail.com");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message9" , "Email-ID is not valid"));

        log.info("Leave password empty");
        newCustomerPage.sendValueToField(driver , "password" , "");

        verifyTrue(newCustomerPage.isErrorMsgShowed(driver , "message18" , "Password must not be blank"));

    }

    @Test
    public void TC_10(){
        log.info("Input valid data to name field");
        newCustomerPage.sendValueToField(driver, "name" , customerName);

        log.info("Input valid data to dob field");
        newCustomerPage.sendValueToField(driver, "dob" , dob);

        log.info("Input valid data to address field");
        newCustomerPage.sendValueToField(driver, "addr" , addr);

        log.info("Input valid data to city field");
        newCustomerPage.sendValueToField(driver, "city" , city);

        log.info("Input valid data to state field");
        newCustomerPage.sendValueToField(driver, "state" , state);

        log.info("Input valid data to PIN field");
        newCustomerPage.sendValueToField(driver, "pinno" , pin);

        log.info("Input valid data to Mobile field");
        newCustomerPage.sendValueToField(driver, "telephoneno" , mobile);

        log.info("Input valid data to Email field");
        newCustomerPage.sendValueToField(driver, "emailid" , mail);

        log.info("Input valid data to Password field");
        newCustomerPage.sendValueToField(driver, "password" , password);

        log.info("Click submit");
        newCustomerPage.clickToButton(driver , "sub");

    }

    @Test
    public void TC_11(){
        log.info("Get Customer ID");
        customerID = newCustomerPage.getTextInTable(driver , "Customer ID");

        log.info("Verify customer name correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "Customer Name") , customerName);

        log.info("Verify dob correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "Birthdate") , dob);

        log.info("Verify address correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "Address") , addr);

        log.info("Verify city correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "City") , city);

        log.info("Verify state correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "State") , state);

        log.info("Verify pin correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "Pin") , pin);

        log.info("Verify mobile correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "Mobile No.") , mobile);

        log.info("Verify email correct");
        verifyEquals(newCustomerPage.getTextInTable(driver , "Email") , mail);

        log.info("CLick to link continue");
        newCustomerPage.clickToLink(driver , "Continue");

        dashboardPage = PageGenerator.getDashboardPage(driver);

        log.info("Verify back to dashboardpage");
        verifyTrue(dashboardPage.isMsgWelcomeDisplayed("Welcome To Manager's Page of Guru99 Bank"));

        log.info("Create the second new customer");
        dashboardPage.clickToLink(driver , "New Customer");

        newCustomerPage = PageGenerator.getNewCustomerPage(driver);

        log.info("Input value to new customer");
        newCustomerPage.sendValueToField(driver , "name" , customerName1);

        log.info("Input value to dob");
        newCustomerPage.sendValueToField(driver , "dob" , dob1);

        log.info("Input value to address");
        newCustomerPage.sendValueToField(driver  , "addr" , addr1);

        log.info("Input value to city");
        newCustomerPage.sendValueToField(driver , "city"  , city1);

        log.info("Input value to state");
        newCustomerPage.sendValueToField(driver  ,"state" , state1);

        log.info("Input value to pin");
        newCustomerPage.sendValueToField(driver  ,"pinno" , pin1);

        log.info("Input value to mobile");
        newCustomerPage.sendValueToField(driver  ,"telephoneno" , mobile1);

        log.info("Input value to email");
        newCustomerPage.sendValueToField(driver  ,"emailid" , mail1);

        log.info("Input value to password");
        newCustomerPage.sendValueToField(driver  ,"password" , password1);

        log.info("Click to submit button");
        newCustomerPage.clickToButton(driver , "sub");

        log.info("Get Customer ID");
        customerID1 = newCustomerPage.getTextInTable(driver , "Customer ID");


    }

    @Test
    public void TC_12(){
        newCustomerPage.clickToLink(driver , "Edit Customer");

        log.info("Go to Edit customer screen");
        editCustomerPage = PageGenerator.getEditCustomerPage(driver);

        log.info("Verify edit customer page");
        verifyTrue(editCustomerPage.isPageTitleCorrect(driver , "Edit Customer Form"));

        log.info("Leave customer id blank");
        editCustomerPage.sendValueToField(driver , "cusid" , "");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver , "message14" , "Customer ID is required"));

        log.info("Input customer id is not number");
        editCustomerPage.sendValueToField(driver , "cusid" , "aa");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver , "message14" , "Characters are not allowed"));

        log.info("Input id is special char");
        editCustomerPage.sendValueToField(driver , "cusid" , "12@@");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver , "message14" , "Special characters are not allowed"));

        log.info("Input valid customer id");
        editCustomerPage.sendValueToField(driver , "cusid" , customerID);

        log.info("CLick button submit");
        editCustomerPage.clickToButton(driver , "AccSubmit");

        verifyTrue(editCustomerPage.isPageTitleCorrect(driver , "Edit Customer"));

    }

    @Test
    public void TC_13() {
        log.info("Verify customer name disable in edit");
        verifyFalse(editCustomerPage.isFieldEnabled(driver, "name"));

        log.info("Verify gender disable in edit");
        verifyFalse(editCustomerPage.isFieldEnabled(driver, "gender"));

        log.info("Verify dob disable in edit");
        verifyFalse(editCustomerPage.isFieldEnabled(driver, "dob"));

        log.info("Verify address enable in edit");
        verifyTrue(editCustomerPage.isFieldEnabled(driver, "addr"));

        log.info("Verify city enable in edit");
        verifyTrue(editCustomerPage.isFieldEnabled(driver, "city"));

        log.info("Verify state enable in edit");
        verifyTrue(editCustomerPage.isFieldEnabled(driver, "state"));

        log.info("Verify pin enable in edit");
        verifyTrue(editCustomerPage.isFieldEnabled(driver, "pinno"));

        log.info("Verify mobile enable in edit");
        verifyTrue(editCustomerPage.isFieldEnabled(driver, "telephoneno"));

        log.info("Verify email enable in edit");
        verifyTrue(editCustomerPage.isFieldEnabled(driver, "emailid"));

        log.info("Leave address blank in edit");
        editCustomerPage.sendValueToField(driver, "addr", "");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message3", "Address Field must not be blank"));

        log.info("Leave city blank in edit");
        editCustomerPage.sendValueToField(driver, "city", "");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message4", "City Field must not be blank"));

        log.info("Input city numeric in edit");
        editCustomerPage.sendValueToField(driver, "city", "11a");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message4", "Numbers are not allowed"));

        log.info("Input city special char in edit");
        editCustomerPage.sendValueToField(driver, "city", "aa@@");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message4", "Special characters are not allowed"));

        log.info("Leave state blank in edit");
        editCustomerPage.sendValueToField(driver, "state", "");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message5", "State must not be blank"));

        log.info("Input state numeric in edit");
        editCustomerPage.sendValueToField(driver, "state", "aa1");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message5", "Numbers are not allowed"));

        log.info("Input state special char in edit");
        editCustomerPage.sendValueToField(driver, "state", "aa@");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message5", "Special characters are not allowed"));

        log.info("Leave Pin field blank in edit");
        editCustomerPage.sendValueToField(driver, "pinno", "");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message6", "PIN Code must not be blank"));

        log.info("Input Pin field not numeric in edit");
        editCustomerPage.sendValueToField(driver, "pinno", "aa");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message6", "Characters are not allowed"));

        log.info("Input Pin field has 1 char in edit");
        editCustomerPage.sendValueToField(driver, "pinno", "1");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message6", "PIN Code must have 6 Digits"));

        log.info("Input Pin field has special char in edit");
        editCustomerPage.sendValueToField(driver, "pinno", "1@");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message6", "Special characters are not allowed"));

        log.info("Leave tele field blank in edit");
        editCustomerPage.sendValueToField(driver, "telephoneno", "");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message7", "Mobile no must not be blank"));

        log.info("Input tele field special char in edit");
        editCustomerPage.sendValueToField(driver, "telephoneno", "@@");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message7", "Special characters are not allowed"));

        log.info("Leave email blank in edit");
        editCustomerPage.sendValueToField(driver, "emailid", "");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message9", "Email-ID must not be blank"));

        log.info("Input email wrong format");
        editCustomerPage.sendValueToField(driver, "emailid", "fdf@gmail");

        verifyTrue(editCustomerPage.isErrorMsgShowed(driver, "message9", "Email-ID is not valid"));

        log.info("Update value of address");
        editCustomerPage.sendValueToField(driver, "addr", addEdit);

        log.info("Update value of city");
        editCustomerPage.sendValueToField(driver, "city", cityEdit);

        log.info("Update value of state");
        editCustomerPage.sendValueToField(driver, "state", stateEdit);

        log.info("Update value of pin");
        editCustomerPage.sendValueToField(driver, "pinno", pinEdit);

        log.info("Update value of mobile");
        editCustomerPage.sendValueToField(driver, "telephoneno", mobileEdit);

        log.info("Update value of email");
        editCustomerPage.sendValueToField(driver, "emailid", emailEdit);

        log.info("Click button subbmit");
        editCustomerPage.clickToButton(driver, "sub");

        log.info("Verify value add updated");
        verifyEquals(editCustomerPage.getTextInTable(driver, "Address"), addEdit);

        log.info("Verify value city updated");
        verifyEquals(editCustomerPage.getTextInTable(driver, "City"), cityEdit);

        log.info("Verify value of state updated");
        verifyEquals(editCustomerPage.getTextInTable(driver, "State"), stateEdit);

        log.info("Verify value of pin updated");
        verifyEquals(editCustomerPage.getTextInTable(driver, "Pin"), pinEdit);

        log.info("Verify value of mobile updated");
        verifyEquals(editCustomerPage.getTextInTable(driver, "Mobile"), mobileEdit);

        log.info("Verify value of email updated");
        verifyEquals(editCustomerPage.getTextInTable(driver, "Email"), emailEdit);
    }

    @Test
    public void TC_14() {
        editCustomerPage.clickToLink(driver , "Delete Customer");

        deleteCustomerPage = PageGenerator.getDeleteCustomerPage(driver);

        log.info("Verify title delete customer form");
        verifyTrue(deleteCustomerPage.isPageTitleCorrect(driver , "Delete Customer Form"));

        log.info("Leave customer id blank");
        deleteCustomerPage.sendValueToField(driver , "cusid" , "");

        verifyTrue(deleteCustomerPage.isErrorMsgShowed(driver , "message14" , "Customer ID is required"));

        log.info("Input customer id not numeric");
        deleteCustomerPage.sendValueToField(driver , "cusid" , "aa");

        verifyTrue(deleteCustomerPage.isErrorMsgShowed(driver , "message14" , "Characters are not allowed"));

        log.info("Input customer id special char");
        deleteCustomerPage.sendValueToField(driver , "cusid" , "123@");

        verifyTrue(deleteCustomerPage.isErrorMsgShowed(driver , "message14" , "Special characters are not allowed"));

        log.info("Input customer id blank space");
        deleteCustomerPage.sendValueToField(driver , "cusid" , "123 12");

        verifyTrue(deleteCustomerPage.isErrorMsgShowed(driver , "message14" , "Characters are not allowed"));

        log.info("Input customer id first space");
        deleteCustomerPage.sendValueToField(driver , "cusid" , "");

        verifyTrue(deleteCustomerPage.isErrorMsgShowed(driver , "message14" , "First character can not have space"));

    }

    @Test
    public void TC_15(){
        deleteCustomerPage.clickToLink(driver , "New Account");

        newAccountPage = PageGenerator.getNewAccountPage(driver);

        log.info("Verify title form new account");
        verifyTrue(newAccountPage.isPageTitleCorrect(driver , "Add new account form"));

        log.info("Leave customer id blank");
        newAccountPage.sendValueToField(driver , "cusid" , "");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver,  "message14" , "Customer ID is required"));

        log.info("Input customer id is not numeric");
        newAccountPage.sendValueToField(driver , "cusid" , "aa");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver,  "message14" , "Characters are not allowed"));

        log.info("Input customer id special char");
        newAccountPage.sendValueToField(driver , "cusid" , "1233@");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver,  "message14" , "Special characters are not allowed"));

        log.info("Input customer id blank space");
        newAccountPage.sendValueToField(driver , "cusid" , "123 123");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver,  "message14" , "Characters are not allowed"));

        log.info("Input customer id first space");
        newAccountPage.sendValueToField(driver , "cusid" , " 1");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver,  "message14" , "First character can not have space"));

        log.info("Leave field deposit blank");
        newAccountPage.sendValueToField(driver , "inideposit" , "");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver , "message19" , "Initial Deposit must not be blank"));

        log.info("Input field deposit not numeric");
        newAccountPage.sendValueToField(driver , "inideposit" , "a");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver , "message19" , "Characters are not allowed"));

        log.info("Input field deposit special char");
        newAccountPage.sendValueToField(driver , "inideposit" , "123@");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver , "message19" , "Special characters are not allowed"));

        log.info("Input field deposit space");
        newAccountPage.sendValueToField(driver , "inideposit" , "123 123");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver , "message19" , "Characters are not allowed"));

        log.info("Input field deposit first space");
        newAccountPage.sendValueToField(driver , "inideposit" , " 1");

        verifyTrue(newAccountPage.isErrorMsgShowed(driver , "message19" , "First character can not have space"));
    }

    @Test
    public void TC_16(){
        log.info("Input customer id in new account");
        newAccountPage.sendValueToField(driver , "cusid" , customerID);

        log.info("Input deposit in new account");
        newAccountPage.sendValueToField(driver , "inideposit" , deposit);

        newAccountPage.clickToButton(driver , "button2");

        log.info("Get account no");
        accountID = newAccountPage.getTextInTable(driver , "Account ID");

        log.info("Verify value of customer id");
        verifyEquals(newAccountPage.getTextInTable(driver , "Customer ID") , customerID);

        log.info("Verify value of customer name");
        verifyEquals(newAccountPage.getTextInTable(driver , "Customer Name") , customerName);

        log.info("Verify value of email");
        verifyEquals(newAccountPage.getTextInTable(driver , "Email") , email);

        log.info("Verify value of account type");
        verifyEquals(newAccountPage.getTextInTable(driver , "Account Type") , accountType);

        log.info("Verify value of date of open");
        date = newAccountPage.getCurrentDate(driver);
        verifyEquals(newAccountPage.getTextInTable(driver , "Date of Opening") , date);

        log.info("Verify value of deposit");
        verifyEquals(newAccountPage.getTextInTable(driver , "Current Amount") , deposit);

        log.info("Click link continue");
        newAccountPage.clickToLink(driver , "Continue");

        log.info("Verify back to dashboard Page");
        dashboardPage = PageGenerator.getDashboardPage(driver);

        dashboardPage.isMsgWelcomeDisplayed("Welcome To Manager's Page of Guru99 Bank");

        dashboardPage.clickToLink(driver , "New Account");

        newAccountPage = PageGenerator.getNewAccountPage(driver);

        log.info("Input customer id");
        newAccountPage.sendValueToField(driver , "cusid" , customerID1);

        log.info("Input depoosit");
        newAccountPage.sendValueToField(driver , "inideposit" , "50000");

        log.info("Click to submit button");
        newAccountPage.clickToButton(driver , "button2");

        log.info("Get account no 2");
        accountID1 = newAccountPage.getTextInTable(driver, "Account ID");

    }

    @Test
    public void TC_17(){
        newAccountPage.clickToLink(driver , "Edit Account");

        editAccountPage = PageGenerator.getEditAccountPage(driver);

        log.info("Verify edit account title");
        verifyTrue(editAccountPage.isPageTitleCorrect(driver , "Edit Account Form"));

        log.info("Leave field account number blank");
        editAccountPage.sendValueToField(driver , "accountno" , "");

        verifyTrue(editAccountPage.isErrorMsgShowed(driver , "message2" , "Account Number must not be blank"));

        log.info("Input field account number not numeric");
        editAccountPage.sendValueToField(driver , "accountno" , "aa");

        verifyTrue(editAccountPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input field account number special char");
        editAccountPage.sendValueToField(driver , "accountno" , "11@");

        verifyTrue(editAccountPage.isErrorMsgShowed(driver , "message2" , "Special characters are not allowed"));

        log.info("Input field account number blank space");
        editAccountPage.sendValueToField(driver , "accountno" , "123 123");

        verifyTrue(editAccountPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input field account number first space");
        editAccountPage.sendValueToField(driver , "accountno" , " ");

        verifyTrue(editAccountPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input field account number vald");
        editAccountPage.sendValueToField(driver , "accountno" , accountID);

    }

    @Test
    public void TC_18(){
        log.info("Verify title of edit account info");
        verifyTrue(editAccountPage.isPageTitleCorrect(driver , "Edit Account Entry Form"));

        log.info("Verify field customer id disable in field");
        verifyFalse(editAccountPage.isFieldEnabled(driver , "txtcid"));

        log.info("Verify field account type enable");
        verifyTrue(editAccountPage.isDropdownEnable(driver , "a_type"));

        log.info("Verify field balance enable");
        verifyTrue(editAccountPage.isFieldEnabled(driver , "txtinitdep"));

        log.info("Verify value of customer id");
        verifyEquals(editAccountPage.getValueInField(driver , "txtcid") , customerID);

        log.info("Verify value of account type");
        verifyEquals(editAccountPage.getValueInDropdown(driver , "a_type") , accountType);

        log.info("Verify value of balance");
        verifyEquals(editAccountPage.getValueInField(driver , "txtinitdep") , deposit);

        log.info("Update account type to current");
        editAccountPage.selectInDropdown(driver , "a_type" , "Current");

        log.info("Click button subbmit");
        editAccountPage.clickToButton(driver , "AccSubmit");

        log.info("Verify update msg displayed");
        verifyTrue(editAccountPage.isPageTitleCorrect(driver , "Account details updated Successfully!!!"));

        log.info("Verify account type update");
        verifyEquals(editAccountPage.getTextInTable(driver , "Account Type") , "Current");
    }

    @Test
    public void TC_19(){
        editAccountPage.clickToLink(driver ,"Delete Account");

        deleteAccountPage = PageGenerator.getDeleteAccountPage(driver);

        log.info("Verify title of delete form");
        verifyTrue(deleteAccountPage.isPageTitleCorrect(driver , "Delete Account Form"));

        log.info("Leave account no blank");
        deleteAccountPage.sendValueToField(driver , "accountno" , "");

        verifyTrue(deleteAccountPage.isErrorMsgShowed(driver , "message2" , "Account Number must not be blank"));

        log.info("Input account no not numeric");
        deleteAccountPage.sendValueToField(driver , "accountno" , "a");

        verifyTrue(deleteAccountPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input account no special char");
        deleteAccountPage.sendValueToField(driver , "accountno" , "12@");

        verifyTrue(deleteAccountPage.isErrorMsgShowed(driver , "message2" , "Special characters are not allowed"));

        log.info("Input account no blank space");
        deleteAccountPage.sendValueToField(driver , "accountno" , "123 123");

        verifyTrue(deleteAccountPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input account no space in the first");
        deleteAccountPage.sendValueToField(driver , "accountno" , " ");

        verifyTrue(deleteAccountPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));
    }

    @Test
    public void TC_20(){
        deleteAccountPage.clickToLink(driver , "Mini Statement");

        miniStatementPage = PageGenerator.getMiniStatementPage(driver);

        log.info("Verify mini statement form");
        verifyTrue(miniStatementPage.isPageTitleCorrect(driver , "Mini Statement Form"));

        log.info("Leave account no blank");
        miniStatementPage.sendValueToField(driver , "accountno" , "");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Account Number must not be blank"));

        log.info("Input account no not numeric");
        miniStatementPage.sendValueToField(driver , "accountno" , "aa");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Special characters are not allowed"));

        log.info("Input account no special char");
        miniStatementPage.sendValueToField(driver , "accountno" , "11@");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Account Number must not be blank"));

        log.info("Input account no blank space");
        miniStatementPage.sendValueToField(driver , "accountno" , "12 12");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input account no first space");
        miniStatementPage.sendValueToField(driver , "accountno" , " ");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

    }

    @Test
    public void TC_21(){
        miniStatementPage.clickToLink(driver , "Customised Statement");

        customizeStatementPage = PageGenerator.getCustomizeStatementPage(driver);

        log.info("Verify custom statement form");
        verifyTrue(customizeStatementPage.isPageTitleCorrect(driver , "Customized Statement Form"));

        log.info("Leave account no blank");
        miniStatementPage.sendValueToField(driver , "accountno" , "");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Account Number must not be blank"));

        log.info("Input account no not numeric");
        miniStatementPage.sendValueToField(driver , "accountno" , "aa");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input account no special char");
        miniStatementPage.sendValueToField(driver , "accountno" , "11@");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Special characters are not allowed"));

        log.info("Input account no blank space");
        miniStatementPage.sendValueToField(driver , "accountno" , "12 12");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Input account no first space");
        miniStatementPage.sendValueToField(driver , "accountno" , " ");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message2" , "Characters are not allowed"));

        log.info("Leave amount lower limit blank");
        miniStatementPage.sendValueToField(driver , "amountlowerlimit" , "");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message12" , "Amount Lower Limit cannot be empty"));

        log.info("Input amount lower limit not numeric");
        miniStatementPage.sendValueToField(driver , "amountlowerlimit" , "aa");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message12" , "Characters are not allowed"));

        log.info("Input amount lower limit special char");
        miniStatementPage.sendValueToField(driver , "amountlowerlimit" , "@@");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message12" , "Special characters are not allowed"));

        log.info("Input amout lower limit has space");
        miniStatementPage.sendValueToField(driver , "amountlowerlimit" , "123 123");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message12" , "Characters are not allowed"));

        log.info("Input amount lower limit has the first space");
        miniStatementPage.sendValueToField(driver , "amountlowerlimit" , " ");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message12" , "Characters are not allowed"));

        log.info("Leave number of transaction field blank");
        miniStatementPage.sendValueToField(driver , "numtransaction" , "");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message13" , "Number of Transaction cannot be empty"));

        log.info("Input number of transaction field not numeric");
        miniStatementPage.sendValueToField(driver , "numtransaction" , "aa");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message13" , "Characters are not allowed"));

        log.info("Input number of transaction field special char");
        miniStatementPage.sendValueToField(driver , "numtransaction" , "12@");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message13" , "Special characters are not allowed"));

        log.info("Input number of transaction field has space");
        miniStatementPage.sendValueToField(driver , "numtransaction" , "12 12");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message13" , "Characters are not allowed"));

        log.info("Input number of transaction field has first space");
        miniStatementPage.sendValueToField(driver , "numtransaction" , " ");

        verifyTrue(miniStatementPage.isErrorMsgShowed(driver , "message13" , "Characters are not allowed"));
    }

    @Test
    public void TC_22(){
        miniStatementPage.clickToLink(driver , "Fund Transfer");

        fundTransferPage = PageGenerator.getFundTransferPage(driver);

        log.info("Verify title of fund transfer form");
        verifyTrue(fundTransferPage.isPageTitleCorrect(driver , "Fund transfer"));

        log.info("Verify field Payer blank");
        fundTransferPage.sendValueToField(driver , "payersaccount" , "");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message10" , "Payers Account Number must not be blank"));

        log.info("Input field Payer not numeric");
        fundTransferPage.sendValueToField(driver , "payersaccount" , "aa");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message10" , "Characters are not allowed"));

        log.info("Input field Payer special char");
        fundTransferPage.sendValueToField(driver , "payersaccount" , "@");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message10" , "Special characters are not allowed"));

        log.info("Verify field Payee blank");
        fundTransferPage.sendValueToField(driver , "payeeaccount" , "");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message11" , "Payees Account Number must not be blank"));

        log.info("Input field Payee not numeric");
        fundTransferPage.sendValueToField(driver , "payeeaccount" , "aa");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message11" , "Characters are not allowed"));

        log.info("Input field Payee special char");
        fundTransferPage.sendValueToField(driver , "payeeaccount" , "@");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message11" , "Special characters are not allowed"));

        log.info("Verify field amount blank");
        fundTransferPage.sendValueToField(driver , "ammount" , "");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message1" , "Amount field must not be blank"));

        log.info("Input field amount is not numeric");
        fundTransferPage.sendValueToField(driver , "ammount" , "aa");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message1" , "Characters are not allowed"));

        log.info("Verify field amount blank");
        fundTransferPage.sendValueToField(driver , "ammount" , "12@");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message1" , "Special characters are not allowed"));

        log.info("Leave field desc blank");
        fundTransferPage.sendValueToField(driver , "desc" , "");

        verifyTrue(fundTransferPage.isErrorMsgShowed(driver , "message17" , "Description can not be blank"));

    }

    @Test
    public void TC_23(){
        fundTransferPage.clickToLink(driver , "Change Password");

        changePWPage = PageGenerator.getChangePWPage(driver);

        log.info("Verify change pw form title");
        verifyTrue(changePWPage.isPageTitleCorrect(driver , "Change Password"));

        log.info("Leave field old pw blank");
        changePWPage.sendValueToField(driver , "oldpassword" , "");

        verifyTrue(changePWPage.isErrorMsgShowed(driver , "message20" , "Old Password must not be blank"));

        log.info("Leave field new pw blank");
        changePWPage.sendValueToField(driver , "newpassword" , "");

        verifyTrue(changePWPage.isErrorMsgShowed(driver , "message21" , "New Password must not be blank"));

        log.info("Input new pw no numeric value");
        changePWPage.sendValueToField(driver , "newpassword" , "abc@");

        verifyTrue(changePWPage.isErrorMsgShowed(driver , "message21" , "Enter at-least one numeric value"));

        log.info("Input new pw no special char value");
        changePWPage.sendValueToField(driver , "newpassword" , "abc1");

        verifyTrue(changePWPage.isErrorMsgShowed(driver , "message21" , "Enter at-least one special character"));

        log.info("Input new pw contain text password");
        changePWPage.sendValueToField(driver , "newpassword" , "Guru99@Password");

        verifyTrue(changePWPage.isErrorMsgShowed(driver , "message21" , "Choose a difficult Password"));

        log.info("Input valid new password");
        changePWPage.sendValueToField(driver , "newpassword" , "Guru99@");

        log.info("Leave confirm pw blank");
        changePWPage.sendValueToField(driver , "confirmpassword" , "");

        verifyTrue(changePWPage.isErrorMsgShowed(driver , "message22" , "Confirm Password must not be blank"));

        log.info("Input confirm pw diffrent with new pw");
        changePWPage.sendValueToField(driver , "newpassword" , "Guru98@");

        verifyTrue(changePWPage.isErrorMsgShowed(driver , "message22" , "Passwords do not Match"));


    }

    @Test
    public void TC_24() {
        changePWPage.clickToLink(driver, "Edit Customer");

        log.info("Click to link Deposit");
        changePWPage.clickToLink(driver, "Deposit");

        depositPage = PageGenerator.getDepositPage(driver);

        log.info("Verify title deposit page");
        verifyTrue(depositPage.isPageTitleCorrect(driver, "Amount Deposit Form"));

        log.info("Input value account no");
        depositPage.sendValueToField(driver, "accountno", accountID);

        log.info("Input value amount");
        depositPage.sendValueToField(driver, "ammount", depositNum);

        log.info("Input value description");
        depositPage.sendValueToField(driver, "desc", "Deposit");

        log.info("Click button submit");
        depositPage.clickToButton(driver, "AccSubmit");

        log.info("Verify title of table info");
        verifyTrue(depositPage.isPageTitleCorrect(driver, "Transaction details of Deposit for Account " + accountID));

        log.info("Verify Account No");
        verifyEquals(depositPage.getTextInTable(driver, "Account No"), accountID);

        log.info("Verify Amount Credited");
        verifyEquals(depositPage.getTextInTable(driver, "Amount Credited"), depositNum);

        log.info("Verify Type of Transaction");
        verifyEquals(depositPage.getTextInTable(driver, "Type of Transaction"), "Deposit");

        log.info("Verify Description");
        verifyEquals(depositPage.getTextInTable(driver, "Description"), "Deposit");

        log.info("Verify Current Balance");
        verifyEquals(depositPage.getTextInTable(driver, "Current Balance"), numAfterDeposit);

    }

    @Test
    public void TC_25(){
        depositPage.clickToLink(driver , "Withdrawal");

        withdrawPage = PageGenerator.getWithdrawPage(driver);

        log.info("Verify with draw page title");
        verifyTrue(withdrawPage.isPageTitleCorrect(driver , "Amount Withdrawal Form"));

        log.info("Input value account no");
        withdrawPage.sendValueToField(driver , "accountno" , accountID);

        log.info("Input value amount");
        withdrawPage.sendValueToField(driver , "ammount" , withdrawNum);

        log.info("Input value description");
        withdrawPage.sendValueToField(driver , "desc" , "Withdraw");

        log.info("CLick button subbmit");
        withdrawPage.clickToButton(driver, "AccSubmit");

        log.info("Verify title after submit");
        verifyTrue(withdrawPage.isPageTitleCorrect(driver , "Transaction details of Withdrawal for Account " + accountID));

        log.info("Verify account no");
        verifyEquals(withdrawPage.getTextInTable(driver , "Account No") , accountID);

        log.info("Verify Amount Debited");
        verifyEquals(withdrawPage.getTextInTable(driver , "Amount Debited") , withdrawNum);

        log.info("Verify Type of Transaction");
        verifyEquals(withdrawPage.getTextInTable(driver , "Type of Transaction") , "Withdrawal");

        log.info("Verify Description");
        verifyEquals(withdrawPage.getTextInTable(driver , "Description") , "Withdraw");

        log.info("Verify Current Balance");
        verifyEquals(withdrawPage.getTextInTable(driver , "Current Balance") , numAfterDeposit);

    }

    @Test
    public void TC_26(){
        withdrawPage.clickToLink(driver , "Fund Transfer");

        fundTransferPage = PageGenerator.getFundTransferPage(driver);

        log.info("Verify fund transfer form title");
        verifyTrue(fundTransferPage.isPageTitleCorrect(driver , "Fund transfer"));

        log.info("Input value payer account");
        fundTransferPage.sendValueToField(driver , "payersaccount" , accountID);

        log.info("Input value payee account");
        fundTransferPage.sendValueToField(driver , "payeeaccount" , accountID1);

        log.info("Input value of amount");
        fundTransferPage.sendValueToField(driver , "ammount" , transferNum);

        log.info("Input value of description");
        fundTransferPage.sendValueToField(driver , "desc" , "Transfer");

        log.info("Click to subbmit button");
        fundTransferPage.clickToButton(driver , "AccSubmit");

        log.info("Verify title after submit");
        verifyTrue(fundTransferPage.isPageTitleCorrect(driver , "Fund Transfer Details"));

        log.info("Verify field from account");
        verifyEquals(fundTransferPage.getTextInTable(driver , "From Account Number") , accountID);

        log.info("Verify field to account");
        verifyEquals(fundTransferPage.getTextInTable(driver , "To Account Number") , accountID1);

        log.info("Verify amount");
        verifyEquals(fundTransferPage.getTextInTable(driver , "Amount") , transferNum);

        log.info("Verify description");
        verifyEquals(fundTransferPage.getTextInTable(driver , "Description") , "Transfer");
    }

    @Test
    public void TC_27(){
        fundTransferPage.clickToLink(driver , "Balance Enquiry");

        balancePage = PageGenerator.getBalancePage(driver);

        log.info("Verify title form balance");
        verifyTrue(balancePage.isPageTitleCorrect(driver , "Balance Enquiry Form"));

        log.info("Input account no");
        balancePage.sendValueToField(driver , "accountno" , accountID);

        log.info("Click to submit");
        balancePage.clickToButton(driver , "AccSubmit");

        log.info("Verify title of balance form details");
        verifyTrue(balancePage.isPageTitleCorrect(driver , "Balance Details for Account " + accountID));

        log.info("Verify account no");
        verifyEquals(balancePage.getTextInTable(driver , "Account No") , accountID);

        log.info("Verify Type of account");
        verifyEquals(balancePage.getTextInTable(driver , "Type of Account") , "Current");

        log.info("Verify balance");
        verifyEquals(balancePage.getTextInTable(driver , "Balance") , numAfterTransfer);
    }

    @Test
    public void TC_28(){
        balancePage.clickToLink(driver , "Delete Account");

        deleteAccountPage = PageGenerator.getDeleteAccountPage(driver);

        log.info("Input Account no");
        deleteAccountPage.sendValueToField(driver  ,"accountno" , accountID);

        log.info("Click to button subbmit");
        deleteAccountPage.clickToButton(driver , "AccSubmit");

        log.info("Verify msg in alert");
        verifyEquals(deleteAccountPage.getAlertText(driver) , "Do you really want to delete this Account?");

        log.info("Click yes in alert");
        deleteAccountPage.acceptAlert(driver);

        log.info("Verify msg after delete account");
        verifyEquals(deleteAccountPage.getAlertText(driver) , "Account Deleted Sucessfully");

        log.info("Close pop up");
        deleteAccountPage.acceptAlert(driver);

        dashboardPage = PageGenerator.getDashboardPage(driver);
        log.info("Verify back to dashboard page");
        verifyTrue(dashboardPage.isMsgWelcomeDisplayed("Account Deleted Sucessfully"));

        dashboardPage.clickToLink(driver , "Edit Account");

        editAccountPage = PageGenerator.getEditAccountPage(driver);

        log.info("Input account no deleted");
        editAccountPage.sendValueToField(driver , "accountno" , accountID);

        log.info("Click button submit");
        editAccountPage.clickToButton(driver , "AccSubmit");

        log.info("Verify msg no account id displayed");
        verifyEquals(editAccountPage.getAlertText(driver) , "Account does not exist");

        log.info("Close pop up");
        editAccountPage.acceptAlert(driver);
    }
    @Test
    public void TC_29(){
        editAccountPage.clickToLink(driver , "Delete Customer");

        deleteCustomerPage = PageGenerator.getDeleteCustomerPage(driver);

        log.info("Input customer id");
        deleteCustomerPage.sendValueToField(driver , "cusid" , customerID);

        log.info("Click button submit");
        deleteCustomerPage.clickToButton(driver , "AccSubmit");

        log.info("Verify msg after click button submit");
        verifyEquals(deleteCustomerPage.getAlertText(driver) , "Do you really want to delete this Customer?");

        log.info("Accept delete customer");
        deleteCustomerPage.acceptAlert(driver);

        log.info("Verify msg after delete customer");
        verifyEquals(deleteCustomerPage.getAlertText(driver) , "Customer deleted Successfully");

        log.info("Close pop up after delete");
        deleteAccountPage.acceptAlert(driver);

        log.info("Verify back to dashboard page");
        dashboardPage = PageGenerator.getDashboardPage(driver);

        verifyTrue(dashboardPage.isMsgWelcomeDisplayed("Welcome To Manager's Page of Guru99 Bank"));

        dashboardPage.clickToLink(driver , "Edit Custoemr");

        editCustomerPage = PageGenerator.getEditCustomerPage(driver);

        log.info("Input customer id");
        editCustomerPage.sendValueToField(driver , "cusid" , customerID);

        log.info("Click submit button");
        editCustomerPage.clickToButton(driver , "AccSubmit");

        log.info("Verify message no customer");
        verifyEquals(editCustomerPage.getAlertText(driver) , "Customer does not exist!!");

        log.info("Close pop up");
        editCustomerPage.acceptAlert(driver);

    }
}
