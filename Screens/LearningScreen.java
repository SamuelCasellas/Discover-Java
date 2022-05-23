package Screens;
import Gui.*;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import FileService.FileService;

// Screen # 3

public class LearningScreen extends Screen {

    public LearningScreen(FileService fileService, Gui theGui, int screenNumber) throws Exception {
        super(fileService, theGui);
        this.screenNumber = screenNumber;
        this.frame.setVisible(true); 
        
        this.concreteNodes(null);
        this.displayScreen();

    }


    @Override
    public void concreteNodes(ArrayList<String> none) {
        JButton newWordButton = new JButton("New word");

        JLabel englishLabel = new JLabel("English");
        JLabel learningLangLabel = new JLabel(); // Input language
        
    }

    @Override
    public int listenforEvents() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public void wordElements(HashMap<String, String> words) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void nNodes(ArrayList<String> elementNames) {

    }



}