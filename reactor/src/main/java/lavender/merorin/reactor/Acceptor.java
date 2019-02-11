package lavender.merorin.reactor;

import lombok.Getter;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public class Acceptor implements Runnable {

    @Getter
    private final int port;

    private final Selector selector;

    private final BlockingQueue<InputSource> sourceQueue = new LinkedBlockingQueue<>();

    public Acceptor(int port, Selector selector) {
        this.port = port;
        this.selector = selector;
    }

    public void addNewConnection(InputSource source) {
        this.sourceQueue.offer(source);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Optional.ofNullable(this.sourceQueue.poll())
                        .map(source -> Event.builder().source(source).type(EventType.ACCEPT).build())
                        .ifPresent(this.selector::addEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
