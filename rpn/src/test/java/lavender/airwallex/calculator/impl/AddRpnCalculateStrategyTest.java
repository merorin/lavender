package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.RpnCalculateStrategy;
import lavender.airwallex.data.Element;
import org.junit.Assert;
import org.junit.Test;

/**
 * Description:The test class for {@link AddRpnCalculateStrategy}
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class AddRpnCalculateStrategyTest extends BaseRpnCalculateStrategyTest {

    private static final RpnCalculateStrategy STRATEGY = new AddRpnCalculateStrategy();

    @Test
    public void testInsufficientParameters() {
        this.testInsufficientParameters(STRATEGY);
    }

    @Test
    public void testNormalAdditionForMore() {

        final Element expected = new Element(this.second.getValue() + this.third.getValue());
        expected.addFactor(this.second);
        expected.addFactor(this.third);

        STRATEGY.calculate(this.stack);
        Assert.assertEquals(2, this.stack.size());
        Assert.assertEquals(expected, this.stack.getLast());
        Assert.assertEquals(this.first, this.stack.getFirst());
    }
}