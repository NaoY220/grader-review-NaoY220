import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testSimple() {
    List<String> left = Arrays.asList("a");
    List<String> right = Arrays.asList("b");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "b");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testSkips() {
    List<String> left = Arrays.asList("a", "i", "z");
    List<String> right = Arrays.asList("e", "y");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "e", "i", "y", "z");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testIdentical() {
    List<String> left = Arrays.asList("a", "i", "z");
    List<String> right = Arrays.asList("a", "i", "z");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "i", "i", "z", "z");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeLeftEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(right, left);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testFilterSimpleTrue() {
    List<String> input = Arrays.asList("moon");
    List<String> output = ListExamples.filter(input, new IsMoon());
    List<String> expected = Arrays.asList("moon");
    assertEquals(expected, output);
  }

  @Test(timeout = 500)
  public void testFilterSimpleFalse() {
    List<String> input = Arrays.asList("2312iiojne");
    List<String> output = ListExamples.filter(input, new IsMoon());
    List<String> expected = Arrays.asList();
    assertEquals(expected, output);
  }

  @Test(timeout = 500)
  public void testFilterSimpleCase() {
    List<String> input = Arrays.asList("MoOn");
    List<String> output = ListExamples.filter(input, new IsMoon());
    List<String> expected = Arrays.asList("MoOn");
    assertEquals(expected, output);
  }

  @Test(timeout = 500)
  public void testFilterComplexTrue() {
    List<String> input = Arrays.asList("312321", "MoOn", "moon", "cheese", "12312");
    List<String> output = ListExamples.filter(input, new IsMoon());
    List<String> expected = Arrays.asList("MoOn", "moon");
    assertEquals(expected, output);
  }

  @Test(timeout = 500)
  public void testFilterComplexFalse() {
    List<String> input = Arrays.asList("312321", "123", "m2112oon", "cHeese", "12312");
    List<String> output = ListExamples.filter(input, new IsMoon());
    List<String> expected = Arrays.asList();
    assertEquals(expected, output);
  }

  @Test(timeout = 500)
  public void testFilterInterspersed() {
    List<String> input = Arrays.asList("moon", "sjdasi", "MOOn", "dasdas", "moon", "moon", "cHeese", "12312");
    List<String> output = ListExamples.filter(input, new IsMoon());
    List<String> expected = Arrays.asList("moon", "MOOn", "moon", "moon");
    assertEquals(expected, output);
  }




}
