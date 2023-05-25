package Tests.PIMPageTest;

import PageObjects.CommonPO;
import PageObjects.PIM.PIMPage;
import Setup.Setup;
import Tests.BaseLogin;
import org.testng.annotations.Test;

import static Utils.Helper.getDriver;
import static Utils.Helper.properties;

public class PIMPageTest extends Setup {

    @Test
    public void testToVerifySearchWithPartialText() throws InterruptedException {
        BaseLogin baseLogin = new BaseLogin();
        PIMPage pimPage =new PIMPage(getDriver());
        CommonPO commonPO = new CommonPO(getDriver());
        baseLogin.loginFlow();
        commonPO.clickMenuOption(properties.getProperty("menuPIM"));
        pimPage.enterPartialSearchText(properties.getProperty("partialText"));
        pimPage.checkSuggestedNameForPartialText(properties.getProperty("partialText"));
        pimPage.clickSearchButton();
        pimPage.verifyPartialTextInSearchedResult(properties.getProperty("partialText"));

    }
}
