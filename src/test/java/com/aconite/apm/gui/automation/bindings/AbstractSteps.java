package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.utilities.DatabaseConnection;
import com.aconite.apm.gui.automation.webpages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class AbstractSteps {

    private static boolean initialized = false;
    private static WebDriver driver;
    private String baseUrl;
    private String username;
    private String password;
    private String testType;
    public static Scenario myScenario;

    Properties properties;

    @BeforeAll
    public static void beforeAll() {

        /* Create run-time log and set out */
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);
        PrintStream outStream = null;
        try {
            outStream = new PrintStream("outFile_" + strDate + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(outStream);

    }

    @Before ()
    public void setUp(Scenario myScenario) {

        AbstractSteps.myScenario = myScenario;

        System.out.println("*****************************************************************");
        System.out.println("Scenario: " + myScenario.getName());
        System.out.println("*****************************************************************\n");

        if (!initialized)
        {

            // initialize the driver
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--ignore-ssl-errors");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--allow-insecure-localhost");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(chromeOptions);

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            initialized = true;

        }

        properties = new Properties();

        try
        {
            properties.load(DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException iox)
        {
            iox.printStackTrace();
        }


        baseUrl = properties.getProperty("apm_url");
        username = properties.getProperty("apm_username");
        password = properties.getProperty("apm_password");

    }

    @Given("I set test type \"([^\"]*)\"$")
    public void setTestType(String testType){
        this.testType = testType;
    }

    public String getTestType()
    {
        return this.testType;
    }

    public String getDataPath(){

        String testPath = "";

        if(getTestType().equals("Admin")){
            testPath = "src/test/resources/TestData/Functional";
        }
        if(getTestType().equals("Scheduler")){
            testPath = "src/test/resources/TestData/Functional";
        }
        if(getTestType().equals("Validation")){
            testPath = "src/test/resources/TestData/Validation";
        }

        return testPath;
    }

    public WebDriver getDriver()
    {
         return driver;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    @Given("I am logged in")
    public void i_am_logged_in() {

        try {

            LoginPage loginPage = new LoginPage(getBaseUrl(), driver);
            loginPage.login(getUsername(), getPassword());

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Given("I am on the Home Page")
    public void i_am_on_the_home_page() {

        try {

            LoginPage loginPage = new LoginPage(getBaseUrl(), driver);
            loginPage.goToHomePage();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Then("I take a screenshot \"([^\"]*)\"$")
    public static void i_take_a_screenshot(String imageName){
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        AbstractSteps.myScenario.attach(screenshot, "image/png", imageName);
    }

    @After()
    public void tearDown()
    {
        driver.quit();
        initialized = false;
    }

}