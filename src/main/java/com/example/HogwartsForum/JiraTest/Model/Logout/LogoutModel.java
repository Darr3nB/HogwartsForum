package com.example.HogwartsForum.JiraTest.Model.Logout;

import com.example.HogwartsForum.JiraTest.Model.BaseModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutModel extends BaseModel {
    public LogoutModel() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "header-details-user-fullname")
    private WebElement profileIcon;
    @FindBy(id = "log_out")
    private WebElement logoutButton;
    @FindBy(xpath = "//*[@id='main']//p[@class = 'title']")
    private WebElement logoutMsg;

    public String getLogoutMsg(){
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main']//p[@class = 'title']")));
        return this.logoutMsg.getText();
    }

    public void logout(){
        profileIcon.click();
        logoutButton.click();
    }
}
