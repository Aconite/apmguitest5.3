package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.KeyDataRecord;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import com.aconite.apm.gui.automation.testcases.ValidationTestcases;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class KeyPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddKey;

    @FindBy(id = "btn-reset-filter")
    WebElement btnResetAll;

    @FindBy(id = "search-keyname")
    WebElement txtSearchKeyName;

    @FindBy(id = "search-isactive")
    WebElement cbxSearchOnlyActive;

    @FindBy(id = "input-key-id-dummy")
    WebElement txtId;

    @FindBy(id = "input-key-status")
    WebElement selStatus;

    @FindBy(id = "input-key-type")
    WebElement selKeyType;

    @FindBy(id = "input-key-index")
    WebElement txtKeyIndex;

    @FindBy(id = "val-key-index")
    WebElement msgKeyIndex;

    @FindBy(id = "input-key-dectable")
    WebElement txtDecimalisationTable;

    @FindBy(id = "input-key-is-dectable-enc")
    WebElement cbxDecimalisationTableEncryption;

    @FindBy(id = "input-key-validate-pattern")
    WebElement txtValidationPattern;

    @FindBy(id = "input-key-name")
    WebElement txtName;

    @FindBy(id = "input-key-hsmtype")
    WebElement selHsmType;

    @FindBy(id = "input-key-mkcv")
    WebElement txtMkCheckValue;

    @FindBy(id = "input-key-sci-keytype")
    WebElement txtSciKeyType;

    @FindBy(id = "input-key-keyalgo")
    WebElement selKeyAlgorithm;

    @FindBy(id = "input-key-blocksize")
    WebElement txtBlockSize;

    @FindBy(id = "input-key-value")
    WebElement txtKeyValue;

    @FindBy(id = "input-key-kcv")
    WebElement txtKeyCheckValue;

    @FindBy(id = "input-key-expirydate")
    WebElement txtExpiryDate;

    @FindBy(id = "input-key-ext")
    WebElement txtExtension;

    @FindBy(id = "btn-save")
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



    public KeyPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Keys");
    }

    public void clickAddButton(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddKey));

        btnAddKey.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

    }

    public void searchKey(String keyType, String keyName){

        Select selKeyType = new Select(webDriver.findElement(By.id("search-keytype")));

        adminCommon.selectCheckbox(cbxSearchOnlyActive, "false");

        if(!keyType.equalsIgnoreCase("")) {
            selKeyType.selectByVisibleText(keyType);
        }

        if(!keyName.equalsIgnoreCase("")) {
            txtSearchKeyName.clear();
            txtSearchKeyName.sendKeys(keyName);
        }

        adminCommon.hardWait(1000);
    }

    public boolean checkRequiredFields(String field, String message){

        boolean rc = true;

        switch (field) {

            case ("Status"):
                selStatus.click();
                txtKeyIndex.click();
                if (!webDriver.findElement(By.id("val-key-status")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("Key type"):
                selKeyType.click();
                txtKeyIndex.click();
                if (!webDriver.findElement(By.id("val-key-type")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("Name"):
                txtName.clear();
                txtName.click();
                txtName.sendKeys(Keys.TAB);
                if (!webDriver.findElement(By.id("val-key-name")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("HSM type"):
                selHsmType.click();
                txtKeyIndex.click();
                if (!webDriver.findElement(By.id("val-key-hsmtype")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("MK check value"):
                txtMkCheckValue.clear();
                txtMkCheckValue.click();
                txtMkCheckValue.sendKeys(Keys.TAB);
                if (!webDriver.findElement(By.id("val-key-mkcv")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("Key algorithm"):
                selKeyAlgorithm.click();
                txtKeyIndex.click();
                if (!webDriver.findElement(By.id("val-key-keyalgo")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("Block size"):
                txtBlockSize.clear();
                txtBlockSize.click();
                txtBlockSize.sendKeys(Keys.TAB);
                if (!webDriver.findElement(By.id("val-key-blocksize")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

           case ("Key value"):
                txtKeyValue.clear();
                txtKeyValue.click();
                txtKeyValue.sendKeys(Keys.TAB);
                if (!webDriver.findElement(By.id("val-key-value")).getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;

    }

    public KeyDataRecord enterKeyData(KeyDataRecord newKey) {

        KeyDataRecord newRecord = new KeyDataRecord();

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddKey));

        btnAddKey.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selStatusList = new Select(webDriver.findElement(By.id("input-key-status")));
        Select selKeyTypeList = new Select(webDriver.findElement(By.id("input-key-type")));
        Select selHsmTypeList = new Select(webDriver.findElement(By.id("input-key-hsmtype")));
        Select selKeyAlgorithmList = new Select(webDriver.findElement(By.id("input-key-keyalgo")));

        selStatusList.selectByVisibleText(newKey.getStatus());
        selKeyTypeList.selectByVisibleText(newKey.getKeyType());
        txtName.clear();
        txtName.sendKeys(newKey.getName());
        selHsmTypeList.selectByVisibleText(newKey.getHsmType());
        txtMkCheckValue.clear();
        txtMkCheckValue.sendKeys(newKey.getMkCheckValue());
        selKeyAlgorithmList.selectByVisibleText(newKey.getKeyAlgorithm());
        txtBlockSize.clear();
        txtBlockSize.sendKeys(newKey.getBlockSize());
        txtKeyValue.clear();
        txtKeyValue.sendKeys(newKey.getKeyValue());

        myWait.until(ExpectedConditions.visibilityOf(btnSaveKey));
        btnSaveKey.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newKey;
            newRecord.setTestOutput("Add Key: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setStatus(null);
            newRecord.setKeyType(null);
            newRecord.setName(null);
            newRecord.setHsmType(null);
            newRecord.setKeyCheckValue(null);
            newRecord.setKeyAlgorithm(null);
            newRecord.setBlockSize(null);
            newRecord.setKeyValue(null);
            newRecord.setTestOutput("Add Key: UNSUCCESSFUL\n" + alertDialog.getText());

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return newRecord;
    }

    public KeyDataRecord editKeyData(KeyDataRecord editKeyRecord, String editField){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        myWait.until(ExpectedConditions.visibilityOf(txtSearchKeyName));

        switch(editField){

            case("Name"):
                searchKey(editKeyRecord.getKeyType(), "");
                break;
            case("Key type"):
                searchKey("", editKeyRecord.getName());
                break;
            default:
                searchKey(editKeyRecord.getKeyType(), editKeyRecord.getName());
                break;
        }

        myWait.until(ExpectedConditions.visibilityOf(btnResetAll));

        /* Click the Edit button */

        if(adminCommon.clickEditByUniqueId(editKeyRecord.getDbId())) {

            Select selStatusList = new Select(webDriver.findElement(By.id("input-key-status")));
            Select selKeyTypeList = new Select(webDriver.findElement(By.id("input-key-type")));
            Select selHsmTypeList = new Select(webDriver.findElement(By.id("input-key-hsmtype")));
            Select selKeyAlgorithmList = new Select(webDriver.findElement(By.id("input-key-keyalgo")));

            // Edit the field required
            switch (editField) {

                case ("Name"):
                    txtName.clear();
                    txtName.sendKeys(editKeyRecord.getName());
                    break;

                case ("Status"):
                    selStatusList.selectByVisibleText(editKeyRecord.getStatus());
                    break;

                case ("Key type"):
                    selKeyTypeList.selectByVisibleText(editKeyRecord.getKeyType());
                    break;

                case ("Index"):
                    txtKeyIndex.clear();
                    txtKeyIndex.sendKeys(editKeyRecord.getIndex());
                    break;

                case ("Decimalisation table"):
                    txtDecimalisationTable.clear();
                    txtDecimalisationTable.sendKeys(editKeyRecord.getDecimalisationTable());
                    break;

                case ("Decimalisation table encryption"):
                    adminCommon.selectCheckbox(cbxDecimalisationTableEncryption, editKeyRecord.getDecimalisationTableEncryption());
                    break;

                case ("Validation pattern"):
                    txtValidationPattern.clear();
                    txtValidationPattern.sendKeys(editKeyRecord.getValidationPattern());
                    break;

                case ("HSM type"):
                    selHsmTypeList.selectByVisibleText(editKeyRecord.getHsmType());
                    break;

                case ("MK check value"):
                    txtMkCheckValue.clear();
                    txtMkCheckValue.sendKeys(editKeyRecord.getMkCheckValue());
                    break;

                case ("SCI key type"):
                    txtSciKeyType.clear();
                    txtSciKeyType.sendKeys(editKeyRecord.getSciKeytype());
                    break;

                case ("Key algorithm"):
                    selKeyAlgorithmList.selectByVisibleText(editKeyRecord.getKeyAlgorithm());
                    break;

                case ("Block size"):
                    txtBlockSize.clear();
                    txtBlockSize.sendKeys(editKeyRecord.getBlockSize());
                    break;

                case ("Key value"):
                    txtKeyValue.clear();
                    txtKeyValue.sendKeys(editKeyRecord.getKeyValue());
                    break;

                case ("Key check value"):
                    txtKeyCheckValue.clear();
                    txtKeyCheckValue.sendKeys(editKeyRecord.getKeyCheckValue());
                    break;

                case ("Expiry date"):
                    txtExpiryDate.clear();
                    txtExpiryDate.sendKeys(editKeyRecord.getExpiryDate());
                    String startDay = editKeyRecord.getExpiryDate().substring(0,2);
                    String startMonth = editKeyRecord.getExpiryDate().substring(3,5);
                    String startYear = editKeyRecord.getExpiryDate().substring(6);
                    editKeyRecord.setExpiryDate(startYear +"-" + startMonth + "-" + startDay); // Hack to ensure exp res are correctly formatted
                    break;

                case ("Extension"):
                    txtExtension.clear();
                    txtExtension.sendKeys(editKeyRecord.getExtension());
                    break;
            }

            /* Click the Save button */
            myWait.until(ExpectedConditions.visibilityOf(btnSaveKey));
            btnSaveKey.click();

            adminCommon.hardWait(1000);

            /* Check for the alert dialog */
            List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
            if(alertDialogList.size()==0) {

                searchKey(editKeyRecord.getKeyType(), editKeyRecord.getName());

                myWait.until(ExpectedConditions.visibilityOf(btnResetAll));

                /* Click the Edit button to check change */
                adminCommon.clickEditByUniqueId(editKeyRecord.getDbId());
                myWait.until(ExpectedConditions.visibilityOf(btnCancel));

                // Edit the field required
                switch (editField) {

                    case ("Name"):
                        if (txtName.getAttribute("value").equalsIgnoreCase(editKeyRecord.getName())) {
                            editKeyRecord.setTestOutput("Edit Key Name: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Name: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getName() = " + editKeyRecord.getName() + "\n" +
                                    "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                        }
                        break;

                    case ("Status"):
                        selStatusList = new Select(webDriver.findElement(By.id("input-key-status")));
                        if (selStatusList.getFirstSelectedOption().getText().equalsIgnoreCase(editKeyRecord.getStatus())) {
                            editKeyRecord.setTestOutput("Edit Key Status: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Status: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getStatus() = " + editKeyRecord.getStatus() + "\n" +
                                    "ACT: selStatusList.getFirstSelectedOption().getText() = " + selStatusList.getFirstSelectedOption().getText());
                        }
                        break;

                    case ("Key type"):
                        selKeyTypeList = new Select(webDriver.findElement(By.id("input-key-type")));
                        if (selKeyTypeList.getFirstSelectedOption().getText().equalsIgnoreCase(editKeyRecord.getKeyType())) {
                            editKeyRecord.setTestOutput("Edit Key Type: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Type: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getKeyType() = " + editKeyRecord.getKeyType() + "\n" +
                                    "ACT: selKeyTypeList.getFirstSelectedOption().getText() = " + selKeyTypeList.getFirstSelectedOption().getText());
                        }
                        break;

                    case ("Index"):
                        if (txtKeyIndex.getAttribute("value").equalsIgnoreCase(editKeyRecord.getIndex())) {
                            editKeyRecord.setTestOutput("Edit Key Index: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Index: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getIndex() = " + editKeyRecord.getIndex() + "\n" +
                                    "ACT: txtKeyIndex.getAttribute(\"value\") = " + txtKeyIndex.getAttribute("value"));
                        }
                        break;

                    case ("Decimalisation table"):
                        if (txtDecimalisationTable.getAttribute("value").equalsIgnoreCase(editKeyRecord.getDecimalisationTable())) {
                            editKeyRecord.setTestOutput("Edit Key Decimalisation Table: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Decimalisation Table: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getDecimalisationTable() = " + editKeyRecord.getDecimalisationTable() + "\n" +
                                    "ACT: txtDecimalisationTable.getAttribute(\"value\") = " + txtDecimalisationTable.getAttribute("value"));
                        }
                        break;

                    case ("Decimalisation table encryption"):
                        if (adminCommon.getCheckboxValue(cbxDecimalisationTableEncryption).equalsIgnoreCase(editKeyRecord.getDecimalisationTableEncryption())) {
                            editKeyRecord.setTestOutput("Edit Key Decimalisation Table Encryption: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Decimalisation Table Encryption: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getDecimalisationTableEncryption() = " + editKeyRecord.getDecimalisationTableEncryption() + "\n" +
                                    "ACT: adminCommon.getCheckboxValue(cbxDecimalisationTableEncryption) =" + adminCommon.getCheckboxValue(cbxDecimalisationTableEncryption));
                        }
                        break;

                    case ("Validation pattern"):
                        if (txtValidationPattern.getAttribute("value").equalsIgnoreCase(editKeyRecord.getValidationPattern())) {
                            editKeyRecord.setTestOutput("Edit Key Validation Pattern: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Validation Pattern: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getValidationPattern() = " + editKeyRecord.getValidationPattern() + "\n" +
                                    "ACT: txtValidationPattern.getAttribute(\"value\") = " + txtValidationPattern.getAttribute("value"));
                        }
                        break;

                    case ("HSM type"):
                        selHsmTypeList = new Select(webDriver.findElement(By.id("input-key-hsmtype")));
                        if (selHsmTypeList.getFirstSelectedOption().getText().equalsIgnoreCase(editKeyRecord.getHsmType())) {
                            editKeyRecord.setTestOutput("Edit Key HSM Type: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key HSM Type: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getHsmType() = " + editKeyRecord.getHsmType() + "\n" +
                                    "ACT: selHsmTypeList.getFirstSelectedOption().getText() = " + selHsmTypeList.getFirstSelectedOption().getText());
                        }
                        break;

                    case ("MK check value"):
                        if (txtMkCheckValue.getAttribute("value").equalsIgnoreCase(editKeyRecord.getMkCheckValue())) {
                            editKeyRecord.setTestOutput("Edit Key MK Check Value: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key MK Check Value: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getMkCheckValue() = " + editKeyRecord.getMkCheckValue() + "\n" +
                                    "ACT: txtMkCheckValue.getAttribute(\"value\") = " + txtMkCheckValue.getAttribute("value"));
                        }
                        break;

                    case ("SCI key type"):
                        if (txtSciKeyType.getAttribute("value").equalsIgnoreCase(editKeyRecord.getSciKeytype())) {
                            editKeyRecord.setTestOutput("Edit Key SCI Key Type: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key SCI Key Type: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getSciKeytype() = " + editKeyRecord.getSciKeytype() + "\n" +
                                    "ACT: txtSciKeyType.getAttribute(\"value\") = " + txtSciKeyType.getAttribute("value"));
                        }
                        break;

                    case ("Key algorithm"):
                        selKeyAlgorithmList = new Select(webDriver.findElement(By.id("input-key-keyalgo")));
                        if (selKeyAlgorithmList.getFirstSelectedOption().getText().equalsIgnoreCase(editKeyRecord.getKeyAlgorithm())) {
                            editKeyRecord.setTestOutput("Edit Key Key Algorithm: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key HSM Type: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getKeyAlgorithm() = " + editKeyRecord.getKeyAlgorithm() + "\n" +
                                    "ACT: selKeyAlgorithmList.getFirstSelectedOption().getText() = " + selKeyAlgorithmList.getFirstSelectedOption().getText());
                        }
                        break;

                    case ("Block size"):
                        if (txtBlockSize.getAttribute("value").equalsIgnoreCase(editKeyRecord.getBlockSize())) {
                            editKeyRecord.setTestOutput("Edit Key Block Size: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Block Size: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getBlockSize() = " + editKeyRecord.getBlockSize() + "\n" +
                                    "ACT: txtBlockSize.getAttribute(\"value\") = " + txtBlockSize.getAttribute("value"));
                        }
                        break;

                    case ("Key value"):
                        if (txtKeyValue.getAttribute("value").equalsIgnoreCase(editKeyRecord.getKeyValue())) {
                            editKeyRecord.setTestOutput("Edit Key Key Value: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Key Value: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getKeyValue() = " + editKeyRecord.getKeyValue() + "\n" +
                                    "ACT: txtKeyValue.getAttribute(\"value\") = " + txtKeyValue.getAttribute("value"));
                        }
                        break;

                    case ("Key check value"):
                        if (txtKeyCheckValue.getAttribute("value").equalsIgnoreCase(editKeyRecord.getKeyCheckValue())) {
                            editKeyRecord.setTestOutput("Edit Key Key Check Value: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Key Check Value: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getKeyCheckValue() = " + editKeyRecord.getKeyCheckValue() + "\n" +
                                    "ACT: txtKeyCheckValue.getAttribute(\"value\") = " + txtKeyCheckValue.getAttribute("value"));
                        }
                        break;

                    case ("Expiry date"):
                        if (txtExpiryDate.getAttribute("value").equalsIgnoreCase(editKeyRecord.getExpiryDate())) {
                            editKeyRecord.setTestOutput("Edit Key Expiry Date: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Expiry Date: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getExpiryDate() = " + editKeyRecord.getExpiryDate() + "\n" +
                                    "ACT: txtExpiryDate.getAttribute(\"value\") = " + txtExpiryDate.getAttribute("value"));
                          }
                        break;

                    case ("Extension"):
                        if (txtExtension.getAttribute("value").equalsIgnoreCase(editKeyRecord.getExtension())) {
                            editKeyRecord.setTestOutput("Edit Key Extension: SUCCESSFUL");
                        } else {
                            editKeyRecord.setTestOutput("Edit Key Extension: UNSUCCESSFUL\n" +
                                    "EXP: editKeyRecord.getExtension() = " + editKeyRecord.getExtension() + "\n" +
                                    "ACT: txtExtension.getAttribute(\"value\") = " + txtExtension.getAttribute("value"));
                         }
                        break;

                }

                btnCancel.click();
                btnResetAll.click();
                adminCommon.hardWait(1000);

            }
            else{
                System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
                editKeyRecord.setStatus(null);
                editKeyRecord.setKeyType(null);
                editKeyRecord.setIndex(null);
                editKeyRecord.setDecimalisationTable(null);
                editKeyRecord.setValidationPattern(null);
                editKeyRecord.setDecimalisationTableEncryption(null);
                editKeyRecord.setHsmType(null);
                editKeyRecord.setSciKeytype(null);
                editKeyRecord.setKeyAlgorithm(null);
                editKeyRecord.setBlockSize(null);
                editKeyRecord.setKeyValue(null);
                editKeyRecord.setKeyCheckValue(null);
                editKeyRecord.setExpiryDate(null);
                editKeyRecord.setName(null);
                editKeyRecord.setMkCheckValue(null);
                editKeyRecord.setExtension(null);
                editKeyRecord.setTestOutput("Edit Key: UNSUCCESSFUL\n" +  alertDialog.getText());
                btnOK.click();
            }

        }

        return editKeyRecord;

    }

    public KeyDataRecord checkAndDeleteKey(KeyDataRecord editKeyRecord){

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
            editKeyRecord.setTestOutput("Delete Key: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editKeyRecord.setStatus(null);
            editKeyRecord.setKeyType(null);
            editKeyRecord.setIndex(null);
            editKeyRecord.setDecimalisationTable(null);
            editKeyRecord.setValidationPattern(null);
            editKeyRecord.setDecimalisationTableEncryption(null);
            editKeyRecord.setHsmType(null);
            editKeyRecord.setSciKeytype(null);
            editKeyRecord.setKeyAlgorithm(null);
            editKeyRecord.setBlockSize(null);
            editKeyRecord.setKeyValue(null);
            editKeyRecord.setKeyCheckValue(null);
            editKeyRecord.setExpiryDate(null);
            editKeyRecord.setName(null);
            editKeyRecord.setMkCheckValue(null);
            editKeyRecord.setExtension(null);
            editKeyRecord.setTestOutput("Delete Key: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(editKeyRecord.getName())){

                try {
                    btnModalYes.click();
                    editKeyRecord.setTestOutput("Delete Key: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    editKeyRecord.setTestOutput("Delete Key: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return editKeyRecord;

    }

    public KeyDataRecord getDbIdByKeyName(KeyDataRecord key) {

        KeyDataRecord record = new KeyDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM ENC_D_SCI " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, key.getName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setDbId(null);
                record.setStatus(null);
                record.setKeyType(null);
                record.setName(null);
                record.setHsmType(null);
                record.setKeyCheckValue(null);
                record.setKeyAlgorithm(null);
                record.setBlockSize(null);
                record.setKeyValue(null);
            }
            else{
                rs.next();
                record = key;
                record.setDbId(rs.getString(1));

            }
            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public KeyDataRecord getTableRecordByRecord(KeyDataRecord key) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        KeyDataRecord record = new KeyDataRecord();
        searchKey(key.getKeyType(), key.getName());

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setDbId(null);
                record.setName(null);

                return record;
            }

        }

        btnFirstPage.click();
        adminCommon.hardWait(500);
//        adminCommon.selectCheckbox(cbxSearchOnlyActive, "false");


        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + key.getDbId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setDbId(key.getDbId());
                record.setName(webDriver.findElement(By.id("col-name" + key.getDbId())).getAttribute("textContent"));
                record.setKeyType(webDriver.findElement(By.id("col-keytype" + key.getDbId())).getAttribute("textContent"));
                record.setStatus(webDriver.findElement(By.id("col-status" + key.getDbId())).getAttribute("textContent"));
                record.setExpiryDate(webDriver.findElement(By.id("col-expirydate" + key.getDbId())).getAttribute("textContent"));

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
                    record.setKeyType(null);
                    record.setStatus(null);
                    record.setExpiryDate(null);

                    return record;
                }

            }

        }while (!found);

        return record;
    }

    public KeyDataRecord getDBDataById(String instId){

        KeyDataRecord record = new KeyDataRecord();

        try {

            String getKeyByIdSQL = "SELECT ID, " +
                    "CASE WHEN STATUS = 'A' THEN 'Active' " +
                    "WHEN STATUS = 'I' THEN 'Inactive' END AS STATUS, " +
                    "CASE WHEN KEY_TYPE_ID = '1' THEN 'Zone PIN Key' " +
                    "WHEN KEY_TYPE_ID = '2' THEN 'PIN Verification Key' " +
                    "WHEN KEY_TYPE_ID = '3' THEN 'PAN Encryption Key' " +
                    "WHEN KEY_TYPE_ID = '4' THEN 'PIN Encryption Key (software encryption)' " +
                    "WHEN KEY_TYPE_ID = '5' THEN 'Key Export Key' " +
                    "WHEN KEY_TYPE_ID = '6' THEN 'PIN Generation Key' " +
                    "WHEN KEY_TYPE_ID = '7' THEN 'Asymmetric Import Key' " +
                    "WHEN KEY_TYPE_ID = '8' THEN 'Asymmetric Export Key' " +
                    "END AS KEYTYPE, " +
                    "KEY_INDEX, " +
                    "DECIMALISATION_TABLE, " +
                    "CASE " +
                    "WHEN DEC_TABLE_ENCRYPTED_FLAG = 'N' THEN 'false' " +
                    "WHEN DEC_TABLE_ENCRYPTED_FLAG = 'Y' THEN 'true' " +
                    "END AS DEC_TABLE_ENCRYPTED_FLAG, " +
                    "VALIDATION_DATA_PATTERN, " +
                    "NAME, " +
                    "CASE WHEN HSM_TYPE = 'THALES_PAYSHIELD_KB' THEN 'Thales payShield (keyblock)' " +
                    "WHEN HSM_TYPE = 'THALES_PAYSHIELD_VT' THEN 'Thales payShield (variant)' END, " +
                    "LMK_KCV, " +
                    "SCI_KEY_TYPE, " +
                    "SCI_KEY_ALGORITHM, " +
                    "BLOCK_SIZE, " +
                    "KEY_VALUE, " +
                    "KEY_KCV, " +
                    "TO_CHAR(EXPIRY_DATE, 'YYYY-MM-DD'), " +
                    "EXTENSION " +
                    "FROM ENC_D_SCI " +
                    "WHERE ID = ? ";

            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getKeyByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setDbId(null);
                record.setName(null);
            }
            else{
                rs.next();

                record.setDbId(rs.getString(1));
                record.setStatus(rs.getString(2));
                record.setKeyType(rs.getString(3));
                record.setIndex(rs.getString(4));
                record.setDecimalisationTable(rs.getString(5));
                record.setDecimalisationTableEncryption(rs.getString(6));
                record.setValidationPattern(rs.getString(7));
                record.setName(rs.getString(8));
                record.setHsmType(rs.getString(9));
                record.setMkCheckValue(rs.getString(10));
                record.setSciKeytype(rs.getString(11));
                record.setKeyAlgorithm(rs.getString(12));
                record.setBlockSize(rs.getString(13));
                record.setKeyValue(rs.getString(14));
                record.setKeyCheckValue(rs.getString(15));
                record.setExpiryDate(rs.getString(16));
                record.setExtension(rs.getString(17));
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
        String expMessage2;

        ValidationTestcases validationTestcases = new ValidationTestcases();


        switch (field) {

            case("Index"):

                obStringId = "val-key-index";
                expMessage = "Index must be a decimal value";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtKeyIndex.click();
                    txtKeyIndex.clear();
                    txtKeyIndex.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Excluded_Pins"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case("Decimalisation table"):

                obStringId = "val-key-dectable";
                expMessage = "Decimalisation table must be a hexadecimal value";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtDecimalisationTable.click();
                    txtDecimalisationTable.clear();
                    txtDecimalisationTable.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Hexadecimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case("Validation pattern"):

                obStringId = "val-key-validate-pattern";
                expMessage = "Must be a valid validation pattern";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtValidationPattern.click();
                    txtValidationPattern.clear();
                    txtValidationPattern.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Hexadecimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("Name"):

                obStringId = "val-key-name";
                expMessage = "Name is required";
                expMessage2 = "\"<\", \">\" and \"\\\" are not valid characters";

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

                        case ("Less_Than"):
                        case ("Greater_Than"):
                        case ("Back_Slash"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

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

            case ("MK check value"):

                obStringId = "val-key-mkcv";
                expMessage = "MK check value must be a hexadecimal value";
                expMessage2 = "MK check value is required";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtMkCheckValue.click();
                    txtMkCheckValue.clear();
                    txtMkCheckValue.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Hexadecimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("SCI key type"):

                obStringId = "val-key-sci-keytype";
                expMessage = "Must be a valid SCI key type";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtSciKeyType.click();
                    txtSciKeyType.clear();
                    txtSciKeyType.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Spaces"):
                        case ("String_Value"):
                        case ("Spaced_String"):
                        case ("Blank"):
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Hexadecimal_Value"):
                        case ("Acute_e_Hexadecimal_Value"):
                        case ("Grave_and_Acute_e_String"):
                        case ("Tilde_Character_String"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("Block size"):

                obStringId = "val-key-blocksize";
                expMessage = "Block size must be a decimal value";
                expMessage2 = "Block size is required";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtBlockSize.click();
                    txtBlockSize.clear();
                    txtBlockSize.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("Key check value"):

                obStringId = "val-key-kcv";
                expMessage = "Key check value must be a hexadecimal value";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtKeyCheckValue.click();
                    txtKeyCheckValue.clear();
                    txtKeyCheckValue.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Hexadecimal_Value"):
                        case ("Blank"):
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
