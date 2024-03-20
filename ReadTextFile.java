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
      private int numStr;
      int count;
      int fileSize;
      String strDisplay;
public ReadTextFile(){
      avl = new AVLTree();
      file = null;
      secFile = null;
      numStr = 0;
      fileSize = 0;
      strDisplay="";
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
 if(secFile!=null){
 
 try{ 
      this.secFile = secFile;
      
      File myObj = new File(secFile);
      Scanner myReader = new Scanner(myObj); 
      //FileWriter writer1 = new FileWriter("10.txt"); 
      while (myReader.hasNextLine()) {
      myReader.nextLine(); 
      fileSize++;}
      return "Query file is loaded successfully.";
  }catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }}
       
  return "File not found";

}
public String search(){
   String str = "";
  if(secFile!=null){
   try{
      
      File myObj = new File(secFile);
      Scanner myReader = new Scanner(myObj); 
     while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        BinaryDataFields term = avl.find(data);
        //numStr += avl.getOpCounter();
        //avl.opCounter=0;
        
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


public int getNumStr(){return numStr;}
public int getFileSize(){return fileSize;}

public void display(String num){
    try{
      count=0;
      File myObj = new File(secFile);
      Scanner myReader = new Scanner(myObj); 
      //FileWriter writer1 = new FileWriter("10.txt"); 
      while (myReader.hasNextLine()) {
      if (count!=Integer.parseInt(num)){
        String data = myReader.nextLine();
        BinaryDataFields term = avl.find(data);
        numStr += avl.getOpCounter();
        avl.opCounter=0;

        count++;}
        else{break;}       
              }
      //writer1.close();
      myReader.close();
      
     }catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    float average = numStr/count;
    
    
display1(num+" The Average Number Of Comparisons: "+String.valueOf(average));

}
public void display1(String e){
      strDisplay+=e+"\n";
}
public String getResults(){return strDisplay;}
}