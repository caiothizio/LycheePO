/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.lycheepo.pages;

import br.edu.utfpr.lycheepo.basepage.LycheeBasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author caiot
 */
public class LycheeTest {
    /* Consts */
    private final String user = "teste";
    private final String password = "utfpr";
    
    private WebDriver driver;
    AlbumsPage home;
   
    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup(); 
    }
   
    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("lang=en-US");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://169.254.8.146/");
        home = new AlbumsPage(driver, LycheeBasePage.DESLOGADO);
    }
    
    @After
    public void tearDown() {
        driver.close();
    }
    
    /* CT01: Realizar Login */
    @Test 
    @Ignore
    public void CT01(){
        AlbumsPage albumsPage = home.
                                clicarNoBotaoDeLogin().
                                preencherLoginCom(user, password).
                                clicarEmSignIn();
        
        assertTrue(albumsPage.estaLogado());
    }
    
    /* CT02: Criar álbum e depois excluí-lo */
    @Test(expected = org.openqa.selenium.NoSuchElementException.class)
    @Ignore
    public void CT02(){
        AlbumsPage albumsPage = home.
                                clicarNoBotaoDeLogin().
                                preencherLoginCom(user, password).
                                clicarEmSignIn().
                                clicarEmAdd().
                                clicarEmNewAlbum().
                                preencherNomeDoAlbumCom("teste").
                                clicarEmCreateAlbum().
                                clicarEmDeletar().
                                confirmarExclusao();
        
        albumsPage.albumFoiApagado(); //a função não consegue encontrar o elemento (pois foi excluído) e lança uma NoSuchElementException.
    }
 
    
}
