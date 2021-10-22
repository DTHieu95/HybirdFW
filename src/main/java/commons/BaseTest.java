package commons;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    private WebDriver driver;
    protected final Log log;
    private String projectLocation = System.getProperty("user.dir");
    private String osName = System.getProperty("os.name");

    protected BaseTest(){
        log = LogFactory.getLog(getClass());
    }

    private enum BROWSER {
        CHROME , FIREFOX , IE , SAFARI , EDGE_CHROMIUM , H_CHROME , H_FIREFOX;
    }

    private enum OS{
        WINDOW , MAC_OS , LINUX;
    }

    private enum PLATFORM{
        ANDROID , IOS;
    }




    protected WebDriver getBrowserDriver(String browserName) {
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());

        if(browser == BROWSER.FIREFOX) {
            //System.setProperty("webdriver.gecko.driver", projectLocation + getSlash("browserDrivers") + "geckodriver");

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if (browser == BROWSER.CHROME) {
            //System.setProperty("webdriver.chrome.driver", projectLocation + getSlash("browserDrivers") +  "chromedriver");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else {
            throw new RuntimeException("Wrong browser name");
        }

        driver.manage().timeouts().implicitlyWait( 15, TimeUnit.SECONDS);
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName , String appURL ) {
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        if (browser == BROWSER.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if (browser == BROWSER.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browser == BROWSER.EDGE_CHROMIUM) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else if (browser == BROWSER.SAFARI) {
            driver = new SafariDriver();
        }else if (browser == BROWSER.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        }else if (browser == BROWSER.H_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait( 15, TimeUnit.SECONDS);
        driver.get(appURL);
        return driver;
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }


    private boolean checkTrue(boolean condition) {
        boolean pass = true;
        try {
            if (condition == true) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyTrue(boolean condition) {
        return checkTrue(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (condition == false) {
                log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }


    @BeforeTest
    public void deleteAllFilesInReportNGScreenshot() {
        System.out.println("---------- START delete file in folder ----------");
        deleteAllFileInFolder();
        System.out.println("---------- END delete file in folder ----------");
    }

    public void deleteAllFileInFolder() {
        try {
            String workingDir = System.getProperty("user.dir");
            String pathFolderDownload = workingDir + File.separator + "screenshotreportNG";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println(listOfFiles[i].getName());
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void cleanBrowserAndDriver() {
        String cmd = "";
        try {

            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            if (driver.toString().contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = cmd = "pkill chromedriver";
                }

            } else if (driver.toString().contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driver.toString().contains("firefox")) {
                if (osName.toLowerCase().contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driver.toString().contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else  {
                    cmd = "pkill msedgedriver";

                }
            }
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    protected void cleanExecutableDriver() {

    }


    protected void showBrowserConsoleLogs(WebDriver driver) {
        if (driver.toString().contains("chrome")) {
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> loglist = logs.getAll();
            for (LogEntry logging : loglist) {
                System.out.println(" -------- " + logging.getLevel().toString() + " ---------- \n" + logging.getMessage());;
            }
        }
    }




}


