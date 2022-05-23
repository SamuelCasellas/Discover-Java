package Screens;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import FileService.FileService;
import Gui.*;

// Screen # 2

public class ChooseSessionScreen extends Screen {

    public ChooseSessionScreen(FileService fService, Gui theGui, int screenNumber) {
        super(fService, theGui);
        this.screenNumber = screenNumber;
        this.frame.setVisible(true); 

        this.concreteNodes(null);
        this.displayScreen();

    }

    @Override
    protected void concreteNodes(ArrayList<String> elementNames) {
        // TODO Auto-generated method stub
         //Labels: 1
         JLabel Language = new JLabel(langPicked[0]);

         //Buttons: 2
         JButton learningButton = new JButton("Learning");
         JButton quizButton = new JButton("Quiz");
    }

    @Override
    public void displayScreen() {
        super.displayScreen();
    }
 
    @Override
    public void clearScreen() {
        super.clearScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    protected void nNodes(ArrayList<String> elementNames) {
        // TODO Auto-generated method stub
        
    
    @Override
    public void wordElements(HashMap<String, String> words) {
        // TODO Auto-generated method stub
        
    }



}