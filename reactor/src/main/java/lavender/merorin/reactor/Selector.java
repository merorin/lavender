package lavender.merorin.reactor;

import java.util.List;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public interface Selector {

    /**
     * select events
     * @return events
     */
    List<Event> select();

    /**
     * select events with timeout
     * @param timeout timeout
     * @return events
     */
    List<Event> select(long timeout);

    /**
     * add a event
     * @param e event
     */
    void addEvent(Event e);
}
