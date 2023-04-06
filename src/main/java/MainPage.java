import org.apache.log4j.chainsaw.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By logInButton = By.xpath("//div[@class='auth-bar__item auth-bar__item--text']");
    private final By headingLogIn = By.xpath("//div[@class='auth-form__title auth-form__title_big auth-form__title_condensed-default']");
    private final By cartButton = By.xpath("//a[@title='Корзина']");
    private final By logInButtonGreen = By.xpath("//button[@class='auth-button auth-button_primary auth-button_middle auth-form__button auth-form__button_width_full']");
    private final By usernameField = By.xpath("//input[@placeholder='Ник или e-mail']");
    private final By passwordField = By.xpath("//input[@placeholder='Пароль']");
    private final By errorUsername = By.xpath("//div[contains(text(),'Укажите ник')]");
    private final By errorPassword = By.xpath("//div[contains(text(),'Укажите пароль')]");
    private final By errorWrongCreds = By.xpath("//div[contains(text(),'Неверный логин')]");
    private final By catalogueButton = By.xpath("//*[@id=\"container\"]/div/div/header/div[2]/div/nav/ul[1]/li[1]/a[2]");
    private final By searchField = By.xpath("//input[@class='fast-search__input']");
    private final By resultWrapper = By.xpath("//a[text()='Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)']");
    private final By mainLogo = By.xpath("//img[@class='onliner_logo']");
    private final By signUp = By.xpath("//a[contains(text(),'Зарегистрироваться')]");


    public MainPage logInClick() {
        driver.findElement(logInButton).click();
        return new MainPage(driver);
    }

    public CartPage cartClick() {
        driver.findElement(cartButton).click();
        return new CartPage(driver);

    }

    public String getHeadingLogIn() {
        return driver.findElement(headingLogIn).getText();
    }

    public boolean usernameFieldisDisplayed() {
        return driver.findElement(usernameField).isDisplayed();
    }

    public boolean passwordFieldisDisplayed() {
        return driver.findElement(passwordField).isDisplayed();
    }

    public MainPage logInGreenClick() {
        driver.findElement(logInButtonGreen).click();
        return this;
    }

    public MainPage logInWithEmptyFields() {
        this.logInClick();
        this.logInGreenClick();
        return this;

    }

    public String getTextErrorUsername() {
        return driver.findElement(errorUsername).getText();
    }

    public String getTextErrorPassword() {
        return driver.findElement(errorPassword).getText();
    }

    public MainPage enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public MainPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public MainPage enterWrongCreds(String username, String password) {
        this.logInClick();
        this.enterUsername(username);
        this.enterPassword(password);
        this.logInGreenClick();
        return this;
    }

    public String getTextErrorWrongCreds() {
        return driver.findElement(errorWrongCreds).getText();
    }

    public CataloguePage clickCatalogueButton() {
        driver.findElement(catalogueButton).click();
        return new CataloguePage(driver);
    }

    public boolean findSpecificItemInCatalogue(String itemName) {
        driver.findElement(searchField).sendKeys(itemName);
        return driver.findElement(resultWrapper).isDisplayed();
    }

    public boolean findMainLogo() {
        return driver.findElement(mainLogo).isDisplayed();
    }

    public ProfilePage goToProfile() {
        driver.findElement(logInButton).click();
        driver.findElement(signUp).click();
        return new ProfilePage(driver);
    }


}
