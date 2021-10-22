package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.text.*;
import java.util.*;
import java.util.concurrent.*;

import static eu.javaspecialists.perf.string.StringAppenderConstants.*;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringAppenderBenchmark {
    private String title = STRING_BENCHMARKS;
    private long id1 = ID1;
    private long id2 = ID2;
    private String optiontxt1 = PLUS_VS_CONCAT;
    private String optiontxt2 = STRING_BUILDER;
    private MessageFormat messageFormat;

    @Setup
    public void setup() {
        messageFormat = new MessageFormat("<h1>{0}</h1><ul><li><b>{1}</b> {2}</li><li><b>{3}</b> {4}</li></ul>", Locale.US);
        NumberFormat integerInstance = NumberFormat.getIntegerInstance(Locale.US);
        integerInstance.setGroupingUsed(false);
        messageFormat.setFormat(1, integerInstance);
        messageFormat.setFormat(3, integerInstance);
    }

    @Benchmark
    public String mixed_1_plus() {
        //        4                      16                   5                          12                5                       10
        return "<h1>" + title + "</h1><ul><li><b>" + id1 + "</b> " + optiontxt1 + "</li><li><b>" + id2 + "</b> " + optiontxt2 + "</li></ul>";
    }

    @Benchmark
    public String mixed_2_sb_sized() {
        return new StringBuilder(52 + title.length() + 10 + optiontxt1.length() + 6 + optiontxt2.length())
                .append("<h1>").append(title).append("</h1><ul><li><b>").append(id1).append("</b> ").append(optiontxt1)
                .append("</li><li><b>").append(id2).append("</b> ").append(optiontxt2).append("</li></ul>").toString();
    }

    @Benchmark
    public String mixed_3_sb() {
        return new StringBuilder()
                .append("<h1>").append(title).append("</h1><ul><li><b>").append(id1).append("</b> ").append(optiontxt1)
                .append("</li><li><b>").append(id2).append("</b> ").append(optiontxt2).append("</li></ul>").toString();
    }

    @Benchmark
    public String mixed_4_concat() {
        return "<h1>".concat(title).concat("</h1><ul><li><b>").concat(Long.toString(id1)).concat("</b> ").concat(optiontxt1)
                .concat("</li><li><b>").concat(Long.toString(id2)).concat("</b> ").concat(optiontxt2).concat("</li></ul>");
    }

    @Benchmark
    public String mixed_5_format() {
        return String.format("<h1>%s</h1><ul><li><b>%d</b> %s</li><li><b>%d</b> %s</li></ul>", title, id1, optiontxt1, id2, optiontxt2);
    }

    // Test only works Java 15+
//    private static final String FORMAT =
//            "<h1>%s</h1><ul><li><b>%d</b> %s</li><li><b>%d</b> %s</li></ul>";
    // @Benchmark
//    public String formatted() {
//        return FORMAT.formatted(title, id1, optiontxt1, id2, optiontxt2);
//    }

    // @Benchmark
    public String message_format_cached_instance() {
        return messageFormat.format(new Object[]{title, id1, optiontxt1, id2, optiontxt2});
    }

    //@Benchmark
    public String message_format() {
        return MessageFormat.format("<h1>{0}</h1><ul><li><b>{1}</b> {2}</li><li><b>{3}</b> {4}</li></ul>", new Object[]{title, id1, optiontxt1, id2, optiontxt2});
    }

    @Benchmark
    public String strings_1_appendBasic() {
        String question = title, answer1 = optiontxt1, answer2 = optiontxt2;
        return "<h1>" + question + "</h1><ol><li>" + answer1 +
                "</li><li>" + answer2 + "</li></ol>";
    }

    @Benchmark
    public String strings_2_appendStringBuilder() {
        String question = title, answer1 = optiontxt1, answer2 = optiontxt2;
        return new StringBuilder().append("<h1>").append(question)
                .append("</h1><ol><li>").append(answer1)
                .append("</li><li>").append(answer2)
                .append("</li></ol>").toString();
    }

    @Benchmark
    public String strings_3_appendStringBuilderSize() {
        String question = title, answer1 = optiontxt1, answer2 = optiontxt2;
        int len = 36 + question.length() + answer1.length() + answer2.length();
        return new StringBuilder(len).append("<h1>").append(question)
                .append("</h1><ol><li>").append(answer1)
                .append("</li><li>").append(answer2)
                .append("</li></ol>").toString();
    }
}
