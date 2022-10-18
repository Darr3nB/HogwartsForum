package JiraTest;

import com.example.HogwartsForum.JiraTest.Model.EditIssue.EditIssueModel;
import com.example.HogwartsForum.JiraTest.WebDriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class EditIssueTest {
    private EditIssueModel editIssueModel;


    @BeforeEach
    public void openNewTab() {
        editIssueModel = new EditIssueModel();
        editIssueModel.doLogin();
    }

    @AfterEach
    public void closeWebDriver() {
        WebDriverService.getInstance().quitWebDriver();
    }



    @Test
    public void successfulEditIssue(){
        editIssueModel.openUrlWithSpecificPathAndMaximizeWindowSize("/browse/MTP-2245");

        Assertions.assertTrue(editIssueModel.getIssueID().contains("MTP-2245"));

        editIssueModel.getEditBtn();

        editIssueModel.waitForModal();
        Assertions.assertTrue(editIssueModel.getEditModelTitle().contains("Edit Issue : MTP-2245"));

        editIssueModel.setModalSummaryField("Allopenissues");

        editIssueModel.clickUpdateBtn();
        String actualSummary = editIssueModel.checkSummaryTitle();

        Assertions.assertEquals("Allopenissues", actualSummary);

        editIssueModel.getEditBtn();

        editIssueModel.waitForModal();
        editIssueModel.setModalSummaryField("Jira Test Project");

        editIssueModel.clickUpdateBtn();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issueIds.csv")
    public void editIssueWithSpecificId(String issueId){
        editIssueModel.openUrlWithSpecificPathAndMaximizeWindowSize(String.format("/browse/%s", issueId));
        Assertions.assertTrue(editIssueModel.checkEditButton());
    }
}
