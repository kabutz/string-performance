package eu.javaspecialists.playground;

public class StringBuilderAppendingPerformanceLoop {
    public static void main(String... args) {
        for (int i = 0; i < 3; i++) {
            test();
        }
    }
    private static void test() {
        for (int n = 1000; n <= 100000; n*=2) {
            test(n);
        }
    }
    private static void test(int n) {
        long time = System.nanoTime();
        try {
            StringBuilder sb = new StringBuilder();
            // O(n)
            for (int i = 0; i < n; i++) {
                sb.append(i);
            }
            String s = sb.toString();
//            String s = "";
//                // O(n^2)
//            for (int i = 0; i < n; i++) {
//                s += i;
//            }
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("%d time = %dms%n", n, (time / 1_000_000));
        }
    }
}
