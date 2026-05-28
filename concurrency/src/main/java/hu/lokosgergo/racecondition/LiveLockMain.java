package hu.lokosgergo.racecondition;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiveLockMain {

    static class Friend {
        private String name;

        public  Friend(String name) {}
        boolean movedAllowed = false;

        public synchronized void allowMove() {
            log.debug("{} allowing move", name);
            movedAllowed = true;
        }

        public synchronized void disallowMove() {
            log.debug("{} disallowing move", name);
            movedAllowed = false;
        }

        public synchronized boolean canMove() {
            return movedAllowed;
        }
    }

    @AllArgsConstructor
    static class MovementTask implements Runnable {
        private final Friend friend;
        private final Friend friend2;

        @Override
        public void run() {
            while (true) {
                if(friend.canMove()) {
                    //Friend1 aki próbál mozogni, de látja, hogy a másik mozog
                    friend.disallowMove(); //Megáll, hogy elengedje a másikat
                    friend2.allowMove(); //Engedi a másikat mozogni
                }
            }
        }
    }

    static void main() {
        var alice = new Friend("Alice");
        var bob = new Friend("Bob");

        var aliceTask = new MovementTask(alice, bob);
        var bobTask = new MovementTask(bob, alice);

        alice.allowMove(); //Start with Alice being able to move

        new Thread(aliceTask).start();
        new Thread(bobTask).start();
    }
}
