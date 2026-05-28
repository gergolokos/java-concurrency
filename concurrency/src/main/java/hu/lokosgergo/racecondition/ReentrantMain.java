package hu.lokosgergo.racecondition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReentrantMain {

    private Object lock = new Object();

    public void countdown(int counter) {
        synchronized (lock) {
            if(counter > 0) {
                countdown(counter-1);
            }
        }
    }

    public void logCountdown(int countdown) {
        synchronized (lock) {
            log.debug("Counter: {}", countdown);
        }
    }

    static void main() {
        var main = new ReentrantMain();



        Runnable task = () -> main.countdown(10);
        new Thread(task).start();
    }

}
