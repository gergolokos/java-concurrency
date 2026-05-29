package hu.lokosgergo.collections;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CollectionsMain {

    @SneakyThrows
    static void main() {
        //Azért mert megjelentek a concurrenthashmapben olyan függvények, amelyek nincsennek benne a sima mapben
        ConcurrentHashMap<String, Integer> map =//new HashMap<>();
        //Collections.synchronizedMap(new HashMap<>());
                new ConcurrentHashMap<>();
        //Entry olyan osztály, amiben benne van a kulcs meg az érték - forEachEntry

        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        var result = map.reduceValues(2, ( v) -> v * 2, (x, y) -> x + y);
        log.info("result: {}", result);

        //Nincs ConcurrentHashSet -> ConcurrentHashMap-et kell rá használni
        Set<String> set = ConcurrentHashMap.newKeySet();

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        var value = queue.poll(1, TimeUnit.SECONDS);
        log.info("value: {}", value);
    }
}
