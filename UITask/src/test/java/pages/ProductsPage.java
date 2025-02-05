package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize explicit wait
    }

    // Locators (ADJUST THESE BASED ON SAUCE DEMO WEBSITE)
    private By productItems = By.className("inventory_item"); // Example: Class for individual product containers
    private By addToCartButton = By.xpath("//button[text()='Add to cart']"); // Example: Add to cart button
    private By cartButton = By.id("shopping_cart_container"); // Example: Cart button
    private By productPrice = By.className("inventory_item_price"); // Example: product price
    private By productName = By.className("inventory_item_name"); // Example: product name



    public void addProductToCart(int index) {
        List<WebElement> products = driver.findElements(productItems);
        if (index >= 0 && index < products.size()) {
            WebElement product = products.get(index);
            product.findElement(addToCartButton).click();
        } else {
            System.out.println("Invalid product index.");
        }
    }

    public void addMostExpensiveItems(int count) {
        List<WebElement> products = driver.findElements(productItems);
        List<Double> prices = new ArrayList<>();

        for (WebElement product : products) {
            String priceText = product.findElement(productPrice).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }

        // Sort prices in descending order
        prices.sort((p1, p2) -> Double.compare(p2, p1));

        for (int i = 0; i < Math.min(count, prices.size()); i++) {
            double highestPrice = prices.get(i);
            for (WebElement product : products) {
                String priceText = product.findElement(productPrice).getText().replace("$", "");
                double price = Double.parseDouble(priceText);
                if (price == highestPrice) {
                    product.findElement(addToCartButton).click();
                    break; // Avoid adding the same product multiple times
                }
            }
        }
    }


    public void clickCartButton() {
        driver.findElement(cartButton).click();
    }

    public boolean isProductPageDisplayed() {
        // Add a suitable check to determine if you are on the product page.
        // For example, you could check for the presence of a specific element.
        return driver.getCurrentUrl().contains("/inventory.html"); // Example check
    }

    public void addProductToCartByName(String productName) {
        List<WebElement> products = driver.findElements(productItems);
        for (WebElement product : products) {
            String name = product.findElement(this.productName).getText();
            if (name.equals(productName)) {
                product.findElement(addToCartButton).click();
                return; // Exit the loop once the product is found and added
            }
        }
        System.out.println("Product with name '" + productName + "' not found."); // Handle product not found
    }


    // ... other methods for interacting with products page elements
}