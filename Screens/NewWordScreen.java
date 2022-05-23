package Screens;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Gui.*;

import FileService.FileService;

// Screen # 4

public class NewWordScreen extends Screen {

    public NewWordScreen(FileService fileService, Gui theGui, int screenNumber) throws Exception {
        super(fileService, theGui);
        this.screenNumber = screenNumber;
        this.frame.setVisible(true); 
        
        this.concreteNodes();
        this.displayScreen();

    }


    @Override
    public void concreteNodes(String foreignLang1[]) {
        // Fixed number of nodes
        // Labels: 2

        JLabel newWordLabel = new JLabel("New ");
        JLabel englishWordlLabel= new JLabel("Word in English");

        // Text field: 2

        JTextField newWordTextField = new JTextField(20);
        JTextField englishWordTextField = new JTextField(20);

        // Buttons: 2

        JButton enterButton = new JButton("Enter");
        JButton returnButton = new JButton("Return");
        
    }


    @Override
    public int listenforEvents() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public void concreteNodes(ArrayList<String> elementNames) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void nNodes(ArrayList<String> elementNames) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void wordElements(HashMap<String, String> words) {
        // TODO Auto-generated method stub
        
    }

}