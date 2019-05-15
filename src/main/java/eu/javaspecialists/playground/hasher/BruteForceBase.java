package eu.javaspecialists.playground.hasher;

public abstract class BruteForceBase {
    protected static final byte[] alphabet = new byte[26 * 2];
    protected static long number;
    private final static boolean DEBUG = false;
    protected static long time;

    static {
        for (int i = 0; i < 26; i++) {
            alphabet[i] = (byte) ('A' + i);
            alphabet[i + 26] = (byte) ('a' + i);
        }
        System.out.println("alphabet = " + new String(alphabet));
        time = System.nanoTime();
    }

    protected static void checkProgress() {
        if (DEBUG) {
            if (++number % 10_000_000_000L == 0) {
                System.out.println("number = " + number);
                time = System.nanoTime() - time;
                System.out.println("start = " + time / 1_000_000 + "ms");
                time = System.nanoTime();
            }
        }
    }
}
