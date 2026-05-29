package hu.lokosgergo.synchronizer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchMain {


    @SneakyThrows
    static void main() {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("Task finished");
        };

        try
            (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (int i = 1; i <= 5; i++) {
                executorService.execute(task);
            }
            log.debug("Tasks started");
            countDownLatch.await();
            log.debug("All tasks finished");
        }
    }

}
