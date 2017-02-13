package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains only the elements that are in both sets") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      val s234 = union(union(s2, s3), s4)
      val s23 = intersect(s123, s234)

      assert(contains(s23, 2), "Intersection 2, 3 contains 2")
      assert(contains(s23, 3), "Intersection 2, 3 contains 3")
      assert(!contains(s23, 1), "Intersection 2, 3 does not contain 1")
      assert(!contains(s23, 4), "Intersection 2, 3 does not contain 4")
    }
  }

  test("diff contains only the elements in the first set that are not in the second set") {
    new TestSets {
      val s1234 = union(union(union(s1, s2), s3), s4)
      val s23 = union(s2, s3)
      val s14 = diff(s1234, s23)

      assert(contains(s14, 1), "Difference of 1, 2, 3 and 2, 3, 4 contains 1")
      assert(contains(s14, 4), "Difference of 1, 2, 3 and 2, 3, 4 contains 4")
      assert(!contains(s14, 2), "Difference of 1, 2, 3 and 2, 3, 4 does not contain 2")
      assert(!contains(s14, 3), "Difference of 1, 2, 3 and 2, 3, 4 does not contain 3")


    }
  }

  test("isEven returns true when the argument is even; false when it's not") {
    new TestSets {
      assert(isEven(4), "4 is even")
      assert(!isEven(33), "33 is not even")
    }
  }

  test("filter applies the isEven filter correctly") {
    new TestSets {
      val s1234 = union(union(union(s1, s2), s3), s4)
      val s24 = filter(s1234, isEven)

      assert(contains(s24, 2), "isEven-filtered set of 1,2,3,4 contains 2")
      assert(contains(s24, 4), "isEven-filtered set of 1,2,3,4 contains 4")
      assert(!contains(s24, 1), "isEven-filtered set of 1,2,3,4 does not contain 1")
      assert(!contains(s24, 3), "isEven-filtered set of 1,2,3,4 does not contain 3")

    }
  }

  test("forall works as expected") {
    new TestSets {
      val s24 = union(s2, s4)
      val s1234 = union(union(union(s1, s2), s3), s4)

      assert(forall(s24, isEven), "forAll returns 'true' when 'isEven' is applied to the set of 2, 4")
      assert(!forall(s1234, isEven), "forAll returns 'false' when 'isEven' is applied to the set of 1, 2, 3, 4")
    }
  }

  test("exists works as expected") {
    new TestSets {
      val s34 = union(s3, s4)
      val s13 = union(s1, s3)

      assert(exists(s34, isEven), "exists returns 'true' when 'isEven' is applied to the set of 3, 4")
      assert(!exists(s13, isEven), "exists returns 'false'when 'isEven' is applied to the set of 1, 3")
    }
  }

  test("map works as expected") {
    new TestSets {
      val s1234 = union(union(union(s1, s2), s3), s4)
      val squares = map(s1234, x => x * x)

      assert(contains(squares, 1), "map of squaring set of 1, 2, 3, 4 contains 1")
      assert(contains(squares, 4), "map of squaring set of 1, 2, 3, 4 contains 4")
      assert(contains(squares, 9), "map of squaring set of 1, 2, 3, 4 contains 9")
      assert(contains(squares, 16), "map of squaring set of 1, 2, 3, 4 contains 16")

    }
  }

}
