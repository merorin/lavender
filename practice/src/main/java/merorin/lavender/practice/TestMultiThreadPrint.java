package merorin.lavender.practice;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author bin.guo
 * On 2019-07-05
 */
public class TestMultiThreadPrint {

    /**
     * We can read rules from files
     */
    private static final List<Tuple2<Character, Character>> RULES = buildRules();


    public static void main(String[] args) {
        Set<Character> set = new HashSet<>();

        for (Tuple2<Character, Character> tuple : RULES) {
            set.add(tuple._1);
            set.add(tuple._2);
        }

        (new PrintDistributor(RULES, set)).start();
    }

    private static List<Tuple2<Character, Character>> buildRules() {
        final List<Tuple2<Character, Character>> list = new ArrayList<>();
        list.add(Tuple.of('a', 'w'));
        list.add(Tuple.of('w', 'x'));
        list.add(Tuple.of('x', 'a'));
        return Collections.unmodifiableList(list);
    }

    private static class PrintDistributor {

        private final Map<Character, Character> rule;

        private final Set<Character> set;

        private volatile char current = 'a';

        private volatile boolean keepRunning = true;

        private final ExecutorService executorService;

        PrintDistributor(List<Tuple2<Character, Character>> list, Set<Character> set) {
            rule = list.stream().collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));

            this.set = set;
            executorService = new ThreadPoolExecutor(
                    set.size(), set.size(), 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
            );
        }

        void start() {
            System.out.println("Character set is" + set);
            for (Character c : set) {
                executorService.submit(new PrintWorker(c));
            }
        }

        void stop() {
            executorService.shutdown();
        }

        private class PrintWorker implements Runnable {

            private final char characterToBePrinted;

            PrintWorker(char characterToBePrinted) {
                this.characterToBePrinted = characterToBePrinted;
            }

            @Override
            public void run() {
                while (keepRunning) {
                    if (current == characterToBePrinted) {
                        System.out.println(characterToBePrinted);
                        Character next = rule.get(current);
                        if (next == null) {
                            keepRunning = false;
                            stop();
                        } else {
                            current = next;
                        }
                    }
                }
                System.out.println("End worker...." + characterToBePrinted);
            }
        }
    }
}
