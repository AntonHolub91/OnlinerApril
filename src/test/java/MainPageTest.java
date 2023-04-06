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

public class MainPageTest {
    WebDriver driver;
    MainPage mainPage;

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
        driver.get("https://www.onliner.by/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void goTologInForm() throws InterruptedException {
        MainPage newMainPage = mainPage.logInClick();
        String heading = newMainPage.getHeadingLogIn();
        boolean username = newMainPage.usernameFieldisDisplayed();
        boolean password = newMainPage.passwordFieldisDisplayed();
        Thread.sleep(2000);
        Assert.assertEquals("Вход", heading);
        Assert.assertTrue("Not true",username);
        Assert.assertTrue("Not true", password);
    }

    @Test
    public void goToCart(){
        CartPage newCartPage = mainPage.cartClick();
        String heading = newCartPage.getHeading();
        Assert.assertEquals("Корзина", heading);
    }

    @Test
    public  void logInWithEmptyCreds(){
        MainPage newMainPage = mainPage.logInWithEmptyFields();
        String errorUsername = newMainPage.getTextErrorUsername();
        String errorPassword = newMainPage.getTextErrorPassword();
        Assert.assertEquals("Укажите ник или e-mail", errorUsername);
        Assert.assertEquals("Укажите пароль", errorPassword);
    }

    @Test
    public void logInWithWrongCreds(){
        MainPage newMainPage = mainPage.enterWrongCreds("eqweqwe","zzzzzz");
        newMainPage.logInGreenClick();
        String error = newMainPage.getTextErrorWrongCreds();
        Assert.assertEquals("Неверный логин или пароль", error);
    }

    @Test
    public void goToCatalogViaButtonClick(){
        CataloguePage newCataloguePage = mainPage.clickCatalogueButton();
        String heading = newCataloguePage.getTextHeadingCatalogue();
        Assert.assertEquals("КаталогВсе суперцены!", heading);
    }

    @Test
    public void goToRegistarationPage() {
        ProfilePage newProfilePage = mainPage.goToProfile();
        String heading = newProfilePage.getHeading();
        Assert.assertEquals("Регистрация", heading);
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
