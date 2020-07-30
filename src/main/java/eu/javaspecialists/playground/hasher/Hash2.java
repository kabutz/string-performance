package eu.javaspecialists.playground.hasher;

/**
 * http://horstmann.com/unblog/2020-04-02/index.html
 *
 * @author Cay Horstmann
 */
public class Hash2 {
    public static void next(int[] positions, int n) {
        positions[positions.length - 1]++;
        int carry = 0;
        for (int i = positions.length - 1; i >= 0; i--) {
            positions[i] += carry;
            if (positions[i] >= n) {
                positions[i] -= n;
                carry = 1;
            } else
                carry = 0;
        }
    }

    public static int hashCode(int[] positions, String chars) {
        int result = 0;
        for (int i = 0; i < positions.length; i++) {
            result = result * 31 + chars.charAt(positions[i]);
        }
        return result;
    }

    public static String str(int[] positions, String chars) {
        String result = "";
        for (int i = 0; i < positions.length; i++) {
            result += chars.charAt(positions[i]);
        }
        return result;
    }

    public static boolean allZero(int[] positions) {
        for (int i = 0; i < positions.length; i++)
            if (positions[i] != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        // chars += chars.toUpperCase();
        int n = 8;
        int[] positions = new int[n];
        long time = System.nanoTime();
        try {
            int count = 0;
            do {
                int h = hashCode(positions, chars);
                if (h == 0) {
                    System.out.println(str(positions, chars));
                    count++;
                    if (count == 2) break;
                }
                next(positions, chars.length());
            } while (!allZero(positions));
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1_000_000));
        }
    }
}
