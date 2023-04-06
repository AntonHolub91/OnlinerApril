import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CartPageTest {
    WebDriver driver;
    CartPage cartPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "E:\\QA\\Projects\\OnlinerApril\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "E:\\QA\\Projects\\OnlinerApril\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.edge.driver", "E:\\QA\\Projects\\OnlinerApril\\drivers\\msedgedriver.exe");

//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
//        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://cart.onliner.by/");
        cartPage = new CartPage(driver);
    }

    @Test
    public void logInViaCart() throws InterruptedException {
        CartPage newCartPage = cartPage.clickLogInFromCart();
        String heading = newCartPage.getTextHeading();
        Thread.sleep(2000);
        Assert.assertEquals("Вход", heading);
    }

    @Test
    public void goToCataloguefromCart(){
        CataloguePage newCataloguePage = cartPage.clickCatalogueFromCart();
        String heading = newCataloguePage.getTextHeadingCatalogue();
        Assert.assertEquals("КаталогВсе суперцены!", heading);
    }

    @Test
    public void goToMainPageFromCart(){
        MainPage newMainPage = cartPage.clickMainPageButtonFromCart();
        boolean logo = newMainPage.findMainLogo();
        Assert.assertTrue("wrong", logo);
    }

    @After
    public void tearDown(){
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("E:\\QA\\OnlinerScreenshots\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

}
