# Discover Java
Here I will document my journey of learning the Java language, one of the most popular languages to date, 
by making a simple language program.

Start date: April 30th, 2022

# Resources 
- Java Design Kit (JDK), which includes all the resources needed, including javac to compile source code to Java byte code and the Virtual Java Machine, or VJM)
- IDE (VS Code)

# Classes to Use
 - Main
    - Responsibilities: Run the program
    - Behaviors: Run through each of the screens until a return statement is reached.
        - main(): contain the while loop that goes through each screen.
    - Status:
        - Instantiated classes of the following listed

 - (Abstract class) Screen
    - Responsibilities: Contain the layout of a screen
    - Behaviors (concrete):
        - setScreen()
        - clearScreen()
    - Behaviors (abstract):
        - 
    - Status:
        - Screen nodes (Array)
        - Screen number: know which screen we are on (int)

 - MenuScreen (screen 1) (inherits Screen): 
    - Responsibilities: Display the 
    - Status:
        - 

 - ChooseSessionScreen (screen 2) (inherits Screen):
    - Responsibilities: Display the 
    - Added Behaviors:
        - Print the options to the screen (print)
        - 
    - Added Status:

 - LearningScreen (Screen 3) (inherits Screen):
    - Responsibilities: List the word
    - Added Behaviors: 
    - Status:
        
 - NewWordScreen (Screen 4) (inherits Screen):
    - Responsibilities: Have a place for the user to input new words added
        - Added Behaviors: 
        - Status:
            - currentFormat (object) 

 - QuizScreen (Screen 3) (inherits Screen): 
    - Responsibilities: Display the questios for the user to select/type the answers
    - Added Behaviors: 
        - configureQuestionBasedOnLang: 
    - Status:
        - The type of question (int: 0 = mc, 1 = fill in blank)

 - GUI: 
    - Responsibilities: Displays the selected, receive the inputs from the user
    - Behaviors:
        - Display nodes: display_nodes()
        - Retrieve inputs: retrieve_inputs()
        - 
    - Status: 
        - current_screen (instantiated class)

- FileService:
    - Responsibilities: Read and write words learned: Column 1: Word in English; Column 2-n: Word in other languages.
    - Behaviors:
        - read_file()
        - write_file()
    - Status: 
        - lang_file (String)

# Interfaces:
 - Question
    - question_number (int)
    - question_type (char)

# Realizations
- April 30th, 2022: A hashmap is essentially a class that allows mapping of key, value pairs. I am more familiar to python's dictionary.

# Overview

Learned:

- How to logically go through each of the screens in the main function.
- Interface vs abstract class
- How to use an array and a 
- Having classes throw exceptions (like if the file is not found)
- What exactly is static?
    Hypothesize: Static means that the class/method is tied to the instance and not the class itself.
    Actually, static means there is only one version of that variable/method that is tied to the class itself.
- An array of type abstract class can include child classes.
- "This field is not visible": The variables must be set to public
- The difference of naming an array and allocated it memory when initializing.
- Arraylists can change in size while arrays cannot.
- Access an index's item in an ArrayList with .get(index)
- Getting the subset of an array Footnote 1
- .equals() is used for comparing strings, not ==

- Problem statement: How do I have the gui listen to an event coming from a button in the screen? 
Do I even need a looping mechanism?

Hypothesis: Have 

{Important!  Do not say in this section that this is college assignment.  Talk about what you are trying to accomplish as a software engineer to further your learning.}

{Provide a description the software that you wrote to demonstrate the Java language.}

{Describe your purpose for writing this software.}

{Provide a link to your YouTube demonstration.  It should be a 4-5 minute demo of the software running and a walkthrough of the code.  Focus should be on sharing what you learned about the language syntax.}

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

{Describe the tools that you used to develop the software}

{Describe the programming language that you used and any libraries.}

# Useful Websites

{Make a list of websites that you found helpful in this project}
* [Web Site Name](https://stackoverflow.com/questions/4822256/java-is-there-an-easy-way-to-select-a-subset-of-an-array)
* [Web Site Name](https://stackoverflow.com/questions/157944/create-arraylist-from-array)

# Future Work

{Make a list of things that you need to fix, improve, and add in the future.}
* Item 1
* Item 2
* Item 3
