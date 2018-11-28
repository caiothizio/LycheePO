/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.lycheepo.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author caiot
 */
public class LycheeBasePage extends BasePage{
    
    /*Constantes*/
    public static final boolean LOGADO = true;
    public static final boolean DESLOGADO = false;
    
    @FindBy(xpath = "/html/body/header/div[3]/a[2]")
    WebElement headerTitle;
    
    @FindBy(id = "basicModal__action")
    WebElement buttonAction;
    
    @FindBy(id = "basicModal__cancel")
    WebElement buttonCancel;
    
    public LycheeBasePage(WebDriver driver) {
        super(driver);
    }
    
    public String verTitulo(){
        return headerTitle.getText();
    }
    
    public void performAction(){
        wait(1);
        buttonAction.click();
        
    }
    
    public void cancelAction(){
        buttonCancel.click();
    }
    
    public void waitForElementVisibility(int time, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until( ExpectedConditions.visibilityOf(element) );
    }
    
    public void wait(int timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LycheeBasePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
