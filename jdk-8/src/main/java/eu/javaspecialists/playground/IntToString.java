package eu.javaspecialists.playground;
/*
Results are all using plain-vanilla Java versions, without any flags set.
Integer.toString(i)
6       4680ms
7       3506ms
8       3731ms
11      2611ms

"" + i
6       5819ms
7       2830ms
8       2621ms
11      3071ms
 */
public class IntToString {
  private static String s;

  public static void main(String... args) {
    for (int i = 0; i < 10; i++) {
      test();
    }
  }

  private static void test() {
    long time = System.nanoTime();
    try {
      for (int i = 0; i < 100000000; i++) {
        s = Integer.toString(i);
//        s = "" + i;
      }
    } finally {
      time = System.nanoTime() - time;
      System.out.printf("time = %dms%n", (time / 1000000));
    }
  }
}
