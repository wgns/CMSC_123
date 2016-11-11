import junit.framework.TestCase;
import org.junit.Test;


public class BSTTest extends TestCase {
    BST tree;

    @Override
    protected void setUp() throws Exception {
        tree = new BST();
    }

    @Test
    public void testAdd() throws Exception {
        tree.add(15);
        assertTrue(tree.contains(15));
    }

    @Test
    public void testAddMany() throws Exception {
        tree.add(5);
        tree.add(10);
        tree.add(30);
        tree.add(20);
        tree.add(40);
        assertEquals(tree.toString(), "5 10 20 30 40 ");
    }

    @Test
    public void testRemoveNodeWithOneChildAtRight() throws Exception {
        tree.add(15);
        tree.add(20);
        tree.remove(15);
        assertFalse(tree.contains(15));
        assertTrue(tree.contains(20));
    }

    @Test
    public void testRemoveNodeWithOneChildAtLeft() throws Exception {
        tree.add(20);
        tree.add(15);
        tree.remove(20);
        assertFalse(tree.contains(20));
        assertTrue(tree.contains(15));
    }

    @Test
    public void testRemoveLeaf() throws Exception {
        tree.add(15);
        tree.add(20);
        tree.add(45);
        tree.add(2);
        tree.add(180);
        tree.add(90);
        tree.remove(90);
        assertFalse(tree.contains(90));
        assertTrue(tree.contains(15));
        assertTrue(tree.contains(45));
        assertTrue(tree.contains(180));
    }

    @Test
    public void testRemoveNodeWithTwoChildren() throws Exception {
        tree.add(15);
        tree.add(20);
        tree.add(45);
        tree.add(2);
        tree.add(180);
        tree.add(17);
        tree.add(90);
        tree.add(31);
        tree.remove(20);
        assertEquals("Replaced 20 to 31.", new Integer(31), tree.root.getRight().getData());
        assertEquals("Tree is ...", "2 15 17 31 45 90 180 ", tree.toString());
    }

    @Test
    public void testRemoveNonExistingNumber() throws Exception {
        tree.add(5);
        tree.add(10);
        tree.add(30);
        tree.add(20);
        tree.add(40);
        try {
            tree.remove(50);
            fail("Should've thrown an exception!");
        }
        catch (Exception e) {
            // expected!
        }
    }

    @Test
    public void testContains() throws Exception {
        tree.add(5);
        tree.add(10);
        tree.add(30);
        tree.add(20);
        tree.add(40);
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(16));
    }
}