package lavender.merorin.tour;

import reactor.core.publisher.Flux;

/**
 * @author bin.guo
 * On 2019-03-11
 */
public class SubscribeWithError {

    private static final int BOUND = 3;

    public static void main(String[] args) {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(SubscribeWithError::exceptionOnFour);
        ints.subscribe(System.out::println, SubscribeWithError::printErr);
    }

    static Integer exceptionOnFour(Integer i) {
        if (i <= BOUND) {
            return i;
        }
        throw new RuntimeException("Got to 4");
    }

    static void printErr(Throwable t) {
        System.err.println("Error: " + t);
    }
}
