package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.PersoBureauDataRecord;
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

public class PersoBureauPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewPersoBureau;

    @FindBy(id = "btn-save")
    WebElement btnSaveChanges;

    @FindBy(id = "btn-cancel-edit")
    WebElement btnCancel;

    @FindBy(id = "input-id-dummy")
    WebElement txtId;

    @FindBy(id = "input-name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "input-code")
    WebElement txtCode;

    @FindBy(id = "val-code")
    WebElement msgCode;

    @FindBy(id = "input-destinationDir")
    WebElement txtDestinationDirectory;

    @FindBy(id = "val-destinationDir")
    WebElement msgDestinationDirectory;

    @FindBy(id = "input-ez")
    WebElement selEncryptionZone;

    @FindBy(id = "val-ez")
    WebElement msgEncryptionZone;

//    @FindBy(id = "input-extractPan")
//    WebElement cbxExtractPan;

    @FindBy(id = "input-extractPanDisplay")
    WebElement cbxExtractPanDisplay;

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

    public PersoBureauPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Perso bureaus");
    }

    public void clickAddButton(){

        btnAddNewPersoBureau.click();

    }

    public boolean checkRequiredFieldMessages(String field, String message) {

        boolean rc = true;
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        switch (field) {

            case("Name"):
                txtName.clear();
                txtName.click();
                txtName.sendKeys(Keys.TAB);
                if (!msgName.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Code"):
                txtCode.clear();
                txtCode.click();
                txtCode.sendKeys(Keys.TAB);
                if (!msgCode.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Destination directory"):
                txtDestinationDirectory.clear();
                txtDestinationDirectory.click();
                txtDestinationDirectory.sendKeys(Keys.TAB);
                if (!msgDestinationDirectory.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Encryption zone"):
                selEncryptionZone.click();
                selEncryptionZone.sendKeys(Keys.ESCAPE);
                txtDestinationDirectory.click();
                if (!msgEncryptionZone.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        btnCancel.click();

        return rc;

    }

    public PersoBureauDataRecord enterPersoBureauData(PersoBureauDataRecord newPersoBureau){

        PersoBureauDataRecord newRecord = new PersoBureauDataRecord();
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        btnAddNewPersoBureau.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selEncryptionZoneList = new Select(webDriver.findElement(By.id("input-ez")));

        txtName.clear();
        txtName.click();
        txtName.sendKeys(newPersoBureau.getName());

        txtCode.clear();
        txtCode.click();
        txtCode.sendKeys(newPersoBureau.getCode());

        txtDestinationDirectory.clear();
        txtDestinationDirectory.click();
        txtDestinationDirectory.sendKeys(newPersoBureau.getDestinationDirectory());

        selEncryptionZoneList.selectByVisibleText(newPersoBureau.getEncryptionZone());

        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newPersoBureau;
            newRecord.setTestOutput("Add Personalisation Bureau: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setId(null);
            newRecord.setName(null);
            newRecord.setCode(null);
            newRecord.setDestinationDirectory(null);
            newRecord.setEncryptionZone(null);
//            newRecord.setExtractPan(null);
            newRecord.setExtractPanDisplay(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return newRecord;
    }

    public PersoBureauDataRecord editPersoBureauData(PersoBureauDataRecord editPersoBureau, String editField){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewPersoBureau));

        Select selEncryptionZoneList;

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editPersoBureau.getId())) {

            // Edit the field required
            switch (editField) {

                case ("Name"):
                    txtName.clear();
                    txtName.click();
                    txtName.sendKeys(editPersoBureau.getName());

                case ("Code"):
                    txtCode.clear();
                    txtCode.click();
                    txtCode.sendKeys(editPersoBureau.getCode());

                case ("Destination directory"):
                    txtDestinationDirectory.clear();
                    txtDestinationDirectory.click();
                    txtDestinationDirectory.sendKeys(editPersoBureau.getDestinationDirectory());

                case ("Encryption zone"):
                    selEncryptionZoneList = new Select(webDriver.findElement(By.id("input-ez")));
                    selEncryptionZoneList.selectByVisibleText(editPersoBureau.getEncryptionZone());

//                case ("extractPAN"):
//                    adminCommon.selectCheckbox(cbxExtractPan, editPersoBureau.getExtractPan());
//                    break;

                case ("extractPANDisplay"):
                    adminCommon.selectCheckbox(cbxExtractPanDisplay, editPersoBureau.getExtractPanDisplay());
                    break;

            }
        }

        /* Click the Save button */
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();
        adminCommon.hardWait(1000);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {

            adminCommon.clickEditByUniqueId(editPersoBureau.getId());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch (editField) {

                case ("Name"):
                    if (txtName.getAttribute("value").equalsIgnoreCase(editPersoBureau.getName())) {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Name: SUCCESSFUL");
                    } else {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Name: UNSUCCESSFUL\n" +
                                "EXP: editPersoBureau.getName() = " + editPersoBureau.getName() + "\n" +
                                "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                    }
                    break;

                case ("Code"):
                    if (txtCode.getAttribute("value").equalsIgnoreCase(editPersoBureau.getCode())) {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Code: SUCCESSFUL");
                    } else {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Code: UNSUCCESSFUL\n" +
                                "EXP: editPersoBureau.getCode() = " + editPersoBureau.getCode() + "\n" +
                                "ACT: txtCode.getAttribute(\"value\") = " + txtCode.getAttribute("value"));
                    }
                    break;

                case ("Destination directory"):
                    if (txtDestinationDirectory.getAttribute("value").equalsIgnoreCase(editPersoBureau.getDestinationDirectory())) {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Destination Directory: SUCCESSFUL");
                    } else {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Destination Directory: UNSUCCESSFUL\n" +
                                "EXP: editPersoBureau.getDestinationDirectory() = " + editPersoBureau.getDestinationDirectory() + "\n" +
                                "ACT: txtDestinationDirectory.getAttribute(\"value\") = " + txtDestinationDirectory.getAttribute("value"));
                    }
                    break;

                case ("Encryption zone"):
                    selEncryptionZoneList = new Select(webDriver.findElement(By.id("input-ez")));
                    if (selEncryptionZoneList.getFirstSelectedOption().getText().equalsIgnoreCase(editPersoBureau.getEncryptionZone())) {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Encryption Zone: SUCCESSFUL");
                    } else {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Encryption Zone: UNSUCCESSFUL\n" +
                                "EXP: editPersoBureau.getEncryptionZone() = " + editPersoBureau.getEncryptionZone() + "\n" +
                                "ACT: selEncryptionZoneList.getFirstSelectedOption().getText() = " + selEncryptionZoneList.getFirstSelectedOption().getText());
                    }
                    break;

//                case ("extractPAN"):
//                    if (adminCommon.getCheckboxValue(cbxExtractPan).equalsIgnoreCase(editPersoBureau.getExtractPan())) {
//                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Extract PAN: SUCCESSFUL");
//                    } else {
//                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Extract PAN: UNSUCCESSFUL\n" +
//                                "EXP: editPersoBureau.getExtractPan() = " + editPersoBureau.getExtractPan() + "\n" +
//                                "ACT: adminCommon.getCheckboxValue(cbxExtractPan) = " + adminCommon.getCheckboxValue(cbxExtractPan));
//                    }
//                    break;

                case ("extractPANDisplay"):
                    if (adminCommon.getCheckboxValue(cbxExtractPanDisplay).equalsIgnoreCase(editPersoBureau.getExtractPanDisplay())) {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Extract PAN Display: SUCCESSFUL");
                    } else {
                        editPersoBureau.setTestOutput("Edit Personalisation Bureau Extract PAN Display: UNSUCCESSFUL\n" +
                                "EXP: editPersoBureau.getExtractPanDisplay() = " + editPersoBureau.getExtractPanDisplay() + "\n" +
                                "ACT: adminCommon.getCheckboxValue(cbxExtractPanDisplay) = " + adminCommon.getCheckboxValue(cbxExtractPanDisplay));
                    }
                    break;

            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewPersoBureau));

        }
        else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editPersoBureau.setId(null);
            editPersoBureau.setName(null);
            editPersoBureau.setCode(null);
            editPersoBureau.setEncryptionZone(null);
            editPersoBureau.setDestinationDirectory(null);
            editPersoBureau.setExtractPanDisplay(null);
//            editPersoBureau.setExtractPan(null);
            editPersoBureau.setTestOutput("Edit Personalisation Bureau: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        return editPersoBureau;

    }

    public PersoBureauDataRecord checkDeletePersoBureauById(PersoBureauDataRecord delPersoBureau){

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
            delPersoBureau.setTestOutput("Delete Personalisation Bureau: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            delPersoBureau.setId(null);
            delPersoBureau.setName(null);
            delPersoBureau.setCode(null);
            delPersoBureau.setEncryptionZone(null);
            delPersoBureau.setDestinationDirectory(null);
            delPersoBureau.setExtractPanDisplay(null);
//            delPersoBureau.setExtractPan(null);
            delPersoBureau.setTestOutput("Delete Personalisation Bureau: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(delPersoBureau.getName())){

                try {
                    btnModalYes.click();
                    delPersoBureau.setTestOutput("Delete Personalisation Bureau: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    delPersoBureau.setTestOutput("Delete Personalisation Bureau: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return delPersoBureau;
    }

    public PersoBureauDataRecord getDbIdByPersoBureauName(PersoBureauDataRecord key){

        PersoBureauDataRecord record = new PersoBureauDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM INT_D_PERSO_BUREAU " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, key.getName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setCode(null);
                record.setEncryptionZone(null);
                record.setDestinationDirectory(null);
                record.setExtractPanDisplay(null);
//                record.setExtractPan(null);
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

    public PersoBureauDataRecord getTableRecordByRecord(PersoBureauDataRecord editPersoBureau) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        PersoBureauDataRecord record = new PersoBureauDataRecord();

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setId(null);
                record.setName(null);

                return record;
            }

        }

        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + editPersoBureau.getId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setId(editPersoBureau.getId());
                record.setName(webDriver.findElement(By.id("col-name" + editPersoBureau.getId())).getAttribute("textContent"));
                record.setCode(webDriver.findElement(By.id("col-code" + editPersoBureau.getId())).getAttribute("textContent"));
                record.setDestinationDirectory(webDriver.findElement(By.id("col-destination-dir" + editPersoBureau.getId())).getAttribute("textContent"));
                record.setEncryptionZone(webDriver.findElement(By.id("col-encryptZone" + editPersoBureau.getId())).getAttribute("textContent"));

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
                    record.setCode(null);
                    record.setDestinationDirectory(null);
                    record.setEncryptionZone(null);

                    return record;
                }

            }

        }while (!found);

        return record;
    }

    public PersoBureauDataRecord getDBDataById(String instId){

        PersoBureauDataRecord record = new PersoBureauDataRecord();

        try {
            String getIssByIdSQL = "SELECT IDPB.ID, IDPB.NAME, IDPB.BUREAU_CODE, IDPB.DESTINATION_DIRECTORY, " +
                    "EDEZ.NAME, IDPB.EXTRACT_PAN_FLAG, " +
                    "CASE " +
                    "WHEN IDPB.EXTRACT_PAN_DISPLAY_FLAG = 'Y' THEN 'true' " +
                    "WHEN IDPB.EXTRACT_PAN_DISPLAY_FLAG = 'N' THEN 'false' " +
                    "END AS EXTRACT_PAN_DISPLAY_FLAG " +
                    "FROM INT_D_PERSO_BUREAU IDPB " +
                    "LEFT JOIN ENC_D_ENCRYPTION_ZONE EDEZ ON IDPB.APM_BUREAU_ENC_ZONE_ID=EDEZ.ID " +
                    "WHERE IDPB.ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()) {
                record.setId(null);
                record.setName(null);
                record.setCode(null);
                record.setDestinationDirectory(null);
                record.setEncryptionZone(null);
//                record.setExtractPan(null);
                record.setExtractPanDisplay(null);

            }
            else{
                rs.next();
                record.setId(instId);
                record.setName(rs.getString(2));
                record.setCode(rs.getString(3));
                record.setDestinationDirectory(rs.getString(4));
                record.setEncryptionZone(rs.getString(5));
//                record.setExtractPan(rs.getString(6));
                record.setExtractPanDisplay(rs.getString(7));

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

                        case ("Back_Slash"):
                        case ("Greater_Than"):
                        case ("Less_Than"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("Code"):

                obStringId = "val-code";
                expMessage = "Code is required";
                expMessage2 = "\"<\", \">\" and \"\\\" are not valid characters";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtCode.click();
                    txtCode.clear();
                    txtCode.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Test cases with no error message
                        case ("Blank"):
                        case ("Spaces"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case ("Back_Slash"):
                        case ("Greater_Than"):
                        case ("Less_Than"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("Destination directory"):

                obStringId = "val-destinationDir";
                expMessage = "Destination directory is required";
                expMessage2 = "Destination directory must be a valid directory path";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtDestinationDirectory.click();
                    txtDestinationDirectory.clear();
                    txtDestinationDirectory.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Test cases with no error message
                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case ("Back_Slash"):
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Hexadecimal_Value"):
                        case ("String_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                    }

                }

                break;


        }

        return msg.toString();

    }
}
