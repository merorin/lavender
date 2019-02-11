package lavender.merorin.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public final class SimpleSelector implements Selector {

    private final BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();

    @Override
    public List<Event> select() {
        return this.select(0L);
    }

    @Override
    public List<Event> select(long timeout) {
        final boolean needWaiting = timeout > 0 && this.eventQueue.isEmpty();
        if (needWaiting) {
            synchronized (this) {
                if (this.eventQueue.isEmpty()) {
                    try {
                        this.wait(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        final List<Event> events = new ArrayList<>(this.eventQueue.size());
        this.eventQueue.drainTo(events);
        return events;
    }

    @Override
    public void addEvent(Event e) {
        if (this.eventQueue.offer(e)) {
            synchronized (this) {
                this.notify();
            }
        }
    }
}
