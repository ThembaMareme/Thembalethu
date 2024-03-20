import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ReadTextFile{

      private AVLTree avl;
      private String file;
      private String secFile;
      private int numStr;
      int fileSize;
      String strDisplay;
      List<String> words;
      int numStr1;
      String insertStr;
      String searchStr;
public ReadTextFile(){
      avl = new AVLTree();
      file = null;
      secFile = null;
      numStr = 0;
      fileSize = 0;
      strDisplay="";
      words = new ArrayList<String>();
      numStr1=0;
      insertStr="The insertion results\n";
      searchStr="The search results\n";
}
public void initialiseFile(String file1, String file2){
      file=file1;
      secFile = file2;
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
       avl.opCounter=0;
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
      File myObj = new File(file);
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
        if(term!=null){
         str += term.getTerm()+"\t"+term.getStatement()+"\t"+term.getConfi()+"\n";
        }
        else{
        str += "Term not found: "+data+"\n";
        }
        }
        avl.opCounter=0;
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

// public void display(String num){
//     try{
//       count=0;
//       File myObj = new File(secFile);
//       Scanner myReader = new Scanner(myObj); 
//       //FileWriter writer1 = new FileWriter("10.txt"); 
//       while (myReader.hasNextLine()) {
//       if (count!=Integer.parseInt(num)){
//         String data = myReader.nextLine();
//         BinaryDataFields term = avl.find(data);
//         numStr += avl.getOpCounter();
//         avl.opCounter=0;
// 
//         count++;}
//         else{break;}       
//               }
//       //writer1.close();
//       myReader.close();
//       
//      }catch (FileNotFoundException e) {
//       System.out.println("An error occurred.");
//       e.printStackTrace();
//     }
//     float average = numStr/count;
//     
//     
// display1(num+" The Average Number Of Comparisons: "+String.valueOf(average));
// 
//  }
public void display1(){
      strDisplay+=insertStr+"\n"+searchStr;
}
public String getResults(){
display1();
return strDisplay;}
public void addToList(){
         try{
      this.file = file;
      File myObj = new File(file);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()){
       String data = myReader.nextLine();
       words.add(data);
       } myReader.close();
       } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}
public void insertShuffle(String size){
      avl.clear();
      int num = Integer.parseInt(size);
      if(num==fileSize){num=num-1;}
      for(int j=0;j<5;j++){
      Collections.shuffle(words);
      for(int i=0; i<num;i++){
      BinaryDataFields data = new BinaryDataFields(words.get(i));
      avl.insert(data);
      numStr += avl.getOpCounter();
      avl.opCounter=0;
      }
     try{ 
      File myObj = new File(secFile);
      Scanner myReader = new Scanner(myObj);
       while (myReader.hasNextLine()) {
       String data = myReader.nextLine();
        avl.find(data);
        numStr1 += avl.getOpCounter();
        avl.opCounter=0;}
        myReader.close();}
        catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
      }
      searchShuffle(size);
  float average = numStr/5;
  float average2 = average/num;
    
    
insertStr+=size+" The Average Number Of Comparisons: "+String.valueOf(average2)+"\n";

}
public void searchShuffle(String size){

 float average = numStr1/5;
 float average2 = average/5000;
searchStr+=size+" The Average Number Of Comparisons: "+String.valueOf(average2)+"\n";
  
}
}