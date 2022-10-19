package JiraTest.Model.Login;


import JiraTest.Model.BaseModel;
import com.example.HogwartsForum.Util.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPageModel extends BaseModel {
    @FindBy(className = "aui-page-header-main")
    private WebElement title;
    @FindBy(xpath = "//*[@id='login-form']//p")
    private WebElement invalidLoginMsg;


    public String getErrorMsg(){
        return invalidLoginMsg.getText();
    }

    public String getTitle(){
        return title.getText();
    }

    public void login(String username, String password){
        setUsername(username);
        setPassword(password);
        clickOnLoginButton();
    }

    public void getLoginPage()
    {
        webDriver.get(FileReader.getValueByKeyFromConfigProperties("jira.baseurl") + "/login.jsp?os_destination=%2Fsecure%2FTests.jspa#/design?projectId=10101");
        webDriver.manage().window().maximize();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-submit")));
    }

    public void waitUntilErrorAppearsOnLoginPage()
    {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login-form']//p")));
    }

    public void openProfilePageAndWaitForLoadIn(){
        openUrlWithSpecificPathAndMaximizeWindowSize("/secure/ViewProfile.jspa");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("up-d-fullname")));
    }

    public void invalidLoginTry(String username, String password){
        login(username, password);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login-form']//p")));
    }
}
