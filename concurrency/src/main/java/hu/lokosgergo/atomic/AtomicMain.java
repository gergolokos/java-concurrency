package hu.lokosgergo.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicMain {

    private AtomicInteger counter = new AtomicInteger();

    static void main() {
        var main = new AtomicMain();
            Runnable task = () -> {
               var counter = main.counter.incrementAndGet();
               log.debug("counter = {}", counter);
            };

            try (var executorService =  Executors.newFixedThreadPool(200)) {
                for (int i = 0; i < 10_000; i++) {
                    executorService.execute(task);
                }
            }
            log.info("Counter = {}", main.counter);
    }
}
