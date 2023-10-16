package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.PVVDataRecord;
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


public class PVVPage {

    public WebDriver webDriver;
    private final TokenProductsPage tokenProductsPage;
    private final TokenApplicationProfilePage tokenApplicationProfilePage;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewPinVerificationValue;

    @FindBy(id = "btn-add-zone")
    WebElement btnSubmitNewPinVerificationValue;

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

    @FindBy(id = "input-key-id-dummy")
    WebElement txtId;

    @FindBy(id = "search-appSeqNum")
    WebElement txtFilterAppSeqNum;

    @FindBy(id = "tokenProduct")
    WebElement selTokenProduct;

    @FindBy(id = "val-tokenProduct")
    WebElement msgTokenProduct;

    @FindBy(id = "appSeqNum")
    WebElement selAppSeqNum;

    @FindBy(id = "val-appSeqNum")
    WebElement msgAppSeqNum;

    @FindBy(id = "pvkIdentifier")
    WebElement txtPvkIdentifier;

    @FindBy(id = "val-pvkIdentifier")
    WebElement msgPvkIdentifier;

    @FindBy(id = "pvMethod")
    WebElement selPinVerificationMethod;

    @FindBy(id = "val-pvMethod")
    WebElement msgPinVerificationMethod;

    @FindBy(id = "pvKey")
    WebElement selPinVerificationKey;

    @FindBy(id = "val-pvKey")
    WebElement msgPinVerificationKey;


    public PVVPage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        tokenProductsPage = new TokenProductsPage(webDriver);
        tokenApplicationProfilePage = new TokenApplicationProfilePage(webDriver);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public void filterByRecord(PVVDataRecord searchPvv){

        Select selTokenProductFilter = new Select(webDriver.findElement(By.id("search-token-product")));

        btnReset.click();
        adminCommon.hardWait(500);
        selTokenProductFilter.selectByVisibleText(searchPvv.getParentTokenProduct());
        adminCommon.hardWait(500);

    }

    public boolean isPageOpened() { return(pageTitle.getText().equalsIgnoreCase("Additional PIN verification values")); }

    public void clickAddButton(){

        btnAddNewPinVerificationValue.click();

    }

    public boolean checkRequiredFieldMessages(String field, String message){

        boolean rc = true;

        switch(field) {


            case("TokenProduct"):
                selTokenProduct.click();
                selTokenProduct.sendKeys(Keys.TAB);
                selTokenProduct.sendKeys(Keys.TAB);
                if (!msgTokenProduct.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("AppSequenceNumber"):
                selAppSeqNum.click();
                selAppSeqNum.sendKeys(Keys.TAB);
                selAppSeqNum.sendKeys(Keys.TAB);
                if (!msgAppSeqNum.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                adminCommon.hardWait(10000);
                break;

            case ("PVKIdentifier"):
                txtPvkIdentifier.clear();
                txtPvkIdentifier.click();
                txtPvkIdentifier.sendKeys(Keys.TAB);
                if (!msgPvkIdentifier.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PinVerificationMethod"):
                selPinVerificationMethod.click();
                selPinVerificationMethod.sendKeys(Keys.TAB);
                selPinVerificationMethod.sendKeys(Keys.TAB);
                if (!msgPinVerificationMethod.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("PinVerificationKey"):
                selPinVerificationKey.click();
                selPinVerificationKey.sendKeys(Keys.TAB);
                selPinVerificationKey.sendKeys(Keys.TAB);
                if (!msgPinVerificationKey.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

        }

        return rc;
    }

    public PVVDataRecord enterPVVData(PVVDataRecord recordNewPVV){

        PVVDataRecord record = new PVVDataRecord();

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewPinVerificationValue));
        btnAddNewPinVerificationValue.click();

        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        adminCommon.hardWait(5000);

        Select selTokenProductList = new Select(webDriver.findElement(By.id("tokenProduct")));
        selTokenProductList.selectByVisibleText(recordNewPVV.getParentTokenProduct());

        Select selAppSeqNumList = new Select(webDriver.findElement(By.id("appSeqNum")));
        selAppSeqNumList.selectByVisibleText(recordNewPVV.getParentTokenApplicationProfileAppSeqNum());

        txtPvkIdentifier.clear();
        txtPvkIdentifier.sendKeys(recordNewPVV.getPvkIdentifier());

        Select selPinVerificationMethod = new Select(webDriver.findElement(By.id("pvMethod")));
        selPinVerificationMethod.selectByVisibleText(recordNewPVV.getPinVerificationMethod());

        Select selPinVerificationKey = new Select(webDriver.findElement(By.id("pvKey")));
        selPinVerificationKey.selectByVisibleText(recordNewPVV.getPinVerificationKey());

        btnSubmitNewPinVerificationValue.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            record = recordNewPVV;
        }
        else{

            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            record.setParentInstitution(null);
            record.setParentIssuer(null);
            record.setParentTokenProduct(null);
            record.setParentTokenProductId(null);
            record.setId(null);
            record.setParentTokenApplicationProfileName(null);
            record.setParentTokenApplicationProfileAppSeqNum(null);
            record.setPvkIdentifier(null);
            record.setPinVerificationKey(null);
            record.setPinVerificationMethod(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return record;

    }

    public PVVDataRecord editPVVData(PVVDataRecord recordEditPVV, String field){

        WebDriverWait myWait = new WebDriverWait(webDriver, 10);

        Select selPinVerificationMethodList;
        Select selPinVerificationKeyList;

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(recordEditPVV.getId())) {

            myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));

            switch (field) {

                case ("PVKIdentifier"):
                    txtPvkIdentifier.clear();
                    txtPvkIdentifier.sendKeys(recordEditPVV.getPvkIdentifier());
                    break;

                case ("PinVerificationMethod"):
                    selPinVerificationMethodList = new Select(webDriver.findElement(By.id("pvMethod")));
                    selPinVerificationMethodList.selectByVisibleText(recordEditPVV.getPinVerificationMethod());
                    break;

                case ("PinVerificationKey"):
                    selPinVerificationKeyList = new Select(webDriver.findElement(By.id("pvKey")));
                    selPinVerificationKeyList.selectByVisibleText(recordEditPVV.getPinVerificationKey());
                    break;


            }
        }

        // Click the Save button
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewPinVerificationValue));
        adminCommon.clickEditByUniqueId(recordEditPVV.getId());
        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));

        /* Check for the alert dialog */
        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if (alertDialogList.size() == 0) {

            switch (field) {

                case("PVKIdentifier"):
                    if (txtPvkIdentifier.getAttribute("value").equalsIgnoreCase(recordEditPVV.getPvkIdentifier())) {
                        recordEditPVV.setTestOutput("Edit Additional PVV PVK Identifier: SUCCESSFUL");
                    }
                    else{
                        recordEditPVV.setTestOutput("Edit Additional PVV PVK Identifier: UNSUCCESSFUL\n" +
                                "EXP: recordEditPVV.getPvkIdentifier() = " + recordEditPVV.getPvkIdentifier() + "\n" +
                                "ACT: txtPvkIdentifier.getAttribute(\"value\") = " + txtPvkIdentifier.getAttribute("value"));
                    }
                    break;

                case("PinVerificationMethod"):
                    selPinVerificationMethodList = new Select(webDriver.findElement(By.id("pvMethod")));
                    if(selPinVerificationMethodList.getFirstSelectedOption().getText().equalsIgnoreCase(recordEditPVV.getPinVerificationMethod()))
                    {
                        recordEditPVV.setTestOutput("Edit Additional PVV Pin Verification Method: SUCCESSFUL");
                    }
                    else{
                        recordEditPVV.setTestOutput("Edit Additional PVV Pin Verification Method: UNSUCCESSFUL\n" +
                                "EXP: recordEditPVV.getPinVerificationMethod() = " + recordEditPVV.getPinVerificationMethod() + "\n" +
                                "ACT: selPinVerificationMethodList.getFirstSelectedOption().getText() = " + selPinVerificationMethodList.getFirstSelectedOption().getText());
                    }
                    break;

                case("PinVerificationKey"):
                    selPinVerificationKeyList = new Select(webDriver.findElement(By.id("pvKey")));
                    if(selPinVerificationKeyList.getFirstSelectedOption().getText().equalsIgnoreCase(recordEditPVV.getPinVerificationKey()))
                    {
                        recordEditPVV.setTestOutput("Edit Additional PVV Pin Verification Key: SUCCESSFUL");
                    }
                    else{
                        recordEditPVV.setTestOutput("Edit Additional PVV Pin Verification Key: UNSUCCESSFUL\n" +
                                "EXP: recordEditPVV.getPinVerificationKey() = " + recordEditPVV.getPinVerificationKey() + "\n" +
                                "ACT: selPinVerificationKeyList.getFirstSelectedOption().getText() = " + selPinVerificationKeyList.getFirstSelectedOption().getText());
                    }
                    break;

            }

            btnCancel.click();
            myWait.until(ExpectedConditions.visibilityOf(btnAddNewPinVerificationValue));

        }
        else {
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            recordEditPVV.setParentInstitution(null);
            recordEditPVV.setParentIssuer(null);
            recordEditPVV.setParentTokenProduct(null);
            recordEditPVV.setParentTokenProductId(null);
            recordEditPVV.setId(null);
            recordEditPVV.setParentTokenApplicationProfileName(null);
            recordEditPVV.setParentTokenApplicationProfileAppSeqNum(null);
            recordEditPVV.setPvkIdentifier(null);
            recordEditPVV.setPinVerificationKey(null);
            recordEditPVV.setPinVerificationMethod(null);
            recordEditPVV.setTestOutput("Edit Additional PVV: UNSUCCESSFUL\n" + alertDialog.getText());
            btnOK.click();
        }

        return(recordEditPVV);

    }

    public PVVDataRecord checkDeletePVV(PVVDataRecord recordEditPVV){

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
            recordEditPVV.setTestOutput("Delete Additional PVV: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            recordEditPVV.setParentInstitution(null);
            recordEditPVV.setParentIssuer(null);
            recordEditPVV.setParentTokenProduct(null);
            recordEditPVV.setParentTokenProductId(null);
            recordEditPVV.setId(null);
            recordEditPVV.setParentTokenApplicationProfileName(null);
            recordEditPVV.setParentTokenApplicationProfileAppSeqNum(null);
            recordEditPVV.setPvkIdentifier(null);
            recordEditPVV.setPinVerificationKey(null);
            recordEditPVV.setPinVerificationMethod(null);
            recordEditPVV.setTestOutput("Delete Additional PVV: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(recordEditPVV.getId())){

                try {
                    btnModalYes.click();
                    recordEditPVV.setTestOutput("Delete Additional PVV: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    recordEditPVV.setTestOutput("Delete Additional PVV: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return recordEditPVV;
    }


//    public PVVDataRecord getPVVRecordByPVKIdentifier(PVVDataRecord pvvPvkIdentifier) {
//
//        adminCommon.sleep(1000);
//
//        WebDriverWait myWait = new WebDriverWait(webDriver, 10);
//        myWait.until(ExpectedConditions.visibilityOf(orAdmin.tblPVVList()));
//        PVVDataRecord record = new PVVDataRecord();
//
//        List<WebElement> rows = orAdmin.tblPVVList().findElements(By.xpath(".//tr[contains(@class,'x-grid-row')]"));
//        if(rows.size()==0){
//            record.setPvkIdentifier(null);
//        }
//        else {
//            boolean found = false;
//            for (WebElement row : rows) {
//
//                WebElement cellId = row.findElement(By.xpath(".//td[contains(@class, 'idPvkIdentifier')]"));
//                if (pvvPvkIdentifier.getPvkIdentifier().equals(cellId.getText())) {
//
//                    record.setId(row.findElement(By.xpath(".//td[contains(@class, 'idGID')]")).getText());
//                    record.setPvkIdentifier(row.findElement(By.xpath(".//td[contains(@class, 'idPvkIdentifier')]")).getText());
//                    record.setParentTokenProduct(row.findElement(By.xpath(".//td[contains(@class, 'idGTPID')]")).getText());
//                    record.setParentTokenApplicationProfileAppSeqNum(row.findElement(By.xpath(".//td[contains(@class, 'idASNum')]")).getText());
//                    record.setPinVerificationKey(row.findElement(By.xpath(".//td[contains(@class, 'idPVK')]")).getText());
//                    record.setPinVerificationMethod(row.findElement(By.xpath(".//td[contains(@class, 'idPVM')]")).getText());
//                    found = true;
//                    break;
//
//                }
//            }
//            if (!found) {
//                record.setPvkIdentifier(null);
//            }
//        }
//
//        return(record);
//    }



    public PVVDataRecord getDBDataById(PVVDataRecord pvv){

        PVVDataRecord record = new PVVDataRecord();

        try {

            String SQL = "SELECT INST.NAME, ISS.NAME, TP.NAME, PVV.TOKEN_PRODUCT_ID, PVV.ID, TAP.NAME, TAP.APP_SEQ_NUMBER, PVV.PVK_IDENTIFIER , SCI.NAME, PVM.NAME " +
                    "FROM ENC_D_PVVS PVV " +
                    "LEFT JOIN C_D_TOKEN_APP_PROFILE TAP ON PVV.TOKEN_PRODUCT_ID=TAP.TOKEN_PRODUCT_ID " +
                    "AND PVV.APP_SEQ_NUMBER=TAP.APP_SEQ_NUMBER " +
                    "LEFT JOIN C_D_TOKEN_PRODUCT TP ON PVV.TOKEN_PRODUCT_ID=TP.ID " +
                    "LEFT JOIN C_D_ISSUER ISS ON PVV.TOKEN_PRODUCT_ID=TP.ID AND TP.ISSUER_ID=ISS.ID " +
                    "LEFT JOIN C_D_INSTITUTION INST ON ISS.INSTITUTION_ID=INST.ID " +
                    "LEFT JOIN ENC_D_SCI SCI ON PVV.SCI_PIN_VERIFICATION_KEY_ID=SCI.ID " +
                    "LEFT JOIN ENC_S_PIN_VERIFICATION_METHOD PVM ON PVV.PIN_VERIFICATION_METHOD_ID=PVM.ID " +
                    "WHERE PVV.ID = ?";

            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setString(1, pvv.getId());

            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){

                record.setParentInstitution(null);
                record.setParentIssuer(null);
                record.setParentTokenProduct(null);
                record.setParentTokenProductId(null);
                record.setId(null);
                record.setParentTokenApplicationProfileName(null);
                record.setParentTokenApplicationProfileAppSeqNum(null);
                record.setPvkIdentifier(null);
                record.setPinVerificationKey(null);
                record.setPinVerificationMethod(null);
            }
            else{
                rs.next();

                record.setParentInstitution(rs.getString(1));
                record.setParentIssuer(rs.getString(2));
                record.setParentTokenProduct(rs.getString(3));
                record.setParentTokenProductId(rs.getString(4));
                record.setId(rs.getString(5));
                record.setParentTokenApplicationProfileName(rs.getString(6));
                record.setParentTokenApplicationProfileAppSeqNum(rs.getString(7));
                record.setPvkIdentifier(rs.getString(8));
                record.setPinVerificationKey(rs.getString(9));
                record.setPinVerificationMethod(rs.getString(10));

            }
            connection.close();

        }catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public PVVDataRecord getDBIdByPVKIdentifier(String pvkIdentifier){

        PVVDataRecord record = new PVVDataRecord();

        try {

            String SQL = "SELECT INST.NAME, ISS.NAME, TP.NAME, PVV.TOKEN_PRODUCT_ID, PVV.ID, TAP.NAME, TAP.APP_SEQ_NUMBER, PVV.PVK_IDENTIFIER , SCI.NAME, PVM.NAME " +
                    "FROM ENC_D_PVVS PVV " +
                    "LEFT JOIN C_D_TOKEN_APP_PROFILE TAP ON PVV.TOKEN_PRODUCT_ID=TAP.TOKEN_PRODUCT_ID " +
                    "AND PVV.APP_SEQ_NUMBER=TAP.APP_SEQ_NUMBER " +
                    "LEFT JOIN C_D_TOKEN_PRODUCT TP ON PVV.TOKEN_PRODUCT_ID=TP.ID " +
                    "LEFT JOIN C_D_ISSUER ISS ON PVV.TOKEN_PRODUCT_ID=TP.ID AND TP.ISSUER_ID=ISS.ID " +
                    "LEFT JOIN C_D_INSTITUTION INST ON ISS.INSTITUTION_ID=INST.ID " +
                    "LEFT JOIN ENC_D_SCI SCI ON PVV.SCI_PIN_VERIFICATION_KEY_ID=SCI.ID " +
                    "LEFT JOIN ENC_S_PIN_VERIFICATION_METHOD PVM ON PVV.PIN_VERIFICATION_METHOD_ID=PVM.ID " +
                    "WHERE PVV.PVK_IDENTIFIER = ?";

            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(SQL);
            preparedSelect.setString(1, pvkIdentifier);

            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){

                record.setParentInstitution(null);
                record.setParentIssuer(null);
                record.setParentTokenProduct(null);
                record.setParentTokenProductId(null);
                record.setId(null);
                record.setParentTokenApplicationProfileName(null);
                record.setParentTokenApplicationProfileAppSeqNum(null);
                record.setPvkIdentifier(null);
                record.setPinVerificationKey(null);
                record.setPinVerificationMethod(null);
            }
            else{
                rs.next();

                record.setParentInstitution(rs.getString(1));
                record.setParentIssuer(rs.getString(2));
                record.setParentTokenProduct(rs.getString(3));
                record.setParentTokenProductId(rs.getString(4));
                record.setId(rs.getString(5));
                record.setParentTokenApplicationProfileName(rs.getString(6));
                record.setParentTokenApplicationProfileAppSeqNum(rs.getString(7));
                record.setPvkIdentifier(rs.getString(8));
                record.setPinVerificationKey(rs.getString(9));
                record.setPinVerificationMethod(rs.getString(10));

            }
            connection.close();

        }catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public PVVDataRecord getTableRecordByRecord(PVVDataRecord editPvv) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        PVVDataRecord record = new PVVDataRecord();

        List<WebElement> paginationFirstButtonList = webDriver.findElements(By.id("btn-pagination-first"));
        if(paginationFirstButtonList.size()==0){

            List<WebElement> noDataMessageList = webDriver.findElements(By.xpath("//img[contains(@src,'sad-card.svg')]"));
            if(noDataMessageList.size()>0){

                record.setParentInstitution(null);
                record.setParentIssuer(null);
                record.setParentTokenProduct(null);
                record.setParentTokenProductId(null);
                record.setId(null);
                record.setParentTokenApplicationProfileName(null);
                record.setParentTokenApplicationProfileAppSeqNum(null);
                record.setPvkIdentifier(null);
                record.setPinVerificationKey(null);
                record.setPinVerificationMethod(null);

                return record;
            }

        }

        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + editPvv.getId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setId(editPvv.getId());
                record.setParentTokenProduct(webDriver.findElement(By.id("col-tp" + editPvv.getId())).getAttribute("textContent"));
                record.setParentTokenApplicationProfileAppSeqNum(webDriver.findElement(By.id("col-appSeqNum" + editPvv.getId())).getAttribute("textContent"));
                record.setPvkIdentifier(webDriver.findElement(By.id("col-pvk-id" + editPvv.getId())).getAttribute("textContent"));
                record.setPinVerificationKey(webDriver.findElement(By.id("col-pin-vk" + editPvv.getId())).getAttribute("textContent"));
                record.setPinVerificationMethod(webDriver.findElement(By.id("col-pin-vm" + editPvv.getId())).getAttribute("textContent"));

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
                    record.setParentInstitution(null);
                    record.setParentIssuer(null);
                    record.setParentTokenProduct(null);
                    record.setParentTokenProductId(null);
                    record.setId(null);
                    record.setParentTokenApplicationProfileName(null);
                    record.setParentTokenApplicationProfileAppSeqNum(null);
                    record.setPvkIdentifier(null);
                    record.setPinVerificationKey(null);
                    record.setPinVerificationMethod(null);

                    return record;
                }

            }

        }while (!found);

        return record;
    }

    public String validateTextInputFields(String field){

        StringBuilder msg = new StringBuilder();
        Actions builder = new Actions(webDriver);
        String obStringId;
        String expMessage;
        String expMessage2;

        ValidationTestcases validationTestcases = new ValidationTestcases();


        switch (field) {

//            case ("AppSequenceNumber"):
//
//                obStringId = "val-appSeqNum";
//                expMessage = "Application sequence number is required";
//                expMessage2 = "Application sequence number must be an integer value of 1-99";
//
//                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {
//
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//
//                    txtAppSeqNum.click();
//                    txtAppSeqNum.sendKeys(Keys.DELETE);
//                    txtAppSeqNum.sendKeys(Keys.DELETE);
//                    txtAppSeqNum.sendKeys(Keys.DELETE);
//                    txtAppSeqNum.sendKeys(Keys.DELETE);
//                    txtAppSeqNum.sendKeys(Keys.BACK_SPACE);
//                    txtAppSeqNum.sendKeys(Keys.BACK_SPACE);
//                    txtAppSeqNum.sendKeys(Keys.BACK_SPACE);
//                    txtAppSeqNum.sendKeys(Keys.BACK_SPACE);
//                    txtAppSeqNum.click();
//                    txtAppSeqNum.sendKeys(value);
//                    txtAppSeqNum.sendKeys(Keys.TAB);
//                    try {
//                        builder.moveToElement(txtId).click().build().perform();
//                        int width = txtId.getSize().getWidth();
//                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
//                    }catch(ElementClickInterceptedException ignored){}
//
//                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");
//
//                    switch (key) {
//
//                        case ("Blank"):
//                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
//                            break;
//
//                        case ("Decimal_Value"):
//                        case ("Three_Digit_Decimal_Value"):
//                        case ("Excluded_Pins"):
//                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
//                            break;
//
//                        default:
//                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
//                            break;
//
//                    }
//
//                }
//
//                break;

            case ("PVKIdentifier"):

                obStringId = "val-pvkIdentifier";
                expMessage = "PVK identifier is required";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPvkIdentifier.click();
                    txtPvkIdentifier.clear();
                    txtPvkIdentifier.sendKeys(value);
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
