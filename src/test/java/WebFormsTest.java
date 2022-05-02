import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.testng.AssertJUnit.assertEquals;


public class WebFormsTest {
    WebDriver driver;

    public WebFormsTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testDropDown(){

        WebElement element = driver.findElement(By.xpath("//*[@id=\"dropdowm-menu-1\"]"));
        Select select = new Select(element);
        List<String> list = new ArrayList<>(Arrays.asList("JAVA", "SQL" ,"Python" , "C#"));

        for (String s: list ) {
            select.selectByVisibleText(s);
            assertEquals(s, select.getFirstSelectedOption().getText());
        }

    }

    @Test
    public void testCheckBox() throws InterruptedException {
//        List<WebElement> checkBox = driver.findElements(By.xpath("//*[@id=\"checkboxes\"]"));
//        for(WebElement box : checkBox){
//            System.out.println(box.getText());
//            if(!box.isSelected()) box.click();
//        }
        List<WebElement> list = new ArrayList<>();
        list.add(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/label[1]")));
        list.add(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/label[2]")));
        list.add(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/label[3]")));
        list.add(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/label[4]")));
        for(WebElement box : list ){
            if(!box.isSelected()) {
                Thread.sleep(2000);
                box.click();
            }
        }
    }

    @Test
    public void testRadioButton() {
        List<WebElement> button = driver.findElements(By.xpath("//*[@id=\"radio-buttons\"]"));
        for(WebElement butt : button){
            if(butt.getText().equals("Yellow")){
                butt.click();
            }
        }

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
