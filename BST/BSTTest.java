import junit.framework.TestCase;

/**
 * Created by Sotto and Serato on November 08, 2016.
 */
public class BSTTest extends TestCase {
    BST tree;
    public void setUp() {
        tree = new BST();
    }

    public void testAdd() throws Exception {
        tree.add("3");
        assertEquals("\"3\" is in the root.", "3", tree.root.getData());
        tree.add("2");
        assertEquals("\"2\" is in the left of the root.", "2", tree.root.getLeft().getData());
        tree.add("5");
        assertEquals("\"5\" is i the right of the root.", "5", tree.root.getRight().getData());
        tree.add("4");
        assertEquals("\"4\" is in the left of the right of the root.", "4", tree.root.getRight().getLeft().getData());
    }

    public void testToString() throws Exception {
        tree.add("34");
        tree.add("3");
        tree.add("9");
        tree.add("45");
        tree.add("18");
        tree.add("22");
        assertEquals("", "3 9 18 22 34 45 ", tree.toString(tree.root));
    }

    public void testException() {
        try {
            tree.add("13");
            tree.add("7");
            tree.add("13");
            fail("Should've thrown an exception");
        } catch (Exception e) {
            // expected!
        }
    }
}