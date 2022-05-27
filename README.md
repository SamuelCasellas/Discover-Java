# Discover Java
Author: Samuel Casellas
Start date: April 30th, 2022

Data types used in Java:
- HashMaps
- Sets
- ArrayLists

### MODIFICATIONS ###

Due to high complexity issues, I downgraded from doing a GUI to a terminal for my language program.

# Classes to Use
- Main: Run the program by navigating through the screens
- Term

# Overview

Here I will document my journey of learning the Java language, one of the most popular languages to date, 
by making a simple language program. In this program, we will allow the user to set up word pairs and do quizzes.

My main focus here was to learn the different syntax for Java. I discovered how necessary it is too only use instantiated classes
in the main so that I don't end up needed to make all of the methods static. Having instances of classes makes coding more scalable.

Other things I learned:

- Interface vs abstract class
- How to iterate through an array.
- Having classes throw exceptions (like if the file is not found)
- What exactly is static?
    Hypothesize: Static means that the class/method is tied to the instance and not the class itself.
    Conclusion after further research: Static means there is only one version of that variable/method that is tied to the class itself.
- An array of type abstract class can include child classes.
- "This field is not visible": The variables must be set to public
- The difference of naming an array and allocated it memory when initializing.
- Arraylists can change in size while arrays cannot.
- Access an index's item in an ArrayList with .get(index)
- Getting the subset of an array Footnote 1
- .equals() is used for comparing strings, not ==
- Iterating through a HashMap like so:
`Map.Entry<String, String> e : engLangHash.entrySet()`


[Software Demo Video](https://youtu.be/o9oCjFDiA14)

# Development Environment

- Java Design Kit (JDK), which includes all the resources needed, including javac to compile source code to Java byte code and the Virtual Java Machine, or VJM)
- IDE (VS Code)
- Many libraries were used, such as different data types like hashmaps and implementing a wait timer with concurrent.TimeUnit.

# Useful Websites

* [Stack Overflow: Anonymous Lambda Functions in Java](https://stackoverflow.com/questions/5388584/does-java-support-inner-local-sub-methods)
* [Geeks for Geeks: How to use Hashmaps](https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/)
* [Youtube: How to format strings](https://www.youtube.com/watch?v=LUv9OBVlFc4)


# Future Work

* Make each screen its own class.
* After the adjustments above, implement this to a data base like Fire Base.
* Find futher types of language activities.
