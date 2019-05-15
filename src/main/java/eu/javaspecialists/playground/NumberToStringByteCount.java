package eu.javaspecialists.playground;

import eu.javaspecialists.perf.util.*;

public class NumberToStringByteCount {
    private int intVal = 1640531527;
    private long longVal = -8454749669228202880L;

    public String int_plus() {
        return "" + intVal;
    }

    public String int_toString() {
        return Integer.toString(intVal);
    }

    public String long_plus() {
        return "" + longVal;
    }

    public String long_toString() {
        return Long.toString(longVal);
    }

    public static void main(String... args) {
        NumberToStringByteCount bc = new NumberToStringByteCount();

        ByteWatcherSingleThread bw = new ByteWatcherSingleThread();
        for (int i = 0; i < 1000000; i++) {
            bc.int_plus();
            bc.int_toString();
            bc.long_plus();
            bc.long_toString();
        }

        bw.reset();
        bc.int_plus();
        long int_plus_bytes = bw.calculateAllocations();

        bw.reset();
        bc.int_toString();
        long int_toString_bytes = bw.calculateAllocations();

        bw.reset();
        bc.long_plus();
        long long_plus_bytes = bw.calculateAllocations();

        bw.reset();
        bc.long_toString();
        long long_toString_bytes = bw.calculateAllocations();

        System.out.println("int_plus_bytes = " + int_plus_bytes);
        System.out.println("int_toString_bytes = " + int_toString_bytes);
        System.out.println("long_plus_bytes = " + long_plus_bytes);
        System.out.println("long_toString_bytes = " + long_toString_bytes);
    }
}
