package testcases;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P02_LoginPage;

import java.io.IOException;

import static drivers.DriverHolder.getDriver;
import static pages.PageBase.captureScreenshot;
import static util.Utility.*;

public class TC02_LoginPage extends TestBase {

    @Test(priority = 1, description = "Method to login of Para-Bank website")
    public void validateLogin_P() throws IOException, ParseException {
        loadJsonData("src\\test\\resources\\TestData\\assertionURL.json");
        loadCSV("src\\test\\resources\\TestData\\usersData.csv");
        new P02_LoginPage(getDriver()).enterUsername(getUserData("user_1", "username"))
                .enterPassword(getUserData("user_1", "password"))
                .clickLoginButton();

        //get Account ID
        updateColumnValue("src\\test\\resources\\TestData\\usersData.csv",
                "user_1", "AccountID",new P02_LoginPage(getDriver()).getAccountID());

        //Capture ScreenShot
        captureScreenshot(getDriver(), "Login_Page_screenShot");

        //Soft_Assertion of Message and URL
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                new P02_LoginPage(getDriver()).isLoginSuccess(getValue("LoginWelcomeMessage")),
                "❌ Welcome message mismatch!"
        );

        //Hard_Assertion With Login
        Assert.assertTrue(
                new P02_LoginPage(getDriver()).isLoginSuccessful(getValue("loginURL")),
                "❌ Login did not redirect to expected URL!"
        );

    }
}
