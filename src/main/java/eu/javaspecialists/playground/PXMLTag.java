package eu.javaspecialists.playground;

import java.util.*;

/*
This odd case
type 'java.util.Map'
currentPrimitiveType 'java.lang.String'
They are equal? false
They are compared 9
m_type 'java.util.Map'
m_primitiveType[i] 'java.lang.String'
They are equal? false
They are compared 9
java.util.Map not really primitive!

My program terminates on 1.7.0.45-64 but works fine on 1.7.0.25-64.
Fails also on 1.7.0_40
Failed also on 1.8.0_31

did not happen with -XX:DisableIntrinsic=_compareTo

List of Java 8 intrinsics: https://gist.github.com/apangin/7a9b7062a4bd0cd41fcc
List of Java 9 intrinsics: https://gist.github.com/apangin/8bc69f06879a86163e490a61931b37e8


 */
public class PXMLTag {
  public String m_type;
  public static Random m_random = new Random();
  private static String[] m_primitiveTypes = {"java.lang.String",
      "boolean",
      "int",
      "long",
      "float",
      "short"};

  private static String[] m_allTypes = {"java.lang.String",
      "boolean",
      "int",
      "long",
      "float",
      "java.util.Vector",
      "java.util.Map",
      "short"};

  public PXMLTag() {
    super();
  }

  public boolean isPrimitiveType() {
    for (int i = 0; i < m_primitiveTypes.length; i++) {
      String type = m_type;
      String primitiveType = m_primitiveTypes[i];
      if (type.compareTo(primitiveType) == 0) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int threads = 1;
    if (args.length == 1) {
      threads = Integer.parseInt(args[0]);
    }
    for (int i = 0; i < threads; i++) {
      Thread t = createThread();
      t.start();
    }
  }

  private static Thread createThread() {
    return new Thread(new Runnable() {

      @Override
      public void run() {
        while (true) {
          PXMLTag tag = new PXMLTag();
          tag.m_type = getRandomType();
          if (tag.isPrimitiveType() && notReallyPrimitiveType(tag.m_type)) {
            System.out.println(tag.m_type + " not really primitive!");
            System.exit(0);
          }
          try {
            Thread.sleep(m_random.nextInt(100));
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }

      private boolean notReallyPrimitiveType(String m_type) {
        return m_type.contains("Vector") || m_type.contains("Map");
      }

      private String getRandomType() {
        return m_allTypes[m_random.nextInt(m_allTypes.length)];
      }
    });
  }
}