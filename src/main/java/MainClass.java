import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class MainClass {
    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\QA\\Projects\\OnlinerApril\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "E:\\QA\\Projects\\OnlinerApril\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.edge.driver", "E:\\QA\\Projects\\OnlinerApril\\drivers\\msedgedriver.exe");

        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
//        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://profile.onliner.by/registration");

        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);


//        test

}
}
