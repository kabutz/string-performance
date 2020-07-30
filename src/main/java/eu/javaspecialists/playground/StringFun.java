package eu.javaspecialists.playground;

import java.nio.charset.*;

public class StringFun {
    public static void main(String... args) {
        String s = "≈≈≈≈≈≈≈≈™£¢∞¡@#$¬˚∆∆∆˙©ƒ∂ßåå";

        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        String s2 = new String(bytes);
        System.out.println(s);
    }
}
