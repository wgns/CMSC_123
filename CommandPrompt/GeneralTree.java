import java.util.*;

/**
 * Created by Wina Gen Sotto on December 10, 2016.
 */

public class GeneralTree {
   private MyNode root;

   public GeneralTree() {
      root = new MyNode("root");
   }

   public MyNode root() {
      return root;
   }

   public void insert(MyNode current, String fileName) {
      current.addContents(new MyNode(current, fileName));
   }

   public void insert(MyNode current, MyNode newNode) {
      current.addContents(newNode);
   }

   public MyNode newDirectory(String fileName) {
      ArrayDeque<MyNode> folders = new ArrayDeque<>();

      for (MyNode n : root.getContents()) {
         if (n.getDescription().getType() instanceof FolderFileType) {
            folders.add(n);
         }

         if (n.getDescription().getFileName().equals(fileName)) {
            return n;
         }
      }

      while (!folders.isEmpty()) {
         MyNode n = folders.poll();

         for (MyNode n2 : n.getContents()) {
            if (n2.getDescription().getType() instanceof FolderFileType) {
               folders.add(n2);
            }

            if (n2.getDescription().getFileName().equals(fileName)) {
               return n2;
            }
         }
      }

      return null;
   }

   public boolean delete(String fileName) {
      ArrayDeque<MyNode> folders = new ArrayDeque<>();

      for (MyNode n : root.getContents()) {
         if (n.getDescription().getType() instanceof FolderFileType) {
            folders.add(n);
         }

         if (n.getDescription().getFileName().equals(fileName)) {
            LinkedList<MyNode> temp = root.getContents();
            temp.remove(n);
            root.setContents(temp);
            return true;
         }
      }

      while (!folders.isEmpty()) {
         MyNode n = folders.poll();

         for (MyNode n2 : n.getContents()) {
            if (n2.getDescription().getType() instanceof FolderFileType) {
               folders.add(n2);
            }

            if (n2.getDescription().getFileName().equals(fileName)) {
               LinkedList<MyNode> temp = root.getContents();
               temp.remove(n);
               root.setContents(temp);
               return true;
            }
         }
      }

      return false;
   }

   public boolean search(String fileName) {
      ArrayDeque<MyNode> folders = new ArrayDeque<>();

      for (MyNode n : root.getContents()) {
         if (n.getDescription().getType() instanceof FolderFileType) {
            folders.add(n);
         }

         if (n.getDescription().getFileName().equals(fileName)) {
            return true;
         }
      }

      while (!folders.isEmpty()) {
         MyNode n = folders.poll();

         for (MyNode n2 : n.getContents()) {
            if (n2.getDescription().getType() instanceof FolderFileType) {
               folders.add(n2);
            }

            if (n2.getDescription().getFileName().equals(fileName)) {
               return true;
            }
         }
      }

      return false;
   }
}
