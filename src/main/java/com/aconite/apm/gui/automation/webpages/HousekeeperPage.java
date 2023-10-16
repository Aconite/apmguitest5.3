package com.aconite.apm.gui.automation.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HousekeeperPage {

    WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "institutionId")
    WebElement selInstitution;

    @FindBy(className = "modal-title")
    WebElement btnModalTitle;

    @FindBy(id = "btn_0")
    WebElement btnModalCancel;

    @FindBy(id = "btn_1")
    WebElement btnModalConfirm;

//    @FindBy(id = "col-exe-Token-clean-up")
//    WebElement cellRunTokenCleanUp;
//
//    @FindBy(id = "col-exe-Transaction-history-clean-up")
//    WebElement cellRunTransactionCleanUp;
//
//    @FindBy(id = "col-exe-Unused-PIN/password-clean-up")
//    WebElement cellRunUnusedPinPasswordCleanUp;


    public HousekeeperPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);

    }

    public boolean isPageOpened ()
    {
        return(pageTitle.getText().equalsIgnoreCase("Housekeeper"));
    }

    public boolean clickRunTaskCleanUp(String institution){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(selInstitution));

        Select selInstitutionList = new Select(webDriver.findElement(By.id("institutionId")));
        selInstitutionList.selectByVisibleText(institution);

        WebElement exeButton = webDriver.findElement(By.xpath("(//td[@class='col-exe'])[1]/div/span"));
        exeButton.click();

        myWait.until(ExpectedConditions.visibilityOf(btnModalTitle));

        List<WebElement> btnConfirmList = webDriver.findElements(By.id("btn_1"));
        if(btnConfirmList.size()>0){
            WebElement btnConfirm = webDriver.findElement(By.id("btn_1"));
            String btnDisabled = btnConfirm.getAttribute("disabled");
            System.out.println("btnDisabled = " + btnDisabled);
            if(btnDisabled == null || btnDisabled.equalsIgnoreCase("null")){
                btnDisabled = "false";
            }
            if(btnDisabled.equalsIgnoreCase("false")){
                btnConfirm.click();
            }
            else{
                return false;
            }
        }

        adminCommon.hardWait(10000);

//        String lastRunAfter = webDriver.findElement(By.xpath("//td[@class='col-exe'][0]/div/span")).getAttribute("textContent");
//        if(lastRunBefore.equalsIgnoreCase(lastRunAfter)){
//            return("ERROR>Run for " + taskName + " has not updated application.");
//        }

        return true;
    }

    public boolean clickRunTransactionHistoryCleanUp(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(selInstitution));

        WebElement exeButton = webDriver.findElement(By.xpath("(//td[@class='col-exe'])[2]/div/span"));
        exeButton.click();

        myWait.until(ExpectedConditions.visibilityOf(btnModalTitle));

        List<WebElement> btnConfirmList = webDriver.findElements(By.id("btn_1"));
        if(btnConfirmList.size()>0){
            WebElement btnConfirm = webDriver.findElement(By.id("btn_1"));
            String btnDisabled = btnConfirm.getAttribute("disabled");
            System.out.println("btnDisabled = " + btnDisabled);
            if(btnDisabled == null || btnDisabled.equalsIgnoreCase("null")){
                btnDisabled = "false";
            }
            if(btnDisabled.equalsIgnoreCase("false")){
                btnConfirm.click();
            }
            else{
                return false;
            }
        }

        adminCommon.hardWait(10000);

//        String lastRunAfter = webDriver.findElement(By.xpath("//td[@class='col-exe'][0]/div/span")).getAttribute("textContent");
//        if(lastRunBefore.equalsIgnoreCase(lastRunAfter)){
//            return("ERROR>Run for " + taskName + " has not updated application.");
//        }

        return true;
    }

    public boolean clickRunUnusedPinPasswordCleanUp(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
//        myWait.until(ExpectedConditions.visibilityOf(selInstitution));

        WebElement exeButton = webDriver.findElement(By.xpath("(//td[@class='col-exe'])[3]/div/span"));
        exeButton.click();

        myWait.until(ExpectedConditions.visibilityOf(btnModalTitle));

        List<WebElement> btnConfirmList = webDriver.findElements(By.id("btn_1"));
        if(btnConfirmList.size()>0){
            WebElement btnConfirm = webDriver.findElement(By.id("btn_1"));
            String btnDisabled = btnConfirm.getAttribute("disabled");
            System.out.println("btnDisabled = " + btnDisabled);
            if(btnDisabled == null || btnDisabled.equalsIgnoreCase("null")){
                btnDisabled = "false";
            }
            if(btnDisabled.equalsIgnoreCase("false")){
                btnConfirm.click();
            }
            else{
                return false;
            }
        }

        adminCommon.hardWait(10000);

//        String lastRunAfter = webDriver.findElement(By.xpath("//td[@class='col-exe'][0]/div/span")).getAttribute("textContent");
//        if(lastRunBefore.equalsIgnoreCase(lastRunAfter)){
//            return("ERROR>Run for " + taskName + " has not updated application.");
//        }

        return true;
    }
}
