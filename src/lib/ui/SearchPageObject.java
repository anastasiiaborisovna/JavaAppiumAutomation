package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
    searchInitElement = "//*[contains(@text, 'Search Wikipedia')]",
    searchInput = "//*[contains(@text, 'Search…')]",
    searchResultBySubstringTPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    searchCancelButton = "org.wikipedia:id/search_close_btn",
    searchResultElement = "//*[@resource-id ='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    searchEmptyResultElement = "//*[@text = 'No results found']",
    searchResultByTitleAndDescriptionsTPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/..//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring) {
        return searchResultBySubstringTPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElements(String title, String description) {
        return searchResultByTitleAndDescriptionsTPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /*TEMPLATE METHODS*/

    public void waitForElementByTitleAndDescription(String title, String description) {
        String searchResultXpath = getResultSearchElements(title, description);
        this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Cannot find element with title: '" + title+ "' and description: '" + description + "'",
                15);
    }

    public void initSearchInput(){
        this.waitForElementAndClick(
                By.xpath(searchInitElement),
                "Cannot find and click search init element (" +searchInitElement+")",
                5);

        this.waitForElementPresent(
                By.xpath(searchInitElement),
                "Cannot find search input after clicking search init element (" +searchInitElement+")",
                5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(
                By.id(searchCancelButton),
                "Cannot find search channel button!",
                5
        );
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(
                By.id(searchCancelButton),
                "Search channel button is still present",
                5
        );
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(searchCancelButton),
                "Cannot find and click cancel search button",
                5);

    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(
                By.xpath(searchInput),
                searchLine,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring){

        String searchResultXpath = getResultSearchElement(substring);

        this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Cannot find search result with substring "+substring );
    }

    public void clickByArticleWithSubstring(String substring){

        String searchResultXpath = getResultSearchElement(substring);

        this.waitForElementAndClick(
                By.xpath(searchResultXpath),
                "Cannot find and click search result with substring "+substring,
                10 );
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                By.xpath(searchResultElement),
                "Cannot find anything be request ",
                15
        );
        return this.getAmountOfElement(By.xpath(searchResultElement));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                By.xpath(searchEmptyResultElement),
                "Cannot find empty result element",
                15
        );
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                By.xpath(searchResultElement),
                "We supposed not to find any result"
        );
    }

}
