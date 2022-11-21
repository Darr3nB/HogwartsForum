package JiraTest;

import JiraTest.Model.BrowseIssue.BrowseIssueModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class BrowseIssueTest {
    private BrowseIssueModel browseIssueModel;

    @BeforeEach
    public void openNewTab() {
        browseIssueModel = new BrowseIssueModel();
        browseIssueModel.doLogin();
    }

    @AfterEach
    public void closeWebDriver() {
        WebDriverService.getInstance().quitWebDriver();
    }



    @Test
    public void browseExistingIssue() {
        browseIssueModel.openUrlWithSpecificPathAndMaximizeWindowSize("/browse/MTP-2253");
        Assertions.assertEquals("MTP-2253", browseIssueModel.getIssueId());
    }

    @Test
    public void checkPossibilityOfBrowsing() {
        String expectedKey = "MTP-2245";
        browseIssueModel.openUrlWithSpecificPathAndMaximizeWindowSize("/issues/?jql=");
        browseIssueModel.clickSearchField();
        browseIssueModel.writeSearchField("Jira Test Project");
        browseIssueModel.clickSearchButton();
        Assertions.assertTrue(browseIssueModel.waitUntilKeyIsVisible(expectedKey));
    }

    @Test
    public void browseNonExistingIssue() {
        browseIssueModel.openUrlWithSpecificPathAndMaximizeWindowSize("/browse/MTP-99999999999");
        Assertions.assertEquals("You can't view this issue", browseIssueModel.getErrorMessageField());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issueIds.csv")
    public void browseIssueWithSpecificId(String issueId) {
        browseIssueModel.openUrlWithSpecificPathAndMaximizeWindowSize(String.format("/browse/%s", issueId));
        Assertions.assertDoesNotThrow(() -> browseIssueModel.getIssueId());
        Assertions.assertEquals(issueId, browseIssueModel.getIssueId());
    }
}
