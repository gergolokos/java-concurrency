package hu.lokosgergo.executors;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorMain {

    static void main() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
        //try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Runnable task = () -> {
                log.debug("Executing task");
                try {
                    Thread.sleep(Duration.ofSeconds(2));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            for (int i = 0; i < 10; i++) {
                executorService.execute(task);
            }
        }
        //executorService.shutdown();
    }
}
