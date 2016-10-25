import junit.framework.TestCase;

import java.util.EmptyStackException;

/**
 * Created by Jay Vince Serato and Wina Gen Sotto on October 25, 2016.
 */
public class ListStackTest extends TestCase {
    ListStack<String> lst;

    public void setUp() {
        lst = new ListStack<>();
    }

    public void testPushAndPeek() {
        lst.push("Avocado");
        assertEquals("Added one item to the stack and the item was \"Avocado\".", "Avocado", lst.peek());
    }

    public void testPopAndIsEmpty() {
        lst.push("Avocado");
        assertEquals("Removed the recently added item which is \"Avocado\".", "Avocado", lst.pop());
        assertEquals("The stack is now empty.", true, lst.isEmpty());
    }

    public void testPopWhenEmpty() {
        try {
            lst.pop();
            fail("Should have thrown an exception.");
        } catch (EmptyStackException e) {
            // expected!
        }
    }

    public void testPeekWhenEmpty() {
        try {
            lst.peek();
            fail("Should have thrown an exception.");
        } catch (EmptyStackException e) {
            // expected!
        }
    }

    public void testCheckSize() {
        lst.push("Avocado");
        lst.push("Banana");
        lst.push("Cherry");
        lst.push("Durian");
        assertEquals("There are four items in the stack.", 4, lst.size());
    }
}