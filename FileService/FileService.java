package FileService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutionException;

import javax.script.*;
import javax.swing.SortOrder;


public class FileService {
    
    private String path = "";

    private boolean firstLine;
    private String line = "";
    private ArrayList<String> englishWordsArrayList;
    private ArrayList<ArrayList<String>> foreignWordsArrayList;

    
    public FileService(String selectedPath) { 
        this.path = selectedPath;
    } 

    public int numColumns() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        String columns[] = line.split(",");
        reader.close();
        return columns.length;
    }

    public int alreadyAddedIndex(String criteria) throws Exception { // Good
        /*
        Returns -1 if english word has not been added yet.
        */
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int index = 0;
        this.firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (!this.firstLine) {
            String engWord = line.split(",")[0];
            if (engWord.equals(criteria)) {
                reader.close();
                return index;
            }
            index++;
          }
          this.firstLine = false;
        }
        reader.close();
        return -1;
    }

    public ArrayList<String> getLangsInHeader() throws Exception { // Seems good...
        /*Get an arraylist of all the foreign languages
        */ 
        
        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        String rawHeader = reader.readLine();
        String headerParts[] = rawHeader.split(",");
        ArrayList<String> foreignLangs = new ArrayList<>(Arrays.asList(headerParts));
        reader.close();
        return foreignLangs;
    }

    // public HashMap<String, ArrayList<String>> 
    // fileToHashMap( engWords <String, ArrayList<String>> foreignWords, int englishWords) throws Exception { // Done NOT
    //     engWords = null;




    //     BufferedReader reader = new BufferedReader(new FileReader(path));
        
    //     HashMap<String, ArrayList<String>> fileAsHashMap = new HashMap<>();
    //     ArrayList<String> oneLine = new ArrayList<>();

    //     boolean this.firstLine = true;
    //     String headers[] = null; // May be an issue
    //     String engWord = "";
        
    //     while ((line = reader.readLine()) != null) {
    //         String parts[] = line.split(",");
    //         if (this.firstLine) {
    //             this.firstLine = false;
    //             headers = parts;
    //             for (String part : parts) { fileAsHashMap.add(part); }
    //             fileAsHashMap.put("English", Arrays.copyOfRange(headers, 1, headers.length));
    //         }

    //         else {
    //             String foreignWords[] = new String[headers.length-1];
    //             for (int i = 0; i < parts.length; i++) {
    //                 if (i == 0) { engWord = parts[i]; }
    //                 else {
    //                     foreignWords[i-1] = parts[i];
    //                 }
    //             } 
    //             fileAsHashMap.put(engWord, foreignWords);
    //         }
    //     }
    //     reader.close(); 

    //     return fileAsHashMap;

    // }

    public ArrayList<String> englishWords() throws Exception { // Good
        /* Get all of the English words in an Arraylist of String.
        Parameters: Hashmap of String
    
        */

        ArrayList<String> engWords = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        
        this.firstLine = true;  
        while ((line = reader.readLine()) != null) {
            if (!this.firstLine) { 
            String parts[] = line.split(",");
            engWords.add(parts[0]);
            }
            this.firstLine = false;
        }
        reader.close();
        return engWords;
    }

    public ArrayList<ArrayList<String>> foreignWords() throws Exception { // Good
        /* Get the list of all the foreign words in an arraylist of arraylist string.
        */
        ArrayList<ArrayList<String>> foreignWords = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(path));
        
        this.firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (!this.firstLine) { 
            String parts[] = line.split(",");
            String chosenParts[] = Arrays.copyOfRange(parts, 1, parts.length);
            
            foreignWords.add(new ArrayList<>(Arrays.asList(chosenParts))); // convert our array to an ArrayList.
            }
            this.firstLine = false;
    }

        reader.close();
        return foreignWords;

}

    public static void main(String[] args) throws Exception { // Good
        FileService fService = new FileService("CSV/word_list.csv");
        System.out.println(fService.englishWords());
        System.out.println(fService.foreignWords());
        System.out.println(fService.alreadyAddedIndex("Great"));
        System.out.println(fService.numColumns());
        System.out.println(fService.getLangsInHeader());

    }


    public static void add_language() {
        
    }

    
    // public void writeFile(HashMap<String, ArrayList<String>> engLangNew, int langIndex) throws Exception {

    //     // Save elements of current file
        
    //     ArrayList<String> langs = this.getLangsInHeader();
    //     ArrayList<String> engWords = this.englishWords();
    //     ArrayList<ArrayList<String>> foreignWords = this.foreignWords();

    //     int[] overwriteLocations = new int[engLangNew.size()];

    //     // See if there are any locations to overwrite (i.e. words already added)
    //     for (int i = 0; i < engLangNew.size(); i++) {
    //         overwriteLocations[i] = this.alreadyAddedIndex(engWords.get(i));
    //     }

    //     // Catch all the rewrites in one sweep if sorted from smallest to largest.
    //     Arrays.sort(overwriteLocations); 

    //     // Initiate writer object for writing
    //     BufferedWriter writer = new BufferedWriter(new FileWriter(path));

    //     // ArrayList<ArrayList<String>> insertions = new ArrayList<>();
    //     // ArrayList<String> oneLine = new ArrayList<>();

    //     // for (Map.Entry<String, ArrayList<String>> e : engLangNew.entrySet()) {
    //     //     oneLine.clear();
    //     //     oneLine.add(e.getKey()); 
    //     //     for (int i = 0; i < foreignWords.size(); i++) {
    //     //         if (i == langIndex) {
    //     //             oneLine.add(e.getValue());
    //     //         }
    //     //         else {
    //     //             oneLine.add("N/A"); // These words have yet to be assessed in other Langs.
    //     //         }
    //     //     }
            
    //     //     insertions.add(oneLine);
    //     // } 
        
        
    //     int overwriteStepper = 0;
    //     int overwriteIndex = overwriteLocations[overwriteStepper];
    //     this.firstLine = true;
    //     for (int i = 0; i < foreignWords.size(); i++) {
    //         if (this.firstLine) {
    //             String langsString = langs.toString();
    //             langsString.replace;
    //             this.firstLine = false;
    //         }

    //         if (i == overwriteIndex) {
    //             this.foreignWordsArrayList = foreignWords.get(i);
    //             this.foreignWordsArrayList.remove(langIndex);
    //             this.foreignWordsArrayList.add(langIndex, engWords.get(i));

    //             writer.write(str);
    //             overwriteStepper++;
    //             overwriteIndex = overwriteLocations[overwriteStepper];
    //         }
    //         else {

    //         }
            

    //     }

        
        


    //     reader.close();

     }



