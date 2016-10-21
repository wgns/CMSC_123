import junit.framework.TestCase;

import java.util.NoSuchElementException;

/**
 * Created by Jay Vince Serato and Wina Gen Sotto on 10/21/2016.
 */
public class ArrayQueueTest extends TestCase {
    ArrayQueue<String> list;

    public void setUp() {
        list = new ArrayQueue<>();
    }

    public void testEnqueue() {
        list.enqueue("Alligator");
        assertEquals("Queued first item which is \"Alligator\".", "Alligator", list.front());
    }

    public void testDequeue() {
        list.enqueue("Alligator");
        list.enqueue("Bear");
        assertEquals("Dequeued the first element which is \"Alligator\"." , "Alligator", list.dequeue());
        assertEquals("Dequeued the first element which is \"Bear\"." , "Bear", list.dequeue());
    }

    public void testIsEmpty() {
        assertEquals("The list is indeed empty.", true, list.isEmpty());
        list.enqueue("Alligator");
        assertEquals("The list is no longer empty.", false, list.isEmpty());
    }

    public void testDequeueOnEmptyList() {
        try {
            list.dequeue();
            fail("Should have thrown an exception!");
        } catch (NoSuchElementException e) {
            // expected!
        }
    }

    public void testDequeueOnAnotherEmptyList() {
        list.enqueue("Alligator");
        list.dequeue();
        try {
            list.dequeue();
            fail("Should have thrown an exception!");
        } catch (NoSuchElementException e) {
            // expected!
        }
    }

    public void testCircularReachedMaximum() {
        list.enqueue("Alligator");
        list.enqueue("Bear");
        list.enqueue("Cat");
        list.enqueue("Dog");
        list.enqueue("Eagle");
        list.enqueue("Frog");
        list.enqueue("Giraffe");
        list.enqueue("Horse");
        list.enqueue("Iguana");
        list.enqueue("Jaguar");
        try {
            list.enqueue("Kangaroo");
            fail("Exceeds list length!");
        } catch (IllegalStateException e) {
            // expected!
        }
    }

    public void testCircularDequeuedFrontChange() {
        list.enqueue("Alligator");
        list.enqueue("Bear");
        list.enqueue("Cat");
        list.dequeue();
        assertEquals("Dequeued \"Alligator\". The front is now \"Bear\".", "Bear", list.front());
    }

    public void testCircularWithDequeue() {
        list.enqueue("Alligator");
        list.enqueue("Bear");
        list.enqueue("Cat");
        list.enqueue("Dog");
        list.enqueue("Eagle");
        list.dequeue();
        list.enqueue("Frog");
        list.enqueue("Giraffe");
        list.enqueue("Horse");
        list.enqueue("Iguana");
        list.enqueue("Jaguar");
        list.enqueue("Kangaroo");
        assertEquals("Added \"Kangaroo\" at index 0", 0, list.getRear());
    }

    public void testCircularWithDequeueExceeds() {
        list.enqueue("Alligator");
        list.enqueue("Bear");
        list.enqueue("Cat");
        list.enqueue("Dog");
        list.enqueue("Eagle");
        list.dequeue();
        list.enqueue("Frog");
        list.enqueue("Giraffe");
        list.enqueue("Horse");
        list.enqueue("Iguana");
        list.enqueue("Jaguar");
        list.enqueue("Kangaroo");
        try{
            list.enqueue("Leopard");
            fail("Should have thrown an exception.");
        } catch (IllegalStateException e) {
            // expected!
        }
    }

    public void testCircularWithEnqueue() {
        list.enqueue("Alligator");
        list.enqueue("Bear");
        list.enqueue("Cat");
        list.enqueue("Dog");
        list.enqueue("Eagle");
        list.enqueue("Frog");
        list.enqueue("Giraffe");
        list.enqueue("Horse");
        list.enqueue("Iguana");
        list.enqueue("Jaguar");

        list.dequeue(); // Bear
        list.dequeue(); // Cat
        list.dequeue(); // Dog
        list.dequeue(); // Eagle
        list.dequeue(); // Frog
        list.dequeue(); // Giraffe
        list.dequeue(); // Horse
        list.dequeue(); // Iguana
        list.dequeue(); // Jaguar is front here index 9

        assertEquals("Front is now \"Kangaroo\".", "Jaguar", list.front());

        list.enqueue("Kangaroo");
        list.enqueue("Leopard");
        list.enqueue("Monkey");
        list.enqueue("Nightingale");
        list.enqueue("Octopus");
        list.enqueue("Platypus");
        list.enqueue("Quail");

        assertEquals("Front is now \"Kangaroo\".", "Jaguar", list.front());
        list.dequeue();
        assertEquals("Front is now \"Kangaroo\".", "Kangaroo", list.front());
    }
}
