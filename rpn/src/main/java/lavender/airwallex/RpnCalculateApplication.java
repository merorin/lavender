package lavender.airwallex;

import lavender.airwallex.calculator.RpnCalculatorFactory;
import lavender.airwallex.calculator.exception.RpnInsufficientParameterException;
import lavender.airwallex.calculator.exception.RpnNoSuchStrategyDefinedException;
import lavender.airwallex.config.RpnStrategyConfig;
import lavender.airwallex.data.Element;
import lavender.airwallex.data.RpnStack;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Deque;
import java.util.Scanner;

/**
 * Description: command-line based RPN calculator
 *
 * This is the main class.
 *
 * @author guobin On date 2018/8/7.
 * @version 1.0
 * @since jdk 1.8
 */
@SpringBootApplication
public class RpnCalculateApplication {

    private static final int DEFAULT_STACK_CAPACITY = 10;

    private static final String INSUFFICIENT_MSG_FORMAT = "Operator %s (position:%d): insufficient parameters";

    private static final String TERMINAL_MSG_FORMAT = "(the latter command '%s' cannot be proceeded on the stack due to previous error)";

    private static final DecimalFormat DISPLAY_DECIMAL_FORMAT;

    private static final char SPACE = ' ';

    static {
        // According to the example 2, we need to round down.
        DISPLAY_DECIMAL_FORMAT = new DecimalFormat("#.##########");
        DISPLAY_DECIMAL_FORMAT.setRoundingMode(RoundingMode.DOWN);
    }


    public static void main(String[] args) {
        System.out.println("Please input the command to perform RPN calculation...");

        Deque<Element> stack = new RpnStack(DEFAULT_STACK_CAPACITY);

        boolean keepRunning = true;
        final Scanner scanner = new Scanner(System.in);
        while (keepRunning) {
            String str = scanner.nextLine().trim();
            final char[] chars = str.toCharArray();
            final StringBuilder sb = new StringBuilder();

            int position = 0;
            try {
                while (position < chars.length) {
                    final char c = chars[position++];
                    if (c != SPACE) {
                        sb.append(c);
                    } else {
                        process(sb, stack);
                    }
                }
                // Must be invoked here because we allow the last char of the command is not a space.
                // can also solve this problem by adding an extra space character to charArray, invoke System.arrayCopy()
                process(sb, stack);
            } catch (RpnInsufficientParameterException ripe) {
                System.err.println(String.format(INSUFFICIENT_MSG_FORMAT, sb.toString(), (position - sb.length())));
                final String undoneCommandStr = str.substring(position);
                System.err.println(String.format(TERMINAL_MSG_FORMAT, undoneCommandStr));
            } catch (RpnNoSuchStrategyDefinedException rssde) {
                System.err.println("No strategy defined for operator:" + sb.toString());
                keepRunning = false;
            } catch (Exception ne) {
                System.err.println("Failed to execute the command due to exception:" + ne);
            }

            printStack(stack);
        }
    }

    /**
     * deal with the command string
     * @param sb StringBuilder which is used to build a full command
     * @param stack the data stack
     */
    private static void process(StringBuilder sb, Deque<Element> stack) {
        if (sb.length() <= 0) {
            return;
        }
        final String s = sb.toString();
        if (RpnStrategyConfig.getInstance().isOperator(s)) {
            RpnCalculatorFactory.getStrategy(s).calculate(stack);
        } else {
            final Element element = new Element(Double.parseDouble(s));
            stack.offerLast(element);
        }
        sb.delete(0, sb.length());
    }

    /**
     * print all elements in stack
     * @param stack stack need operating
     */
    private static void printStack(Deque<Element> stack) {
        final StringBuilder sb = new StringBuilder("Stack: ");
        stack.stream()
                .mapToDouble(Element::getValue)
                .mapToObj(RpnCalculateApplication::decimalFormat)
                .forEach(sb::append);
        System.out.println(sb.toString());
    }

    /**
     * format decimal.Display to 10 decimal places.
     * @param value the value to format
     * @return format string result
     */
    private static String decimalFormat(double value) {
        // add a space to the end of value string
        return DISPLAY_DECIMAL_FORMAT.format(value) + " ";
    }
}
