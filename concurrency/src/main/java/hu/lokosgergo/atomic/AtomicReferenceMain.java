package hu.lokosgergo.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferenceMain {

    record Counter(int value) {
        public Counter increment() {
            return new Counter(value + 1);
        }
    }

    private AtomicReference<Counter> counter =  new AtomicReference<>(new Counter(0));

    static void main() {
        var main =  new AtomicReferenceMain();
        Runnable task = () -> {
            while (true) {
                Counter oldValue = main.counter.get();
                Counter newValue = oldValue.increment();

                if (main.counter.compareAndSet(oldValue, newValue)) {
                    break;
                }

                log.debug("counter = {}", main.counter);
            }
        };
        try (var executorService = Executors.newFixedThreadPool(200)) {
            for (int i = 0; i < 100; i++) {
                executorService.execute(task);
            }
        }
    }
}
