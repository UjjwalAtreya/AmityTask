package PageObjects.Admin;

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

public class AdminPage {

    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath = "//nav[@aria-label='Topbar Menu']/ul/li")
    List<WebElement> topBarMenus;
    @FindBy(xpath = "//ul[@class='oxd-dropdown-menu']/li/a")
    List<WebElement> jobTypes;

    @FindBy(xpath = "//div[@class='orangehrm-header-container']//button[@type='button']")
    WebElement addButton;

    @FindBy(xpath = "//div[@class='oxd-form-row']//input")
    WebElement nameTextField;

    @FindBy(xpath = "//div[@class='oxd-form-actions']//button[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//div[@class='orangehrm-header-container']//button")
    WebElement addCurrencyButton;


    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement clickSelectCurrencyDropDown;
    @FindBy(xpath = "//div[@role='option']/span")
    List<WebElement> currencyDropDownOptions;

    @FindBy(xpath = "((//div[@class='oxd-form-row'])[3]//input)[1]")
    WebElement minimumSalaryTextBox;

    @FindBy(xpath = "((//div[@class='oxd-form-row'])[3]//input)[2]")
    WebElement maximumSalaryTextBox;

    @FindBy(xpath = "(//div[@class='oxd-form-actions']//button[@type='submit'])[2]")
    WebElement currencySaveButton;

    @FindBy(xpath = "//div[@role='cell']")
    List<WebElement> savedRecords;

    @FindBy (xpath = "(//div[@role='cell'])[2]")
    WebElement savedCurrencyName;

    @FindBy (xpath = "(//div[@role='cell'])[3]")
    WebElement savedMinimumSalary;

    @FindBy (xpath = "(//div[@role='cell'])[4]")
    WebElement savedMaximumSalary;

    public AdminPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickTopBarMenu(String menuName){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(topBarMenus));
        for (WebElement menu:topBarMenus) {
            if(menu.getText().equalsIgnoreCase(menuName)){
                menu.click();
                break;
            }
        }
    }

    public void clickJobType(String jType){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(jobTypes));
        for (WebElement jobType:jobTypes) {
            if(jobType.getText().equalsIgnoreCase(jType)){
                jobType.click();
                break;
            }
        }
    }

    public void clickAddButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(addButton));
        addButton.click();
    }

    public void enterNameForPayGrade(String name){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(nameTextField));
        nameTextField.sendKeys(name+Math.random());
    }

    public void clickSavePayGradeButton(){
        saveButton.click();
    }

    public void clickAddInCurrencySection() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(addCurrencyButton));
        addCurrencyButton.click();
        Thread.sleep(2000);
    }

    public void setClickSelectCurrencyDropDown(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(clickSelectCurrencyDropDown));
        clickSelectCurrencyDropDown.click();
    }

    public void selectFromCurrenciesDropDown(String curr){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(currencyDropDownOptions));
        for (WebElement currency:currencyDropDownOptions) {
            if(currency.getText().equalsIgnoreCase(curr)){
                currency.click();
                break;
            }
        }
    }

    public void enterMinimumSalary(String minSal){
        minimumSalaryTextBox.sendKeys(minSal);
    }
    public void enterMaximumSalary(String maxSal){
        maximumSalaryTextBox.sendKeys(maxSal);
    }

    public void clickSaveButton(){
        currencySaveButton.click();
    }

    public List<String> verifySavedRecords(String[] expectedResult){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(savedRecords));
       List<String> arrList = new ArrayList<String>();
        for (WebElement record:savedRecords){

            arrList.add(record.getText());
            //Assert.assertEquals(record.getText(), expectedResult[index], "");
        }
        return arrList;
    }
}
