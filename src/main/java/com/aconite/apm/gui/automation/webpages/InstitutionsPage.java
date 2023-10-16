package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.InstitutionDataRecord;
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

public class InstitutionsPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewInstitution;

    @FindBy(id = "btn-add-zone")
    WebElement btnSaveInstitution;

    @FindBy(id = "btn-save-zone")
    WebElement btnSaveChanges;

    @FindBy(id = "btn-cancel-edit")
    WebElement btnCancel;

    @FindBy(id = "id")
    WebElement txtId;

    @FindBy(id = "name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "sel-pan-enc-key")
    WebElement selLocalZone;

    @FindBy(id = "val-localZone")
    WebElement msgLocalZone;

    @FindBy(id = "sel-pin-blk-fmt")
    WebElement selInstitutionZone;

    @FindBy(id = "val-institutionZone")
    WebElement msgInstitutionZone;

    @FindBy(id = "sel-pin-enc-key")
    WebElement selInterfaceZone;

    @FindBy(id = "val-interfaceZone")
    WebElement msgInterfaceZone;

    @FindBy(id = "maxVPPPINID")
    WebElement txtMaxVppPinIdSeconds;

    @FindBy(id = "val-maxVPPPINID")
    WebElement msgMaxVppPinIdSeconds;

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


    public InstitutionsPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);

    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Institutions");
    }

    public void clickAddButton(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewInstitution));

        btnAddNewInstitution.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

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

            case("LocalZone"):
                selLocalZone.click();
                selLocalZone.sendKeys(Keys.TAB);
                selLocalZone.sendKeys(Keys.TAB);
                if (!msgLocalZone.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("InstitutionZone"):
                selInstitutionZone.click();
                selInstitutionZone.sendKeys(Keys.TAB);
                selInstitutionZone.sendKeys(Keys.TAB);
                if (!msgInstitutionZone.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("InterfaceZone"):
                selInterfaceZone.click();
                selInterfaceZone.sendKeys(Keys.TAB);
                selInterfaceZone.sendKeys(Keys.TAB);
                if (!msgInterfaceZone.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("MaxVppPinIdSeconds"):
                txtMaxVppPinIdSeconds.clear();
                txtMaxVppPinIdSeconds.click();
                txtMaxVppPinIdSeconds.sendKeys(Keys.TAB);
                if (!msgMaxVppPinIdSeconds.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;

    }

    public InstitutionDataRecord enterInstitutionData(InstitutionDataRecord newInst){

        InstitutionDataRecord newRecord = new InstitutionDataRecord();
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        // Click Add button
        btnAddNewInstitution.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selLocalZoneList = new Select(webDriver.findElement(By.id("sel-pan-enc-key")));
        Select selInstitutionZoneList = new Select(webDriver.findElement(By.id("sel-pin-blk-fmt")));
        Select selInterfaceZoneList = new Select(webDriver.findElement(By.id("sel-pin-enc-key")));

        txtName.sendKeys(newInst.getInstitutionName());
        selLocalZoneList.selectByVisibleText(newInst.getLocalZone());
        selInstitutionZoneList.selectByVisibleText(newInst.getInstitutionZone());
        selInterfaceZoneList.selectByVisibleText(newInst.getInterfaceZone());
        txtMaxVppPinIdSeconds.sendKeys(newInst.getMaxVppPinIdSeconds());

        myWait.until(ExpectedConditions.visibilityOf(btnSaveInstitution));
        btnSaveInstitution.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newInst;
            newRecord.setTestOutput("Add Institution: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setId(null);
            newRecord.setInstitutionName(null);
            newRecord.setInstitutionZone(null);
            newRecord.setInterfaceZone(null);
            newRecord.setLocalZone(null);
            newRecord.setMaxVppPinIdSeconds(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        return newRecord;

    }

    public InstitutionDataRecord editInstitutionData(InstitutionDataRecord recordEditInst, String instField){

        Select selLocalZoneList;
        Select selInstitutionZoneList;
        Select selInterfaceZoneList;

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        myWait.until(ExpectedConditions.visibilityOf(btnAddNewInstitution));

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(recordEditInst.getId())) {

            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            selLocalZoneList = new Select(webDriver.findElement(By.id("sel-pan-enc-key")));
            selInstitutionZoneList = new Select(webDriver.findElement(By.id("sel-pin-blk-fmt")));
            selInterfaceZoneList = new Select(webDriver.findElement(By.id("sel-pin-enc-key")));

            // Edit the field required
            switch (instField) {

                case ("Name"):
                    txtName.clear();
                    txtName.sendKeys(recordEditInst.getInstitutionName());
                    break;

                case ("LocalZone"):
                    selLocalZoneList.selectByVisibleText(recordEditInst.getLocalZone());
                    break;

                case ("InstitutionZone"):
                    selInstitutionZoneList.selectByVisibleText(recordEditInst.getInstitutionZone());
                    break;

                case ("InterfaceZone"):
                    selInterfaceZoneList.selectByVisibleText(recordEditInst.getInterfaceZone());
                    break;

                case ("MaxVppPinIdSeconds"):
                    txtMaxVppPinIdSeconds.clear();
                    txtMaxVppPinIdSeconds.sendKeys(recordEditInst.getMaxVppPinIdSeconds());
                    break;

            }
        }
        else{
            recordEditInst.setId(null);
            recordEditInst.setInstitutionName(null);
            recordEditInst.setLocalZone(null);
            recordEditInst.setInstitutionName(null);
            recordEditInst.setInterfaceZone(null);
            recordEditInst.setMaxVppPinIdSeconds(null);
            recordEditInst.setTestOutput("Edit Institution: Click Edit Button UNSUCCESSFUL");
            return recordEditInst;
        }

        /* Click the Save button */
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();

        adminCommon.hardWait(1000);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0) {

            /* Click the Edit button to check change */
            adminCommon.findObjectByColumn("ID",recordEditInst.getId());
            webDriver.findElement(By.xpath("//td[@id=\"col-edit" + recordEditInst.getId() + "\"]/div/a")).click();

            selLocalZoneList = new Select(webDriver.findElement(By.id("sel-pan-enc-key")));
            selInstitutionZoneList = new Select(webDriver.findElement(By.id("sel-pin-blk-fmt")));
            selInterfaceZoneList = new Select(webDriver.findElement(By.id("sel-pin-enc-key")));

            // Check the edited field
            switch(instField){

                case("Name"):
                    if(txtName.getAttribute("value").equalsIgnoreCase(recordEditInst.getInstitutionName()))
                        {
                            recordEditInst.setTestOutput("Edit Institution Name: SUCCESSFUL");
                        }
                        else{
                            recordEditInst.setTestOutput("Edit Institution Name: UNSUCCESSFUL\n" +
                                "EXP: recordEditInst.getInstitutionName() = " + recordEditInst.getInstitutionName() + "\n" +
                                "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                        }
                    break;

                case("LocalZone"):
                    if(selLocalZoneList.getFirstSelectedOption().getText().equalsIgnoreCase(recordEditInst.getLocalZone()))
                        {
                            recordEditInst.setTestOutput("Edit Institution Local Zone: SUCCESSFUL");
                        }
                        else{
                            recordEditInst.setTestOutput("Edit Institution Local Zone: UNSUCCESSFUL\n" +
                                "EXP: recordEditInst.getLocalZone() = " + recordEditInst.getLocalZone() + "\n" +
                                "ACT: selLocalZoneList.getFirstSelectedOption().getText() = " + selLocalZoneList.getFirstSelectedOption().getText());
                        }
                    break;

                case("InstitutionZone"):
                     if(selInstitutionZoneList.getFirstSelectedOption().getText().equalsIgnoreCase(recordEditInst.getInstitutionZone()))
                        {
                            recordEditInst.setTestOutput("Edit Institution Institution Zone: SUCCESSFUL");
                        }
                        else{
                            recordEditInst.setTestOutput("Edit Institution Institution Zone: UNSUCCESSFUL\n" +
                                 "EXP: recordEditInst.getInstitutionZone() = " + recordEditInst.getInstitutionZone() + "\n" +
                                 "ACT: selInstitutionZoneList.getFirstSelectedOption().getText() = " + selInstitutionZoneList.getFirstSelectedOption().getText());System.out.println("ACT: selInstitutionZoneList.getFirstSelectedOption().getText() = " + selInstitutionZoneList.getFirstSelectedOption().getText());
                        }
                     break;

                case("InterfaceZone"):
                    if(selInterfaceZoneList.getFirstSelectedOption().getText().equalsIgnoreCase(recordEditInst.getInterfaceZone()))
                        {
                            recordEditInst.setTestOutput("Edit Institution Interface Zone: SUCCESSFUL");
                        }
                        else{
                            recordEditInst.setTestOutput("Edit Institution Interface Zone: UNSUCCESSFUL\n" +
                                "EXP: recordEditInst.getInterfaceZone() = " + recordEditInst.getInterfaceZone() + "\n" +
                                "ACT: selInterfaceZoneList.getFirstSelectedOption().getText() = " + selInterfaceZoneList.getFirstSelectedOption().getText());System.out.println("ACT: selInstitutionZoneList.getFirstSelectedOption().getText() = " + selInstitutionZoneList.getFirstSelectedOption().getText());
                        }
                    break;


                case("MaxVppPinIdSeconds"):
                     if(txtMaxVppPinIdSeconds.getAttribute("value").equalsIgnoreCase(recordEditInst.getMaxVppPinIdSeconds()))
                        {
                            recordEditInst.setTestOutput("Edit Institution Max Vpp Pin Id Seconds: SUCCESSFUL");
                        }
                        else{
                            recordEditInst.setTestOutput("Edit Institution Max Vpp Pin Id Seconds: UNSUCCESSFUL\n" +
                                 "EXP: recordEditInst.getMaxVppPinIdSeconds() = " + recordEditInst.getMaxVppPinIdSeconds() + "\n" +
                                 "ACT: txtMaxVppPinIdSeconds.getAttribute(\"value\") = " + txtMaxVppPinIdSeconds.getAttribute("value"));
                        }
                     break;

            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewInstitution));

        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            recordEditInst.setId(null);
            recordEditInst.setInstitutionName(null);
            recordEditInst.setLocalZone(null);
            recordEditInst.setInstitutionName(null);
            recordEditInst.setInterfaceZone(null);
            recordEditInst.setMaxVppPinIdSeconds(null);
            recordEditInst.setTestOutput("Edit Institution: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        return recordEditInst;

    }

    public boolean clickDeleteInstitutionById(InstitutionDataRecord delInstitution){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        // Click delete
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewInstitution));
        adminCommon.sleep(500);
        adminCommon.clickDeleteByUniqueId(delInstitution.getId());

        // Click Yes
        myWait.until(ExpectedConditions.visibilityOf(modalDialog));

        if(modalDialog.getAttribute("textContent").contains(delInstitution.getInstitutionName())){
            try {
                btnModalYes.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        else{
            try {
                btnModalNo.click();
            }
            catch(ElementClickInterceptedException ignored){}
            return false;
        }

        return true;

    }

    public InstitutionDataRecord checkAndDeleteInstitution(InstitutionDataRecord editInstitutionRecord){

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
            editInstitutionRecord.setTestOutput("Delete Institution: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editInstitutionRecord.setId(null);
            editInstitutionRecord.setInstitutionName(null);
            editInstitutionRecord.setLocalZone(null);
            editInstitutionRecord.setInstitutionName(null);
            editInstitutionRecord.setInterfaceZone(null);
            editInstitutionRecord.setMaxVppPinIdSeconds(null);
            editInstitutionRecord.setTestOutput("Delete Institution: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(editInstitutionRecord.getInstitutionName())){

                try {
                    btnModalYes.click();
                    editInstitutionRecord.setTestOutput("Delete Institution: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    editInstitutionRecord.setTestOutput("Delete Institution: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return editInstitutionRecord;

    }

    public InstitutionDataRecord getDbIdByInstitutionName(InstitutionDataRecord inst) throws Exception{

        InstitutionDataRecord record = new InstitutionDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM C_D_INSTITUTION " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, inst.getInstitutionName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setInstitutionName(null);
                record.setMaxVppPinIdSeconds(null);
                record.setLocalZone(null);
                record.setInstitutionZone(null);
                record.setInterfaceZone(null);

            }
            else{
                rs.next();
                record = inst;
                record.setId(rs.getString(1));
            }

            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public InstitutionDataRecord getTableDataById(InstitutionDataRecord institution){

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        InstitutionDataRecord record = new InstitutionDataRecord();

        btnFirstPage.click();
        adminCommon.hardWait(1000);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + institution.getId() + "']"));

            if(rows.size()>0){

                found = true;
                adminCommon.hardWait(500);
                record.setId(institution.getId());
                record.setInstitutionName(webDriver.findElement(By.id("col-name" + institution.getId())).getAttribute("textContent"));
                record.setLocalZone(webDriver.findElement(By.id("col-localEncryptionZone" + institution.getId())).getAttribute("textContent"));
                record.setInstitutionZone(webDriver.findElement(By.id("col-institutionEncryptionZone" + institution.getId())).getAttribute("textContent"));
                record.setInterfaceZone(webDriver.findElement(By.id("col-interfaceEncryptionZone" + institution.getId())).getAttribute("textContent"));
                record.setMaxVppPinIdSeconds(webDriver.findElement(By.id("col-maxVppPinIDLifetime" + institution.getId())).getAttribute("textContent"));

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
                    record.setInstitutionName(null);
                    record.setLocalZone(null);
                    record.setInstitutionName(null);
                    record.setInterfaceZone(null);
                    record.setMaxVppPinIdSeconds(null);
                    return(record);
                }

            }

        }while (!found);

        return(record);
    }

    public InstitutionDataRecord getDBDataById(String instId) throws Exception{

        InstitutionDataRecord record = new InstitutionDataRecord();

        try {

            String getInstByIdSQL = "SELECT CDI.ID, CDI.NAME, EDEZ1.NAME, " +
                    "EDEZ2.NAME, EDEZ3.NAME, CDI.MAX_VPPPINID_LIFETIME " +
                    "FROM C_D_INSTITUTION CDI " +
                    "LEFT JOIN  ENC_D_ENCRYPTION_ZONE EDEZ1 ON CDI.APM_LOCAL_ENC_ZONE_ID = EDEZ1.ID " +
                    "LEFT JOIN  ENC_D_ENCRYPTION_ZONE EDEZ2 ON CDI.APM_INSTITUTION_ENC_ZONE_ID = EDEZ2.ID " +
                    "LEFT JOIN  ENC_D_ENCRYPTION_ZONE EDEZ3 ON CDI.APM_IF_ENC_ZONE_ID = EDEZ3.ID " +
                    "WHERE CDI.ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getInstByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setInstitutionName(null);
                record.setLocalZone(null);
                record.setInstitutionZone(null);
                record.setInterfaceZone(null);
                record.setMaxVppPinIdSeconds(null);
            }
            else{

            rs.next();

            record.setId(rs.getString(1));
            record.setInstitutionName(rs.getString(2));
            record.setLocalZone(rs.getString(3));
            record.setInstitutionZone(rs.getString(4));
            record.setInterfaceZone(rs.getString(5));
            record.setMaxVppPinIdSeconds(rs.getString(6));

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
                expMessage = "Institution name is required";

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

            case ("Max VPP PIN ID seconds"):

                obStringId = "val-maxVPPPINID";
                expMessage = "Max VPP PIN ID seconds must be a decimal value";
                expMessage2 = "Max VPP PIN ID seconds is required";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtMaxVppPinIdSeconds.click();
                    txtMaxVppPinIdSeconds.clear();
                    txtMaxVppPinIdSeconds.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Test cases with no error message
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        // Test cases with expMessage2
                        case ("Blank"):
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
