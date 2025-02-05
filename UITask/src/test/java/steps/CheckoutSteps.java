package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.utils.ConfigReader;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CheckoutSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("I have added products to the cart and am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        String browser = ConfigReader.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update path
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "path/to/geckodriver"); // Update path
            driver = new FirefoxDriver();
        }
        driver.get(ConfigReader.getProperty("url"));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Login
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();

        // Add products (example - adapt as needed)
        productsPage.addMostExpensiveItems(2);
        productsPage.clickCartButton();
        cartPage.clickCheckoutButton();


    }


    @When("I fill in the checkout information with valid data")
    public void iFillInTheCheckoutInformation() {
        checkoutPage.enterFirstName("John"); // Replace with test data or data-driven approach
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
    }

    @And("I click the continue button")
    public void iClickTheContinueButton() {
        checkoutPage.clickContinue();
    }

    @Then("I should be on the overview page")
    public void iShouldBeOnTheOverviewPage() {
        Assert.assertTrue(checkoutPage.isOverviewPageDisplayed());
        Assert.assertTrue(checkoutPage.getOverviewURL().contains("checkout-step-two.html"));
    }

    @And("I click the finish button")
    public void iClickTheFinishButton() {
        checkoutPage.clickFinish();
    }

    @Then("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        // Assertions to verify successful order placement (e.g., thank you message)
    }


    @When("I enter invalid first name {string} and last name {string} and postal code {string}")
    public void iEnterInvalidFirstNameLastNamePostalCode(String firstName, String lastName, String postalCode) {
        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostalCode(postalCode);
    }

    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String errorMessage) {
        // Assertions to verify error message
    }
}