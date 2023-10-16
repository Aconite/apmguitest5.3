package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.webpages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;


public class LoginTestSteps
{
    LoginPage loginPage = null;

    private AbstractSteps abstractSteps;
    public WebDriver webDriver = null;

    public LoginTestSteps(AbstractSteps abstractSteps)
    {
        this.abstractSteps = abstractSteps;
        webDriver = abstractSteps.getDriver();
    }

    @Given("that i am on the APM login page")
    public void that_i_am_on_the_apm_login_page() throws Exception
    {
        loginPage.goToLoginPage();
        loginPage.isLoginPageDisplayed();
    }

    @Then("i am successfully logged in")
    public void i_am_successfully_logged_in() throws Exception
    {
        loginPage.isLoginSuccessful();
    }

    @Given("I am logged in as \"([^\"]*)\"$")
    public void i_am_logged_in_as(String login) {

        try {

            String[] arrLogin = login.split("/");
            String userName = arrLogin[0];
            String pwd = arrLogin[1];

            LoginPage loginPage = new LoginPage(abstractSteps.getBaseUrl(), webDriver);
            loginPage.login(userName, pwd);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
