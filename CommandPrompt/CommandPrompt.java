import java.io.*;
import java.util.*;

/**
 * Created by Wina Gen Sotto on December 11, 2016.
 * Read-a-file Mode not tested. Absolute path commands not available. Assumes that all file names are distinct :(.
 */

public class CommandPrompt {

   private static final String FILE_IN = "mp4.in";
   private static final String FILE_OUT = "mp4.out";

   public static void main(String[] args) {
      GeneralTree dir = new GeneralTree();
      MyNode current = dir.root();
      String mainAddress = "J:/root/";
      String currentAddress;
      String[] commands;
      Scanner sc = new Scanner(System.in);
      File fileIn = new File(FILE_IN);
      File fileOut = new File(FILE_OUT);
      boolean run = true;

      if (!fileIn.exists()) {    // Interactive Mode
         while (run) {
            currentAddress = "";
            Stack<String> newAddress = new Stack<>();
            MyNode temp = current;

            while (temp.getParent() != null) {
               newAddress.push(temp.getDescription().getFileName());
               temp = temp.getParent();
            }

            while (!newAddress.isEmpty()) {
               currentAddress += newAddress.pop() + "/";
            }

            System.out.print(mainAddress + currentAddress + ">");
            String input = sc.nextLine();
            commands = input.split("\\s+");
            boolean edit = false;

            switch (commands[0]) {
               case "mkdir":     // okay
                  if (commands.length == 1 || commands.length > 2) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     dir.insert(current, commands[1]);
                     current = dir.newDirectory(commands[1]);
                  }
                  break;
               case "rmdir":     // okay
                  if (commands.length == 1 || commands.length > 2) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (!dir.delete(commands[1])) {
                        System.out.println("The system cannot find the file specified.");
                     }
                  }
                  break;
               case "cd":        // okay
                  if (commands.length == 1 || commands.length > 2) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (!dir.search(commands[1])) {
                        System.out.println("The system cannot find the file specified.");
                     } else {
                        current = dir.newDirectory(commands[1]);
                     }
                  }
                  break;
               case "cd..":      // okay
                  if (current.getParent() != null) {
                     current = current.getParent();
                  }
                  break;
               case "edit":      // okay
                  if (commands.length == 1 || commands.length > 2) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (!dir.search(commands[1])) {
                        dir.insert(current, commands[1]);
                        current = dir.newDirectory(commands[1]);
                     } else {
                        current = dir.newDirectory(commands[1]);
                        System.out.println(current.getDescription().getContent());
                     }

                     String content = sc.nextLine();
                     current.editContent(content);
                     content = sc.nextLine();
                     current = current.getParent();
                     edit = true;
                  }
                  break;
               case "rm":        // okay
                  if (commands.length == 1 || commands.length > 2) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (commands[1].charAt(0) == '*') {
                        String fileType = commands[1].substring(2) + " File";
                        ArrayDeque<MyNode> toBeDeleted = new ArrayDeque<>();

                        for (MyNode n : current.getContents()) {
                           if (n.getDescription().getType().getFileType().equals(fileType)) {
                              toBeDeleted.add(n);
                           }
                        }

                        while (!toBeDeleted.isEmpty()) {
                           MyNode n = toBeDeleted.poll();
                           dir.delete(n.getDescription().getFileName());
                        }
                     } else if (commands[1].charAt(0) == '/') {
                        String[] address = commands[1].split("/");
                        if (!dir.delete(address[address.length - 1])) {
                           System.out.println("Cannot delete '" + address[address.length - 1] + "': No such file or directory.");
                        }
                     } else if (!dir.delete(commands[1])) {
                        System.out.println("Cannot delete '" + commands[1] + "': No such file or directory.");
                     }
                  }
                  break;
               case "rn":        // okay
                  if (commands.length <= 2 || commands.length > 3) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     current = dir.newDirectory(commands[1]);
                     current.editFileName(commands[2]);
                     current = current.getParent();
                  }
                  break;
               case "mv":        //
                  if (commands.length <= 2 || commands.length > 3) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (!dir.search(commands[1]) || !dir.search(commands[2])) {
                        System.out.println("The system cannot find the file specified.");
                     } else {
                        String[] address = commands[2].split("/");
                        MyNode move = dir.newDirectory(commands[1]);
                        MyNode newDir = dir.newDirectory(address[address.length - 1]);
                        dir.delete(commands[1]);
                        dir.insert(newDir, move);
                     }
                  }
                  break;
               case "cp":        // okay
                  if (commands.length <= 2 || commands.length > 3) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (!dir.search(commands[1])) {
                        System.out.println("The system cannot find the file specified.");
                     } else {
                        dir.insert(current, commands[2]);
                        MyNode copy = dir.newDirectory(commands[2]);
                        MyNode orig = dir.newDirectory(commands[1]);
                        copy.editContent(orig.getDescription().getContent());
                     }
                  }
                  break;
               case "ls":        // okay
                  if (commands.length == 1) {
                     System.out.println(current.seeContents());
                  } else {
                     String fileType = commands[1].substring(2) + " File";

                     for (MyNode n : current.getContents()) {
                        if (n.getDescription().getType().getFileType().equals(fileType)) {
                           System.out.println(n.getDescription().getFileName());
                        }
                     }
                  }

                  edit = true;
                  break;
               case "show":      // okay
                  if (commands.length == 1 || commands.length > 2) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (!dir.search(commands[1])) {
                        System.out.println("Cannot show '" + commands[1] + "': No such file or directory.");
                     } else {
                        current = dir.newDirectory(commands[1]);
                        System.out.println(current.getDescription().getContent());
                        current = current.getParent();
                     }
                  }
                  break;
               case "whereis":   // okay
                  if (commands.length == 1 || commands.length > 2) {
                     System.out.println("The syntax of the command is incorrect.");
                  } else {
                     if (!dir.search(commands[1])) {
                        System.out.println("The system cannot find the file specified.");
                     } else {
                        String lookAddress = "";
                        Stack<String> where = new Stack<>();
                        MyNode look = dir.newDirectory(commands[1]);

                        while (look.getParent() != null) {
                           where.push(look.getDescription().getFileName());
                           look = look.getParent();
                        }

                        while (!where.isEmpty()) {
                           lookAddress += where.pop() + "/";
                        }

                        lookAddress = lookAddress.substring(0, lookAddress.length() - 1);

                        System.out.println(mainAddress + lookAddress);
                     }
                  }
                  break;
               case "exit":      // okay
                  run = false;
                  break;
               default:
                  System.out.println("'" + commands[0] + "' is not recognized as a command.");
            }

            if (!edit) {
               System.out.println();
            }
         }
      }
      else {    // Read-a-file Mode
         BufferedReader br = null;

         try {
            if (!fileOut.exists()) {
               fileOut.createNewFile();
            }

            String currentLine;
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            br = new BufferedReader(new FileReader(FILE_IN));

            while ((currentLine = br.readLine()) != null) {
               commands = currentLine.split("\\s+");

               switch (commands[0]) {
                  case "mkdir":     // okay
                     if (commands.length == 1 || commands.length > 2) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        dir.insert(current, commands[1]);
                        current = dir.newDirectory(commands[1]);
                     }
                     break;
                  case "rmdir":     // okay
                     if (commands.length == 1 || commands.length > 2) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (!dir.delete(commands[1])) {
                           bw.write("The system cannot find the file specified.");
                        }
                     }
                     break;
                  case "cd":        // okay
                     if (commands.length == 1 || commands.length > 2) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (!dir.search(commands[1])) {
                           bw.write("The system cannot find the file specified.");
                        } else {
                           current = dir.newDirectory(commands[1]);
                        }
                     }
                     break;
                  case "cd..":      // okay
                     if (current.getParent() != null) {
                        current = current.getParent();
                     }
                     break;
                  case "edit":      // okay
                     if (commands.length == 1 || commands.length > 2) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (!dir.search(commands[1])) {
                           dir.insert(current, commands[1]);
                           current = dir.newDirectory(commands[1]);
                        } else {
                           current = dir.newDirectory(commands[1]);
                           bw.write(current.getDescription().getContent());
                        }

                        String content = sc.nextLine();
                        current.editContent(content);
                        content = sc.nextLine();
                        current = current.getParent();
                     }
                     break;
                  case "rm":        // okay
                     if (commands.length == 1 || commands.length > 2) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (commands[1].charAt(0) == '*') {
                           String fileType = commands[1].substring(2) + " File";
                           ArrayDeque<MyNode> toBeDeleted = new ArrayDeque<>();

                           for (MyNode n : current.getContents()) {
                              if (n.getDescription().getType().getFileType().equals(fileType)) {
                                 toBeDeleted.add(n);
                              }
                           }

                           while (!toBeDeleted.isEmpty()) {
                              MyNode n = toBeDeleted.poll();
                              dir.delete(n.getDescription().getFileName());
                           }
                        } else if (commands[1].charAt(0) == '/') {
                           String[] address = commands[1].split("/");
                           if (!dir.delete(address[address.length - 1])) {
                              bw.write("Cannot delete '" + address[address.length - 1] + "': No such file or directory.");
                           }
                        } else if (!dir.delete(commands[1])) {
                           bw.write("Cannot delete '" + commands[1] + "': No such file or directory.");
                        }
                     }
                     break;
                  case "rn":        // okay
                     if (commands.length <= 2 || commands.length > 3) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        current = dir.newDirectory(commands[1]);
                        current.editFileName(commands[2]);
                        current = current.getParent();
                     }
                     break;
                  case "mv":        //
                     if (commands.length <= 2 || commands.length > 3) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (!dir.search(commands[1]) || !dir.search(commands[2])) {
                           bw.write("The system cannot find the file specified.");
                        } else {
                           String[] address = commands[2].split("/");
                           MyNode move = dir.newDirectory(commands[1]);
                           MyNode newDir = dir.newDirectory(address[address.length - 1]);
                           dir.delete(commands[1]);
                           dir.insert(newDir, move);
                        }
                     }
                     break;
                  case "cp":        // okay
                     if (commands.length <= 2 || commands.length > 3) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (!dir.search(commands[1])) {
                           bw.write("The system cannot find the file specified.");
                        } else {
                           dir.insert(current, commands[2]);
                           MyNode copy = dir.newDirectory(commands[2]);
                           MyNode orig = dir.newDirectory(commands[1]);
                           copy.editContent(orig.getDescription().getContent());
                        }
                     }
                     break;
                  case "ls":        // okay
                     if (commands.length == 1) {
                        bw.write(current.seeContents());
                     } else {
                        String fileType = commands[1].substring(2) + " File";

                        for (MyNode n : current.getContents()) {
                           if (n.getDescription().getType().getFileType().equals(fileType)) {
                              bw.write(n.getDescription().getFileName());
                           }
                        }
                     }
                     break;
                  case "show":      // okay
                     if (commands.length == 1 || commands.length > 2) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (!dir.search(commands[1])) {
                           bw.write("Cannot show '" + commands[1] + "': No such file or directory.");
                        } else {
                           current = dir.newDirectory(commands[1]);
                           bw.write(current.getDescription().getContent());
                           current = current.getParent();
                        }
                     }
                     break;
                  case "whereis":   // okay
                     if (commands.length == 1 || commands.length > 2) {
                        bw.write("The syntax of the command is incorrect.");
                     } else {
                        if (!dir.search(commands[1])) {
                           bw.write("The system cannot find the file specified.");
                        } else {
                           String lookAddress = "";
                           Stack<String> where = new Stack<>();
                           MyNode look = dir.newDirectory(commands[1]);

                           while (look.getParent() != null) {
                              where.push(look.getDescription().getFileName());
                              look = look.getParent();
                           }

                           while (!where.isEmpty()) {
                              lookAddress += where.pop() + "/";
                           }

                           lookAddress = lookAddress.substring(0, lookAddress.length() - 1);

                           bw.write(mainAddress + lookAddress);
                        }
                     }
                     break;
                  default:
                     bw.write("'" + commands[0] + "' is not recognized as a command.");
               }

               bw.newLine();
            }

            bw.close();
         } catch (IOException e) {
            e.printStackTrace();
         } finally {
            try {
               if (br != null) {
                  br.close();
               }
            } catch (IOException ex) {
               ex.printStackTrace();
            }
         }
      }
   }
}
