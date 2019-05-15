package eu.javaspecialists.playground;

import eu.javaspecialists.perf.util.*;

import java.text.*;
import java.util.*;

public class StringAppenderByteCount {
    private String title = "String benchmarks";
    private long id1 = 2734923874L;
    private long id2 = 100100;
    private String optiontxt1 = "plus vs concat";
    private String optiontxt2 = "StringBuilder";
    private MessageFormat messageFormat;

    public StringAppenderByteCount() {
        messageFormat = new MessageFormat("<h1>{0}</h1><ul><li><b>{1}</b> {2}</li><li><b>{3}</b> {4}</li></ul>", Locale.US);
        NumberFormat integerInstance = NumberFormat.getIntegerInstance(Locale.US);
        integerInstance.setGroupingUsed(false);
        messageFormat.setFormat(1, integerInstance);
        messageFormat.setFormat(3, integerInstance);
    }

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

    public String message_format() {
        return messageFormat.format(new Object[]{title, id1, optiontxt1, id2, optiontxt2});
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

    public static void main(String... args) {
        StringAppenderByteCount bc = new StringAppenderByteCount();

        ByteWatcherSingleThread bw = new ByteWatcherSingleThread();
        for (int i = 0; i < 1000000; i++) {
            bc.plus();
            bc.sb_sized();
            bc.sb();
            bc.concat();
            bc.message_format();
            bc.format();
        }

        bw.reset();
        bc.plus();
        long plus_bytes = bw.calculateAllocations();

        bw.reset();
        bc.sb_sized();
        long sb_sized_bytes = bw.calculateAllocations();

        bw.reset();
        bc.sb();
        long sb_bytes = bw.calculateAllocations();

        bw.reset();
        bc.concat();
        long concat_bytes = bw.calculateAllocations();

        bw.reset();
        bc.message_format();
        long message_format_bytes = bw.calculateAllocations();

        bw.reset();
        bc.format();
        long format_bytes = bw.calculateAllocations();

        System.out.println("plus_bytes = " + plus_bytes);
        System.out.println("sb_sized_bytes = " + sb_sized_bytes);
        System.out.println("sb_bytes = " + sb_bytes);
        System.out.println("concat_bytes = " + concat_bytes);
        System.out.println("message_format_bytes = " + message_format_bytes);
        System.out.println("format_bytes = " + format_bytes);
    }
}
