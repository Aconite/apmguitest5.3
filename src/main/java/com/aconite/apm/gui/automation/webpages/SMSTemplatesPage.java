package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.SMSTemplateDataRecord;
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


public class SMSTemplatesPage {


    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewSmsTemplate;

    @FindBy(id = "btn-add")
    WebElement btnSaveNewSmsTemplate;

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

    @FindBy(id = "input-id-dummy")
    WebElement txtId;

    @FindBy(id = "input-name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "input-institution")
    WebElement selInstitution;

    @FindBy(id = "val-institution")
    WebElement msgInstitution;

    @FindBy(id = "input-templateType")
    WebElement selTemplateType;

    @FindBy(id = "val-templateType")
    WebElement msgTemplateType;

    @FindBy(id = "filter-template-name")
    WebElement txtFilterName;



    public SMSTemplatesPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return(pageTitle.getText().equalsIgnoreCase("SMS templates"));
    }

    public void clickAddButton(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewSmsTemplate));
        btnAddNewSmsTemplate.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));
    }

    public void searchSmsTemplates(String institution, String templateType, String templateName){

        Select selInstitution = new Select(webDriver.findElement(By.id("filter-institution")));
        Select selTemplateType = new Select(webDriver.findElement(By.id("filter-template-type")));

        // Click Reset button
        btnReset.click();

        if(!institution.equalsIgnoreCase("")) {
            selInstitution.selectByVisibleText(institution);
        }

        if(!templateName.equalsIgnoreCase("")) {
            txtFilterName.clear();
            txtFilterName.sendKeys(templateName);
        }

        if(!templateType.equalsIgnoreCase("")) {
            selTemplateType.selectByVisibleText(templateType);
        }

        adminCommon.hardWait(1000);
    }

    public boolean checkRequiredFieldMessages(String field, String message) {

        boolean rc = true;

        switch (field) {

            case("Name"):
                txtName.clear();
                txtName.click();
                try {
                    txtId.click();
                }
                catch(ElementClickInterceptedException ignored){}
                if (!msgName.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("InstitutionName"):
                selInstitution.click();
                try {
                    txtId.click();
                }
                catch(ElementClickInterceptedException ignored){}
                if (!msgInstitution.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("TemplateType"):
                selTemplateType.click();
                try {
                    txtId.click();
                }
                catch(ElementClickInterceptedException ignored){}
                if (!msgTemplateType.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        btnCancel.click();

        return rc;

    }

    public SMSTemplateDataRecord enterSMSTemplateData(SMSTemplateDataRecord newSmsTemplate) {

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        SMSTemplateDataRecord record = new SMSTemplateDataRecord();

        // Click Reset button
        btnReset.click();

        // Click Add button
        btnAddNewSmsTemplate.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selTemplateTypeList = new Select(webDriver.findElement(By.id("input-templateType")));
        Select selInstitutionList = new Select(webDriver.findElement(By.id("input-institution")));

        txtName.clear();
        txtName.click();
        txtName.sendKeys(newSmsTemplate.getName());

        selInstitutionList.selectByVisibleText(newSmsTemplate.getInstitutionName());
        selTemplateTypeList.selectByVisibleText(newSmsTemplate.getTemplateType());

        btnSaveNewSmsTemplate.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            record = newSmsTemplate;
            record.setTestOutput("Add SMS Template: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            record.setId(null);
            record.setInstitutionName(null);
            record.setTemplateType(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return record;
    }

    public SMSTemplateDataRecord editSMSTemplateData(SMSTemplateDataRecord editSMSTemplate, String instField){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);

        // Click Reset button
        btnReset.click();
        searchSmsTemplates(editSMSTemplate.getInstitutionName(), editSMSTemplate.getTemplateType(), "");

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editSMSTemplate.getId())) {

            // Edit the field required
            switch (instField) {

                case ("Name"):
                    txtName.clear();
                    txtName.sendKeys(editSMSTemplate.getName());
                    txtName.sendKeys(Keys.TAB);
                    break;

            }

        }

        /* Click the Save button */
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();

        adminCommon.hardWait(500);
        btnReset.click();
        adminCommon.hardWait(500);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {

            searchSmsTemplates(editSMSTemplate.getInstitutionName(), editSMSTemplate.getTemplateType(), editSMSTemplate.getName());

            adminCommon.clickEditByUniqueId(editSMSTemplate.getId());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch(instField) {

                case ("Name"):
                    if (txtName.getAttribute("value").equalsIgnoreCase(editSMSTemplate.getName())) {
                        editSMSTemplate.setTestOutput("Edit SMS Template Name: SUCCESSFUL");
                    } else {
                        editSMSTemplate.setTestOutput("Edit SMS Template Name: UNSUCCESSFUL\n" +
                                "EXP: editSMSTemplate.getName() = " + editSMSTemplate.getName() + "\n" +
                                "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                    }
                    break;

            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewSmsTemplate));
            // Click Reset button
            btnReset.click();

        }
        else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editSMSTemplate.setId(null);
            editSMSTemplate.setName(null);
            editSMSTemplate.setInstitutionName(null);
            editSMSTemplate.setTemplateType(null);
            editSMSTemplate.setTestOutput("Edit SMS Template: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        btnReset.click();

        return editSMSTemplate;
    }

    public SMSTemplateDataRecord checkDeleteSmsTemplateById(SMSTemplateDataRecord delSmsTemplate){

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
            delSmsTemplate.setTestOutput("Delete SMS Template: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            delSmsTemplate.setId(null);
            delSmsTemplate.setName(null);
            delSmsTemplate.setInstitutionName(null);
            delSmsTemplate.setTemplateType(null);
            delSmsTemplate.setTestOutput("Delete SMS Template: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(delSmsTemplate.getName())){

                try {
                    btnModalYes.click();
                    delSmsTemplate.setTestOutput("Delete SMS Template: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    delSmsTemplate.setTestOutput("Delete SMS Template: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        btnReset.click();

        return delSmsTemplate;
    }

    public SMSTemplateDataRecord getDBDataById(String instId) throws Exception{

        SMSTemplateDataRecord record = new SMSTemplateDataRecord();

        try {

            String getIssByIdSQL = "SELECT PMDT.ID, PMDT.NAME, INST.NAME, PMST.NAME " +
                    "FROM PM_D_TEMPLATE PMDT " +
                    "LEFT JOIN  C_D_INSTITUTION INST ON PMDT.INSTITUTION_ID = INST.ID " +
                    "LEFT JOIN  PM_S_TEMPLATE_TYPE PMST ON PMDT.TYPE_ID = PMST.ID " +
                    "WHERE PMDT.ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setInstitutionName(null);
                record.setTemplateType(null);
            }
            else{

                rs.next();

                record.setId(rs.getString(1));
                record.setName(rs.getString(2));
                record.setInstitutionName(rs.getString(3));
                record.setTemplateType(rs.getString(4));


            }
            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public SMSTemplateDataRecord getTableRecordByRecord(SMSTemplateDataRecord editSmsTemplate) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        SMSTemplateDataRecord record = new SMSTemplateDataRecord();
        // Click Reset button
        btnReset.click();
        searchSmsTemplates(editSmsTemplate.getInstitutionName(), editSmsTemplate.getTemplateType(), editSmsTemplate.getName());

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setId(null);
                record.setName(null);
                record.setInstitutionName(null);
                record.setTemplateType(null);
                return record;
            }

        }

        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + editSmsTemplate.getId() + "']"));

            if(rows.size()>0){

                found = true;
                record.setId(editSmsTemplate.getId());
                record.setInstitutionName(webDriver.findElement(By.id("col-institution" + editSmsTemplate.getId())).getAttribute("textContent"));
                record.setName(webDriver.findElement(By.id("col-name" + editSmsTemplate.getId())).getAttribute("textContent"));
                record.setTemplateType(webDriver.findElement(By.id("col-type" + editSmsTemplate.getId())).getAttribute("textContent"));
                btnReset.click();

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
                    record.setTemplateType(null);
                    btnReset.click();
                    return record;
                }

            }

        }while (!found);

        btnReset.click();
        return record;
    }

    public SMSTemplateDataRecord getDbIdBySmsTemplateName(SMSTemplateDataRecord key){

        SMSTemplateDataRecord record = new SMSTemplateDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM PM_D_TEMPLATE " +
                    "WHERE NAME = ? ";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, key.getName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setInstitutionName(null);
                record.setTemplateType(null);
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

        }

        return msg.toString();

    }
    

}





