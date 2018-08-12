package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.RpnCalculateStrategy;
import lavender.airwallex.data.Element;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Description:test class for {@link UndoRpnCalculateStrategy}
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class UndoRpnCalculateStrategyTest extends BaseRpnCalculateStrategyTest {

    private static final RpnCalculateStrategy STRATEGY = new UndoRpnCalculateStrategy();

    @Test
    public void testUndoEmptyStack() {
        this.stack.clear();

        try {
            STRATEGY.calculate(this.stack);
            assertTrue(this.stack.isEmpty());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testUndoOperation() {
        final Element firstFactor = new Element(new Random().nextDouble());
        final Element secondFactor = new Element(new Random().nextDouble());
        final double result = new Random().nextDouble();

        // we assume that the result can be evaluated by certain means, how to evaluate is not the concerned here
        final Element element = new Element(result);
        element.addFactor(firstFactor);
        element.addFactor(secondFactor);
        this.stack.offerLast(element);

        STRATEGY.calculate(this.stack);

        assertEquals(3 + 2, this.stack.size());
        assertEquals(secondFactor, this.stack.pollLast());
        assertEquals(firstFactor, this.stack.pollLast());
    }
}