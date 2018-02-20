import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Wina Gen Sotto on December 11, 2016.
 */

public class FileDescriptor {
   private String fileName;
   private FileType type;
   private String dateCreated;
   private String dateModified;
   private String content;

   public FileDescriptor(String fileName) {
      char[] temp = fileName.toCharArray();
      boolean file = false;

      for (int i = 0; i < temp.length; i++) {
         if (temp[i] == '.') {
            this.fileName = fileName;
            type = new FileFileType(fileName.substring(i + 1));
            file = true;
            content = "";
            break;
         }
      }

      if (!file) {
         this.fileName = fileName;
         type = new FolderFileType();
         content = null;
      }

      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMM yyyy, HH:mm:ss a");
      dateCreated = dateModified = sdf.format(cal.getTime());
   }

   public String getFileName() {
      return fileName;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public FileType getType() {
      return type;
   }

   public String getDateCreated() {
      return dateCreated;
   }

   public String getDateModified() {
      return dateModified;
   }

   public void setDateModified() {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMM yyyy, HH:mm:ss a");
      dateModified = sdf.format(cal.getTime());
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String toString() {
      return "File Name: " + fileName +
          "\nFile Type: " + type.getFileType() +
          "\nCreated: " + dateCreated +
          "\nModified: " + dateModified;
   }
}
