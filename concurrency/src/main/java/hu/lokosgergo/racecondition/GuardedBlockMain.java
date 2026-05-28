package hu.lokosgergo.racecondition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class GuardedBlockMain {

    private boolean condition = false;

    @SneakyThrows
    public synchronized void waitForCondition() {
        while (!condition) {
            log.debug("Waiting for condition...");
            //Wait engedi el a lockot
            wait();
            log.debug("Waiting for condition finished...");
        }
    }

    public synchronized void setCondition() {
        condition = true;
        //Szól a wait()-nek, hogy feloldódik a lock és mehet tovább
        notify();
    }

    @SneakyThrows
    static void main() {
        var main = new GuardedBlockMain();
        Runnable waitingTask = main::waitForCondition;
        new Thread(waitingTask).start();

        Thread.sleep(Duration.ofSeconds(2));
        Runnable settingTask = main::setCondition;
        new Thread(settingTask).start();

        log.debug("End");
    }

}
