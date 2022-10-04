package com.example.HogwartsForum.JiraTest.Model.EditIssue;

import com.example.HogwartsForum.JiraTest.Model.BaseModel;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditIssueModel extends BaseModel {

    public EditIssueModel() {
        super();
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "key-val")
    WebElement issueID;

    @FindBy(id="edit-issue")
    WebElement editBtn;

    @FindBy(xpath = "//*[@id='edit-issue-dialog']//h2")
    WebElement editModalTitle;

    @FindBy(id = "summary")
    WebElement modalSummaryField;

    @FindBy(id = "edit-issue-submit")
    WebElement updateBtn;

    @FindBy(id="summary-val")
    WebElement summaryTitle;

    @FindBy(xpath = "//*[@id='aui-flag-container']//button")
    WebElement closeModalBtn;

    public String getIssueID(){
        return issueID.getText();
    }

    public void clickEditBtn(){
        issueID.click();
    }

    public String getEditModelTitle(){
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit-issue-dialog']//h2")));
        return editModalTitle.getText();
    }

    public void getEditBtn() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        editBtn.click();
    }

    public void setModalSummaryField(String strSummary){
        modalSummaryField.clear();
        modalSummaryField.sendKeys(strSummary);
    }

    public void clickUpdateBtn(){
        updateBtn.click();
    }

    public String getSummaryTitle() {
        return summaryTitle.getText();
    }

    public void clickModalBtn(){
        closeModalBtn.click();
    }

    public void waitForModal()
    {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit-issue-dialog']//h2")));
    }
    public String checkSummaryTitle()
    {
        try {
            WebDriverWait shortWait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("summary-val")));
        }
        catch (TimeoutException ignore){}
        finally {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary-val")));
        }
        webDriver.navigate().refresh();
        return getSummaryTitle();
    }

    public boolean checkEditButton()
    {
        try {
            WebDriverWait shortWait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }
}
