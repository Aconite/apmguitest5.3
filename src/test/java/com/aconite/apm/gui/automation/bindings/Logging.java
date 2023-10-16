package com.aconite.apm.gui.automation.bindings;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import javax.xml.bind.SchemaOutputResolver;


public class Logging {

    public WebDriver webDriver;
    public SoftAssert softAssert = new SoftAssert();

    public Logging(AbstractSteps AbstractSteps)
    {
        webDriver = AbstractSteps.getDriver();
    }

    public static void passMessage(String msg){

        AbstractSteps.myScenario.log("<font color=\"green\"><b>" + msg + "</b></font>");
        System.out.println("STEP PASS: " + msg);

    }

    public static void failMessage(String msg){

        AbstractSteps.myScenario.log("<font color=\"red\"><b>" + msg + "</b></font>");
        System.out.println("STEP FAIL: " + msg);

    }

    public static void warnMessage(String msg){

        AbstractSteps.myScenario.log("<font color=\"chocolate\"><b>" + msg + "</b></font>");
        System.out.println("STEP WARN: " + msg);

    }

    public static void stepName(String msg){

        AbstractSteps.myScenario.log("=====================================================================================<p>   " +
                msg +
                "<p>   ======================================================================================");
        System.out.println("\nSTEP NAME: " + msg);

    }

    public static void dataMsg(String msg){

        AbstractSteps.myScenario.log( msg );
        System.out.println("STEP DATA: " + msg);

    }




}
