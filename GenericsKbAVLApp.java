import java.util.Scanner;
import javax.swing.JOptionPane;

public class GenericsKbAVLApp{
public static void main(String [] args){
Scanner obj = new Scanner(System.in);
ReadTextFile fileObj = new ReadTextFile();



String choice;
choice = JOptionPane.showInputDialog("Choose an action from the menu:\n1. Load a knowledge base from a file\n2. Search for an item in the knowledge base by term\n3. Enter the new size of file\n4. Dispaly the result\n5. Exit the program");
if (choice==null ){System.exit(0);}

while(true){
   if(choice==null){System.exit(0);}
  if (choice.trim().equals("1")){//When the user enters 1.
   String filename = JOptionPane.showInputDialog("Enter file name for AVL Tree:");//Display the option for the user to enter the file name.
  if(filename!=null){ JOptionPane.showMessageDialog(null, fileObj.read(filename.trim()));}
  else{System.exit(0);}
   String filename1 = JOptionPane.showInputDialog("Enter file name with queries:");
   if(filename1!=null){
      JOptionPane.showMessageDialog(null, fileObj.read2(filename1.trim()));
      fileObj.initialiseFile(filename,filename1);}
      else{System.exit(0);}
      }
else if (choice.trim().equals("2")){
   JOptionPane.showMessageDialog(null, fileObj.search());}
else if(choice.trim().equals("3")){
   int n =fileObj.getFileSize();
    JOptionPane.showMessageDialog(null, "The file Size Of The Query is: "+Integer.toString(fileObj.getFileSize()));
   String size1;
   fileObj.addToList();
   size1 =  JOptionPane.showInputDialog("Enter the new size (must be a multiple of 10 and not greater than "+Integer.toString(fileObj.getFileSize())+":");
   // if(size1.equals("Q") || size1.equals("Quit") || size1.equals("q") || size1.equals("quit")){System.exit(0);}
    if(size1==null){System.exit(0);}
   while (Integer.parseInt(size1)<n+1){
   fileObj.insertShuffle(size1);
   size1 =  JOptionPane.showInputDialog("Enter the new size (must be a multiple of 10 and not greater than "+Integer.toString(n)+" or click (Q)uit:");
   if(size1.equals("Q") || size1.equals("Quit") || size1.equals("q") || size1.equals("quit")){break;}
   }
   if(size1==null){System.exit(0);}

} 
else if (choice.trim().equals("4")){
     JOptionPane.showMessageDialog(null, fileObj.getResults());
}
else if (choice.trim().equals("5")){System.exit(0);} 
else{JOptionPane.showMessageDialog(null,"Invalid Option");}
choice = JOptionPane.showInputDialog("Choose an action from the menu:\n1. Load a knowledge base from a file\n2. Search for an item in the knowledge base by term\n3. Enter the new size of file\n4. Dispaly the result\n5. Exit the program");
}
}
}