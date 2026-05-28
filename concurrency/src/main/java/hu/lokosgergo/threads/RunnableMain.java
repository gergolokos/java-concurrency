package hu.lokosgergo.threads;

import hu.lokosgergo.annotation.BadPractice;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@BadPractice("""
        Ne használjunk Threadeket, túl alacsony szint,
        helyette inkább ForkJoinPool vagy Executor vagy CompletableFuture-t
        vagy Structured Concurrency
        """)
public class RunnableMain {

    static void main() {

        //Ez egy TASK
        Runnable runnable = () -> {
            log.debug("Start");
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new IllegalStateException();
            //log.debug("Stop");
        };

        var thread = new Thread(runnable);
        //thread.setDaemon(true);
        thread.start();
        log.debug("Main");

        Thread.setDefaultUncaughtExceptionHandler((t, e) ->
                log.error("Uncaught exception", e));

        for (int i = 0; i < 5; i++) {
            var newThread = new Thread(runnable);
            newThread.setName("SleeperThread-" + i);
            newThread.setUncaughtExceptionHandler((t, e) -> log.error("Uncaught exception", e));
            newThread.start();
            //
            //newThread.run();
        }
    }
}
