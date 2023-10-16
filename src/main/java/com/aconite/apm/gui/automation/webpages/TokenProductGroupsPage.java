package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.TokenProductGroupDataRecord;
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


public class TokenProductGroupsPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewTPG;

    @FindBy(id = "btn-reset-filter")
    WebElement btnResetAll;

    @FindBy(id = "btn-add-zone")
    WebElement btnSubmitNewTPG;

    @FindBy(id = "btn-save-zone")
    WebElement btnSaveTPG;

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

    @FindBy(id = "code")
    WebElement txtGroupCode;

    @FindBy(id = "val-code")
    WebElement msgGroupCode;

    @FindBy(id = "issuerId")
    WebElement selIssuer;

    @FindBy(id = "val-issuerId")
    WebElement msgIssuer;

    public TokenProductGroupsPage(WebDriver webDriver) {

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Token product groups");
    }

    public void clickAddButton(){

        try {
            btnAddNewTPG.click();
        }
        catch(ElementClickInterceptedException ignored){}

    }

    public void searchByInstitutionAndIssuer(String institution, String issuer){

        Select selInstitutionList = new Select(webDriver.findElement(By.id("search-institution")));
        Select selIssuerList = new Select(webDriver.findElement(By.id("search-issuer")));

        if(!institution.equalsIgnoreCase("")){
            selInstitutionList.selectByVisibleText(institution);
        }
        if(!issuer.equalsIgnoreCase("")){
            selIssuerList.selectByVisibleText(issuer);
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

            case("Issuer"):
                selIssuer.click();
                selIssuer.sendKeys(Keys.TAB);
                selIssuer.sendKeys(Keys.TAB);
                if (!msgIssuer.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }

                break;

            case("GroupCode"):
                txtGroupCode.clear();
                txtGroupCode.click();
                txtGroupCode.sendKeys(Keys.TAB);
                if (!msgGroupCode.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;

    }

    public TokenProductGroupDataRecord enterTokenProductGroupData(TokenProductGroupDataRecord newTPG){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        TokenProductGroupDataRecord newRecord = new TokenProductGroupDataRecord();

        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTPG));
        btnAddNewTPG.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        txtName.clear();
        txtName.click();
        txtName.sendKeys(newTPG.getName());

        Select selIssuerList = new Select(webDriver.findElement(By.id("issuerId")));
        selIssuerList.selectByVisibleText(newTPG.getIssuer());

        txtGroupCode.clear();
        txtGroupCode.click();
        txtGroupCode.sendKeys(newTPG.getGroupCode());

        // Click Add button
        btnSubmitNewTPG.click();
        //myWait.until(ExpectedConditions.visibilityOf(btnResetAll));

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newTPG;
            newRecord.setTestOutput("Add Token Product Group: SUCCESSFUL");
        }
        else{

            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setName(null);
            newRecord.setId(null);
            newRecord.setParentInstitution(null);
            newRecord.setIssuer(null);
            newRecord.setGroupCode(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return newRecord;

    }

    public TokenProductGroupDataRecord editTokenProductGroupData(TokenProductGroupDataRecord editTPG, String tpgField){

        Select selIssuerList;

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTPG));


        if(!tpgField.equalsIgnoreCase("Issuer")){
                searchByInstitutionAndIssuer(editTPG.getParentInstitution(), editTPG.getIssuer());
        }

        if(adminCommon.clickEditByUniqueId(editTPG.getId())) {

            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            // Edit the field required
            switch (tpgField) {

                case ("Name"):
                    txtName.clear();
                    txtName.sendKeys(editTPG.getName());
                    break;

                case ("Issuer"):
                    selIssuerList = new Select(webDriver.findElement(By.id("issuerId")));
                    selIssuerList.selectByVisibleText(editTPG.getIssuer());
                    break;

                case ("GroupCode"):
                    txtGroupCode.clear();
                    txtGroupCode.sendKeys(editTPG.getGroupCode());
                    break;

            }
        }

        /* Click the Save button */
        myWait.until(ExpectedConditions.visibilityOf(btnSaveTPG));
        btnSaveTPG.click();

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0) {

            // Check the message and click OK
            myWait.until(ExpectedConditions.visibilityOf(btnResetAll));
            searchByInstitutionAndIssuer(editTPG.getParentInstitution(), editTPG.getIssuer());
            adminCommon.clickEditByUniqueId(editTPG.getId());

            switch (tpgField) {

                case ("Name"):
                    if(txtName.getAttribute("value").equalsIgnoreCase(editTPG.getName()))
                    {
                        editTPG.setTestOutput("Edit Token Product Group Name: SUCCESSFUL");
                    }
                    else{
                        editTPG.setTestOutput("Edit Token Product Group Name: UNSUCCESSFUL\n" +
                                "EXP: editTPG.getName() = " + editTPG.getName() + "\n" +
                                "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                    }
                    break;

                case ("Issuer"):
                    selIssuerList = new Select(webDriver.findElement(By.id("issuerId")));
                    if(selIssuerList.getFirstSelectedOption().getText().equalsIgnoreCase(editTPG.getIssuer()))
                    {
                        editTPG.setTestOutput("Edit Token Product Group Issuer: SUCCESSFUL");
                    }
                    else{
                        editTPG.setTestOutput("Edit Token Product Group Issuer: UNSUCCESSFUL\n" +
                                "EXP: editTPG.getIssuer() = " + editTPG.getIssuer() + "\n" +
                                "ACT: selIssuerList.getFirstSelectedOption().getText() = " + selIssuerList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("GroupCode"):
                    if(txtGroupCode.getAttribute("value").equalsIgnoreCase(editTPG.getGroupCode()))
                    {
                        editTPG.setTestOutput("Edit Token Product Group Group Code: SUCCESSFUL");
                    }
                    else{
                        editTPG.setTestOutput("Edit Token Product Group Group Code: UNSUCCESSFUL\n" +
                                "EXP: editTPG.getGroupCode() = " + editTPG.getGroupCode() + "\n" +
                                "ACT: txtGroupCode.getAttribute(\"value\") = " + txtGroupCode.getAttribute("value"));
                    }
                    break;

            }

            btnCancel.click();
            adminCommon.hardWait(1000);

        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            editTPG.setId(null);
            editTPG.setName(null);
            editTPG.setParentInstitution(null);
            editTPG.setIssuer(null);
            editTPG.setGroupCode(null);
            editTPG.setTestOutput("Edit Token Product Group: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        return editTPG;
    }

    public TokenProductGroupDataRecord checkDeleteTokenProductGroupById(TokenProductGroupDataRecord delTPG){

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
            delTPG.setTestOutput("Delete Token Product Group: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            delTPG.setId(null);
            delTPG.setName(null);
            delTPG.setParentInstitution(null);
            delTPG.setIssuer(null);
            delTPG.setGroupCode(null);
            delTPG.setTestOutput("Delete Token Product Group: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(delTPG.getName())){

                try {
                    btnModalYes.click();
                    delTPG.setTestOutput("Delete Token Product Group: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    delTPG.setTestOutput("Delete Token Product Group: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return delTPG;
    }

    public TokenProductGroupDataRecord getTableRecordByRecord(TokenProductGroupDataRecord editTPG) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        TokenProductGroupDataRecord record = new TokenProductGroupDataRecord();
        searchByInstitutionAndIssuer(editTPG.getParentInstitution(), editTPG.getIssuer());

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setId(null);
                record.setName(null);
                record.setParentInstitution(null);
                record.setIssuer(null);
                record.setGroupCode(null);
                System.out.println("getTableRecordByRecord record 1 = " + record);
                return record;
            }

        }

        try {
            btnFirstPage.click();
        }
        catch(ElementClickInterceptedException ignored){}
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + editTPG.getId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setId(editTPG.getId());
                record.setName(webDriver.findElement(By.id("col-name" + editTPG.getId())).getAttribute("textContent"));
                record.setGroupCode(webDriver.findElement(By.id("col-code" + editTPG.getId())).getAttribute("textContent"));
                record.setParentInstitution(webDriver.findElement(By.id("col-institution" + editTPG.getId())).getAttribute("textContent"));
                record.setIssuer(webDriver.findElement(By.id("col-issuer" + editTPG.getId())).getAttribute("textContent"));

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
                    record.setParentInstitution(null);
                    record.setIssuer(null);
                    record.setGroupCode(null);
                    System.out.println("getTableRecordByRecord record 2 = " + record);
                    return record;
                }

            }

        }while (!found);

        System.out.println("getTableRecordByRecord record 3 = " + record);

        return record;
    }

    public TokenProductGroupDataRecord getDBDataById(TokenProductGroupDataRecord editTPG){

        TokenProductGroupDataRecord record = new TokenProductGroupDataRecord();

        try {

            String getTPGByIdSQL = "SELECT CDTPG.ID, CDTPG.NAME,  CDI.NAME, CDTPG.GROUP_CODE, CDINST.NAME " +
                    "FROM C_D_TOKEN_PRODUCT_GROUP CDTPG " +
                    "LEFT JOIN  C_D_ISSUER CDI ON CDTPG.ISSUER_ID = CDI.ID " +
                    "LEFT JOIN  C_D_INSTITUTION CDINST ON CDI.INSTITUTION_ID = CDINST.ID " +
                    "WHERE CDTPG.ID = ?";

            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getTPGByIdSQL);
            preparedSelect.setString(1, editTPG.getId());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setParentInstitution(null);
                record.setIssuer(null);
                record.setGroupCode(null);

            }
            else{

                rs.next();

                record.setId(rs.getString(1));
                record.setName(rs.getString(2));
                record.setIssuer(rs.getString(3));
                record.setGroupCode(rs.getString(4));
                record.setParentInstitution(rs.getString(5));

            }

            connection.close();

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public TokenProductGroupDataRecord getDbIdByTokenProductGroupName(TokenProductGroupDataRecord key){

        TokenProductGroupDataRecord record = new TokenProductGroupDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM C_D_TOKEN_PRODUCT_GROUP " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, key.getName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setParentInstitution(null);
                record.setIssuer(null);
                record.setGroupCode(null);

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

            case ("GroupCode"):

                obStringId = "val-code";
                expMessage = "Group code is required";


                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtGroupCode.click();
                    txtGroupCode.clear();
                    txtGroupCode.sendKeys(value);
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

//                        case ("Decimal_Value"):
//                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
//                            break;

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
