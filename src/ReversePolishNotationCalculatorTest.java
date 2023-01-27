import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReversePolishNotationCalculatorTest {
    ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        int sum = calculator.calculatePolishNotation("1 2 +");
        Assertions.assertEquals(3, sum);
    }
    @Test
    public void shouldCalculateSubtraction() {
        int subtr = calculator.calculatePolishNotation("2 1 -");
        Assertions.assertEquals(1, subtr);
    }
    @Test
    public void shouldCalculateSubtractionNegativeResult() {
        int subtrNeg = calculator.calculatePolishNotation("-1 2 -");
        Assertions.assertEquals(-3, subtrNeg);
    }
    @Test
    public void shouldCalculateMultiplication() {
        int mult = calculator.calculatePolishNotation("2 2    *");
        Assertions.assertEquals(4, mult);
    }

}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}