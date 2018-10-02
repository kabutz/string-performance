package eu.javaspecialists.playground;

/**
 * Bad style - should use the more elegant recursion - but this is faster
 */
public class BruteForceParallel {
  private static long numbers;
  private static final byte[] chars = new byte[26 * 2];

  static {
    for (int i = 0; i < 26; i++) {
      chars[i] = (byte) ('A' + i);
      chars[i + 26] = (byte) ('a' + i);
    }
    System.out.println("new String(chars) = " + new String(chars));
  }

  public static void main(String[] args) {
    for (int i = 0; i < 4; i++) {
      int from = i * 13;
      int to = from + 13;
      new Thread(() -> {
        for (int index = from; index < to; index++) {
          byte i0 = chars[index];
          int h0 = i0;
          for (byte i1 : chars) {
            int h1 = h0 * 31 + i1;
            for (byte i2 : chars) {
              int h2 = h1 * 31 + i2;
              for (byte i3 : chars) {
                int h3 = h2 * 31 + i3;
                for (byte i4 : chars) {
                  int h4 = h3 * 31 + i4;
                  for (byte i5 : chars) {
                    int h5 = h4 * 31 + i5;
                    for (byte i6 : chars) {
                      int h6 = h5 * 31 + i6;
                      if (h6 == 0) {
                        System.out.print((char) i0);
                        System.out.print((char) i1);
                        System.out.print((char) i2);
                        System.out.print((char) i3);
                        System.out.print((char) i4);
                        System.out.print((char) i5);
                        System.out.print((char) i6);
                        System.out.println();
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