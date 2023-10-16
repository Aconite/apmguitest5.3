package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.ManageTokensPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class ManageTokensTestSteps {

    public WebDriver webDriver;
    public SoftAssert softAssert = new SoftAssert();

    ManageTokensPage manageTokensPage;

    AdminCommon adminCommon;

    public ManageTokensTestSteps(AbstractSteps abstractSteps)
    {

        this.webDriver = abstractSteps.getDriver();

        manageTokensPage = new ManageTokensPage(webDriver);
        adminCommon = new AdminCommon(webDriver);

    }

    @Then("I click on the Tokens Menu Manage Tokens")
    public void clickTokensMenuManageTokens() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnTokensManageTokensMenuItem();

            Logging.stepName("Click On Tokens Menu Manage Tokens");

            if(manageTokensPage.isPageOpened()){
                Logging.passMessage("Logged in and on Tokens Menu Manage Tokens Page");
            }
            else{
                Logging.failMessage("Not logged in and on Tokens Menu Manage Tokens Page");
                softAssert.fail("Not logged in and on Tokens Menu Manage Tokens Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }



}
