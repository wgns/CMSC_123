public interface MyList<E> extends Iterable {
   public void add(E item);
   public void add(int i, E item);
   public void remove(int i);
   public void remove(E item);
   public E get(int i);
   public void set(int i, E item);
   public int size = 0;
}