package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators (ADJUST THESE BASED ON SAUCE DEMO WEBSITE)
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    // Overview Page Locators
    private By finishButton = By.id("finish");
    private By overviewPageTitle = By.xpath("//span[text()='Checkout: Overview']"); // Example XPath
    private By itemTotal = By.className("summary_subtotal_label");  // Example
    private By taxAmount = By.className("summary_tax_label"); // Example (May not be present on overview page)
    private By totalPrice = By.className("summary_total_label"); // Example
    private By overviewURL = By.xpath("//*[contains(text(),'checkout-step-two.html')]"); // Example XPath

    // Getters for verification
    public String getItemTotal() {
        return driver.findElement(itemTotal).getText();
    }

    public String getTaxAmount() {
        return driver.findElement(taxAmount).getText();
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    public String getOverviewURL() {
        return driver.getCurrentUrl();
    }


    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }


    public boolean isOverviewPageDisplayed() {
        return driver.findElement(overviewPageTitle).isDisplayed();
    }

}