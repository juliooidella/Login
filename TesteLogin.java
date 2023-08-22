import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class TesteLogin {

    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\julio.oidella\\IdeaProjects\\GoogleSearchTest\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        extentReports = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-report.html"); // Nome do arquivo de relatório
        extentReports.attachReporter(htmlReporter);
    }

    @Test
    public void testElementWithErrorClassExists() {
        extentTest = extentReports.createTest("Test Element with Error Class Exists"); // Nome do teste no relatório

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("invalidUsername");
        driver.findElement(By.id("password")).sendKeys("invalidPassword");

        WebElement botaoLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        botaoLogin.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".flash.error"));
        assertTrue(errorMessage.isDisplayed());

        extentTest.log(Status.PASS, "Test passed!"); // Adicione um log ao relatório em caso de sucesso
    }

    @After
    public void teardown() {
        extentReports.flush(); // Finalize o relatório após os testes
        driver.quit();
    }
}
