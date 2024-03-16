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
  if(firstFile!=null){
   try{
      file = firstFile;
      File myObj = new File(file);
      Scanner myReader = new Scanner(myObj);  
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
            


}