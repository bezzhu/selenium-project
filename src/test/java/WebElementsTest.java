import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class WebElementsTest {
    WebDriver driver;
    @BeforeTest
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void test() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addElementButton =  driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        for (int i = 0; i < 3; i++) {
           addElementButton.click();
        }
        WebElement lastDeleteButton = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[3]"));
        System.out.println(lastDeleteButton.getText());

        List<WebElement> deleteButtonList = driver.findElements(By.cssSelector("button[class^='added'"));
        System.out.println(deleteButtonList.get(2).getText());

        WebElement deleteButton = driver.findElement(By.xpath("//*[contains(@class, 'manually') and text()='Delete'][3]"));
        System.out.println(deleteButton.getText());

        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        WebElement lorem = driver.findElement(By.xpath("//td[text()='Apeirian9']//parent::tr/child::td"));
        System.out.println(lorem.getText());

        WebElement nextToIpsum = driver.findElement(By.xpath("//td[text()='Apeirian9']//following-sibling::td"));
        System.out.println(nextToIpsum.getText());
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
