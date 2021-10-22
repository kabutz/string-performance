package eu.javaspecialists.playground;

public class StringAppender {
    private static String leak;

    public static String appendBasic(
            String question, String answer1, String answer2) {
        return "<h1>" + question + "</h1><ol><li>" + answer1 +
                "</li><li>" + answer2 + "</li></ol>";
    }

    public static String appendStringBuilder(
            String question, String answer1, String answer2) {
        return new StringBuilder().append("<h1>").append(question)
                .append("</h1><ol><li>").append(answer1)
                .append("</li><li>").append(answer2)
                .append("</li></ol>").toString();
    }

    public static String appendStringBuilderSize(
            String question, String answer1, String answer2) {
        int len = 36 + question.length() + answer1.length() + answer2.length();
        return new StringBuilder(len).append("<h1>").append(question)
                .append("</h1><ol><li>").append(answer1)
                .append("</li><li>").append(answer2)
                .append("</li></ol>").toString();
    }

    // don't show this option
    public static String appendFormat(
            String question, String answer1, String answer2) {
        return String.format("<h1>%s</h1><ol><li>%s</li><li>%s</li></ol>", question, answer1, answer2);
    }


    public static void main(String... args) {
        String question = "What is the question?";
        String answer1 = "Answer 1";
        String answer2 = "Answer 2";
        for (int i = 0; i < 10; i++) {
            test(question, answer1, answer2);
        }
    }
    private static void test(String question, String answer1, String answer2) {
        long time = System.nanoTime();
        try {
            for (int i = 0; i < 20000000; i++) {
                leak = appendFormat(question, answer1, answer2);
            }
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1000000));
        }
    }
}
