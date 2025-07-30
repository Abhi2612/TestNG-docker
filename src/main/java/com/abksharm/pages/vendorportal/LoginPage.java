package com.abksharm.pages.vendorportal;

import com.abksharm.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id="username")
    WebElement username;
    @FindBy(id="password")
    WebElement password;
    @FindBy(id="login")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.username));
        return this.username.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();
    }
}
