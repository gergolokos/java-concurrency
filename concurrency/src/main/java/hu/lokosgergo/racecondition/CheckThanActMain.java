package hu.lokosgergo.racecondition;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CheckThanActMain {

    private List<Integer> numbers = new ArrayList<>();

    private final Object lock = new Object();

    public void addIfAbsent(int n) {
        synchronized (lock) {
            numbers.add(n);
        }
        //if (!numbers.contains(n)) {
        //}
        log.debug("Numbers: {}", numbers.size());
    }

    static void main() {
        var main = new CheckThanActMain();
        for (int i = 0; i < 10_000; i++) {
            //j effectively final
            var j = i;
            Runnable task = () -> main.addIfAbsent(j);
            new Thread(task).start();
        }
    }

}
