import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Exceptions {
    WebDriver driver;
    @BeforeTest
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void testDatePicker(){
        driver.get("https://jqueryui.com/datepicker/");
        //Click on 'Date' button
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"content\"]/iframe")));
        driver.findElement(By.xpath("//*[@id=\"datepicker\"]")).click();
        //Try to avoid and handle NoSuchFrameException
        try {
            driver.switchTo().frame("noFrame");
        } catch (NoSuchFrameException e){
            System.out.println("Wrong frame name");
        }
        //Select from date picker the last day of previous month dynamically
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]/span")).click();
        WebElement htmlTable=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table"));
        List<WebElement> rows= htmlTable.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(rows.size() - 1).findElements(By.tagName("td"));
        columns.get(columns.size() - 1).click();
    }
    @Test
    public void testExceptions() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("timerAlertButton")).click();
        //Try to invoke TimeOutException exception
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (TimeoutException e) {
            System.out.println("TimeoutException");
        }
        //Try to avoid NoAlertPresentException and Handle possible exception
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("NoAlertPresentException");
        }
        //Try to invoke StaleElementReferenceException
        try {
            driver.findElement(By.xpath("//*[@id='timerAlertButton']")).click();
        } catch (StaleElementReferenceException e){
            System.out.println("StaleElementReferenceException");
        }
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
