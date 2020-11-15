package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Credential {

    protected static WebDriver driver;
    private final JavascriptExecutor js;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTAB;

    @FindBy(id = "credentialAdd")
    private WebElement addCredentialBtn;

    @FindBy(id="credentialSave")
    private WebElement credentialSubmitBtn;

    @FindBy(name = "url")
    private WebElement credentialUrlText;

    @FindBy(name = "username")
    private WebElement credentialUsernameText;

    @FindBy(name = "password")
    private WebElement credentialPasswordText;

    @FindBy(id = "credentialEdit")
    private WebElement editCredentialBtn;

    @FindBy(id = "credentialDelete")
    private WebElement delCredentialBtn;

    @FindBy(linkText = "here")
    private WebElement returnLink;

    public Credential(WebDriver driver){
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void navCredentials(WebDriver driver) throws InterruptedException{

        this.js.executeScript("arguments[0].click()", credentialTAB);

    }

    public void createCredential(String url, String username, String password) throws InterruptedException {
        js.executeScript("arguments[0].click();",addCredentialBtn);
        js.executeScript("arguments[0].value='" + url + "';", credentialUrlText);
        js.executeScript("arguments[0].value='" + username + "';", credentialUsernameText);
        js.executeScript("arguments[0].value='" + password + "';", credentialPasswordText);
        js.executeScript("arguments[0].click();", credentialSubmitBtn);
        js.executeScript("arguments[0].click();", returnLink);
    }

    public void editCredential(String url, String username, String password) throws InterruptedException {
        js.executeScript("arguments[0].click();",addCredentialBtn);
        js.executeScript("arguments[0].value='" + url + "';", credentialUrlText);
        js.executeScript("arguments[0].value='" + username + "';", credentialUsernameText);
        js.executeScript("arguments[0].value='" + password + "';", credentialPasswordText);
        js.executeScript("arguments[0].click();", credentialSubmitBtn);
        js.executeScript("arguments[0].click();", returnLink);
    }

    public void deleteCredential() throws InterruptedException {
        js.executeScript("arguments[0].click();", delCredentialBtn);
        js.executeScript("arguments[0].click();", returnLink);
    }
}
