/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.lycheepo.basepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author caiot
 */
public class LycheeBasePage extends BasePage{
    
    /*Constantes*/
    public static final boolean LOGADO = true;
    public static final boolean DESLOGADO = false;
    
    @FindBy(className = "header__title")
    WebElement headerTitle;
    
    public LycheeBasePage(WebDriver driver) {
        super(driver);
    }
    
    public String verTitulo(){
        return headerTitle.getText();
    }
    
}
