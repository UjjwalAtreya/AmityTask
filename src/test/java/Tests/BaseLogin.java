package Tests;

import PageObjects.LoginPage.LoginPage;

import static Utils.Helper.*;

public class BaseLogin {
    public void loginFlow() throws InterruptedException {
        LoginPage loginPageObj = new LoginPage(getDriver());
        loginPageObj.enterUserName(properties.getProperty("username"));
        loginPageObj.enterPassword(properties.getProperty("password"));
        loginPageObj.clickSubmitButton();
        Thread.sleep(3000);
    }
}
