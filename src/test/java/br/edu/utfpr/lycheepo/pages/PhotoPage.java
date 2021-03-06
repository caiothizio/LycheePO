/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.lycheepo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author caiot
 */
public class PhotoPage extends LycheeBasePage{
    
    /* Buttons */
    @FindBy(id = "button_star")
    WebElement buttonStar;
    
    @FindBy(id = "button_back")
    WebElement buttonBack;
    
    @FindBy(id = "button_trash")
    WebElement buttonDelete;
        
    public PhotoPage(WebDriver driver) {
        super(driver);
    }
    
    public PhotoPage clicarNaEstrela(){
        buttonStar.click();
        wait(1);
        
        return this;
    }
    
    public PhotoPage clicarEmApagar(){
        buttonDelete.click();
        wait(1);
        
        return this;
    }
    
    public AlbumPage clicarEmVoltar(){
        buttonBack.click();
        wait(1);
        
        return new AlbumPage(driver);
    }
    
    public AlbumPage clicarEmDeletePhoto(){
        performAction();
        
        return new AlbumPage(driver);
    }
    
    public boolean favoritou(){
        return buttonStar.getAttribute("class").contains("active");
    }
    
    public boolean desfavoritou(){
        return !favoritou();
    }
}
