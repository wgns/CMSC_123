import java.util.*;

public class AList<E> implements MyList<E>
{
    private E[] items;
    private int count;

    public AList(int MAX)
    {
        count = 0;
        items = (E[]) new Object[MAX];
    }

    public void add(E item) {
        items[count++] = item;
    }

    public void add(int i, E item) {
        count++;
        for (int j = count - 1; j > i; j--)
        {
            items[j] = items[j - 1];
        }
        items[i] = item;
    }

    public void remove(E item){
        for(int i = 0; i < count; i++)
        {
            if (items[i].equals(item))
            {
                for (int j = i + 1; j < count; j++)
                {
                    items[j - 1] = items[j];
                }
                count--;
                break;
            }
        }
    }

    public E get(int i) {
        return (E) items[i];
    }

    public void remove(int i) {
        if (i < count)
        {
            for (; i < count; i++)
            {
                items[i] = items[i+1];
            }
        }
        count--;
    }

    public void set(int i, E item) {
        items[i] = item;
    }

    public int size() {
        return count;
    }
}
