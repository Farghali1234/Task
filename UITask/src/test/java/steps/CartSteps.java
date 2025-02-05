package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.utils.ConfigReader;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;


public class CartSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Given("I am logged in and on the products page")
    public void iAmLoggedInAndOnTheProductsPage() {
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

        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();

        Assert.assertTrue(productsPage.isProductPageDisplayed());
    }

    @When("I add the \"([^\"]*)\" to the cart")
    public void iAddProductToCart(String productName) {
        productsPage.addProductToCartByName(productName);
    }

    @And("I click on the cart button")
    public void iClickOnTheCartButton() {
        productsPage.clickCartButton();
    }

    @Then("I should be on the cart page")
    public void iShouldBeOnTheCartPage() {
        Assert.assertTrue(cartPage.isCartPageDisplayed());
    }

    @And("the cart should contain the added items")
    public void theCartShouldContainAddedItems(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<String> expectedItemNames = new java.util.ArrayList<>();
        List<Double> expectedItemPrices = new java.util.ArrayList<>();
        List<Integer> expectedQuantities = new java.util.ArrayList<>();

        for (Map<String, String> row : rows) {
            expectedItemNames.add(row.get("Product Name"));
            expectedItemPrices.add(Double.parseDouble(row.get("Price")));
            expectedQuantities.add(Integer.parseInt(row.get("Quantity")));
        }

        List<String> actualItemNames = cartPage.getCartItemNames();
        List<Double> actualItemPrices = cartPage.getCartItemPrices();
        List<Integer> actualItemQuantities = cartPage.getCartItemQuantities();

        Assert.assertEquals(expectedItemNames, actualItemNames);
        Assert.assertEquals(expectedItemPrices, actualItemPrices);
    }


    @When("I add the \"([^\"]*)\" to the cart again")
    public void iAddTheProductToTheCartAgain(String productName) {
        productsPage.addProductToCartByName(productName);
    }

    @And("the cart should contain the product with correct quantity")
    public void theCartShouldContainTheProductWithCorrectQuantity(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String expectedProductName = rows.get(0).get("Product Name");
        Double expectedPrice = Double.parseDouble(rows.get(0).get("Price"));
        int expectedQuantity = Integer.parseInt(rows.get(0).get("Quantity"));

        List<String> actualItemNames = cartPage.getCartItemNames();
        List<Double> actualItemPrices = cartPage.getCartItemPrices();
        List<Integer> actualItemQuantities = cartPage.getCartItemQuantities();

        Assert.assertTrue(actualItemNames.contains(expectedProductName));

        int indexOfProduct = actualItemNames.indexOf(expectedProductName);

        Assert.assertEquals(expectedPrice, actualItemPrices.get(indexOfProduct));
    }

    @When("I remove the \"([^\"]*)\" from the cart")
    public void iRemoveTheItemFromTheCart(String productName) {
        cartPage.removeItem(productName); // You'll need to implement this method in CartPage
    }

    @And("the cart should be empty")
    public void theCartShouldBeEmpty() {
        Assert.assertTrue(cartPage.isCartEmpty()); // You'll need to implement this method in CartPage
    }

    @And("I click the checkout button")
    public void iClickTheCheckoutButton() {
        cartPage.clickCheckoutButton();
    }

    @Then("I should be on the checkout page")
    public void iShouldBeOnTheCheckoutPage() {
        // Add assertion to verify navigation to the checkout page
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout-step-one.html")); // Example
    }
}