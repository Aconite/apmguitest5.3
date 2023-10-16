package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.EncryptionZoneDataRecord;
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

public class EncryptionZonePage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewZone;

    @FindBy(id = "btn-add-zone")
    WebElement btnSubmitNewZone;

    @FindBy(id = "btn-save-zone")
    WebElement btnSaveKey;

    @FindBy(id = "btn-upload")
    WebElement btnUploadKey;

    @FindBy(id = "btn-cancel-edit")
    WebElement btnCancel;

    @FindBy(className = "alert-dialog")
    WebElement alertDialog;

    @FindBy(id = "btn-ok")
    WebElement btnOK;

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

    @FindBy(id = "id")
    WebElement txtId;

    @FindBy(id = "name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "pan-enc")
    WebElement cbxPanEncrypted;

    @FindBy(id = "sel-pan-enc-key")
    WebElement selPanEncryptionKeyId;

    @FindBy(id = "val-panEncryptionKeyId")
    WebElement msgPanEncryptionKeyId;

    @FindBy(id = "sel-pin-blk-fmt")
    WebElement selPinBlockFormat;

    @FindBy(id = "val-pinBlockFormatId")
    WebElement msgPinBlockFormat;

    @FindBy(id = "sel-pin-enc-key")
    WebElement selPinEncryptionKeyId;

    @FindBy(id = "val-pinEncryptionKeyId")
    WebElement msgPinEncryptionKeyId;

    @FindBy(id = "sel-puk-blk-fmt")
    WebElement selPukBlockFormat;

    @FindBy(id = "val-pukBlockFormatId")
    WebElement msgPukBlockFormat;

    @FindBy(id = "sel-puk-enc-key")
    WebElement selPukEncryptionKeyId;

    @FindBy(id = "val-pukEncryptionKeyId")
    WebElement msgPukEncryptionKeyId;

    @FindBy(id = "sel-pin-session")
    WebElement selPinSession;

    @FindBy(id = "val-pinKeySessionStaticFlag")
    WebElement msgPinSession;

    @FindBy(id = "sel-puk-session")
    WebElement selPukSession;

    @FindBy(id = "val-pukKeySessionStaticFlag")
    WebElement msgPukSession;

    @FindBy(id = "input-excludedPins")
    WebElement txtExcludedPins;

    @FindBy(id = "input-pukEncryptedFlag")
    WebElement cbxPukEncrypted;

    public EncryptionZonePage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Encryption zone");
    }

    public void clickAddButton(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewZone));

        try {
            btnAddNewZone.click();
        }
        catch(ElementClickInterceptedException ignored){}

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

    }

    public boolean checkRequiredFields(String field, String message){

        boolean rc = true;
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        switch (field) {

            case ("Name"):
                txtName.clear();
                txtName.click();
                txtName.sendKeys(Keys.TAB);
                if (!msgName.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("PAN encryption key"):
                selPanEncryptionKeyId.click();
                selPanEncryptionKeyId.sendKeys(Keys.TAB);
                selPanEncryptionKeyId.sendKeys(Keys.TAB);
                if (!msgPanEncryptionKeyId.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("PIN block format"):
                selPinBlockFormat.click();
                selPinBlockFormat.sendKeys(Keys.TAB);
                selPinBlockFormat.sendKeys(Keys.TAB);
                if (!msgPinBlockFormat.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;


            case ("PIN encryption key"):
                selPinEncryptionKeyId.click();
                selPinEncryptionKeyId.sendKeys(Keys.TAB);
                selPinEncryptionKeyId.sendKeys(Keys.TAB);
                if (!msgPinEncryptionKeyId.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("PUK block format"):
                selPukBlockFormat.click();
                selPukBlockFormat.sendKeys(Keys.TAB);
                selPukBlockFormat.sendKeys(Keys.TAB);
                if (!msgPukBlockFormat.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("PUK encryption key"):
                selPukEncryptionKeyId.click();
                selPukEncryptionKeyId.sendKeys(Keys.TAB);
                selPukEncryptionKeyId.sendKeys(Keys.TAB);
                if (!msgPukEncryptionKeyId.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("PIN session"):
                selPinSession.click();
                selPinSession.sendKeys(Keys.TAB);
                selPinSession.sendKeys(Keys.TAB);
                if (!msgPinSession.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

           case ("PUK session"):
               selPukSession.click();
               selPukSession.sendKeys(Keys.TAB);
               selPukSession.sendKeys(Keys.TAB);
                if (!msgPukSession.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;

    }

    public EncryptionZoneDataRecord enterEncryptionZoneData(EncryptionZoneDataRecord newEncryptionZone) {

        EncryptionZoneDataRecord newRecord = new EncryptionZoneDataRecord();

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewZone));

        try {
            btnAddNewZone.click();
        }
        catch(ElementClickInterceptedException ignored){}
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selPanEncryptionKey = new Select(webDriver.findElement(By.id("sel-pan-enc-key")));
        Select selPinBlockFormat = new Select(webDriver.findElement(By.id("sel-pin-blk-fmt")));
        Select selPinEncryptionKey = new Select(webDriver.findElement(By.id("sel-pin-enc-key")));
        Select selPukBlockFormat = new Select(webDriver.findElement(By.id("sel-puk-blk-fmt")));
        Select selPukEncryptionKey = new Select(webDriver.findElement(By.id("sel-puk-enc-key")));
        Select selPinSession = new Select(webDriver.findElement(By.id("sel-pin-session")));
        Select selPukSession = new Select(webDriver.findElement(By.id("sel-puk-session")));


        txtName.clear();
        txtName.sendKeys(newEncryptionZone.getName());

        adminCommon.hardWait(500);

        String cbxChecked = cbxPanEncrypted.getAttribute("checked");
        if(cbxChecked==null){
            cbxChecked = "false";
        }
        if(cbxChecked.equalsIgnoreCase("true") && newEncryptionZone.getPanEncrypted().equalsIgnoreCase("false")){
            cbxPanEncrypted.click();
        }
        if(cbxChecked.equalsIgnoreCase("false") && newEncryptionZone.getPanEncrypted().equalsIgnoreCase("true")){
            cbxPanEncrypted.click();
        }

        adminCommon.hardWait(500);

        selPanEncryptionKey.selectByVisibleText(newEncryptionZone.getPanEncryptionKey());

        selPinBlockFormat.selectByVisibleText(newEncryptionZone.getPinBlockFormat());

        selPinEncryptionKey.selectByVisibleText(newEncryptionZone.getPinEncryptionKey());

        adminCommon.hardWait(500);

        cbxChecked = cbxPukEncrypted.getAttribute("checked");
        if(cbxChecked==null){
            cbxChecked = "false";
        }
        if(cbxChecked.equalsIgnoreCase("true") && newEncryptionZone.getPukEncrypted().equalsIgnoreCase("false")){
            cbxPukEncrypted.click();
        }
        if(cbxChecked.equalsIgnoreCase("false") && newEncryptionZone.getPukEncrypted().equalsIgnoreCase("true")){
            cbxPukEncrypted.click();
        }

        adminCommon.hardWait(500);

        selPukBlockFormat.selectByVisibleText(newEncryptionZone.getPukBlockFormat());

        selPukEncryptionKey.selectByVisibleText(newEncryptionZone.getPukEncryptionKey());

        selPinSession.selectByVisibleText(newEncryptionZone.getPinSession());

        selPukSession.selectByVisibleText(newEncryptionZone.getPukSession());

        adminCommon.hardWait(500);

        myWait.until(ExpectedConditions.visibilityOf(btnSubmitNewZone));
        btnSubmitNewZone.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newEncryptionZone;
            newRecord.setTestOutput("Add Encryption Zone: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setName(null);
            newRecord.setPanEncrypted(null);
            newRecord.setPanEncryptionKey(null);
            newRecord.setPinBlockFormat(null);
            newRecord.setPinEncryptionKey(null);
            newRecord.setPukEncrypted(null);
            newRecord.setPukBlockFormat(null);
            newRecord.setPukEncryptionKey(null);
            newRecord.setPinSession(null);
            newRecord.setPukSession(null);
            newRecord.setTestOutput("Add Encryption Zone: UNSUCCESSFUL\n" + alertDialog.getText());

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return newRecord;
    }

    public EncryptionZoneDataRecord editEncryptionZoneData(EncryptionZoneDataRecord editEncryptionZoneRecord, String editField){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        Select selPanEncryptionKeyList;
        Select selPinBlockFormatList;
        Select selPinEncryptionKeyList;
        Select selPukBlockFormatList;
        Select selPukEncryptionKeyList;
        Select selPinSessionList;
        Select selPukSessionList;
        Select selRsaKeyList;

        myWait.until(ExpectedConditions.visibilityOf(btnAddNewZone));

        adminCommon.sortTableByColumn("ID","DESC");

        if(adminCommon.clickEditByUniqueId(editEncryptionZoneRecord.getDbId())) {

            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            // Edit the field required
            switch (editField) {

                case ("PAN encryption key"):
                    selPanEncryptionKeyList = new Select(webDriver.findElement(By.id("sel-pan-enc-key")));
                    selPanEncryptionKeyList.selectByVisibleText(editEncryptionZoneRecord.getPanEncryptionKey());
                    break;

                case ("PIN block format"):
                    selPinBlockFormatList = new Select(webDriver.findElement(By.id("sel-pin-blk-fmt")));
                    selPinBlockFormatList.selectByVisibleText(editEncryptionZoneRecord.getPinBlockFormat());
                    break;

                case ("PIN encryption key"):
                    selPinEncryptionKeyList = new Select(webDriver.findElement(By.id("sel-pin-enc-key")));
                    selPinEncryptionKeyList.selectByVisibleText(editEncryptionZoneRecord.getPinEncryptionKey());
                    break;

                case ("Excluded PINs"):
                    txtExcludedPins.clear();
                    txtExcludedPins.sendKeys(editEncryptionZoneRecord.getExcludedPins());
                    break;

                case ("PUK block format"):
                    selPukBlockFormatList = new Select(webDriver.findElement(By.id("sel-puk-blk-fmt")));
                    selPukBlockFormatList.selectByVisibleText(editEncryptionZoneRecord.getPukBlockFormat());
                    break;

                case ("PUK encryption key"):
                    selPukEncryptionKeyList = new Select(webDriver.findElement(By.id("sel-puk-enc-key")));
                    selPukEncryptionKeyList.selectByVisibleText(editEncryptionZoneRecord.getPukEncryptionKey());
                    break;

                case ("PIN session"):
                    selPinSessionList = new Select(webDriver.findElement(By.id("sel-pin-session")));
                    selPinSessionList.selectByVisibleText(editEncryptionZoneRecord.getPinSession());
                    break;

                case ("PUK session"):
                    selPukSessionList = new Select(webDriver.findElement(By.id("sel-puk-session")));
                    selPukSessionList.selectByVisibleText(editEncryptionZoneRecord.getPukSession());
                    break;

                case ("RSA key"):
                    selRsaKeyList = new Select(webDriver.findElement(By.id("sel-rsa-key")));
                    selRsaKeyList.selectByVisibleText(editEncryptionZoneRecord.getRsaKey());
                    break;

                case ("PAN encrypted"):
                    adminCommon.selectCheckbox(cbxPanEncrypted,"false");
                    break;

                case ("PUK encrypted"):
                    adminCommon.selectCheckbox(cbxPukEncrypted,"false");
                    break;

            }

            /* Click the Save button */
            myWait.until(ExpectedConditions.visibilityOf(btnSaveKey));
            btnSaveKey.click();

            adminCommon.hardWait(1000);

            /* Check for the alert dialog */
            List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
            if(alertDialogList.size()==0) {

            /* Click the Edit button to check change */
            adminCommon.sortTableByColumn("ID","DESC");
            adminCommon.clickEditByUniqueId(editEncryptionZoneRecord.getDbId());

            // Edit the field required
            switch (editField) {

                case ("PAN encryption key"):
                    selPanEncryptionKeyList = new Select(webDriver.findElement(By.id("sel-pan-enc-key")));
                    if(selPanEncryptionKeyList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getPanEncryptionKey()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PAN Encryption Key: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PAN Encryption Key: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPanEncryptionKey() = " + editEncryptionZoneRecord.getPanEncryptionKey() + "\n" +
                                "ACT: selPanEncryptionKeyList.getFirstSelectedOption().getText() = " + selPanEncryptionKeyList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PIN block format"):
                    selPinBlockFormatList = new Select(webDriver.findElement(By.id("sel-pin-blk-fmt")));
                    if(selPinBlockFormatList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getPinBlockFormat()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PIN Block Format: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PIN Block Format: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPinBlockFormat() = " + editEncryptionZoneRecord.getPinBlockFormat() + "\n" +
                                "ACT: selPinBlockFormatList.getFirstSelectedOption().getText() = " + selPinBlockFormatList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PIN encryption key"):
                    selPinEncryptionKeyList = new Select(webDriver.findElement(By.id("sel-pin-enc-key")));
                    if(selPinEncryptionKeyList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getPinEncryptionKey()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PIN Encryption Key: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PIN Encryption Key: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPinEncryptionKey() = " + editEncryptionZoneRecord.getPinEncryptionKey() + "\n" +
                                "ACT: selPinEncryptionKeyList.getFirstSelectedOption().getText() = " + selPinEncryptionKeyList.getFirstSelectedOption().getText());
                        }
                    break;

                case ("Excluded PINs"):
                    if(txtExcludedPins.getAttribute("value").equalsIgnoreCase(editEncryptionZoneRecord.getExcludedPins()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone Excluded PINs: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone Excluded PINs: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getExcludedPins() = " + editEncryptionZoneRecord.getExcludedPins() + "\n" +
                                "ACT: txtExcludedPins.getAttribute(\"value\") = " + txtExcludedPins.getAttribute("value"));
                    }
                    break;

                case ("PUK block format"):
                    selPukBlockFormatList = new Select(webDriver.findElement(By.id("sel-puk-blk-fmt")));
                    if(selPukBlockFormatList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getPukBlockFormat()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Block Format: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Block Format: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPukBlockFormat() = " + editEncryptionZoneRecord.getPukBlockFormat() + "\n" +
                                "ACT: selPukBlockFormatList.getFirstSelectedOption().getText() = " + selPukBlockFormatList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PUK encryption key"):
                    selPukEncryptionKeyList = new Select(webDriver.findElement(By.id("sel-puk-enc-key")));
                    if(selPukEncryptionKeyList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getPukEncryptionKey()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Encryption Key: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Encryption Key: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPukEncryptionKey() = " + editEncryptionZoneRecord.getPukEncryptionKey() + "\n" +
                                "ACT: selPukEncryptionKeyList.getFirstSelectedOption().getText() = " + selPukEncryptionKeyList.getFirstSelectedOption().getText());
                        }
                    break;

                case ("PIN session"):
                    selPinSessionList = new Select(webDriver.findElement(By.id("sel-pin-session")));
                    if(selPinSessionList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getPinSession()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PIN Session: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PIN Session: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPinSession() = " + editEncryptionZoneRecord.getPinSession() + "\n" +
                                "ACT: selPinSessionList.getFirstSelectedOption().getText() = " + selPinSessionList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PUK session"):
                    selPukSessionList = new Select(webDriver.findElement(By.id("sel-puk-session")));
                    if(selPukSessionList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getPukSession()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Session: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Session: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPukSession() = " + editEncryptionZoneRecord.getPukSession() + "\n" +
                                "ACT: selPukSessionList.getFirstSelectedOption().getText() = " + selPukSessionList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("RSA key"):
                    selRsaKeyList = new Select(webDriver.findElement(By.id("sel-rsa-key")));
                    if(selRsaKeyList.getFirstSelectedOption().getText().equalsIgnoreCase(editEncryptionZoneRecord.getRsaKey()))
                    {
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone RSA Key: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone RSA Key: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getRsaKey() = " + editEncryptionZoneRecord.getRsaKey() + "\n" +
                                "ACT: selRsaKeyList.getFirstSelectedOption().getText() = " + selRsaKeyList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PAN encrypted"):
                    String cbx1 = adminCommon.getCheckboxValue(cbxPanEncrypted);
                    WebElement panEncryptionKey = webDriver.findElement(By.xpath("//select[@id='sel-pan-enc-key']/.."));
                    if(cbx1.equalsIgnoreCase(editEncryptionZoneRecord.getPanEncrypted()) && panEncryptionKey.getAttribute("className").contains("disabled")){
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PAN Encrypted: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PAN Encrypted: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPanEncrypted() = " + editEncryptionZoneRecord.getPanEncrypted() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPanEncrypted) = " + adminCommon.getCheckboxValue(cbxPanEncrypted));
                    }
                    break;

                case ("PUK encrypted"):
                    String cbx2 = adminCommon.getCheckboxValue(cbxPukEncrypted);
                    WebElement pukBlockFormat = webDriver.findElement(By.xpath("//select[@id='sel-puk-blk-fmt']/.."));
                    WebElement pukEncryptionKey = webDriver.findElement(By.xpath("//select[@id='sel-puk-enc-key']/.."));
                    if(cbx2.equalsIgnoreCase(editEncryptionZoneRecord.getPanEncrypted()) && pukEncryptionKey.getAttribute("className").contains("disabled") && pukBlockFormat.getAttribute("className").contains("disabled")){
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Encrypted: SUCCESSFUL");
                    }
                    else{
                        editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone PUK Encrypted: UNSUCCESSFUL\n" +
                                "EXP: editEncryptionZoneRecord.getPukEncrypted() = " + editEncryptionZoneRecord.getPukEncrypted() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxPukEncrypted) = " + adminCommon.getCheckboxValue(cbxPukEncrypted));
                    }
                    break;


            }

            btnCancel.click();
            adminCommon.hardWait(1000);

            }
            else{
                System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
                editEncryptionZoneRecord.setName(null);
                editEncryptionZoneRecord.setPanEncrypted(null);
                editEncryptionZoneRecord.setPanEncryptionKey(null);
                editEncryptionZoneRecord.setPinBlockFormat(null);
                editEncryptionZoneRecord.setPinEncryptionKey(null);
                editEncryptionZoneRecord.setPukEncrypted(null);
                editEncryptionZoneRecord.setPukBlockFormat(null);
                editEncryptionZoneRecord.setPukEncryptionKey(null);
                editEncryptionZoneRecord.setPinSession(null);
                editEncryptionZoneRecord.setPukSession(null);
                editEncryptionZoneRecord.setTestOutput("Edit Encryption Zone: UNSUCCESSFUL\n" +  alertDialog.getText());
                btnOK.click();
            }

        }

        return  editEncryptionZoneRecord;

    }

    public EncryptionZoneDataRecord checkAndDeleteEncryptionZone(EncryptionZoneDataRecord editEncryptionZoneRecord){

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
            editEncryptionZoneRecord.setTestOutput("Delete Encryption Zone: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editEncryptionZoneRecord.setName(null);
            editEncryptionZoneRecord.setPanEncrypted(null);
            editEncryptionZoneRecord.setPanEncryptionKey(null);
            editEncryptionZoneRecord.setPinBlockFormat(null);
            editEncryptionZoneRecord.setPinEncryptionKey(null);
            editEncryptionZoneRecord.setPukEncrypted(null);
            editEncryptionZoneRecord.setPukBlockFormat(null);
            editEncryptionZoneRecord.setPukEncryptionKey(null);
            editEncryptionZoneRecord.setPinSession(null);
            editEncryptionZoneRecord.setPukSession(null);
            editEncryptionZoneRecord.setTestOutput("Delete Encryption Zone: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(editEncryptionZoneRecord.getName())){

                try {
                    btnModalYes.click();
                    editEncryptionZoneRecord.setTestOutput("Delete Encryption Zone: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    editEncryptionZoneRecord.setTestOutput("Delete Encryption Zone: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return editEncryptionZoneRecord;

    }

//    public boolean clickDeleteEncryptionZoneById(EncryptionZoneDataRecord delEncryptionZone){
//
//        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
//        myWait.until(ExpectedConditions.visibilityOf(btnAddNewZone));
//
//        adminCommon.sortTableByColumn("ID","DESC");
//        adminCommon.clickDeleteByUniqueId(delEncryptionZone.getDbId());
//        adminCommon.hardWait(1000);
//
//        // Click Yes
//        if(modalDialog.getAttribute("textContent").contains(delEncryptionZone.getName())) {
//            try {
//
//                myWait.until(ExpectedConditions.visibilityOf(btnModalYes));
//                btnModalYes.click();
//
//                myWait.until(ExpectedConditions.visibilityOf(btnAddNewZone));
//
//            } catch (ElementClickInterceptedException ignored) {
//            }
//        }
//        else{
//            try {
//                btnModalNo.click();
//            }
//            catch(ElementClickInterceptedException ignored){}
//            return false;
//        }
//
//
//        return true;
//    }

    public EncryptionZoneDataRecord getDbIdByEncryptionZoneName(EncryptionZoneDataRecord zone) throws Exception{

        EncryptionZoneDataRecord record = new EncryptionZoneDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM ENC_D_ENCRYPTION_ZONE " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, zone.getName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setName(null);
                record.setPanEncrypted(null);
                record.setPanEncryptionKey(null);
                record.setPinBlockFormat(null);
                record.setPinEncryptionKey(null);
                record.setPukEncrypted(null);
                record.setPukBlockFormat(null);
                record.setPukEncryptionKey(null);
                record.setPinSession(null);
                record.setPukSession(null);
            }
            else{
                rs.next();
                record = zone;
                record.setDbId(rs.getString(1));

            }
            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public EncryptionZoneDataRecord getTableRecordByRecord(EncryptionZoneDataRecord zone) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        EncryptionZoneDataRecord record = new EncryptionZoneDataRecord();

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setDbId(null);
                record.setName(null);

                return record;
            }

        }

        adminCommon.sortTableByColumn("ID","DESC");
        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + zone.getDbId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setDbId(zone.getDbId());
                record.setName(webDriver.findElement(By.id("col-name" + zone.getDbId())).getAttribute("textContent"));
                record.setPanEncrypted(webDriver.findElement(By.id("col-panEncryptedFlag" + zone.getDbId())).getAttribute("textContent"));
                record.setPanEncryptionKey(webDriver.findElement(By.id("col-sciPanEncryptionKey" + zone.getDbId())).getAttribute("textContent"));
                record.setPinBlockFormat(webDriver.findElement(By.id("col-pinBlockFormat" + zone.getDbId())).getAttribute("textContent"));
                record.setPinEncryptionKey(webDriver.findElement(By.id("col-sciPinEncryptionKey" + zone.getDbId())).getAttribute("textContent"));
                record.setPukEncrypted(webDriver.findElement(By.id("col-pukEncryptedFlag" + zone.getDbId())).getAttribute("textContent"));
                //record.setPukBlockFormat(webDriver.findElement(By.id("col-pukBlockFormat" + zone.getDbId())).getAttribute("textContent"));
                //record.setPukEncryptionKey(webDriver.findElement(By.id("col-sciPukEncryptionKey" + zone.getDbId())).getAttribute("textContent"));

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
                    record.setDbId(null);
                    record.setName(null);
                    record.setPanEncrypted(null);
                    record.setPanEncryptionKey(null);
                    record.setPinBlockFormat(null);
                    record.setPinEncryptionKey(null);
                    record.setPukEncrypted(null);
                    record.setPukBlockFormat(null);
                    record.setPukEncryptionKey(null);

                    return record;
                }

            }

        }while (!found);

        return record;
    }

    public EncryptionZoneDataRecord getDBDataById(String instId) throws Exception{

        EncryptionZoneDataRecord record = new EncryptionZoneDataRecord();

        try {

            String getKeyByIdSQL = "SELECT EDEZ.ID, EDEZ.NAME, " +
                    "CASE " +
                    "WHEN EDEZ.PAN_ENCRYPTED_FLAG = 'Y' THEN 'true' " +
                    "WHEN EDEZ.PAN_ENCRYPTED_FLAG = 'N' THEN 'false' " +
                    "END AS PAN_ENCRYPTED_FLAG, " +
                    "EDS1.NAME, " +
                    "ESPBF1.DESCRIPTION, EDS2.NAME, NVL(EDEZ.EXCLUDED_PINS,' '), " +
                    "CASE " +
                    "WHEN EDEZ.PUK_ENCRYPTED_FLAG = 'Y' THEN 'true' " +
                    "WHEN EDEZ.PUK_ENCRYPTED_FLAG = 'N' THEN 'false' " +
                    "END AS PUK_ENCRYPTED_FLAG, " +
                    "ESPBF2.DESCRIPTION, EDS3.NAME, " +
                    "CASE " +
                    "WHEN EDEZ.PIN_KEY_SESSION_STATIC_FLAG = 'S' THEN 'Static' " +
                    "WHEN EDEZ.PIN_KEY_SESSION_STATIC_FLAG = 'D' THEN 'Dynamic' " +
                    "END AS PIN_KEY_SESSION_STATIC_FLAG, " +
                    "CASE " +
                    "WHEN EDEZ.PUK_KEY_SESSION_STATIC_FLAG = 'S' THEN 'Static' " +
                    "WHEN EDEZ.PUK_KEY_SESSION_STATIC_FLAG = 'D' THEN 'Dynamic' " +
                    "END AS PUK_KEY_SESSION_STATIC_FLAG, " +
                    "NVL(EDS4.NAME, ' ') " +
                    "FROM ENC_D_ENCRYPTION_ZONE EDEZ " +
                    "LEFT JOIN ENC_D_SCI EDS1 ON EDEZ.SCI_PAN_ENCRYPTION_KEY_ID = EDS1.ID " +
                    "LEFT JOIN ENC_D_SCI EDS2 ON EDEZ.SCI_PIN_ENCRYPTION_KEY_ID = EDS2.ID " +
                    "LEFT JOIN ENC_D_SCI EDS3 ON EDEZ.SCI_PUK_ENCRYPTION_KEY_ID = EDS3.ID " +
                    "LEFT JOIN ENC_D_SCI EDS4 ON EDEZ.SCI_SESSION_ENCRYPTION_KEY_ID = EDS4.ID " +
                    "LEFT JOIN ENC_S_PIN_BLOCK_FORMAT ESPBF1 ON EDEZ.PIN_BLOCK_FORMAT_ID = ESPBF1.ID " +
                    "LEFT JOIN ENC_S_PIN_BLOCK_FORMAT ESPBF2 ON EDEZ.PUK_BLOCK_FORMAT_ID = ESPBF2.ID " +
                    "WHERE EDEZ.ID = ?";

            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getKeyByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setDbId(null);
                record.setName(null);
                record.setPanEncrypted(null);
                record.setPanEncryptionKey(null);
                record.setPinBlockFormat(null);
                record.setPinEncryptionKey(null);
                record.setPukEncrypted(null);
                record.setPukBlockFormat(null);
                record.setPukEncryptionKey(null);
                record.setRsaKey(null);
            }
            else{
                rs.next();

                record.setDbId(rs.getString(1));
                record.setName(rs.getString(2));
                record.setPanEncrypted(rs.getString(3));
                record.setPanEncryptionKey(rs.getString(4));
                record.setPinBlockFormat(rs.getString(5));
                record.setPinEncryptionKey(rs.getString(6));
                record.setExcludedPins(rs.getString(7));
                record.setPukEncrypted(rs.getString(8));
                record.setPukBlockFormat(rs.getString(9));
                record.setPukEncryptionKey(rs.getString(10));
                record.setPinSession(rs.getString(11));
                record.setPukSession(rs.getString(12));
                record.setRsaKey(rs.getString(13));

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

        ValidationTestcases validationTestcases = new ValidationTestcases();


        switch (field) {

            case ("Name"):

                obStringId = "val-name";
                expMessage = "Name is required";

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

            case ("Excluded PINS"):

                obStringId = "val-excludedPins";
                expMessage = "Invalid excluded PIN format";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtExcludedPins.click();
                    txtExcludedPins.clear();
                    txtExcludedPins.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                        case ("Excluded_Pins"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
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
