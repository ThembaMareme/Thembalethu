public class ReadTextFile{

      private AVLTree avl;
      private String firstFile;
      private String secFile;

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
   String str;
  if(firstFile!=null){
   try{
      file = firstFile;
      File myObj = new File(file);
      Scanner myReader = new Scanner(myObj);  
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        BinaryDataFields term = avl.find(data);
        if(term!=null){
         str += term.getTerm()+"\t"+term.getStatement()+"\t"+term.getConfi()+"\n";
        }
        else{
        str += "Term not found: "+data;
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