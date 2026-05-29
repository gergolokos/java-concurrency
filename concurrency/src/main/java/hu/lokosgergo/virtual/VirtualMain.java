package hu.lokosgergo.virtual;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class VirtualMain {

    @SneakyThrows
    static void main() {
        //ExecutorService executorService = Executors.newFixedThreadPool(16);
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        var start = System.currentTimeMillis();
        try(executorService) {
            Runnable task = () -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            for(int i = 0; i < 100; i++) {
                executorService.execute(task);
            }
        }
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
        log.info("Time taken: {}", System.currentTimeMillis() - start);
    }
}
