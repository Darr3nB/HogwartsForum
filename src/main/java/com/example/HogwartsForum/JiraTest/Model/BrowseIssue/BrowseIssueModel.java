package com.example.HogwartsForum.JiraTest.Model.BrowseIssue;

import com.example.HogwartsForum.JiraTest.Model.BaseModel;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowseIssueModel extends BaseModel {
    private final WebDriverWait driverWait;

    public BrowseIssueModel() {
        super();
        PageFactory.initElements(webDriver, this);
        this.driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    @FindBy(id = "key-val")
    private WebElement issueId;

    @FindBy(id = "searcher-query")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='main']//form//button[text() = 'Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='issue-content']//h1")
    private WebElement errorMessageField;

    @FindBy(id = "type-val")
    private WebElement typeValueField;
    @FindBy(id = "summary-val")
    private WebElement summaryValueField;
    @FindBy(id = "key-val")
    private WebElement keyValField;

    @FindBy(id = "opsbar-operations_more")
    private WebElement moreButton;

    @FindBy(id = "delete-issue")
    private WebElement deleteButton;

    @FindBy(id = "delete-issue-submit")
    private WebElement modalDeleteConfirm;

    public String getIssueId() {
        return issueId.getText();
    }


    public void clickSearchField()
    {
        searchField.click();
    }
    public void writeSearchField(String searchPhrase)
    {
        searchField.sendKeys(searchPhrase);
    }

    public void clickSearchButton()
    {
        searchButton.click();
    }

    public String getErrorMessageField() {
        return errorMessageField.getText();
    }

    public void deleteIssue()
    {
        moreButton.click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-issue")));
        deleteButton.click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id("delete-issue-submit")));
        modalDeleteConfirm.click();
    }
    public boolean waitUntilKeyIsVisible(String key)
    {
        try {
            WebDriverWait shortWait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[@id = 'key-val' and text() =  '%s']",key))));
        }
        catch (TimeoutException e) {return false;}
        return true;
    }

    public String getIssueType()
    {
        return typeValueField.getText();
    }

    public String getSummary()
    {
        return summaryValueField.getText();
    }

    public String getProjectKey()
    {
        return keyValField.getText();
    }
}
