package hu.lokosgergo.executors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.*;

@Slf4j
public class SubmitMain {

    @SneakyThrows
    static void main() {
        try (var executorService = Executors.newFixedThreadPool(2)) {
            Callable<Integer> task = () -> {
                    Thread.sleep(Duration.ofSeconds(10));
                    return 1;
            };

            Future<Integer> result = executorService.submit(task);
            log.debug("Ott vagyunk már? {}", result);

            try {
                var value = result.get(5, TimeUnit.SECONDS);
                log.debug("Értéke: {}", value);
            } catch (TimeoutException e) {
                log.error("Timeout", e);
            }

        }


    }
}
