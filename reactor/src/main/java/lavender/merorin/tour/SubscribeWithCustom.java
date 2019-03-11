package lavender.merorin.tour;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * @author bin.guo
 * On 2019-03-11
 */
public class SubscribeWithCustom {

    public static void main(String[] args) {
        CustomSubscriber<Integer> subscriber = new CustomSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(System.out::println, SubscribeWithError::printErr, SubscribeWithErrAndCompletion::done, s -> subscriber.request(10));
        ints.subscribe(subscriber);
    }

    private static class CustomSubscriber<T> extends BaseSubscriber<T> {

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }
}
