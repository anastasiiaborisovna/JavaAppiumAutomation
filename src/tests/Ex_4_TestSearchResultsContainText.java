package tests;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;


public class Ex_4_TestSearchResultsContainText extends CoreTestCase {

    @Test
        public void testSearchResultsContainText() {

            SearchPageObject SearchPageObject = new SearchPageObject(driver);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.waitForElementByTitleAndDescription("Java version history", "Wikimedia list article");
            SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
            SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
    }
}
