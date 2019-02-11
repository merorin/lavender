package lavender.merorin.reactor;

/**
 * @author bin.guo
 * On 2019-02-11
 */
public class AcceptEventHandler extends AbstractEventHandler {

    private final Selector selector;

    public AcceptEventHandler(Selector selector) {
        super(EventType.ACCEPT);
        this.selector = selector;
    }

    @Override
    protected void doHandle(Event event) {
        System.out.println("Process accept event.");

        this.selector.addEvent(Event.builder().source(event.getSource()).type(EventType.READ).build());
    }
}
