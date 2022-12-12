package JiraTest;

import JiraTest.Model.Login.DashPageModel;
import JiraTest.Model.Login.LoginPageModel;
import JiraTest.Model.Login.ProfilePageModel;
import com.HogwartsForum.util.Utility;
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

        loginPageModel.login(Utility.getValueByKeyFromConfigProperties("jira.username"), Utility.getValueByKeyFromConfigProperties("jira.password"));
        loginPageModel.waitUntilLoggedIn();

        loginPageModel.openProfilePageAndWaitForLoadIn();

        Assertions.assertTrue(profilePageModel.getFullName().contains(Utility.getValueByKeyFromConfigProperties("jira.displayname")));
    }

    @Test
    public void successfulLoginOnDashPage() {
        dashPageModel.openDashboardLoginPage();
        dashPageModel.waitUntilWebElementIsClickable("id", "login");
        Assertions.assertTrue(dashPageModel.getDashPageTitle().contains("System Dashboard"));

        dashPageModel.loginOnDashPage(Utility.getValueByKeyFromConfigProperties("jira.username"), Utility.getValueByKeyFromConfigProperties("jira.password"));
        dashPageModel.waitUntilLoggedIn();
        profilePageModel.openProfilePage();

        Assertions.assertTrue(profilePageModel.getFullName().contains(Utility.getValueByKeyFromConfigProperties("jira.displayname")));
    }

    @Test
    public void loginWithInvalidUserName() {
        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.invalidLoginTry("whatever", Utility.getValueByKeyFromConfigProperties("jira.password"));
        loginPageModel.waitUntilErrorAppearsOnLoginPage();

        Assertions.assertTrue(loginPageModel.getErrorMsg().contains("Sorry, your username and password are incorrect - please try again."));

        loginPageModel.login(Utility.getValueByKeyFromConfigProperties("jira.username"), Utility.getValueByKeyFromConfigProperties("jira.password"));
    }

    @Test
    public void loginWithInvalidPassword() {
        Assertions.assertTrue(loginPageModel.getTitle().contains("Welcome to Jira Auto"));

        loginPageModel.invalidLoginTry(Utility.getValueByKeyFromConfigProperties("jira.username"), "whatever");
        loginPageModel.waitUntilErrorAppearsOnLoginPage();

        Assertions.assertTrue(loginPageModel.getErrorMsg().contains("Sorry, your username and password are incorrect - please try again."));

        loginPageModel.login(Utility.getValueByKeyFromConfigProperties("jira.username"), Utility.getValueByKeyFromConfigProperties("jira.password"));
    }
}
