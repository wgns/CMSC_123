import junit.framework.TestCase;

import static org.junit.Assert.*;

/**
 * Created by localuser on 11/22/16.
 */
public class SplayTreeTest extends TestCase{
    SplayTree tree;
    public void setUp() {
        tree = new SplayTree();
    }

    public void testOk() throws Exception{
        tree.add(10);
        tree.add(15);
        tree.add(20);
        System.out.println("ORIGINAL: ");
        tree.print();
        tree.splay(20);
        System.out.println("FINAL: ");
        tree.print();
        assertTrue(tree.root().equals(20));
    }
}