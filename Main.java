import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import FileService.FileService;
import TerminalService.TerminalService;

public class Main {
  /* Control the screens for the language program. */
  
  // User inputs
  static int userChoiceInt;
  static String userChoiceString;
  
  static boolean quit = false;
  static HashMap<String, String> learningHashMap = new HashMap<>();
  static int wordsLearned;

  public static void main(String args[]) throws Exception {
    // Create instance of our file service
    // Assuming that a csv has been creating with a single header for English.
    FileService fService = new FileService("CSV/word_list.csv");
    menuScreen(fService);
  } 

  public static void menuScreen(FileService fService) throws IOException, InterruptedException {
    TerminalService.clrScreen();
      
    TerminalService.print("Language Learning\nBy Samuel Casellas\n");
    
    // Dynamic parts: 
    // Get langs already being learned for display.
    ArrayList<String> currentLangs = fService.getLangsInHeader();

    // Not counting English
    if (currentLangs.size() > 1) {
      TerminalService.print("Current Languages:");
      for (int i = 1; i < currentLangs.size(); i++) {
        TerminalService.print(String.format("%s. %s", i, currentLangs.get(i)));
      }

      userChoiceInt = TerminalService.inputInt("\nChoose language\nSelect # (-1 for new language) ");
      if (userChoiceInt == -1) {
        String newLang = TerminalService.inputString("\nWhat language do you want to learn? ");
        fService.newLanguage(newLang);
        chooseSession(fService, newLang);
      }
      else {
        try {
          String langChosen = currentLangs.get(userChoiceInt);
          chooseSession(fService, langChosen);
          return;
        }
        catch (Exception e) {
            TerminalService.print("Invalid choice!");
            TimeUnit.SECONDS.sleep(1);
            menuScreen(fService);
        }
      }
    }
  
    else {
      String newLang = TerminalService.inputString("What language do you want to learn?");
      fService.newLanguage(newLang);
      chooseSession(fService, newLang);
    }
  }

  public static void chooseSession(FileService fService, String langChosen) throws IOException, InterruptedException {

    // lambda function available after version 8 of JDK
    Function<HashMap<String,String>,Integer> countWordsLearned = hMap -> {
      wordsLearned = 0;
      for (Map.Entry<String, String> e : learningHashMap.entrySet()) {
        if (! e.getValue().equals("N/A")) {
          wordsLearned++;
        }
      }
      return wordsLearned;
    };
    
    TerminalService.clrScreen();
    learningHashMap = fService.getLangsHashMap(langChosen);
    
    // Ascertain that the user has learned at least 20 words before allowing to do a quiz.
    wordsLearned = countWordsLearned.apply(learningHashMap);
    if (wordsLearned >= 20) {
      TerminalService.print(String.format("%s Learning", langChosen));
      TerminalService.print("1. Learning");
      TerminalService.print("2. Quiz");
      TerminalService.print("3. Select another language");
      TerminalService.print("4. Quit");
      userChoiceInt = TerminalService.inputInt("");
      switch (userChoiceInt) {
        case 1: {
          learningScreen(fService, langChosen, wordsLearned);
          if (!quit)
            chooseSession(fService, langChosen);
          break;
        }
        case 2: {
          quizScreen(fService, langChosen);
          chooseSession(fService, langChosen);
          break;
        }
        case 3: {
          menuScreen(fService);
          break;
        }
        case 4: {
          TerminalService.clrScreen();
          TerminalService.print("Goodbye! See you next time.\n");
          quit = true;
          break;
        }
        default: {
          TerminalService.print("Invalid!");
          TimeUnit.SECONDS.sleep(1);
          chooseSession(fService, langChosen);
          break;
        }
      }
    }
    // Quiz option not available.
    else {
      learningScreen(fService, langChosen, wordsLearned);
      if (quit)
        return;
    
      // Update hash map based on new words added in learning screen.
      learningHashMap = fService.getLangsHashMap(langChosen);
      wordsLearned = countWordsLearned.apply(learningHashMap);

      // Handle learning screen return: able to do quiz?
      if (wordsLearned >= 20)
        chooseSession(fService, langChosen);
      else
        menuScreen(fService);
    }
  }

  public static void learningScreen(FileService fService, String langChosen, 
                                    int wordsLearned) 
                                    throws IOException, InterruptedException {
    TerminalService.clrScreen();
    String goBackOption = "Change Language";
    if (20 > wordsLearned) 
      TerminalService.print(String.format
                                  ("Need to learn at least 20 words to do quizzes. %s more are needed.\n", 
                                  20 - wordsLearned));
    else {
      TerminalService.print(String.format
                                  ("%s total words learned.\n(The option to do quizzes is available.)", 
                                  wordsLearned));
      goBackOption = "Go back";
    }
    if (wordsLearned > 0) {
      // Display currrent words learned for further learning.
      TerminalService.print(String.format("%-12s\t\t%s", "English", langChosen));
      TerminalService.print("------------\t\t------------");
      learningHashMap = fService.getLangsHashMap(langChosen);
      for (Map.Entry<String, String> e : learningHashMap.entrySet()) 
        if (! e.getValue().equals("N/A")) {
          TerminalService.print(String.format("%-12s\t\t%s\n", e.getKey(), e.getValue()));
        }
      TerminalService.print(String.format("\n1. %s\n2. New word\n3. Quit", goBackOption));
      userChoiceInt = TerminalService.inputInt("");
      switch (userChoiceInt) {
        case 1: {
          if (20 > wordsLearned)
            menuScreen(fService);
          else
            chooseSession(fService, langChosen);
          break;
        }
        case 2: {
          wordsLearned += newWordScreen(fService, langChosen);
          learningScreen(fService, langChosen, wordsLearned);
          break;
        }
        case 3: {
          TerminalService.clrScreen();
          TerminalService.print("Goodbye! See you next time.\n");
          quit = true;
          break;
        }
        default: {
          TerminalService.print("Invalid!");
          TimeUnit.SECONDS.sleep(1);
          learningScreen(fService, langChosen, wordsLearned);
        }
      }
    }
    else {
      TimeUnit.SECONDS.sleep(3);
      wordsLearned += newWordScreen(fService, langChosen);
      learningScreen(fService, langChosen, wordsLearned);
    }
  }

  public static int newWordScreen(FileService fService, String langChosen) throws IOException {
    /* Return the number of new words added which will affect quiz availability */
    TerminalService.clrScreen();
    TerminalService.print("Type new words with the following format:");
    learningHashMap.clear();
    learningHashMap = fService.getLangsHashMap(langChosen);
    int newWords = 0;
    // For ensuring no double definitions in a single input session.
    ArrayList<String> proposedEngWords = new ArrayList<>();
    do {
      userChoiceString = TerminalService.inputString(String.format
                                                    ("[%s word]: [word in %s] (Enter 0 to exit)", 
                                                    "English", 
                                                    langChosen));
      userChoiceString = userChoiceString.replace(" ", "");
      String wordSplit[] = userChoiceString.split(":"); 
      try {
        String engWord = wordSplit[0].strip().toLowerCase();
        String fWord = wordSplit[1].strip().toLowerCase();
        
        // Notify the user if there already is a translation for the English word
        if ((learningHashMap.containsKey(engWord) && !learningHashMap.get(engWord).equals("N/A")) || 
        proposedEngWords.contains(engWord)) {
          if (TerminalService.inputString(
              String.format(
              "\nThe translation for \"%s\" already exists: \"%s\".\nReplace for \"%s\"? (y/n)", 
              engWord, 
              learningHashMap.get(engWord),
              fWord))
              .toLowerCase().equals("y")) 
                                        {
              learningHashMap.put(engWord, fWord); 
              TerminalService.print(String.format("\nInputed \"%s\" as \"%s\"\n", engWord, fWord));
              proposedEngWords.add(engWord);
          }
        }
        // No word collision
        else {
          learningHashMap.put(engWord, fWord);
          TerminalService.print(String.format("\nInputed \"%s\" as \"%s\"\n", engWord, fWord));
          proposedEngWords.add(engWord);
          newWords++;
        }
      }
      // If invalid # of delimiters
      catch (Exception e) {
        if (!userChoiceString.equals("0"))
          TerminalService.print("\nInput is invalid. Please try again.\n");
      }
    } while (!userChoiceString.equals("0"));

    // Record all of the new words.
    fService.writeFile(learningHashMap, langChosen);
    return newWords;
  }

  public static void quizScreen(FileService fService, String langChosen) throws IOException, InterruptedException {
    /*
    Take numQuestions from the user. Randomly decide between 
    these two types of questions (3:1 ratio):

    Type 1: multiple choice (a-d)
    Type 2: word response

    Get hash map of all the english and foreign words for this language. 
    Display right or wrong and keep track of percent right. 
    Go back to choose session screen when completed.
    */
    TerminalService.clrScreen();

    // For a nice decoration effect.
    String lineChars[] = {"-", "*", "=", "+", "#", "~"};
    int charStepper = 0;

    long baseChance;
    String usersAnswer;
    String questionWord = "";
    String correctAnswer = "";
    String answerCase; // Either "English" or "For."
    boolean answeredCorrectly;
    String[] printOptions = {"a", "b", "c", "d"};
    
    learningHashMap = fService.getLangsHashMap(langChosen);

    // Only words that have been introduced remain.
    for (Map.Entry<String, String> e : learningHashMap.entrySet()) {
      if (e.getValue().equals("N/A"))
        learningHashMap.remove(e.getKey());
    }

    int numCorrect = 0;
    int numQuestions = TerminalService.inputInt("How many questions for this quiz?");

    TerminalService.print("\n(Remember to only enter the letter for the multiple choice.)");
    try {
    // Begin quiz
    for (int i = 0; i < numQuestions; i++) {
      String[] options = new String[4];
      TerminalService.print(lineChars[charStepper % lineChars.length].repeat(20));
      charStepper++;

      answeredCorrectly = false;
      baseChance = Math.round(Math.random()*100);
      int questionPos = (int) baseChance % learningHashMap.size();
      
    // Determine if the answer is to be an english or a foreign language word.
      // Foreign Answer
      if (Math.random() > 0.5) {
        answerCase = langChosen;
        // Find question word and associated answer
        int j = 0;
        for (Map.Entry<String, String> e : learningHashMap.entrySet()) {
          if (j == questionPos) {
            questionWord = e.getKey();
            correctAnswer = e.getValue();
            break;
          }
          j++;
        }
      }
      // English Answer
      else {
        answerCase = "English";
        // Find question word and associated answer
        int j = 0;
        for (Map.Entry<String, String> e : learningHashMap.entrySet()) {
          if (j == questionPos) {
            questionWord = e.getValue();
            correctAnswer = e.getKey();
            break;
          }
          j++;
        }
      }
      
      // Multiple choice
      if (Math.random() > 0.25) {
        // The location of the correct answer in list of options.
        int answerPos = (int) baseChance % 4;
        String uniqueIncorrect;
        Object[] englishPossibilities = learningHashMap.keySet().toArray();
        Object[] foreignPossibilities= learningHashMap.values().toArray();
        // Deal out options
        for (int j = 0; j < options.length; j++) {
          if (j == answerPos)
            options[j] = correctAnswer;
          else
            {
              if (answerCase.equals("English"))
                // while loops ensure that an answer is not repeated.
                do {
                long chance1 = Math.round(Math.random()*100) % learningHashMap.size();
                int chance1Int = (int) chance1;
                uniqueIncorrect = englishPossibilities[chance1Int].toString();
                } while (Arrays.toString(options).contains(uniqueIncorrect) || (uniqueIncorrect.equals(correctAnswer)));
              
              // Foreign answer required
              else
                do {
                  long chance1 = Math.round(Math.random()*100) % learningHashMap.size();
                  int chance1Int = (int) chance1;
                  uniqueIncorrect = foreignPossibilities[chance1Int].toString();
                } while (Arrays.toString(options).contains(uniqueIncorrect) || (uniqueIncorrect.equals(correctAnswer)));
                
            options[j] = uniqueIncorrect;
          }
        }

        for (int j = 0; j < options.length; j++) {
          TerminalService.print(String.format("%s. %s", printOptions[j], options[j]));
        }

        usersAnswer = TerminalService.
                      inputString(String.format("\nQ. %s/%s: What is the %s translation for \"%s\"?", 
                                                i + 1,
                                                numQuestions,
                                                answerCase, 
                                                questionWord))
                                                .toLowerCase();
        
        if (usersAnswer.equals(printOptions[answerPos]))
          answeredCorrectly = true;
      }
      // Word answer
      else {
        usersAnswer = TerminalService.
                      inputString(String.format("\nQ. %s/%s: Type the %s word for \"%s\"", 
                                                i + 1,
                                                numQuestions,
                                                answerCase,
                                                questionWord))
                                                .toLowerCase()
                                                .replace(" ", "");
        if (usersAnswer.equals(correctAnswer))
          answeredCorrectly = true;
      }

      if (answeredCorrectly) {
        numCorrect++;
          TerminalService.print(String.format("\nCorrect!\nScore: %s/%s\n", 
                                              numCorrect,
                                              i + 1));
      }

      else
        TerminalService
        .print(String.format("\nIncorrect! The correct answer was \"%s.\"\n", 
                              correctAnswer));
    }
    }
    catch (Exception e) {
      TerminalService.print(e.getLocalizedMessage());
    }
    TerminalService.print(String.format("\n\nQuiz completed\nFinal Score: %s/%s\n", 
                                          numCorrect,
                                          numQuestions));
    TimeUnit.SECONDS.sleep(4);
    return;
  }

}