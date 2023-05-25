package PageObjects.PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PIMPage {
    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    WebElement employeeNameTextBox;

    @FindBy(xpath = "//div[@class='oxd-autocomplete-option']/span")
    List<WebElement> suggestedEmployeeNames;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "((//div[@class='oxd-table-card'])//div[@class='oxd-table-cell oxd-padding-cell']/div)")
    List<WebElement> allSearchResults;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> listRow;
    public PIMPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void enterPartialSearchText(String partialText){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(employeeNameTextBox));
        employeeNameTextBox.sendKeys(partialText);
    }

    public void checkSuggestedNameForPartialText(String partialText) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(suggestedEmployeeNames));
        for (WebElement name : suggestedEmployeeNames) {
            Assert.assertTrue(name.getText().toLowerCase().contains(partialText.toLowerCase()));
        }
    }

    public void clickSearchButton() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        Thread.sleep(3000);
    }

    public void verifyPartialTextInSearchedResult(String partialText){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(allSearchResults));
        int firstNameIndex = 1;
        int lastNameIndex =2;
        int nextNameValue = 7;
       List<String> validData = new ArrayList<>();
        for (WebElement row:allSearchResults) {
            if(row.getText() !=""){
                validData.add(row.getText());
            }
        }
        for(int i=0; i<listRow.size();i++){
            String actualDataToVerify = validData.get(firstNameIndex)+validData.get(lastNameIndex);
            firstNameIndex = firstNameIndex+nextNameValue;
            lastNameIndex = lastNameIndex+nextNameValue;
            Assert.assertTrue(actualDataToVerify.toLowerCase().contains(partialText.toLowerCase()));
        }
    }
}
