package hu.lokosgergo.racecondition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class CancelMain {

    @SneakyThrows
    static void main() {
        Runnable task = () -> {
            log.debug("Starting debug");
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    log.debug("Pl. Egy Web Service hívás vagy Prím szám számolása");
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) { //Az interrupted flag-et falsera billenti
                    log.debug("Interrupted");
                    Thread.currentThread().interrupt(); //Az Interrupted flag-et visszabillenti true-ra
                }
                log.debug("Finished debug");
            }
        };

        var thread = new Thread(task);
        thread.start();

        Thread.sleep(Duration.ofSeconds(5));

        thread.interrupt();
    }

}
