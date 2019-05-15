package eu.javaspecialists.playground;

import org.openjdk.jol.info.*;

public class StringLayout {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(String.class).toPrintable());
        System.out.println(GraphLayout.parseInstance("").toPrintable());
        System.out.println(GraphLayout.parseInstance("Hello, world!").toPrintable());
    }
}
