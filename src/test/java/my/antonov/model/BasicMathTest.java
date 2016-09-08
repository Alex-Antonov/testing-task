package my.antonov.model;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
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
public abstract class BasicMathTest extends TestCase {

    private final static String CSV_DATA_PATH = "src/test/resources/basic-math-data.csv";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameter("operand1")
    protected String operand1;

    @Parameter("operand2")
    protected String operand2;

    @Parameter("operation")
    protected String operation;

    @Parameter("result")
    protected String result;

    protected BasicMath basicMath;


    public BasicMathTest(String operand1, String operand2,
                         String operation, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
        basicMath = new BasicMath();
    }



    public static Collection testSumData(String operation) throws IOException {
        return getTestData(CSV_DATA_PATH, operation);
    }


    private static Collection<String[]> getTestData(String fileName, String operation)
            throws IOException {
        List<String[]> records = new ArrayList<String[]>();
        String record;
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while ((record = file.readLine()) != null) {
            String fields[] = record.split(";");
                if(operation.equals(fields[2]))
                    records.add(fields);
        }
        file.close();
        return records;
    }

//    @Test
//    public abstract void testMathMethod();

}
