package my.antonov.model;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alex on 31.08.2016.
 */
@Features("Testing basic math operations")
@RunWith(value = Parameterized.class)
public class BasicMathTest {

    private final static String CSV_DATA_PATH = "src/test/resources/basic-math-data.csv";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameter("operand1")
    private String operand1;

    @Parameter("operand2")
    private String operand2;

    @Parameter("operation")
    private String operation;

    @Parameter("result")
    private String result;

    private BasicMath basicMath;


    public BasicMathTest(String operand1, String operand2,
                         String operation, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
        basicMath = new BasicMath();
    }

    @Parameterized.Parameters(name = "Test #{index} checks: {0} {2} {1} = {3}")
    public static Collection testData() throws IOException {
        return getTestData(CSV_DATA_PATH);
    }

    private static Collection<String[]> getTestData(String fileName)
            throws IOException {
        List<String[]> records = new ArrayList<String[]>();
        String record;
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while ((record = file.readLine()) != null) {
            String fields[] = record.split(";");
            records.add(fields);
        }
        file.close();
        return records;
    }

    @Test
    @Description("test math operations")//(expected = IllegalArgumentException.class)
    public void testBasicMathFunctions() throws ArithmeticException {
        int result = 0;
        int actualResult = 0;

        switch(operation) {
            case "+":
                result = basicMath.sum(Integer.parseInt(operand1), Integer.parseInt(operand2));
                break;
            case "-":
                result = basicMath.dif(Integer.parseInt(operand1), Integer.parseInt(operand2));
                break;
            case "/":
                if(Integer.parseInt(operand2) == 0)
                    thrown.expect(ArithmeticException.class);
                result = basicMath.dev(Integer.parseInt(operand1), Integer.parseInt(operand2));
                break;
            case "*":
                result = basicMath.mul(Integer.parseInt(operand1), Integer.parseInt(operand2));
                break;
            default:
                Assert.fail("Illegal test operator: " + operation + " is not basic math operation");
        }
        

        try {
            actualResult = Integer.parseInt(this.result);
            Assert.assertTrue(actualResult == result);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Illegal value of actual testing result");
        }

    }

}
