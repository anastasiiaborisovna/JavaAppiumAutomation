import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

public class AssertTitle {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/a.naumova/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {

        driver.quit();
    }

    /*
Ex6: Тест: assert title
Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
 */

    @Test
    public void assertTitle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not find element Search Wikipedia",
                5);

        String searchLine = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Can not find element Search",
                5);

        String searchResultLocator = "//*[@class='android.widget.LinearLayout']//*[@text='Object-oriented programming language']";

        waitForElementAndClick(
                By.xpath(searchResultLocator),
                "Can not find 'Object-oriented programming language' topic searching by " + searchLine,
                5);

        String articleTitle = "org.wikipedia:id/view_page_header_container";

        assertElementPresent(
                By.id(articleTitle),
                "No title on the page 'Object-oriented programming language'");
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds ) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return  element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds ) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return  element;
    }

    private int getAmountOfElement(By by) {
        List elements = driver.findElements(by);
        return elements.size();

    }

    private void assertElementPresent(By by, String errorMessage ) {
        int amountOfElements = getAmountOfElement(by);
        if (amountOfElements == 0) {
            String defaultMessage = " An element '" + by.toString() + "' not found";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }
}