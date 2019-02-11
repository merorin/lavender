package lavender.merorin.reactor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public abstract class AbstractEventHandler implements EventHandler {

    private static final String ERR_MSG = "Invalid event type, %s. %s is expected for %s.";

    @Getter
    @Setter
    private InputSource source;

    private final EventType type;

    public AbstractEventHandler(EventType type) {
        this.type = type;
    }

    @Override
    public void handle(Event event) {
        if (event.getType() != this.type) {
            throw new IllegalStateException(String.format(ERR_MSG, event.getType(), this.type, this.getClass().getSimpleName()));
        }
        this.doHandle(event);
    }

    @Override
    public EventType getType() {
        return type;
    }

    /**
     * Handle event for a certain type. Implemented by subclass
     * @param event event
     */
    protected abstract void doHandle(Event event);
}
