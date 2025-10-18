package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P03_ResetAccountPag extends PageBase {

    public P03_ResetAccountPag(WebDriver driver) {
        super(driver);
    }

    // ==================== LOCATORS ====================

    // Customer Lookup Form Elements
    private final By firstNameField = By.id("firstName");
    private final By lastNameField = By.id("lastName");
    private final By addressField = By.id("address.street");
    private final By cityField = By.id("address.city");
    private final By stateField = By.id("address.state");
    private final By zipCodeField = By.id("address.zipCode");
    private final By ssnField = By.id("ssn");
    private final By findMyLoginInfoButton = By.xpath("//input[@type='submit' and @value='Find My Login Info']");

    private final By loginUsernameField = By.xpath("//form[@name='login']//input[@name='username']");
    private final By loginPasswordField = By.xpath("//form[@name='login']//input[@name='password']");
    private final By loginButton = By.xpath("//form[@name='login']//input[@type='submit']");

    private final By homeLink = By.xpath("//a[contains(@href,'index.htm')]");

    //Reset Message
    private final By resetMessage = By.xpath("//div/h1/following-sibling::p");

    // ==================== ACTION METHODS ====================

    // Customer Lookup Form Actions
    public P03_ResetAccountPag enterFirstName(String firstName) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.firstNameField));
        driver.findElement(this.firstNameField).clear();
        driver.findElement(this.firstNameField).sendKeys(firstName);
        return this;
    }

    public P03_ResetAccountPag enterLastName(String lastName) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.lastNameField));
        driver.findElement(this.lastNameField).clear();
        driver.findElement(this.lastNameField).sendKeys(lastName);
        return this;
    }

    public P03_ResetAccountPag enterAddress(String address) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.addressField));
        driver.findElement(this.addressField).clear();
        driver.findElement(this.addressField).sendKeys(address);
        return this;
    }

    public P03_ResetAccountPag enterCity(String city) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.cityField));
        driver.findElement(this.cityField).clear();
        driver.findElement(this.cityField).sendKeys(city);
        return this;
    }

    public P03_ResetAccountPag enterState(String state) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.stateField));
        driver.findElement(this.stateField).clear();
        driver.findElement(this.stateField).sendKeys(state);
        return this;
    }

    public P03_ResetAccountPag enterZipCode(String zipCode) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.zipCodeField));
        driver.findElement(this.zipCodeField).clear();
        driver.findElement(this.zipCodeField).sendKeys(zipCode);
        return this;
    }

    public P03_ResetAccountPag enterSSN(String ssn) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.ssnField));
        driver.findElement(this.ssnField).clear();
        driver.findElement(this.ssnField).sendKeys(ssn);
        return this;
    }

    public P03_ResetAccountPag clickFindMyLoginInfoButton() {
        longWait(ExpectedConditions.elementToBeClickable(this.findMyLoginInfoButton)).click();
        return this;
    }

    // Complete Lookup Methods
    public P03_ResetAccountPag fillLookupForm(String firstName, String lastName, String address,
                                              String city, String state, String zipCode, String ssn) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        enterSSN(ssn);
        return this;
    }


    public P03_ResetAccountPag clickHome() {
        longWait(ExpectedConditions.elementToBeClickable(this.homeLink)).click();
        return this;
    }


    // ==================== ASSERTION METHODS ====================

    // URL and Message Assertions
    public boolean isResetPassword(String message) {
        return message.equals(driver.findElement(resetMessage).getText());
    }

    public boolean isLookupSuccessful(String expectedSuccessUrl) {
        return expectedSuccessUrl.equals(driver.getCurrentUrl());
    }


}