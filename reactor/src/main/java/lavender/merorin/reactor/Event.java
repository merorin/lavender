package lavender.merorin.reactor;

import lombok.Builder;
import lombok.Data;

/**
 * @author bin.guo
 * On 2019-02-11
 */
@Data
@Builder
public class Event {

    private InputSource source;

    private EventType type;
}
