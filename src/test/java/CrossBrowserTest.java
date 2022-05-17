import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.File;
import static org.testng.AssertJUnit.assertEquals;

public class CrossBrowserTest {
    WebDriver driver;
    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser){
        if(browser.equalsIgnoreCase("Edge")){
            System.setProperty("webdriver.edge.driver","src/drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        }else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            ChromeDriver driver = new ChromeDriver(options);

        }
    }
    @Test
    public void testToDoList() {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[3]/span/i"));

        Actions action = new Actions(driver);
        action.moveToElement(element);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    @Test
    public void testScrolling(){
        driver.get("http://webdriveruniversity.com/Scrolling/index.html");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"zone2-entries\"]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);

        String sText = js.executeScript("return document.getElementById('zone2-entries').innerHTML").toString();
        assertEquals("0 Entries" , sText);


    }
    @Test
    public void test() {
        File path = new File("C:\\Users\\LENOVO\\Desktop\\Screenshot 2022-05-12 202203.png");
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement addFile = driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));
        addFile.sendKeys(path.getAbsolutePath());
        driver.findElement(By.xpath("//*[@id=\"file-submit\"]")).click();

        try {
            addFile.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException");
        }
    }
        @AfterClass
        public void tearDown(){
            driver.close();
        }
}
