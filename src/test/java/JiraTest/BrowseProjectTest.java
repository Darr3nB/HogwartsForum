package JiraTest;

import com.example.HogwartsForum.JiraTest.Model.BrowseProject.BrowseProjectModel;
import com.example.HogwartsForum.JiraTest.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BrowseProjectTest {
    private BrowseProjectModel browseProjectModel;

    @BeforeEach
    public void openNewTab() {
        browseProjectModel = new BrowseProjectModel();
        browseProjectModel.doLogin();
    }

    @AfterEach
    public void closeWebDriver() {
        WebDriverService.getInstance().quitWebDriver();
    }


    @ParameterizedTest
    @ValueSource(strings = {"MTP", "JETI", "TOUCAN", "COALA"})
    public void browseProject(String projectType) {
        browseProjectModel.openUrlWithSpecificPathAndMaximizeWindowSize(String.format("/projects/%s/summary", projectType));

        Assertions.assertTrue(browseProjectModel.getProjectKey().contains(projectType));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DUMMYDATA"})
    public void browseNonExistingProject(String projectType) {
        browseProjectModel.openUrlWithSpecificPathAndMaximizeWindowSize(String.format("/projects/%s/summary", projectType));

        Assertions.assertTrue(browseProjectModel.getErrorMessage().contains("You can't view this project"));
    }
}
