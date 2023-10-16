package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.MessageTemplateDataRecord;
import com.aconite.apm.gui.automation.testcases.ValidationTestcases;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


public class MessageTemplatesPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewMessageTemplate;

    @FindBy(id = "btn-add-zone")
    WebElement btnSaveNewMessageTemplate;

    @FindBy(id = "btn-save-zone")
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

    @FindBy(id = "editMessageTemplate-dummy")
    WebElement txtId;

    @FindBy(id = "name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "sel-institution")
    WebElement selInstitution;

    @FindBy(id = "val-institution")
    WebElement msgInstitution;

    @FindBy(id = "input-pan")
    WebElement cbxPan;

    @FindBy(id = "input-panSeqNum")
    WebElement cbxPanSequence;

    @FindBy(id = "input-expiryDate")
    WebElement cbxExpiryDate;

    @FindBy(id = "input-panAlias")
    WebElement cbxPanAlias;

    @FindBy(id = "input-panID")
    WebElement cbxPanId;

    @FindBy(id = "input-pinPukFlag")
    WebElement cbxPinPukFlag;

    @FindBy(id = "input-pinBlock")
    WebElement cbxPinBlock;

    @FindBy(id = "input-pukBlock")
    WebElement cbxPukBlock;

    @FindBy(id = "input-pinVV")
    WebElement cbxPinVerificationValue;

    @FindBy(id = "input-pinVM")
    WebElement cbxPinVerificationMethod;

    @FindBy(id = "input-pvvKeyName")
    WebElement cbxPvvKeyName;

    @FindBy(id = "input-pukVV")
    WebElement cbxPukVerificationValue;

    @FindBy(id = "input-customerCode")
    WebElement cbxCustomerCode;

    @FindBy(id = "input-operation")
    WebElement cbxOperation;

    @FindBy(id = "input-tokenProductName")
    WebElement cbxTokenProductName;

    @FindBy(id = "input-appSeqNum")
    WebElement cbxAppSequenceNumber;

    @FindBy(id = "fixed-data")
    WebElement txtFixedData;


    public MessageTemplatesPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);

    }

    public boolean isPageOpened() {
        return(pageTitle.getText().equalsIgnoreCase("Message Templates"));
    }

    public void searchInstitution(MessageTemplateDataRecord messageTemplate){

        System.out.println("searchInstitution - " + messageTemplate.getInstitutionName());

        Select selInstitutionList = new Select(webDriver.findElement(By.id("search-institution")));
        selInstitutionList.selectByVisibleText(messageTemplate.getInstitutionName());

    }

    public void clickAddButton(){

        btnAddNewMessageTemplate.click();

    }

    public boolean checkRequiredFieldMessages(String field, String message) {

        boolean rc = true;

        switch (field) {

            case("Name"):
                txtName.clear();
                txtName.click();
                txtName.sendKeys(Keys.TAB);
                if (!msgName.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Institution"):
                selInstitution.click();
                selInstitution.sendKeys(Keys.TAB);
                selInstitution.sendKeys(Keys.TAB);
                if (!msgInstitution.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;

    }

    public MessageTemplateDataRecord enterMessageTemplateData(MessageTemplateDataRecord newMessageTemplate) {

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        MessageTemplateDataRecord record = new MessageTemplateDataRecord();

        // Click Reset button
        btnReset.click();

        searchInstitution(newMessageTemplate);

        // Click Add button
        btnAddNewMessageTemplate.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        // Enter required field data
        txtName.sendKeys(newMessageTemplate.getName());

        Select selInstitutionList = new Select(webDriver.findElement(By.id("sel-institution")));
        selInstitutionList.selectByVisibleText(newMessageTemplate.getInstitutionName());


        btnSaveNewMessageTemplate.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            record = newMessageTemplate;
            record.setTestOutput("Add Message Template: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            record.setId(null);
            record.setName(null);
            record.setInstitutionName(null);
            record.setPan(null);
            record.setPanSequence(null);
            record.setExpiryDate(null);
            record.setPanAlias(null);
            record.setPanId(null);
            record.setPinPukFlag(null);
            record.setPinBlock(null);
            record.setPukBlock(null);
            record.setPinVerificationValue(null);
            record.setPukVerificationValue(null);
            record.setCustomerCode(null);
            record.setOperation(null);
            record.setTokenProductName(null);
            record.setAppSequenceNumber(null);
            record.setFixedData(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return record;
    }

    public MessageTemplateDataRecord editMessageTemplateData(MessageTemplateDataRecord editMessageTemplate, String instField){

//        boolean updateSucceeded = false;
        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        Select selInstitutionList;

        if(!instField.equalsIgnoreCase("InstitutionName")) {
            searchInstitution(editMessageTemplate);
        }

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editMessageTemplate.getId())) {

            // Edit the field required
            switch (instField) {

                case ("Name"):
                    txtName.clear();
                    txtName.sendKeys(editMessageTemplate.getName());
                    break;

                case ("InstitutionName"):
                    selInstitutionList = new Select(webDriver.findElement(By.id("sel-institution")));
                    selInstitutionList.selectByVisibleText(editMessageTemplate.getInstitutionName());
                    break;

                case ("Pan"):
                    adminCommon.selectCheckbox(cbxPan, editMessageTemplate.getPan());
                    break;

                case ("PanSequence"):
                    adminCommon.selectCheckbox(cbxPanSequence, editMessageTemplate.getPanSequence());
                    break;

                case ("ExpiryDate"):
                    adminCommon.selectCheckbox(cbxExpiryDate, editMessageTemplate.getExpiryDate());
                    break;

                case ("PanAlias"):
                    adminCommon.selectCheckbox(cbxPanAlias, editMessageTemplate.getPanAlias());
                    break;

                case ("PanId"):
                    adminCommon.selectCheckbox(cbxPanId, editMessageTemplate.getPanId());
                    break;

                case ("PinPukFlag"):
                    adminCommon.selectCheckbox(cbxPinPukFlag, editMessageTemplate.getPinPukFlag());
                    break;

                case ("PinBlock"):
                    adminCommon.selectCheckbox(cbxPinBlock, editMessageTemplate.getPinBlock());
                    break;

                case ("PukBlock"):
                    adminCommon.selectCheckbox(cbxPukBlock, editMessageTemplate.getPukBlock());
                    break;

                case ("PinVerificationValue"):
                    adminCommon.selectCheckbox(cbxPinVerificationValue, editMessageTemplate.getPinVerificationValue());
                    break;

                case ("PinVerificationMethod"):
                    adminCommon.selectCheckbox(cbxPinVerificationMethod, editMessageTemplate.getPinVerificationMethod());
                    break;

                case ("PvvKeyName"):
                    adminCommon.selectCheckbox(cbxPvvKeyName, editMessageTemplate.getPvvKeyName());
                    break;

                case ("PukVerificationValue"):
                    adminCommon.selectCheckbox(cbxPukVerificationValue, editMessageTemplate.getPukVerificationValue());
                    break;

                case ("CustomerCode"):
                    adminCommon.selectCheckbox(cbxCustomerCode, editMessageTemplate.getCustomerCode());
                    break;

                case ("Operation"):
                    adminCommon.selectCheckbox(cbxOperation, editMessageTemplate.getOperation());
                    break;

                case ("TokenProductName"):
                    adminCommon.selectCheckbox(cbxTokenProductName, editMessageTemplate.getTokenProductName());
                    break;

                case ("AppSequenceNumber"):
                    adminCommon.selectCheckbox(cbxAppSequenceNumber, editMessageTemplate.getAppSequenceNumber());
                    break;

                case ("FixedData"):
                    txtFixedData.clear();
                    txtFixedData.sendKeys(editMessageTemplate.getFixedData());
                    break;

            }
        }

        // Click the Save button
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();

        adminCommon.hardWait(500);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {

            searchInstitution(editMessageTemplate);

            adminCommon.clickEditByUniqueId(editMessageTemplate.getId());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch(instField) {

                case ("Name"):
                    if (txtName.getAttribute("value").equalsIgnoreCase(editMessageTemplate.getName())) {
                        editMessageTemplate.setTestOutput("Edit Message Template Name: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Name: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getName() = " + editMessageTemplate.getName() + "\n" +
                                "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                    }
                    break;

                case("Institution"):
                    selInstitutionList = new Select(webDriver.findElement(By.id("sel-institution")));
                    if(selInstitutionList.getFirstSelectedOption().getText().equalsIgnoreCase(editMessageTemplate.getInstitutionName())){
                        editMessageTemplate.setTestOutput("Edit Message Template Institution: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Institution: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getInstitutionName() = " + editMessageTemplate.getInstitutionName() + "\n" +
                                "ACT: selInstitutionList.getFirstSelectedOption().getText() = " + selInstitutionList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("Pan"):
                    if(adminCommon.getCheckboxValue(cbxPan).equalsIgnoreCase(editMessageTemplate.getPan())){
                        editMessageTemplate.setTestOutput("Edit Message Template Pan: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Pan: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPan() = " + editMessageTemplate.getPan() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPan) = " + adminCommon.getCheckboxValue(cbxPan));
                    }
                    break;

                case ("PanAlias"):
                    if(adminCommon.getCheckboxValue(cbxPanAlias).equalsIgnoreCase(editMessageTemplate.getPanAlias())){
                        editMessageTemplate.setTestOutput("Edit Message Template Pan Alias: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Pan Alias: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPanAlias() = " + editMessageTemplate.getPanAlias() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPanAlias) = " + adminCommon.getCheckboxValue(cbxPanAlias));
                    }
                    break;

                case ("PanSequence"):
                    if(adminCommon.getCheckboxValue(cbxPanSequence).equalsIgnoreCase(editMessageTemplate.getPanSequence())){
                        editMessageTemplate.setTestOutput("Edit Message Template Pan Sequence: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Pan Alias: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPanSequence() = " + editMessageTemplate.getPanSequence() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPanSequence) = " + adminCommon.getCheckboxValue(cbxPanSequence));
                    }
                    break;

                case ("ExpiryDate"):
                    if(adminCommon.getCheckboxValue(cbxExpiryDate).equalsIgnoreCase(editMessageTemplate.getExpiryDate())){
                        editMessageTemplate.setTestOutput("Edit Message Template Expiry Date: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Expiry Date: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getExpiryDate() = " + editMessageTemplate.getExpiryDate() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxExpiryDate) = " + adminCommon.getCheckboxValue(cbxExpiryDate));
                    }
                    break;

                case ("PanId"):
                    if(adminCommon.getCheckboxValue(cbxPanId).equalsIgnoreCase(editMessageTemplate.getPanId())){
                        editMessageTemplate.setTestOutput("Edit Message Template PAN ID: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template PAN ID: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPanId() = " + editMessageTemplate.getPanId() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPanId) = " + adminCommon.getCheckboxValue(cbxPanId));
                    }
                    break;

                case ("PinPukFlag"):
                    if(adminCommon.getCheckboxValue(cbxPinPukFlag).equalsIgnoreCase(editMessageTemplate.getPinPukFlag())){
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Puk Flag: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Puk Flag: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPinPukFlag() = " + editMessageTemplate.getPinPukFlag() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPinPukFlag) = " + adminCommon.getCheckboxValue(cbxPinPukFlag));
                    }
                    break;

                case ("PinBlock"):
                    if(adminCommon.getCheckboxValue(cbxPinBlock).equalsIgnoreCase(editMessageTemplate.getPinBlock())){
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Block: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Block: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPinBlock() = " + editMessageTemplate.getPinBlock() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPinBlock) = " + adminCommon.getCheckboxValue(cbxPinBlock));
                    }
                    break;

                case ("PukBlock"):
                    if(adminCommon.getCheckboxValue(cbxPukBlock).equalsIgnoreCase(editMessageTemplate.getPukBlock())){
                        editMessageTemplate.setTestOutput("Edit Message Template Puk Block: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Puk Block: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPukBlock() = " + editMessageTemplate.getPukBlock() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPukBlock) = " + adminCommon.getCheckboxValue(cbxPukBlock));
                    }
                    break;

                case ("PinVerificationValue"):
                    if(adminCommon.getCheckboxValue(cbxPinVerificationValue).equalsIgnoreCase(editMessageTemplate.getPinVerificationValue())){
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Verification Value: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Verification Value: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPinVerificationValue() = " + editMessageTemplate.getPinVerificationValue() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPinVerificationValue) = " + adminCommon.getCheckboxValue(cbxPinVerificationValue));
                    }
                    break;

                case ("PinVerificationMethod"):
                    if(adminCommon.getCheckboxValue(cbxPinVerificationMethod).equalsIgnoreCase(editMessageTemplate.getPinVerificationMethod())){
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Verification Method: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Pin Verification Method: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPinVerificationMethod() = " + editMessageTemplate.getPinVerificationMethod() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPinVerificationMethod) = " + adminCommon.getCheckboxValue(cbxPinVerificationMethod));
                    }
                    break;

                case ("PvvKeyName"):
                    if(adminCommon.getCheckboxValue(cbxPvvKeyName).equalsIgnoreCase(editMessageTemplate.getPvvKeyName())){
                        editMessageTemplate.setTestOutput("Edit Message Template PVV Key Name: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template PVV Key Name: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPvvKeyName() = " + editMessageTemplate.getPvvKeyName() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPvvKeyName) = " + adminCommon.getCheckboxValue(cbxPvvKeyName));
                      }
                    break;

                case ("PukVerificationValue"):
                    if(adminCommon.getCheckboxValue(cbxPukVerificationValue).equalsIgnoreCase(editMessageTemplate.getPukVerificationValue())){
                        editMessageTemplate.setTestOutput("Edit Message Template Puk Verification Value: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Puk Verification Value: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getPukVerificationValue() = " + editMessageTemplate.getPukVerificationValue() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPukVerificationValue) = " + adminCommon.getCheckboxValue(cbxPukVerificationValue));
                    }
                    break;

                case ("CustomerCode"):
                    if(adminCommon.getCheckboxValue(cbxCustomerCode).equalsIgnoreCase(editMessageTemplate.getCustomerCode())){
                        editMessageTemplate.setTestOutput("Edit Message Template Customer Code: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Customer Code: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getCustomerCode() = " + editMessageTemplate.getCustomerCode() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxCustomerCode) = " + adminCommon.getCheckboxValue(cbxCustomerCode));
                    }
                    break;

                case ("Operation"):
                    if(adminCommon.getCheckboxValue(cbxOperation).equalsIgnoreCase(editMessageTemplate.getOperation())){
                        editMessageTemplate.setTestOutput("Edit Message Template Operation: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Operation: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getOperation() = " + editMessageTemplate.getOperation() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxOperation) = " + adminCommon.getCheckboxValue(cbxOperation));
                    }
                    break;

                case ("TokenProductName"):
                    if(adminCommon.getCheckboxValue(cbxTokenProductName).equalsIgnoreCase(editMessageTemplate.getTokenProductName())){
                        editMessageTemplate.setTestOutput("Edit Message Template Token Product Name: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Token Product Name: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getTokenProductName() = " + editMessageTemplate.getTokenProductName() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxTokenProductName) = " + adminCommon.getCheckboxValue(cbxTokenProductName));
                    }
                    break;

                case ("AppSequenceNumber"):
                    if(adminCommon.getCheckboxValue(cbxAppSequenceNumber).equalsIgnoreCase(editMessageTemplate.getAppSequenceNumber())){
                        editMessageTemplate.setTestOutput("Edit Message Template App Sequence Number: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template App Sequence Number: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getAppSequenceNumber() = " + editMessageTemplate.getAppSequenceNumber() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxAppSequenceNumber) = " + adminCommon.getCheckboxValue(cbxAppSequenceNumber));
                    }
                    break;

                case ("FixedData"):
                    if (txtFixedData.getAttribute("value").equalsIgnoreCase(editMessageTemplate.getFixedData())) {
                        editMessageTemplate.setTestOutput("Edit Message Template Fixed Data: SUCCESSFUL");
                    } else {
                        editMessageTemplate.setTestOutput("Edit Message Template Fixed Data: UNSUCCESSFUL\n" +
                                "EXP: editMessageTemplate.getFixedData() = " + editMessageTemplate.getFixedData() + "\n" +
                                "ACT: txtFixedData.getAttribute(\"value\") = " + txtFixedData.getAttribute("value"));
                    }
                    break;

            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewMessageTemplate));

        }
        else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editMessageTemplate.setId(null);
            editMessageTemplate.setName(null);
            editMessageTemplate.setInstitutionName(null);
            editMessageTemplate.setPan(null);
            editMessageTemplate.setPanSequence(null);
            editMessageTemplate.setExpiryDate(null);
            editMessageTemplate.setPanAlias(null);
            editMessageTemplate.setPanId(null);
            editMessageTemplate.setPinPukFlag(null);
            editMessageTemplate.setPinBlock(null);
            editMessageTemplate.setPukBlock(null);
            editMessageTemplate.setPinVerificationValue(null);
            editMessageTemplate.setPukVerificationValue(null);
            editMessageTemplate.setCustomerCode(null);
            editMessageTemplate.setOperation(null);
            editMessageTemplate.setTokenProductName(null);
            editMessageTemplate.setAppSequenceNumber(null);
            editMessageTemplate.setFixedData(null);
            editMessageTemplate.setPinVerificationMethod(null);
            editMessageTemplate.setPvvKeyName(null);
            editMessageTemplate.setTestOutput("Edit SMS Template Text: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        btnReset.click();

        return editMessageTemplate;
    }

    public MessageTemplateDataRecord checkDeleteMessageTemplate(MessageTemplateDataRecord delMessageTemplate){

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
            delMessageTemplate.setTestOutput("Delete Message Template: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            delMessageTemplate.setId(null);
            delMessageTemplate.setName(null);
            delMessageTemplate.setInstitutionName(null);
            delMessageTemplate.setPan(null);
            delMessageTemplate.setPanSequence(null);
            delMessageTemplate.setExpiryDate(null);
            delMessageTemplate.setPanAlias(null);
            delMessageTemplate.setPanId(null);
            delMessageTemplate.setPinPukFlag(null);
            delMessageTemplate.setPinBlock(null);
            delMessageTemplate.setPukBlock(null);
            delMessageTemplate.setPinVerificationValue(null);
            delMessageTemplate.setPukVerificationValue(null);
            delMessageTemplate.setCustomerCode(null);
            delMessageTemplate.setOperation(null);
            delMessageTemplate.setTokenProductName(null);
            delMessageTemplate.setAppSequenceNumber(null);
            delMessageTemplate.setFixedData(null);
            delMessageTemplate.setPinVerificationMethod(null);
            delMessageTemplate.setPvvKeyName(null);
            delMessageTemplate.setTestOutput("Delete Message Template: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(delMessageTemplate.getName())){

                try {
                    btnModalYes.click();
                    delMessageTemplate.setTestOutput("Delete Message Template: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    delMessageTemplate.setTestOutput("Delete Message Template: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        btnReset.click();

        return delMessageTemplate;
    }

    public MessageTemplateDataRecord getDBDataById(String instId){

        MessageTemplateDataRecord record = new MessageTemplateDataRecord();

        try {

            String getIssByIdSQL = "SELECT CDMT.ID, CDMT.NAME, INST.NAME, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PAN = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PAN = 'N' THEN 'false' " +
                    "END AS HAS_PAN, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PSN = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PSN = 'N' THEN 'false' " +
                    "END AS HAS_PSN, " +
                    "CASE " +
                    "WHEN CDMT.HAS_EXPIRY = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_EXPIRY = 'N' THEN 'false' " +
                    "END AS HAS_EXPIRY, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PAN_ALIAS = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PAN_ALIAS = 'N' THEN 'false' " +
                    "END AS HAS_PAN_ALIAS, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PAN_ID = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PAN_ID = 'N' THEN 'false' " +
                    "END AS HAS_PAN_ID, " +
                    "CASE " +
                    " WHEN CDMT.HAS_PIN_PUK_FLAG = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PIN_PUK_FLAG = 'N' THEN 'false' " +
                    "END AS HAS_PIN_PUK_FLAG, " +
                    " CASE " +
                    "WHEN CDMT.HAS_PIN_BLOCK = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PIN_BLOCK = 'N' THEN 'false' " +
                    "END AS HAS_PIN_BLOCK, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PUK_BLOCK = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PUK_BLOCK = 'N' THEN 'false' " +
                    "END AS HAS_PUK_BLOCK, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PIN_PVV = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PIN_PVV = 'N' THEN 'false' " +
                    "END AS HAS_PIN_PVV, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PUK_PVV = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PUK_PVV = 'N' THEN 'false' " +
                    "END AS HAS_PUK_PVV, " +
                    "CASE " +
                    "WHEN CDMT.HAS_CUSTOMER_CODE = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_CUSTOMER_CODE = 'N' THEN 'false' " +
                    "END AS HAS_CUSTOMER_CODE, " +
                    "CASE " +
                    "WHEN CDMT.HAS_OPERATION = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_OPERATION = 'N' THEN 'false' " +
                    "END AS HAS_OPERATION, " +
                    "CASE " +
                    "WHEN CDMT.HAS_TOKEN_PRODUCT_NAME = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_TOKEN_PRODUCT_NAME = 'N' THEN 'false' " +
                    "END AS HAS_TOKEN_PRODUCT_NAME, " +
                    "CASE " +
                    "WHEN CDMT.HAS_APP_SEQ_NUM = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_APP_SEQ_NUM = 'N' THEN 'false' " +
                    "END AS HAS_APP_SEQ_NUM, " +
                    "CDMT.FIXED_DATA, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PIN_VERIFICATION_METHOD = 'Y' THEN 'true' " +
                    " WHEN CDMT.HAS_PIN_VERIFICATION_METHOD = 'N' THEN 'false' " +
                    "END AS HAS_PIN_VERIFICATION_METHOD, " +
                    "CASE " +
                    "WHEN CDMT.HAS_PVV_KEY_NAME = 'Y' THEN 'true' " +
                    "WHEN CDMT.HAS_PVV_KEY_NAME = 'N' THEN 'false' " +
                    "END AS HAS_PVV_KEY_NAME " +
                    "FROM C_D_MESSAGE_TEMPLATE CDMT " +
                    "LEFT JOIN  C_D_INSTITUTION INST ON CDMT.INSTITUTION_ID = INST.ID " +
                    "WHERE CDMT.ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setInstitutionName(null);
                record.setPan(null);
                record.setPanSequence(null);
                record.setExpiryDate(null);
                record.setPanAlias(null);
                record.setPanId(null);
                record.setPinPukFlag(null);
                record.setPinBlock(null);
                record.setPukBlock(null);
                record.setPinVerificationValue(null);
                record.setPukVerificationValue(null);
                record.setCustomerCode(null);
                record.setOperation(null);
                record.setTokenProductName(null);
                record.setAppSequenceNumber(null);
                record.setFixedData(null);
                record.setPinVerificationMethod(null);
                record.setPvvKeyName(null);
            }
            else{

                rs.next();

                record.setId(rs.getString(1));
                record.setName(rs.getString(2));
                record.setInstitutionName(rs.getString(3));
                record.setPan(rs.getString(4));
                record.setPanSequence(rs.getString(5));
                record.setExpiryDate(rs.getString(6));
                record.setPanAlias(rs.getString(7));
                record.setPanId(rs.getString(8));
                record.setPinPukFlag(rs.getString(9));
                record.setPinBlock(rs.getString(10));
                record.setPukBlock(rs.getString(11));
                record.setPinVerificationValue(rs.getString(12));
                record.setPukVerificationValue(rs.getString(13));
                record.setCustomerCode(rs.getString(14));
                record.setOperation(rs.getString(15));
                record.setTokenProductName(rs.getString(16));
                record.setAppSequenceNumber(rs.getString(17));
                record.setFixedData(rs.getString(18));
                record.setPinVerificationMethod(rs.getString(19));
                record.setPvvKeyName(rs.getString(20));
            }
            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public MessageTemplateDataRecord getTableRecordByRecord(MessageTemplateDataRecord editMessageTemplate) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        MessageTemplateDataRecord record = new MessageTemplateDataRecord();
        searchInstitution(editMessageTemplate);

        adminCommon.hardWait(2000);
        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));

        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setId(null);
                record.setName(null);
                record.setInstitutionName(null);
                btnReset.click();
                return record;
            }

        }

        if(webDriver.findElement(By.id("btn-pagination-first")).getAttribute("disabled").equalsIgnoreCase("false")) {
            adminCommon.hardWait(500);
            webDriver.findElement(By.id("btn-pagination-first")).click();
            adminCommon.hardWait(500);
        }

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + editMessageTemplate.getId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setId(editMessageTemplate.getId());
                record.setName(webDriver.findElement(By.id("col-name" + editMessageTemplate.getId())).getAttribute("textContent"));

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
                    record.setId(null);
                    record.setName(null);
                    record.setInstitutionName(null);
                    btnReset.click();
                    return record;
                }

            }

        }while (!found);

        btnReset.click();
        return record;
    }

    public MessageTemplateDataRecord getDbIdByMessageTemplateName(MessageTemplateDataRecord key){

        MessageTemplateDataRecord record = new MessageTemplateDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM C_D_MESSAGE_TEMPLATE " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, key.getName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setInstitutionName(null);
                record.setPan(null);
                record.setPanSequence(null);
                record.setExpiryDate(null);
                record.setPanAlias(null);
                record.setPanId(null);
                record.setPinPukFlag(null);
                record.setPinBlock(null);
                record.setPukBlock(null);
                record.setPinVerificationValue(null);
                record.setPukVerificationValue(null);
                record.setCustomerCode(null);
                record.setOperation(null);
                record.setTokenProductName(null);
                record.setAppSequenceNumber(null);
                record.setFixedData(null);
            }
            else{
                rs.next();
                record = key;
                record.setId(rs.getString(1));

            }
            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public String validateTextInputFields(String field){

        StringBuilder msg = new StringBuilder();
        Actions builder = new Actions(webDriver);
        String obStringId;
        String expMessage;
//        String expMessage2;

        ValidationTestcases validationTestcases = new ValidationTestcases();


        switch (field) {

            case ("Name"):

                obStringId = "val-name";
                expMessage = "Name is required";
//                expMessage2 = "Invalid template text";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtName.click();
                    txtName.clear();
                    txtName.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                        case ("Spaces"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("Fixed data"):

                obStringId = "val-fixed-data";
//                expMessage = "Name is required";
//                expMessage2 = "Invalid template text";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtFixedData.click();
                    txtFixedData.clear();
                    txtFixedData.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

        }

        return msg.toString();

    }

}





