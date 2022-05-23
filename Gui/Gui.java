package Gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Screens.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import FileService.FileService;

public class Gui {
    /* Display and handle the screen for the user.
    */

    private FileService fileService;

    private JFrame frame = new JFrame(); // The actual window
    private JPanel panel = new JPanel(); // The border for the elements in the frame
    private Screens.Screen[] screens = new Screens.Screen[5];
    private int showingScreen = 0;
    
    public Gui(FileService fService) throws Exception {

        this.fileService = fService;

        frame.setTitle("Language Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //
        frame.setSize(800, 800);
        frame.setLayout(null);
        
        
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
  //      panel.setLayout(new BorderLayout()); // Rows and columns // New GridManager(0, 1)
        frame.add(panel, BorderLayout.CENTER);
        
        MenuScreen menuScreen = new MenuScreen(this.fileService, this, 1);
        screens[0] = menuScreen;
        menuScreen.displayScreen();


        // ChooseSessionScreen chooseSessionScreen = new ChooseSessionScreen(this.fileService, this, 2);
        // screens[1] = chooseSessionScreen;

        // LearningScreen learningScreen = new LearningScreen(this.fileService, this, 3);
        // screens[2] = learningScreen;

        // NewWordScreen newWordScreen = new NewWordScreen(this.fileService, this, 4);
        // screens[3] = newWordScreen;

        // QuizScreen quizScreen = new QuizScreen(this.fileService, this, 5);
        // screens[4] = quizScreen;

        frame.setVisible(true);

            //frame.pack(); // Not sure what this does. It seems to shrink the screen.

    }

    public void manageLangSession() {
        // Start in the menu
        while (true) {
            Screen screen = screens[this.showingScreen];
            screen.displayScreen();


        }

    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public static void main(String args[]) throws Exception { // Test the above code here...
        new Gui(new FileService("CSV/word_list.csv"));
    }
    

    public void setLabels(ArrayList<JLabel> nodes, JFrame theFrame) { // Figure this out later... how to add multiple kinds of classes in an array
        for (int i = 0; i < nodes.size(); i++) {
            theFrame.add(nodes.get(i));
        }
    }

    public void setTextBoxes(ArrayList<JTextField> nodes, JFrame theFrame) { // Figure this out later... how to add multiple kinds of classes in an array
        for (int i = 0; i < nodes.size(); i++) {
            theFrame.add(nodes.get(i));
        }
    }

    public void setButtons(ArrayList<JButton> nodes, JFrame theFrame) { // Figure this out later... how to add multiple kinds of classes in an array
        for (int i = 0; i < nodes.size(); i++) {
            theFrame.add(nodes.get(i));
        }
    }

    public void clearLabels(ArrayList<JLabel> nodes, JFrame theFrame) { // Figure this out later... how to add multiple kinds of classes in an array
        for (int i = 0; i < nodes.size(); i++) {
            theFrame.remove(nodes.get(i));
        }
    }

    public void clearTextBoxes(ArrayList<JTextField> nodes, JFrame theFrame) { // Figure this out later... how to add multiple kinds of classes in an array
        for (int i = 0; i < nodes.size(); i++) {
            theFrame.remove(nodes.get(i));
        }
    }

    public void clearButtons(ArrayList<JButton> nodes, JFrame theFrame) { // Figure this out later... how to add multiple kinds of classes in an array
        for (int i = 0; i < nodes.size(); i++) {
            theFrame.remove(nodes.get(i));
        }
    }
    
    public static void clearScreen() {

    }


}

