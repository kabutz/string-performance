package eu.javaspecialists.playground;

import eu.javaspecialists.perf.util.*;

/*
Java 64-bit Compressed Oops Compact Strings on:
SELECT id, insert_time FROM history WHERE last_update_time > 1539203113091
byte[]: 74 characters
12 + 4 + 74 = 90 ≈ 96
String:
12 + 4 + 1 + 4 = 21 ≈ 24
Total: 120

Compact Strings off:
SELECT id, insert_time FROM history WHERE last_update_time > 1539203113091
byte[]: 74 characters
12 + 4 +  = 164 ≈ 168
String:
12 + 4 + 1 + 4 = 21 ≈ 24
Total: 192
 */
public class BytesCountAppending {
  public static void main(String... args) {
    String question = "What is Faster?";
    String answer1 = "Using + to add Strings";
    String answer2 = "Using StringAppender";
    ByteWatcherSingleThread bw = new ByteWatcherSingleThread();
    for (int i = 0; i < 1000000; i++) {
      concat(question, answer1, answer2);
      sb(question, answer1, answer2);
      sb_sized(question, answer1, answer2);
      format(question, answer1, answer2);

      concat(question, answer1, (Object) answer2);
      sb(question, answer1, (Object) answer2);
      sb_sized(question, answer1, (Object) answer2);
      format(question, answer1, (Object)answer2);
    }

    bw.reset();
    concat(question, answer1, answer2);
    long concat_bytes_strings = bw.calculateAllocations();

    bw.reset();
    sb(question, answer1, answer2);
    long sb_bytes_strings = bw.calculateAllocations();

    bw.reset();
    sb_sized(question, answer1, answer2);
    long sb_sized_bytes_strings = bw.calculateAllocations();

    bw.reset();
    format(question, answer1, answer2);
    long format_strings = bw.calculateAllocations();

    bw.reset();
    concat(question, answer1, (Object) answer2);
    long concat_bytes_mixed = bw.calculateAllocations();

    bw.reset();
    sb(question, answer1, (Object) answer2);
    long sb_bytes_mixed = bw.calculateAllocations();

    bw.reset();
    sb_sized(question, answer1, (Object) answer2);
    long sb_sized_bytes_mixed = bw.calculateAllocations();

    bw.reset();
    format(question, answer1, (Object)answer2);
    long format_mixed = bw.calculateAllocations();

    System.out.println("concat_bytes_strings = " + concat_bytes_strings);
    System.out.println("sb_bytes_strings = " + sb_bytes_strings);
    System.out.println("sb_sized_bytes_strings = " + sb_sized_bytes_strings);
    System.out.println("format_strings = " + format_strings);
    System.out.println("concat_bytes_mixed = " + concat_bytes_mixed);
    System.out.println("sb_bytes_mixed = " + sb_bytes_mixed);
    System.out.println("sb_sized_bytes_mixed = " + sb_sized_bytes_mixed);
    System.out.println("format_mixed = " + format_mixed);
  }

  public static String concat(String question, String answer1, String answer2) {
    return "<h1>" + question + "</h1><ol><li>" + answer1 +
        "</li><li>" + answer2 + "</li></ol>";
  }

  public static String sb(String question, String answer1, String answer2) {
    return new StringBuilder().append("<h1>").append(question)
        .append("</h1><ol><li>").append(answer1)
        .append("</li><li>").append(answer2)
        .append("</li></ol>").toString();
  }

  public static String sb_sized(String question, String answer1, String answer2) {
    int len = 36 + question.length() + answer1.length() + answer2.length();
    return new StringBuilder(len).append("<h1>").append(question)
        .append("</h1><ol><li>").append(answer1)
        .append("</li><li>").append(answer2)
        .append("</li></ol>").toString();
  }

  public static String format(String question, String answer1, String answer2) {
    return String.format("<h1>%s</h1><ol><li>%s</li><li>%s</li></ol>", question, answer1, answer2);
  }


  public static String concat(String question, String answer1, Object answer2) {
    return "<h1>" + question + "</h1><ol><li>" + answer1 +
        "</li><li>" + answer2 + "</li></ol>";
  }

  public static String sb(String question, String answer1, Object answer2) {
    return new StringBuilder().append("<h1>").append(question)
        .append("</h1><ol><li>").append(answer1)
        .append("</li><li>").append(answer2)
        .append("</li></ol>").toString();
  }

  public static String sb_sized(String question, String answer1, Object answer2) {
    int len = 36 + question.length() + answer1.length() + answer2.toString().length();
    return new StringBuilder(len).append("<h1>").append(question)
        .append("</h1><ol><li>").append(answer1)
        .append("</li><li>").append(answer2)
        .append("</li></ol>").toString();
  }

  public static String format(String question, String answer1, Object answer2) {
    return String.format("<h1>%s</h1><ol><li>%s</li><li>%s</li></ol>", question, answer1, answer2);
  }
}
