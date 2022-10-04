package JiraTest.Login;

import com.example.HogwartsForum.JiraTest.Model.Login.DashPageModel;
import com.example.HogwartsForum.JiraTest.Model.Login.LoginPageModel;
import com.example.HogwartsForum.JiraTest.Model.Login.ProfilePageModel;
import com.example.HogwartsForum.JiraTest.WebDriverService;
import com.example.HogwartsForum.Util.FileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private LoginPageModel loginPageModel;
    private ProfilePageModel profilePageModel;
    private DashPageModel dashPageModel;

    @BeforeEach
    public void setProperty() {
        loginPageModel = new LoginPageModel();
        profilePageModel = new ProfilePageModel();
        dashPageModel = new DashPageModel();
        loginPageModel.getLoginPage();
    }

    @AfterEach
    public void closeTab() {
        WebDriverService.getInstance().quitWebDriver();
    }

    @Test
    public void successfulLoginOnLoginPage() {
        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
        loginPageModel.waitUntilLoggedIn();

        loginPageModel.openProfilePageAndWaitForLoadIn();

        Assertions.assertTrue(profilePageModel.getFullName().contains(FileReader.getValueByKey("jira.displayname")));
    }

    @Test
    public void successfulLoginOnDashPage() {
        dashPageModel.openDashboardLoginPage();
        dashPageModel.waitUntilWebElementIsClickable("id", "login");
        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
        dashPageModel.waitUntilLoggedIn();
        profilePageModel.openProfilePage();

        Assertions.assertTrue(profilePageModel.getFullName().contains(FileReader.getValueByKey("jira.displayname")));
    }

    @Test
    public void loginWithInvalidUserName() {
        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.invalidLoginTry("whatever", FileReader.getValueByKey("jira.password"));
        loginPageModel.waitUntilErrorAppearsOnLoginPage();

        Assertions.assertTrue(loginPageModel.getErrorMsg().contains("Sorry, your username and password are incorrect - please try again."));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
    }

    @Test
    public void loginWithInvalidPassword() {
        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.invalidLoginTry(FileReader.getValueByKey("jira.username"), "whatever");
        loginPageModel.waitUntilErrorAppearsOnLoginPage();

        Assertions.assertTrue(loginPageModel.getErrorMsg().contains("Sorry, your username and password are incorrect - please try again."));

        loginPageModel.login(FileReader.getValueByKey("jira.username"), FileReader.getValueByKey("jira.password"));
    }
}
