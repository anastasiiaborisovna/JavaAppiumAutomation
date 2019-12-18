package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

    private static final String
     myListLink = "xpath://android.widget.FrameLayout[@content-desc = 'My lists']";


    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList(){
        this.waitForElementAndClick(
                myListLink,
                "Cannot find navigation button to My Lists",
                5
        );
    }
}
