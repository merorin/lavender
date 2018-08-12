package lavender.airwallex.calculator.exception;

/**
 * Description:The exception of rpn
 *
 * Usually thrown when the strategy desired is not defined in the application
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class RpnNoSuchStrategyDefinedException extends RuntimeException {

    public RpnNoSuchStrategyDefinedException() {
    }

    public RpnNoSuchStrategyDefinedException(String message) {
        super(message);
    }
}
