package eu.javaspecialists.playground;

import java.util.*;
import java.util.concurrent.*;

public class KeyDemo {
    public static void main(String... args) {
        Map<Key, String> keys = new ConcurrentHashMap<Key, String>();
        long time = System.nanoTime();
        try {
            for (int i = 0; i < 30; i++) {
                keys.put(new Key(i), "" + i);
            }
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1000000));
        }

        time = System.nanoTime();
        try {
            for (int i = 0; i < 30; i++) {
                System.out.println(keys.get(new Key(i)));
            }
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1000000));
        }
    }
}
