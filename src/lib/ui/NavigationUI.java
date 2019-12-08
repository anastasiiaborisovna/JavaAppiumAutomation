package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
     myListLink = "//android.widget.FrameLayout[@content-desc = 'My lists']";


    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList(){
        this.waitForElementAndClick(
                By.xpath(myListLink),
                "Cannot find navigation button to My Lists",
                5
        );

    }




}
