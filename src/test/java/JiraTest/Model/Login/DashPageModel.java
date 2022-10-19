package JiraTest.Model.Login;

import JiraTest.Model.BaseModel;
import com.example.HogwartsForum.Util.FileReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashPageModel extends BaseModel {
    public DashPageModel() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "login")
    private WebElement loginButtonOnDash;
    @FindBy(xpath = "//*[@id='dashboard-content']//div[@class='aui-page-header-main']/h1")
    private WebElement dashPageTitle;

    public String getDashPageTitle(){
        return dashPageTitle.getText();
    }

    private void clickLoginButtonOnDash(){
        this.loginButtonOnDash.click();
    }

    public void openDashboardLoginPage()
    {
        webDriver.get(FileReader.getValueByKeyFromConfigProperties("jira.baseurl") + "/secure/Dashboard.jspa");
    }

    public void loginOnDashPage(String username, String password){
        setUsername(username);
        setPassword(password);
        clickLoginButtonOnDash();
    }
}
