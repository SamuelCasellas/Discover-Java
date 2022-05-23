// May 7th, 2022

// Hypothesizing static and non-static methods.
// See printed lines for explanations on the difference between them.


public class StaticTestingMain {

    public static void main(String args[]) {
        
        StaticTesting.staticMethod();
        // StaticTesting.nonStaticMethod(); // This is illegal!
        StaticTesting anObject = new StaticTesting(); 
        anObject.nonStaticMethod();
    }
}

// For convenience, I put the two classes on the same file. Usually we would make these two seperate files.

class StaticTesting {

    public void nonStaticMethod() {
        System.out.println("This is a method that is non-static. Non-static methods cannot be called from directly calling a class. You would need to create an object first and then use that.");
    }
    
    public static void staticMethod() {
        System.out.println("This is a static method! You can access me through the class itself or through an object instantiated from it.");
    }

}