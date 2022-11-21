package JiraTest.Model.Login;

import JiraTest.Model.BaseModel;
import com.example.HogwartsForum.util.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePageModel extends BaseModel {
    public ProfilePageModel() {
        PageFactory.initElements(webDriver, this);
    }

    public void openProfilePage()
    {
        webDriver.get(FileReader.getValueByKeyFromConfigProperties("jira.baseurl") + "/secure/ViewProfile.jspa");
        webDriver.manage().window().maximize();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("up-d-fullname")));
    }

    @FindBy(id = "up-d-fullname")
    private WebElement fullName;

    public String getFullName(){
        return fullName.getText();
    }
}
