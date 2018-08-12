package lavender.airwallex.calculator.impl;

import lavender.airwallex.calculator.AbstractRpnCalculateFunctionStrategy;

/**
 * Description: The strategy to perform "sqrt" command.
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public final class SqrtRpnCalculateStrategy extends AbstractRpnCalculateFunctionStrategy {

    @Override
    protected double doCalculation(double parameter) {
        return Math.sqrt(parameter);
    }
}
