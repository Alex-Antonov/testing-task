package my.antonov.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by alex on 09.09.2016.
 */
public class TestDev extends BasicMathTest {

    public TestDev(String operand1, String operand2, String operation, String result) {
        super(operand1, operand2, operation, result);
    }

    @Parameterized.Parameters(name = "Test dev #{index} checks: {0} {2} {1} = {3}")
    public static Collection getTestData() throws IOException {
        return testSumData("/");
    }

    @Test
    public void testDev() throws ArithmeticException {
        int result = 0;

        if(Integer.parseInt(operand2) == 0)
            thrown.expect(ArithmeticException.class);
        result = basicMath.dev(Integer.parseInt(operand1), Integer.parseInt(operand2));
        Assert.assertTrue(result == Integer.parseInt(this.result));

    }
}
