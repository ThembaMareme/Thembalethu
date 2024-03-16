import java.util.Scanner;
/**
*This class Accepts a single String and useDelimeter to separate the words when a tap is noticed then put them in the respected varibles.
*@auter MRMTHE008.
*/
public class BinaryDataFields{

   private String term;
   private String statement;
   private String confi;
/**
*This method take the String Check the String if the is a tap then put those words in the respected variables.
*@param data Accepts the String that is a single line.
*@return the object of the class that contains the three variables that can only be accesed through the methods of the class. 
*/
   public BinaryDataFields (String data){
   Scanner keyboard = new Scanner(data);
   keyboard.useDelimiter("\t");
   this.term= keyboard.next();
   this.statement = keyboard.next();
   this.confi = keyboard.next();
   }
/**
*Allow access to the private instance variables.
*@return a String of the instance variable.
*/
   public String getTerm(){return term;}
   public String getStatement(){return statement;}
   public String getConfi(){return confi;}
/**
*This method compares the two string the return an integer a positive,negative and zero if they are equal.
*@return an integer a positive , negative and zero.
*/
   public int compareTo(String word){
   return term.compareTo(word);
   }
/**
*Represents the object of the class.
*@return a String that represents the object of the class
*/  
   public String toString(){
   return term+"\t"+statement+"\t"+confi;
   }

}