package Screens;
import Gui.*;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import FileService.FileService;

interface Question {
    int questionNumber = 0;
    char questionType = ' ';
}

// Screen # 5

public class QuizScreen extends Screen {

    public QuizScreen(FileService fileService, Gui theGui, int screenNumber) throws Exception {
        super(fileService, theGui);
        this.screenNumber = screenNumber;
        this.frame.setVisible(true); 
        
        this.concreteNodes(null);
        this.displayScreen();
    }


    //public String[] getSpecs

    @Override
    public void concreteNodes(String[] strings) {
        // Labels: 2

        JLabel questionLabel = new JLabel("What does this mean?");
        JLabel wordLabel = new JLabel(); // the word in question

        //Buttons: 4
        JButton answer1 = new JButton("a. {}".format(format, args));
        JButton answer2 = new JButton("b. {}".format(format, args));
        JButton answer3 = new JButton("c. {}".format(format, args));
        JButton answer4 = new JButton("d. {}".format(format, args));
        
    }

    @Override
    public void nNodes(ArrayList<String> elementNames) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void concreteNodes(ArrayList<String> elementNames) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void wordElements(HashMap<String, String> words) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int listenforEvents() {
        // TODO Auto-generated method stub
        return 0;
    }

}