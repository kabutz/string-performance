package eu.javaspecialists.playground.hasher;

import java.util.*;
import java.util.concurrent.*;

// 37 seconds to 10 billion checks
public class BruteForceRecursive2Parallel extends BruteForceBase {
    public static void main(String... args) {
        byte[] is = new byte[7];
        Arrays.fill(is, (byte) '!'); // check that all slots are filled
        check(0, 0, is);
    }

    private static void check(int depth, int h, byte[] is) {
        if (depth == 7) {
            if (h == 0) {
                System.out.println(new String(is));
            }
            // checkProgress(); <-- not thread-safe
            return;
        }

        if (depth == 0) {
            Collection<RecursiveAction> actions = new ArrayList<>();
            for (byte i : alphabet) {
                actions.add(new RecursiveAction() {
                    protected void compute() {
                        System.out.println("Launching worker bee for letter "
                                + (char) i + " " + Thread.currentThread().getName());
                        byte[] clone = is.clone();
                        clone[depth] = i;
                        check(depth + 1, h * 31 + i, clone);
                    }
                });
            }
            ForkJoinTask.invokeAll(actions);
        } else {
            for (byte i : alphabet) {
                is[depth] = i;
                check(depth + 1, h * 31 + i, is);
            }
        }
    }
}
