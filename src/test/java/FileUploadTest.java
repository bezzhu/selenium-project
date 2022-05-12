import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileUploadTest {
    WebDriver driver;
    @BeforeTest
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void test() {
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement addFile = driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));
        addFile.sendKeys("C:\\Users\\LENOVO\\Desktop\\Screenshot 2022-05-12 202203.png");
        driver.findElement(By.xpath("//*[@id=\"file-submit\"]")).click();

        try{
            addFile.click();
        }catch (StaleElementReferenceException e){
            System.out.println("StaleElementReferenceException");
        }
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
