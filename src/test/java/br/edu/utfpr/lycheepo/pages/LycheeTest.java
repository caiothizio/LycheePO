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
import static org.junit.Assert.assertEquals;
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
    
    private final String testImg = System.getProperty("user.dir")+"\\img\\shiba.jpg";
    
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

        driver.get("http://192.168.0.103/");
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
 
    /* CT03: Adicionar foto a álbum via link */
    @Test
    @Ignore
    public void CT03(){
        AlbumPage albumPage = home.
                              clicarNoBotaoDeLogin().
                              preencherLoginCom(user, password).
                              clicarEmSignIn().
                              clicarEmAdd().
                              clicarEmNewAlbum().
                              preencherNomeDoAlbumCom("Dogs").
                              clicarEmCreateAlbum().
                              clicarEmAdd().
                              clicarEmImportFromLink().
                              preencherLinkDaImagemCom("https://t1.ea.ltmcdn.com/pt/images/7/0/3/como_adestrar_um_shiba_inu_21307_600.jpg").
                              clicarEmImport();
        
        assertEquals("como_adestrar_um_shiba_inu_21307_600", albumPage.tituloDaFotoImportada());
        
        albumPage.clicarEmDeletar().confirmarExclusao();
        
        //Funciona fora da UTFPR. Precisa de configuração do proxy para funcionar.
    }
    
    /* CT04: Adicionar foto sem especificar álbum */
    @Test
    @Ignore
    public void CT04(){
        AlbumsPage albumsPage = home.
                                clicarNoBotaoDeLogin().
                                preencherLoginCom(user, password).
                                clicarEmSignIn().
                                preencherCaminhoDeImagemCom(testImg);
        
        //Parado. Nao está funcionando a interação com o input do tipo file, pois este está hidden na página.                              
    }
    
    /* CT05: Estrelar foto */
    @Test
    @Ignore
    public void CT05(){
        /* Importante: para este caso de teste funcionar, é preciso ter ao menos uma imagem carregada no álbum "Unsorted" e esta não pode estar marcada com estrela. */
        AlbumPage albumPage = home.
                              clicarNoBotaoDeLogin().
                              preencherLoginCom(user, password).
                              clicarEmSignIn().
                              clicarNoAlbumUnsorted();
        
        assertEquals(albumPage.getCurrentTitle(), "Lychee - Unsorted");
        
        PhotoPage photoPage = albumPage.
                              clicarNaPrimeiraFoto().clicarNaEstrela();
        
        assertTrue(photoPage.favoritou());
        
        albumPage = photoPage.
                    clicarEmVoltar();
        
        assertTrue(albumPage.fotoPossuiBadge());
        
        photoPage = albumPage.
                    clicarNaPrimeiraFoto().
                    clicarNaEstrela();
        
        assertTrue(photoPage.desfavoritou());
    }
    
    /* CT06: Renomear Álbum */
    @Test
    @Ignore
    public void CT06(){
        AlbumPage albumPage = home.
                              clicarNoBotaoDeLogin().
                              preencherLoginCom(user, password).
                              clicarEmSignIn().
                              clicarEmAdd().
                              clicarEmNewAlbum().
                              preencherNomeDoAlbumCom("Dogs").
                              clicarEmCreateAlbum().
                              clicarNoNomeDoAlbum().
                              clicarEmRename().
                              preencherNovoNomeDoAlbumCom("Teste").
                              clicarEmSetTitle();
        
        assertEquals(albumPage.verTitulo(), "Teste");
        
        //Apagar álbum para conseguir reexecutar este teste.
        
        albumPage.clicarEmDeletar().confirmarExclusao();
    }
    
    /* CT07: Excluir Foto */
    @Test
    @Ignore
    public void CT07(){
        /* Importante: para este caso de teste funcionar, é necessário possuir uma foto no álbum Unsorted. */
        AlbumPage albumPage = home.
                              clicarNoBotaoDeLogin().
                              preencherLoginCom(user, password).
                              clicarEmSignIn().
                              clicarNoAlbumUnsorted().
                              clicarNaPrimeiraFoto().
                              clicarEmApagar().
                              clicarEmDeletePhoto();
        
        assertTrue(albumPage.apagouFoto());                        
    }
    
    /* CT08: Buscar */
    @Test
    @Ignore
    public void CT08(){
        AlbumsPage albumsPage = home.
                                clicarNoBotaoDeLogin().
                                preencherLoginCom(user, password).
                                clicarEmSignIn().
                                preencherCampoDeBuscaCom("doggo");
        
        assertTrue(albumsPage.achouResultado());
        
        albumsPage.preencherCampoDeBuscaCom("teste");
        
        assertTrue(albumsPage.naoAchouResultado());
    }
    
    /* CT09: Tornar álbum público com senha */
    @Test
    @Ignore
    public void CT09(){
        AlbumPage albumPage = home.
                              clicarNoBotaoDeLogin().
                              preencherLoginCom(user, password).
                              clicarEmSignIn().
                              clicarEmAdd().
                              clicarEmNewAlbum().
                              preencherNomeDoAlbumCom("AlbumPublico").
                              clicarEmCreateAlbum().
                              clicarEmMakePublic().
                              clicarEmPasswordProtected().
                              preencherSenhaDoAlbumCom("teste").
                              clicarEmShare().
                              clicarEmVoltar().
                              clicarEmSettings().
                              clicarEmSignOut().
                              clicarNoAlbumPublico().
                              preencherPasswordDoAlbumCom("teste").
                              clicarEmEnter();
        
        assertEquals("AlbumPublico", albumPage.verTitulo());
        
        //Excluir album para repetir teste
        AlbumsPage albumsPage = albumPage.
                                clicarEmVoltar().
                                clicarNoBotaoDeLogin().
                                preencherLoginCom(user, password).
                                clicarEmSignIn().
                                clicarNoPrimeiroAlbum().
                                clicarEmDeletar().
                                confirmarExclusao();
    }
    
}
