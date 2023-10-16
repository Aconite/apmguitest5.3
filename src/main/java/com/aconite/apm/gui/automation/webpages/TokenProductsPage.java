package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.TokenProductDataRecord;
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


public class TokenProductsPage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewTokenProduct;

    @FindBy(id = "btn-add-zone")
    WebElement btnSaveNewTokenProduct;

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

    @FindBy(id = "id")
    WebElement txtId;

    @FindBy(id = "name")
    WebElement txtName;

    @FindBy(id = "val-name")
    WebElement msgName;

    @FindBy(id = "productCode")
    WebElement txtIssuerTokenProductCode;

    @FindBy(id = "val-productCode")
    WebElement msgIssuerTokenProductCode;

    @FindBy(id = "issuer-name")
    WebElement selIssuer;

    @FindBy(id = "val-issuerName")
    WebElement msgIssuer;

    @FindBy(id = "productGroup")
    WebElement selTokenProductGroup;

    @FindBy(id = "val-productGroup")
    WebElement msgTokenProductGroup;

    @FindBy(id = "retentionDays")
    WebElement txtRetentionDays;

    @FindBy(id = "val-retentionDays")
    WebElement msgRetentionDays;

    @FindBy(id = "country")
    WebElement selCountry;

    @FindBy(id = "val-country")
    WebElement msgCountry;

    @FindBy(id = "language")
    WebElement selLanguage;

    @FindBy(id = "val-language")
    WebElement msgLanguage;

    @FindBy(id = "persoBureau")
    WebElement selPersoBureau;

    @FindBy(id = "val-persoBureau")
    WebElement msgPersoBureau;

    @FindBy(id = "input-feedback")
    WebElement cbxFeedbackRequired;

    @FindBy(id = "input-active")
    WebElement cbxActive;

    @FindBy(id = "serviceCode")
    WebElement txtServiceCode;



    public TokenProductsPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }


    /********************************
     * Main Page Functions
     ********************************/

    public boolean isPageOpened() { return(pageTitle.getText().equalsIgnoreCase("Token products")); }

    public void clickAddButton(){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenProduct));

        btnAddNewTokenProduct.click();

    }

    public void searchTokenProduct(String institution, String issuerName, String tokenProductGroup){

        Select selInstitution = new Select(webDriver.findElement(By.id("search-institution")));
        Select selIssuerName = new Select(webDriver.findElement(By.id("search-issuer")));
        Select selTokenProductGroup = new Select(webDriver.findElement(By.id("search-group")));

        if(institution!=null && !institution.isEmpty()) {
            selInstitution.selectByVisibleText(institution);
        }

        if(issuerName!=null && !issuerName.isEmpty()) {
            selIssuerName.selectByVisibleText(issuerName);
        }

        if(tokenProductGroup!=null && !tokenProductGroup.isEmpty()) {
            selTokenProductGroup.selectByVisibleText(tokenProductGroup);
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

            case("IssuerTokenProductCode"):
                txtIssuerTokenProductCode.clear();
                txtIssuerTokenProductCode.click();
                txtIssuerTokenProductCode.sendKeys(Keys.TAB);
                if (!msgIssuerTokenProductCode.getAttribute("textContent").equalsIgnoreCase(message)) {
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

            case("TokenProductGroup"):
                selTokenProductGroup.click();
                selTokenProductGroup.sendKeys(Keys.TAB);
                selTokenProductGroup.sendKeys(Keys.TAB);
                if (!msgTokenProductGroup.getAttribute("textContent").equalsIgnoreCase(message)) {
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

            case("Language"):
                selLanguage.click();
                selLanguage.sendKeys(Keys.TAB);
                selLanguage.sendKeys(Keys.TAB);
                if (!msgLanguage.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PersoBureau"):
                selPersoBureau.click();
                selPersoBureau.sendKeys(Keys.TAB);
                selPersoBureau.sendKeys(Keys.TAB);
                if (!msgPersoBureau.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;

    }

    public TokenProductDataRecord enterTokenProductData(TokenProductDataRecord newTokenProduct){

        TokenProductDataRecord newRecord = new TokenProductDataRecord();
        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenProduct));
        btnAddNewTokenProduct.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        txtName.clear();
        txtName.sendKeys(newTokenProduct.getName());

        txtIssuerTokenProductCode.clear();
        txtIssuerTokenProductCode.sendKeys(newTokenProduct.getIssuerTokenProductCode());

        Select selIssuerList = new Select(webDriver.findElement(By.id("issuer-name")));
        selIssuerList.selectByVisibleText(newTokenProduct.getIssuer());

        adminCommon.hardWait(500);

        Select selTokenProductGroupList = new Select(webDriver.findElement(By.id("productGroup")));
        selTokenProductGroupList.selectByVisibleText(newTokenProduct.getTokenProductGroup());

        txtRetentionDays.clear();
        txtRetentionDays.sendKeys(newTokenProduct.getRetentionDays());

        Select selCountryList = new Select(webDriver.findElement(By.id("country")));
        selCountryList.selectByVisibleText(newTokenProduct.getCountry());

        Select selLanguageList = new Select(webDriver.findElement(By.id("language")));
        selLanguageList.selectByVisibleText(newTokenProduct.getLanguage());

        Select selPersoBureauList = new Select(webDriver.findElement(By.id("persoBureau")));
        selPersoBureauList.selectByVisibleText(newTokenProduct.getPersoBureau());

        adminCommon.selectCheckbox(cbxFeedbackRequired,newTokenProduct.getFeedbackRequired());

        adminCommon.selectCheckbox(cbxActive,newTokenProduct.getActive());

//        System.out.println("newTokenProduct.getServiceCode() = >" + newTokenProduct.getServiceCode() + "<");
//        System.out.println("newTokenProduct.getServiceCode().length() = " + newTokenProduct.getServiceCode().length());

        if(newTokenProduct.getServiceCode().length()>1) {
            System.out.println("Entering Service Code");
            txtServiceCode.clear();
            txtServiceCode.sendKeys(newTokenProduct.getServiceCode());
        }
        else{
            System.out.println("Clearing Service Code");
            txtServiceCode.click();
            adminCommon.hardWait(500);
            txtServiceCode.sendKeys(Keys.BACK_SPACE);
            txtServiceCode.sendKeys(Keys.BACK_SPACE);
        }

        btnSaveNewTokenProduct.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newTokenProduct;
            newRecord.setTestOutput("Add Token Product: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setId(null);
            newRecord.setName(null);
            newRecord.setIssuerTokenProductCode(null);
            newRecord.setIssuer(null);
            newRecord.setTokenProductGroup(null);
            newRecord.setRetentionDays(null);
            newRecord.setCountry(null);
            newRecord.setPersoBureau(null);
            newRecord.setFeedbackRequired(null);
            newRecord.setActive(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        return newTokenProduct;

    }

    public TokenProductDataRecord editTokenProductCodeData(TokenProductDataRecord editTokenProductCode, String instField){

        Select selTokenProductGroupList;
        Select selCountryList;
        Select selLanguageList;
        Select selPersoBureauList;

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenProduct));

        if(instField.equalsIgnoreCase("TokenProductGroup") || instField.equalsIgnoreCase("Issuer")){
            searchTokenProduct(editTokenProductCode.getParentInstitution(), "", "");
        }
        else{
            searchTokenProduct(editTokenProductCode.getParentInstitution(), editTokenProductCode.getIssuer(), editTokenProductCode.getTokenProductGroup());
        }

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editTokenProductCode.getId())) {

            myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));

            switch (instField) {

                case ("Name"):
                    txtName.clear();
                    txtName.click();
                    txtName.sendKeys(editTokenProductCode.getName());
                    break;

                case ("IssuerTokenProductCode"):
                    txtIssuerTokenProductCode.clear();
                    txtIssuerTokenProductCode.click();
                    txtIssuerTokenProductCode.sendKeys(editTokenProductCode.getIssuerTokenProductCode());
                    break;

                case ("TokenProductGroup"):
                    selTokenProductGroupList = new Select(webDriver.findElement(By.id("productGroup")));
                    selTokenProductGroupList.selectByVisibleText(editTokenProductCode.getTokenProductGroup());
                    break;

                case ("RetentionDays"):
                    txtRetentionDays.clear();
                    txtRetentionDays.click();
                    txtRetentionDays.sendKeys(editTokenProductCode.getRetentionDays());
                    break;


                case ("Country"):
                    selCountryList = new Select(webDriver.findElement(By.id("country")));
                    selCountryList.selectByVisibleText(editTokenProductCode.getCountry());
                    break;

                case ("Language"):
                    selLanguageList = new Select(webDriver.findElement(By.id("language")));
                    selLanguageList.selectByVisibleText(editTokenProductCode.getLanguage());
                    break;

                case ("PersoBureau"):
                    selPersoBureauList = new Select(webDriver.findElement(By.id("persoBureau")));
                    selPersoBureauList.selectByVisibleText(editTokenProductCode.getPersoBureau());
                    break;

                case ("FeedbackRequired"):
                    adminCommon.selectCheckbox(cbxFeedbackRequired, editTokenProductCode.getFeedbackRequired());
                    break;

                case ("Active"):
                    adminCommon.selectCheckbox(cbxActive, editTokenProductCode.getActive());
                    break;

                case ("ServiceCode"):
                    txtServiceCode.clear();
                    txtServiceCode.click();
                    txtServiceCode.sendKeys(editTokenProductCode.getServiceCode());
                    break;

            }
        }

        /* Click the Save button */
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();
        adminCommon.hardWait(500);

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0) {

            searchTokenProduct(editTokenProductCode.getParentInstitution(), editTokenProductCode.getIssuer(), editTokenProductCode.getTokenProductGroup());
            adminCommon.clickEditByUniqueId(editTokenProductCode.getId());
            myWait.until(ExpectedConditions.visibilityOf(btnCancel));

            switch (instField) {

                case ("Name"):
                    if (txtName.getAttribute("value").equalsIgnoreCase(editTokenProductCode.getName())) {
                        editTokenProductCode.setTestOutput("Edit Token Product Name: SUCCESSFUL");
                    } else {
                        editTokenProductCode.setTestOutput("Edit Token Product Name: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getName() = " + editTokenProductCode.getName() + "\n" +
                                "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                    }
                    break;

                case ("IssuerTokenProductCode"):
                    if (txtIssuerTokenProductCode.getAttribute("value").equalsIgnoreCase(editTokenProductCode.getIssuerTokenProductCode())) {
                        editTokenProductCode.setTestOutput("Edit Token Product Code: SUCCESSFUL");
                    } else {
                        editTokenProductCode.setTestOutput("Edit Token Product Name: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getName() = " + editTokenProductCode.getName() + "\n" +
                                "ACT: txtIssuerTokenProductCode.getAttribute(\"value\") = " + txtIssuerTokenProductCode.getAttribute("value"));
                    }
                    break;

                case ("TokenProductGroup"):
                    selTokenProductGroupList = new Select(webDriver.findElement(By.id("productGroup")));
                    if(selTokenProductGroupList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenProductCode.getTokenProductGroup()))
                    {
                        editTokenProductCode.setTestOutput("Edit Token Product Token Product Group: SUCCESSFUL");
                    }
                    else{
                        editTokenProductCode.setTestOutput("Edit Token Product Name: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getTokenProductGroup() = " + editTokenProductCode.getTokenProductGroup() + "\n" +
                                "ACT: selTokenProductGroupList.getFirstSelectedOption().getText() = " + selTokenProductGroupList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("RetentionDays"):
                    if (txtRetentionDays.getAttribute("value").equalsIgnoreCase(editTokenProductCode.getRetentionDays())) {
                        editTokenProductCode.setTestOutput("Edit Token Product Retention Days: SUCCESSFUL");
                    } else {
                        editTokenProductCode.setTestOutput("Edit Token Product Retention Days: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getRetentionDays() = " + editTokenProductCode.getRetentionDays() + "\n" +
                                "ACT: txtRetentionDays.getAttribute(\"value\") = " + txtRetentionDays.getAttribute("value"));
                    }
                    break;


                case ("Country"):
                    selCountryList = new Select(webDriver.findElement(By.id("country")));
                    if(selCountryList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenProductCode.getCountry()))
                    {
                        editTokenProductCode.setTestOutput("Edit Token Product Country: SUCCESSFUL");
                    }
                    else{
                        editTokenProductCode.setTestOutput("Edit Token Product Country: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getCountry() = " + editTokenProductCode.getCountry() + "\n" +
                                "ACT: selCountryList.getFirstSelectedOption().getText() = " + selCountryList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("Language"):
                    selLanguageList = new Select(webDriver.findElement(By.id("language")));
                    if(selLanguageList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenProductCode.getLanguage()))
                    {
                        editTokenProductCode.setTestOutput("Edit Token Product Language: SUCCESSFUL");
                    }
                    else{
                        editTokenProductCode.setTestOutput("Edit Token Product Language: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getLanguage() = " + editTokenProductCode.getLanguage() + "\n" +
                                "ACT: selLanguageList.getFirstSelectedOption().getText() = " + selLanguageList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("PersoBureau"):
                    selPersoBureauList = new Select(webDriver.findElement(By.id("persoBureau")));
                    if(selPersoBureauList.getFirstSelectedOption().getText().equalsIgnoreCase(editTokenProductCode.getPersoBureau()))
                    {
                        editTokenProductCode.setTestOutput("Edit Token Product Perso Bureau: SUCCESSFUL");
                    }
                    else{
                        editTokenProductCode.setTestOutput("Edit Token Product Perso Bureau: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getPersoBureau() = " + editTokenProductCode.getPersoBureau() + "\n" +
                                "ACT: selPersoBureauList.getFirstSelectedOption().getText() = " + selPersoBureauList.getFirstSelectedOption().getText());
                    }
                    break;

                case ("FeedbackRequired"):
                    String cbxChecked = adminCommon.getCheckboxValue(cbxFeedbackRequired);
                    if(cbxChecked.equalsIgnoreCase(editTokenProductCode.getFeedbackRequired()))
                    {
                        editTokenProductCode.setTestOutput("Edit Token Product Feedback Required: SUCCESSFUL");
                    }
                    else{
                        editTokenProductCode.setTestOutput("Edit Token Product Feedback Required: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getFeedbackRequired() = " + editTokenProductCode.getFeedbackRequired() + "\n" +
                                "ACT: cbxFeedbackRequired.getCheckboxValue() = " + cbxChecked);
                    }
                    break;

                case ("Active"):
                    String cbxChecked2 = adminCommon.getCheckboxValue(cbxActive);
                    if(cbxChecked2.equalsIgnoreCase(editTokenProductCode.getActive()))
                    {
                        editTokenProductCode.setTestOutput("Edit Token Product Status: SUCCESSFUL");
                    }
                    else{
                        editTokenProductCode.setTestOutput("Edit Token Product Status: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getActive() = " + editTokenProductCode.getActive() + "\n" +
                                "ACT: cbxActive.getCheckboxValue() = " + cbxChecked2);
                    }
                    break;

                case ("ServiceCode"):
                    if (txtServiceCode.getAttribute("value").equalsIgnoreCase(editTokenProductCode.getServiceCode())) {
                        editTokenProductCode.setTestOutput("Edit Token Product Service Code: SUCCESSFUL");
                    } else {
                        editTokenProductCode.setTestOutput("Edit Token Product Service Code: UNSUCCESSFUL\n" +
                                "EXP: editTokenProductCode.getServiceCode() = " + editTokenProductCode.getServiceCode() + "\n" +
                                "ACT: txtServiceCode.getAttribute(\"value\") = " + txtServiceCode.getAttribute("value"));
                    }
                    break;


            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewTokenProduct));
            btnReset.click();

        }

        return editTokenProductCode;

    }

    public TokenProductDataRecord checkDeleteTokenProduct(TokenProductDataRecord deleteTokenProduct){

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
            deleteTokenProduct.setTestOutput("Delete Token Product: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            deleteTokenProduct.setId(null);
            deleteTokenProduct.setName(null);
            deleteTokenProduct.setIssuerTokenProductCode(null);
            deleteTokenProduct.setIssuer(null);
            deleteTokenProduct.setTokenProductGroup(null);
            deleteTokenProduct.setRetentionDays(null);
            deleteTokenProduct.setCountry(null);
            deleteTokenProduct.setPersoBureau(null);
            deleteTokenProduct.setFeedbackRequired(null);
            deleteTokenProduct.setActive(null);
            deleteTokenProduct.setTestOutput("Delete Token Product: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(deleteTokenProduct.getName())){

                try {
                    btnModalYes.click();
                    deleteTokenProduct.setTestOutput("Delete Token Product: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    deleteTokenProduct.setTestOutput("Delete Token Product: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return deleteTokenProduct;
    }

    public TokenProductDataRecord getTableRecordByRecord(TokenProductDataRecord tokenProduct) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        TokenProductDataRecord record = new TokenProductDataRecord();
        searchTokenProduct(tokenProduct.getParentInstitution(), tokenProduct.getIssuer(), tokenProduct.getTokenProductGroup());

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){
                record.setId(null);
                record.setName(null);
                btnReset.click();
                return record;
            }

        }

        adminCommon.sortTableByColumn("ID","DESC");
        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + tokenProduct.getId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setId(tokenProduct.getId());
                record.setName(webDriver.findElement(By.id("col-name" + tokenProduct.getId())).getAttribute("textContent"));
                record.setIssuerTokenProductCode(webDriver.findElement(By.id("col-code" + tokenProduct.getId())).getAttribute("textContent"));
                record.setIssuer(webDriver.findElement(By.id("col-issuer" + tokenProduct.getId())).getAttribute("textContent"));
                record.setTokenProductGroup(webDriver.findElement(By.id("col-group" + tokenProduct.getId())).getAttribute("textContent"));
                //record.setRetentionDays(webDriver.findElement(By.id("col-retention" + tokenProduct.getId())).getAttribute("textContent"));
                record.setCountry(webDriver.findElement(By.id("col-country" + tokenProduct.getId())).getAttribute("textContent"));
                record.setPersoBureau(webDriver.findElement(By.id("col-persobureau" + tokenProduct.getId())).getAttribute("textContent"));
                //record.setFeedbackRequired(webDriver.findElement(By.id("col-feedback" + tokenProduct.getId())).getAttribute("textContent"));
                record.setActive(webDriver.findElement(By.id("col-active" + tokenProduct.getId())).getAttribute("textContent"));

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
                    record.setIssuerTokenProductCode(null);
                    record.setIssuer(null);
                    record.setTokenProductGroup(null);
                    record.setRetentionDays(null);
                    record.setCountry(null);
                    record.setPersoBureau(null);
                    record.setFeedbackRequired(null);
                    record.setActive(null);
                    btnReset.click();
                    return record;
                }

            }

        }while (!found);
        btnReset.click();
        return record;
    }

    public TokenProductDataRecord getDbIdByTokenProductName(TokenProductDataRecord key){

        TokenProductDataRecord record = new TokenProductDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM C_D_TOKEN_PRODUCT " +
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
                record.setParentIssuer(null);

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

    public TokenProductDataRecord getDBDataById(String instId) throws Exception{

        TokenProductDataRecord record = new TokenProductDataRecord();

        try {

            String getIssByIdSQL = "SELECT CDTP.ID, CDTP.NAME, CDTP.ISSUER_TOKEN_PRODUCT_CODE, CDI.NAME AS ISSUER, " +
                    "CDTPG.NAME AS TOKEN_PRODUCT_GROUP, CDTP.RETENTION_DAYS, CTRY.NAME AS COUNTRY, " +
                    "LANG.NAME, IDPB.NAME AS PERSO_BUREAU, " +
                    "CASE " +
                    "WHEN CDTP.FEEDBACK_REQUIRED_FLAG = 'Y' THEN 'true' " +
                    "WHEN CDTP.FEEDBACK_REQUIRED_FLAG = 'N' THEN 'false' " +
                    "END AS FEEDBACK_REQUIRED_FLAG, " +
                    "CASE " +
                    "WHEN CDTP.STATUS = 'A' THEN 'true' " +
                    "WHEN CDTP.STATUS = 'I' THEN 'false' " +
                    "END AS ACTIVE_FLAG, " +
                    "CDTP.SERVICE_CODE " +
                    "FROM C_D_TOKEN_PRODUCT CDTP " +
                    "LEFT JOIN C_D_ISSUER CDI ON CDTP.ISSUER_ID = CDI.ID " +
                    "LEFT JOIN C_D_TOKEN_PRODUCT_GROUP CDTPG ON CDTP.TOKEN_PRODUCT_GROUP_ID = CDTPG.ID " +
                    "LEFT JOIN C_S_COUNTRIES CTRY ON CDTP.COUNTRY_CODE = CTRY.ID " +
                    "LEFT JOIN C_S_LANGUAGE LANG ON CDTP.LANGUAGE_CODE = LANG.LANGUAGE_CODE " +
                    "LEFT JOIN INT_D_PERSO_BUREAU IDPB ON CDTP.PERSO_BUREAU_ID = IDPB.ID " +
                    "WHERE CDTP.ID = ?";

            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setIssuerTokenProductCode(null);
                record.setIssuer(null);
                record.setTokenProductGroup(null);
                record.setRetentionDays(null);
                record.setCountry(null);
                record.setLanguage(null);
                record.setPersoBureau(null);
                record.setFeedbackRequired(null);
                record.setActive(null);
                record.setServiceCode(null);
            }
            else{

                rs.next();

                record.setId(rs.getString(1));
                record.setName(rs.getString(2));
                record.setIssuerTokenProductCode(rs.getString(3));
                record.setIssuer(rs.getString(4));
                record.setTokenProductGroup(rs.getString(5));
                record.setRetentionDays(rs.getString(6));
                record.setCountry(rs.getString(7));
                record.setLanguage(rs.getString(8));
                record.setPersoBureau(rs.getString(9));
                record.setFeedbackRequired(rs.getString(10));
                record.setActive(rs.getString(11));
                record.setServiceCode(rs.getString(12));

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

            case ("IssuerTokenProductCode"):

                obStringId = "val-productCode";
                expMessage = "Issuer token product code is required";


                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtIssuerTokenProductCode.click();
                    txtIssuerTokenProductCode.clear();
                    txtIssuerTokenProductCode.sendKeys(value);
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

            case ("RetentionDays"):

                obStringId = "val-retentionDays";
                expMessage = "Retention days is required";
                expMessage2 = "Retention days must be a decimal value";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtRetentionDays.click();
                    txtRetentionDays.clear();
                    txtRetentionDays.sendKeys(value);
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

                        case ("Zero_Value"):
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

            case ("ServiceCode"):

                obStringId = "val-serviceCode";
                expMessage = "Service code must be in 3 digits";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtServiceCode.click();
                    txtServiceCode.clear();
                    txtServiceCode.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case ("Blank"):
                        case ("Three_Digit_Decimal_Value"):
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




