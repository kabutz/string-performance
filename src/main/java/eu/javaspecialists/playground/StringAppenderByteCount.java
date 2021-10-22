package eu.javaspecialists.playground;

import eu.javaspecialists.perf.util.*;

import java.text.*;
import java.util.*;

import static eu.javaspecialists.perf.string.StringAppenderConstants.*;

public class StringAppenderByteCount {
    private static volatile Object leak;
    private String title = STRING_BENCHMARKS;
    private long id1 = ID1;
    private long id2 = ID2;
    private String optiontxt1 = PLUS_VS_CONCAT;
    private String optiontxt2 = STRING_BUILDER;

    public String plus() {
        //        4                      16                   5                          12                5                       10
        return "<h1>" + title + "</h1><ul><li><b>" + id1 + "</b> " + optiontxt1 + "</li><li><b>" + id2 + "</b> " + optiontxt2 + "</li></ul>";
    }

    public String concat() {
        return "<h1>".concat(title).concat("</h1><ul><li><b>").concat(Long.toString(id1)).concat("</b> ").concat(optiontxt1)
                .concat("</li><li><b>").concat(Long.toString(id2)).concat("</b> ").concat(optiontxt2).concat("</li></ul>");
    }

    public String format() {
        return String.format("<h1>%s</h1><ul><li><b>%d</b> %s</li><li><b>%d</b> %s</li></ul>", title, id1, optiontxt1, id2, optiontxt2);
    }

    public String sb() {
        return new StringBuilder()
                .append("<h1>").append(title).append("</h1><ul><li><b>").append(id1).append("</b> ").append(optiontxt1)
                .append("</li><li><b>").append(id2).append("</b> ").append(optiontxt2).append("</li></ul>").toString();
    }

    public String sb_sized() {
        return new StringBuilder(52 + title.length() + 10 + optiontxt1.length() + 6 + optiontxt2.length())
                .append("<h1>").append(title).append("</h1><ul><li><b>").append(id1).append("</b> ").append(optiontxt1)
                .append("</li><li><b>").append(id2).append("</b> ").append(optiontxt2).append("</li></ul>").toString();
    }

    public String appendBasic() {
        String question = title, answer1 = optiontxt1, answer2 = optiontxt2;
        return "<h1>" + question + "</h1><ol><li>" + answer1 +
                "</li><li>" + answer2 + "</li></ol>";
    }

    public String appendStringBuilder() {
        String question = title, answer1 = optiontxt1, answer2 = optiontxt2;
        return new StringBuilder().append("<h1>").append(question)
                .append("</h1><ol><li>").append(answer1)
                .append("</li><li>").append(answer2)
                .append("</li></ol>").toString();
    }

    public String appendStringBuilderSize() {
        String question = title, answer1 = optiontxt1, answer2 = optiontxt2;
        int len = 36 + question.length() + answer1.length() + answer2.length();
        return new StringBuilder(len).append("<h1>").append(question)
                .append("</h1><ol><li>").append(answer1)
                .append("</li><li>").append(answer2)
                .append("</li></ol>").toString();
    }

    public static void main(String... args) throws InterruptedException {
        StringAppenderByteCount bc = new StringAppenderByteCount();

        ByteWatcherSingleThread bw = new ByteWatcherSingleThread();
        for (int i = 0; i < 1000000; i++) {
            leak = bc.object();
            leak = bc.makeLong();
            leak = bc.plus();
            leak = bc.sb_sized();
            leak = bc.sb();
            leak = bc.concat();
            leak = bc.format();
        }

        for (int i = 0; i < 5; i++) {
            test(bc, bw);
            System.out.println();
            Thread.sleep(1000);
        }

    }
    private static void test(StringAppenderByteCount bc, ByteWatcherSingleThread bw) {
        bw.reset();
        leak = bc.object();
        long object_bytes = bw.calculateAllocations();

        bw.reset();
        leak = bc.makeLong();
        long long_bytes = bw.calculateAllocations();

        bw.reset();
        leak = bc.plus();
        long plus_bytes = bw.calculateAllocations();

        bw.reset();
        leak = bc.sb_sized();
        long sb_sized_bytes = bw.calculateAllocations();

        bw.reset();
        leak = bc.sb();
        long sb_bytes = bw.calculateAllocations();

        bw.reset();
        leak = bc.concat();
        long concat_bytes = bw.calculateAllocations();

        bw.reset();
        leak = bc.format();
        long format_bytes = bw.calculateAllocations();

        if (object_bytes != 16) throw new AssertionError();
        if (long_bytes != 24) throw new AssertionError();
        System.out.println("plus_bytes = " + plus_bytes);
        System.out.println("sb_sized_bytes = " + sb_sized_bytes);
        System.out.println("sb_bytes = " + sb_bytes);
        System.out.println("concat_bytes = " + concat_bytes);
        System.out.println("format_bytes = " + format_bytes);
    }
    private Object object() {
        return new Object();
    }
    private Long makeLong() {
        return 42000000000000L;
    }
}
