package eu.javaspecialists.playground.hasher;

/**
 * Unhashing the String aka Darcy can produce a String with any
 * hashCode, but not in printable characters.
 * See also https://blogs.oracle.com/darcy/unhashing-a-string
 */
/*
number = 10000000000
start = 100827ms
 */
public class BruteForceRecursive extends BruteForceBase {
    public static void main(String... args) {
        // no point checking smaller than 7 characters as it is
        // mathematically impossible to get a String with smaller
        // size and character in range [A-Za-z] with hashCode==0
        long time = System.nanoTime();
        try {
            findZeroHashCodes(new byte[7], 0);
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("start = %dms%n", (time / 1000000));
        }
    }

    private static void findZeroHashCodes(byte[] value, int pos) {
        if (pos == value.length) {
            if (hashCode(value) == 0)
                System.out.println(new String(value));
            checkProgress();
            return;
        }
        for (byte i : alphabet) {
            value[pos] = i;
            findZeroHashCodes(value, pos + 1);
        }
    }

    public static int hashCode(byte[] value) {
        int h = 0;
        for (byte v : value) {
            h = 31 * h + (v & 0xff);
        }
        return h;
    }
}
