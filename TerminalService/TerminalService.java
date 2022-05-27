package TerminalService;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalService {
    /*A completely static class that handles inputs and outputs at the terminal level.
    */

    static Scanner sc = new Scanner(System.in);

    public static void print(String line) {
        System.out.println(line);
    }

    public static void clrScreen() {
        for (int i = 0; i < 25; i++)
            System.out.println("\n");
    }

    public static int inputInt(String inputMessage) {
        TerminalService.print(inputMessage);
        int inputInteger = sc.nextInt();
        return inputInteger;
    }

    public static String inputString(String inputMessage) {
        // Seperate at \n rather than spaces
        sc = sc.useDelimiter("\n");
        
        TerminalService.print(inputMessage);
        String inputString = sc.next();
        
        return inputString;
    }

    public static void main(String[] args) {
        int input = TerminalService.inputInt("Give me 5: ");
        if (input == 5) { TerminalService.print(String.format("Nice, you gave me %s.", input)); }
        else { TerminalService.print(String.format("Hey, why did you give me %s?", input)); }

        ArrayList<String> yolo = new ArrayList<>();

        yolo.add("yellow");
        yolo.add("bad news");
        TerminalService.print(yolo.toString());


    }

    
}
