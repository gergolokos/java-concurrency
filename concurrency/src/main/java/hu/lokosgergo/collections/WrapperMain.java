package hu.lokosgergo.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
public class WrapperMain {

    static void main() {
        //Egyszerű de lassú, ha fontos a performancia más megoldást kell választani
        var numbers = Collections.synchronizedList(new ArrayList<Integer>());
        log.debug(numbers.getClass().getName());
    }

}
