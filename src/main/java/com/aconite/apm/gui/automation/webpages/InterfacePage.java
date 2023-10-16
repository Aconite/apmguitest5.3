package com.aconite.apm.gui.automation.webpages;

import com.aconite.apm.gui.automation.records.InterfaceDataRecord;
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

public class InterfacePage {

    public WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(id = "btn-add-key")
    WebElement btnAddNewInterface;

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

    @FindBy(id = "input-type")
    WebElement selInterfaceType;

    @FindBy(id = "val-type")
    WebElement msgType;

    @FindBy(id = "input-institution")
    WebElement selInstitution;

    @FindBy(id = "val-institution")
    WebElement msgInstitution;

    @FindBy(id = "input-ez")
    WebElement selEncryptionZone;

    @FindBy(id = "val-ez")
    WebElement msgEncryptionZone;

    @FindBy(id = "input-host")
    WebElement txtHost;

    @FindBy(id = "val-host")
    WebElement msgHost;

    @FindBy(id = "input-port")
    WebElement txtPort;

    @FindBy(id = "val-port")
    WebElement msgPort;

    @FindBy(id = "input-timeout")
    WebElement txtTimeout;

    @FindBy(id = "val-timeout")
    WebElement msgTimeout;

    @FindBy(id = "input-ssl")
    WebElement cbxSsl;

    @FindBy(id = "input-kspath")
    WebElement txtKeystorePath;

    @FindBy(id = "input-ksformat")
    WebElement selKeystoreFormat;

    @FindBy(id = "input-kspasswd")
    WebElement txtKeystorePassword;

    @FindBy(id = "input-kspasswdcfm")
    WebElement txtKeystorePasswordConfirmation;

    @FindBy(id = "input-certalias")
    WebElement txtCertificateAlias;

    @FindBy(id = "input-keypasswd")
    WebElement txtKeyPassword;

    @FindBy(id = "input-keypasswdcfm")
    WebElement txtKeyPasswordConfirmation;

    @FindBy(id = "input-context")
    WebElement txtContext;

    @FindBy(id = "input-username")
    WebElement txtUsername;

    @FindBy(id = "input-passwd")
    WebElement txtUserPassword;

    @FindBy(id = "input-passwdcfm")
    WebElement txtUserPasswordConfirmation;

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


    public InterfacePage(WebDriver webDriver){

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened() {
        return pageTitle.getText().equalsIgnoreCase("Interfaces");
    }

    public void clickAddButton(){

        btnAddNewInterface.click();

    }

    public boolean checkRequiredFieldMessages(String field, String message) {

        boolean rc = true;
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        switch (field) {

            case("name"):
                txtName.clear();
                txtName.click();
                txtName.sendKeys(Keys.TAB);
                if (!msgName.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("type"):
                selInterfaceType.click();
                selInterfaceType.sendKeys(Keys.ESCAPE);
                txtName.click();
                if (!msgType.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("institution"):
                selInstitution.click();
                selInstitution.sendKeys(Keys.ESCAPE);
                txtName.click();
                if (!msgInstitution.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("encryptionZone"):
                selEncryptionZone.click();
                selEncryptionZone.sendKeys(Keys.ESCAPE);
                txtName.click();
                if (!msgEncryptionZone.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("host"):
                txtHost.clear();
                txtHost.click();
                txtHost.sendKeys(Keys.TAB);
                if (!msgHost.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("port"):
                txtPort.clear();
                txtPort.click();
                txtPort.sendKeys(Keys.TAB);
                if (!msgPort.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;

            case("timeout"):
                txtTimeout.clear();
                txtTimeout.click();
                txtTimeout.sendKeys(Keys.TAB);
                if (!msgTimeout.getAttribute("textContent").equalsIgnoreCase(message)) {
                    rc = false;
                }
                break;
        }

        return rc;

    }

    public InterfaceDataRecord enterInterfaceData(InterfaceDataRecord newInterface){

        InterfaceDataRecord newRecord = new InterfaceDataRecord();
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        btnAddNewInterface.click();
        myWait.until(ExpectedConditions.visibilityOf(btnCancel));

        Select selInterfaceTypeList = new Select(webDriver.findElement(By.id("input-type")));
        Select selInstitutionList = new Select(webDriver.findElement(By.id("input-institution")));
        Select selEncryptionZoneList = new Select(webDriver.findElement(By.id("input-ez")));

        txtName.sendKeys(newInterface.getName());
        selInterfaceTypeList.selectByVisibleText(newInterface.getType());
        selInstitutionList.selectByVisibleText(newInterface.getInstitution());
        selEncryptionZoneList.selectByVisibleText(newInterface.getEncryptionZone());
        txtHost.sendKeys(newInterface.getHost());
        txtPort.sendKeys(newInterface.getPort());
        txtTimeout.sendKeys(newInterface.getTimeout());

        myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
        btnSaveChanges.click();

        List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
        if(alertDialogList.size()==0){
            newRecord = newInterface;
            newRecord.setTestOutput("Add Interface: SUCCESSFUL");
        }
        else{
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            newRecord.setId(null);
            newRecord.setName(null);
            newRecord.setInstitution(null);
            newRecord.setType(null);
            newRecord.setEncryptionZone(null);
            newRecord.setHost(null);
            newRecord.setPort(null);
            newRecord.setTimeout(null);

            try {
                btnOK.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }
        return newRecord;
    }

    public InterfaceDataRecord editInterfaceData(InterfaceDataRecord editInterface, String editField){

        Select selInterfaceTypeList;
        Select selInstitutionList;
        Select selEncryptionZoneList;
        Select selKeystoreFormatList;

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddNewInterface));

        /* Click the Edit button */
        if(adminCommon.clickEditByUniqueId(editInterface.getId())) {

            // Edit the field required
            switch (editField) {

                case ("name"):
                    txtName.clear();
                    txtName.click();
                    txtName.sendKeys(editInterface.getName());

                case ("type"):
                    selInterfaceTypeList = new Select(webDriver.findElement(By.id("input-type")));
                    selInterfaceTypeList.selectByVisibleText(editInterface.getType());
                    break;

                case ("institution"):
                    selInstitutionList = new Select(webDriver.findElement(By.id("input-institution")));
                    selInstitutionList.selectByVisibleText(editInterface.getInstitution());
                    break;

                case ("encryptionZone"):
                    selEncryptionZoneList = new Select(webDriver.findElement(By.id("input-ez")));
                    selEncryptionZoneList.selectByVisibleText(editInterface.getEncryptionZone());
                    break;

                case ("host"):
                    myWait.until(ExpectedConditions.visibilityOf(txtHost));
                    txtHost.clear();
                    adminCommon.hardWait(500);
                    txtHost.click();
                    adminCommon.hardWait(500);
                    txtHost.sendKeys(editInterface.getHost());
                    adminCommon.hardWait(500);
                    break;

                case ("port"):
                    txtPort.clear();
                    txtPort.click();
                    txtPort.sendKeys(editInterface.getPort());
                    break;

                case ("timeout"):
                    myWait.until(ExpectedConditions.visibilityOf(txtTimeout));
                    txtTimeout.clear();
                    adminCommon.hardWait(500);
                    txtTimeout.click();
                    adminCommon.hardWait(500);
                    txtTimeout.sendKeys(editInterface.getTimeout());
                    adminCommon.hardWait(500);
                    break;

                case ("ssl"):
                    adminCommon.selectCheckbox(cbxSsl, editInterface.getSsl());
                    break;

                case ("keystorePath"):
                    txtKeystorePath.clear();
                    txtKeystorePath.click();
                    txtKeystorePath.sendKeys(editInterface.getKeystorePath());
                    break;

                case ("keystoreFormat"):
                    selKeystoreFormatList = new Select(webDriver.findElement(By.id("input-ksformat")));
                    selKeystoreFormatList.selectByVisibleText(editInterface.getKeystoreFormat());
                    break;

//                case ("keystorePassword"):
//                    txtKeystorePassword.clear();
//                    txtKeystorePassword.click();
//                    txtKeystorePassword.sendKeys(editInterface.getKeystorePassword());
//                    txtKeystorePasswordConfirmation.clear();
//                    txtKeystorePasswordConfirmation.click();
//                    txtKeystorePasswordConfirmation.sendKeys(editInterface.getKeystorePassword());
//                    break;

                case ("certificateAlias"):
                    txtCertificateAlias.clear();
                    txtCertificateAlias.click();
                    txtCertificateAlias.sendKeys(editInterface.getCertificateAlias());
                    break;

//               case ("keyPassword"):
//                   txtKeyPassword.clear();
//                   txtKeyPassword.click();
//                   txtKeyPassword.sendKeys(editInterface.getKeyPassword());
//                   txtKeyPasswordConfirmation.clear();
//                   txtKeyPasswordConfirmation.click();
//                   txtKeyPasswordConfirmation.sendKeys(editInterface.getKeyPassword());
//                   break;

                case ("context"):
                    txtContext.clear();
                    txtContext.click();
                    txtContext.sendKeys(editInterface.getContext());
                    break;

                case ("username"):
                    txtUsername.clear();
                    txtUsername.click();
                    txtUsername.sendKeys(editInterface.getUsername());
                    break;

//               case ("userPassword"):
//                   txtUserPassword.clear();
//                   txtUserPassword.click();
//                   txtUserPassword.sendKeys(editInterface.getPassword());
//                   txtUserPasswordConfirmation.clear();
//                   txtUserPasswordConfirmation.click();
//                   txtUserPasswordConfirmation.sendKeys(editInterface.getPassword());
//                   break;

                }
            }
        else{
            System.out.println("NO EDIT FOUND");
        }

            /* Click the Save button */
            myWait.until(ExpectedConditions.visibilityOf(btnSaveChanges));
            btnSaveChanges.click();

            adminCommon.hardWait(500);

            /* Check for the alert dialog */
            List<WebElement> alertDialogList = webDriver.findElements(By.className("alert-dialog"));
            if(alertDialogList.size()==0) {

                adminCommon.clickEditByUniqueId(editInterface.getId());
                myWait.until(ExpectedConditions.visibilityOf(btnCancel));
                adminCommon.hardWait(500);

                // Edit the field required
                switch (editField) {

                    case ("name"):
                        if (txtName.getAttribute("value").equalsIgnoreCase(editInterface.getName())) {
                            editInterface.setTestOutput("Edit Interface Name: SUCCESSFUL");
                        }
                        else {
                            editInterface.setTestOutput("Edit Interface Name: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getName() = " + editInterface.getName() + "\n" +
                                    "ACT: txtName.getAttribute(\"value\") = " + txtName.getAttribute("value"));
                        }
                        break;

                    case ("type"):
                        selInterfaceTypeList = new Select(webDriver.findElement(By.id("input-type")));
                        if(selInterfaceTypeList.getFirstSelectedOption().getText().equalsIgnoreCase(editInterface.getType()))
                        {
                            editInterface.setTestOutput("Edit Interface Type: SUCCESSFUL");
                        }
                        else{
                            editInterface.setTestOutput("Edit Interface Name: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getType() = " + editInterface.getType() + "\n" +
                                    "ACT: selInterfaceTypeList.getFirstSelectedOption().getText() = " + selInterfaceTypeList.getFirstSelectedOption().getText());
                            }
                        break;

                    case ("institution"):
                        selInstitutionList = new Select(webDriver.findElement(By.id("input-institution")));
                        if(selInstitutionList.getFirstSelectedOption().getText().equalsIgnoreCase(editInterface.getInstitution()))
                        {
                            editInterface.setTestOutput("Edit Interface Institution: SUCCESSFUL");
                        }
                        else{
                            editInterface.setTestOutput("Edit Interface Institution: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getInstitution() = " + editInterface.getInstitution() + "\n" +
                                    "ACT: selInstitutionList.getFirstSelectedOption().getText() = " + selInstitutionList.getFirstSelectedOption().getText());
                        }
                        break;

                    case ("encryptionZone"):
                        selEncryptionZoneList = new Select(webDriver.findElement(By.id("input-ez")));
                        if(selEncryptionZoneList.getFirstSelectedOption().getText().equalsIgnoreCase(editInterface.getEncryptionZone()))
                        {
                            editInterface.setTestOutput("Edit Interface Encryption Zone: SUCCESSFUL");
                        }
                        else{
                            editInterface.setTestOutput("Edit Interface Encryption Zone: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getEncryptionZone() = " + editInterface.getEncryptionZone() + "\n" +
                                    "ACT: selEncryptionZoneList.getFirstSelectedOption().getText() = " + selEncryptionZoneList.getFirstSelectedOption().getText());
                        }
                        break;

                    case ("host"):
                        if (txtHost.getAttribute("value").equalsIgnoreCase(editInterface.getHost())) {
                            editInterface.setTestOutput("Edit Interface Host: SUCCESSFUL");
                        } else {
                            editInterface.setTestOutput("Edit Interface Host: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getHost() = " + editInterface.getHost() + "\n" +
                                    "ACT: txtHost.getAttribute(\"value\") = " + txtHost.getAttribute("value"));
                        }
                        break;

                    case ("port"):
                        if (txtPort.getAttribute("value").equalsIgnoreCase(editInterface.getPort())) {
                            editInterface.setTestOutput("Edit Interface Port: SUCCESSFUL");
                        } else {
                            editInterface.setTestOutput("Edit Interface Port: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getPort() = " + editInterface.getPort() + "\n" +
                                    "ACT: txtPort.getAttribute(\"value\") = " + txtPort.getAttribute("value"));
                        }
                        break;

                    case ("timeout"):
                        if (txtTimeout.getAttribute("value").equalsIgnoreCase(editInterface.getTimeout())) {
                            editInterface.setTestOutput("Edit Interface Timeout: SUCCESSFUL");
                        } else {
                            editInterface.setTestOutput("Edit Interface Timeout: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getTimeout() = " + editInterface.getTimeout() + "\n" +
                                    "ACT: txtTimeout.getAttribute(\"value\") = " + txtTimeout.getAttribute("value"));
                        }
                        break;

                    case ("ssl"):
                        if(adminCommon.getCheckboxValue(cbxSsl).equalsIgnoreCase(editInterface.getSsl())){
                            editInterface.setTestOutput("Edit Interface SSL: SUCCESSFUL");
                        } else {
                            editInterface.setTestOutput("Edit Interface SSL: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getSsl() = " + editInterface.getSsl() + "\n" +
                                    "ACT: adminCommon.getCheckboxValue(cbxSsl) = " + adminCommon.getCheckboxValue(cbxSsl));
                        }
                        break;

                    case ("keystorePath"):
                        if (txtKeystorePath.getAttribute("value").equalsIgnoreCase(editInterface.getKeystorePath())) {
                            editInterface.setTestOutput("Edit Interface Keystore Path: SUCCESSFUL");
                        } else {
                            editInterface.setTestOutput("Edit Interface Keystore Path: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getKeystorePath() = " + editInterface.getKeystorePath() + "\n" +
                                    "ACT: txtKeystorePath.getAttribute(\"value\") = " + txtKeystorePath.getAttribute("value"));
                        }
                        break;

                    case ("keystoreFormat"):
                        selKeystoreFormatList = new Select(webDriver.findElement(By.id("input-ksformat")));
                        if(selKeystoreFormatList.getFirstSelectedOption().getText().equalsIgnoreCase(editInterface.getKeystoreFormat()))
                        {
                            editInterface.setTestOutput("Edit Interface Keystore Format: SUCCESSFUL");
                        }
                        else{
                            editInterface.setTestOutput("Edit Interface Keystore Format: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getKeystoreFormat() = " + editInterface.getKeystoreFormat() + "\n" +
                                    "ACT: selKeystoreFormatList.getFirstSelectedOption().getText() = " + selKeystoreFormatList.getFirstSelectedOption().getText());
                        }
                        break;

                    case ("certificateAlias"):
                        if (txtCertificateAlias.getAttribute("value").equalsIgnoreCase(editInterface.getCertificateAlias())) {
                            editInterface.setTestOutput("Edit Interface Certificate Alias: SUCCESSFUL");
                        } else {
                            editInterface.setTestOutput("Edit Interface Certificate Alias: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getCertificateAlias() = " + editInterface.getCertificateAlias() + "\n" +
                                    "ACT: txtCertificateAlias.getAttribute(\"value\") = " + txtCertificateAlias.getAttribute("value"));
                        }
                        break;

                    case ("context"):
                        if (txtContext.getAttribute("value").equalsIgnoreCase(editInterface.getContext())) {
                            editInterface.setTestOutput("Edit Interface Context: SUCCESSFUL");
                        } else {
                            editInterface.setTestOutput("Edit Interface Context: UNSUCCESSFUL\n" +
                                    "EXP: editInterface.getContext() = " + editInterface.getContext() + "\n" +
                                    "ACT: txtContext.getAttribute(\"value\") = " + txtContext.getAttribute("value"));
                        }
                        break;

                }

                btnCancel.click();
                myWait.until(ExpectedConditions.visibilityOf(btnAddNewInterface));

            }
            else{
                System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
                editInterface.setId(null);
                editInterface.setName(null);
                editInterface.setType(null);
                editInterface.setInstitution(null);
                editInterface.setEncryptionZone(null);
                editInterface.setHost(null);
                editInterface.setPort(null);
                editInterface.setTimeout(null);
                editInterface.setSsl(null);
                editInterface.setKeystorePath(null);
                editInterface.setCertificateAlias(null);
                editInterface.setContext(null);
                editInterface.setUsername(null);
                editInterface.setTestOutput("Edit Interface: UNSUCCESSFUL\n" +  alertDialog.getText());
                btnOK.click();
            }

        return editInterface;

    }

    public InterfaceDataRecord checkDeleteInterfaceById(InterfaceDataRecord delInterface){

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
            delInterface.setTestOutput("Delete Interface: UNSUCCESSFUL\nNo dialog boxes found.");
        }

        if(alertList.size()>0){
            System.out.println("Alert Dialog Get Text\n" + alertDialog.getText());
            delInterface.setId(null);
            delInterface.setName(null);
            delInterface.setType(null);
            delInterface.setInstitution(null);
            delInterface.setEncryptionZone(null);
            delInterface.setHost(null);
            delInterface.setPort(null);
            delInterface.setTimeout(null);
            delInterface.setSsl(null);
            delInterface.setKeystorePath(null);
            delInterface.setCertificateAlias(null);
            delInterface.setContext(null);
            delInterface.setUsername(null);
            delInterface.setTestOutput("Delete Interface: UNSUCCESSFUL\n" +  alertDialog.getText());
            btnOK.click();
        }

        if(modalList.size()>0){

            if(modalDialog.getAttribute("textContent").contains(delInterface.getName())){

                try {
                    btnModalYes.click();
                    delInterface.setTestOutput("Delete Interface: SUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

            else{

                try {
                    btnModalNo.click();
                    delInterface.setTestOutput("Delete Interface: UNSUCCESSFUL\n" +  modalDialog.getAttribute("textContent"));
                }
                catch(ElementClickInterceptedException ignored){}

            }

        }

        return delInterface;

    }

    public InterfaceDataRecord getDBDataById(String instId){

        InterfaceDataRecord record = new InterfaceDataRecord();

        try {
            String getIssByIdSQL = "SELECT CDI.ID, CDI.NAME, ISIT.NAME, CDINST.NAME, EDEZ.NAME, CDI.HOST, CDI.PORT, " +
                    "CDI.TIMEOUT, " +
                    "CASE " +
                    "WHEN CDI.SSL_FLAG = 'Y' THEN 'true' " +
                    "WHEN CDI.SSL_FLAG = 'N' THEN 'false' " +
                    "END AS SSL_FLAG, NVL(CDI.KEYSTORE_PATH,' '), NVL(CDI.CERTIFICATE_ALIAS,' '), " +
                    "NVL(CDI.CONTEXT,' '), CDI.USERNAME " +
                    "FROM C_D_INTERFACE CDI " +
                    "LEFT JOIN INT_S_INTERFACE_TYPE ISIT ON CDI.TYPE_ID=ISIT.ID " +
                    "LEFT JOIN C_D_INSTITUTION CDINST ON CDI.INSTITUTION_ID=CDINST.ID " +
                    "LEFT JOIN ENC_D_ENCRYPTION_ZONE EDEZ ON CDI.INTERFACE_ENC_ZONE_ID=EDEZ.ID " +
                    "WHERE CDI.ID = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, instId);
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()) {
                record.setId(null);
                record.setName(null);
                record.setType(null);
                record.setInstitution(null);
                record.setEncryptionZone(null);
                record.setHost(null);
                record.setPort(null);
                record.setTimeout(null);
                record.setSsl(null);
                record.setKeystorePath(null);
                record.setCertificateAlias(null);
                record.setContext(null);
                record.setUsername(null);

            }
            else{

                rs.next();
                record.setId(instId);
                record.setName(rs.getString(2));
                record.setType(rs.getString(3));
                record.setInstitution(rs.getString(4));
                record.setEncryptionZone(rs.getString(5));
                record.setHost(rs.getString(6));
                record.setPort(rs.getString(7));
                record.setTimeout(rs.getString(8));
                record.setSsl(rs.getString(9));
                record.setKeystorePath(rs.getString(10));
                record.setCertificateAlias(rs.getString(11));
                record.setContext(rs.getString(12));
                record.setUsername(rs.getString(13));

            }

            connection.close();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        return(record);

    }

    public InterfaceDataRecord getTableRecordByRecord(InterfaceDataRecord editInterface) {

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        InterfaceDataRecord record = new InterfaceDataRecord();

        btnFirstPage.click();
        adminCommon.hardWait(500);

        do {

            adminCommon.hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath("//td[@id='col-id" + editInterface.getId() + "']"));

            if(rows.size()>0){

                found = true;

                record.setId(editInterface.getId());
                record.setName(webDriver.findElement(By.id("col-name" + editInterface.getId())).getAttribute("textContent"));
                record.setType(webDriver.findElement(By.id("col-type" + editInterface.getId())).getAttribute("textContent"));
                record.setInstitution(webDriver.findElement(By.id("col-institution" + editInterface.getId())).getAttribute("textContent"));
                record.setEncryptionZone(webDriver.findElement(By.id("col-encryptZone" + editInterface.getId())).getAttribute("textContent"));

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
                    record.setType(null);
                    record.setInstitution(null);
                    record.setEncryptionZone(null);

                    return record;
                }

            }

        }while (!found);

        return record;
    }

    public InterfaceDataRecord getDbIdByInterfaceName(InterfaceDataRecord key){

        InterfaceDataRecord record = new InterfaceDataRecord();

        try {
            String getIssByIdSQL = "SELECT ID " +
                    "FROM C_D_INTERFACE " +
                    "WHERE NAME = ?";
            Connection connection;
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedSelect = connection.prepareStatement(getIssByIdSQL);
            preparedSelect.setString(1, key.getName());
            ResultSet rs = preparedSelect.executeQuery();
            if(!rs.isBeforeFirst()){
                record.setId(null);
                record.setName(null);
                record.setType(null);
                record.setEncryptionZone(null);
                record.setInstitution(null);
                record.setTimeout(null);
                record.setPort(null);
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

            case ("Host"):

                obStringId = "val-host";
                expMessage = "Host is required";
                expMessage2 = "Host can only accept alphanumeric, \":\" , \".\" , \"-\" , \"_\" and \"%\"";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtHost.click();
                    txtHost.clear();
                    txtHost.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Required fields
                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        case ("Zero_Value"):
                        case ("String_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Negative_Integer_Value"):
                        case ("Hexadecimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                    }

                }

                break;

            case ("Port"):

                obStringId = "val-port";
                expMessage = "Port must be a numeric";
                expMessage2 = "Port is required";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtPort.click();
                    txtPort.clear();
                    txtPort.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Required fields
                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        // Required fields
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("Timeout"):

                obStringId = "val-timeout";
                expMessage = "Timeout must be a numeric";
                expMessage2 = "Timeout is required";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtTimeout.click();
                    txtTimeout.clear();
                    txtTimeout.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Required fields
                        case ("Blank"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage2, actMsg));
                            break;

                        // Required fields
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("Keystore path"):

                obStringId = "val-kspath";
                expMessage = "Keystore path must be a valid directory path";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtKeystorePath.click();
                    txtKeystorePath.clear();
                    txtKeystorePath.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Required fields
                        case ("Blank"):
                        case ("Back_Slash"):
                        case ("String_Value"):
                        case ("Zero_Value"):
                        case ("Decimal_Value"):
                        case ("Three_Digit_Decimal_Value"):
                        case ("Hexadecimal_Value"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                    }

                }

                break;

            case ("Keystore password"):

                obStringId = "val-keypasswd";
                expMessage = "";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtKeystorePassword.click();
                    txtKeystorePassword.clear();
                    txtKeystorePassword.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("Keystore password confirmation"):

                obStringId = "val-keypasswdcfm";
                expMessage = "";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtKeystorePasswordConfirmation.click();
                    txtKeystorePasswordConfirmation.clear();
                    txtKeystorePasswordConfirmation.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("Certificate alias"):

                obStringId = "val-certalias";
                expMessage = "\"<\", \">\" and \"\\\" are not valid characters";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtCertificateAlias.click();
                    txtCertificateAlias.clear();
                    txtCertificateAlias.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Back_Slash"):
                        case("Greater_Than"):
                        case("Less_Than"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("New key password"):

                obStringId = "val-keypasswd";
                expMessage = "";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtKeyPassword.click();
                    txtKeyPassword.clear();
                    txtKeyPassword.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("New key password confirmation"):

                obStringId = "val-keypasswdcfm";
                expMessage = "";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtKeyPasswordConfirmation.click();
                    txtKeyPasswordConfirmation.clear();
                    txtKeyPasswordConfirmation.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("Context"):

                obStringId = "val-context";
                expMessage = "\"<\", \">\" and \"\\\" are not valid characters";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtContext.click();
                    txtContext.clear();
                    txtContext.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        case("Back_Slash"):
                        case("Greater_Than"):
                        case("Less_Than"):
                            msg.append(adminCommon.validationTestcaseReporting(field,key, expMessage, actMsg));
                            break;

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;

                    }

                }

                break;

            case ("New user password"):

                obStringId = "val-passwd";
                expMessage = "";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtUserPassword.click();
                    txtUserPassword.clear();
                    txtUserPassword.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Most tests should show message
                        default:
                            msg.append(adminCommon.validationTestcaseReporting(field,key, "", actMsg));
                            break;
                    }

                }

                break;

            case ("New user password confirmation"):

                obStringId = "val-passwdcfm";
                expMessage = "";

                for (Map.Entry<String, String> entry : validationTestcases.getTestcases().entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtUserPasswordConfirmation.click();
                    txtUserPasswordConfirmation.clear();
                    txtUserPasswordConfirmation.sendKeys(value);
                    try {
                        builder.moveToElement(txtId).click().build().perform();
                        int width = txtId.getSize().getWidth();
                        builder.moveToElement(txtId).moveByOffset(width, 0).click().build().perform();
                    }catch(ElementClickInterceptedException ignored){}

                    String actMsg = webDriver.findElement(By.id(obStringId)).getAttribute("textContent");

                    switch (key) {

                        // Most tests should show message
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
