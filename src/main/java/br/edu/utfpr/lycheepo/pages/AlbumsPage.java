/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.lycheepo.pages;

import br.edu.utfpr.lycheepo.basepage.LycheeBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    
    @FindBy(id = "basicModal__action")
    WebElement buttonSignIn;
    
    @FindBy(id = "basicModal__cancel")
    WebElement buttonCancel;
    
    
    /* Inputs */
    @FindBy(name = "username")
    WebElement inputUsername;
    
    @FindBy(name = "password")
    WebElement inputPassword;
    
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
    
    public AlbumsPage preencherCom(String user, String senha){
        inputUsername.sendKeys(user);
        inputPassword.sendKeys(senha);
        
        return this;
    }
    
    public AlbumsPage clicarEmSignIn(){
        buttonSignIn.click();
        
        if(inputPassword.getAttribute("class").contains("error"))
            return this;
        else
            return new AlbumsPage(driver, LycheeBasePage.LOGADO);
    }
    
    /* Verificações */
    public boolean estaLogado(){
        return logado;
    }
    
}
