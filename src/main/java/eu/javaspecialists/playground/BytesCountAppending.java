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
    ByteWatcherSingleThread bw = new ByteWatcherSingleThread();
    for (int i = 0; i < 1000000; i++) {
      concat("id, insert_time", "history", "1539203113091");
      sb("id, insert_time", "history", "1539203113091");
      sb_sized("id, insert_time", "history", "1539203113091");
      concat("id, insert_time", "history", 1539203113091L);
      sb("id, insert_time", "history", 1539203113091L);
      sb_sized("id, insert_time", "history", 1539203113091L);
    }

    bw.reset();
    concat("id, insert_time", "history", "1539203113091");
    long concat_bytes_strings = bw.calculateAllocations();

    bw.reset();
    sb("id, insert_time", "history", "1539203113091");
    long sb_bytes_strings = bw.calculateAllocations();

    bw.reset();
    sb_sized("id, insert_time", "history", "1539203113091");
    long sb_sized_bytes_strings = bw.calculateAllocations();

    bw.reset();
    concat("id, insert_time", "history", 1539203113091L);
    long concat_bytes_mixed = bw.calculateAllocations();

    bw.reset();
    sb("id, insert_time", "history", 1539203113091L);
    long sb_bytes_mixed = bw.calculateAllocations();

    bw.reset();
    sb_sized("id, insert_time", "history", 1539203113091L);
    long sb_sized_bytes_mixed = bw.calculateAllocations();

    System.out.println("concat_bytes_strings = " + concat_bytes_strings);
    System.out.println("sb_bytes_strings = " + sb_bytes_strings);
    System.out.println("sb_sized_bytes_strings = " + sb_sized_bytes_strings);
    System.out.println("concat_bytes_mixed = " + concat_bytes_mixed);
    System.out.println("sb_bytes_mixed = " + sb_bytes_mixed);
    System.out.println("sb_sized_bytes_mixed = " + sb_sized_bytes_mixed);
  }

  public static String concat(String columns, String table, String time) {
    return "SELECT " + columns + " FROM " + table + " WHERE last_update_time > " + time;
  }

  public static String sb(String columns, String table, String time) {
    return new StringBuilder()
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE last_update_time > ").append(time).toString();
  }

  public static String sb_sized(String columns, String table, String time) {
    return new StringBuilder(39 + columns.length() + table.length() + 19)
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE last_update_time > ").append(time).toString();
  }
  public static String concat(String columns, String table, long time) {
    return "SELECT " + columns + " FROM " + table + " WHERE last_update_time > " + time;
  }

  public static String sb(String columns, String table, long time) {
    return new StringBuilder()
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE last_update_time > ").append(time).toString();
  }

  public static String sb_sized(String columns, String table, long time) {
    return new StringBuilder(39 + columns.length() + table.length() + 19)
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE last_update_time > ").append(time).toString();
  }
}
