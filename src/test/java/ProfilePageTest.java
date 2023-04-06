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

public class ProfilePageTest {
    WebDriver driver;
    ProfilePage profilePage;
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
        driver.get("https://profile.onliner.by/registration");
        profilePage = new ProfilePage(driver);
    }

    @Test
    public void enterInvalidEmail() throws InterruptedException {
        ProfilePage newProfilePage = profilePage.typeEmail("dsdsdsd");
        Thread.sleep(2000);
        String emailError = newProfilePage.incorrectEmail();
        Assert.assertEquals("Некорректный e-mail", emailError);
    }

    @Test
    public void emptyEmailMessage() throws InterruptedException {
        ProfilePage newProfilePage = profilePage.typeEmail("");
        newProfilePage.typePassword("qwertyuiop");
        newProfilePage.typeRepeatPassword("qwertyuiop");
        newProfilePage.selectCheckbox();
        newProfilePage.submitForm();
        String emailError = newProfilePage.emptyEmail();
        Assert.assertEquals("Укажите e-mail", emailError);
    }

    @Test
    public void emptyPasswordMessage(){
        ProfilePage newProfilePage = profilePage.typeEmail("testemail@test.test");
        newProfilePage.typePassword("");
        newProfilePage.typeRepeatPassword("");
        newProfilePage.selectCheckbox();
        newProfilePage.submitForm();
        String passwordError = newProfilePage.emptyPassword();
        String repeatPasswordError = newProfilePage.emptyRepeatPassword();
        Assert.assertEquals("Укажите пароль", passwordError);
        Assert.assertEquals("Укажите пароль", repeatPasswordError);
    }

    @Test
    public void shortPasswordMessage(){
        ProfilePage newProfilePage = profilePage.typeEmail("testemail@test.test");
        newProfilePage.typePassword("qwertyu");
        newProfilePage.typeRepeatPassword("qwertyu");
        newProfilePage.selectCheckbox();
        newProfilePage.submitForm();
        String passwordError = newProfilePage.emptyPassword();
        String repeatPasswordError = newProfilePage.shortPasswordError();
        Assert.assertEquals("Пароль должен быть от 8 до 64 символов", passwordError);
    }

    @Test
    public void differentPasswordsMessage(){
        ProfilePage newProfilePage = profilePage.typeEmail("testemail@test.test");
        newProfilePage.typePassword("qwertyu1");
        newProfilePage.typeRepeatPassword("12345672");
        newProfilePage.selectCheckbox();
        newProfilePage.submitForm();
        String differentPasswordsError = newProfilePage.differentPasswordsErrorGetText();
        Assert.assertEquals("Пароли не совпадают", differentPasswordsError);
    }

    @Test
    public void submitWithoutCheckbox(){
        ProfilePage newProfilePage = profilePage.submitForm();
        String error = newProfilePage.checkboxIsNotSelectedError();
        Assert.assertEquals("Для регистрации аккаунта необходимо ваше согласие с Политикой конфиденциальности и Пользовательским соглашением", error);
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
