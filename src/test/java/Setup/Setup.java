package Setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static Utils.Helper.*;

public class Setup {

    protected static String browser;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"configFile"})
    public void readConfigFile(String configFile)  {
        try{
            readConfig(configFile);
        }catch (Exception err){
            System.out.println(err.fillInStackTrace());
        }
    }


    @BeforeMethod
    @Parameters({"browserName"})
    public void getBrowser(String browserName){
        browser = browserName;
    }

    @AfterMethod
    public void tearDown(){
            getDriver().quit();
    }

}
