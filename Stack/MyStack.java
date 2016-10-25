/**
 * Created by Wina Gen Sotto and Jay Vince Serato on October 25, 2016.
 */

public interface MyStack<E> {

    public void push(E item);

    public E pop();

    public E peek();

    public int size();

    public boolean isEmpty();
}