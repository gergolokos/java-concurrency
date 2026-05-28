package hu.lokosgergo.racecondition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class JoinMain {

    private Random random = new Random();

    @SneakyThrows
    public void sleep() {
        Thread.sleep(Duration.ofSeconds(random.nextInt(5)));
        log.debug("Sleeping");
    }


    @SneakyThrows
    static void main() {
        var joinMain = new JoinMain();
        Runnable task = joinMain::sleep;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (var thread : threads) {
            boolean result = thread.join(Duration.ofSeconds(10));
            //Timeout megadása mindig kötelező
            log.debug("Result: {}", result);
        }

        log.debug("End...");
    }
}
