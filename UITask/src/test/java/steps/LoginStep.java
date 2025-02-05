package steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.utils.ConfigReader;
import pages.LoginPage;

public class LoginStep {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // Initialize driver (Chrome or Firefox based on config)
        String browser = ConfigReader.getProperty("browser");
        // ... browser setup code ...

        driver.get(ConfigReader.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    @When("I enter invalid username {string} and password {string}")
    public void iEnterInvalidCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void iClickLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeErrorMessage(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(expectedError, actualError);
    }
    
}