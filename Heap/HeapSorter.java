/**
 * Created by Wina Gen Sotto on November 25, 2016.
 */
public class HeapSorter {
   private int[] items;
   private int heapSize;
   private final int ROOT = 0;

   public HeapSorter(int[] items) {
      this.items = items;
      this.heapSize = 0;
   }

   public void sort() {
      for (int i = 0; i < items.length; i++) {
         add(items[i]);
      }

      for (int i = items.length - 1; i >= 0; i--) {
         items[i] = remove();
      }
   }

   public void add(int x) {
      items[heapSize] = x;
      bubbleUp(heapSize);
      heapSize++;
   }

   public void bubbleUp(int i) { // receives array index
      if (i != ROOT) {
         if (items[(i - 1) / 2] < items[i]) {
            swap(i, (i - 1) / 2);
         }

         bubbleUp((i - 1) / 2);
      }
   }

   public void swap(int i, int j) {
      int up = items[i];
      items[i] = items[j];
      items[j] = up;
   }

   public int parent(int i) {    // get index of parent
      return (i - 1) / 2;
   }

   public int remove() {
      int rem = items[ROOT];
      heapSize--;

      if (heapSize > 0) {
         items[ROOT] = items[heapSize];
         trickleDown(ROOT);
      }

      return rem;
   }

   public void trickleDown(int i) {    // checks all left??? nah ambot
      int j;

      if ((2 * i) + 1 >= heapSize) {
         j = i;
      } else if ((2 * i) + 2 == heapSize || items[(2 * i) + 1] > items[(2 * i) + 2]) {
         j = (2 * i) + 1;
      } else {
         j = (2 * i) + 2;
      }

      if (items[i] < items[j]) {
         swap(i, j);
         trickleDown(j);
      }
   }

   public boolean hasLeft(int i) {
      return (2 * i) + 1 < items.length;
   }

   public boolean hasRight(int i) {
      return (2 * i) + 2 < items.length;
   }

   public int right(int i) {     // get right child given index
      return items[(2 * i) + 2];
   }

   public int left(int i) {      // get left child given index
      return items[(2 * i) + 1];
   }

}