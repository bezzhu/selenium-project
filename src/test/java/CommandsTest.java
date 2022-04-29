import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CommandsTest {

    WebDriver driver;

    public CommandsTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void test() {
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();

        // Click on Enable button
        driver.findElement(By.xpath("//*[@id=\"input-example\"]/button")).click();

        // Check that input field became enabled
        driver.findElement(By.xpath("//*[@id=\"input-example\"]/input")).isEnabled();

        // text 'It's enabled!' is displayed
        boolean b = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/p")).isDisplayed();
        System.out.println(b);
        // Check that button text has changed from Enable to Disable
        String s = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button")).getText();
        System.out.println(s);

    }

    @Test
    public void testInput(){
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();

        WebElement elem = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/input"));
        elem.sendKeys("Bootcamp");
        elem.clear();
    }

    @Test
    public void testLocation(){
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();

        int a = driver.findElement(By.id("column-a")).getLocation().getY();
        int b = driver.findElement(By.id("column-b")).getLocation().getY();
        System.out.println(a + " = " + b);
    }

    @Test
    public void testCheckbox(){
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();
        // Click checkbox
        driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input")).click();
        // Click "remove"
        driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/button")).click();
        // text 'It's gone!' is displayed
        boolean a = driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input")).isDisplayed();
        System.out.println(a);
    }

    @Test
    public void testSize(){
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();

        int widthA = driver.findElement(By.id("column-a")).getSize().getWidth();
        int heightA =  driver.findElement(By.id("column-a")).getSize().getHeight();
        int widthB = driver.findElement(By.id("column-b")).getSize().getWidth();
        int heightB =  driver.findElement(By.id("column-b")).getSize().getHeight();

        int areaA = widthA * heightA;
        int areaB = widthB * heightB;

        System.out.println(areaA);
        System.out.println(areaB);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
