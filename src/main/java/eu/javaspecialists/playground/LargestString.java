package eu.javaspecialists.playground;

public class LargestString {
    public static void main(String... args) {
        StringBuilder sb = new StringBuilder(2000000000);
        sb.append("hello");
        String s1 = sb.toString();
        System.out.println("s1.length() = " + s1.length());
        sb.append("hełło");
        String s2 = sb.toString();
        System.out.println("s2.length() = " + s1.length());
    }
}
