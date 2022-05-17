import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class JSexecutor {
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
    public void testToDoList() {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[3]/span/i"));

        Actions action = new Actions(driver);
        action.moveToElement(element);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].remove();", element);
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
    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
