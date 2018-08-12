package lavender.airwallex.data;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Objects;

/**
 * Description:Stack structure
 *
 * ArrayDeque doesn't override the hashCode() & equals()
 *
 * @author guobin On date 2018/8/12.
 * @version 1.0
 * @since jdk 1.8
 */
public class RpnStack extends ArrayDeque<Element> {

    public RpnStack() { }

    public RpnStack(int numElements) {
        super(numElements);
    }

    @Override
    public int hashCode() {
        final Element[] arr = new Element[this.size()];
        return Objects.hash((Object[]) (this.toArray(arr)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RpnStack)) {
            return false;
        }
        RpnStack that = (RpnStack) obj;
        if (this.size() == that.size()) {
            Iterator<Element> thatItr = that.iterator();
            return this.stream().allMatch(e -> Objects.equals(e, thatItr.next()));
        }
        // all the true conditions have been shown above
        return false;
    }
}
