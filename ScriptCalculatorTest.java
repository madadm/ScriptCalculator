import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScriptCalculatorTest {
    @Test
    public void testResult(){
        String res;
        try {
            res = ScriptCalculator.result(new String[]{"one", "multiply", "one", "plus", "three-two", "multiply", "one-one"});
            assertEquals("1*1+32*11", res);

            res = ScriptCalculator.result(new String[]{"minus", "one", "multiply", "five", "plus", "six-two"});
            assertEquals("-1*5+62", res);

            res = ScriptCalculator.result(new String[]{"minus", "one", "multiply", "minus", "five", "plus", "six-two"});
            assertEquals("-1*-5+62", res);

            res = ScriptCalculator.result(new String[]{"minus", "one", "multiply", "curly-brace-open", "minus", "five", "plus", "six-two", "curly-brace-close"});
            assertEquals("-1*(-5+62)", res);

            res = ScriptCalculator.result(new String[]{"curly-brace-open", "minus", "one", "multiply", "curly-brace-open", "minus", "five", "plus", "six-two", "curly-brace-close", "curly-brace-close", "multiply", "zero"});
            assertEquals("(-1*(-5+62))*0", res);

            res = ScriptCalculator.calcString("1*1+32*11");
            assertEquals("353", res);

            res = ScriptCalculator.calcString("-1*-5*62");
            assertEquals("310", res);

            res = ScriptCalculator.calcString("(-1*(-5*62))/0");
            assertEquals("Infinity", res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}