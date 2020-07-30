package eu.javaspecialists.playground;

import java.lang.reflect.*;

public class DeduplicationDemo {
    public static void main(String... args) throws Exception {
        char[] heinz = {'h', 'e', 'i', 'n', 'z'};
        String[] s = {new String(heinz), new String(heinz),};
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);
        System.out.println("Before GC");
        System.out.println((Object)heinz);
        System.out.println(value.get(s[0]));
        System.out.println(value.get(s[1]));
        System.gc();
        System.gc();
        System.gc();
        Thread.sleep(1000);
        System.out.println("After GC");
        System.out.println((Object)heinz);
        System.out.println(value.get(s[0]));
        System.out.println(value.get(s[1]));
    }
}
