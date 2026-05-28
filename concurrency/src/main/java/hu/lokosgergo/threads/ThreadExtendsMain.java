package hu.lokosgergo.threads;

import hu.lokosgergo.annotation.BadPractice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@BadPractice("""
Sose származzunk le a thread osztályból, mindig Runnable implementálás
""")
public class ThreadExtendsMain {

    static void main() {
        var thread = new Thread() {
            @Override
            public void run() {
                log.debug("Start");
            }
        };
        thread.start();
    }

}
