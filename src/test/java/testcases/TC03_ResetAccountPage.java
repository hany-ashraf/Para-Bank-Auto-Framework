package testcases;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P02_LoginPage;
import pages.P03_ResetAccountPag;

import java.io.IOException;

import static drivers.DriverHolder.getDriver;
import static pages.PageBase.captureScreenshot;
import static util.Utility.*;

public class TC03_ResetAccountPage extends TestBase {

    @Test(priority = 1, description = "Method to login of Para-Bank website")
    public void validateResetPassword_P() throws IOException, ParseException {
        loadJsonData("src\\test\\resources\\TestData\\assertionURL.json");
        loadCSV("src\\test\\resources\\TestData\\usersData.csv");
        new P02_LoginPage(getDriver()).clickForgotLoginInfo();
        new P03_ResetAccountPag(getDriver()).enterFirstName(getUserData("user_1", "firstName"))
                .enterLastName(getUserData("user_1", "lastName"))
                .enterAddress(getUserData("user_1", "address"))
                .enterCity(getUserData("user_1", "city"))
                .enterState(getUserData("user_1", "state"))
                .enterZipCode(getUserData("user_1", "zipCode"))
                .enterSSN(getUserData("user_1", "ssn"))
                .clickFindMyLoginInfoButton();

        //Capture ScreenShot
        captureScreenshot(getDriver(), "ResetPassword_Page_screenShot");

        //Soft_Assertion of Message
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                new P03_ResetAccountPag(getDriver()).isResetPassword(getValue("ResetMessage")),
                "❌ Welcome message mismatch!"
        );
        //Hard_Assertion With Login
        Assert.assertTrue(
                new P03_ResetAccountPag(getDriver()).isLookupSuccessful(getValue("forgetURL")),
                "❌ Reset Account did not redirect to expected URL!"
        );
    }
}
