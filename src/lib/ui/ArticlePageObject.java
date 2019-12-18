package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            title = "id:org.wikipedia:id/view_page_title_text",
            footerElement = "xpath://*[@text='View page in browser']",
            optionsButton = "xpath://android.widget.ImageView[@content-desc = 'More options']",
            optionAddToMyListButton = "xpath://*[@text = 'Add to reading list']",
            addToMyListOverlay = "id:org.wikipedia:id/onboarding_button",
            myListNameInput = "id:org.wikipedia:id/text_input",
            myListOkButton = "xpath://*[@text = 'OK']",
            closeArticleButton = "xpath://android.widget.ImageButton[@content-desc = 'Navigate up']",
            articleTitle = "id:org.wikipedia:id/view_page_header_container",
            itemTitleOfFolder ="id:org.wikipedia:id/item_title";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(title,
                "Cannot find article title on page!",
                15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                footerElement,
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String nameOfFolder) {

        this.waitForElementAndClick(
                optionsButton,
                "Cannot find button 'More options'",
                5
        );

        this.waitForElementAndClick(
               optionAddToMyListButton,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                addToMyListOverlay,
                "Cannot find 'Got it' tip overlay",
                10
        );

        this.waitForElementAndClear(
                myListNameInput,
                "Cannot find input to set name of article list",
                5
        );

        this.waitForElementAndSendKeys(
                myListNameInput,
                nameOfFolder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                myListOkButton,
                "Cannot press OK button",
                5
        );

    }

    public void addSecondArticleToMyList(String nameOfFolder){

        this.waitForElementAndClick(
                optionsButton,
                "Cannot find button 'More options'",
                5
        );

        this.waitForElementAndClick(
               optionAddToMyListButton,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                itemTitleOfFolder,
                "Cannot find name of Folder " + nameOfFolder,
                5
        );

    }

    public void presentArticleTitle() {
        this.assertElementPresent(
                articleTitle,
                "No title on the page 'Object-oriented programming language'");
    }

    public void closeArticle() {

            this.waitForElementAndClick(
            closeArticleButton,
                "Cannot close article, cannot find X link",
                    5);
}
}
