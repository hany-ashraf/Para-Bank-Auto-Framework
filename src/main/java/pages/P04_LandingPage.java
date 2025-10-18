package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class P04_LandingPage extends PageBase {

    public P04_LandingPage(WebDriver driver) {
        super(driver);
    }

    // ==================== LOCATORS ====================

    // Welcome Message
    private final By welcomeMessage = By.xpath("//p[@class='smallText']");

    // Left Panel Account Services
    private final By accountServicesHeader = By.xpath("//h2[text()='Account Services']");
    private final By openNewAccountLink = By.xpath("//a[contains(@href,'openaccount.htm')]");
    private final By accountsOverviewLink = By.xpath("//a[contains(@href,'overview.htm')]");
    private final By transferFundsLink = By.xpath("//a[contains(@href,'transfer.htm')]");
    private final By billPayLink = By.xpath("//a[contains(@href,'billpay.htm')]");
    private final By findTransactionsLink = By.xpath("//a[contains(@href,'findtrans.htm')]");
    private final By updateContactInfoLink = By.xpath("//a[contains(@href,'updateprofile.htm')]");
    private final By requestLoanLink = By.xpath("//a[contains(@href,'requestloan.htm')]");
    private final By logOutLink = By.xpath("//a[contains(@href,'logout.htm')]");
    private final By accountServicesMenu = By.xpath("//ul[li/a[contains(@href,'openaccount.htm')]]");

    // Open Account Form Elements
    private final By openAccountForm = By.id("openAccountForm");
    private final By formTitle = By.xpath("//h1[@class='title' and text()='Open New Account']");
    private final By accountTypeQuestion = By.xpath("//p[b[text()='What type of Account would you like to open?']]");
    private final By accountTypeDropdown = By.xpath("//p//following-sibling::select[@id=\"type\"]");
    private final By minimumDepositMessage = By.xpath("//p[contains(text(),'A minimum of $100.00')]");
    //changed this loactor
    private final By fromAccDropdown = By.xpath("//div/select[@id=\"fromAccountId\"]");
    private final By fromAccountDropdown = By.xpath("(//form/p)[2]/following-sibling::select[@id=\"fromAccountId\"]");

    private final By openNewAccountButton = By.xpath("//input[@type='button' and @value='Open New Account']");
    private final By clickNewAcc = By.xpath("//div/input[@value=\"Open New Account\"]");

    // Account Type Options
    private final By checkingOption = By.xpath("//option[@value='0' and text()='CHECKING']");
    private final By savingsOption = By.xpath("//option[@value='1' and text()='SAVINGS']");

    //Account number Locator
    private final By accountNum = By.xpath("//b//following-sibling::a[@id=\"newAccountId\"]");

    //Transaction Locators
    private final By fromAccount = By.xpath("//select[@id=\"fromAccountId\"]");
    private final By inputAmount = By.xpath("//input[@id=\"amount\"]");
    private final By submitTransaction = By.xpath("//input[@type=\"submit\"]");
    private final By transactionComplete = By.xpath("//div/h1[text()='Transfer Complete!']");

    //Update Contact Info
    private final By phoneNumber = By.xpath("//td/input[@id=\"customer.phoneNumber\"]");
    private final By updateProfile = By.xpath("//td/input[@value=\"Update Profile\"]");
    private final By updatedMessage = By.xpath("//td/input[@value=\"Update Profile\"]");
    private final By accountTableRows = By.xpath("//table[@id='accountTable']/tbody/tr[not(contains(td, 'Total'))]");

    // ==================== ACTION METHODS ====================

    // Account Services Navigation
    public P04_LandingPage clickOpenNewAccount() {
        longWait(ExpectedConditions.elementToBeClickable(this.openNewAccountLink)).click();
        return this;
    }

    public P04_LandingPage clickAccountsOverview() {
        longWait(ExpectedConditions.elementToBeClickable(this.accountsOverviewLink)).click();
        return this;
    }

    public P04_LandingPage clickTransferFunds() {
        longWait(ExpectedConditions.elementToBeClickable(this.transferFundsLink)).click();
        return this;
    }

    public P04_LandingPage clickBillPay() {
        longWait(ExpectedConditions.elementToBeClickable(this.billPayLink)).click();
        return this;
    }

    public P04_LandingPage clickFindTransactions() {
        longWait(ExpectedConditions.elementToBeClickable(this.findTransactionsLink)).click();
        return this;
    }

    public P04_LandingPage clickUpdateContactInfo() {
        longWait(ExpectedConditions.elementToBeClickable(this.updateContactInfoLink)).click();
        return this;
    }

    public P04_LandingPage clickRequestLoan() {
        longWait(ExpectedConditions.elementToBeClickable(this.requestLoanLink)).click();
        return this;
    }

    public P04_LandingPage clickLogOut() {
        longWait(ExpectedConditions.elementToBeClickable(this.logOutLink)).click();
        return this;
    }

    // Open Account Form Actions
    public P04_LandingPage selectAccountType(String accountType) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.accountTypeDropdown));
        Select accountTypeSelect = new Select(driver.findElement(this.accountTypeDropdown));
        accountTypeSelect.selectByVisibleText(accountType);
        return this;
    }

    public P04_LandingPage selectCheckingAccount() {
        return selectAccountType("CHECKING");
    }

    public P04_LandingPage selectSavingsAccount() {
        return selectAccountType("SAVINGS");
    }

    public P04_LandingPage selectAccountTypeByValue(String value) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.accountTypeDropdown));
        Select accountTypeSelect = new Select(driver.findElement(this.accountTypeDropdown));
        accountTypeSelect.selectByValue(value);
        return this;
    }
    public P04_LandingPage selectFromAccount(String accountId) {
        System.out.println("accountId without trim : [" + accountId + "]");
        String cleanAccountId = accountId.trim().replaceAll("[^0-9]", "");
        System.out.println("accountId cleaned : [" + cleanAccountId + "]");

        // Wait until the dropdown is visible
        longWait(ExpectedConditions.visibilityOfElementLocated(this.fromAccountDropdown));

        Select fromAccountSelect = new Select(driver.findElement(this.fromAccountDropdown));

        try {
            // Wait until the desired option is present in the dropdown
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//select[@id='fromAccountId']/option[@value='" + cleanAccountId + "']"))
            );

            // Select by value
            fromAccountSelect.selectByValue(cleanAccountId);
            System.out.println("✅ Selected accountId: " + cleanAccountId);

        } catch (NoSuchElementException e) {
            System.out.println("❌ Option with value [" + cleanAccountId + "] not found. Available options:");
            // Print all available options for debugging
            for (WebElement opt : fromAccountSelect.getOptions()) {
                System.out.println(" -> value=" + opt.getAttribute("value") + " | text=" + opt.getText());
            }
            throw e; // rethrow so the test fails
        }

        return this;
    }



    public P04_LandingPage clickNewAccButton(){
        longWait(ExpectedConditions.elementToBeClickable(this.clickNewAcc)).click();
        return this;
    }

    public String getAccountNum(){
        System.out.println(driver.findElement(this.accountNum).getText());
        return longWait(ExpectedConditions.elementToBeClickable(this.accountNum)).getText();
    }

    public P04_LandingPage selectFromAccountByValue(String accountId) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.fromAccountDropdown));
        Select fromAccountSelect = new Select(driver.findElement(this.fromAccountDropdown));
        fromAccountSelect.selectByValue(accountId);
        return this;
    }

    public P04_LandingPage selectFromAccountByIndex(int index) {
        shortWait(ExpectedConditions.visibilityOfElementLocated(this.fromAccountDropdown));
        Select fromAccountSelect = new Select(driver.findElement(this.fromAccountDropdown));
        fromAccountSelect.selectByIndex(index);
        return this;
    }

    public P04_LandingPage clickOpenNewAccountButton() {
        longWait(ExpectedConditions.elementToBeClickable(this.openNewAccountButton)).click();
        return this;
    }

    public P04_LandingPage selectByValue(String value) {

        System.out.println("accountId without trim : [" + value + "]");
        String cleanAccountId = value.trim().replaceAll("[^0-9]", "");
        System.out.println("accountId cleaned : [" + cleanAccountId + "]");

        // Wait until the dropdown is visible
        longWait(ExpectedConditions.visibilityOfElementLocated(this.fromAccDropdown));

        Select fromAccountSelect = new Select(driver.findElement(this.fromAccDropdown));

        try {
            // Wait until the desired option is present in the dropdown
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//select[@id='fromAccountId']/option[@value='" + cleanAccountId + "']"))
            );

            // Select by value
            fromAccountSelect.selectByValue(cleanAccountId);
            System.out.println("✅ Selected accountId: " + cleanAccountId);

        } catch (NoSuchElementException e) {
            System.out.println("❌ Option with value [" + cleanAccountId + "] not found. Available options:");
            // Print all available options for debugging
            for (WebElement opt : fromAccountSelect.getOptions()) {
                System.out.println(" -> value=" + opt.getAttribute("value") + " | text=" + opt.getText());
            }
            throw e; // rethrow so the test fails
        }

        return this;
    }

    public P04_LandingPage sendAmount(String amount){
        longWait(ExpectedConditions.elementToBeClickable(this.inputAmount)).sendKeys(amount);
        return this;
    }
    public P04_LandingPage clickSubmitTransaction(){
        longWait(ExpectedConditions.elementToBeClickable(this.submitTransaction)).click();
        return this;
    }

    public P04_LandingPage updatePhoneNumber(String phone){
        longWait(ExpectedConditions.elementToBeClickable(this.phoneNumber)).sendKeys(phone);
        return this;
    }
    public P04_LandingPage clickUpdateProfile(){
        longWait(ExpectedConditions.elementToBeClickable(this.updateProfile)).click();
        return this;
    }

    // URL and Message Assertions
    public boolean isAccountNum(String accNum) {
        System.out.println("Accoutn NUmbbbber : " + accNum);
        String cleanAccountNum = accNum.trim().replaceAll("[^0-9]", "");
        System.out.println("Expected accountNum: [" + cleanAccountNum + "]");

        // Wait for the account number element to be visible
        longWait(ExpectedConditions.visibilityOfElementLocated(accountNum));

        String actualAccountNum = driver.findElement(accountNum).getText().trim().replaceAll("[^0-9]", "");
        System.out.println("Actual accountId from UI: [" + actualAccountNum + "]");

        boolean result = cleanAccountNum.equals(actualAccountNum);
        if (result) {
            System.out.println("✅ Account number matched: " + actualAccountNum);
        } else {
            System.out.println("❌ Account number mismatch. Expected [" + cleanAccountNum + "] but found [" + actualAccountNum + "]");
        }

        return result;
    }

    public boolean isTransactionDone(String message) {
        return message.equals(driver.findElement(transactionComplete).getText());
    }

    public boolean isTransactionSuccessful(String expectedSuccessUrl) {
        return expectedSuccessUrl.equals(driver.getCurrentUrl());
    }
    public boolean isUpdatedContactInfoMessage(String message) {
        return message.equals(driver.findElement(updatedMessage).getText());
    }
    public boolean isUpdatedSuccessURL(String expectedSuccessUrl) {
        return expectedSuccessUrl.equals(driver.getCurrentUrl());
    }
    // Extract account IDs from the table
    public List<String> getAccountIds() {
        List<String> accountIds = new ArrayList<>();
        try {
            List<WebElement> rows = driver.findElements(accountTableRows);
            for (WebElement row : rows) {
                WebElement link = row.findElement(By.tagName("a"));
                accountIds.add(link.getText());
            }
        } catch (Exception e) {
            // Return empty list
        }
        return accountIds;
    }
    public boolean isAccountIdInTable(String accountId) {
        List<String> accountIds = getAccountIds();
        return accountIds.contains(accountId);
    }
}