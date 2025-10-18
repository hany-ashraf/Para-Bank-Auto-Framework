package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P02_LoginPage extends PageBase {

    public P02_LoginPage(WebDriver driver) {
        super(driver);
    }

    // ==================== LOCATORS ====================

    // Login Form Elements
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//input[@type='submit' and @value='Log In']");
    private final By accountID = By.xpath("//tr//td/a");
    private final By forgetButton = By.xpath("//div/form/following-sibling::p/a[text()='Forgot login info?']");

    private final By registerButton = By.xpath("//div/form//following-sibling::p//following-sibling::p//a");

    //Login Successful Message
    private final By loginSuccessMessage = By.xpath("(//div/p)[2]");

    // ==================== ACTION METHODS ====================

    // Login Actions
    public P02_LoginPage enterUsername(String username) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.usernameField));
        driver.findElement(this.usernameField).clear();
        driver.findElement(this.usernameField).sendKeys(username);
        return this;
    }

    public P02_LoginPage enterPassword(String password) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.passwordField));
        driver.findElement(this.passwordField).clear();
        driver.findElement(this.passwordField).sendKeys(password);
        return this;
    }

    public P02_LoginPage clickLoginButton() {
        longWait(ExpectedConditions.elementToBeClickable(this.loginButton)).click();
        return this;
    }

    public P02_LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    public P02_LoginPage clickForgotLoginInfo() {
        longWait(ExpectedConditions.elementToBeClickable(this.forgetButton)).click();
        return this;
    }

    public P02_LoginPage clickRegister() {
        longWait(ExpectedConditions.elementToBeClickable(this.registerButton)).click();
        return this;
    }

    public String getAccountID() {
        // Wait until the element is visible or clickable before getting its text
        WebElement accountElement = longWait(ExpectedConditions.visibilityOfElementLocated(this.accountID));

        String accountText = accountElement.getText().trim();
        System.out.println("Account ID: " + accountText);

        return accountText;
    }



    // ==================== ASSERTION METHODS ====================


    // URL and Message Assertions
    public boolean isLoginSuccess(String message) {
        return message.equals(driver.findElement(loginSuccessMessage).getText());
    }

    public boolean isLoginSuccessful(String expectedSuccessUrl) {
        return expectedSuccessUrl.equals(driver.getCurrentUrl());
    }

}