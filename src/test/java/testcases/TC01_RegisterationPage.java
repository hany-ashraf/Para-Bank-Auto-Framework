package testcases;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P02_LoginPage;
import pages.P01_RegisterationPage;
import pages.P03_ResetAccountPag;
import pages.P04_LandingPage;

import java.io.IOException;

import static drivers.DriverHolder.getDriver;
import static pages.PageBase.captureScreenshot;
import static util.Utility.*;

public class TC01_RegisterationPage extends TestBase {

    String phoneNum = "12355682";

    @Test(priority = 1, description = "Method for create new user")
    public void validateCreationNewUser_P() throws IOException, ParseException {
        loadJsonData("src\\test\\resources\\TestData\\assertionURL.json");
        loadCSV("src\\test\\resources\\TestData\\usersData.csv");
        new P02_LoginPage(getDriver()).clickRegister();
        new P01_RegisterationPage(getDriver()).enterFirstName(getUserData("user_1", "firstName"))
                .enterLastName(getUserData("user_1", "lastName"))
                .enterAddress(getUserData("user_1", "address"))
                .enterCity(getUserData("user_1", "city"))
                .enterState(getUserData("user_1", "state"))
                .enterZipCode(getUserData("user_1", "zipCode"))
                .enterPhoneNumber(getUserData("user_1", "phoneNumber"))
                .enterSSN(getUserData("user_1", "ssn"))
                .enterUsername(getUserData("user_1", "username"))
                .enterPassword(getUserData("user_1", "password"))
                .enterConfirmPassword(getUserData("user_1", "confirmPassword"))
                .clickRegisterButton();

        //Capture ScreenShot
        captureScreenshot(getDriver(), "Registeration_Page_screenShot");

        //Soft_Assertion of Message and URL
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                new P01_RegisterationPage(getDriver()).isSuccessfulRegister(getValue("RegisteredSucccess")),
               "❌ Welcome message mismatch!"
        );
        //Hard_Assertion With Login
        Assert.assertTrue(
                new P01_RegisterationPage(getDriver()).isRegistrationSuccessful(getValue("registerationURL")),
                "❌ Registration did not redirect to expected URL!"
        );

    }


}
