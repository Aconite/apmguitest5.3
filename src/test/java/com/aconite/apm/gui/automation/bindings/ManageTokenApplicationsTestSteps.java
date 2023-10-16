package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.ManageTokensPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class ManageTokenApplicationsTestSteps {

    public WebDriver webDriver;
    public SoftAssert softAssert = new SoftAssert();

    ManageTokensPage manageTokensPage;

    AdminCommon adminCommon;

    public ManageTokenApplicationsTestSteps(AbstractSteps abstractSteps)
    {

        this.webDriver = abstractSteps.getDriver();

        manageTokensPage = new ManageTokensPage(webDriver);
        adminCommon = new AdminCommon(webDriver);

    }

    @Then("I click on the Tokens Menu Manage Token Applications")
    public void clickTokensMenuManageTokenApplications() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnTokensManageTokenApplicationsMenuItem();

            Logging.stepName("Click On Tokens Menu Manage Token Applications");

            if(manageTokensPage.isPageOpened()){
                Logging.passMessage("Logged in and on Tokens Menu Manage Token Applications Page");
            }
            else{
                Logging.failMessage("Not logged in and on Tokens Menu Manage Token Applications Page");
                softAssert.fail("Not logged in and on Tokens Menu Manage Token Applications Page");

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
