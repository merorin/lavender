package lavender.merorin.reactor;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public interface Dispatcher {

    /**
     * register a event handler to dispatcher
     * @param eventHandler event handler
     */
    void registerEventHandler(EventHandler eventHandler);

    /**
     * remove a event handler to dispatcher
     * @param type type
     */
    void removeEventHandler(EventType type);

    /**
     * handle events
     */
    void handleEvents();
}
