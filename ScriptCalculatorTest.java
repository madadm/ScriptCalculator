import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScriptCalculatorTest {
    @Test
    public void  testResult() throws Exception {
        String res = ScriptCalculator.result(new String[]{"one","multiply","one", "plus", "three-two", "multiply", "one-one"});
        assertEquals("1*1+32*11", res);
        res = ScriptCalculator.calcString("1*1+32*11");
        assertEquals("353", res);

    }
}