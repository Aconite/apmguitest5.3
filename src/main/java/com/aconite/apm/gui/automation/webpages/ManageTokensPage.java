package com.aconite.apm.gui.automation.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ManageTokensPage {

    WebDriver webDriver;
    public AdminCommon adminCommon;

    public

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-reset-filter")
    WebElement btnResetAll;

    @FindBy(id = "filter-institution")
    WebElement selInstitution;

    @FindBy(id = "filter-issuer")
    WebElement selIssuer;

    @FindBy(id = "filter-tokenProduct")
    WebElement selTokenProduct;

    @FindBy(id = "btn-save")
    WebElement btnSaveChanges;

    @FindBy(id = "btn-cancel-edit")
    WebElement btnCancel;

    @FindBy(id = "input-id")
    WebElement txtPanId;

    @FindBy(id = "input-status")
    WebElement selStatus;

    @FindBy(id = "input-tokenProduct")
    WebElement txtTokenProduct;

    @FindBy(id = "input-defaultTokenAppID")
    WebElement txtDefaultAppId;

    @FindBy(id = "input-customerCode")
    WebElement txtCustomerCode;




    public ManageTokensPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);

    }

    public boolean isPageOpened ()
    {
        return(pageTitle.getText().equalsIgnoreCase("Manage token applications"));
    }

    public List<String> getIds(int count){

        List<String> outlist = new ArrayList<>();
        List<WebElement> cellId = webDriver.findElements(By.xpath("//td[@class='col-id]"));


        for(int i=1;i<=count;i++){
            outlist.add(cellId.get(i).getAttribute("textContent"));
        }

        return outlist;
    }

    public boolean setTokenStatus(String id, String status){

        adminCommon.clickEditByUniqueId(id);

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));

        Select selStatusList = new Select(webDriver.findElement(By.id("input-status")));
        selStatusList.selectByVisibleText(status);

        btnSaveChanges.click();

        myWait.until(ExpectedConditions.visibilityOf(btnResetAll));

        WebElement cellStatus = webDriver.findElement(By.id("col-status" + id));
        if(cellStatus.getAttribute("textContent").equalsIgnoreCase(status)){
            return true;
        }
        else {
            return false;
        }

    }



}
