import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Set;

import static org.testng.AssertJUnit.assertNull;

public class CookieTest {
    WebDriver driver;
    @BeforeTest
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void test(){
        driver.get("https://demo.guru99.com/test/cookie/selenium_aut.php");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("beka");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@name='submit']")).click();

        Set<Cookie> cookies = driver.manage().getCookies();

        driver.manage().deleteCookieNamed("Selenium");
        assertNull(driver.manage().getCookieNamed("selenium"));

        for(Cookie cookie : cookies){
            if(cookie.getExpiry() == null) {
                driver.manage().deleteCookie(cookie);
            }

        }
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }

}
