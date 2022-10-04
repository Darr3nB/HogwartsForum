package JiraTest.Logout;

import com.example.HogwartsForum.JiraTest.Model.Logout.LogoutModel;
import com.example.HogwartsForum.JiraTest.WebDriverService;
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
