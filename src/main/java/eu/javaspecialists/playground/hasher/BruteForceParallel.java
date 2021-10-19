package eu.javaspecialists.playground.hasher;

/**
 * Bad style - recursion is more elegant - but this is faster
 */
public class BruteForceParallel extends BruteForceBase {
    public static void main(String... args) {
        for (int i = 0; i < 4; i++) {
            final int from = i * 13;
            final int to = from + 13;
            new Thread(new Runnable() {
                public void run() {
                    for (int index = from; index < to; index++) {
                        byte i0 = alphabet[index];
                        int h0 = i0;
                        for (byte i1 : alphabet) {
                            int h1 = h0 * 31 + i1;
                            for (byte i2 : alphabet) {
                                int h2 = h1 * 31 + i2;
                                for (byte i3 : alphabet) {
                                    int h3 = h2 * 31 + i3;
                                    for (byte i4 : alphabet) {
                                        int h4 = h3 * 31 + i4;
                                        for (byte i5 : alphabet) {
                                            int h5 = h4 * 31 + i5;
                                            for (byte i6 : alphabet) {
                                                int h6 = h5 * 31 + i6;
                                                if (h6 == 0) {
                                                    byte[] is = {i0, i1, i2, i3, i4, i5, i6};
                                                    System.out.println(new String(is));
                                                }
                                                // checkProgress(); <-- not thread-safe
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }).start();
        }
    }
}
