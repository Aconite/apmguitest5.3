package com.aconite.apm.gui.automation.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage
{
    private WebDriver webDriver;
    private String url;

    @FindBy (id="username")
    WebElement txtUsername;

    @FindBy (id="password")
    WebElement txtPassword;

    @FindBy (id="login-button")
    WebElement btnLogin;

    public LoginPage(String url, WebDriver webDriver) throws Exception
    {
        try {
            this.webDriver = webDriver;
            PageFactory.initElements(webDriver, this);
            this.url = url;
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    public void goToLoginPage ()
    {
//        System.out.println(url);
        webDriver.navigate().to(url);
    }

    public void goToHomePage ()
    {
        webDriver.findElement(By.className("aconite-logo")).click();
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.className("duck-splash"))));

    }

    public void isLoginPageDisplayed() throws Exception
    {
        if (!webDriver.getTitle().equals("APM - Login"))
        {
            throw new Exception("Login Page not displayed");
        }
    }

    public void enterCredentials (String username, String password)
    {
        txtUsername.clear();
        txtUsername.sendKeys(username);
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickLogin ()
    {
        btnLogin.click();
    }

    public void isLoginSuccessful() throws Exception
    {
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("default-component"))));

        if (!webDriver.getTitle().equals("APM"))
        {
            throw new Exception("Login not successful");
        }
    }

    public void login(String username, String password) throws Exception
    {
        goToLoginPage();
        isLoginPageDisplayed();
        enterCredentials(username, password);
        clickLogin();
        isLoginSuccessful();
    }
}
