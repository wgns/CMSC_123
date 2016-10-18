import java.util.*;

public class DLList<E> implements MyList<E> {
  private MyNode head;
  private MyNode tail;
  private int size;
  
  public DLList()
  {
    head = null;
    tail = null;
    size = 0;
  }
  
  public void checkIfInside(int i)
  {
     if (i > size)
     {
        throw new IndexOutOfBoundsException();
     }
  }
  
  public void checkIfInsideInGet(int i)
  {
     if (size == 0 || i >= size)
     {
        throw new IndexOutOfBoundsException();
     }
  } 
   
  public void add(E item) {
     add(size, item);
  }
  
  public void add(int i, E item) {
    checkIfInside(i);
    MyNode newNode = new MyNode(item, null, null);
    
    if (size == 0 && i == 0){ // Add first on empty list
       head = newNode;
       tail = newNode;
    }
    else if (size > 0 && i == 0){ // Add first on nonempty list
       newNode.setNext(head);
       head = newNode;
    }
    else if (i == size) { // Append
       newNode.setPrev(tail);
       tail.setNext(newNode);
       tail = newNode;
    }
    else{ // General Insert
       MyNode cursor = head;
       while(--i > 0){
          cursor = cursor.getNext();
       }
       newNode.setNext(cursor.getNext());
       cursor.setNext(newNode);
    }
    size++;
  }
  
  public void remove(int i){
     MyNode rem1;
     MyNode rem2;
     MyNode current = head;

     if (i == size - 1) { // Remove tail
        rem1 = tail;
        current = rem1.getPrev();
        rem1.setPrev(null);
        current.setNext(null);
        tail = current;
     }
     else if (i > 0){ // General remove
        if (i > size / 2) { // Removing from tail
           current = tail;
           for (int j = size - 1; j > i; j--){
              current = current.getPrev();
           }
        }
        else { // Removing from head
           for (int j = 0; j < i; j++){
              current = current.getNext();
           }
        }

        rem1 = current.getPrev();
        rem2 = current.getNext();
        rem1.setNext(rem2);
        rem2.setPrev(rem1);
        current.setPrev(null);
        current.setNext(null);
     }
     else if (i == 0 && size == 1) { // Removing head/tail on only one element
         tail = null;
         head = null;
     }
     else{ // Removing the head
         current = head;
         head = head.getNext();
         current.setNext(null);
     }
     size--;
  }
  
  public void remove(E item){
     MyNode rem = head;
     for(int i = 0; i < size; i++){
         String c1 = (String) rem.getData();
         String c2 = (String) item;
         if (Arrays.equals(c1.toCharArray(), c2.toCharArray())) {
             remove(i);
             break;
         }
         rem = rem.getNext();
     }
  }
  
  public E get(){
     return get(size);
  }
  
  public E get(int index){
     checkIfInsideInGet(index);

     MyNode current;
     if (index < size / 2) {
        current = head;
        for (int i = 0; i < index; i++) {
           current = current.getNext();
        }
     } else {
        current = tail;
        for (int i = size - 1; i > index; i--) {
           current = current.getPrev();
        }
     }

     return current.getData();
  }
  
  public void set(int index, E item){
     checkIfInsideInGet(index);
    
     MyNode current;
     if (index < size / 2) {
        current = head;
        for (int i = 0; i < index; i++) {
           current = current.getNext();
        }
     } else {
        current = tail;
        for (int i = size - 1; i > index; i--) {
           current = current.getPrev();
        }
     }
    current.setData(item);
  }
  
  public MyNode getTail(){
    return tail;
  }

   public MyNode getHead() {
      return head;
   }

   protected class MyNode {
    private E data;
    private MyNode next;
    private MyNode prev;
    
    public MyNode(E d, MyNode n, MyNode p) {
      data = d;
      next = n;
      prev = p;
    }
    
    public E getData() {
      return data;
    }
    
    public void setData(E data) {
      this.data = data;
    }
    
    public MyNode getNext() {
      return next; 
    }
    
    public void setNext(MyNode next) {
      this.next = next;
    }

    public MyNode getPrev() {
      return prev;
    }

    public void setPrev(MyNode prev) {
      this.prev = prev;
    }


  }
  
  public Iterator iterator() {
    return new LListIterator(head);
  }
  
  private class LListIterator implements Iterator<E> {
    private MyNode prev;
    private MyNode last;
    private MyNode next;
    private boolean canRemove = false;
    
    public LListIterator(MyNode head) {
        prev = null;
        last = null;
        next = head;
    }
    
    public E next() {
      if (next == null) {
        throw new NoSuchElementException();
      }
      
      E data = next.getData();
      prev = last;
      last = next;
      next = next.getNext();
      canRemove = true;
      return data;
    }
    
    public boolean hasNext() {
      return next != null;
    }
    
    public void remove() {
      if (!(canRemove)){
        throw new IllegalStateException();
      }
      canRemove = false;
      if (prev == null){
        head = next;
      }
      else{
        prev.setNext(next);
      }
      last.setNext(null);
      last = null;
    }
  }
}
