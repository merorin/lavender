package lavender.merorin.tour;

import reactor.core.publisher.Flux;

/**
 * @author bin.guo
 * On 2019-03-11
 */
public class SubscribeTest {

    public static void main(String[] args) {
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(System.out::println);
    }
}
