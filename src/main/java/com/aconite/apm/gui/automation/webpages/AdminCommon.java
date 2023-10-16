package com.aconite.apm.gui.automation.webpages;


import com.aconite.apm.gui.automation.databasegathers.ExtractLogDataGatherer;
import com.aconite.apm.gui.automation.testcases.ValidationTestcases;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class AdminCommon {

    public WebDriver webDriver;

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

    public AdminCommon(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void hardWait(int timeout){
        try {
            Thread.sleep(timeout);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public String getDateTime(){

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);

    }

    public String getTomorrowHyphenated(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(tomorrow);

    }

    public String getTodayHyphenated(){

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(today);

    }

    public String getTomorrowNonHyphenated(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(tomorrow);

    }

    public static String getStandardMilliseconds(String thisDate){

        String strMilliseconds;
        String delimiters = "-: .";
        int ctr = 0;
        String thisString = "";

        StringTokenizer stObj = new StringTokenizer(thisDate, delimiters);
        String[] strArray = new String[stObj.countTokens()];

        while (stObj.hasMoreElements()) {
            thisString = stObj.nextElement().toString();
//            System.out.println("StringTokenizer Output: " + thisString);
            strArray[ctr] = thisString;
            ctr++;
        }

        if(strArray.length==6){
            //assume milliseconds are missing
            strMilliseconds = "000";
        }
        else{
            if(strArray[6].equals("0") || strArray[6].equals("000")  || strArray[6].equals("000000000") ){
                strMilliseconds = "000";
            }
            else if(strArray[6].length()<3){
                while (strArray[6].length()<3){
                    strArray[6] = strArray[6] + "0";
                }
                strMilliseconds = strArray[6];
            }
            else{
                strMilliseconds = strArray[6].substring(0,3);
            }
        }

        return (strArray[0] + "-" + strArray[1] + "-" + strArray[2] + " " + strArray[3] + ":" + strArray[4] + ":" + strArray[5] + "." + strMilliseconds);

    }

    public void sleep(int milli){
        try {
            Thread.sleep(milli);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    /* Updated for 5.2 */
    public boolean findObjectByColumn(String columnName, String value){

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        /* Some column names need adjusting */
        if(columnName.equalsIgnoreCase("Country code")){
            columnName = "code";
        }

        List <WebElement> btnFirstPageList = webDriver.findElements(By.id("btn-pagination-first"));
        if(btnFirstPageList.size()>0) {
            try {
                btnFirstPage.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        do {

            hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath(".//table[contains(@class,'apm-data-table')]/tbody/tr"));
            for (WebElement row : rows) {
                WebElement cellId = row.findElement(By.className("col-" + columnName.toLowerCase()));
                if (value.equals(cellId.getText())) {
                    found = true;
                    break;
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
                    return false;
                }
            }

        }while (!found);

        return true;

    }

    /* Updated for 5.2 */
    public boolean clickEditByColumn(String columnName, String value){

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        /* Some column names need adjusting */
        if(columnName.equalsIgnoreCase("Country code")){
            columnName = "code";
        }

        List <WebElement> btnFirstPageList = webDriver.findElements(By.id("btn-pagination-first"));
        if(btnFirstPageList.size()>0) {
            try {
                btnFirstPage.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        do {

            hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath(".//table[contains(@class,'apm-data-table')]/tbody/tr"));
            for (WebElement row : rows) {
                WebElement cellId = row.findElement(By.className("col-" + columnName.toLowerCase()));
                if (value.equals(cellId.getText())) {
                    found = true;
                    row.findElement(By.xpath(".//td/div/span/img[contains(@src,'edit.svg')]")).click(); //Click the edit button
                    break;
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
                    return false;
                }
            }

        }while (!found);

        return true;

    }

    /* New for 5.2 */
    public boolean clickEditByUniqueId(String uniqueId){

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        myWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.className("duck-footer"))));

        List <WebElement> btnFirstPageList = webDriver.findElements(By.id("btn-pagination-first"));

        if(btnFirstPageList.size()>0) {
            try {
                btnFirstPage.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        do {

            hardWait(500);

            List<WebElement> editButtons = webDriver.findElements(By.id("col-edit" + uniqueId));
            if(editButtons.size()>0){

                try{
                    WebElement activeIcon = editButtons.get(0).findElement(By.xpath(".//span[@class='active-icon']"));
                    activeIcon.click();
                }
                catch(ElementClickInterceptedException ignored){}

                found = true;

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
                    return false;
                }
            }

        }while (!found);

        return true;

    }

    /* New for 5.2 */
    public boolean clickDeleteByUniqueId(String uniqueId){

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);

        myWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.className("duck-footer"))));

        List <WebElement> btnFirstPageList = webDriver.findElements(By.id("btn-pagination-first"));
        if(btnFirstPageList.size()>0) {
            try {
                btnFirstPage.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        do {

            hardWait(500);

            List<WebElement> delButtons = webDriver.findElements(By.id("col-delete" + uniqueId));
            if(delButtons.size()>0){

                WebElement activeIcon = delButtons.get(0).findElement(By.xpath(".//span[@class='active-icon']"));

                try{
                    activeIcon.click();
                }
                catch(ElementClickInterceptedException ignored){}

                found = true;

            }

            if (!found) {

                hardWait(500);
                thisPage = Integer.parseInt(txtCurrentPage.getAttribute("value"));
                lastPageString = txtPageCount.getText();
                pageArray = lastPageString.split(" ");
                lastItem = Integer.parseInt(pageArray[1]);

                if(thisPage < lastItem){
                    try {
                        btnNextPage.click();
                    }
                    catch(ElementClickInterceptedException ignored){}
                }
                else{
                    return false;
                }
            }

        }while (!found);


        return true;

    }

    /* Updated for 5.2 */
    public boolean clickDeleteByColumn(String columnName, String value){

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        /* Some column names need adjusting */
        if(columnName.equalsIgnoreCase("Country code")){
            columnName = "code";
        }

        List <WebElement> btnFirstPageList = webDriver.findElements(By.id("btn-pagination-first"));
        if(btnFirstPageList.size()>0) {
            try {
                btnFirstPage.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        do {

            hardWait(500);

            List<WebElement> rows = webDriver.findElements(By.xpath(".//table[contains(@class,'apm-data-table')]/tbody/tr"));
            for (WebElement row : rows) {
                WebElement cellId = row.findElement(By.className("col-" + columnName.toLowerCase()));
                if (value.equals(cellId.getText())) {
                    found = true;
                    row.findElement(By.xpath(".//td/div/span/img[contains(@src,'trash-can.svg')]")).click(); //Click the delete button
                    break;
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
                    return false;
                }
            }

        }while (!found);

        return true;

    }

    /* New for 5.2 */
    public boolean clickRunByUniqueId(String uniqueId){

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        List <WebElement> btnFirstPageList = webDriver.findElements(By.id("btn-pagination-first"));
        if(btnFirstPageList.size()>0) {
            try {
                btnFirstPage.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        do {

            hardWait(500);

            List<WebElement> editButtons = webDriver.findElements(By.id("col-exe" + uniqueId));

            if(editButtons.size()>0){
                found = true;
                List<WebElement> cellSpans1 = webDriver.findElements(By.xpath("//td[@id='col-exe" + uniqueId + "']/div/span"));
                if(cellSpans1.size()>0) {
                    webDriver.findElement(By.xpath("//td[@id='col-exe" + uniqueId + "']/div/span")).click();
                }
                List<WebElement> cellSpans2 = webDriver.findElements(By.xpath("//td[@id='col-exe" + uniqueId + "']/div/a/span"));
                if(cellSpans2.size()>0) {
                    webDriver.findElement(By.xpath("//td[@id='col-exe" + uniqueId + "']/div/a/span")).click();
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
                    return false;
                }
            }

        }while (!found);

        return true;

    }


    public void selectCheckbox(WebElement obj, String val){

        String cbxChecked = obj.getAttribute("checked");
        if(cbxChecked==null){
            cbxChecked = "false";
        }
        if(cbxChecked.equalsIgnoreCase("true") && val.equalsIgnoreCase("false")){
            obj.click();
        }
        if(cbxChecked.equalsIgnoreCase("false") && val.equalsIgnoreCase("true")){
            obj.click();
        }

    }

    public String getCheckboxValue(WebElement obj){

        String cbxChecked = obj.getAttribute("checked");
        if(cbxChecked==null){
            cbxChecked = "false";
        }
        return cbxChecked;

    }


    public void sortTableByColumn(String columnName, String columnDirection){

        String expUp;
        String expDown;

        WebElement sortSpan = webDriver.findElement(By.xpath("//table//span[contains(text(),'" + columnName + "')]"));
        WebElement chevronUp = sortSpan.findElement(By.xpath(".//img[contains(@src,'chevron-up.svg')]/.."));
        WebElement chevronDown = sortSpan.findElement(By.xpath(".//img[contains(@src,'chevron-down.svg')]/.."));

        if(columnDirection.equalsIgnoreCase("asc")){
            expUp = "visible";
            expDown = "hidden";
        }
        else{
            expUp = "hidden";
            expDown = "visible";
        }

        do{
            try {
                sortSpan.click();
            }catch(ElementClickInterceptedException ignored){}
            hardWait(500);

        }while((!chevronUp.getAttribute("className").equalsIgnoreCase(expUp)) && (!chevronDown.getAttribute("className").equalsIgnoreCase(expDown)));

    }

    public boolean checkScheduledTaskInTable(String taskName, String taskId, String status, String nextRun, String issuer){

        WebDriverWait myWait = new WebDriverWait(webDriver, 30);
        myWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("btn-add-key"))));

        int lastItem;
        int thisPage;
        String lastPageString;
        String[] pageArray;
        boolean found = false;

        List <WebElement> btnFirstPageList = webDriver.findElements(By.id("btn-pagination-first"));
        if(btnFirstPageList.size()>0) {
            try {
                btnFirstPage.click();
            }
            catch(ElementClickInterceptedException ignored){}
        }

        do {

            hardWait(500);

            List<WebElement> editButtons = webDriver.findElements(By.id("col-delete-" + taskId));

            if(editButtons.size()>0){
                found = true;

                if(!webDriver.findElement(By.id("col-name-" + taskId)).getAttribute("textContent").equalsIgnoreCase(taskName)){
                    System.out.println("col-name \nEXP: " + taskName + "\nACT: " +
                            webDriver.findElement(By.id("col-name-" + taskId)).getAttribute("textContent"));
                    return false;
                }

                if(!webDriver.findElement(By.id("col-status-" + taskId)).getAttribute("textContent").equalsIgnoreCase(status)){
                    System.out.println("col-status \nEXP: " + status + "\nACT: " +
                            webDriver.findElement(By.id("col-status-" + taskId)).getAttribute("textContent"));
                    return false;
                }

                if(!webDriver.findElement(By.id("col-lastRunTime-" + taskId)).getAttribute("textContent").equalsIgnoreCase(" ")){
                    System.out.println("col-lastRunTime \nEXP:\nACT: " +
                            webDriver.findElement(By.id("col-lastRunTime-" + taskId)).getAttribute("textContent"));
                    return false;
                }

                if(!webDriver.findElement(By.id("col-nextRunTime-" + taskId)).getAttribute("textContent").equalsIgnoreCase(nextRun)){
                    System.out.println("col-nextRunTime \nEXP: " + nextRun + "\nACT: " +
                            webDriver.findElement(By.id("col-nextRunTime-" + taskId)).getAttribute("textContent"));
                    return false;
                }

                // in case of ""
                if(issuer!=null) {
                    if(issuer.equalsIgnoreCase("")){
                        issuer = " ";
                    }
                    if (!webDriver.findElement(By.id("col-issuer-" + taskId)).getAttribute("textContent").equalsIgnoreCase(issuer)) {
                        System.out.println("col-issuer \nEXP: " + issuer + "\nACT: " +
                                webDriver.findElement(By.id("col-issuer-" + taskId)).getAttribute("textContent"));
                        return false;
                    }
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
                    return false;
                }
            }

        }while (!found);

        return true;
    }

    public boolean checkRunInDatabase(int lastRunIdBefore, int maxTimeout){

        long start = System.currentTimeMillis();
        long end = start + maxTimeout * 1000;
        boolean found = false;

        while (System.currentTimeMillis() < end) {
            if(getLastRunIdInDatabase()<=lastRunIdBefore){
                hardWait(1000);
            }
            if(getLastRunIdInDatabase()>lastRunIdBefore){
                found = true;
                break;
            }
        }

        return found;
    }

    public int getLastRunIdInDatabase(){
        return ExtractLogDataGatherer.getLastRunId();
    }



    public String validationTestcaseReporting(String field, String testcaseName, String expMessage, String actMessage){

        ValidationTestcases validationTestcases = new ValidationTestcases();
        Map<String, String> testCases = validationTestcases.getTestcases();
        StringBuilder msg = new StringBuilder();



        if (actMessage.equalsIgnoreCase(expMessage)) {
            msg.append("-------------------------------------------------------------------------------------------\n");
            msg.append(field);
            msg.append(" - Testcase ");
            msg.append(testcaseName);
            msg.append("\t\"");
            msg.append(testCases.get(testcaseName));
            msg.append("\": \tPASS");
            msg.append("\n-------------------------------------------------------------------------------------------\n");
            msg.append("EXP: ");
            msg.append(expMessage);
            msg.append("\n");
            msg.append("ACT: ");
            msg.append(actMessage);
            msg.append("\n-------------------------------------------------------------------------------------------\n");
        } else {
            msg.append("-------------------------------------------------------------------------------------------\n");
            msg.append(field);
            msg.append(" - Testcase ");
            msg.append(testcaseName);
            msg.append("\t\"");
            msg.append(testCases.get(testcaseName));
            msg.append("\": \tFAIL");
            msg.append("\n-------------------------------------------------------------------------------------------\n");
            msg.append("EXP: ");
            msg.append(expMessage);
            msg.append("\n");
            msg.append("ACT: ");
            msg.append(actMessage);
            msg.append("\n-------------------------------------------------------------------------------------------\n");
        }

        return msg.toString();
    }

}
