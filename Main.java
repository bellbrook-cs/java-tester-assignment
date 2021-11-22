/*
 * Program Title: 
 * Author: 
 * Date: 
 * Purpose: 
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Main {
    public static void main(String[] args) {

      System.out.println("\nStart running tests...\n");
      
      Result result = JUnitCore.runClasses(Main.class);
      
      for (Failure failure : result.getFailures()) {

        System.out.println(failure.getTestHeader());
        System.out.println(failure.getMessage());
        System.out.println();

      }
      
      if (result.wasSuccessful()) {
        System.out.println("All tests sucessful! Congrats!");
      } else {
        System.out.printf("Failed %d/%d tests. See the above results for more information.%n", result.getFailureCount(), result.getRunCount());
      }

    }

    @Test
    public void testHello() {
      assertEquals("Hello", Problem.Hello());
    }

    @Test
    public void testWorld() {
      assertEquals("World", Problem.Hello());
    }
}
