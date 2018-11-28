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
public class AlbumPage extends LycheeBasePage {

    /* Buttons */
    @FindBy(id = "button_trash_album")
    WebElement buttonDelete;

    @FindBy(xpath = "/html/body/header/div[3]/a[8]")
    WebElement buttonAdd;
    
    @FindBy(id = "button_share_album")
    WebElement buttonMakePublic;
    
    @FindBy(id = "button_back_home")
    WebElement buttonBackHome;
    
    @FindBy(xpath = "/html/body/div[6]/div/div[1]/form/div[3]/label/span[1]")
    WebElement checkBoxPwProtected;
    
    @FindBy(name = "passwordtext")
    WebElement inputPwProtected;
    
    /* Menu */
    @FindBy(className = "basicContextContainer")
    WebElement divMenu;

    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[3]/td")
    WebElement importFromLink;
    
    @FindBy(xpath = "/html/body/header/div[3]/a[2]")
    WebElement menuRename;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr/td")
    WebElement buttonRename;
    
    @FindBy(xpath = "/html/body")
    WebElement desselecionar;

    /* Inputs */
    @FindBy(name = "link")
    WebElement inputLinkPhoto;
    
    @FindBy(name = "title")
    WebElement inputAlbumTitle;
    
    /* Photos */
    @FindBy(xpath = "/html/body/div[2]/div")
    WebElement firstPhoto;
    
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/h1")
    WebElement firstPhotoTitle;

    /* Badges */
    @FindBy(xpath = "/html/body/div[2]/div/div[2]/a[1]")
    WebElement badgeStar;
    
    /* Construtor */
    public AlbumPage(WebDriver driver) {
        super(driver);
    }

    /* Ações */
    public AlbumPage clicarEmDeletar() {
        buttonDelete.click();

        return this;
    }

    public AlbumPage clicarEmAdd() {
        buttonAdd.click();

        wait(1);

        return this;
    }
    
    public AlbumsPage clicarEmVoltar(){
        desselecionar.click();
        wait(1);
        buttonBackHome.click();
        
        return new AlbumsPage(driver, LOGADO);
    }
    
    public AlbumPage clicarEmMakePublic(){
        buttonMakePublic.click();
        wait(1);
        
        return this;
    }
    
    public AlbumPage clicarEmPasswordProtected(){
        checkBoxPwProtected.click();
        
        return this;
    }
    
    public AlbumPage preencherSenhaDoAlbumCom(String pw){
        inputPwProtected.clear();
        inputPwProtected.sendKeys(pw);
        
        return this;
    }
    
    public AlbumPage clicarEmShare(){
        performAction();
        wait(1);
        
        return this;
    }
    
    public AlbumPage clicarNoNomeDoAlbum(){
        menuRename.click();
        
        waitForElementVisibility(3, divMenu);
        
        return this;
    }

    public AlbumsPage confirmarExclusao() {
        performAction();
        wait(1);
        return new AlbumsPage(driver, LOGADO);
    }
    
    public AlbumPage clicarEmSetTitle() {
        performAction();
        wait(1);
        return this;
    }
    
    public AlbumPage clicarEmImport(){
        performAction();
        wait(2);
        
        return this;
    }

    public AlbumPage clicarEmImportFromLink() {

        importFromLink.click();

        return this;
    }
    
    public AlbumPage clicarEmRename(){
        buttonRename.click();
        
        return this;
    }
    
    
    public AlbumPage preencherLinkDaImagemCom(String url){
        inputLinkPhoto.sendKeys(url);
        
        return this;
    }
    
    public AlbumPage preencherNovoNomeDoAlbumCom(String newName){
        inputAlbumTitle.clear();
        inputAlbumTitle.sendKeys(newName);
        
        return this;
    }
    
    public PhotoPage clicarNaPrimeiraFoto(){
        wait(1);
        firstPhoto.click();
        
        return new PhotoPage(driver);
    }
    
    public boolean fotoPossuiBadge(){
        return badgeStar.isDisplayed();
    }
    
    public boolean apagouFoto(){
        return firstPhoto.isDisplayed();
    }
    
    public String tituloDaFotoImportada(){
        return firstPhotoTitle.getAttribute("title");
    }
    
}
