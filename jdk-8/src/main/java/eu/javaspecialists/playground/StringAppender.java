package eu.javaspecialists.playground;

public class StringAppender {
  public static String appendBasic(
      String s1, String s2, String s3) {
    return "SELECT " + s1 + " FROM " + s2 + " WHERE " + s3;
  }
  public static String appendStringBuilder(
      String s1, String s2, String s3) {
    return new StringBuilder().append("SELECT ").append(s1)
        .append(" FROM ").append(s2).append(" WHERE ")
        .append(s3).toString();
  }
  public static String appendStringBuilderSize(
      String s1, String s2, String s3) {
    int len = 20 + s1.length() + s2.length() + s3.length();
    return new StringBuilder(len).append("SELECT ").append(s1)
        .append(" FROM ").append(s2).append(" WHERE ")
        .append(s3).toString();
  }
}