/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.lycheepo.pages;

import br.edu.utfpr.lycheepo.basepage.LycheeBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author caiot
 */
public class AlbumPage extends LycheeBasePage{
    /* Buttons */
    @FindBy(id = "button_trash_album")
    WebElement buttonDelete;
    
    /* Construtor */
    public AlbumPage(WebDriver driver) {
        super(driver);
    }
    
    /* Ações */
    public AlbumPage clicarEmDeletar(){
        buttonDelete.click();
        
        
        return this;
    }
    
    public AlbumsPage confirmarExclusao(){
        performAction();
        wait(1);
        return new AlbumsPage(driver, LOGADO);
    }
    
}
