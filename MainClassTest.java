import org.junit.Assert;
import org.junit.Test;


public class MainClassTest {

    MainClass objClass = new MainClass();

    @Test
    public void testGetLocalNumber()
    {
        int expected = 14;
        int actual = objClass.getLocalNumber();

        Assert.assertFalse("Number !=14",actual!=expected);
    }
}
