package lavender.airwallex.calculator;

import lavender.airwallex.calculator.exception.RpnInsufficientParameterException;
import lavender.airwallex.data.Element;

import java.util.Deque;
import java.util.Optional;

/**
 * Description:Abstract implement for {@link RpnCalculateStrategy}
 *
 * Use Template Design Pattern here.
 *
 * The common behavior shows below:
 *
 * 1. poll two elements from stack according to the command type.
 * 2. perform calculation.
 * 3. push the result calculated by step 2 into the stack.
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public abstract class AbstractRpnCalculateBiFunctionStrategy implements RpnCalculateStrategy {

    @Override
    public final void calculate(Deque<Element> stack) {
        // second is the latter one in the stack
        final Element second = Optional.ofNullable(stack.pollLast()).orElseThrow(RpnInsufficientParameterException::new);
        final Element first = stack.pollLast();
        if (first == null) {
            // Insufficient parameters, put the second parameter back into the stack.
            stack.offerLast(second);
            throw new RpnInsufficientParameterException();
        }

        final double result = this.doCalculation(first.getValue(), second.getValue());
        final Element element = new Element(result);
        element.addFactor(first);
        element.addFactor(second);

        stack.offerLast(element);
    }


    /**
     * actually perform the evaluate action. Implemented by subclass
     * @param first the first parameter
     * @param second the second parameter
     * @return the result
     */
    protected abstract double doCalculation(double first, double second);
}
