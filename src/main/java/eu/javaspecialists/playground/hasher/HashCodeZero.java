package eu.javaspecialists.playground.hasher;

import java.util.*;

// Java 6 get() is linear
// Java 7 get() is also linear, but special case for Strings if you us -Djdk.map.althashing.threshold=512
// hashes differently
// Java 8+ get() is logarithmic when bucket has a lot of clashes

// source code is compatible with Java 6
public class HashCodeZero {
    private static final String[] zeroHashCodes = {
            "ARbyguv", "ARbygvW", "ARbyhVv", "ARbyhWW", "ARbzHuv", "ARbzHvW", "ARbzIVv", "ARbzIWW",
            "ARcZguv", "ARcZgvW", "ARcZhVv", "ARcZhWW", "ASCyguv", "ASCygvW", "ASCyhVv", "ASCyhWW",
            "ASCzHuv", "ASCzHvW", "ASCzIVv", "ASCzIWW", "ASDZguv", "ASDZgvW", "ASDZhVv", "ASDZhWW",
    };

    public static void main(String... args) {
        for (int i = 5; i < 17; i++) {
            System.out.println("Testing with size " + (1 << i));
            test(1 << i);
        }
    }

    private static void test(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(zeroHashCodes[i % zeroHashCodes.length]);
        }
        String s = sb.toString();
        System.out.println("s.length() = " + s.length());
        System.out.println("s.hashCode() = " + s.hashCode());

        long time = System.nanoTime();
        try {
            int total = 0;
            for (int i = 0; i < 10000; i++) {
                total += s.hashCode();
            }
            System.out.println("total = " + total);
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("call hashCode() 10000x = %dms%n", (time / 1000000));
        }
    }
}
