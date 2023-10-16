package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.TokenProductGroupsPage;
import com.aconite.apm.gui.automation.webpages.TokenProductsPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PermissionsTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    AdminCommon adminCommon;

    TokenProductsPage tokenProductsPage;
    TokenProductGroupsPage tokenProductGroupsPage;
    HomePage homePage;

    public PermissionsTestSteps(AbstractSteps abstractSteps) {
        try {

            webDriver = abstractSteps.getDriver();
            adminCommon = new AdminCommon(webDriver);

            tokenProductsPage = new TokenProductsPage(webDriver);
            tokenProductGroupsPage = new TokenProductGroupsPage(webDriver);
            homePage = new HomePage(webDriver);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Then("I check the user can not access other pages")
    public void checkPagesForUser(String userName){

    }


    @Then("Check Permissions scenario")
    public void check_permissions_scenario() {
        softAssert.assertAll();
    }



}
