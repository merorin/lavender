package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.RpnCalculateStrategy;
import lavender.airwallex.calculator.exception.RpnInsufficientParameterException;
import lavender.airwallex.data.Element;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description:the test class for {@link SqrtRpnCalculateStrategy}
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class SqrtRpnCalculateStrategyTest extends BaseRpnCalculateStrategyTest {

    private static final RpnCalculateStrategy STRATEGY = new SqrtRpnCalculateStrategy();

    @Test
    public void testInsufficientParameters() {
        // clear the stack so that there are insufficient parameters in stack.
        this.stack.clear();

        try {
            STRATEGY.calculate(this.stack);
            // if the program execute the code below, there must be something wrong.
            fail();
        } catch (RpnInsufficientParameterException e) {
            assertTrue(this.stack.isEmpty());
        }
    }

    @Test
    public void testNormalSqrt() {
        final double root = Math.sqrt(this.third.getValue());
        final Element expected = new Element(root);
        expected.addFactor(this.third);
        STRATEGY.calculate(this.stack);

        assertEquals(3, this.stack.size());
        assertEquals(expected, this.stack.getLast());
        assertEquals(this.first, this.stack.pollFirst());
        assertEquals(this.second, this.stack.pollFirst());
    }
}