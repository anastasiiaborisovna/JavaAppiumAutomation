import org.junit.Assert;
import org.junit.Test;


public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        int actual = new MainClass().getLocalNumber();

        Assert.assertFalse("Method getLocalNumber() does not return 14",actual!=expected);
    }
}
