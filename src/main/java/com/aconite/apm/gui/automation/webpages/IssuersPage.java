package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.IssuerDataRecord;
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

public class IssuersPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddIssuer;

    @FindBy(id = "btn-add-zone")
    WebElement btnSaveNewIssuer;

    @FindBy(id = "btn-save-zone")
    WebElement btnSaveIssuer;

    @FindBy(id = "btn-cancel-edit")
    WebElement btnCancel;

    @FindBy(id = "btn-reset-filter")
    WebElement btnResetAll;

    @FindBy(id = "id")
    WebElement txtId;

    @FindBy(id = "name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "institutionId")
    WebElement selInstitution;

    @FindBy(id = "val-institutionId")
    WebElement msgInstitution;

    @FindBy(id = "countryCode")
    WebElement selCountry;

    @FindBy(id = "val-countryCode")
    WebElement msgCountry;

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

    @FindBy(id = "search-issuer-name")
    WebElement txtFilterName;



    public IssuersPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Issuers");
    }

    public void clickAddButton(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddIssuer));

        btnAddIssuer.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

    }

    public void searchIssuer(String institution, String issuerName){

        Select selInstitution = new Select(webDriver.findElement(By.id("search-institution")));

        if(institution != null && !institution.equalsIgnoreCase("")) {
            selInstitution.selectByVisibleText(institution);
        }

        if(issuerName != null && !issuerName.equalsIgnoreCase("")) {
            txtFilterName.clear();
            txtFilterName.sendKeys(issuerName);
        }


        adminCommon.hardWait(1000);
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

            case("Country"):
                selCountry.click();
                selCountry.sendKeys(Keys.TAB);
                selCountry.sendKeys(Keys.TAB);
                if (!msgCountry.getAttribute("textContent").equalsIgnoreCase(message)) {
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

    public IssuerDataRecord enterIssuerData(IssuerDataRecord newIss) {

        IssuerDataRecord newRecord = new IssuerDataRecord();
        WebDriverWait myWait = new WebDriverWait(webDriver, 10);

        myWait.until(ExpectedConditions.visibilityOf(btnAddIssuer));

        // Click Add button
        btnAddIssuer.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selInstitutionList = new Select(webDriver.findElement(By.id("institutionId")));
        Select selCountryList = new Select(webDriver.findElement(By.id("countryCode")));

        txtName.sendKeys(newIss.getIssuerName());
        selInstitutionList.selectByVisibleText(newIss.getInstitutionName());
        selCountryList.selectByVisibleText(newIss.getCountry());

        myWait.until(ExpectedConditions.visibilityOf(btnSaveNewIssuer));
        btnSaveNewIssuer.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newIss;
            newRecord.setTestOutput("Add Issuer: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setId(null);
            newRecord.setInstitutionName(null);
            newRecord.setIssuerName(null);
            newRecord.setCountry(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        return(newRecord);

    }

    public IssuerDataRecord editIssuerData(IssuerDataRecord editIssuer, String field){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnResetAll));

        Select selInstitutionList = null;
        Select selCountryList = null;

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editIssuer.getId())) {


            selCountryList = new Select(webDriver.findElement(By.id("countryCode")));

            // Edit the field required
            switch (field) {

                case ("Name"):
                    txtName.clear();
                    txtName.sendKeys(editIssuer.getIssuerName());
                    break;

                case ("Institution"):
                    selInstitutionList = new Select(webDriver.findElement(By.id("institutionId")));
                    selInstitutionList.selectByVisibleText(editIssuer.getInstitutionName());
                    break;

                case ("Country"):
                    selCountryList = new Select(webDriver.findElement(By.id("countryCode")));
                    selCountryList.selectByVisibleText(editIssuer.getCountry());
                    break;

            }
        }
        else{
            System.out.println("clickEditByUniqueId FAILED");
        }

        /* Click the Save button */
        btnSaveIssuer.click();
        adminCommon.hardWait(1000);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {

            adminCommon.clickEditByUniqueId(editIssuer.getId());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch (field) {

                case ("Name"):
                    if (txtName.getAttribute("value").equalsIgnoreCase(editIssuer.getIssuerName())) {
                        editIssuer.setTestOutput("Edit Issuer Name: SUCCESSFUL");
                    } else {
                        editIssuer.setTestOutput("Edit Issuer Name: UNSUCCESSFUL\n" +
                                "EXP: editIssuer.getIssuerName() = " + editIssuer.getIssuerName() + "\n" +
                                "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                    }
                    break;

                case ("Institution"):
                    selInstitutionList = new Select(webDriver.findElement(By.id("institutionId")));
                    if (selInstitutionList.getFirstSelectedOption().getText().equalsIgnoreCase(editIssuer.getInstitutionName())) {
                        editIssuer.setTestOutput("Edit Issuer Institution: SUCCESSFUL");
                    } else {
                        editIssuer.setTestOutput("Edit Issuer Institution: UNSUCCESSFUL\n" +
                                "EXP: editIssuer.getInstitutionName() = " + editIssuer.getInstitutionName() + "\n" +
                                "ACT: selInstitutionList.getFirstSelectedOption().getText() = " + selInstitutionList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("Country"):
                    selCountryList = new Select(webDriver.findElement(By.id("countryCode")));
                    if (selCountryList.getFirstSelectedOption().getText().equalsIgnoreCase(editIssuer.getCountry())) {
                        editIssuer.setTestOutput("Edit Issuer Country: SUCCESSFUL");
                    } else {
                        editIssuer.setTestOutput("Edit Issuer Country: UNSUCCESSFUL\n" +
                                "EXP: editIssuer.getCountry() = " + editIssuer.getCountry() + "\n" +
                                "ACT: selCountryList.getFirstSelectedOption().getText() = " + selCountryList.getFirstSelectedOption().getText());
                    }
                    break;

            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnResetAll));

        }
        else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editIssuer.setId(null);
            editIssuer.setInstitutionName(null);
            editIssuer.setIssuerName(null);
            editIssuer.setCountry(null);
            editIssuer.setTestOutput("Edit Issuer: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        return editIssuer;

    }

    public IssuerDataRecord checkAndDeleteIssuer(IssuerDataRecord editIssuer){

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
            editIssuer.setTestOutput("Delete Issuer: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editIssuer.setId(null);
            editIssuer.setInstitutionName(null);
            editIssuer.setIssuerName(null);
            editIssuer.setCountry(null);
            editIssuer.setTestOutput("Delete Issuer: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(editIssuer.getIssuerName())){

                try {
                    btnModalYes.click();
                    editIssuer.setTestOutput("Delete Issuer: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    editIssuer.setTestOutput("Delete Issuer: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return editIssuer;
    }


    public IssuerDataRecord getDbIdByIssuerName(IssuerDataRecord issuer){

        IssuerDataRecord record = new IssuerDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM C_D_ISSUER " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, issuer.getIssuerName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setInstitutionName(null);
                record.setIssuerName(null);
                record.setCountry(null);
            }
            else{
                rs.next();
                record = issuer;
                record.setId(rs.getString(1));

            }
            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public IssuerDataRecord getTableRecordByRecord(IssuerDataRecord issuer) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        IssuerDataRecord record = new IssuerDataRecord();
        searchIssuer(issuer.getInstitutionName(), issuer.getIssuerName());

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setId(null);
                record.setIssuerName(null);
                record.setInstitutionName(null);
                record.setCountry(null);

                return record;
            }

        }

        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + issuer.getId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setId(issuer.getId());
                record.setIssuerName(webDriver.findElement(By.id("col-name" + issuer.getId())).getAttribute("textContent"));
                record.setInstitutionName(webDriver.findElement(By.id("col-institution" + issuer.getId())).getAttribute("textContent"));
                record.setCountry(webDriver.findElement(By.id("col-country" + issuer.getId())).getAttribute("textContent"));

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
                    record.setIssuerName(null);
                    record.setInstitutionName(null);
                    record.setCountry(null);

                    return record;
                }

            }

        }while (!found);

        return record;
    }

    public IssuerDataRecord getDBDataById(String instId) throws Exception{

        IssuerDataRecord record = new IssuerDataRecord();

        try {

            String getIssByIdSQL = "SELECT CDI.ID, CDI.NAME, INST.NAME, CTRY.NAME " +
                    "FROM C_D_ISSUER CDI " +
                    "LEFT JOIN  C_D_INSTITUTION INST ON CDI.INSTITUTION_ID = INST.ID " +
                    "LEFT JOIN  C_S_COUNTRIES CTRY ON CDI.COUNTRY_CODE = CTRY.ID " +
                    "WHERE CDI.ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setIssuerName(null);
                record.setInstitutionName(null);
                record.setCountry(null);
            }
            else{

                rs.next();

                record.setId(rs.getString(1));
                record.setIssuerName(rs.getString(2));
                record.setInstitutionName(rs.getString(3));
                record.setCountry(rs.getString(4));

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

        }

        return msg.toString();

    }

}
