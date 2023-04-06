import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//div[contains(text(),'Корзина')]");
    private By logInFromCart = By.xpath("//a[text()='Войдите на сайт']");
    private By catalogueFromCart = By.xpath("//a[text()='каталог']");
    private By headingLogIn = By.xpath("//div[@class='auth-form__title auth-form__title_big auth-form__title_condensed-default']");
    private By mainPageButton = By.xpath("//a[@class='cart-form__image cart-form__image_logo']");
    private By cityTitle = By.xpath("//div[contains(text(),'Ваш населенный пункт')]/a");
    private By cityField = By.xpath("//input[@placeholder='Укажите ваш населенный пункт']");
    private By changeButton = By.xpath("//button[contains(text(),'Изменить')]");
    private By picture = By.xpath("/html/body/div[3]/div[2]/div/div/div/div[1]/div[2]");


    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public CartPage clickLogInFromCart() {
        driver.findElement(logInFromCart).click();
        return new CartPage(driver);
    }

    public String getTextHeading() {
        return driver.findElement(headingLogIn).getText();
    }

    public CataloguePage clickCatalogueFromCart() {
        driver.findElement(catalogueFromCart).click();
        return new CataloguePage(driver);
    }

    public MainPage clickMainPageButtonFromCart() {
        driver.findElement(mainPageButton).click();
        return new MainPage(driver);
    }

}
