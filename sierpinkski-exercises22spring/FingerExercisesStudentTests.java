import static org.junit.Assert.*;
import org.junit.*;

public class FingerExercisesStudentTests {

  /*** GCD Test ***/
  @Test
  public void TestGCDFortyOneIsHalfAnother() {
    int expected = 49;
    int actual = FingerExercises.gcd(98, 49);
    assertEquals("gcd of 49 and 98", expected, actual);
  }

  /*** Sum Between Test ***/
  @Test
  public void TestSumBetweenEightFourteen() {
    int expected = 77;
    int actual = FingerExercises.sumBetween(8, 14);
    assertEquals("sum between 8 and 14", expected, actual);
  }

  /*** Sum To Test ***/
  @Test
  public void TestSumToTwentyTwo() {
    int expected = 253;
    int actual = FingerExercises.sumTo(22);
    assertEquals("sum to 22", expected, actual);
  }

  /*** Find Second Largest Test ***/
  @Test
  public void TestFindSecondLargestDuplicate8() {
    int expected = 8;
    int actual = FingerExercises.findSecondLargest(new int[] {8, 8, 2});
    assertEquals("second largest of [8, 8, 2]", expected, actual);
  }

  /*** Sum of Digits Test ***/
  @Test
  public void TestSumOfDigits15098() {
    int expected = 23;
    int actual = FingerExercises.sumOfDigits(15098);
    assertEquals("sum of digits 15098", expected, actual);
  }

  /*** Climb Stairs Test ***/
  @Test
  public void TestClimbStairsSix() {
    int expected = 13;
    int actual = FingerExercises.countWaysToClimb(6);
    assertEquals("ways to climb 6 stairs", expected, actual);
  }

  /*** Log Test ***/
  @Test
  public void TestLogBaseTwoOfSeventy() {
    int expected = 6;
    int actual = FingerExercises.log(2, 70);
    assertEquals("log base 2 of 70", expected, actual);
  }

  /*** Substring Test ***/
  @Test
  public void TestSubstringNoneFound() {
    int expected = 0;
    int actual = FingerExercises.countSubstrings("hi", "cis110");
    assertEquals("count of hi in cis110", expected, actual);
  }
  
}
