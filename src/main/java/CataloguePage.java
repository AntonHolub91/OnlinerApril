import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CataloguePage {
    WebDriver driver;

    public CataloguePage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//div[@class='catalog-navigation catalog-navigation_opened']//div[contains(text(),'Каталог')]");
    private By electronicLink = By.xpath("//span[text()='Электроника']");
    private By computersLink = By.xpath("//span[contains(text(),'Компьютеры и')]");
    private By teqnique = By.xpath("//span[text()='Бытовая техника']");
    private By mobilePhoneLink = By.xpath("//div[contains(text(),' Мобильные телефоны')]");
    private By smartphoneLink = By.xpath("//*[@id=\"container\"]/div/div/div/div/div[1]/div[4]/div/div[2]/div[1]/div/div[1]/div[2]/div/a[1]");
    private By link1 = By.xpath("//*[@id=\"schema-products\"]/div[2]/div/div[3]/div[2]/div[1]/a");
    private By allProposals = By.xpath("//*[@id=\"container\"]/div/div/div/div/div[2]/div[1]/main/div/div/div[1]/div[2]/div[5]/div[2]/div/a[1]");
    private By inCart1 = By.xpath("//*[@id=\"container\"]/div/div/div/div/div[2]/div[1]/main/div/div/aside/div[1]/div[3]/div[1]/div[1]/div[4]/a[2]");





    public String getTextHeadingCatalogue(){
        return driver.findElement(heading).getText();
    }

    public CataloguePage goToElectronic(){
        driver.findElement(electronicLink).click();
        return this;
    }

    public boolean mobileIsDisplayed(){
        return driver.findElement(mobilePhoneLink).isDisplayed();
    }

    public CataloguePage goToMobilePhones(){
        driver.findElement(mobilePhoneLink).click();
        return this;
    }

//    public CataloguePage goToSmartphones(){
//        goToElectronic();
//        WebElement link = driver.findElement(By.xpath("//div[contains(text(),' Мобильные телефоны')]"));
//        Actions action = new Actions(driver);
//        action.moveToElement(link).build().perform();
//        driver.findElement(smartphoneLink).click();
//        driver.findElement(link1);
////        driver.findElement(allProposals);
//        driver.findElement(inCart1);
//        return new CataloguePage(driver);
//    }






}
