package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.AbstractRpnCalculateBiFunctionStrategy;

/**
 * Description:The strategy to perform "*" command.
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public final class MultiplyRpnCalculateStrategy extends AbstractRpnCalculateBiFunctionStrategy {

    @Override
    protected double doCalculation(double first, double second) {
        return first * second;
    }
}
