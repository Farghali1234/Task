package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement usernameField = driver.findElement(By.id("user-name"));
    private WebElement passwordField = driver.findElement(By.id("password"));
    private WebElement loginButton = driver.findElement(By.id("login-button"));
    private WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    // ... other methods for interacting with login page elements
}
