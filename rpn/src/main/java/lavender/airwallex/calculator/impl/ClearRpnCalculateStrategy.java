package lavender.airwallex.calculator.impl;

import lavender.airwallex.data.Element;
import lavender.airwallex.calculator.RpnCalculateStrategy;

import java.util.Deque;

/**
 * Description:The strategy to perform "clear" command.
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public final class ClearRpnCalculateStrategy implements RpnCalculateStrategy {

    @Override
    public void calculate(Deque<Element> stack) {
        stack.clear();
    }
}
