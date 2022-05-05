import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SwitchToTest {
    WebDriver driver;
    @BeforeTest
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void testTextField() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/iframe");
        WebElement element = driver.findElement(By.id("mce_0_ifr"));

        driver.switchTo().frame(element);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p")).sendKeys("Here Goes");
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("#content > div > div > div.tox-editor-container > div.tox-editor-header > div.tox-toolbar-overlord > div > div:nth-child(4) > button:nth-child(2) > span > svg")).click();
    }
    @Test
    public void testAlert() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        driver.switchTo().alert().accept();
    }

}
