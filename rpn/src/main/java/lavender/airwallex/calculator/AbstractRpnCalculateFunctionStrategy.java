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
 * 1. poll one elements from stack according to the command type.
 * 2. perform calculation.
 * 3. push the result calculated by step 2 into the stack.
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public abstract class AbstractRpnCalculateFunctionStrategy implements RpnCalculateStrategy {

    @Override
    public final void calculate(Deque<Element> stack) {
        final Element parameter = Optional.ofNullable(stack.pollLast()).orElseThrow(RpnInsufficientParameterException::new);

        final double result = this.doCalculation(parameter.getValue());
        final Element element = new Element(result);
        element.addFactor(parameter);

        stack.offerLast(element);
    }

    /**
     * actually perform the evaluate action. Implemented by subclass
     * @param parameter the first parameter
     * @return the result
     */
    protected abstract double doCalculation(double parameter);
}
