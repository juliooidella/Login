import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignTest {

    private WebDriver driver;

    @BeforeMethod
    public void configuracaoInicial() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\julio.oidella\\IdeaProjects\\GoogleSearchTest\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testElementoComClasseDeErroExiste() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("http://127.0.0.1:8080/");

        driver.findElement(By.id("user")).sendKeys("invalidUsername");
        driver.findElement(By.id("senha")).sendKeys("invalidPassword");

        WebElement botaoLogin = driver.findElement(By.className("btn-success"));
        botaoLogin.click();

        WebElement mensagemErro = driver.findElement(By.cssSelector(".flash.error"));
        boolean exibicaoMensagemErro = mensagemErro.isDisplayed();

        Assert.assertTrue(exibicaoMensagemErro, "A mensagem de erro está sendo exibida.");
    }

    @Test
    public void testElementoComClasseDeSucessoExiste() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        botaoLogin.click();

        WebElement mensagemSucesso = driver.findElement(By.cssSelector(".flash.success"));
        boolean exibicaoMensagemSucesso = mensagemSucesso.isDisplayed();

        Assert.assertTrue(exibicaoMensagemSucesso, "A mensagem de sucesso está sendo exibida.");
    }

    @AfterMethod
    public void finalizarTeste() {
        driver.quit();
    }
}
