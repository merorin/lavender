package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.RpnCalculateStrategy;
import lavender.airwallex.data.Element;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description:the test class for {@link SubtractRpnCalculateStrategy}
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class SubtractRpnCalculateStrategyTest extends BaseRpnCalculateStrategyTest {

    private static final RpnCalculateStrategy STRATEGY = new SubtractRpnCalculateStrategy();

    @Test
    public void testInsufficientParameters() {
        this.testInsufficientParameters(STRATEGY);
    }

    @Test
    public void testNormalSubtract() {
        final Element element = new Element(this.second.getValue() - this.third.getValue());
        element.addFactor(this.second);
        element.addFactor(this.third);

        STRATEGY.calculate(this.stack);
        assertEquals(2, this.stack.size());
        assertEquals(element, this.stack.getLast());
        assertEquals(this.first, this.stack.getFirst());
    }

}