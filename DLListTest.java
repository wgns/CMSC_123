import junit.framework.*;
import java.util.Iterator;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class DLListTest extends TestCase{

  private DLList<String> lst;
  private Iterator<String> it;
  
  public void setUp() {
    lst = new DLList<>();
    lst.add("Ant");
    lst.add("Brick");
    lst.add("Christmas");
    it = lst.iterator();
  }
  
  public void testAddLast(){
    lst.add(3, "Diameter");
    assertEquals("Added a last item which is \"Diameter\".", "Diameter", lst.getTail().getData());
  }

  public void testRemoveMid(){
    lst.remove(1);
    assertEquals("Removed the middle item which is \"Brick\". The second element is now \"Christmas\".", "Christmas", lst.get(1));
  }

  public void testAddMid(){
    lst.add(1, "Ball");
    assertEquals("Added a second item which is \"Ball\".", "Ball", lst.get(1));
    assertEquals("The third element is now \"Brick\".", "Brick", lst.get(2));
  }

  public void testSetMid(){
    lst.set(1, "Balloon");
    assertEquals("Changed a second item and it is now \"Balloon\".", "Balloon", lst.get(1));
  }

  public void testSetFirst(){
    lst.set(0, "Avocado");
    assertEquals("Changed the first item and it is now \"Avocado\".", "Avocado", lst.get(0));
  }

  public void testSetLast(){
    lst.set(2, "Chocolate");
    assertEquals("Changed the last item and it is now \"Chocolate\".", "Chocolate", lst.get(2));
  }
  
  public void testRemoveTail(){
    lst.add("Diameter");
    lst.remove(3);
    assertEquals("Removed the last item which is \"Diameter\". The tail is now \"Christmas\".", "Christmas", lst.getTail().getData());
  }

  public void testRemoveHead(){
    lst.remove(0);
    assertEquals("Removed the first item which is \"Ant\". The head is now \"Brick\".", "Brick", lst.getHead().getData());
  }
  
  public void testRemoveElementOfTail(){
    lst.add("Diameter");
    lst.remove("Diameter");
    assertEquals("Removed the last item which is \"Diameter\". The tail is now \"Christmas\".", "Christmas", lst.getTail().getData());
  }
  
  public void testNextOne(){
    assertEquals("First element is \"Ant\".", "Ant", it.next());
  }
  
  public void testNextTwo(){
    assertEquals("First element is \"Ant\".", "Ant", it.next());
    assertEquals("Second element is \"Brick\".", "Brick", it.next());
  }
  
  public void testNextThree(){
    assertEquals("First element is \"Ant\".", "Ant", it.next());
    assertEquals("Second element is \"Brick\".", "Brick", it.next());
    assertEquals("Third element is \"Christmas\".", "Christmas", it.next());
  }
  
  public void testRemoveFirst(){
    assertEquals("First element is \"Ant\".", "Ant", it.next());
    it.remove();
    assertEquals("Removed \"Ant\". Now the first element is \"Brick\".", "Brick", it.next());
  }
  
  public void testRemoveWithoutNext(){
    try{
      it.remove();
      fail("Should've thrown an exception.");
    } catch (IllegalStateException e){
      // expected!
    }
  }
  
  public void testCharAt(){
    assertEquals("The second letter of the first element, which is \"Ant\", is 'n'", 'n' ,lst.get(0).charAt(1));
  }
  
  public void testRemoveTwice(){
    it.next();
    it.remove();
    try{
      it.remove();
      fail("Should've thrown an exception.");
    } catch (IllegalStateException e){
      // expected!
    }
  }
}