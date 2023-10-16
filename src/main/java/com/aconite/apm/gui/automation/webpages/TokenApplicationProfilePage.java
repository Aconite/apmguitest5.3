package com.aconite.apm.gui.automation.webpages;


import com.aconite.apm.gui.automation.records.TokenApplicationProfileDataRecord;
import com.aconite.apm.gui.automation.testcases.ValidationTestcases;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


public class TokenApplicationProfilePage {

    public WebDriver webDriver;
    private final TokenProductsPage tokenProductsPage;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewTokenAppProfile;

    @FindBy(id = "btn-save")
    WebElement btnSaveChanges;

    @FindBy(id = "btn-cancel-edit")
    WebElement btnCancel;

    @FindBy (id="btn-pagination-last")
    WebElement btnLastPage;

    @FindBy (id="btn-pagination-first")
    WebElement btnFirstPage;

    @FindBy (id="btn-pagination-previous")
    WebElement btnPreviousPage;

    @FindBy (id="btn-pagination-next")
    WebElement btnNextPage;

    @FindBy (id="btn-pagination-count")
    WebElement txtPageCount;

    @FindBy (id="pagination-page")
    WebElement txtCurrentPage;

    @FindBy (id="pagination-size")
    WebElement txtPageSize;

    @FindBy(className = "modal-contents")
    WebElement modalDialog;

    @FindBy(id = "btn_0")
    WebElement btnModalNo;

    @FindBy(id = "btn_1")
    WebElement btnModalYes;

    @FindBy(className = "alert-dialog")
    WebElement alertDialog;

    @FindBy(id = "btn-ok")
    WebElement btnOK;

    @FindBy(id = "btn-reset-filter")
    WebElement btnReset;

    @FindBy(id = "input-institution")
    WebElement selInstitution;

    @FindBy(id = "val-institution")
    WebElement msgInstitution;

    @FindBy(id = "input-issuer")
    WebElement selIssuer;

    @FindBy(id = "val-issuer")
    WebElement msgIssuer;

    @FindBy(id = "input-tokenProduct")
    WebElement selTokenProduct;

    @FindBy(id = "val-tokenProduct")
    WebElement msgTokenProduct;

    @FindBy(id = "input-appSeqNum")
    WebElement txtAppSequenceNumber;

    @FindBy(id = "val-appSeqNum")
    WebElement msgAppSequenceNumber;

    @FindBy(id = "input-name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "input-status")
    WebElement selStatus;

    @FindBy(id = "val-status")
    WebElement msgStatus;

    @FindBy(id = "input-defaultApp")
    WebElement cbxDefaultApp;

    @FindBy(id = "btn-pinDetailsTab")
    WebElement btnPinDetails;

    @FindBy(id = "btn-pukDetailsTab")
    WebElement btnPukDetails;

    @FindBy(id = "btn-verificationTab")
    WebElement btnVerification;

    @FindBy(id = "btn-deliveryTab")
    WebElement btnDelivery;

    @FindBy(id = "btn-templatesTab")
    WebElement btnTemplates;

    @FindBy(id = "btn-smsTab")
    WebElement btnSms;

    @FindBy(id = "input-pinRequire")
    WebElement cbxPinRequired;

    @FindBy(id = "input-pinLength")
    WebElement txtPinLength;

    @FindBy(id = "val-pinLength")
    WebElement msgPinLength;

    @FindBy(id = "input-importEZ")
    WebElement selImportEncryptionZone;

    @FindBy(id = "val-importEZ")
    WebElement msgImportEncryptionZone;

    @FindBy(id = "input-exportEZ")
    WebElement selExportEncryptionZone;

    @FindBy(id = "val-exportEZ")
    WebElement msgExportEncryptionZone;

    @FindBy(id = "input-returnIntf")
    WebElement selReturnInterface;

    @FindBy(id = "input-pinMailerDelay")
    WebElement txtPinMailerDelay;

    @FindBy(id = "val-pinMailerDelay")
    WebElement msgPinMailerDelay;

    @FindBy(id = "input-usesPinOfTokenAppSeqNum")
    WebElement txtPinHeldBySequenceNumber;

    @FindBy(id = "input-allowPinChange")
    WebElement cbxAllowPinChange;

    @FindBy(id = "input-allowOnlinePinChange")
    WebElement cbxAllowOnlinePinChange;

    @FindBy(id = "input-allowOnlinePinView")
    WebElement cbxAllowOnlinePinView;

    @FindBy(id = "input-allowPinShared")
    WebElement cbxIndependentTokenPins;

    @FindBy(id = "input-pukRequire")
    WebElement cbxPukRequired;

    @FindBy(id = "input-pukLength")
    WebElement txtPukLength;

    @FindBy(id = "val-pukLength")
    WebElement msgPukLength;

    @FindBy(id = "input-allowPukChange")
    WebElement cbxAllowPukChange;

    @FindBy(id = "input-usesPukOfTokenAppSeqNum")
    WebElement txtPukHeldBySequenceNumber;

    @FindBy(id = "input-allowPukShared")
    WebElement cbxIndependentTokenPuks;


    @FindBy(id = "input-pinVerifyMethod")
    WebElement selPinVerificationMethod;

    @FindBy(id = "input-pinVerifyKey")
    WebElement selPinVerificationKey;

    @FindBy(id = "val-pinVerifyKey")
    WebElement msgPinVerificationKey;

    @FindBy(id = "input-pinTriesNum")
    WebElement txtPinTries;

    @FindBy(id = "input-verifyBackoff")
    WebElement txtVerificationBackoff;

    @FindBy(id = "input-backoffMultiplier")
    WebElement txtBackoffMultiplier;

    @FindBy(id = "input-importPinDeliveryMethod")
    WebElement selTokenImportPinDeliveryMethod;

    @FindBy(id = "input-importOutputInterface")
    WebElement selTokenImportOutputInterface;

    @FindBy(id = "input-orderPinDeliveryMethod")
    WebElement selTokenOrderPinDeliveryMethod;

    @FindBy(id = "input-orderForcePinDelivery")
    WebElement cbxForceTokenOrderPinDelivery;

    @FindBy(id = "input-orderOutputInterface")
    WebElement selTokenOrderOutputInterface;

    @FindBy(id = "input-advicePinDeliveryMethod")
    WebElement selPinAdvicePinDeliveryMethod;

    @FindBy(id = "input-adviceForcePinDelivery")
    WebElement cbxForcePinAdvicePinDelivery;

    @FindBy(id = "input-adviceOutputInterface")
    WebElement selPinAdviceOutputInterface;

    @FindBy(id = "input-updatePinDeliveryMethod")
    WebElement selUpdatePinDeliveryMethod;

    @FindBy(id = "input-updateOutputInterface")
    WebElement selUpdateOutputInterface;

    @FindBy(id = "input-vppPinDeliveryMethod")
    WebElement selVppPinDeliveryMethod;

    @FindBy(id = "input-vppOutputInterface")
    WebElement selVppOutputInterface;

    @FindBy(id = "input-pinSmsTemplate")
    WebElement selPinTemplate;

    @FindBy(id = "val-pinSmsTemplate")
    WebElement msgPinTemplate;

    @FindBy(id = "input-secondaryPinSmsTemplate")
    WebElement selSecondaryPinTemplate;

    @FindBy(id = "input-pukSmsTemplate")
    WebElement selPukTemplate;

    @FindBy(id = "val-pukSmsTemplate")
    WebElement msgPukTemplate;

    @FindBy(id = "input-passwdSmsTemplate")
    WebElement selPasswordTemplate;

    @FindBy(id = "val-passwdSmsTemplate")
    WebElement msgPasswordTemplate;

    @FindBy(id = "input-messageTemplate")
    WebElement selMessageTemplate;

    @FindBy(id = "input-messageInterface")
    WebElement selMessageInterface;

    @FindBy(id = "input-exportInterface")
    WebElement selSmsInterface;

    @FindBy(id = "input-pinOverSmsDelay")
    WebElement txtPinOverSmsDelayHours;

    @FindBy(id = "val-pinOverSmsDelay")
    WebElement msgPinOverSmsDelayHours;

    @FindBy(id = "input-smsPasswdDelay")
    WebElement txtSmsPasswordDelayHours;

    @FindBy(id = "val-smsPasswdDelay")
    WebElement msgSmsPasswordDelayHours;

    @FindBy(id = "input-smsSender")
    WebElement txtSmsSender;

    @FindBy(id = "val-smsSender")
    WebElement msgSmsSender;

    @FindBy(id = "input-smsValidity")
    WebElement txtSmsValidityHours;

    @FindBy(id = "val-smsValidity")
    WebElement msgSmsValidityHours;

    @FindBy(id = "input-smsPasswdExpiry")
    WebElement txtSmsPasswordExpiryHours;

    @FindBy(id = "val-smsPasswdExpiry")
    WebElement msgSmsPasswordExpiryHours;

    @FindBy(id = "input-smsClass")
    WebElement selSmsClass;

    @FindBy(id = "val-smsClass")
    WebElement msgSmsClass;

    public TokenApplicationProfilePage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        tokenProductsPage = new TokenProductsPage(webDriver);
        adminCommon = new AdminCommon(this.webDriver);
    }



    public boolean isPageOpened() { return(pageTitle.getText().equalsIgnoreCase("Token app profiles")); }

    public void clickAddTokenApplication(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenAppProfile));

        try {
        btnAddNewTokenAppProfile.click();
        }
        catch(ElementClickInterceptedException ignored){}
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

    }

    public void filterByRecord(TokenApplicationProfileDataRecord searchTokenApplicationProfile){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnReset));

        try {
            btnReset.click();
        }
        catch(ElementClickInterceptedException ignored){}

        Select selInstitutionFilter = new Select(webDriver.findElement(By.id("filter-institution")));
        try {
            selInstitutionFilter.selectByVisibleText(searchTokenApplicationProfile.getParentInstitution());
        }
        catch(StaleElementReferenceException ignored){}

        adminCommon.hardWait(500);
        Select selIssuerFilter = new Select(webDriver.findElement(By.id("filter-issuer")));
        try {
            selIssuerFilter.selectByVisibleText(searchTokenApplicationProfile.getParentIssuer());
        }
        catch(StaleElementReferenceException ignored){}
        adminCommon.hardWait(500);
        Select selTokenProductGroupFilter = new Select(webDriver.findElement(By.id("filter-tokenProductGroup")));
        try {
            selTokenProductGroupFilter.selectByVisibleText(searchTokenApplicationProfile.getParentTokenProductGroupName());
        }
        catch(StaleElementReferenceException ignored){}
        adminCommon.hardWait(500);
        Select selTokenProductFilter = new Select(webDriver.findElement(By.id("filter-tokenProduct")));
        try {
            selTokenProductFilter.selectByVisibleText(searchTokenApplicationProfile.getParentTokenProductName());
        }
        catch(StaleElementReferenceException ignored){}
        adminCommon.hardWait(500);

    }

    public boolean checkRequiredFieldMessages(String field, String message){

        boolean rc = true;

        switch(field) {

            case("Institution"):
                selInstitution.click();
                selInstitution.sendKeys(Keys.TAB);
                selInstitution.sendKeys(Keys.TAB);
                if (!msgInstitution.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Issuer"):
                selIssuer.click();
                selIssuer.sendKeys(Keys.TAB);
                selIssuer.sendKeys(Keys.TAB);
                if (!msgIssuer.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("TokenProduct"):
                selTokenProduct.click();
                selTokenProduct.sendKeys(Keys.TAB);
                selTokenProduct.sendKeys(Keys.TAB);
                if (!msgTokenProduct.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("AppSequenceNumber"):
                txtAppSequenceNumber.clear();
                txtAppSequenceNumber.click();
                txtAppSequenceNumber.sendKeys(Keys.TAB);
                if (!msgAppSequenceNumber.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Name"):
                txtName.clear();
                txtName.click();
                txtName.sendKeys(Keys.TAB);
                if (!msgName.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Status"):
                selStatus.click();
                selStatus.sendKeys(Keys.TAB);
                selStatus.sendKeys(Keys.TAB);
                if (!msgStatus.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("PinLength"):
                btnPinDetails.click();
                txtPinLength.click();
                txtPinLength.sendKeys(Keys.DELETE);
                txtPinLength.sendKeys(Keys.DELETE);
                txtPinLength.sendKeys(Keys.DELETE);
                txtPinLength.sendKeys(Keys.DELETE);
                txtPinLength.sendKeys(Keys.BACK_SPACE);
                txtPinLength.sendKeys(Keys.BACK_SPACE);
                txtPinLength.sendKeys(Keys.BACK_SPACE);
                txtPinLength.sendKeys(Keys.BACK_SPACE);
                txtPinLength.click();
                txtPinLength.sendKeys(Keys.TAB);
                if (!msgPinLength.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("ImportEncryptionZone"):
                btnPinDetails.click();
                selImportEncryptionZone.click();
                selImportEncryptionZone.sendKeys(Keys.TAB);
                selImportEncryptionZone.sendKeys(Keys.TAB);
                if (!msgImportEncryptionZone.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("ExportEncryptionZone"):
                btnPinDetails.click();
                selExportEncryptionZone.click();
                selExportEncryptionZone.sendKeys(Keys.TAB);
                selExportEncryptionZone.sendKeys(Keys.TAB);
                if (!msgExportEncryptionZone.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PinMailerDelayHours"):
                btnPinDetails.click();
                txtPinMailerDelay.click();
                txtPinMailerDelay.sendKeys(Keys.DELETE);
                txtPinMailerDelay.sendKeys(Keys.DELETE);
                txtPinMailerDelay.sendKeys(Keys.DELETE);
                txtPinMailerDelay.sendKeys(Keys.DELETE);
                txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                txtPinMailerDelay.click();
                txtPinMailerDelay.sendKeys(Keys.TAB);
                if (!msgPinMailerDelay.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PukLength"):
                btnPukDetails.click();
                adminCommon.selectCheckbox(cbxPukRequired,"true");
                txtPukLength.click();
                txtPukLength.sendKeys(Keys.DELETE);
                txtPukLength.sendKeys(Keys.DELETE);
                txtPukLength.sendKeys(Keys.DELETE);
                txtPukLength.sendKeys(Keys.DELETE);
                txtPukLength.sendKeys(Keys.BACK_SPACE);
                txtPukLength.sendKeys(Keys.BACK_SPACE);
                txtPukLength.sendKeys(Keys.BACK_SPACE);
                txtPukLength.sendKeys(Keys.BACK_SPACE);
                txtPukLength.click();
                txtPukLength.sendKeys(Keys.TAB);
                if (!msgPukLength.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PinVerificationKey"):
                btnVerification.click();
                selPinVerificationKey.click();
                selPinVerificationKey.sendKeys(Keys.TAB);
                selPinVerificationKey.sendKeys(Keys.TAB);
                if (!msgPinVerificationKey.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PinTemplate"):
                btnSms.click();
                selPinTemplate.click();
                selPinTemplate.sendKeys(Keys.TAB);
                selPinTemplate.sendKeys(Keys.TAB);
                if (!msgPinTemplate.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PukTemplate"):
                btnSms.click();
                selPukTemplate.click();
                selPukTemplate.sendKeys(Keys.TAB);
                selPukTemplate.sendKeys(Keys.TAB);
                if (!msgPukTemplate.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PasswordTemplate"):
                btnSms.click();
                selPasswordTemplate.click();
                selPasswordTemplate.sendKeys(Keys.TAB);
                selPasswordTemplate.sendKeys(Keys.TAB);
                if (!msgPasswordTemplate.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PinOverSMSDelayHours"):
                btnSms.click();
                txtPinOverSmsDelayHours.clear();
                txtPinOverSmsDelayHours.click();
                txtPinOverSmsDelayHours.sendKeys(Keys.TAB);
                if (!msgPinOverSmsDelayHours.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("SmsPasswordDelayHours"):
                btnSms.click();
                txtSmsPasswordDelayHours.clear();
                txtSmsPasswordDelayHours.click();
                txtSmsPasswordDelayHours.sendKeys(Keys.TAB);
                if (!msgSmsPasswordDelayHours.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("SmsSender"):
                btnSms.click();
                txtSmsSender.clear();
                txtSmsSender.click();
                txtSmsSender.sendKeys(Keys.TAB);
                if (!msgSmsSender.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("SmsValidityHours"):
                btnSms.click();
                txtSmsValidityHours.clear();
                txtSmsValidityHours.click();
                txtSmsValidityHours.sendKeys(Keys.TAB);
                if (!msgSmsValidityHours.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("SmsPasswordHours"):
                btnSms.click();
                txtSmsPasswordExpiryHours.clear();
                txtSmsPasswordExpiryHours.click();
                txtSmsPasswordExpiryHours.sendKeys(Keys.TAB);
                if (!msgSmsPasswordExpiryHours.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("SmsClass"):
                btnSms.click();
                selSmsClass.click();
                selSmsClass.sendKeys(Keys.TAB);
                selSmsClass.sendKeys(Keys.TAB);
                if (!msgSmsClass.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;
    }

    public TokenApplicationProfileDataRecord enterTokenApplicationProfileData(TokenApplicationProfileDataRecord newTokenApplicationProfile){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        TokenApplicationProfileDataRecord record = new TokenApplicationProfileDataRecord();

        Select selInstitutionList;
        Select selIssuerList;
        Select selTokenProductList;
        Select selStatusList;
        Select selImportEncryptionZoneList;
        Select selExportEncryptionZoneList;
        Select selPinVerificationKeyList;
        Select selPinTemplateList;
        Select selPukTemplateList;
        Select selPasswordTemplateList;
        Select selSmsClassList;

        clickAddTokenApplication();

        selInstitutionList = new Select(webDriver.findElement(By.id("input-institution")));
        selInstitutionList.selectByVisibleText(newTokenApplicationProfile.getParentInstitution());

        selIssuerList = new Select(webDriver.findElement(By.id("input-issuer")));
        selIssuerList.selectByVisibleText(newTokenApplicationProfile.getParentIssuer());

        selTokenProductList = new Select(webDriver.findElement(By.id("input-tokenProduct")));
        selTokenProductList.selectByVisibleText(newTokenApplicationProfile.getParentTokenProductName());

        txtAppSequenceNumber.clear();
        txtAppSequenceNumber.sendKeys(newTokenApplicationProfile.getAppSequenceNumber());

        txtName.clear();
        txtName.sendKeys(newTokenApplicationProfile.getName());

        selStatusList = new Select(webDriver.findElement(By.id("input-status")));
        selStatusList.selectByVisibleText(newTokenApplicationProfile.getStatus());

        btnPinDetails.click();

        txtPinLength.clear();
        txtPinLength.sendKeys(newTokenApplicationProfile.getPinLength());

        selImportEncryptionZoneList = new Select(webDriver.findElement(By.id("input-importEZ")));
        selImportEncryptionZoneList.selectByVisibleText(newTokenApplicationProfile.getImportEncryptionZone());

        selExportEncryptionZoneList = new Select(webDriver.findElement(By.id("input-exportEZ")));
        selExportEncryptionZoneList.selectByVisibleText(newTokenApplicationProfile.getExportEncryptionZone());

        txtPinMailerDelay.clear();
        txtPinMailerDelay.sendKeys(newTokenApplicationProfile.getPinMailerDelayHours());

        btnVerification.click();

        selPinVerificationKeyList = new Select(webDriver.findElement(By.id("input-pinVerifyKey")));
        selPinVerificationKeyList.selectByVisibleText(newTokenApplicationProfile.getPinVerificationKey());

        btnSms.click();

        selPinTemplateList = new Select(webDriver.findElement(By.id("input-pinSmsTemplate")));
        selPinTemplateList.selectByVisibleText(newTokenApplicationProfile.getPinTemplate());

        selPukTemplateList = new Select(webDriver.findElement(By.id("input-pukSmsTemplate")));
        selPukTemplateList.selectByVisibleText(newTokenApplicationProfile.getPukTemplate());

        selPasswordTemplateList = new Select(webDriver.findElement(By.id("input-passwdSmsTemplate")));
        selPasswordTemplateList.selectByVisibleText(newTokenApplicationProfile.getPasswordTemplate());


        txtPinOverSmsDelayHours.clear();
        txtPinOverSmsDelayHours.sendKeys(newTokenApplicationProfile.getPinOverSMSDelayHours());

        txtSmsPasswordDelayHours.clear();
        txtSmsPasswordDelayHours.sendKeys(newTokenApplicationProfile.getSmsPasswordExpiryHours());

        txtSmsSender.clear();
        txtSmsSender.sendKeys(newTokenApplicationProfile.getSmsSender());

        txtSmsValidityHours.clear();
        txtSmsValidityHours.sendKeys(newTokenApplicationProfile.getSmsValidityHours());

        txtSmsPasswordExpiryHours.clear();
        txtSmsPasswordExpiryHours.sendKeys(newTokenApplicationProfile.getSmsPasswordExpiryHours());

        selSmsClassList = new Select(webDriver.findElement(By.id("input-smsClass")));
        selSmsClassList.selectByVisibleText(newTokenApplicationProfile.getSmsClass());

        // Click Add button
        btnSaveChanges.click();
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenAppProfile));

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            record = newTokenApplicationProfile;
            record.setTestOutput("Add Token Application Profile: SUCCESSFUL");
        }
        else{

            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            record.setName(null);
            record.setParentInstitution(null);
            record.setParentIssuer(null);
            record.setAppSequenceNumber(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return record;

    }

    public TokenApplicationProfileDataRecord editTokenApplicationProfileData(TokenApplicationProfileDataRecord editTokenApplicationProfile, String field){

        Select selStatusList;
        Select selImportEncryptionZoneList;
        Select selExportEncryptionZoneList;
        Select selReturnInterfaceList;
        Select selPinVerificationMethodList;
        Select selPinVerificationKeyList;
        Select selTokenImportPinDeliveryMethodList;
        Select selTokenOrderPinDeliveryMethodList;
        Select selPinAdvicePinDeliveryMethodList;
        Select selUpdatePinDeliveryMethodList;
        Select selVppPinDeliveryMethodList;
        Select selPinTemplateList;
        Select selSecondaryPinTemplateList;
        Select selPukTemplateList;
        Select selPasswordTemplateList;
        Select selMessageTemplateList;
        Select selMessageInterfaceList;
        Select selSmsInterfaceList;
        Select selSmsClassList;

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);

        filterByRecord(editTokenApplicationProfile);

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editTokenApplicationProfile.getParentTokenProductId() + "_" + editTokenApplicationProfile.getAppSequenceNumber())) {

            adminCommon.sleep(1000);

            switch (field) {

                /* Currently, not done - name and token product code used to id token application profile */
                case ("Name"):
                    txtName.clear();
                    txtName.sendKeys(editTokenApplicationProfile.getName());
                    break;

                case ("Status"):
                    selStatusList = new Select(webDriver.findElement(By.id("input-status")));
                    selStatusList.selectByVisibleText(editTokenApplicationProfile.getStatus());
                    break;

                case ("DefaultApp"):
                    adminCommon.selectCheckbox(cbxDefaultApp, editTokenApplicationProfile.getDefaultApp());
                    break;

                case ("PinRequired"):
                    btnPinDetails.click();
                    adminCommon.selectCheckbox(cbxPinRequired, editTokenApplicationProfile.getPinRequired());
                    break;

                case ("PinLength"):
                    btnPinDetails.click();
                    txtPinLength.clear();
                    txtPinLength.sendKeys(editTokenApplicationProfile.getPinLength());
                    break;

                case ("ImportEncryptionZone"):
                    btnPinDetails.click();
                    selImportEncryptionZoneList = new Select(webDriver.findElement(By.id("input-importEZ")));
                    selImportEncryptionZoneList.selectByVisibleText(editTokenApplicationProfile.getImportEncryptionZone());
                    break;

                case ("ExportEncryptionZone"):
                    btnPinDetails.click();
                    selExportEncryptionZoneList = new Select(webDriver.findElement(By.id("input-exportEZ")));
                    selExportEncryptionZoneList.selectByVisibleText(editTokenApplicationProfile.getExportEncryptionZone());
                    break;

                case ("ReturnInterface"):
                    btnPinDetails.click();
                    selReturnInterfaceList = new Select(webDriver.findElement(By.id("input-returnIntf")));
                    selReturnInterfaceList.selectByVisibleText(editTokenApplicationProfile.getReturnInterface());
                    break;

                case ("PinMailerDelayHours"):
                    btnPinDetails.click();
                    txtPinMailerDelay.clear();
                    txtPinMailerDelay.sendKeys(editTokenApplicationProfile.getPinMailerDelayHours());
                    break;

                case ("PinHeldBySeqNum"):
                    btnPinDetails.click();
                    txtPinHeldBySequenceNumber.clear();
                    txtPinHeldBySequenceNumber.sendKeys(editTokenApplicationProfile.getPinHeldBySeqNum());
                    break;

                case ("AllowPinChange"):
                    btnPinDetails.click();
                    adminCommon.selectCheckbox(cbxAllowPinChange, editTokenApplicationProfile.getAllowPinChange());
                    break;

                case ("AllowOnlinePinChange"):
                    btnPinDetails.click();
                    adminCommon.selectCheckbox(cbxAllowOnlinePinChange, editTokenApplicationProfile.getAllowOnlinePinChange());
                    break;

                case ("IndependentTokenPins"):
                    btnPinDetails.click();
                    adminCommon.selectCheckbox(cbxIndependentTokenPins, editTokenApplicationProfile.getIndependentTokenPins());
                    break;

                case ("AllowOnlinePinView"):
                    btnPinDetails.click();
                    adminCommon.selectCheckbox(cbxAllowOnlinePinView, editTokenApplicationProfile.getAllowOnlinePinView());
                    break;

                case ("PukRequired"):
                    btnPukDetails.click();
                    adminCommon.selectCheckbox(cbxPukRequired, editTokenApplicationProfile.getPukRequired());
                    if (editTokenApplicationProfile.getPukRequired().equals("true")) {
                        txtPukLength.clear();
                        txtPukLength.sendKeys("5");
                        txtPukLength.sendKeys(Keys.TAB);
                        editTokenApplicationProfile.setPukLength("5");
                    }
                    break;

                case ("PukLength"):
                    btnPukDetails.click();
                    adminCommon.selectCheckbox(cbxPukRequired, "true");
                    txtPukLength.clear();
                    txtPukLength.sendKeys(editTokenApplicationProfile.getPukLength());
                    break;

                case ("AllowPukChange"):
                    btnPukDetails.click();
                    adminCommon.selectCheckbox(cbxAllowPukChange, editTokenApplicationProfile.getAllowPukChange());
                    break;

                case ("PukHeldBySeqNum"):
                    btnPukDetails.click();
                    txtPukHeldBySequenceNumber.clear();
                    txtPukHeldBySequenceNumber.sendKeys(editTokenApplicationProfile.getPukHeldBySeqNum());
                    break;

                case ("IndependentTokenPuks"):
                    btnPukDetails.click();
                    adminCommon.selectCheckbox(cbxIndependentTokenPuks, editTokenApplicationProfile.getIndependentTokenPuks());
                    break;

                case ("PinVerificationMethod"):
                    btnVerification.click();
                    selPinVerificationMethodList = new Select(webDriver.findElement(By.id("input-pinVerifyMethod")));
                    selPinVerificationMethodList.selectByVisibleText(editTokenApplicationProfile.getPinVerificationMethod());
                    break;

                case ("PinVerificationKey"):
                    btnVerification.click();
                    selPinVerificationKeyList = new Select(webDriver.findElement(By.id("input-pinVerifyKey")));
                    selPinVerificationKeyList.selectByVisibleText(editTokenApplicationProfile.getPinVerificationKey());
                    break;

                case ("PinTries"):
                    btnVerification.click();
                    txtPinTries.clear();
                    txtPinTries.sendKeys(editTokenApplicationProfile.getPinTries());
                    break;

                case ("VerificationBackoff"):
                    btnVerification.click();
                    txtVerificationBackoff.clear();
                    txtVerificationBackoff.sendKeys(editTokenApplicationProfile.getVerificationBackoff());
                    break;

                case ("BackoffMultiplier"):
                    btnVerification.click();
                    txtBackoffMultiplier.clear();
                    txtBackoffMultiplier.sendKeys(editTokenApplicationProfile.getBackoffMultiplier());
                    break;

                case ("TokenImportPinDeliveryMethod"):
                    btnDelivery.click();
                    selTokenImportPinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-importPinDeliveryMethod")));
                    selTokenImportPinDeliveryMethodList.selectByVisibleText(editTokenApplicationProfile.getTokenImportPinDeliveryMethod());
                    break;

                case ("TokenOrderPinDeliveryMethod"):
                    btnDelivery.click();
                    selTokenOrderPinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-orderPinDeliveryMethod")));
                    selTokenOrderPinDeliveryMethodList.selectByVisibleText(editTokenApplicationProfile.getTokenOrderPinDeliveryMethod());
                    break;

                case ("ForceTokenOrderPinDelivery"):
                    btnDelivery.click();
                    adminCommon.selectCheckbox(cbxForceTokenOrderPinDelivery, editTokenApplicationProfile.getForceTokenOrderPinDelivery());
                    break;

                case ("PinAdvicePinDeliveryMethod"):
                    btnDelivery.click();
                    selPinAdvicePinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-advicePinDeliveryMethod")));
                    selPinAdvicePinDeliveryMethodList.selectByVisibleText(editTokenApplicationProfile.getPinAdvicePinDeliveryMethod());
                    break;

                case ("ForcePinAdvicePinDelivery"):
                    btnDelivery.click();
                    adminCommon.selectCheckbox(cbxForcePinAdvicePinDelivery, editTokenApplicationProfile.getForcePinAdvicePinDelivery());
                    break;

                case ("UpdatePinDeliveryMethod"):
                    btnDelivery.click();
                    selUpdatePinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-updatePinDeliveryMethod")));
                    selUpdatePinDeliveryMethodList.selectByVisibleText(editTokenApplicationProfile.getUpdatePinDeliveryMethod());
                    break;

                case ("VppPinDeliveryMethod"):
                    btnDelivery.click();
                    selVppPinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-vppPinDeliveryMethod")));
                    selVppPinDeliveryMethodList.selectByVisibleText(editTokenApplicationProfile.getVppPinDeliveryMethod());
                    break;

                case ("PinTemplate"):
                    btnSms.click();
                    selPinTemplateList = new Select(webDriver.findElement(By.id("input-pinSmsTemplate")));
                    selPinTemplateList.selectByVisibleText(editTokenApplicationProfile.getPinTemplate());
                    break;

                case ("SecondaryPinTemplate"):
                    btnSms.click();
                    selSecondaryPinTemplateList = new Select(webDriver.findElement(By.id("input-secondaryPinSmsTemplate")));
                    selSecondaryPinTemplateList.selectByVisibleText(editTokenApplicationProfile.getSecondaryPinTemplate());
                    break;

                case ("PukTemplate"):
                    btnSms.click();
                    selPukTemplateList = new Select(webDriver.findElement(By.id("input-pukSmsTemplate")));
                    selPukTemplateList.selectByVisibleText(editTokenApplicationProfile.getPukTemplate());
                    break;

                case ("PasswordTemplate"):
                    btnSms.click();
                    selPasswordTemplateList = new Select(webDriver.findElement(By.id("input-passwdSmsTemplate")));
                    selPasswordTemplateList.selectByVisibleText(editTokenApplicationProfile.getPasswordTemplate());
                    break;

                case ("MessageTemplate"):
                    btnTemplates.click();
                    selMessageTemplateList = new Select(webDriver.findElement(By.id("input-messageTemplate")));
                    selMessageTemplateList.selectByVisibleText(editTokenApplicationProfile.getMessageTemplate());
                    break;

                case ("MessageInterface"):
                    btnTemplates.click();
                    selMessageInterfaceList = new Select(webDriver.findElement(By.id("input-messageInterface")));
                    selMessageInterfaceList.selectByVisibleText(editTokenApplicationProfile.getMessageInterface());
                    break;

                case ("SmsInterface"):
                    btnSms.click();
                    selSmsInterfaceList = new Select(webDriver.findElement(By.id("input-smsInterface")));
                    selSmsInterfaceList.selectByVisibleText(editTokenApplicationProfile.getSmsInterface());
                    break;

                case ("PinOverSMSDelayHours"):
                    btnSms.click();
                    txtPinOverSmsDelayHours.clear();
                    txtPinOverSmsDelayHours.sendKeys(editTokenApplicationProfile.getPinOverSMSDelayHours());
                    break;

                case ("SmsPasswordDelayHours"):
                    btnSms.click();
                    txtSmsPasswordDelayHours.clear();
                    txtSmsPasswordDelayHours.sendKeys(editTokenApplicationProfile.getSmsPasswordDelayHours());
                    break;

                case ("SmsSender"):
                    btnSms.click();
                    txtSmsSender.clear();
                    txtSmsSender.sendKeys(editTokenApplicationProfile.getSmsSender());
                    break;

                case ("SmsValidityHours"):
                    btnSms.click();
                    txtSmsValidityHours.clear();
                    txtSmsValidityHours.sendKeys(editTokenApplicationProfile.getSmsValidityHours());
                    break;

                case ("SmsPasswordExpiryHours"):
                    btnSms.click();
                    txtSmsPasswordExpiryHours.clear();
                    txtSmsPasswordExpiryHours.sendKeys(editTokenApplicationProfile.getSmsPasswordExpiryHours());
                    break;

                case ("SmsClass"):
                    btnSms.click();
                    selSmsClassList = new Select(webDriver.findElement(By.id("input-smsClass")));
                    selSmsClassList.selectByVisibleText(editTokenApplicationProfile.getSmsClass());
                    break;

            }
        }

        // Click the Save button
        adminCommon.hardWait(1000);
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        String btnDisabled = btnSaveChanges.getAttribute("disabled");
        if(btnDisabled==null){
            btnDisabled = "false";
        }

        if(btnDisabled.equalsIgnoreCase("true")){
            editTokenApplicationProfile.setTestOutput("SAVE CHANGES BUTTON DISABLED. EDITING " + field);
            return editTokenApplicationProfile;
        }
        btnSaveChanges.click();
        adminCommon.hardWait(500);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {

            try {
                btnReset.click();
            }
            catch(ElementClickInterceptedException ignored){}
            adminCommon.hardWait(500);
            filterByRecord(editTokenApplicationProfile);
            adminCommon.hardWait(500);
            adminCommon.clickEditByUniqueId(editTokenApplicationProfile.getParentTokenProductId() + "_" + editTokenApplicationProfile.getAppSequenceNumber());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch (field) {

                case ("Status"):
                    selStatusList = new Select(webDriver.findElement(By.id("input-status")));
                    if(selStatusList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getStatus()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Status: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Status: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getStatus() = " + editTokenApplicationProfile.getStatus() + "\n" +
                                "ACT: selStatusList.getFirstSelectedOption().getText() = " + selStatusList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("DefaultApp"):
                    if(adminCommon.getCheckboxValue(cbxDefaultApp).equalsIgnoreCase(editTokenApplicationProfile.getDefaultApp()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Default App: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Default App: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getDefaultApp() = " + editTokenApplicationProfile.getDefaultApp() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxDefaultApp) = " + adminCommon.getCheckboxValue(cbxDefaultApp));
                    }
                    break;

                case ("PinRequired"):
                    btnPinDetails.click();
                    if(adminCommon.getCheckboxValue(cbxPinRequired).equalsIgnoreCase(editTokenApplicationProfile.getPinRequired()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Required: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Required: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinRequired() = " + editTokenApplicationProfile.getPinRequired() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPinRequired) = " + adminCommon.getCheckboxValue(cbxPinRequired));
                    }
                    break;

                case ("PinLength"):
                    if (txtPinLength.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getPinLength())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Length: SUCCESSFUL");
                    } else {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Length: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinLength() = " + editTokenApplicationProfile.getPinLength() + "\n" +
                                "ACT: txtPinLength.getAttribute(\"value\") = " + txtPinLength.getAttribute("value"));
                    }
                    break;

                case ("ImportEncryptionZone"):
                    btnPinDetails.click();
                    selImportEncryptionZoneList = new Select(webDriver.findElement(By.id("input-importEZ")));
                    if(selImportEncryptionZoneList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getImportEncryptionZone()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Import Encryption Zone: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Import Encryption Zone: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getImportEncryptionZone() = " + editTokenApplicationProfile.getImportEncryptionZone() + "\n" +
                                "ACT: selImportEncryptionZoneList.getFirstSelectedOption().getText() = " + selImportEncryptionZoneList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("ExportEncryptionZone"):
                    btnPinDetails.click();
                    selExportEncryptionZoneList = new Select(webDriver.findElement(By.id("input-exportEZ")));
                    if(selExportEncryptionZoneList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getExportEncryptionZone()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Export Encryption Zone: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Export Encryption Zone: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getExportEncryptionZone() = " + editTokenApplicationProfile.getExportEncryptionZone() + "\n" +
                                "ACT: selExportEncryptionZoneList.getFirstSelectedOption().getText() = " + selExportEncryptionZoneList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("ReturnInterface"):
                    btnPinDetails.click();
                    selReturnInterfaceList = new Select(webDriver.findElement(By.id("input-returnIntf")));
                    if(selReturnInterfaceList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getReturnInterface()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Return Interface: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Return Interface: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getReturnInterface() = " + editTokenApplicationProfile.getReturnInterface() + "\n" +
                                "ACT: selReturnInterfaceList.getFirstSelectedOption().getText() = " + selReturnInterfaceList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PinMailerDelayHours"):
                    btnPinDetails.click();
                    if (txtPinMailerDelay.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getPinMailerDelayHours())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Mailer Delay Hours: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Mailer Delay Hours: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinMailerDelayHours() = " + editTokenApplicationProfile.getPinMailerDelayHours() + "\n" +
                                "ACT: txtPinMailerDelay.getAttribute(\"value\") = " + txtPinMailerDelay.getAttribute("value"));
                    }
                    break;

                case ("PinHeldBySeqNum"):
                    btnPinDetails.click();
                    if (txtPinHeldBySequenceNumber.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getPinHeldBySeqNum())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Held By Seq Num: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Held By Seq Num: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinHeldBySeqNum() = " + editTokenApplicationProfile.getPinHeldBySeqNum() + "\n" +
                                "ACT: txtPinHeldBySequenceNumber.getAttribute(\"value\") = " + txtPinHeldBySequenceNumber.getAttribute("value"));
                    }
                    break;

                case ("AllowPinChange"):
                    btnPinDetails.click();
                    if(adminCommon.getCheckboxValue(cbxAllowPinChange).equalsIgnoreCase(editTokenApplicationProfile.getAllowPinChange()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Pin Change: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Pin Change: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getAllowPinChange() = " + editTokenApplicationProfile.getAllowPinChange() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxAllowPinChange) = " + adminCommon.getCheckboxValue(cbxAllowPinChange));
                    }
                    break;

                case ("AllowOnlinePinChange"):
                    btnPinDetails.click();
                    if(adminCommon.getCheckboxValue(cbxAllowOnlinePinChange).equalsIgnoreCase(editTokenApplicationProfile.getAllowOnlinePinChange()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Online Pin Change: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Online Pin Change: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getAllowOnlinePinChange() = " + editTokenApplicationProfile.getAllowOnlinePinChange() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxAllowOnlinePinChange) = " + adminCommon.getCheckboxValue(cbxAllowOnlinePinChange));
                    }
                    break;

                case ("AllowOnlinePinView"):
                    btnPinDetails.click();
                    if(adminCommon.getCheckboxValue(cbxAllowOnlinePinView).equalsIgnoreCase(editTokenApplicationProfile.getAllowOnlinePinView()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Online Pin View: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Online Pin View: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getAllowOnlinePinView() = " + editTokenApplicationProfile.getAllowOnlinePinView() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxAllowOnlinePinView) = " + adminCommon.getCheckboxValue(cbxAllowOnlinePinView));
                    }
                    break;

                case ("IndependentTokenPins"):
                    btnPinDetails.click();
                    if(adminCommon.getCheckboxValue(cbxIndependentTokenPins).equalsIgnoreCase(editTokenApplicationProfile.getIndependentTokenPins()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Independent Token Pins: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Independent Token Pins: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getIndependentTokenPins() = " + editTokenApplicationProfile.getIndependentTokenPins() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxIndependentTokenPins) = " + adminCommon.getCheckboxValue(cbxIndependentTokenPins));
                    }
                    break;

                case ("PukRequired"):
                    btnPukDetails.click();
                    if(adminCommon.getCheckboxValue(cbxPukRequired).equalsIgnoreCase(editTokenApplicationProfile.getPukRequired()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Puk Required: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Puk Required: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPukRequired() = " + editTokenApplicationProfile.getPukRequired() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPukRequired) = " + adminCommon.getCheckboxValue(cbxPukRequired));
                    }
                    break;

                case ("PukLength"):
                    btnPukDetails.click();
                    if (txtPukLength.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getPukLength())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Puk Length: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Puk Length: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPukLength() = " + editTokenApplicationProfile.getPukLength() + "\n" +
                                "ACT: txtPukLength.getAttribute(\"value\") = " + txtPukLength.getAttribute("value"));
                    }
                    break;

                case ("AllowPukChange"):
                    btnPukDetails.click();
                    if(adminCommon.getCheckboxValue(cbxAllowPukChange).equalsIgnoreCase(editTokenApplicationProfile.getAllowPukChange()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Puk Change: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Allow Puk Change: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getAllowPukChange() = " + editTokenApplicationProfile.getAllowPukChange() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxAllowPukChange) = " + adminCommon.getCheckboxValue(cbxAllowPukChange));
                    }
                    break;

                case ("PukHeldBySeqNum"):
                    btnPukDetails.click();
                    if (txtPukHeldBySequenceNumber.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getPukHeldBySeqNum())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Puk Held By Seq Num: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Puk Held By Seq Num: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPukHeldBySeqNum() = " + editTokenApplicationProfile.getPukHeldBySeqNum() + "\n" +
                                "ACT: txtPukHeldBySequenceNumber.getAttribute(\"value\") = " + txtPukHeldBySequenceNumber.getAttribute("value"));
                    }
                    break;

                case ("IndependentTokenPuks"):
                    btnPukDetails.click();
                    if(adminCommon.getCheckboxValue(cbxIndependentTokenPuks).equalsIgnoreCase(editTokenApplicationProfile.getIndependentTokenPuks()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Independent Token Puks: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Independent Token Puks: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getIndependentTokenPuks() = " + editTokenApplicationProfile.getIndependentTokenPuks() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxIndependentTokenPuks) = " + adminCommon.getCheckboxValue(cbxIndependentTokenPuks));
                    }
                    break;

                case ("PinVerificationMethod"):
                    btnVerification.click();
                    selPinVerificationMethodList = new Select(webDriver.findElement(By.id("input-pinVerifyMethod")));
                    if(selPinVerificationMethodList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getPinVerificationMethod()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Verification Method: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Verification Method: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinVerificationMethod() = " + editTokenApplicationProfile.getPinVerificationMethod() + "\n" +
                                "ACT: selPinVerificationMethodList.getFirstSelectedOption().getText() = " + selPinVerificationMethodList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PinVerificationKey"):
                    btnVerification.click();
                    selPinVerificationKeyList = new Select(webDriver.findElement(By.id("input-pinVerifyKey")));
                    if(selPinVerificationKeyList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getPinVerificationKey()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Verification Key: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Verification Key: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinVerificationKey() = " + editTokenApplicationProfile.getPinVerificationKey() + "\n" +
                                "ACT: selPinVerificationKeyList.getFirstSelectedOption().getText() = " + selPinVerificationKeyList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PinTries"):
                    btnVerification.click();
                    if (txtPinTries.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getPinTries())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Tries: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Tries: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinTries() = " + editTokenApplicationProfile.getPinTries() + "\n" +
                                "ACT: txtPinTries.getAttribute(\"value\") = " + txtPinTries.getAttribute("value"));
                    }
                    break;

                case ("VerificationBackoff"):
                    btnVerification.click();
                    if (txtVerificationBackoff.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getVerificationBackoff())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Verification Backoff: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Verification Backoff: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getVerificationBackoff() = " + editTokenApplicationProfile.getVerificationBackoff() + "\n" +
                                "ACT: txtVerificationBackoff.getAttribute(\"value\") = " + txtVerificationBackoff.getAttribute("value"));
                    }
                    break;

                case ("BackoffMultiplier"):
                    btnVerification.click();
                    if (txtBackoffMultiplier.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getBackoffMultiplier())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Backoff Multiplier: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Backoff Multiplier: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getBackoffMultiplier() = " + editTokenApplicationProfile.getBackoffMultiplier() + "\n" +
                                "ACT: txtBackoffMultiplier.getAttribute(\"value\") = " + txtBackoffMultiplier.getAttribute("value"));
                    }
                    break;

                case ("TokenImportPinDeliveryMethod"):
                    btnDelivery.click();
                    selTokenImportPinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-importPinDeliveryMethod")));
                    if(selTokenImportPinDeliveryMethodList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getTokenImportPinDeliveryMethod()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Token Import Pin Delivery Method: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Token Import Pin Delivery Method: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getTokenImportPinDeliveryMethod() = " + editTokenApplicationProfile.getTokenImportPinDeliveryMethod() + "\n" +
                                "ACT: selTokenImportPinDeliveryMethodList.getFirstSelectedOption().getText() = " + selTokenImportPinDeliveryMethodList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("TokenOrderPinDeliveryMethod"):
                    btnDelivery.click();
                    selTokenOrderPinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-orderPinDeliveryMethod")));
                    if(selTokenOrderPinDeliveryMethodList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getTokenOrderPinDeliveryMethod()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Token Order Pin Delivery Method: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Token Order Pin Delivery Method: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getTokenOrderPinDeliveryMethod() = " + editTokenApplicationProfile.getTokenOrderPinDeliveryMethod() + "\n" +
                                "ACT: selTokenOrderPinDeliveryMethodList.getFirstSelectedOption().getText() = " + selTokenOrderPinDeliveryMethodList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("ForceTokenOrderPinDelivery"):
                    btnDelivery.click();
                    if(adminCommon.getCheckboxValue(cbxForceTokenOrderPinDelivery).equalsIgnoreCase(editTokenApplicationProfile.getForceTokenOrderPinDelivery()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Force Token Order Pin Delivery: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Force Token Order Pin Delivery: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getForceTokenOrderPinDelivery() = " + editTokenApplicationProfile.getForceTokenOrderPinDelivery() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxForceTokenOrderPinDelivery) = " + adminCommon.getCheckboxValue(cbxForceTokenOrderPinDelivery));
                    }
                    break;

                case ("PinAdvicePinDeliveryMethod"):
                    btnDelivery.click();
                    selPinAdvicePinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-advicePinDeliveryMethod")));
                    if(selPinAdvicePinDeliveryMethodList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getPinAdvicePinDeliveryMethod()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Advice Pin Delivery Method: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Advice Pin Delivery Method: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinAdvicePinDeliveryMethod() = " + editTokenApplicationProfile.getPinAdvicePinDeliveryMethod() + "\n" +
                                "ACT: selPinAdvicePinDeliveryMethodList.getFirstSelectedOption().getText() = " + selPinAdvicePinDeliveryMethodList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("ForcePinAdvicePinDelivery"):
                    btnDelivery.click();
                    if(adminCommon.getCheckboxValue(cbxForcePinAdvicePinDelivery).equalsIgnoreCase(editTokenApplicationProfile.getForcePinAdvicePinDelivery()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Force Pin Advice Pin Delivery: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Force Pin Advice Pin Delivery: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getForcePinAdvicePinDelivery() = " + editTokenApplicationProfile.getForcePinAdvicePinDelivery() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxForcePinAdvicePinDelivery) = " + adminCommon.getCheckboxValue(cbxForcePinAdvicePinDelivery));
                    }
                    break;

                case ("UpdatePinDeliveryMethod"):
                    btnDelivery.click();
                    selUpdatePinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-updatePinDeliveryMethod")));
                    if(selUpdatePinDeliveryMethodList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getUpdatePinDeliveryMethod()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Update Pin Delivery Method: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Update Pin Delivery Method: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getUpdatePinDeliveryMethod() = " + editTokenApplicationProfile.getUpdatePinDeliveryMethod() + "\n" +
                                "ACT: selUpdatePinDeliveryMethodList.getFirstSelectedOption().getText() = " + selUpdatePinDeliveryMethodList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("VppPinDeliveryMethod"):
                    btnDelivery.click();
                    selVppPinDeliveryMethodList = new Select(webDriver.findElement(By.id("input-vppPinDeliveryMethod")));
                    if(selVppPinDeliveryMethodList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getVppPinDeliveryMethod()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Vpp Pin Delivery Method: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Vpp Pin Delivery Method: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getVppPinDeliveryMethod() = " + editTokenApplicationProfile.getVppPinDeliveryMethod() + "\n" +
                                "ACT: selVppPinDeliveryMethodList.getFirstSelectedOption().getText() = " + selVppPinDeliveryMethodList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PinTemplate"):
                    btnSms.click();
                    selPinTemplateList = new Select(webDriver.findElement(By.id("input-pinSmsTemplate")));
                    if(selPinTemplateList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getPinTemplate()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Template: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Template: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinTemplate() = " + editTokenApplicationProfile.getPinTemplate() + "\n" +
                                "ACT: selPinTemplateList.getFirstSelectedOption().getText() = " + selPinTemplateList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("SecondaryPinTemplate"):
                    btnSms.click();
                    selSecondaryPinTemplateList = new Select(webDriver.findElement(By.id("input-secondaryPinSmsTemplate")));
                    if(selSecondaryPinTemplateList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getSecondaryPinTemplate()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Secondary Pin Template: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Secondary Pin Template: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getSecondaryPinTemplate() = " + editTokenApplicationProfile.getSecondaryPinTemplate() + "\n" +
                                "ACT: selSecondaryPinTemplateList.getFirstSelectedOption().getText() = " + selSecondaryPinTemplateList.getFirstSelectedOption().getText());
                    }
                    break;


                case ("PukTemplate"):
                    btnSms.click();
                    selPukTemplateList = new Select(webDriver.findElement(By.id("input-pukSmsTemplate")));
                    if(selPukTemplateList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getPukTemplate()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Puk Template: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Puk Template: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPukTemplate() = " + editTokenApplicationProfile.getPukTemplate() + "\n" +
                                "ACT: selPukTemplateList.getFirstSelectedOption().getText() = " + selPukTemplateList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PasswordTemplate"):
                    btnSms.click();
                    selPasswordTemplateList = new Select(webDriver.findElement(By.id("input-passwdSmsTemplate")));
                    if(selPasswordTemplateList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getPasswordTemplate()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Password Template: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Password Template: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPasswordTemplate() = " + editTokenApplicationProfile.getPasswordTemplate() + "\n" +
                                "ACT: selPasswordTemplateList.getFirstSelectedOption().getText() = " + selPasswordTemplateList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("MessageTemplate"):
                    btnTemplates.click();
                    selMessageTemplateList = new Select(webDriver.findElement(By.id("input-messageTemplate")));
                    if(selMessageTemplateList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getMessageTemplate()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Message Template: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Message Template: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getMessageTemplate() = " + editTokenApplicationProfile.getMessageTemplate() + "\n" +
                                "ACT: selMessageTemplateList.getFirstSelectedOption().getText() = " + selMessageTemplateList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("MessageInterface"):
                    btnTemplates.click();
                    selMessageInterfaceList = new Select(webDriver.findElement(By.id("input-messageInterface")));
                    if(selMessageInterfaceList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getMessageInterface()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Message Interface: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Message Interface: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getMessageInterface() = " + editTokenApplicationProfile.getMessageInterface() + "\n" +
                                "ACT: selMessageInterfaceList.getFirstSelectedOption().getText() = " + selMessageInterfaceList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("SmsInterface"):
                    btnSms.click();
                    selSmsInterfaceList = new Select(webDriver.findElement(By.id("input-smsInterface")));
                    if(selSmsInterfaceList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getSmsInterface()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Sms Interface: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Sms Interface: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getSmsInterface() = " + editTokenApplicationProfile.getSmsInterface() + "\n" +
                                "ACT: selSmsInterfaceList.getFirstSelectedOption().getText() = " + selSmsInterfaceList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PinOverSmsDelayHours"):
                    btnSms.click();
                    if (txtPinOverSmsDelayHours.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getPinOverSMSDelayHours())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Over Sms Delay Hours: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Pin Over Sms Delay Hours: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getPinOverSMSDelayHours() = " + editTokenApplicationProfile.getPinOverSMSDelayHours() + "\n" +
                                "ACT: txtPinOverSmsDelayHours.getAttribute(\"value\") = " + txtPinOverSmsDelayHours.getAttribute("value"));
                    }
                    break;

                case ("SmsPasswordDelayHours"):
                    btnSms.click();
                    if (txtSmsPasswordDelayHours.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getSmsPasswordDelayHours())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Password Delay Hours: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Password Delay Hours: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getSmsPasswordDelayHours() = " + editTokenApplicationProfile.getSmsPasswordDelayHours() + "\n" +
                                "ACT: txtSmsPasswordDelayHours.getAttribute(\"value\") = " + txtSmsPasswordDelayHours.getAttribute("value"));
                    }
                    break;

                case ("SmsSender"):
                    btnSms.click();
                    if (txtSmsSender.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getSmsSender())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Sender: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Sender: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getSmsSender() = " + editTokenApplicationProfile.getSmsSender() + "\n" +
                                "ACT: txtSmsSender.getAttribute(\"value\") = " + txtSmsSender.getAttribute("value"));
                    }
                    break;

                case ("SmsValidityHours"):
                    btnSms.click();
                    if (txtSmsValidityHours.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getSmsValidityHours())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Validity Hours: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Validity Hours: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getSmsValidityHours() = " + editTokenApplicationProfile.getSmsValidityHours() + "\n" +
                                "ACT: txtSmsValidityHours.getAttribute(\"value\") = " + txtSmsValidityHours.getAttribute("value"));
                    }
                    break;

                case ("SmsPasswordExpiryHours"):
                    btnSms.click();
                    if (txtSmsPasswordExpiryHours.getAttribute("value").equalsIgnoreCase(editTokenApplicationProfile.getSmsPasswordExpiryHours())) {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Password Expiry Hours: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Profile Sms Password Expiry Hours: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getSmsPasswordExpiryHours() = " + editTokenApplicationProfile.getSmsPasswordExpiryHours() + "\n" +
                                "ACT: txtSmsPasswordExpiryHours.getAttribute(\"value\") = " + txtSmsPasswordExpiryHours.getAttribute("value"));
                    }
                    break;

                case ("SmsClass"):
                    btnSms.click();
                    selSmsClassList = new Select(webDriver.findElement(By.id("input-smsClass")));
                    if(selSmsClassList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenApplicationProfile.getSmsClass()))
                    {
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Sms Class: SUCCESSFUL");
                    }
                    else{
                        editTokenApplicationProfile.setTestOutput("Edit Token Application Sms Class: UNSUCCESSFUL\n" +
                                "EXP: editTokenApplicationProfile.getSmsClass() = " + editTokenApplicationProfile.getSmsClass() + "\n" +
                                "ACT: selSmsClassList.getFirstSelectedOption().getText() = " + selSmsClassList.getFirstSelectedOption().getText());
                        System.out.println("EXP: editTokenApplicationProfile.getSmsClass() = " + editTokenApplicationProfile.getSmsClass());
                        System.out.println("ACT: selSmsClassList.getFirstSelectedOption().getText() = " + selSmsClassList.getFirstSelectedOption().getText());
                    }
                    break;


            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenAppProfile));

        }
        else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editTokenApplicationProfile.setParentTokenProductId(null);
            editTokenApplicationProfile.setAppSequenceNumber(null);
            editTokenApplicationProfile.setName(null);
            editTokenApplicationProfile.setParentInstitution(null);
            editTokenApplicationProfile.setParentIssuer(null);
            editTokenApplicationProfile.setParentTokenProductGroupName(null);
            editTokenApplicationProfile.setParentTokenProductName(null);
            editTokenApplicationProfile.setAppSequenceNumber(null);
            editTokenApplicationProfile.setTestOutput("Edit Token Application Profile: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        try {
            btnReset.click();
        }
        catch(ElementClickInterceptedException ignored){}

        return(editTokenApplicationProfile);

    }

    public boolean deleteTokenApplicationProfileByName(TokenApplicationProfileDataRecord delTokenApplicationProfile){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenAppProfile));

        try {
            btnReset.click();
        }
        catch(ElementClickInterceptedException ignored){}

        filterByRecord(delTokenApplicationProfile);

        adminCommon.clickDeleteByUniqueId(delTokenApplicationProfile.getParentTokenProductId() + "_" + delTokenApplicationProfile.getAppSequenceNumber());
        myWait.until(ExpectedConditions.visibilityOf(btnModalYes));

        // Click Yes
        if(modalDialog.getAttribute("textContent").contains(delTokenApplicationProfile.getName())) {
            try {

                myWait.until(ExpectedConditions.visibilityOf(btnModalYes));
                btnModalYes.click();

                myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenAppProfile));

            } catch (ElementClickInterceptedException ignored) {
            }
        }
        else{
            try {
                btnModalNo.click();
            }
            catch(ElementClickInterceptedException ignored){}
            return false;
        }

        adminCommon.hardWait(1000);
        try {
            btnReset.click();
        }
        catch(ElementClickInterceptedException ignored){}
        return true;
    }

    public TokenApplicationProfileDataRecord checkDeleteTokenAppProfile(TokenApplicationProfileDataRecord delTokenApplicationProfile){

        int maxTimeout = 3;
        List<WebElement> modalList;
        List<WebElement> alertList;
        long start = System.currentTimeMillis();
        long end = start + maxTimeout * 1000;

        do {
            modalList = webDriver.findElements(By.className("modal-contents"));
            alertList = webDriver.findElements(By.className("alert-dialog"));
        }while(modalList.size()==0 && alertList.size()==0 && System.currentTimeMillis() < end);

        if(modalList.size()==0 && alertList.size()==0) {
            delTokenApplicationProfile.setTestOutput("Delete Token App Profile: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            delTokenApplicationProfile.setParentTokenProductId(null);
            delTokenApplicationProfile.setAppSequenceNumber(null);
            delTokenApplicationProfile.setName(null);
            delTokenApplicationProfile.setParentInstitution(null);
            delTokenApplicationProfile.setParentIssuer(null);
            delTokenApplicationProfile.setParentTokenProductGroupName(null);
            delTokenApplicationProfile.setParentTokenProductName(null);
            delTokenApplicationProfile.setAppSequenceNumber(null);
            delTokenApplicationProfile.setTestOutput("Delete Token App Profile: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(delTokenApplicationProfile.getName())){

                try {
                    btnModalYes.click();
                    delTokenApplicationProfile.setTestOutput("Delete Token App Profile: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    delTokenApplicationProfile.setTestOutput("Delete Token App Profile: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        try {
            btnReset.click();
        }
        catch(ElementClickInterceptedException ignored){}

        return delTokenApplicationProfile;
    }

    public TokenApplicationProfileDataRecord getDBDataByName(String name){

        TokenApplicationProfileDataRecord record = new TokenApplicationProfileDataRecord();

        try {

            String SQL = "SELECT TAP.NAME, INST.NAME AS INSTITUTION_NAME, ISS.NAME AS ISSUER_NAME, " +
                    "TP.NAME AS TOKEN_PRODUCT, TP.ID AS TOKEN_PRODUCT_ID, TPG.NAME AS TOKEN_PRODUCT_GROUP_NAME, " +
                    "TAP.APP_SEQ_NUMBER, " +
                    "CASE " +
                    "WHEN TAP.STATUS = 'A' THEN 'Active' " +
                    "WHEN TAP.STATUS = 'I' THEN 'Inactive' " +
                    "END AS STATUS, " +
                    "CASE " +
                    "WHEN TAP.DEFAULT_APP_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.DEFAULT_APP_FLAG = 'N' THEN 'false' " +
                    "END AS DEFAULT_APP_FLAG, " +
                    "CASE " +
                    "WHEN TAP.PIN_REQUIRED_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.PIN_REQUIRED_FLAG = 'N' THEN 'false' " +
                    "END AS PIN_REQUIRED_FLAG, " +
                    "TAP.PIN_LENGTH, TAP.EXPIRY_IN_DAYS, EDEZ1.NAME AS IMPORT_EZ, " +
                    "IDI1.NAME AS SMS_INTERFACE, EDEZ2.NAME AS EXPORT_EZ, " +
                    "IDI2.NAME AS RETURN_INTERFACE, TAP.PIN_MAILER_DELAY, " +
                    "TAP.USES_PIN_OF_TOKEN_APP_SEQ_NUM, " +
                    "CASE " +
                    "WHEN TAP.ALLOW_PIN_CHANGE_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.ALLOW_PIN_CHANGE_FLAG = 'N' THEN 'false' " +
                    "END AS ALLOW_PIN_CHANGE_FLAG, " +
                    "CASE " +
                    "WHEN TAP.ALLOW_ONLINE_PIN_CHANGE_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.ALLOW_ONLINE_PIN_CHANGE_FLAG = 'N' THEN 'false' " +
                    "END AS ALLOW_ONLINE_PIN_CHANGE_FLAG, " +
                    "CASE " +
                    "WHEN TAP.ALLOW_ONLINE_PIN_VIEWING_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.ALLOW_ONLINE_PIN_VIEWING_FLAG = 'N' THEN 'false' " +
                    "END AS ALLOW_ONLINE_PIN_VIEWING_FLAG, " +
                    "CASE " +
                    "WHEN TAP.PUK_REQUIRED_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.PUK_REQUIRED_FLAG = 'N' THEN 'false' " +
                    "END AS PUK_REQUIRED_FLAG, " +
                    "TAP.PUK_LENGTH, " +
                    "CASE " +
                    "WHEN TAP.ALLOW_PUK_CHANGE_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.ALLOW_PUK_CHANGE_FLAG = 'N' THEN 'false' " +
                    "END AS ALLOW_PUK_CHANGE_FLAG, " +
                    "TAP.USES_PUK_OF_TOKEN_APP_SEQ_NUM, " +
                    "PVM.NAME AS PIN_VERIFICATION_METHOD, SCI.NAME AS PIN_VERIFICATION_KEY, " +
                    "TAP.PIN_TRIES_NUMBER, TAP.VERIFICATION_BACKOFF, TAP.BACKOFF_MULTIPLIER, " +
                    "PDM6.NAME AS TOKEN_IMPORT_DEL_METHOD, " +
                    "IDI3.NAME AS TOKEN_IMPORT_OUTPUT_INTERFACE, " +
                    "PDM2.NAME AS TOKEN_ORDER_PIN_DEL_METHOD, " +
                    "CASE " +
                    "WHEN TAP.TOWPD_FORCE_DELIVERY_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.TOWPD_FORCE_DELIVERY_FLAG = 'N' THEN 'false' " +
                    "END AS TOWPD_FORCE_DELIVERY_FLAG, " +
                    "IDI4.NAME AS TOKEN_ORDER_OUTPUT_INTERFACE, " +
                    "PDM3.NAME AS PIN_ADVICE_PIN_DEL_METHOD, " +
                    "CASE " +
                    "WHEN TAP.ADVICE_FORCE_DELIVERY_FLAG = 'Y' THEN 'true' " +
                    "WHEN TAP.ADVICE_FORCE_DELIVERY_FLAG = 'N' THEN 'false' " +
                    "END AS ADVICE_FORCE_DELIVERY_FLAG, " +
                    "IDI5.NAME AS PIN_ADVICE_OUTPUT_TEMPLATE, " +
                    "PDM4.NAME AS UPDATE_PIN_DEL_METHOD, IDI6.NAME AS UPDATE_OUTPUT_INTERFACE, " +
                    "PDM5.NAME AS VPP_PIN_DEL_METHOD, IDI7.NAME AS VPP_OUTPUT_INTERFACE, " +
                    "TEM1.NAME AS PIN_TEMPLATE, TEM2.NAME SECONDARY_PIN_TEMPLATE, " +
                    "TEM3.NAME AS PUK_TEMPLATE, TEM4.NAME AS PASSWORD_TEMPLATE, " +
                    "TEM5.NAME AS MESSAGE_TEMPLATE, TAP.PIN_OVER_SMS_DELAY, " +
                    "TAP.SMS_PASSWORD_DELAY, TAP.SMS_SENDER, TAP.SMS_VALIDITY, " +
                    "TAP.SMS_PASSWORD_EXPIRATION, " +
                    "CASE " +
                    "WHEN TAP.SMS_CLASS = '0' THEN 'Class 0 (flash)' " +
                    "WHEN TAP.SMS_CLASS = '1' THEN 'Class 1 (normal)' " +
                    "END AS SMS_CLASS, " +
                    "CASE " +
                    "WHEN TAP.PIN_SHARED_FLAG = 'Y' THEN 'false' " +
                    "WHEN TAP.PIN_SHARED_FLAG = 'N' THEN 'true' " +
                    "END AS PIN_SHARED_FLAG, " +
                    "CASE " +
                    "WHEN TAP.PUK_SHARED_FLAG = 'Y' THEN 'false' " +
                    "WHEN TAP.PUK_SHARED_FLAG = 'N' THEN 'true' " +
                    "END AS PUK_SHARED_FLAG " +
                    "FROM C_D_TOKEN_APP_PROFILE TAP " +
                    "LEFT JOIN C_D_TOKEN_PRODUCT TP ON TAP.TOKEN_PRODUCT_ID = TP.ID " +
                    "LEFT JOIN C_D_ISSUER ISS ON TP.ISSUER_ID = ISS.ID " +
                    "LEFT JOIN C_D_INSTITUTION INST ON ISS.INSTITUTION_ID = INST.ID " +
                    "LEFT JOIN C_D_TOKEN_PRODUCT_GROUP TPG ON TP.TOKEN_PRODUCT_GROUP_ID = TPG.ID " +
                    "LEFT JOIN ENC_D_ENCRYPTION_ZONE EDEZ1 ON TAP.IMPORT_ENC_ZONE_ID = EDEZ1.ID " +
                    "LEFT JOIN C_D_INTERFACE IDI1 ON TAP.EXPORT_INTERFACE_ID = IDI1.ID " +
                    "LEFT JOIN ENC_D_ENCRYPTION_ZONE EDEZ2 ON TAP.EXPORT_ENC_ZONE_ID = EDEZ2.ID " +
                    "LEFT JOIN C_D_INTERFACE IDI2 ON TAP.RETURN_INTERFACE_ID = IDI2.ID " +
                    "LEFT JOIN ENC_S_PIN_VERIFICATION_METHOD PVM ON TAP.PIN_VERIFICATION_METHOD_ID = PVM.ID " +
                    "LEFT JOIN ENC_D_SCI SCI ON TAP.SCI_PIN_VERIFICATION_KEY_ID = SCI.ID " +
                    "LEFT JOIN C_S_PIN_DELIVERY_METHOD PDM6 ON TAP.IMPORT_PIN_DELIVERY_METHOD = PDM6.ID " +
                    "LEFT JOIN C_D_INTERFACE IDI3 ON TAP.IMPORT_OUTPUT_INTERFACE_ID = IDI3.ID " +
                    "LEFT JOIN C_S_PIN_DELIVERY_METHOD PDM2 ON TAP.TOWPD_PIN_DELIVERY_METHOD = PDM2.ID " +
                    "LEFT JOIN C_D_INTERFACE IDI4 ON TAP.TOWPD_OUTPUT_INTERFACE_ID = IDI4.ID " +
                    "LEFT JOIN C_S_PIN_DELIVERY_METHOD PDM3 ON TAP.ADVICE_PIN_DELIVERY_METHOD = PDM3.ID " +
                    "LEFT JOIN C_D_INTERFACE IDI5 ON TAP.ADVICE_OUTPUT_INTERFACE_ID = IDI5.ID " +
                    "LEFT JOIN C_S_PIN_DELIVERY_METHOD PDM4 ON TAP.UPDATE_PIN_DELIVERY_METHOD = PDM4.ID " +
                    "LEFT JOIN C_D_INTERFACE IDI6 ON TAP.UPDATE_OUTPUT_INTERFACE_ID = IDI6.ID " +
                    "LEFT JOIN C_S_PIN_DELIVERY_METHOD PDM5 ON TAP.VPP_PIN_DELIVERY_METHOD = PDM5.ID " +
                    "LEFT JOIN C_D_INTERFACE IDI7 ON TAP.VPP_OUTPUT_INTERFACE_ID = IDI7.ID " +
                    "LEFT JOIN PM_D_TEMPLATE TEM1 ON TAP.PIN_TEMPLATE_ID = TEM1.ID " +
                    "LEFT JOIN PM_D_TEMPLATE TEM2 ON TAP.SECONDARY_PIN_TEMPLATE_ID = TEM2.ID " +
                    "LEFT JOIN PM_D_TEMPLATE TEM3 ON TAP.PUK_TEMPLATE_ID = TEM3.ID " +
                    "LEFT JOIN PM_D_TEMPLATE TEM4 ON TAP.PASSWORD_TEMPLATE_ID = TEM4.ID " +
                    "LEFT JOIN C_D_MESSAGE_TEMPLATE TEM5 ON TAP.MESSAGE_TEMPLATE_ID = TEM5.ID " +
                    "WHERE TAP.NAME = ? ";

            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setString(1, name);

            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setName(null);
                record.setParentInstitution(null);
                record.setParentIssuer(null);
                record.setParentTokenProductName(null);
                record.setParentTokenProductId(null);
                record.setAppSequenceNumber(null);
            }
            else{
                rs.next();

                record.setName(rs.getString(1));
                record.setParentInstitution(rs.getString(2));
                record.setParentIssuer(rs.getString(3));
                record.setParentTokenProductName(rs.getString(4));
                record.setParentTokenProductId(rs.getString(5));
                record.setParentTokenProductGroupName(rs.getString(6));
                record.setAppSequenceNumber(rs.getString(7));
                record.setStatus(rs.getString(8));
                record.setDefaultApp(rs.getString(9));
                record.setPinRequired(rs.getString(10));
                record.setPinLength(rs.getString(11));
//                record.setValidityInDays(rs.getString(12));
                record.setImportEncryptionZone(rs.getString(13));
                record.setSmsInterface(rs.getString(14));
                record.setExportEncryptionZone(rs.getString(15));
                record.setReturnInterface(rs.getString(16));
                record.setPinMailerDelayHours(rs.getString(17));
                record.setPinHeldBySeqNum(rs.getString(18));
                record.setAllowPinChange(rs.getString(19));
                record.setAllowOnlinePinChange(rs.getString(20));
                record.setAllowOnlinePinView(rs.getString(21));
                record.setPukRequired(rs.getString(22));
                record.setPukLength(rs.getString(23));
                record.setAllowPukChange(rs.getString(24));
                record.setPukHeldBySeqNum(rs.getString(25));
                record.setPinVerificationMethod(rs.getString(26));
                record.setPinVerificationKey(rs.getString(27));
                record.setPinTries(rs.getString(28));
                record.setVerificationBackoff(rs.getString(29));
                record.setBackoffMultiplier(rs.getString(30));
                record.setTokenImportPinDeliveryMethod(rs.getString(31));
//                record.setTokenImportOutputInterface(rs.getString(32));
                record.setTokenOrderPinDeliveryMethod(rs.getString(33));
                record.setForceTokenOrderPinDelivery(rs.getString(34));
//                record.setTokenOrderOutputInterface(rs.getString(35));
                record.setPinAdvicePinDeliveryMethod(rs.getString(36));
                record.setForcePinAdvicePinDelivery(rs.getString(37));
//                record.setPinAdviceOutputInterface(rs.getString(38));
                record.setUpdatePinDeliveryMethod(rs.getString(39));
//                record.setUpdateOutputInterface(rs.getString(40));
                record.setVppPinDeliveryMethod(rs.getString(41));
//                record.setVppOutputInterface(rs.getString(42));
                record.setPinTemplate(rs.getString(43));
                record.setSecondaryPinTemplate(rs.getString(44));
                record.setPukTemplate(rs.getString(45));
                record.setPasswordTemplate(rs.getString(46));
                record.setMessageTemplate(rs.getString(47));
                record.setPinOverSMSDelayHours(rs.getString(48));
                record.setSmsPasswordDelayHours(rs.getString(49));
                record.setSmsSender(rs.getString(50));
                record.setSmsValidityHours(rs.getString(51));
                record.setSmsPasswordExpiryHours(rs.getString(52));
                record.setSmsClass(rs.getString(53));
                record.setIndependentTokenPins(rs.getString(54));
                record.setIndependentTokenPuks(rs.getString(55));


            }

            connection.close();

        }catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public TokenApplicationProfileDataRecord getTableRecordByRecord(TokenApplicationProfileDataRecord tap) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        TokenApplicationProfileDataRecord record = new TokenApplicationProfileDataRecord();

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setParentTokenProductId(null);
                record.setAppSequenceNumber(null);
                record.setName(null);
                record.setParentInstitution(null);
                record.setParentIssuer(null);
                record.setParentTokenProductGroupName(null);
                record.setParentTokenProductName(null);
                record.setAppSequenceNumber(null);

                return record;
            }

        }


        adminCommon.sortTableByColumn("App seq num","DESC");
        btnFirstPage.click();
        adminCommon.hardWait(500);


        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-name" + tap.getParentTokenProductId() + "_" + tap.getAppSequenceNumber() + "']"));

            if(rows.size()>0){

                found = true;

                record.setParentTokenProductId(tap.getParentTokenProductId());
                record.setAppSequenceNumber(tap.getAppSequenceNumber());
                record.setName(webDriver.findElement(By.id("col-name" + tap.getParentTokenProductId() + "_" + tap.getAppSequenceNumber())).getAttribute("textContent"));
                record.setParentInstitution(webDriver.findElement(By.id("col-institution" + tap.getParentTokenProductId() + "_" + tap.getAppSequenceNumber())).getAttribute("textContent"));
                record.setParentIssuer(webDriver.findElement(By.id("col-issuer" + tap.getParentTokenProductId() + "_" + tap.getAppSequenceNumber())).getAttribute("textContent"));
//                record.setParentTokenProductGroupName(webDriver.findElement(By.id("col-tokenProductGroup" + tap.getParentTokenProductId() + "_" + tap.getAppSequenceNumber())).getAttribute("textContent"));
                record.setParentTokenProductName(webDriver.findElement(By.id("col-tokenProduct" + tap.getParentTokenProductId() + "_" + tap.getAppSequenceNumber())).getAttribute("textContent"));
                record.setAppSequenceNumber(webDriver.findElement(By.id("col-appSeqNo" + tap.getParentTokenProductId() + "_" + tap.getAppSequenceNumber())).getAttribute("textContent"));

            }

            if (!found) {

                thisPage = Integer.parseInt(txtCurrentPage.getAttribute("value"));
                lastPageString = txtPageCount.getText();
                pageArray = lastPageString.split(" ");
                lastItem = Integer.parseInt(pageArray[1]);

                if(thisPage < lastItem){
                    btnNextPage.click();
                }
                else{
                    record.setParentTokenProductId(null);
                    record.setAppSequenceNumber(null);
                    record.setName(null);
                    record.setParentInstitution(null);
                    record.setParentIssuer(null);
                    record.setParentTokenProductGroupName(null);
                    record.setParentTokenProductName(null);
                    record.setAppSequenceNumber(null);

                    return record;
                }

            }

        }while (!found);

        return record;
    }

    public String validateTextInputFields(String field){

        StringBuilder msg = new StringBuilder();
        Actions builder = new Actions(webDriver);
        String obStringId;
        String expMessage;
        String expMessage2;

        ValidationTestcases validationTestcases = new ValidationTestcases();


        switch (field) {

            case ("Name"):

                obStringId = "val-name";
                expMessage = "Name is required";
                expMessage2 = "\"<\", \">\" and \"\\\" are not valid characters";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtName.click();
                    txtName.clear();
                    txtName.sendKeys(value);
                    try {
                        builder.moveToElement(txtName).click().build().perform();
                        int width = txtName.getSize().getWidth();
                        builder.moveToElement(txtName).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                        case ("Spaces"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case("Back_Slash"):
                        case("Greater_Than"):
                        case("Less_Than"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("AppSequenceNumber"):

                obStringId = "val-appSeqNum";
                expMessage = "App sequence number is required";
                expMessage2 = "App sequence number must be a decimal value and greater than zero";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtAppSequenceNumber.click();
                    txtAppSequenceNumber.clear();
                    txtAppSequenceNumber.sendKeys(value);
                    try {
                        builder.moveToElement(txtAppSequenceNumber).click().build().perform();
                        int width = txtAppSequenceNumber.getSize().getWidth();
                        builder.moveToElement(txtAppSequenceNumber).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"): //Only takes 2 characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                    }

                }

                break;

            case ("PinLength"):

                obStringId = "val-pinLength";
                expMessage = "PIN length is required";
                expMessage2 = "PIN length must be an integer value of 4-12";

                btnPinDetails.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPinLength.click();
                    txtPinLength.sendKeys(Keys.DELETE);
                    txtPinLength.sendKeys(Keys.DELETE);
                    txtPinLength.sendKeys(Keys.DELETE);
                    txtPinLength.sendKeys(Keys.DELETE);
                    txtPinLength.sendKeys(Keys.BACK_SPACE);
                    txtPinLength.sendKeys(Keys.BACK_SPACE);
                    txtPinLength.sendKeys(Keys.BACK_SPACE);
                    txtPinLength.sendKeys(Keys.BACK_SPACE);
                    txtPinLength.click();
                    txtPinLength.sendKeys(value);
                    txtPinLength.sendKeys(Keys.TAB);

                    try {
                        builder.moveToElement(txtPinLength).click().build().perform();
                        int width = txtPinLength.getSize().getWidth();
                        builder.moveToElement(txtPinLength).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"): // Only takes two characters
                        case("Excluded_Pins"): // Only takes two characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                    }

                }

                break;

            case ("PinMailerDelayHours"):

                obStringId = "val-pinMailerDelay";
                expMessage = "PIN mailer delay hours is required";
                expMessage2 = "PIN mailer delay hours must be a decimal value";

                btnPinDetails.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPinMailerDelay.click();
                    txtPinMailerDelay.sendKeys(Keys.DELETE);
                    txtPinMailerDelay.sendKeys(Keys.DELETE);
                    txtPinMailerDelay.sendKeys(Keys.DELETE);
                    txtPinMailerDelay.sendKeys(Keys.DELETE);
                    txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                    txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                    txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                    txtPinMailerDelay.sendKeys(Keys.BACK_SPACE);
                    txtPinMailerDelay.click();
                    txtPinMailerDelay.sendKeys(value);
                    txtPinMailerDelay.sendKeys(Keys.TAB);
                    try {
                        builder.moveToElement(txtPinMailerDelay).click().build().perform();
                        int width = txtPinMailerDelay.getSize().getWidth();
                        builder.moveToElement(txtPinMailerDelay).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case("Decimal_Value"):
                        case("Zero_Value"):
                        case("Three_Digit_Decimal_Value"): // Only takes two characters
                        case("Excluded_Pins"): // Only takes two characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                    }

                }

                break;

            case ("PinHeldBySeqNum"):

                obStringId = "val-usesPinOfTokenAppSeqNum";
                expMessage = "PIN held by sequence number must be a decimal value";
                expMessage2 = "PIN held by sequence number must be greater than zero";

                btnPinDetails.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPinHeldBySequenceNumber.click();
                    txtPinHeldBySequenceNumber.sendKeys(Keys.DELETE);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.DELETE);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.DELETE);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.DELETE);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.BACK_SPACE);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.BACK_SPACE);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.BACK_SPACE);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.BACK_SPACE);
                    txtPinHeldBySequenceNumber.click();
                    txtPinHeldBySequenceNumber.sendKeys(value);
                    txtPinHeldBySequenceNumber.sendKeys(Keys.TAB);
                    try {
                        builder.moveToElement(txtPinHeldBySequenceNumber).click().build().perform();
                        int width = txtPinHeldBySequenceNumber.getSize().getWidth();
                        builder.moveToElement(txtPinHeldBySequenceNumber).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Blank"):
                        case("Decimal_Value"):
                        case("Excluded_Pins"):
                        case("Three_Digit_Decimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                         case("Zero_Value"):
                         case("Negative_Integer_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("PukLength"):

                obStringId = "val-pukLength";
                expMessage = "PUK length is required";
                expMessage2 = "PUK length must be an integer value of 4-12";

                btnPukDetails.click();
                adminCommon.selectCheckbox(cbxPukRequired, "true");

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPukLength.click();
                    txtPukLength.sendKeys(Keys.DELETE);
                    txtPukLength.sendKeys(Keys.DELETE);
                    txtPukLength.sendKeys(Keys.DELETE);
                    txtPukLength.sendKeys(Keys.DELETE);
                    txtPukLength.sendKeys(Keys.BACK_SPACE);
                    txtPukLength.sendKeys(Keys.BACK_SPACE);
                    txtPukLength.sendKeys(Keys.BACK_SPACE);
                    txtPukLength.sendKeys(Keys.BACK_SPACE);
                    txtPukLength.click();
                    txtPukLength.sendKeys(value);
                    txtPukLength.sendKeys(Keys.TAB);
                    try {
                        builder.moveToElement(txtPukLength).click().build().perform();
                        int width = txtPukLength.getSize().getWidth();
                        builder.moveToElement(txtPukLength).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"): // Only takes two characters
                        case("Excluded_Pins"): // Only takes two characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                    }

                }

                break;

            case ("PukHeldBySeqNum"):

                obStringId = "val-usesPukOfTokenAppSeqNum";
                expMessage = "PUK held by sequence number must be a decimal value";
                expMessage2 = "PUK held by sequence number must be greater than zero";

                btnPukDetails.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPukHeldBySequenceNumber.click();
                    txtPukHeldBySequenceNumber.clear();
                    txtPukHeldBySequenceNumber.sendKeys(Keys.BACK_SPACE);
                    txtPukHeldBySequenceNumber.sendKeys(Keys.BACK_SPACE);
                    txtPukHeldBySequenceNumber.sendKeys(Keys.BACK_SPACE);
                    txtPukHeldBySequenceNumber.sendKeys(value);
                    try {
                        builder.moveToElement(txtPukHeldBySequenceNumber).click().build().perform();
                        int width = txtPukHeldBySequenceNumber.getSize().getWidth();
                        builder.moveToElement(txtPukHeldBySequenceNumber).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Blank"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"): // Only takes two characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        case("Zero_Value"):
                        case("Negative_Integer_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("PinTries"):

                obStringId = "val-pinTriesNum";
                expMessage = "PIN tries number must be a decimal value";

                btnVerification.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPinTries.click();
                    txtPinTries.clear();
                    txtPinTries.sendKeys(value);
                    try {
                        builder.moveToElement(txtPinTries).click().build().perform();
                        int width = txtPinTries.getSize().getWidth();
                        builder.moveToElement(txtPinTries).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Blank"):
                        case("Zero_Value"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"): // Only takes two characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("VerificationBackoff"):

                obStringId = "val-verifyBackoff";
                expMessage = "Verification backoff must be a decimal value";

                btnVerification.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtVerificationBackoff.click();
                    txtVerificationBackoff.clear();
                    txtVerificationBackoff.sendKeys(value);
                    try {
                        builder.moveToElement(txtVerificationBackoff).click().build().perform();
                        int width = txtVerificationBackoff.getSize().getWidth();
                        builder.moveToElement(txtVerificationBackoff).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Blank"):
                        case("Zero_Value"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"): // Only takes two characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("BackoffMultiplier"):

                obStringId = "val-backoffMultiplier";
                expMessage = "Backoff multiplier must be a decimal value";

                btnVerification.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtBackoffMultiplier.click();
                    txtBackoffMultiplier.clear();
                    txtBackoffMultiplier.sendKeys(value);
                    try {
                        builder.moveToElement(txtBackoffMultiplier).click().build().perform();
                        int width = txtBackoffMultiplier.getSize().getWidth();
                        builder.moveToElement(txtBackoffMultiplier).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Blank"):
                        case("Zero_Value"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"): // Only takes two characters
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("PinOverSmsDelayHours"):

                obStringId = "val-pinOverSmsDelay";
                expMessage = "PIN over SMS delay hours must be a decimal value";
                expMessage2 = "PIN over SMS delay hours is required";

                btnSms.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPinOverSmsDelayHours.click();
                    txtPinOverSmsDelayHours.sendKeys(Keys.DELETE);
                    txtPinOverSmsDelayHours.sendKeys(Keys.DELETE);
                    txtPinOverSmsDelayHours.sendKeys(Keys.DELETE);
                    txtPinOverSmsDelayHours.sendKeys(Keys.DELETE);
                    txtPinOverSmsDelayHours.sendKeys(Keys.BACK_SPACE);
                    txtPinOverSmsDelayHours.sendKeys(Keys.BACK_SPACE);
                    txtPinOverSmsDelayHours.sendKeys(Keys.BACK_SPACE);
                    txtPinOverSmsDelayHours.sendKeys(Keys.BACK_SPACE);
                    txtPinOverSmsDelayHours.click();
                    txtPinOverSmsDelayHours.sendKeys(value);
                    txtPinOverSmsDelayHours.sendKeys(Keys.TAB);
                    try {
                        builder.moveToElement(txtPinOverSmsDelayHours).click().build().perform();
                        int width = txtPinOverSmsDelayHours.getSize().getWidth();
                        builder.moveToElement(txtPinOverSmsDelayHours).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Zero_Value"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        case("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("SmsPasswordDelayHours"):

                obStringId = "val-smsPasswdDelay";
                expMessage = "SMS password delay hours must be a decimal value";
                expMessage2 = "SMS password delay hours is required";

                btnSms.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtSmsPasswordDelayHours.click();
                    txtSmsPasswordDelayHours.clear();
                    txtSmsPasswordDelayHours.sendKeys(value);
                    try {
                        builder.moveToElement(txtSmsPasswordDelayHours).click().build().perform();
                        int width = txtSmsPasswordDelayHours.getSize().getWidth();
                        builder.moveToElement(txtSmsPasswordDelayHours).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Zero_Value"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        case("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("SmsSender"):

                obStringId = "val-smsSender";
                expMessage = "\"<\", \">\" and \"\\\" are not valid characters";
                expMessage2 = "SMS sender is required";

                btnSms.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtSmsSender.click();
                    txtSmsSender.clear();
                    txtSmsSender.sendKeys(value);
                    try {
                        builder.moveToElement(txtSmsSender).click().build().perform();
                        int width = txtSmsSender.getSize().getWidth();
                        builder.moveToElement(txtSmsSender).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Greater_Than"):
                        case("Less_Than"):
                        case("Back_Slash"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case("Blank"):
                        case("Spaces"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("SmsValidityHours"):

                obStringId = "val-smsValidity";
                expMessage = "SMS validity hours must be a decimal value";
                expMessage2 = "SMS validity hours is required";

                btnSms.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtSmsValidityHours.click();
                    txtSmsValidityHours.clear();
                    txtSmsValidityHours.sendKeys(value);
                    try {
                        builder.moveToElement(txtSmsValidityHours).click().build().perform();
                        int width = txtSmsValidityHours.getSize().getWidth();
                        builder.moveToElement(txtSmsValidityHours).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Zero_Value"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                        case("Excluded_Pins"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        case("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("SmsPasswordExpiryHours"):

                obStringId = "val-smsPasswdExpiry";
                expMessage = "SMS password expiry hours must be a decimal value";
                expMessage2 = "SMS password expiry hours is required";

                btnSms.click();

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtSmsPasswordExpiryHours.click();
                    txtSmsPasswordExpiryHours.clear();
                    txtSmsPasswordExpiryHours.sendKeys(value);
                    try {
                        builder.moveToElement(txtSmsPasswordExpiryHours).click().build().perform();
                        int width = txtSmsPasswordExpiryHours.getSize().getWidth();
                        builder.moveToElement(txtSmsPasswordExpiryHours).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Zero_Value"):
                        case("Decimal_Value"):
                        case("Three_Digit_Decimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        case("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

        }

        return msg.toString();

    }


}
