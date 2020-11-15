package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Note {
    protected static WebDriver driver;
    private final JavascriptExecutor js;

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;
    //----------------

    @FindBy(id = "noteSubmit" )
    private WebElement saveChangesBtn;

    @FindBy(id = "note-title")
    private WebElement noteTitleText;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionText;


    @FindBy(id = "noteAdd")
    private WebElement addNoteBtn;

    @FindBy(id = "noteEdit")
    private WebElement editNoteBtn;

    @FindBy(id = "noteDelete")
    private WebElement delNoteBtn;

    @FindBy(id = "here")
    private WebElement returnLink;
    private JavascriptExecutor jse;

    public Note(WebDriver driver){
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void navNotes(WebDriver driver) throws InterruptedException{
        this.js.executeScript("arguments[0].click()", notesTab);

    }

    public void createNote(String title, String description)throws InterruptedException{
        js.executeScript("arguments[0].click();", addNoteBtn);
        js.executeScript("arguments[0].value='" + title + "';", noteTitleText);
        js.executeScript("arguments[0].value='" + description + "';", noteDescriptionText);
        js.executeScript("arguments[0].click();", saveChangesBtn);
        js.executeScript("arguments[0].click();", returnLink);
    }

    public void editNote(String title, String description) throws InterruptedException {
        js.executeScript("arguments[0].click();", editNoteBtn);
        js.executeScript("arguments[0].value='" + title + "';", noteTitleText);
        js.executeScript("arguments[0].value='" + description + "';", noteDescriptionText);
        js.executeScript("arguments[0].click();", saveChangesBtn);
        js.executeScript("arguments[0].click();", returnLink);
    }

    public void deleteNote() throws InterruptedException {
        js.executeScript("arguments[0].click();", delNoteBtn);
        js.executeScript("arguments[0].click();", returnLink);
    }
}
