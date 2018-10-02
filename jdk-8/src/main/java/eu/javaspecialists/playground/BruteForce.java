package eu.javaspecialists.playground;

/**
 * Unhashing the String aka Darcy can produce a String with any hashCode, but not in printable characters.
 * See also https://blogs.oracle.com/darcy/unhashing-a-string
 */
public class BruteForce {
  private static  long numbers;
  public static void main(String[] args) {
    // no point checking smaller than 7 characters as it is mathematically impossible to get a String with smaller size
    // and character in range [A-Za-z] with hashCode==0
    for (int length = 7; length < 12; length++) {
      System.out.println("length = " + length);
      byte[] value = new byte[length];
      findZeroHashCodes(value, 0);
    }
  }

  private static void findZeroHashCodes(byte[] value, int pos) {
    if (pos == value.length) {
      if (hashCode(value) == 0)
        System.out.println(new String(value));
      return;
    }
    for (byte i = 'A'; i <= 'Z'; i++) {
      value[pos] = i;
      findZeroHashCodes(value, pos + 1);
    }
    for (byte i = 'a'; i <= 'z'; i++) {
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