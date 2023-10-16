package com.aconite.apm.gui.automation.webpages;

import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver webDriver;

    @FindBy (id = "Administration")
    WebElement mnuAdministration;

    @FindBy (id = "Products")
    WebElement mnuProducts;

    @FindBy (id = "Operation")
    WebElement mnuOperation;

    @FindBy (id = "Monitoring")
    WebElement mnuMonitoring;

    @FindBy (id = "Transaction")
    WebElement mnuTransaction;

    @FindBy (id = "Tokens")
    WebElement mnuTokens;

    @FindBy (id = "countries")
    WebElement mnuCountries;

    @FindBy (id = "keys")
    WebElement mnuKeys;

    @FindBy (id = "encrypt-zone")
    WebElement mnuEncryptionZones;

    @FindBy (id = "institutions")
    WebElement mnuInstitutions;

    @FindBy (id = "issuers")
    WebElement mnuIssuers;

    @FindBy (id = "perso-bureaus")
    WebElement mnuPersoBureaus;

    @FindBy (id = "interfaces")
    WebElement mnuInterfaces;

    @FindBy (id = "message-templates")
    WebElement mnuMessageTemplates;

    @FindBy (id = "sms-template-texts")
    WebElement mnuSmsTemplateText;

    @FindBy (id = "sms-templates")
    WebElement mnuSmsTemplate;

    @FindBy (id = "token-product-groups")
    WebElement mnuTokenProductGroups;

    @FindBy (id = "token-products")
    WebElement mnuTokenProducts;

    @FindBy (id = "token-app-profiles")
    WebElement mnuTokenAppProfiles;

    @FindBy (id = "additional-pvvs")
    WebElement mnuAdditionalPvvs;

    @FindBy (id = "scheduler")
    WebElement mnuScheduler;

    @FindBy (id = "housekeeper")
    WebElement mnuHousekeeper;

    @FindBy (id = "audit-log")
    WebElement mnuAuditLog;

    @FindBy (id = "data-extract-log")
    WebElement mnuDataExtractLog;

    @FindBy (id = "file-import")
    WebElement mnuFileImport;

    @FindBy (id = "usage-statistics")
    WebElement mnuUsageStatistics;

    @FindBy (id = "txn-query")
    WebElement mnuTransactionQuery;

    @FindBy (id = "txn-stats")
    WebElement mnuTransactionStatistics;

    @FindBy (id = "manage-tokens")
    WebElement mnuManageTokens;

    @FindBy (id = "manage-token-apps")
    WebElement mnuManageTokenApplications;

    @FindBy (id = "token-stats")
    WebElement mnuTokenStatistics;


    public HomePage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageOpened()
    {
        //System.out.println("isPageOpened: webDiver = " + webDriver);
        return webDriver.getTitle().equals("APM");
    }

    public void clickOnAdminCountriesMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuCountries.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminKeysMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuKeys.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminEncryptionZonesMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuEncryptionZones.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminInstitutionsMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuInstitutions.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminPersoBureauMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuPersoBureaus.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminInterfacesMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuInterfaces.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminMessageTemplatesMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuMessageTemplates.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminSmsTemplateTextMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuSmsTemplateText.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnAdminSMSTemplatesMenuItem()
    {
        isPageOpened();
        try {
            mnuAdministration.click();
            mnuSmsTemplate.click();
        }catch(ElementClickInterceptedException ignored){}

    }


    public void clickOnProductsIssuersMenuItem()
    {
        isPageOpened();
        try {
            mnuProducts.click();
            mnuIssuers.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnProductsTokenProductGroupsMenuItem()
    {
        isPageOpened();
        try {
            mnuProducts.click();
            mnuTokenProductGroups.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnProductsTokenProductMenuItem()
    {
        isPageOpened();
        try {
            mnuProducts.click();
            mnuTokenProducts.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnProductsTokenAppProfilesMenuItem()
    {
        isPageOpened();
        try {
            mnuProducts.click();
            mnuTokenAppProfiles.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnProductsAdditionalPVVsMenuItem()
    {
        isPageOpened();
        try {
            mnuProducts.click();
            mnuAdditionalPvvs.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnOperationSchedulerMenuItem()
    {
        isPageOpened();
        try {
            mnuOperation.click();
            mnuScheduler.click();
        }
        catch(ElementClickInterceptedException ignored){}
        catch(ElementNotInteractableException ignored){}

    }

    public void clickOnOperationHousekeeperMenuItem()
    {
        isPageOpened();
        try {
            mnuOperation.click();
            mnuHousekeeper.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnMonitoringAuditLogMenuItem()
    {
        isPageOpened();
        try {
            mnuMonitoring.click();
            mnuAuditLog.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnMonitoringDataExtractLogMenuItem()
    {
        isPageOpened();
        try {
            mnuMonitoring.click();
            mnuDataExtractLog.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnMonitoringFileImportMenuItem()
    {
        isPageOpened();
        try {
            mnuMonitoring.click();
            mnuFileImport.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnMonitoringUsageStatisticsMenuItem()
    {
        isPageOpened();
        try {
            mnuMonitoring.click();
            mnuUsageStatistics.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnTransactionTransactionQueryMenuItem()
    {
        isPageOpened();
        try {
            mnuTransaction.click();
            mnuTransactionQuery.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnTransactionTransactionStatisticsMenuItem()
    {
        isPageOpened();
        try {
            mnuTransaction.click();
            mnuTransactionStatistics.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnTokensManageTokensMenuItem()
    {
        isPageOpened();
        try {
            mnuTokens.click();
            mnuManageTokens.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnTokensManageTokenApplicationsMenuItem()
    {
        isPageOpened();
        try {
            mnuTokens.click();
            mnuManageTokenApplications.click();
        }catch(ElementClickInterceptedException ignored){}

    }

    public void clickOnTokensTokensStatisticsReportItem()
    {
        isPageOpened();
        try {
            mnuTokens.click();
            mnuTokenStatistics.click();
        }catch(ElementClickInterceptedException ignored){}

    }



}
