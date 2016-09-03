package my.antonov.model;

/**
 * Created by alex on 31.08.2016.
 */
public class BasicMath {

    public int sum(int operand1, int operand2) {
        return operand1 + operand2;
    }

    public int dif(int operand1, int operand2) {
        return operand1 - operand2;
    }

    public int mul(int operand1, int operand2) {
        return operand1 * operand2;
    }

    public int dev(int operand1, int operand2) {
        //if(operand2 == 0)  throw new IllegalArgumentException();
        return operand1 / operand2;
    }
}
