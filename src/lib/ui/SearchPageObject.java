package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private static final String
    searchInitElement = "xpath://*[contains(@text, 'Search Wikipedia')]",
    searchInput = "xpath://*[contains(@text, 'Search…')]",
    searchResultBySubstringTPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    searchCancelButton = "id:org.wikipedia:id/search_close_btn",
    searchResultElement = "xpath://*[@resource-id ='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    searchEmptyResultElement = "xpath://*[@text = 'No results found']",
    searchResultByTitleAndDescriptionsTPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/..//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']";

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
                searchResultXpath,
                "Cannot find element with title: '" + title+ "' and description: '" + description + "'",
                15);
    }

    public void initSearchInput(){
        this.waitForElementAndClick(
                searchInitElement,
                "Cannot find and click search init element (" +searchInitElement+")",
                5);

        this.waitForElementPresent(
                searchInitElement,
                "Cannot find search input after clicking search init element (" +searchInitElement+")",
                5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(
                searchCancelButton,
                "Cannot find search channel button!",
                5
        );
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(
                searchCancelButton,
                "Search channel button is still present",
                5
        );
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(searchCancelButton,
                "Cannot find and click cancel search button",
                5);

    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(
                searchInput,
                searchLine,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring){

        String searchResultXpath = getResultSearchElement(substring);

        this.waitForElementPresent(
                searchResultXpath,
                "Cannot find search result with substring "+substring );
    }

    public void clickByArticleWithSubstring(String substring){

        String searchResultXpath = getResultSearchElement(substring);

        this.waitForElementAndClick(
                searchResultXpath,
                "Cannot find and click search result with substring "+substring,
                10 );
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                searchResultElement,
                "Cannot find anything be request ",
                15
        );
        return this.getAmountOfElement(searchResultElement);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                searchEmptyResultElement,
                "Cannot find empty result element",
                15
        );
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                searchResultElement,
                "We supposed not to find any result"
        );
    }

}
