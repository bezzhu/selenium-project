import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class WebTablesTest {
    WebDriver driver;
    String CAR_NAME = "Honda"; //Suzuki, Hyundai
    @BeforeTest
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void testTable() {
        driver.get("http://techcanvass.com/Examples/webtable.html");
        WebElement htmlTable=driver.findElement(By.xpath("//*[@id=\"t01\"]"));
        List<WebElement> rows=htmlTable.findElements(By.tagName("tr"));

        for(int r =0; r < rows.size(); r++) {
            List<WebElement> columns = rows.get(r).findElements(By.tagName("td"));
            for (int c = 0; c < columns.size(); c++) {
                if(columns.get(c).getText().equals(CAR_NAME) ){
                    System.out.println(columns.get(columns.size() - 1).getText());
                }
            }
        }
    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
