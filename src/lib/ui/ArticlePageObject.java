package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            title = "org.wikipedia:id/view_page_title_text",
            footerElement = "//*[@text='View page in browser']",
            optionsButton = "//android.widget.ImageView[@content-desc = 'More options']",
            optionAddToMyListButton = "//*[@text = 'Add to reading list']",
            addToMyListOverlay = "org.wikipedia:id/onboarding_button",
            myListNameInput = "org.wikipedia:id/text_input",
            myListOkButton = "//*[@text = 'OK']",
            closeArticleButton = "//android.widget.ImageButton[@content-desc = 'Navigate up']",
            articleTitle = "org.wikipedia:id/view_page_header_container";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(title),
                "Cannot find article title on page!",
                15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(footerElement),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String nameOfFolder) {

        this.waitForElementAndClick(
                By.xpath(optionsButton),
                "Cannot find button 'More options'",
                5
        );

        this.waitForElementAndClick(
                By.xpath(optionAddToMyListButton),
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                By.id(addToMyListOverlay),
                "Cannot find 'Got it' tip overlay",
                10
        );

        this.waitForElementAndClear(
                By.id(myListNameInput),
                "Cannot find input to set name of article list",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(myListNameInput),
                nameOfFolder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(myListOkButton),
                "Cannot press OK button",
                5
        );

    }

    public void addSecondArticleToMyList(String nameOfFolder){

        this.waitForElementAndClick(
                By.xpath(optionsButton),
                "Cannot find button 'More options'",
                5
        );

        this.waitForElementAndClick(
                By.xpath(optionAddToMyListButton),
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find name of Folder " + nameOfFolder,
                5
        );

    }

    public void presentArticleTitle() {
        this.assertElementPresent(
                By.id(articleTitle),
                "No title on the page 'Object-oriented programming language'");
    }

    public void closeArticle() {

            this.waitForElementAndClick(
            By.xpath(closeArticleButton),
                "Cannot close article, cannot find X link",
                    5);
}
}
