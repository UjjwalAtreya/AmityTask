package Utils;

import com.google.common.base.Strings;
import Setup.Setup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


public class Helper extends Setup {


    public static Properties properties;
    static {
        properties = new Properties();
    }
    public static String testDataFolderPath ="/src/test/java/config/";

    public static void readConfig(String configFileName) throws IOException {
        try {
            if (configFileName == null || Strings.isNullOrEmpty(configFileName)) {
                configFileName = "test-data.xml";
            }
            String currentWorkingDir = System.getProperty("user.dir");
            File file = new File(currentWorkingDir+testDataFolderPath + configFileName);

            if (!file.exists()) {
                configFileName = "test-data.xml";
                file = new File(testDataFolderPath + configFileName);
            }
            FileInputStream fileInput = new FileInputStream(file);
            properties.loadFromXML(fileInput);

            fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver createInstance(){
        WebDriver driver = null;
        try {
            if(browser.equalsIgnoreCase("chrome")){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver= new ChromeDriver(options);
                driver.manage().window().maximize();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                driver.manage().window().maximize();
            } else if (browser.equalsIgnoreCase("safari")) {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                driver.manage().window().maximize();
            }
            driver.get(properties.getProperty("BaseUrl"));
            Thread.sleep(5000);
            return driver;
        } catch (Exception errorCreateInstance) {
            System.out.println("Cannot create Instance due to :" + errorCreateInstance);
        }
        return driver;
    }

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}
