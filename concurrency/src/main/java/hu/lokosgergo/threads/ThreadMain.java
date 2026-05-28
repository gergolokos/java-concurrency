package hu.lokosgergo.threads;

import hu.lokosgergo.annotation.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class ThreadMain {

    @SneakyThrows
    @BadPractice("""
            Sose használjatok alkalmazásban Thread.sleep()-et csak hosszú folyamat
            szimulálására használjuk a demoban""")
    static void main() {

        log.debug("Thread: {}", Thread.currentThread().getName());

        try {
            Thread.sleep(Duration.ofSeconds(1));
        } catch (InterruptedException e) {
            log.error("Interrupted while sleeping", e);
        }
    }
}
