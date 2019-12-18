
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.URL;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CoreTestCase extends TestCase {
    private static final String platformIos = "ios";
    private static final String platformAndroid = "android";
    private static final String appiumUrl = "http://127.0.0.1:4723/wd/hub";
    protected AppiumDriver driver;

    public CoreTestCase() {
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.driver = this.getDriver();
        this.rotateScreenPortrait();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        this.driver.quit();
    }

    protected void rotateScreenPortrait() {
        this.driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        this.driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        this.driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals("android")) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/a.naumova/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else {
            if (!platform.equals("ios")) {
                throw new Exception("Cannot get run platform from ENV variable. Platform value " + platform);
            }

            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.0");
            capabilities.setCapability("app", "/Users/a.naumova/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        }

        return capabilities;
    }

    private AppiumDriver getDriver() throws Exception {
        URL URL = new URL("http://127.0.0.1:4723/wd/hub");
        if (System.getenv("PLATFORM").equals("android")) {
            return new AndroidDriver(URL, this.getCapabilitiesByPlatformEnv());
        } else if (System.getenv("PLATFORM").equals("ios")) {
            return new IOSDriver(URL, this.getCapabilitiesByPlatformEnv());
        } else {
            throw new Exception("Cannot identify type of driver");
        }
    }
}
