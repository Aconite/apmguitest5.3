package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.CountryDataRecord;
import com.aconite.apm.gui.automation.testcases.ValidationTestcases;
import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class CountryPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddCountry;

    @FindBy(id = "btn-cancel-edit")
    WebElement btnCancel;

    @FindBy(id = "id")
    WebElement txtId;

    @FindBy(id = "name")
    WebElement txtCountryName;

    @FindBy(id = "val-name")
    WebElement msgCountryName;

    @FindBy(id = "code")
    WebElement txtCountryCode;

    @FindBy(id = "val-code")
    WebElement msgCountryCode;

    @FindBy(id = "btn-add-zone")
    WebElement btnAddNewCountry;

    @FindBy(id = "btn-save-zone")
    WebElement btnSaveCountry;

    @FindBy(id = "btn-ok")
    WebElement btnOK;

    @FindBy(className = "alert-dialog")
    WebElement alertDialog;

    @FindBy(className = "modal-contents")
    WebElement modalDialog;

    @FindBy(id = "btn_0")
    WebElement btnModalNo;

    @FindBy(id = "btn_1")
    WebElement btnModalYes;

    @FindBy(id = "btn-pagination-last")
    WebElement btnLastPage;

    @FindBy(id = "btn-pagination-first")
    WebElement btnFirstPage;

    @FindBy(id = "btn-pagination-previous")
    WebElement btnPreviousPage;

    @FindBy(id = "btn-pagination-next")
    WebElement btnNextPage;

    @FindBy(id = "btn-pagination-count")
    WebElement txtPageCount;

    @FindBy(id = "pagination-page")
    WebElement txtCurrentPage;

    @FindBy(id = "pagination-size")
    WebElement txtPageSize;

    public CountryPage(WebDriver webDriver) {

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Countries");
    }

    public void clickAddButton() {

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddCountry));

        btnAddCountry.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

    }

    public boolean checkRequiredFields(String field, String message) {

        boolean rc = true;
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        switch (field) {

            case ("Country code"):
                txtCountryCode.clear();
                txtCountryCode.click();
                txtCountryCode.sendKeys(Keys.TAB);
                if (!msgCountryCode.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case ("Country name"):
                txtCountryName.clear();
                txtCountryName.click();
                txtCountryName.sendKeys(Keys.TAB);
                if (!msgCountryName.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;
        }

        return rc;

    }

    public CountryDataRecord enterCountryData(CountryDataRecord newCountry) {

        CountryDataRecord newRecord = new CountryDataRecord();

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);


        btnAddCountry.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        txtCountryCode.clear();
        txtCountryCode.sendKeys(newCountry.getCountryCode());

        txtCountryName.clear();
        txtCountryName.sendKeys(newCountry.getCountryName());

        myWait.until(ExpectedConditions.visibilityOf(btnAddNewCountry));
        btnAddNewCountry.click();

        adminCommon.hardWait(500);

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {
            newRecord.setCountryCode(newCountry.getCountryCode());
            newRecord.setCountryName(newCountry.getCountryName());
            newRecord.setTestOutput("Add Country: SUCCESSFUL");
        } else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setCountryCode(null);
            newRecord.setCountryName(null);
            newRecord.setTestOutput("Add Country: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }
        return newRecord;
    }

    public CountryDataRecord editCountryData(CountryDataRecord editCountry, String editField) {

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        myWait.until(ExpectedConditions.visibilityOf(btnAddCountry));

//        editCountry = getDbIdByCountryCode(editCountry);

        /* Sort or Filter for records */
        adminCommon.sortTableByColumn("Country code", "DESC");

        /* Click the Edit button */
        if (adminCommon.clickEditByUniqueId(editCountry.getDbId())){

            // Edit the field required
            switch (editField) {

                case ("Country name"):
                    myWait.until(ExpectedConditions.visibilityOf(txtCountryName));
                    txtCountryName.clear();
                    txtCountryName.sendKeys(editCountry.getCountryName());
                    break;

                case ("Country code"):
                    myWait.until(ExpectedConditions.visibilityOf(txtCountryCode));
                    txtCountryCode.clear();
                    txtCountryCode.sendKeys(editCountry.getCountryCode());
                    break;

            }
        }
        else{
            editCountry.setCountryCode(null);
            editCountry.setCountryName(null);
            editCountry.setTestOutput("Edit Country: Click Edit Button UNSUCCESSFUL");

            return editCountry;
        }


        /* Click the Save button */
        myWait.until(ExpectedConditions.visibilityOf(btnSaveCountry));
        btnSaveCountry.click();

        adminCommon.hardWait(500);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){

            adminCommon.sortTableByColumn("Country code", "DESC");

            /* Click the Edit button to check change */
            adminCommon.clickEditByUniqueId(editCountry.getDbId());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch (editField) {

                case ("Country name"):
                    if (txtCountryName.getAttribute("value").equalsIgnoreCase(editCountry.getCountryName())) {
                        editCountry.setTestOutput("Edit Country Name: SUCCESSFUL");
                    } else {
                        editCountry.setTestOutput("Edit Country: UNSUCCESSFUL\n" +
                                "EXP: editCountry.getCountryName() = " + editCountry.getCountryName() + "\n" +
                                "ACT: txtCountryName.getAttribute(\"value\") = " + txtCountryName.getAttribute("value"));
                    }
                    break;

                case ("Country code"):
                    if (txtCountryCode.getAttribute("value").equalsIgnoreCase(editCountry.getCountryCode())) {
                        editCountry.setTestOutput("Edit Country Code: SUCCESSFUL");
                    } else {
                        editCountry.setTestOutput("Edit Country: UNSUCCESSFUL\n" +
                                "EXP: editCountry.getCountryCode() = " + editCountry.getCountryCode() + "\n" +
                                "ACT: txtCountryCode.getAttribute(\"value\") = " + txtCountryCode.getAttribute("value"));
                    }
                    break;

            }

            btnCancel.click();
            adminCommon.hardWait(1000);
        }
        else{

            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editCountry.setCountryCode(null);
            editCountry.setCountryName(null);
            editCountry.setTestOutput("Edit Country: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }


        return editCountry;

    }

    public CountryDataRecord checkAndDeleteCountry(CountryDataRecord editCountry){

        int maxTimeout = 3;
        List<WebElement> modalList;
        List<WebElement> alertList;
        long start = System.currentTimeMillis();
        long end = start + maxTimeout * 1000;

        /* Check for modal dialog or alert dialog */

        do {
            modalList = webDriver.findElements(By.className("modal-contents"));
            alertList = webDriver.findElements(By.className("alert-dialog"));
        }while(modalList.size()==0 && alertList.size()==0 && System.currentTimeMillis() < end);

        if(modalList.size()==0 && alertList.size()==0) {
            editCountry.setTestOutput("Delete Country: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editCountry.setCountryCode(null);
            editCountry.setCountryName(null);
            editCountry.setTestOutput("Delete Country: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }


        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(editCountry.getCountryName())){

                try {
                    btnModalYes.click();
                    editCountry.setTestOutput("Delete Country: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    editCountry.setTestOutput("Delete Country: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return editCountry;
    }

    public CountryDataRecord getDbIdByCountryCode(CountryDataRecord country){

        CountryDataRecord record = new CountryDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID, COUNTRY_CODE, NAME " +
                    "FROM C_S_COUNTRIES " +
                    "WHERE COUNTRY_CODE = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, country.getCountryCode());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setDbId(null);
                record.setCountryCode(null);
                record.setCountryName(null);
            }
            else{
                rs.next();

                record.setDbId(rs.getString(1));
                record.setCountryCode(rs.getString(2));
                record.setCountryName(rs.getString(3));
            }

            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public CountryDataRecord getTableRecordByRecord(CountryDataRecord country) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        CountryDataRecord record = new CountryDataRecord();

        adminCommon.sortTableByColumn("Country code","DESC");
        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setDbId(null);
                record.setCountryName(null);
                record.setCountryCode(null);

                return record;
            }

        }

        adminCommon.sortTableByColumn("Country code","DESC");
        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-code" + country.getDbId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setCountryCode(webDriver.findElement(By.xpath("//td[@id='col-code" + country.getDbId() + "']")).getAttribute("textContent"));
                record.setCountryName(webDriver.findElement(By.xpath("//td[@id='col-name" + country.getDbId() + "']")).getAttribute("textContent"));

                if(record.getCountryCode().equalsIgnoreCase(country.getCountryCode()) &&
                        record.getCountryName().equalsIgnoreCase(country.getCountryName())){
                    return country;
                }

                if(record.getCountryCode().equalsIgnoreCase(country.getCountryCode()) &&
                        !record.getCountryName().equalsIgnoreCase(country.getCountryName())){
                    country.setCountryName(null);
                    return country;
                }

                if(!record.getCountryCode().equalsIgnoreCase(country.getCountryCode()) &&
                        record.getCountryName().equalsIgnoreCase(country.getCountryName())){
                    country.setCountryCode(null);
                    return country;
                }

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
                    country.setCountryName(null);
                    country.setCountryCode(null);
                    return country;
                }

            }

        }while (!found);

        return country;
    }

    public CountryDataRecord getDBDataById(CountryDataRecord country) throws Exception{

        CountryDataRecord record = new CountryDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID, COUNTRY_CODE, NAME " +
                    "FROM C_S_COUNTRIES " +
                    "WHERE ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, country.getDbId());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setDbId(null);
                record.setCountryCode(null);
                record.setCountryName(null);
            }
            else{
                rs.next();

                record.setDbId(rs.getString(1));
                record.setCountryCode(rs.getString(2));
                record.setCountryName(rs.getString(3));
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

            case ("Country name"):

                obStringId = "val-name";
                expMessage = "Country name is required";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtCountryName.click();
                    txtCountryName.clear();
                    txtCountryName.sendKeys(value);
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

            case ("Country code"):

                obStringId = "val-code";
                expMessage = "Country code is required";
                expMessage2 = "Country code must be 1 to 3 digits";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtCountryCode.click();
                    txtCountryCode.clear();
                    txtCountryCode.sendKeys(value);
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

                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
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
