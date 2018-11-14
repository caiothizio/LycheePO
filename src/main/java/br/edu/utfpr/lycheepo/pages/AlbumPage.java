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

    /* Inputs */
    @FindBy(name = "link")
    WebElement inputLinkPhoto;

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

    public AlbumsPage confirmarExclusao() {
        performAction();
        wait(1);
        return new AlbumsPage(driver, LOGADO);
    }

    public AlbumPage clicarEmImportFromLink() {
        waitForElementVisibility(3, importFromLink);

        importFromLink.click();

        return this;
    }
    
    public AlbumPage preencherLinkDaImagemCom(String url){
        inputLinkPhoto.sendKeys(url);
        
        return this;
    }
    
    
}
