package lavender.airwallex.calculator;

import lavender.airwallex.calculator.exception.RpnNoSuchStrategyDefinedException;
import lavender.airwallex.config.RpnStrategyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:The factory of Rpn calculator
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class RpnCalculatorFactory {

    private static final Logger LOG = LoggerFactory.getLogger(RpnCalculatorFactory.class);

    /**
     * Should not be an instance
     */
    private RpnCalculatorFactory() {}

    private static final Map<String, RpnCalculateStrategy> STRATEGY_POOL = new HashMap<>(16);

    /**
     * get strategy according to the operator
     * @param operator operator name
     * @return strategy
     */
    public static RpnCalculateStrategy getStrategy(String operator) {
        return STRATEGY_POOL.computeIfAbsent(operator, RpnCalculatorFactory::createNewStrategyInstance);
    }

    /**
     * create a new instance of strategy
     * @param operator operator name
     * @param <T> type of strategy
     * @return strategy
     */
    @SuppressWarnings("unchecked")
    private static <T extends RpnCalculateStrategy> T createNewStrategyInstance(String operator) {
        return RpnStrategyConfig.getInstance().getClassName(operator).map(name -> {
            T strategy = null;
            try {
                final Class<?> clazz = Class.forName(name);
                strategy = (T) clazz.newInstance();
            } catch (Exception e) {
                LOG.error("Cannot create a new instance for class name," + name, e);
            }
            return strategy;
        }).orElseThrow(RpnNoSuchStrategyDefinedException::new);
    }
}
