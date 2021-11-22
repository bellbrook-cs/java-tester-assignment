/*
 * Program Title: Java Test Runner
 * Author: Hayden Mankin
 * Date: 11/21/2021
 * Purpose: Run tests on Java programming assignments.
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import org.junit.runner.notification.RunListener;
import java.util.Date;
import org.junit.Ignore;
import org.junit.runner.Description;
import static org.junit.Assert.assertEquals;

public class TestRunner {
  @Test
  public void testHello() {
    assertEquals("Hello", Main.Hello());
  }

  @Test
  public void testWorld() {
    assertEquals("World", Main.Hello());
  }

  public static void main(String[] args) {
    JUnitCore runner = new JUnitCore();
    runner.addListener(new TestListener());
    Result result = runner.run(TestRunner.class);
  }
}

class TestListener extends RunListener {
  long startTime;
  long endTime;

  @Override
  public void testRunStarted(Description description) {
      startTime = new Date().getTime();
      System.out.println("Starting Tests...\n");
  }
  @Override
  public void testRunFinished(Result result) throws Exception {
      endTime = new Date().getTime();
      System.out.println("Tests Completed!");
      System.out.printf("Number of test cases: %d%n", result.getRunCount());
      if (result.wasSuccessful()) {
        System.out.println("All tests passed! Congrats!");
      } else {
        System.out.printf("Failed %d/%d tests, see above for more information%n", result.getFailureCount(), result.getRunCount());
      }
      long elapsedSeconds = (endTime - startTime) / 1000;
      System.out.printf("Elapsed time of tests execution: %s seconds%n", elapsedSeconds);
  }
  @Override
  public void testStarted(Description description) {
      System.out.printf("%s test is starting...%n", description.getMethodName());
  }
  @Override
  public void testFinished(Description description) {
      System.out.printf("%s test is finished...%n%n", description.getMethodName());
  }
  @Override
  public void testFailure(Failure failure) {
      System.out.printf("%s test FAILED!%n", failure.getDescription().getMethodName());
      System.out.println(failure.getMessage());
  }
  @Override
  public void testIgnored(Description description) throws Exception {
      super.testIgnored(description);
      Ignore ignore = description.getAnnotation(Ignore.class);
      System.out.printf("@Ignore test method '%s()': '%s'%n", description.getMethodName(), ignore.value());
  }
}
