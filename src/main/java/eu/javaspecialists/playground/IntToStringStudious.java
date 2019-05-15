package eu.javaspecialists.playground;

import java.lang.management.*;

public class IntToStringStudious {
    private static String s;
    private static long bestTime = Long.MAX_VALUE;

    public static void main(String... args) {
        RuntimeMXBean rbean = ManagementFactory.getRuntimeMXBean();
        System.out.println("IntToStringStudious on " +
                rbean.getVmVendor() + ", " +
                rbean.getVmName() + ", Java " +
                rbean.getVmVersion() +
                " : vm args " + rbean.getInputArguments());

        for (int i = 0; i < 10; i++) {
            test();
        }
        System.out.println("s = " + s);
        System.out.println("bestTime = " + bestTime / 1000000);
        System.out.println();
    }

    private static void test() {
        long time = System.nanoTime();
        try {
            for (int i = 0; i < 100000000; i++) {
                s = Integer.toString(i);
            }
        } finally {
            time = System.nanoTime() - time;
            bestTime = Math.min(bestTime, time);
            System.out.printf("time = %dms%n", (time / 1000000));
        }
    }
}
