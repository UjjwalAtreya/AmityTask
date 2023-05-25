package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CommonPO {

    public WebDriver driver;

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li")
    public List<WebElement> menuoptions;
    public CommonPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickMenuOption(String menu){
        for (WebElement option:menuoptions) {
            System.out.println(option.getText());
            if(option.getText().equalsIgnoreCase(menu)){
                option.click();
                break;
            }
        }
    }
}
