package lavender.merorin.reactor;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public interface EventHandler {

    /**
     * handle event
     * @param event event
     */
    void handle(Event event);

    /**
     * get the type that handler process
     * @return type
     */
    EventType getType();
}
