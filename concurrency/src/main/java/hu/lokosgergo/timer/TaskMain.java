package hu.lokosgergo.timer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class TaskMain {

    @SneakyThrows
    static void main() {
        var task = new TimerTask() {
            @Override
            public void run() {
                log.debug("Running task...");
            }
        };
        log.debug("Starting task...");
        var timer = new Timer();
        timer.schedule(task, Duration.ofSeconds(2).toMillis(), Duration.ofSeconds(1).toMillis());
        Thread.sleep(Duration.ofSeconds(5));
        timer.cancel();
    }
}
