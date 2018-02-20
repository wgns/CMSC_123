import java.util.*;

/**
 * Created by Wina Gen Sotto on December 10, 2016.
 */

public class MyNode {
   private MyNode parent;
   private LinkedList<MyNode> contents;
   private FileDescriptor description;

   public MyNode(String filename) {
      parent = null;
      description = new FileDescriptor(filename);

      if (description.getType() instanceof FileFileType) {
         contents = null;
      } else {
         contents = new LinkedList<>();
      }
   }

   public MyNode(MyNode parent, String filename) {
      this.parent = parent;
      description = new FileDescriptor(filename);

      if (description.getType() instanceof FileFileType) {
         contents = null;
      } else {
         contents = new LinkedList<>();
      }
   }

   public MyNode getParent() {
      return parent;
   }

   public LinkedList<MyNode> getContents() {
      return contents;
   }

   public void setContents(LinkedList<MyNode> contents) {
      this.contents = contents;
   }

   public FileDescriptor getDescription() {
      return description;
   }

   public void editFileName(String newFileName) {
      description.setFileName(newFileName);
      description.setDateModified();
   }

   public void addContents(MyNode newContent) {
      contents.addLast(newContent);
      description.setDateModified();
   }

   public void editContent(String newContent) {
      if (description.getContent().equals("")) {
         description.setContent(newContent);
      } else {
         description.setContent(description.getContent() + "\n" + newContent);
      }
      description.setDateModified();
   }

   public String seeContents() {                   // for ls
      String content = "";

      for (int i = 0; i < contents.size(); i++) {
         if (contents.get(i).description.getType() instanceof FileFileType) {
            content += contents.get(i).description.getFileName() + "\n";
         } else {
            content += contents.get(i).description.getFileName() + "/\n";
         }
      }

      return content;
   }
}
