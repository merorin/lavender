package lavender.merorin.reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public final class SimpleDispatcher implements Dispatcher {

    private final Map<EventType, EventHandler> eventTypeEventHandlerMap = new ConcurrentHashMap<>();

    private final Selector selector;

    public SimpleDispatcher(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void registerEventHandler(EventType type, EventHandler eventHandler) {
        this.eventTypeEventHandlerMap.put(type, eventHandler);
    }

    @Override
    public void removeEventHandler(EventType type) {
        this.eventTypeEventHandlerMap.remove(type);
    }

    @Override
    public void handleEvents() {
        while (!Thread.interrupted()) {
            List<Event> events = this.selector.select();
            events.forEach(event -> eventTypeEventHandlerMap.get(event.getType()).handle(event));
        }
    }
}
