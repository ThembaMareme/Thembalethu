import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class ReadTextFile{

      private AVLTree avl;
      private String file;
      private String secFile;
      private String numStr;
public ReadTextFile(){
      avl = new AVLTree();
      file = null;
      secFile = null;
      numStr="";
}

public String read (String firstFile){
 if(firstFile!=null){
   try{
      file = firstFile;
      File myObj = new File(file);
      Scanner myReader = new Scanner(myObj);  
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        BinaryDataFields obj = new BinaryDataFields(data); 
        avl.insert(obj);        
              }
      myReader.close();
      return "Knowledge base loaded successfully.";
       } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }
     else{System.exit(0);} 
  return "File not found";
}

public String read2(String secFile){
   String str = "";
  if(secFile!=null){
   try{
      this.secFile = secFile;
      File myObj = new File(secFile);
      Scanner myReader = new Scanner(myObj);  
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        BinaryDataFields term = avl.find(data);
        numStr += Integer.toString(avl.getOpCounter())+"\n";
        avl.opCounter=0;
        if(term!=null){
         str += term.getTerm()+"\t"+term.getStatement()+"\t"+term.getConfi()+"\n";
        }
        else{
        str += "Term not found: "+data+"\n";
        }
        }
        myReader.close();
        return str;
      }catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }
    else{System.exit(0);} 
    return "File not found";

}
public String getNumStr(){return numStr;}
}