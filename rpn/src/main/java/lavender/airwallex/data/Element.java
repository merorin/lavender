package lavender.airwallex.data;

import java.util.Deque;
import java.util.Objects;

/**
 * Description:This class actually hold the number
 * and is stored in the {@link java.util.Deque}, which behaves like {@link java.util.Stack}
 *
 * If the value of an {@link Element} was evaluated by operators,
 * the one/two parameters that used to calculate the value will be stored in this {@link Element} too.
 *
 * @author guobin On date 2018/8/7.
 * @version 1.0
 * @since jdk 1.8
 */
public class Element {

    private static final int MAX_FACTORS_SIZE = 2;

    public Element() { }

    public Element(double value) {
        this.value = value;
    }

    /**
     * The value which held by this object.
     */
    private double value;

    /**
     * The parameters which used to evaluate the {@link #value}
     */
    private Deque<Element> factors;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Deque<Element> getFactors() {
        return factors;
    }

    public void addFactor(Element element) {
        if (this.factors == null) {
            this.factors = new RpnStack(MAX_FACTORS_SIZE);
        }
        this.factors.offerLast(element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Element)) {
            return false;
        }
        Element element = (Element) o;
        return Double.compare(element.value, value) == 0 &&
                Objects.equals(factors, element.factors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, factors);
    }
}
