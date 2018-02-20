/**
 * Created by Wina Gen Sotto on December 11, 2016.
 */
public abstract class FileType {
   String typename;

   public FileType(String fileExtension) {
      typename = fileExtension;
   }

   public String getFileType() {
      return typename;
   }
}
