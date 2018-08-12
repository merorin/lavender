package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.RpnCalculateStrategy;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description:test class for {@link ClearRpnCalculateStrategy}
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class ClearRpnCalculateStrategyTest extends BaseRpnCalculateStrategyTest {

    private static final RpnCalculateStrategy STRATEGY = new ClearRpnCalculateStrategy();

    @Test
    public void testClear() {
        assertFalse(this.stack.isEmpty());

        STRATEGY.calculate(this.stack);

        assertTrue(this.stack.isEmpty());
    }
}