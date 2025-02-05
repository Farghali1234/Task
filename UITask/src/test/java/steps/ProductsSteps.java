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
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("I am logged in on the products page")
    public void iAmLoggedInOnTheProductsPage() {
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

        // Login (assuming valid credentials are in config file)
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();

        Assert.assertTrue(productsPage.isProductPageDisplayed());
    }

    @When("I add the two most expensive products to the cart")
    public void iAddTheTwoMostExpensiveProductsToTheCart() {
        productsPage.addMostExpensiveItems(2);
    }

    @And("I click on the cart button")
    public void iClickOnTheCartButton() {
        productsPage.clickCartButton();
    }

    @Then("I should see the products in the cart")
    public void iShouldSeeTheProductsInTheCart() {
        // Assertions to check if the products are in the cart.
        // This requires implementing CartPage and its methods.
    }


    @When("I add product at index {int} to the cart")
    public void iAddProductAtIndexToTheCart(int index) {
        productsPage.addProductToCart(index);
    }

    @Then("I should see the product in the cart")
    public void iShouldSeeTheProductInTheCart() {
        // Assertions to verify the product in the cart
    }
}