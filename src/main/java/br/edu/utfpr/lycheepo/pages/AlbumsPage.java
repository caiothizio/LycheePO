/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.lycheepo.pages;

import br.edu.utfpr.lycheepo.basepage.LycheeBasePage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author caiot
 */
public class AlbumsPage extends LycheeBasePage{
    /* Flags */
    boolean logado;
    
    /* Buttons */
    @FindBy(id = "button_signin")
    WebElement loginButton;
    
    @FindBy(xpath = "/html/body/header/div[2]/a[5]")
    WebElement buttonAdd;
    
    /* Albums */
    @FindBy(xpath = "/html/body/div[2]/div[7]")
    WebElement firstAlbum;
    
    /* Menu */
    @FindBy(className = "basicContextContainer")
    WebElement divMenu;
    
    @FindBy(xpath="/html/body/div[6]/div/table/tbody/tr[1]/td")
    WebElement uploadPhoto;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[3]/td")
    WebElement importFromLink;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[7]/td")
    WebElement newAlbum;
    
    
    /* Inputs */
    @FindBy(name = "username")
    WebElement inputUsername;
    
    @FindBy(name = "password")
    WebElement inputPassword;
    
    @FindBy(xpath = "/html/body/div[6]/div/div[1]/p/input")
    WebElement inputAlbumName;
    
    @FindBy(id = "upload_files")
    WebElement inputImg;
    
    /*=================================*/
    
    /* Construtor */
    public AlbumsPage(WebDriver driver, boolean estaLogado) {
        super(driver);
        logado = estaLogado;
    }
    
    /* Ações */
    public AlbumsPage clicarNoBotaoDeLogin(){
        loginButton.click();
        
        return this;
    }
    
    public AlbumsPage preencherLoginCom(String user, String senha){
        inputUsername.sendKeys(user);
        inputPassword.sendKeys(senha);
        
        return this;
    }
    
    public AlbumsPage clicarEmSignIn(){
        performAction();
        
        if(inputPassword.getAttribute("class").contains("error"))
            return this;
        else
            return new AlbumsPage(driver, LycheeBasePage.LOGADO);
    }
    
    public AlbumsPage clicarEmAdd(){
        buttonAdd.click();
        
        waitForElementVisibility(3, divMenu);
        
        return this;
    }
    
    public AlbumsPage clicarEmNewAlbum(){
        waitForElementVisibility(3, newAlbum);
        
        newAlbum.click();
        
        return this;
    }
    
    public AlbumsPage clicarEmUploadPhoto(){
        waitForElementVisibility(3, uploadPhoto);
        
        uploadPhoto.click();
        
        return this;
    }
    
    public AlbumsPage preencherNomeDoAlbumCom(String albumName){
        inputAlbumName.clear();
        inputAlbumName.sendKeys(albumName);
        
        return this;
    }
    
    public AlbumsPage preencherCaminhoDeImagemCom(String path){
        inputImg.clear();
        inputImg.sendKeys(path);
        
        wait(3);
        
        return this;
    }
    
    public AlbumPage clicarEmCreateAlbum(){
        performAction();
        return new AlbumPage(driver);
    }
    
    /* Verificações */
    public boolean estaLogado(){
        return logado;
    }
    
    public boolean albumFoiApagado(){
        return firstAlbum.isDisplayed();
    }
    
}
