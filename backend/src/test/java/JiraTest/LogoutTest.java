package JiraTest;

import JiraTest.Model.Logout.LogoutModel;
import org.junit.jupiter.api.*;

public class LogoutTest {
    private static LogoutModel logoutModel;

    @BeforeAll
    public static void beforeAll(){
        logoutModel = new LogoutModel();
    }

    @BeforeEach
    public void beforeEach(){
        logoutModel.doLogin();
    }

    @AfterEach
    public void closeWebDriver(){
        WebDriverService.getInstance().quitWebDriver();
    }


    @Test
    public void successfulLogout(){
        logoutModel.waitUntilLoggedIn();
        logoutModel.logout();

        Assertions.assertTrue(logoutModel.getLogoutMsg().contains("You are now logged out. Any automatic login has also been stopped."));
    }
}
