package eu.javaspecialists.playground.hasher;

public class UglyButFastParallel {
    protected static final byte[] alphabet = new byte[26];

    static {
        for (int i = 0; i < 26; i++) {
            alphabet[i] = (byte) ('a' + i);
        }
        System.out.println("alphabet = " + new String(alphabet));
    }

    public static void main(String... args) throws InterruptedException {
        long time = System.nanoTime();
        try {
            Thread[] threads = new Thread[13];
            for (int i = 0; i < threads.length; i++) {
                int from = i * 2;
                int to = from + 2;
                threads[i] = new Thread(() -> {
                    for (int index = from; index < to; index++) {
                        byte i0 = alphabet[index];
                        System.out.println("Trying all Strings starting with '" + (char) i0 + "'");
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
                                                for (byte i7 : alphabet) {
                                                    int h7 = h6 * 31 + i7;
                                                    if (h7 == 0) {
                                                        byte[] is = {i0, i1, i2, i3, i4, i5, i6, i7};
                                                        System.out.println(new String(is));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                threads[i].start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1_000_000));
        }
    }
}
