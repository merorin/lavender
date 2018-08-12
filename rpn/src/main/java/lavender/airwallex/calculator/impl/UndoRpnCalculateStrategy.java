package lavender.airwallex.calculator.impl;

import lavender.airwallex.data.Element;
import lavender.airwallex.calculator.RpnCalculateStrategy;

import java.util.Deque;
import java.util.Optional;

/**
 * Description:The strategy to perform "undo" command
 *
 * TO BE CONFIRMED: is clear command can be undone?
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public final class UndoRpnCalculateStrategy implements RpnCalculateStrategy {

    @Override
    public void calculate(Deque<Element> stack) {
        Optional.ofNullable(stack.pollLast())
                .map(Element::getFactors)
                .ifPresent(deque -> this.undo(deque, stack));
    }

    /**
     * perform undo operation
     * @param factors the factors used to calculate the result which was being undoing.
     * @param stack the main stack of RPN calculator
     */
    private void undo(Deque<Element> factors, Deque<Element> stack) {
        while (!factors.isEmpty()) {
            Optional.ofNullable(factors.pollFirst()).ifPresent(stack::offerLast);
        }
    }
}
