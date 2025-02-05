package pages;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize explicit wait
    }

    // Locators (ADJUST THESE BASED ON SAUCE DEMO WEBSITE)
    private By cartItems = By.className("cart_item"); // Example: Class for individual cart item containers
    private By checkoutButton = By.id("checkout"); // Example: Checkout button
    private By cartItemName = By.className("inventory_item_name"); // Example: Cart item name
    private By cartItemPrice = By.className("inventory_item_price"); // Example: Cart item price
    private By cartQuantity = By.className("cart_quantity"); // Example: Cart item quantity
    private By removeButton = By.xpath("//button[text()='Remove']"); // Example: Remove button

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        driver.findElement(checkoutButton).click();
    }

    public List<String> getCartItemNames() {
        List<WebElement> items = driver.findElements(cartItems);
        List<String> names = new ArrayList<>();
        for (WebElement item : items) {
            names.add(item.findElement(cartItemName).getText());
        }
        return names;
    }

    public List<Double> getCartItemPrices() {
        List<WebElement> items = driver.findElements(cartItems);
        List<Double> prices = new ArrayList<>();
        for (WebElement item : items) {
            String priceText = item.findElement(cartItemPrice).getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public List<Integer> getCartItemQuantities() {
        List<WebElement> items = driver.findElements(cartItems);
        List<Integer> quantities = new ArrayList<>();
        for (WebElement item : items) {
            quantities.add(Integer.parseInt(item.findElement(cartQuantity).getText()));
        }
        return quantities;
    }

    public boolean isCartPageDisplayed() {
        // Add a suitable check to determine if you are on the cart page.
        // For example, you could check for the presence of a specific element.
        return driver.getCurrentUrl().contains("/cart.html"); // Example check
    }

    public void removeItem(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(cartItemName).getText();
            if (name.equals(productName)) {
                item.findElement(removeButton).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='" + productName + "']"))); // Wait for item to be removed.
                break;
            }
        }
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }
}