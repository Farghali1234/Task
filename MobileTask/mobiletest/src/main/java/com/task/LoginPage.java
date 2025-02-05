package com.task;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private AppiumDriver driver;

    @AndroidFindBy(accessibility = "test-Username")
    private MobileElement usernameField;

    @AndroidFindBy(accessibility = "test-Password")
    private MobileElement passwordField;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'error')]")
    private MobileElement errorMessage;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isErrorDisplayed() {
        return errorMessage.isDisplayed();
    }
}