package com.aconite.apm.gui.automation.webpages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aconite.apm.gui.automation.databasegathers.QuartzJobListDatabaseGatherer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskSchedulerPage
{

    WebDriver webDriver;
    public AdminCommon adminCommon;

    @FindBy (id = "btn-add-key")
    WebElement btnAddTask;

    @FindBy (id = "btn-add-zone")
    WebElement btnSubmitTask;

    @FindBy(tagName = "h1")
    WebElement pageTitle;

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

    @FindBy(id = "id")
    WebElement txtTaskId;

    @FindBy(id = "name")
    WebElement selTask;

    @FindBy(id = "issuer")
    WebElement selIssuer;

    @FindBy(id = "status")
    WebElement selStatus;

    @FindBy(id = "btn-cron-generator-daily")
    WebElement btnDaily;

    @FindBy(id = "btn-cron-generator-weekly")
    WebElement btnWeekly;

    @FindBy(id = "btn-cron-generator-monthly")
    WebElement btnMonthly;

    @FindBy(id = "btn-cron-generator-oneOff")
    WebElement btnOneOff;

    @FindBy(id = "startTime")
    WebElement txtStartTime;

    @FindBy(id = "recur")
    WebElement txtRecurrence;

    @FindBy(id = "cronParams")
    WebElement txtSchedule;

    public QuartzJobListDatabaseGatherer quartzJobListDatabaseGatherer1;
    public QuartzJobListDatabaseGatherer quartzJobListDatabaseGatherer2;
    public String taskId;


    public TaskSchedulerPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        adminCommon = new AdminCommon(this.webDriver);
    }

    public boolean isPageOpened ()
    {
        return(pageTitle.getText().equalsIgnoreCase("Scheduler"));
    }

    public void clickOnAdd ()
    {

        try {
            WebDriverWait myWait = new WebDriverWait(webDriver, 30);
            myWait.until(ExpectedConditions.visibilityOf(btnAddTask));
            btnAddTask.click();
        }
        catch(ElementClickInterceptedException ignored){}
    }

    public String addTask(String taskName, String issuer) throws Exception{

        Actions builder = new Actions(webDriver);
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        // Capture task list before the new job is added
        quartzJobListDatabaseGatherer1 = new QuartzJobListDatabaseGatherer();
        quartzJobListDatabaseGatherer1.gatherData();


        // Click the Add button
        clickOnAdd();
        myWait.until(ExpectedConditions.visibilityOf(selTask));

        // Select the task
        Select selTaskList = new Select(webDriver.findElement(By.id("name")));
        selTaskList.selectByVisibleText(taskName);
        txtTaskId.click();

        // if the issuer field is enabled select the issuer
        String issuerDisabled = selIssuer.getAttribute("disabled");
        if(issuerDisabled == null){
            issuerDisabled = "false";
        }
        if(issuerDisabled.equalsIgnoreCase("false")){
            Select selIssuerList = new Select(webDriver.findElement(By.id("issuer")));
            selIssuerList.selectByVisibleText(issuer);
        }

        // Select false from the Active drop down
        builder.moveToElement(selStatus).click().build().perform();
        adminCommon.hardWait(1000);
        selStatus.sendKeys("f");
        adminCommon.hardWait(1000);
        selStatus.sendKeys(Keys.TAB);
        int width = selStatus.getSize().getWidth();
        builder.moveToElement(selStatus).moveByOffset(width, 0).click().build().perform();

        // Click the daily button
        myWait.until(ExpectedConditions.visibilityOf(btnDaily));
        btnDaily.click();

        // Enter a future datetime into the Task starting time field
        myWait.until(ExpectedConditions.visibilityOf(txtStartTime));
        width = txtStartTime.getSize().getWidth();
        builder.moveToElement(txtStartTime).build().perform();
        txtStartTime.click();
        txtStartTime.sendKeys("2359");
        builder.moveToElement(txtStartTime).moveByOffset(width, 0).click().build().perform();

        // Enter a recurrence value
        txtRecurrence.clear();
        txtRecurrence.sendKeys("2");

        // Click the add task button
        myWait.until(ExpectedConditions.visibilityOf(btnSubmitTask));
        btnSubmitTask.click();

        // Capture job data after the job has been added
        quartzJobListDatabaseGatherer2 = new QuartzJobListDatabaseGatherer();
        quartzJobListDatabaseGatherer2.gatherData();

        List<String> differences = new ArrayList<>(quartzJobListDatabaseGatherer2.records);
        differences.removeAll(quartzJobListDatabaseGatherer1.records);
        taskId = differences.get(0).substring(0,differences.get(0).length()-4);

        if(adminCommon.checkScheduledTaskInTable(taskName, taskId, "false", adminCommon.getTodayHyphenated() + " 23:59:00", issuer)) {
            return taskId;
        }
        else{
            return null;
        }

    }

    public String editTask(String taskName, String taskId, String issuer){

        Actions builder = new Actions(webDriver);
        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

//        System.out.println("taskId = " + taskId);
        adminCommon.clickEditByUniqueId("-" + taskId); //ensure hyphen is prepended

        myWait.until(ExpectedConditions.visibilityOf(selStatus));

        // Select false from the Active drop down
        builder.moveToElement(selStatus).click().build().perform();
        adminCommon.hardWait(1000);
        selStatus.sendKeys("t");
        adminCommon.hardWait(1000);
        selStatus.sendKeys(Keys.TAB);
        int width = selStatus.getSize().getWidth();
        builder.moveToElement(selStatus).moveByOffset(width, 0).click().build().perform();

        btnSaveChanges.click();

        if(adminCommon.checkScheduledTaskInTable(taskName, taskId, "true", adminCommon.getTodayHyphenated() + " 23:59:00", issuer)) {
            return taskId;
        }
        else{
            return null;
        }

    }

    public String runTask(String taskName, String taskId){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddTask));

        String lastRunBefore = webDriver.findElement(By.id("col-lastRunTime-" + taskId)).getAttribute("textContent");
        int lastRunIdBefore = adminCommon.getLastRunIdInDatabase();

        adminCommon.clickRunByUniqueId("-" + taskId);

        myWait.until(ExpectedConditions.visibilityOf(btnModalYes));
        btnModalYes.click();

        if(!taskName.equalsIgnoreCase("housekeeper")) {
            boolean runComplete = adminCommon.checkRunInDatabase(lastRunIdBefore, 60);
            if (!runComplete) {
                return ("ERROR>Extract for " + taskName + " did not complete within timeout limit");
            }
        }
        else{
            adminCommon.hardWait(10000);
        }

        String lastRunAfter = webDriver.findElement(By.id("col-lastRunTime-" + taskId)).getAttribute("textContent");
        if(lastRunBefore.equalsIgnoreCase(lastRunAfter)){
            return("ERROR>Run for " + taskName + " has not updated application.");
         }

        return "OK>" + lastRunAfter;

    }

    public boolean deleteTask(String taskName, String taskId){

        List <WebElement> btnModalYesAfterList;

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(btnAddTask));

        adminCommon.clickDeleteByUniqueId("-" + taskId);

        myWait.until(ExpectedConditions.visibilityOf(btnModalYes));

        try {

//            List <WebElement> btnModalYesList = webDriver.findElements(By.id("btn_1"));

            do{
                try {
                    btnModalYes.click();
                }
//                catch(Exception e){
//                        System.out.println(e.toString() + "\n" + Arrays.toString(e.getStackTrace()));
//                        e.printStackTrace();
//                    }
                catch(ElementClickInterceptedException ignored){}

                adminCommon.hardWait(1000);
                btnModalYesAfterList = webDriver.findElements(By.id("btn_1"));

            }while(btnModalYesAfterList.size()>0);

        }
//        catch(ElementClickInterceptedException ignored){}
        catch(Exception e){
            System.out.println(e.getStackTrace().toString());
            e.printStackTrace();
        }

        adminCommon.hardWait(1000);

        myWait.until(ExpectedConditions.visibilityOf(btnAddTask));
        if(!quartzJobListDatabaseGatherer2.gatherDeletedData(taskId)) {
            return false;
        }
        return true;
    }





}
