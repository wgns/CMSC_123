/**
 * Created by Jay Vince Serato and Wina Gen Sotto on 10/21/2016.
 */
public interface MyQueue<E> {
    public void enqueue(E item);

    public E dequeue();

    public E front();

    public boolean isEmpty();

    public int size();

    public int getRear();
}