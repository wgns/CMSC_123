import java.util.Arrays;

/**
 * Created by Jay Vince Serato and Wina Gen Sotto on October 25, 2016.
 */

public class LList<E> implements MyList<E>
{
  private MyNode head;
  private int size;
  
  public LList() 
  {
    head = new MyNode();
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
    MyNode newNode = new MyNode(item, null);
    if (size == 0 && i == 0){
       head = newNode;
    }
    else if (size > 0 && i == 0){
       newNode.setNext(head);
       head = newNode;
    }
    else{
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
     MyNode rem = head;
     MyNode current = head;
     if (i > 0){
         for(int j = i; j > 0; j--){
             rem = rem.getNext();
         }
         for(int j = i - 1; j > 0; j--){
             current = current.getNext();
         }
         current.setNext(rem.getNext());
         rem.setNext(null);
     }
     else{
         current = head;
         head = head.getNext();
         current.setNext(null);
     }
     size--;
  }
  
  public void remove(E item){
     MyNode rem = head;
     MyNode current = head;
     for(int i = 0; i < size; i++){
         String c1 = (String) rem.getData();
         String c2 = (String) item;
         if (Arrays.equals(c1.toCharArray(), c2.toCharArray())) {
             if (i == 0){
                 current = head;
                 head = head.getNext();
                 current.setNext(null);
             }
             else{
                 for(int j = i - 1; j > 0; j--) {
                     current = current.getNext();
                 }
                 current.setNext(rem.getNext());
                 rem.setNext(null);
             }
             size--;
             break;
         }
         rem = rem.getNext();
     }
  }
  
  public E get(){
     return get(size);
  }
  
  public E get(int i){
    checkIfInsideInGet(i);
    MyNode cursor = head;
    while(i-- > 0){
       cursor = cursor.getNext();
    }
    return cursor.getData();
  }
  
  public void set(int i, E item){
     //UNUSED as for the moment
  }

  public int size() {
      return size;
  }

    private class MyNode{
     private MyNode next;
     private E data;
     private int size;
   
     public MyNode(){
        this.data = null;
        this.next = null;
        size = 0;
     }
   
     public MyNode(E data, MyNode next){
        this.data = data;
        this.next = next;
        size = 0;
     }
   
     public MyNode getNext(){
        return this.next;
     }
   
     public void setNext(MyNode next){
        this.next = next;
     }
   
     public E getData(){
        return this.data;
     }
    
     public void setData(E data){
        this.data = data;
     }
     
     public int getSize() {
         return size;
     }
  }
}
