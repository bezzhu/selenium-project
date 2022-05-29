import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.testng.AssertJUnit.assertEquals;

public class automationPractice {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;
    Select select;
    Random rd;
    List<String> cart;

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser){

        if(browser.equalsIgnoreCase("Edge")){
            WebDriverManager.edgedriver().setup();
            //System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
            driver = new EdgeDriver();
        }else if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 40);
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        rd = new Random();
        cart = new ArrayList<>();
    }
//    @BeforeTest
//    public void before() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, 40);
//        js = (JavascriptExecutor) driver;
//        action = new Actions(driver);
//        rd = new Random();
//        cart = new ArrayList<>();
//    }

    @Test
    public void test() {

        //Move to 'Women' and select 'T-shirts'

        driver.get("http://automationpractice.com/index.php");
        //WebElement women = driver.findElement(By.cssSelector("a[title='Women']"));
        WebElement tShirts = driver.findElement(By.cssSelector("a[title='T-shirts']")); //By cssSelector(1) Tag and Attribute(1)
        js.executeScript("arguments[0].click();", tShirts); // JS executor(1)
        //action.moveToElement(women).moveToElement(tShirts).click().perform();

        //Click on the button 'Quick view', which is visible on mouse hover on the returned card image

        WebElement quickView = driver.findElement(By.cssSelector("a.product_img_link")); // Tag and class(2)
        js.executeScript("arguments[0].scrollIntoView();", quickView); // JS executor (2) scroll
        wait.until(ExpectedConditions.elementToBeClickable(quickView)).click();

        //In the opened alert window move to the all small images and check that big image changes

        WebElement frame = driver.findElement(By.cssSelector("iframe.fancybox-iframe"));
        driver.switchTo().frame(frame);

        List<WebElement> smallImages = driver.findElements(By.xpath("//ul[@id='thumbs_list_frame']//img"));

        for (WebElement img : smallImages) {
            action.moveToElement(img).perform();
            WebElement bigImage = driver.findElement(By.id("bigpic"));
            int bigImg = Integer.parseInt(bigImage.getAttribute("src").replaceAll("[\\D]", ""));
            int smallImg = Integer.parseInt(bigImage.getAttribute("src").replaceAll("[\\D]", ""));
            assertEquals(bigImg, smallImg);
        }

        //Add two M size to the cart and click on 'Continue Shopping' button
        WebElement product1 = driver.findElement(By.xpath("//*[@itemprop='name']"));
        cart.add(product1.getText());

        WebElement textField = driver.findElement(By.xpath("//input[@name='qty']"));// Basic Xpath (1)
        textField.clear();
        textField.sendKeys("2");

        WebElement size = driver.findElement(By.cssSelector("select#group_1"));// Tag and ID (3)
        select = new Select(size);
        select.selectByVisibleText("M");

        driver.findElement(By.cssSelector("button.exclusive")).click();
        driver.switchTo().defaultContent();
        WebElement continueShopping = driver.findElement(By.xpath("//span[@title='Continue shopping']")); // By xpath(2)
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping)).click();

        //Move to the main page and select 'Casual Dresses' from the 'Dresses'

        driver.findElement(By.xpath("//*[contains(@alt,'My Store')]")).click(); // Using contains (2)

        //WebElement dresses = driver.findElement(By.xpath("//a[text()='Dresses']")); // Using text (3)
        WebElement casualDresses = driver.findElement(By.xpath("//a[text()='Casual Dresses']"));
        //action.moveToElement(dresses).moveToElement(casualDresses).click().perform();
        js.executeScript("arguments[0].click();", casualDresses);
        //Move to the returned element and Add to cart and click on 'Continue Shopping' button
        WebElement product2 = driver.findElement(By.xpath("//a[@itemprop='url' and @class='product-name']"));
        cart.add(product2.getText());
        WebElement returnedElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li"));
        WebElement addToCart = driver.findElement(By.xpath("//a[@title='Add to cart']"));
        action.moveToElement(returnedElement).moveToElement(addToCart).click().perform();
        WebElement continueShop = driver.findElement(By.xpath("//span[@title='Continue shopping']"));

        wait.until(ExpectedConditions.elementToBeClickable(continueShop)).click();

        //Move to the Cart and Check out

        WebElement cart1 = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        WebElement checkOut = driver.findElement(By.xpath("//a[@title='Check out']"));
        action.moveToElement(cart1).moveToElement(checkOut).click().perform();
        checkOut.click();

        //Check by description that all items are added in cart

//        WebElement htmltable = driver.findElement(By.id("cart_summary"));
//        List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
//        boolean product1InCart = false;
//        boolean product2InCart = false;
//        for (int r = 0; r < rows.size(); r++) {
//            List<WebElement> c = htmltable.findElements(By.tagName("td"));
//                for (WebElement w : c) {
//                    if(w.getText().equalsIgnoreCase(cart.get(0))){
//                        product1InCart = true;
//                    }
//                    if(w.getText().equalsIgnoreCase(cart.get(1))){
//                        product2InCart = true;
//                    }
//                }
//        }
//        assertTrue(product1InCart);
//        assertTrue(product2InCart);
        assertEquals(cart.get(0) , driver.findElement(By.xpath("//*[@id=\"product_1_3_0_0\"]/td[2]/p/a")).getText());
        assertEquals(cart.get(1) , driver.findElement(By.xpath("//*[@id=\"product_3_13_0_0\"]/td[2]/p/a")).getText());
        //Click on 'Proceed to checkout' and sign up for a new account
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();
        rd.ints(1000);
        int x = rd.nextInt();
        String gmail = "Tbc"+x+"academy@gmail.com";
        driver.findElement(By.id("email_create")).sendKeys(gmail);
        driver.findElement(By.id("SubmitCreate")).click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"noSlide\"]/h1"), "CREATE AN ACCOUNT"));
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.xpath("//input[@name='customer_firstname']")).sendKeys("name");
        driver.findElement(By.xpath("//input[@name='customer_lastname']")).sendKeys("lastname");
        driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("password");
        js.executeScript("document.getElementById('company').value='Geo';"); //JS executor (3)
        driver.findElement(By.id("address1")).sendKeys("TG");
        driver.findElement(By.id("city")).sendKeys("Tbilisi");
        WebElement country = driver.findElement(By.name("id_state"));
        select = new Select(country);
        select.selectByVisibleText("Georgia");
        driver.findElement(By.id("postcode")).sendKeys("12435");
        driver.findElement(By.id("other")).sendKeys("584234832");
        driver.findElement(By.id("phone_mobile")).sendKeys("584234832");
        driver.findElement(By.id("submitAccount")).click();

        //Click on 'Proceed to checkout' and leave Address data the default

        driver.findElement(By.name("processAddress")).click(); // By name (3)

        //Click on 'Proceed to checkout' and try to click to 'Proceed to checkout' button in Shipping tab without accepting 'Terms of service'
        //Catch error window and accept 'Terms of service'
        driver.findElement(By.xpath("//*[@name='processCarrier']")).click();
        try {
            driver.findElement(By.xpath("//*[@name='processCarrier']")).click();
        }
        catch (Exception e){
            driver.findElement(By.xpath("//*[@title='Close']")).click();
            driver.findElement(By.id("cgv")).click();
            driver.findElement(By.xpath("//*[@name='processCarrier']")).click();
        }

        //In 'Payment' Tab choose 'Pay by check'

        driver.findElement(By.cssSelector("a.cheque")).click();

        //Check that total amount is correct and click on 'Confirm my order'

        assertEquals("$61.02",driver.findElement(By.id("amount")).getText());
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();

        //Click on 'customer service department' link

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/a")).click();

        //Choose heading and order reference, upload file, add message text and Send

        WebElement heading = driver.findElement(By.id("id_contact"));
        select = new Select(heading);
        select.selectByIndex(1);

        driver.findElement(By.xpath("//*[@name='id_order']")).click();
        driver.findElement(By.xpath("//*[@name='id_order']//option//following-sibling::option")).click();

        File path = new File("src/main/resources/Screenshot 2022-05-12 202203.png");
        driver.findElement(By.id("fileUpload")).sendKeys(path.getAbsolutePath());

        driver.findElement(By.id("message")).sendKeys("400 points");
        driver.findElement(By.id("submitMessage")).click();


    }
    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
