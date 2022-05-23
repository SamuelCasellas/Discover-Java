import Gui.*;
import FileService.*;

public class Main {
    
    public static void main(String args[]) throws Exception {

        // Create instances of all classes
      
      FileService fileService = new FileService("CSV/word_list.csv");
      Gui gui = new Gui(fileService);


    } 
}