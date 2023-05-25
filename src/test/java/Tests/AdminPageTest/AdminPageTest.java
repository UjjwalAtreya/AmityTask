package Tests.AdminPageTest;

import PageObjects.Admin.AdminPage;
import PageObjects.CommonPO;
import Setup.Setup;
import Tests.BaseLogin;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static Utils.Helper.getDriver;
import static Utils.Helper.properties;

public class AdminPageTest extends Setup {

    @Test
    public void testToVerifyIfUserCanAddPayGrades() throws InterruptedException {
        String[] expectedResult = {"",properties.getProperty("nameForPayGrade"),properties.getProperty("minimumSalary"),properties.getProperty("maximumSalary"),""};
        BaseLogin baseLogin = new BaseLogin();
        AdminPage adminPage = new AdminPage(getDriver());
        CommonPO commonPO = new CommonPO(getDriver());
        baseLogin.loginFlow();
        commonPO.clickMenuOption(properties.getProperty("menuAdmin"));
        adminPage.clickTopBarMenu(properties.getProperty("topBarMenuJob"));
        adminPage.clickJobType(properties.getProperty("jobMenu_Pay_grades"));

        adminPage.clickAddButton();
        adminPage.enterNameForPayGrade(properties.getProperty("nameForPayGrade"));
        adminPage.clickSavePayGradeButton();
        adminPage.clickAddInCurrencySection();
        adminPage.setClickSelectCurrencyDropDown();
        adminPage.selectFromCurrenciesDropDown(properties.getProperty("currency"));
        adminPage.enterMinimumSalary(properties.getProperty("minimumSalary"));
        adminPage.enterMaximumSalary(properties.getProperty("maximumSalary"));
        adminPage.clickSaveButton();
        List<String> list = adminPage.verifySavedRecords(expectedResult);
        for(int i = 0;i<list.size();i++){
            if(i >1 && i<=4){
                Assert.assertEquals(list.get(i).split("\\.")[0], expectedResult[i]);
                continue;
            }
           Assert.assertEquals(list.get(i), expectedResult[i]);
        }
    }
}
