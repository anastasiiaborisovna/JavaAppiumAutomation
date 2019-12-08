package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex_6_AssertTitle extends CoreTestCase {
    /*
Ex6: Тест: assert title
Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
 */
    @Test
    public void testAssertTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByAricleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
    }

}