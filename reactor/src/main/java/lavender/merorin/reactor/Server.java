package lavender.merorin.reactor;

import java.util.concurrent.CompletableFuture;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public class Server {

    private final Selector selector = new SimpleSelector();

    private final Dispatcher eventLooper = new SimpleDispatcher(selector);

    private final Acceptor acceptor;

    public Server(int port) {
        this.acceptor = new Acceptor(port, this.selector);
    }

    public void start() {
        this.eventLooper.registerEventHandler(new AcceptEventHandler(this.selector));
        CompletableFuture.runAsync(this.acceptor);
        this.eventLooper.handleEvents();
    }
}
