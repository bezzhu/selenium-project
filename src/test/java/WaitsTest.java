import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WaitsTest {

    WebDriver driver;

    public WaitsTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/progress-bar");
    }

    @Test
    public void testStartButton() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"startStopButton\"]"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver,40);
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"progressBar\"]/div"));
        if(wait.until(ExpectedConditions.textToBePresentInElement(element1 , "100%")) ){
            System.out.println("100%");
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
