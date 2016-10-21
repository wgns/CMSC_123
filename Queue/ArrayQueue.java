import java.util.NoSuchElementException;

/**
 * Created by Jay Vince Serato and Wina Gen Sotto on 10/21/2016.
 */
public class ArrayQueue<E> implements MyQueue<E> {
    private MyList list;
    private int front;
    private int rear;
    private int size;

    public static final int MAX_CAPACITY = 10;

    public ArrayQueue() {
        this.list = new AList(MAX_CAPACITY);
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(E item) {
        if ((++rear == MAX_CAPACITY && front == 0) || (rear % MAX_CAPACITY == front && !(isEmpty()))){
            throw new IllegalStateException("Number of items exceed array size!");
        } else if (rear == MAX_CAPACITY) {
            rear = 0;
            System.out.println("ENTER");
        }
        System.out.println(front + " is front");
        list.set(rear, item);
        System.out.println(rear + " at " + item);
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new NoSuchElementException("List is empty!");
        }
        size--;
        System.out.println(front + " before increment");
        E item = (E) list.get(front++);
        if (front == MAX_CAPACITY){
            front = 0;
        }
        return item;
    }

    @Override
    public E front() {
        return (E) list.get(front);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public int getRear() {
        return rear;
    }
}
