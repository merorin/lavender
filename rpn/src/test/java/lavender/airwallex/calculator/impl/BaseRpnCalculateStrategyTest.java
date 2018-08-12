package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.RpnCalculateStrategy;
import lavender.airwallex.calculator.exception.RpnInsufficientParameterException;
import lavender.airwallex.data.Element;
import org.junit.Before;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Description:abstract test class for all rpn calculate strategy
 *
 * @author guobin On date 2018/8/12.
 * @version 1.0
 * @since jdk 1.8
 */
public abstract class BaseRpnCalculateStrategyTest {

    Deque<Element> stack;

    Element first, second, third;

    @Before
    public void setUp() {
        this.first = new Element(new Random().nextDouble());
        this.second = new Element(new Random().nextDouble());
        this.third = new Element(new Random().nextDouble());
        this.stack = new ArrayDeque<>(10);

        // do some preparation..
        this.stack.offerLast(this.first);
        this.stack.offerLast(this.second);
        this.stack.offerLast(this.third);
    }

    /**
     * just for convenience ...
     * @param strategy the calculate strategy
     */
    void testInsufficientParameters(RpnCalculateStrategy strategy) {
        // poll last twice so that there are insufficient parameters in stack.
        this.stack.pollLast();
        this.stack.pollLast();

        try {
            strategy.calculate(this.stack);
            // if the program execute the code below, there must be something wrong.
            fail();
        } catch (RpnInsufficientParameterException e) {
            assertEquals(1, stack.size());
            assertEquals(this.first, this.stack.getFirst());
        }
    }
}
