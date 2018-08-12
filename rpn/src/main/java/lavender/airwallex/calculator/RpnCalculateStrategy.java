package lavender.airwallex.calculator;

import lavender.airwallex.data.Element;

import java.util.Deque;

/**
 * Description:The strategy of rpn calculating.
 * The user of this interface can evaluate the result according to the command.
 *
 * @author guobin On date 2018/8/7.
 * @version 1.0
 * @since jdk 1.8
 */
public interface RpnCalculateStrategy {

    /**
     * perform calculate operation
     * @param stack the stack on which the calculation performs
     */
    void calculate(Deque<Element> stack);
}
