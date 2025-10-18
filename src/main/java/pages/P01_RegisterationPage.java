package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P01_RegisterationPage extends PageBase {

    public P01_RegisterationPage(WebDriver driver) {
        super(driver);
    }

    // ==================== LOCATORS ====================

    // Registration Form Elements
    private final By registrationForm = By.id("customerForm");
    private final By firstNameField = By.id("customer.firstName");
    private final By lastNameField = By.id("customer.lastName");
    private final By addressField = By.id("customer.address.street");
    private final By cityField = By.id("customer.address.city");
    private final By stateField = By.id("customer.address.state");
    private final By zipCodeField = By.id("customer.address.zipCode");
    private final By phoneNumberField = By.id("customer.phoneNumber");
    private final By ssnField = By.id("customer.ssn");
    private final By usernameField = By.id("customer.username");
    private final By passwordField = By.id("customer.password");
    private final By confirmPasswordField = By.id("repeatedPassword");
    private final By registerButton = By.xpath("//input[@type='submit' and @value='Register']");

    //Registeration Successful  Message
    private final By successMessage = By.xpath("//div/h1/following-sibling::p");

    // ==================== ACTION METHODS ====================

    // Registration Form Actions
    public P01_RegisterationPage enterFirstName(String firstName) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.firstNameField));
        driver.findElement(this.firstNameField).clear();
        driver.findElement(this.firstNameField).sendKeys(firstName);
        return this;
    }

    public P01_RegisterationPage enterLastName(String lastName) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.lastNameField));
        driver.findElement(this.lastNameField).clear();
        driver.findElement(this.lastNameField).sendKeys(lastName);
        return this;
    }

    public P01_RegisterationPage enterAddress(String address) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.addressField));
        driver.findElement(this.addressField).clear();
        driver.findElement(this.addressField).sendKeys(address);
        return this;
    }

    public P01_RegisterationPage enterCity(String city) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.cityField));
        driver.findElement(this.cityField).clear();
        driver.findElement(this.cityField).sendKeys(city);
        return this;
    }

    public P01_RegisterationPage enterState(String state) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.stateField));
        driver.findElement(this.stateField).clear();
        driver.findElement(this.stateField).sendKeys(state);
        return this;
    }

    public P01_RegisterationPage enterZipCode(String zipCode) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.zipCodeField));
        driver.findElement(this.zipCodeField).clear();
        driver.findElement(this.zipCodeField).sendKeys(zipCode);
        return this;
    }

    public P01_RegisterationPage enterPhoneNumber(String phoneNumber) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.phoneNumberField));
        driver.findElement(this.phoneNumberField).clear();
        driver.findElement(this.phoneNumberField).sendKeys(phoneNumber);
        return this;
    }

    public P01_RegisterationPage enterSSN(String ssn) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.ssnField));
        driver.findElement(this.ssnField).clear();
        driver.findElement(this.ssnField).sendKeys(ssn);
        return this;
    }

    public P01_RegisterationPage enterUsername(String username) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.usernameField));
        driver.findElement(this.usernameField).clear();
        driver.findElement(this.usernameField).sendKeys(username);
        return this;
    }

    public P01_RegisterationPage enterPassword(String password) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.passwordField));
        driver.findElement(this.passwordField).clear();
        driver.findElement(this.passwordField).sendKeys(password);
        return this;
    }

    public P01_RegisterationPage enterConfirmPassword(String confirmPassword) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.confirmPasswordField));
        driver.findElement(this.confirmPasswordField).clear();
        driver.findElement(this.confirmPasswordField).sendKeys(confirmPassword);
        return this;
    }

    public P01_RegisterationPage clickRegisterButton() {
        longWait(ExpectedConditions.elementToBeClickable(this.registerButton)).click();
        return this;
    }

    // Complete Registration Method
    public P01_RegisterationPage fillRegistrationForm(String firstName, String lastName, String address,
                                                         String city, String state, String zipCode,
                                                         String phoneNumber, String ssn, String username,
                                                         String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        enterPhoneNumber(phoneNumber);
        enterSSN(ssn);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        return this;
    }

    public P01_RegisterationPage completeRegistration(String firstName, String lastName, String address,
                                                         String city, String state, String zipCode,
                                                         String phoneNumber, String ssn, String username,
                                                         String password, String confirmPassword) {
        fillRegistrationForm(firstName, lastName, address, city, state, zipCode,
                phoneNumber, ssn, username, password, confirmPassword);
        clickRegisterButton();
        return this;
    }


    // URL and Message Assertions
    public boolean isSuccessfulRegister(String message) {
        return message.equals(driver.findElement(successMessage).getText());
    }

    public boolean isRegistrationSuccessful(String expectedSuccessUrl) {
        return expectedSuccessUrl.equals(driver.getCurrentUrl());
    }

}