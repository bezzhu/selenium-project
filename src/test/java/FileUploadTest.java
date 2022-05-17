import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;

public class FileUploadTest {
    WebDriver driver;
    @BeforeTest
    public void before(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @Test
    public void test() {
        File path = new File("C:\\Users\\LENOVO\\Desktop\\Screenshot 2022-05-12 202203.png");
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement addFile = driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));
        addFile.sendKeys( path.getAbsolutePath());
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
