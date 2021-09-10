package tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.browserDataProvider.BrowserDataProvider;
import com.browsers.BrowserConfig;
import com.pages.LoginPage;
import com.util.Helper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTest {

    public WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    BrowserDataProvider data;
    LoginPage loginPage;


    @BeforeClass
    public void getTestData() throws IOException {
        data = new BrowserDataProvider();
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("TestReports/report_"+ Helper.getDateAndTime()+".html");
        extent.attachReporter(spark);
        test = extent.createTest("Login test");
    }

    @BeforeMethod
    public void browserSetup() {
        driver = BrowserConfig.startAPP(driver, data.getBrowser(), data.getUrl());
    }

    @AfterMethod
    public void testResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
           test.fail("Failed");
            Helper.takeScreenShot(driver);
        } else {
            test.pass("Passed");
        }

        extent.flush();

        BrowserConfig.closeAPP(driver);
    }



}
