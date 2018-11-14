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
public class AlbumPage extends LycheeBasePage {

    /* Buttons */
    @FindBy(id = "button_trash_album")
    WebElement buttonDelete;

    @FindBy(className = "button_add")
    WebElement buttonAdd;
    
    /* Menu */
    @FindBy(className = "basicContextContainer")
    WebElement divMenu;

    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr[3]/td")
    WebElement importFromLink;
    
    @FindBy(xpath = "/html/body/header/div[3]/a[2]")
    WebElement menuRename;
    
    @FindBy(xpath = "/html/body/div[6]/div/table/tbody/tr/td")
    WebElement buttonRename;

    /* Inputs */
    @FindBy(name = "link")
    WebElement inputLinkPhoto;
    
    @FindBy(name = "title")
    WebElement inputAlbumTitle;
    
    /* Photos */
    @FindBy(xpath = "/html/body/div[2]/div")
    WebElement firstPhoto;

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

        waitForElementVisibility(3, divMenu);

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

    public AlbumPage clicarEmImportFromLink() {
        waitForElementVisibility(3, importFromLink);

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
        firstPhoto.click();
        
        return new PhotoPage(driver);
    }
    
    public boolean fotoPossuiBadge(){
        return badgeStar.isDisplayed();
    }
}
