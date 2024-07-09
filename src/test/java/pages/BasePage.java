package pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

    protected static WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    Actions actions=new Actions(driver);
 

    static {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");       // Deshabilita barras de información
        options.addArguments("--disable-notifications");   // Deshabilita notificaciones
        options.addArguments("--mute-audio");   
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-geolocation");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        
    }
 

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }
    private List<WebElement> Finds(String locator){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }
 
    public void clickElement(String locator){
        Find(locator).click();
    }
    public void waitInicial(int time){
        actions.pause(time).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
    }
    public void clickElementButton(String locator,int time){
  
        actions.pause(time).moveToElement(Find(locator)).pause(time).click().perform();

    }
  
    public String textElement(String locator){
        return Find(locator).getText();
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public static void maximizeBrowser(){
        driver.manage().window().maximize();
    }

    public void write(String locator, String keysToSend){
        Find(locator).clear();
        Find(locator).sendKeys(keysToSend);
    }

    public int cantValues(String locator){
        List<WebElement> listElements= Finds(locator);

        return listElements.size();
    }
    

 
}