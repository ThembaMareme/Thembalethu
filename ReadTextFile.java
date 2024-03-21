import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/**
*This class Read the text file that contains knowledge base statement.
*It contains the methods that helps to tranverse the array that contains the objects of Arrays.
*@auther MRMTHE008.
*
*/
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
/**
*This method initialise the file & secFile instance variable
*@param file1 accepts a String file name of the Knowlege dataset
*@param file2 accepts a string file name of the queries 
*/
public void initialiseFile(String file1, String file2){
      file=file1;
      secFile = file2;
}
/**
*This method reads through the file line by line then take those line that have been read to the class BinaryDataFields.
*Creating objects of the BinaryDataFields class then put the object in the array of Arrays.
*@param firstFile Accept a String file name of the Knowlege dataset.
*@return a String if the file is finally found and it has read the whole file, return a String if the file is not found. 
*/

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
/**
*This method read line by line of the accepted file then increment the fileSize variable to calculate the size of the secFile. 
*@param filename Accept a String file name of the Knowlege dataset.
*@return a String if the file is finally found and it has read the whole file, return a String if the file is not found. 
*/

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
/**
*This method reads the second file the query file, read it line by line the, then use find to search for the item in the avl tree.
*@return this returns the string which contains or show the results that where found in the AVLTree & that are not found.
*/
public String search(){
   String str = "";
  if(secFile!=null){
   try{
      
      File myObj = new File(secFile);
      Scanner myReader = new Scanner(myObj); 
     while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        BinaryDataFields term = avl.find(data);//Search for the term in the AVLTree  
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
public void display1(){
      strDisplay+=insertStr+"\n"+searchStr;
}
/**
*This method get the results made for the comparisons
*@return a string that shows the results
*/
public String getResults(){
display1();
return strDisplay;}
/**
*This method reads the first file, it reads it line by line then add those read lines to the ArrayList 
*/
public void addToList(){
         try{
      this.file = file;
      File myObj = new File(file);
      Scanner myReader = new Scanner(myObj);//reading the file
      while (myReader.hasNextLine()){
       String data = myReader.nextLine();
       words.add(data);//Adding the single lines read in the ArrayList 
       } myReader.close();
       } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}
/**
*This method take the elemets that where added to the ArrayList then Shuffle the whole ArrayList
*It take the first specified elements, like the size variable specify how many elements should be taken, elements that where Shuffled in the ArrayList
*Then insert the those number of specified element in the AVLTree
*This reads the secFile the query file, then search those elemet in the AVLTree, while doing that keeps track number of operations that are being made.
*Then it will repeart the process 5 times
*Then initialize the String variable by increment the size and the average found for the operations made 
*@param size accepts the size need for the method to run on 
*/
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
/**
*This method calculate the average of the comparisons
*Increment the string by the size and the average comparisons made
*/
public void searchShuffle(String size){

 float average = numStr1/5;
 float average2 = average/5000;
searchStr+=size+" The Average Number Of Comparisons: "+String.valueOf(average2)+"\n";
  
}
}