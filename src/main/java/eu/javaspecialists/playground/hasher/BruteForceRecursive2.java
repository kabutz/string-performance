package eu.javaspecialists.playground.hasher;

import java.util.*;

/*
number = 10000000000
start = 40056ms
 */
public class BruteForceRecursive2 extends BruteForceBase {
    public static void main(String... args) {
        byte[] is = new byte[7];
        Arrays.fill(is, (byte) '!');
        check(0, 0, is);
    }

    private static void check(int depth, int h, byte[] is) {
        if (depth == 7) {
            if (h == 0) {
                System.out.println(new String(is));
            }
            checkProgress();
            return;
        }

        for (byte i : alphabet) {
            is[depth] = i;
            check(depth + 1, h * 31 + i, is);
        }
    }
}
