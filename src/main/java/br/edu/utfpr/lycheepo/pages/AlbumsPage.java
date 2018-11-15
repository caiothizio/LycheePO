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
    
    @FindBy(id = "button_settings")
    WebElement buttonSettings;
    
    /* Albums */
    @FindBy(xpath = "/html/body/div[2]/div[2]")
    WebElement unsortedAlbum;
    
    @FindBy(xpath = "/html/body/div[2]/div[7]")
    WebElement firstAlbum;
    
    @FindBy(xpath = "/html/body/div[2]/div")
    WebElement firstPublicAlbum;
    
    /* Menu */
    @FindBy(className = "basicContextContainer")
    WebElement divMenu;
    
    @FindBy(xpath="/html/body/div[6]/div/table/tbody/tr[1]/td")
    WebElement uploadPhoto;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[1]/td")
    WebElement changeLogin;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[3]/td")
    WebElement importFromLink;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[7]/td")
    WebElement newAlbum;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[9]/td")
    WebElement signOut;
    
    
    /* Inputs */
    @FindBy(name = "username")
    WebElement inputUsername;
    
    @FindBy(name = "password")
    WebElement inputPassword;
    
    @FindBy(xpath = "/html/body/div[6]/div/div[1]/p/input")
    WebElement inputAlbumName;
    
    @FindBy(id = "upload_files")
    WebElement inputImg;
    
    @FindBy(name = "search")
    WebElement inputSearch;
    
    @FindBy(name = "oldPassword")
    WebElement inputOldPw;
    
    /* Divs */
    @FindBy(xpath = "/html/body/div[6]")
    WebElement noResults;
    
    @FindBy(xpath = "/html/body/div[2]/div[2]")
    WebElement photo;
    
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
    
    public AlbumsPage clicarEmSettings(){
        buttonSettings.click();
        wait(1);
        
        return this;
    }
    
    public AlbumsPage clicarEmSignOut(){
        signOut.click();
        
        return new AlbumsPage(driver, DESLOGADO);
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
    
    public AlbumsPage clicarEmChangeLogin(){
        waitForElementVisibility(3, changeLogin);
        changeLogin.click();
        
        return this;
    }
    
    public AlbumsPage preencherNomeDoAlbumCom(String albumName){
        inputAlbumName.clear();
        inputAlbumName.sendKeys(albumName);
        
        return this;
    }
    
    public AlbumsPage preencherPasswordAntigoCom(String oldPw){
        inputOldPw.clear();
        inputOldPw.sendKeys(oldPw);
        
        return this;
    }
    
    public AlbumsPage preencherNovoLoginCom(String newUser, String newPw){
        inputUsername.sendKeys(newUser);
        inputPassword.sendKeys(newPw);
        
        return this;
    }
    
    public AlbumsPage preencherCaminhoDeImagemCom(String path){
        System.out.println(inputImg);
        inputImg.clear();
        inputImg.sendKeys(path);
        
        wait(3);
        
        return this;
    }
    
    public AlbumsPage preencherCampoDeBuscaCom(String busca){
        wait(1);
        inputSearch.click();
        inputSearch.clear();
        inputSearch.sendKeys(busca);
        wait(1);
        
        return this;
    }
    
    public AlbumsPage preencherPasswordDoAlbumCom(String pw){
        inputPassword.clear();
        inputPassword.sendKeys(pw);
        
        return this;
    }
    
    public AlbumPage clicarEmEnter(){
        performAction();
        wait(1);
        
        return new AlbumPage(driver);
    }
    
    public AlbumPage clicarEmCreateAlbum(){
        performAction();
        wait(1);
        
        return new AlbumPage(driver);
    }
    
    public AlbumsPage confirmarNovoLogin(){
        performAction();
        wait(1);
        
        return this;
    }
    
    public AlbumPage clicarNoAlbumUnsorted(){
        unsortedAlbum.click();
        wait(1);
        return new AlbumPage(driver);
    }
    
    public AlbumPage clicarNoPrimeiroAlbum(){
        firstAlbum.click();
        wait(1);
        
        return new AlbumPage(driver);
    }
    
    public AlbumsPage clicarNoAlbumPublico(){
        firstPublicAlbum.click();
        
        return this;
    }
    
    /* Verificações */
    public boolean estaLogado(){
        return logado;
    }
    
    public boolean albumFoiApagado(){
        return firstAlbum.isDisplayed();
    }
    
    public boolean achouResultado(){
        return photo.isDisplayed();
    }
    
    public boolean naoAchouResultado(){
        return noResults.isDisplayed();
    }
    
}
