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
  //Start and End time of the tests
  long startTime;
  long endTime;

  @Override
  public void testRunStarted(Description description) {
      //Start time of the tests
      startTime = new Date().getTime();
      //Print the number of tests before the all tests execution.
      System.out.println("Tests started! Number of Test case: " + description.testCount() + "\n");
  }
  @Override
  public void testRunFinished(Result result) throws Exception {
      //End time of the tests
      endTime = new Date().getTime();
      //Print the below lines when all tests are finished.
      System.out.println("Tests finished! Number of test case: " + result.getRunCount());
      long elapsedSeconds = (endTime - startTime) / 1000;
      System.out.println("Elapsed time of tests execution: " + elapsedSeconds + " seconds");
  }
  @Override
  public void testStarted(Description description) {
      //Write the test name when it is started.
      System.out.println(description.getMethodName() + " test is starting...");
  }
  @Override
  public void testFinished(Description description) {
      //Write the test name when it is finished.
      System.out.println(description.getMethodName() + " test is finished...\n");
  }
  @Override
  public void testFailure(Failure failure) {
      //Write the test name when it is failed.
      System.out.println(failure.getDescription().getMethodName() + " test FAILED!");
      System.out.println(failure.getMessage());
  }
  @Override
  public void testIgnored(Description description) throws Exception {
      super.testIgnored(description);
      Ignore ignore = description.getAnnotation(Ignore.class);
      String ignoreMessage = String.format(
          "@Ignore test method '%s()': '%s'",
          description.getMethodName(), ignore.value());
      System.out.println(ignoreMessage + "\n");
  }
}
