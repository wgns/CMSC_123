import java.util.EmptyStackException;

/**
 * Created by Jay Vince Serato and Wina Gen Sotto on October 25, 2016.
 */
public class ListStack<E> implements MyStack<E> {
    private MyList<E> lst;

    public ListStack() {
        this.lst = new LList<E>();
    }

    @Override
    public void push(E item) {
        lst.add(0, item);
    }

    @Override
    public E pop() {
        try {
            E item = lst.get(0);
            lst.remove(0);
            return item;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    @Override
    public E peek() {
        try {
            return lst.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    @Override
    public int size() {
        return lst.size();
    }

    @Override
    public boolean isEmpty() {
        return lst.size() == 0;
    }
}
