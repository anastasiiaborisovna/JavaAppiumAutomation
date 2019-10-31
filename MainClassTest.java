import org.junit.Assert;
import org.junit.Test;


public class MainClassTest {

    @Test
    public void testGetClassNumber() {
        int expected = 45;
        int actual = new MainClass().getClassNumber();
        Assert.assertFalse("Method getClassNumber() return "+ actual + ", " + actual+ " less than "+ expected,actual<expected);

    }
}
