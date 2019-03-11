package lavender.merorin.tour;

import reactor.core.publisher.Flux;

/**
 * @author bin.guo
 * On 2019-03-11
 */
public class SubscribeWithErrAndCompletion {

    public static void main(String[] args) {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(System.out::println, SubscribeWithError::printErr, SubscribeWithErrAndCompletion::done);
    }

    static void done() {
        System.out.println("Done");
    }
}
