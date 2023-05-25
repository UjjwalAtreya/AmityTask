package Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import static Utils.Helper.createInstance;
import static Utils.Helper.setWebDriver;


public class WebDriverListener implements IInvokedMethodListener {
    WebDriver driver = null;
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            driver = createInstance();
            setWebDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        //do something after Invocation

    }
}

