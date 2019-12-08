package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyLIstPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SaveAndDeleteArticleInMyList extends CoreTestCase {

    /*
Ex5: Тест: сохранение двух статей
Написать тест, который:

1. Сохраняет две статьи в одну папку

2. Удаляет одну из статей

3. Убеждается, что вторая осталась

4. Переходит в неё и убеждается, что title совпадает
     */
    @Test
    public void testSaveAndDeleteArticleToMyList() {
        //1

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByAricleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String articleTitle = ArticlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        ArticlePageObject.addArticleToMyList(nameOfFolder);
        ArticlePageObject.closeArticle();

        //2

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.clickByAricleWithSubstring("General-purpose, high-level programming language");
        ArticlePageObject.addSecondArticleToMyList(nameOfFolder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyList();

        MyLIstPageObject MyLIstPageObject = new MyLIstPageObject(driver);
        MyLIstPageObject.openFolderByName(nameOfFolder);
        MyLIstPageObject.swipeByArticleToDelete(articleTitle);
    }
}

