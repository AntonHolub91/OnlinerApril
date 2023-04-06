import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//div[contains(text(),'Регистрация')]");
    private By emailField = By.xpath("//input[@type='email']");
    private By passwordField = By.xpath("//input[@placeholder='Придумайте пароль']");
    private By repeatPasswordField = By.xpath("//input[@placeholder='Повторите пароль']");
    private By checkbox = By.xpath("//span[@class='i-checkbox__faux']");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By errorIncorrectEmail = By.xpath("//div[contains(text(),'Некорректный e-mail')]");
    private By errorEmptyEmail = By.xpath("//div[contains(text(),'Укажите e-mail')]");
    private By errorEmptyPassword = By.xpath("//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[6]/div/div/div[2]/div");
    private By errorEmptyRepeatPassword = By.xpath("//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[8]/div/div/div[2]/div");
    private By errorPasswordLength = By.xpath("//div[contains(text(),'Пароль должен быть от 8 до 64 символов')]");
    private By errorDifferentPasswords = By.xpath("//div[contains(text(),'Пароли не совпадают')]");
    private By errorCheckbBoxIsNotSelected = By.xpath("//div[contains(text(),'Для регистрации аккаунта')]");

    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public ProfilePage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return new ProfilePage(driver);
    }

    public ProfilePage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return new ProfilePage(driver);
    }

    public ProfilePage typeRepeatPassword(String repeatPassword) {
        driver.findElement(repeatPasswordField).sendKeys(repeatPassword);
        return new ProfilePage(driver);
    }

    public ProfilePage selectCheckbox() {
        driver.findElement(checkbox).click();
        return new ProfilePage(driver);
    }

    public ProfilePage submitForm() {
        driver.findElement(submitButton).click();
        return new ProfilePage(driver);
    }

    public ProfilePage emptyForm() {
        this.typeEmail("");
        this.typePassword("");
        this.typeRepeatPassword("");
        this.submitForm();
        return new ProfilePage(driver);
    }

    public String emptyEmail() {
        return driver.findElement(errorEmptyEmail).getText();
    }

    public String incorrectEmail() {
        return driver.findElement(errorIncorrectEmail).getText();
    }

    public String emptyPassword() {
        return driver.findElement(errorEmptyPassword).getText();
    }

    public String emptyRepeatPassword() {
        return driver.findElement(errorEmptyRepeatPassword).getText();
    }

    public String shortPasswordError() {
        return driver.findElement(errorPasswordLength).getText();
    }

    public String differentPasswordsErrorGetText() {
        return driver.findElement(errorDifferentPasswords).getText();
    }

    public String checkboxIsNotSelectedError() {
        return driver.findElement(errorCheckbBoxIsNotSelected).getText();
    }


}
