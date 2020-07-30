package eu.javaspecialists.playground;

public class Quiz {
    public static void main(String... args) {
        String s = "20_JUG_Nis\u030C_Serbia";
        char[] cs = new char[2000000000];
        cs[0] = '\u030C';
        String s1 = new String(cs);

    }
}
