package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.SmsTemplateTextDataRecord;
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


public class SmsTemplateTextPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewSmsTemplateText;

    @FindBy(id = "btn-add")
    WebElement btnSaveNewSmsTemplateText;

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

    @FindBy(id = "input-template")
    WebElement selSmsTemplate;

    @FindBy(id = "val-template")
    WebElement msgSmsTemplate;

    @FindBy(id = "input-language")
    WebElement selLanguageCode;

    @FindBy(id = "val-language")
    WebElement msgLanguageCode;

    @FindBy(id = "input-text")
    WebElement txtTemplateText;

    @FindBy(id = "val-text")
    WebElement msgTemplateText;

    @FindBy(id = "filter-template-name")
    WebElement txtFilterName;



    public SmsTemplateTextPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);

    }

    public boolean isPageOpened() {
        return(pageTitle.getText().equalsIgnoreCase("SMS template texts"));
    }

    public void clickAddButton(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewSmsTemplateText));
        btnAddNewSmsTemplateText.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));
    }

    public void searchSmsTemplateText(String institution, String templateName, String templateType, String languageCode){

        btnReset.click();

        Select selInstitution = new Select(webDriver.findElement(By.id("filter-institution")));
        Select selTemplateType = new Select(webDriver.findElement(By.id("filter-template-type")));
        Select selLanguageCode = new Select(webDriver.findElement(By.id("filter-language")));

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

        if(!languageCode.equalsIgnoreCase("")) {
            selLanguageCode.selectByVisibleText(languageCode);
        }

        adminCommon.hardWait(1000);
    }

    public boolean checkRequiredFieldMessages(String field, String message) {

        boolean rc = true;
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        switch (field) {

            case("SMS template"):
                selSmsTemplate.click();
                try {
                    txtId.click();
                }
                catch(ElementClickInterceptedException ignored){}
                if (!msgSmsTemplate.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Language code"):
                selLanguageCode.click();
                try {
                    txtId.click();
                }
                catch(ElementClickInterceptedException ignored){}
                if (!msgLanguageCode.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("Template text"):
                txtTemplateText.clear();
                txtTemplateText.click();
                try {
                    txtId.click();
                }
                catch(ElementClickInterceptedException ignored){}
                if (!msgTemplateText.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        btnCancel.click();

        return rc;

    }

    public SmsTemplateTextDataRecord enterSmsTemplateTextData(SmsTemplateTextDataRecord newSmsTemplateText) {

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        SmsTemplateTextDataRecord record = new SmsTemplateTextDataRecord();

        // Click Reset button
        btnReset.click();

        // Click Add button
        btnAddNewSmsTemplateText.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selSmsTemplateList = new Select(webDriver.findElement(By.id("input-template")));
        Select selLanguageCodeList = new Select(webDriver.findElement(By.id("input-language")));

        // Enter required field data
        selSmsTemplateList.selectByVisibleText(newSmsTemplateText.getTemplate());
        selLanguageCodeList.selectByVisibleText(newSmsTemplateText.getLanguageCode());
        txtTemplateText.clear();
        txtTemplateText.click();
        txtTemplateText.sendKeys(newSmsTemplateText.getTemplateText());

        btnSaveNewSmsTemplateText.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            record = newSmsTemplateText;
            record.setTestOutput("Add SMS Template Text: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            record.setId(null);
            record.setInstitutionName(null);
            record.setTemplate(null);
            record.setTemplateType(null);
            record.setTemplateText(null);
            record.setLanguageCode(null);
            record.setAbbreviatedLanguageCode(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return record;
    }

    public SmsTemplateTextDataRecord editSmsTemplateTextData(SmsTemplateTextDataRecord editSmsTemplateText, String instField){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);

        searchSmsTemplateText(editSmsTemplateText.getInstitutionName(), editSmsTemplateText.getTemplate(), editSmsTemplateText.getTemplateType(), editSmsTemplateText.getLanguageCode());

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editSmsTemplateText.getId())) {

            // Edit the field required
            switch (instField) {

                case ("Template text"):
                    txtTemplateText.clear();
                    txtTemplateText.sendKeys(editSmsTemplateText.getTemplateText());
                    break;

            }

        }

        /* Click the Save button */
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {

            adminCommon.hardWait(500);
            searchSmsTemplateText(editSmsTemplateText.getInstitutionName(), editSmsTemplateText.getTemplate(), editSmsTemplateText.getTemplateType(), editSmsTemplateText.getLanguageCode());

            adminCommon.clickEditByUniqueId(editSmsTemplateText.getId());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch(instField) {

                case ("Template text"):
                    if (txtTemplateText.getAttribute("value").equalsIgnoreCase(editSmsTemplateText.getTemplateText())) {
                        editSmsTemplateText.setTestOutput("Edit SMS Template Text Template Text: SUCCESSFUL");
                    } else {
                        editSmsTemplateText.setTestOutput("Edit SMS Template Text Template Text: UNSUCCESSFUL\n" +
                                "EXP: editSmsTemplateText.getTemplateText() = " + editSmsTemplateText.getTemplateText() + "\n" +
                                "ACT: txtTemplateText.getAttribute(\"value\") = " + txtTemplateText.getAttribute("value"));
                    }
                    break;

            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewSmsTemplateText));

        }
        else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editSmsTemplateText.setId(null);
            editSmsTemplateText.setInstitutionName(null);
            editSmsTemplateText.setTemplate(null);
            editSmsTemplateText.setTemplateType(null);
            editSmsTemplateText.setAbbreviatedLanguageCode(null);
            editSmsTemplateText.setTemplateText(null);
            editSmsTemplateText.setTestOutput("Edit SMS Template Text: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        btnReset.click();

        return editSmsTemplateText;
    }

    public SmsTemplateTextDataRecord checkDeleteSmsTemplateTextById(SmsTemplateTextDataRecord delSmsTemplateText){

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
            delSmsTemplateText.setTestOutput("Delete SMS Template Text: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            delSmsTemplateText.setId(null);
            delSmsTemplateText.setInstitutionName(null);
            delSmsTemplateText.setTemplate(null);
            delSmsTemplateText.setTemplateType(null);
            delSmsTemplateText.setAbbreviatedLanguageCode(null);
            delSmsTemplateText.setTemplateText(null);
            delSmsTemplateText.setTestOutput("Delete SMS Template Text: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(delSmsTemplateText.getTemplate())){

                try {
                    btnModalYes.click();
                    delSmsTemplateText.setTestOutput("Delete SMS Template Text: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    delSmsTemplateText.setTestOutput("Delete SMS Template Text: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        btnReset.click();

        return delSmsTemplateText;
    }

    public SmsTemplateTextDataRecord getDBDataById(String instId) throws Exception{

        SmsTemplateTextDataRecord record = new SmsTemplateTextDataRecord();

        try {

            String getIssByIdSQL = "SELECT PMDTT.ID, CDI.NAME, PMDT.NAME, PMST.NAME, PMDTT.LANG_CODE, PMDTT.TEXT " +
                    "FROM PM_D_TEMPLATE_TEXT PMDTT " +
                    "LEFT JOIN PM_D_TEMPLATE PMDT ON PMDTT.TEMPLATE_ID = PMDT.ID " +
                    "LEFT JOIN C_D_INSTITUTION CDI ON PMDT.INSTITUTION_ID = CDI.ID " +
                    "LEFT JOIN PM_S_TEMPLATE_TYPE PMST ON PMDT.TYPE_ID = PMST.ID " +
                    "WHERE PMDTT.ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setInstitutionName(null);
                record.setTemplate(null);
                record.setTemplateType(null);
                record.setAbbreviatedLanguageCode(null);
                record.setTemplateText(null);
            }
            else{

                rs.next();

                record.setId(rs.getString(1));
                record.setInstitutionName(rs.getString(2));
                record.setTemplate(rs.getString(3));
                record.setTemplateType(rs.getString(4));
                record.setLanguageCode(rs.getString(5));
                record.setTemplateText(rs.getString(6));
            }
            connection.close();

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public SmsTemplateTextDataRecord getTableRecordByRecord(SmsTemplateTextDataRecord editSmsTemplateText) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        SmsTemplateTextDataRecord record = new SmsTemplateTextDataRecord();
        searchSmsTemplateText(editSmsTemplateText.getInstitutionName(), editSmsTemplateText.getTemplate(), editSmsTemplateText.getTemplateType(), editSmsTemplateText.getLanguageCode());


        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setId(null);
                record.setTemplate(null);
                record.setLanguageCode(null);
                record.setTemplateType(null);
                btnReset.click();
                return record;
            }

        }

        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + editSmsTemplateText.getId() + "']"));

            if(rows.size()>0){

                found = true;
                record.setId(editSmsTemplateText.getId());
                record.setInstitutionName(webDriver.findElement(By.id("col-institution" + editSmsTemplateText.getId())).getAttribute("textContent"));
                record.setTemplate(webDriver.findElement(By.id("col-template" + editSmsTemplateText.getId())).getAttribute("textContent"));
                record.setTemplateType(webDriver.findElement(By.id("col-templateType" + editSmsTemplateText.getId())).getAttribute("textContent"));
                record.setAbbreviatedLanguageCode(webDriver.findElement(By.id("col-lang" + editSmsTemplateText.getId())).getAttribute("textContent"));

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
                    record.setTemplate(null);
                    record.setLanguageCode(null);
                    record.setTemplateType(null);
                    btnReset.click();
                    return record;
                }

            }

        }while (!found);

        btnReset.click();

        return record;
    }

    public SmsTemplateTextDataRecord getDbIdBySmsTemplateTextName(SmsTemplateTextDataRecord key){

        SmsTemplateTextDataRecord record = new SmsTemplateTextDataRecord();
        String templateId = null;
//        String abbreviatedLanguageCode = key.getLanguageCode().substring(0,2);

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM PM_D_TEMPLATE " +
                    "WHERE NAME = ? ";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, key.getTemplate());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                templateId = null;
            }
            else{
                rs.next();
                templateId = rs.getString(1);

            }
            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        if(!templateId.equals(null)){

            try {
                String getIssByIdSQL = "SELECT ID " +
                        "FROM PM_D_TEMPLATE_TEXT " +
                        "WHERE TEMPLATE_ID = ? " +
                        "AND LANG_CODE = ? ";
                Connection connection;
                connection = DatabaseConnection.getConnection();
                PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
                preparedSelect.setString(1, templateId);
                preparedSelect.setString(2, key.getAbbreviatedLanguageCode());
                ResultSet rs = preparedSelect.executeQuery();
                if(!rs.isBeforeFirst()){
                    record.setId(null);

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

            case ("Template text"):

                obStringId = "val-text";
                expMessage = "Template text is required";
                expMessage2 = "Invalid template text";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtTemplateText.click();
                    txtTemplateText.clear();
                    txtTemplateText.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case("Punctuation_String"):
                        case ("Spaces"):
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





