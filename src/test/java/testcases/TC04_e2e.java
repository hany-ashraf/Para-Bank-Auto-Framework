package testcases;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P02_LoginPage;
import pages.P03_ResetAccountPag;
import pages.P04_LandingPage;

import java.io.IOException;

import static drivers.DriverHolder.getDriver;
import static pages.PageBase.captureScreenshot;
import static util.Utility.*;

public class TC04_e2e extends TestBase {

    String phoneNum = "12355682";

    @Test(priority = 1, description = "Method to Open New Account")
    public void validateOpenNewAccount_P() throws IOException, ParseException {
        loadJsonData("src\\test\\resources\\TestData\\assertionURL.json");
        loadCSV("src\\test\\resources\\TestData\\usersData.csv");
        new P02_LoginPage(getDriver()).enterUsername(getUserData("user_1", "username"))
                .enterPassword(getUserData("user_1", "password"))
                .clickLoginButton();
        new P04_LandingPage(getDriver()).clickOpenNewAccount()
                .selectSavingsAccount()
                .selectFromAccount(getUserData("user_1","AccountID"))
                .clickNewAccButton();

        //get Account Number
        updateColumnValue("src\\test\\resources\\TestData\\usersData.csv",
                "user_1", "AccountNum", new P04_LandingPage(getDriver()).getAccountNum());

        //Assertion that Account Created using AccountNum from csv file in Account Overview
//        Assert.assertTrue(
//                new P04_LandingPage(getDriver()).isAccountNum(getUserData("user_1", "AccountNum")),
//                "❌ New Account Not Created !"
//        );
        //Capture ScreenShot
        captureScreenshot(getDriver(), "ResetPassword_Page_screenShot");

        //Hard_Assertion With Login
        Assert.assertTrue(
                new P03_ResetAccountPag(getDriver()).isLookupSuccessful(getValue("AccountOpen")),
                "❌ Reset Account did not redirect to expected URL!"
        );
    }

    @Test(priority = 2, description = "That method for amount of money")
    public void validateTransaction_P() throws IOException, ParseException {
        loadCSV("src\\test\\resources\\TestData\\usersData.csv");
        new P02_LoginPage(getDriver()).enterUsername(getUserData("user_1", "username"))
                .enterPassword(getUserData("user_1", "password"))
                .clickLoginButton();

        //Select your account
        new P04_LandingPage(getDriver()).clickTransferFunds().selectByValue(getUserData("user_1", "AccountNum"));
        //Select random account to send to transaction
        new P04_LandingPage(getDriver()).sendAmount(getRandomNumberAsString1to5());
        //Click Submit Transaction
        new P04_LandingPage(getDriver()).clickSubmitTransaction();

        loadJsonData("src\\test\\resources\\TestData\\testData.json");

        //Soft_Assertion of Message
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                new P04_LandingPage(getDriver()).isTransactionDone(getValue("Transaction")),
                "❌ Welcome message mismatch!"
        );
        loadJsonData("src\\test\\resources\\TestData\\assertionURL.json");
        //Hard_Assertion With Login
        Assert.assertTrue(
                new P04_LandingPage(getDriver()).isTransactionSuccessful(getValue("TrasactionComplete")),
                "❌ Reset Account did not redirect to expected URL!"
        );
    }

    @Test(priority = 3, description = "That method for Updating Contact Info")
    public void validateUpdateContactInfo_P() throws IOException, ParseException {
        loadJsonData("src\\test\\resources\\TestData\\assertionURL.json");
        loadCSV("src\\test\\resources\\TestData\\usersData.csv");
        new P02_LoginPage(getDriver()).enterUsername(getUserData("user_1", "username"))
                .enterPassword(getUserData("user_1", "password"))
                .clickLoginButton();

        //Click on Update Contact Info
        new P04_LandingPage(getDriver()).clickUpdateContactInfo();
        //Fill Data with Updated Info
        new P04_LandingPage(getDriver()).updatePhoneNumber(phoneNum).clickUpdateContactInfo();

        //Soft_Assertion of Message
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                new P04_LandingPage(getDriver()).isUpdatedContactInfoMessage(getValue("UpdatedProfile")),
                "❌ Welcome message mismatch!"
        );
        //Hard_Assertion With Login
        Assert.assertTrue(
                new P04_LandingPage(getDriver()).isUpdatedSuccessURL(getValue("UpdatedContactInfo")),
                "❌ Reset Account did not redirect to expected URL!"
        );
    }
    @Test(priority = 4, description = "That method Log out validation")
    public void validateLogoutFeature_P() throws IOException, ParseException {
        loadJsonData("src\\test\\resources\\TestData\\assertionURL.json");
        loadCSV("src\\test\\resources\\TestData\\usersData.csv");
        new P02_LoginPage(getDriver()).enterUsername(getUserData("user_1", "username"))
                .enterPassword(getUserData("user_1", "password"))
                .clickLoginButton();

        //Click on Logout Feature
        new P04_LandingPage(getDriver()).clickLogOut();
        //Hard_Assertion With Login
        Assert.assertTrue(
                new P04_LandingPage(getDriver()).isUpdatedSuccessURL(getValue("LogoutLink")),
                "❌ Reset Account did not redirect to expected URL!"
        );
    }
}
