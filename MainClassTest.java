import org.junit.Assert;
import org.junit.Test;


public class MainClassTest {

    @Test
    public void testGetClassString() {

        String condition = new MainClass().getClassString();

        Assert.assertTrue("The method does not contain substring \"Hello\" OR \"hello\"", condition.contains("hello") || condition.contains("Hello"));


    }
}


//метод getClassString возвращает строку, в которой есть подстрока “hello” или “Hello”