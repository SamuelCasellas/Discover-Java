package FileService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileService {
    
    private String path = "";
    private String line;
    private boolean firstLine;

    // Components of words file
    // Must be initialized, else null pointer exception when used.
    private ArrayList<String> headersLangsArrayList = new ArrayList<>();
    private ArrayList<String> englishWordsArrayList = new ArrayList<>();
    private ArrayList<ArrayList<String>> foreignWordsArrayListOfArrayList = new ArrayList<>();
    
    private ArrayList<String> oneLineArrayList = new ArrayList<>();

    
    public FileService(String selectedPath) { 
        this.path = selectedPath;
    } 

    private int numColumns() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        String line = reader.readLine();
        String columns[] = line.split(",");
        reader.close();
        return columns.length;
    }

    private int getLangsIndex(String lang) throws IOException {
        ArrayList<String> headerLangs = this.getLangsInHeader();
        return headerLangs.indexOf(lang) - 1;
    }

    // public int alreadyAddedIndex(String criteria) throws IOException { // Good
    //     /*
    //     Returns -1 if english word has not been added yet.
    //     */
    //     BufferedReader reader = new BufferedReader(new FileReader(path));
    //     int index = 0;
    //     firstLine = true;
    //     while ((line = reader.readLine()) != null) {
    //         if (!firstLine) {
    //         String engWord = line.split(",")[0];
    //         if (engWord.equals(criteria)) {
    //             reader.close();
    //             return index;
    //         }
    //         index++;
    //       }
    //       firstLine = false;
    //     }
    //     reader.close();
    //     return -1;
    // }

    public ArrayList<String> getLangsInHeader() throws IOException {
        /*Get an arraylist of all the foreign languages
        */ 
        
        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        String rawHeader = reader.readLine();
        String headerParts[] = rawHeader.split(",");
        ArrayList<String> foreignLangs = new ArrayList<>(Arrays.asList(headerParts));
        reader.close();
        return foreignLangs;
    }

    public HashMap<String, String> getLangsHashMap(String lang) throws IOException { // Done
        HashMap<String, String> learningHashMap = new HashMap<>();
        englishWordsArrayList = this.englishWords();
        foreignWordsArrayListOfArrayList = this.foreignWords();

        int langIndex = this.getLangsIndex(lang);
        String engWord;
        String foreignWord;

        for (int i = 0; i < englishWordsArrayList.size(); i++) {
            engWord = englishWordsArrayList.get(i);

            oneLineArrayList = foreignWordsArrayListOfArrayList.get(i);
            foreignWord = oneLineArrayList.get(langIndex);

            learningHashMap.put(engWord, foreignWord);
        }

        return learningHashMap;
    }

    public ArrayList<String> englishWords() throws IOException { // Good
        /* Get all of the English words in an Arraylist of String.
        */
        BufferedReader reader = new BufferedReader(new FileReader(path));
        englishWordsArrayList.clear();
        
        firstLine = true;  
        while ((line = reader.readLine()) != null) {
            if (!firstLine) { 
            String parts[] = line.split(",");
            englishWordsArrayList.add(parts[0]);
            }
            firstLine = false;
        }
        reader.close();
        return englishWordsArrayList;
    }

    public ArrayList<ArrayList<String>> foreignWords() throws IOException { // Good
        /* Get the list of all the foreign words in an arraylist of arraylist string.
        */

        BufferedReader reader = new BufferedReader(new FileReader(path));
        foreignWordsArrayListOfArrayList.clear();
        
        firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (!firstLine) { 
            String parts[] = line.split(",");
            String chosenParts[] = Arrays.copyOfRange(parts, 1, parts.length);
            
            foreignWordsArrayListOfArrayList.add(new ArrayList<>(Arrays.asList(chosenParts))); // convert our array to an ArrayList.
            }
            firstLine = false;
    }
        reader.close();
        return foreignWordsArrayListOfArrayList;
}

    public static void main(String[] args) throws IOException { // Good
        FileService fService = new FileService("CSV/word_list.csv");
        System.out.println(fService.englishWords());
        System.out.println(fService.foreignWords());
      //  System.out.println(fService.alreadyAddedIndex("Great"));
        System.out.println(fService.numColumns());
        System.out.println(fService.getLangsInHeader());
        fService.newLanguage("BANISH");
        HashMap<String, String> testHash = new HashMap<>();
        testHash.put("fan", "Banish's fan");
    //    fService.writeFile(testHash, fService.numColumns()-2);


            
        };
    

    public void newLanguage(String newLangInsertion) throws IOException {
        headersLangsArrayList = this.getLangsInHeader();
        englishWordsArrayList = this.englishWords();
        foreignWordsArrayListOfArrayList = this.foreignWords();

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));
        // -1 since we don't account for the header.
        for (int i = -1; i < englishWordsArrayList.size(); i++) {
        
            if (i == -1) {
                // Append new language
                headersLangsArrayList.add(newLangInsertion);
                line = formatArrayListToCsvString(headersLangsArrayList);
                writer.write(line + "\n");
            }
            else {
            oneLineArrayList = foreignWordsArrayListOfArrayList.get(i);
            oneLineArrayList.add("N/A"); // Yet to be determined.
            oneLineArrayList.add(0, englishWordsArrayList.get(i));
            line = formatArrayListToCsvString(oneLineArrayList);
            writer.write(line + "\n");
            }
        }

        writer.close();
    }
    
    public void writeFile(HashMap<String, String> engLangHash, String langChosen) throws IOException {
        /* Either create a new column for a new language or insert new words learned 
        

        String newLangInsertion - The new language being requested, else null.
        
        */

        // Save elements of current file
        headersLangsArrayList = this.getLangsInHeader();
        englishWordsArrayList = this.englishWords();
        foreignWordsArrayListOfArrayList = this.foreignWords();

        int langIndex = this.getLangsIndex(langChosen);
        int numberForeignColumns = this.numColumns() - 1;
        String rowsEngWord;


        // For confirming later that this is not a new word pair.
        Set<String> newEngsSet = engLangHash.keySet();
  
        // Initiate writer object for writing
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));

        for (int i = -1; i < englishWordsArrayList.size(); i++) {
            // Write header as unchanged
            if (i == -1) {
                line = formatArrayListToCsvString(headersLangsArrayList);
                writer.write(line + "\n");
            }
            else {
                rowsEngWord = englishWordsArrayList.get(i);
                oneLineArrayList = foreignWordsArrayListOfArrayList.get(i);
                if (newEngsSet.contains(rowsEngWord)) {
                    // Replace N/A with the new foreign word
                    oneLineArrayList.set(langIndex, engLangHash.get(rowsEngWord)); 
                    engLangHash.remove(rowsEngWord);
                }
                oneLineArrayList.add(0, rowsEngWord);
                line = formatArrayListToCsvString(oneLineArrayList);
                writer.write(line + "\n");
            }
        }

        // New English words
        for (Map.Entry<String, String> e : engLangHash.entrySet()) {
            rowsEngWord = e.getKey();
            oneLineArrayList.clear();
            for (int j = 0; j < numberForeignColumns; j++) {
                if (j == langIndex) 
                    oneLineArrayList.add(e.getValue());
                else {
                    oneLineArrayList.add("N/A");
                }
            }
            oneLineArrayList.add(0, rowsEngWord);
            line = formatArrayListToCsvString(oneLineArrayList);
            writer.write(line + "\n");
            
        }

        writer.close();

    }
    
    public void eraseLang(String langChosen) throws IOException {
      headersLangsArrayList = this.getLangsInHeader();
      int removeLangIndex = this.getLangsIndex(langChosen);

      headersLangsArrayList.remove(removeLangIndex+1);

      englishWordsArrayList = this.englishWords();
      foreignWordsArrayListOfArrayList = this.foreignWords();

      // Initiate writer object for writing
      BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));

      for (int i = -1; i < englishWordsArrayList.size(); i++) {
          // Write header as unchanged
          if (i == -1) {
              line = formatArrayListToCsvString(headersLangsArrayList);
              writer.write(line + "\n");
          }
          else {
              String rowsEngWord = englishWordsArrayList.get(i);
              oneLineArrayList = foreignWordsArrayListOfArrayList.get(i);
              oneLineArrayList.remove(removeLangIndex);

              oneLineArrayList.add(0, rowsEngWord);
              line = formatArrayListToCsvString(oneLineArrayList);
              writer.write(line + "\n");
          }
      }
      writer.close();
  }
  

    public static String formatArrayListToCsvString(ArrayList<String> arrList) {
        return Arrays.toString(arrList.toArray()).replace("[", "").replace("]", "").replace(" ", "");
    }


}



