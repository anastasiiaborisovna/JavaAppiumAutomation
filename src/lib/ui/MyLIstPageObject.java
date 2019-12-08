package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyLIstPageObject extends MainPageObject {

    public static final String
    folderByNameTPL = "//*[@text = '{folderName}']",
    articleByTitleTPL = "//*[@text = '{title}']";

    private static String getFolderXpathByName(String nameOfFolder){

        return folderByNameTPL.replace("{folderName}", nameOfFolder);

    }

    private static String getSavedArticleXpathByTitle(String articleTitle){

        return articleByTitleTPL.replace("{title}", articleTitle);

    }

    public MyLIstPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder){

        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(
                By.xpath(folderNameXpath),
                "Cannot find folder by name "+ nameOfFolder,
                10
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle){
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementPresent(
                By.xpath(articleXpath),
                "Cannot find saved article by title " + articleTitle,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String articleTitle){
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementNotPresent(
                By.xpath(articleXpath),
                "Saved article still present with title " + articleTitle,
                15
        );
    }

    public void swipeByArticleToDelete(String articleTitle){

        this.waitForArticleToAppearByTitle(articleTitle);

        String articleXpath = getFolderXpathByName(articleTitle);
        this.swipeElementToLeft(
                By.xpath(articleXpath),
                "Cannot fihd saved article"
        );
        this.waitForArticleToDisappearByTitle(articleTitle);
    }
}
