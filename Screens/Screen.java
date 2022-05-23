package Screens;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import Gui.*;
import FileService.FileService;

public abstract class Screen implements ActionListener // For jumping from screen to screen 
{ 

    FileService fService;
    Gui gui;
    JFrame frame;
    JPanel panel;

    // These variables are different for each screen
    int screenNumber;
    ArrayList<JLabel> screenLabels = new ArrayList<>();
    ArrayList<JTextField> screenTextAreas = new ArrayList<>();
    ArrayList<JButton> screenButtons = new ArrayList<>();

    public Screen(FileService fileService, Gui theGui) {
        this.fService = fileService;
        this.gui = theGui;
        this.frame = this.gui.getFrame();
        this.panel = this.gui.getPanel();
    }

    protected abstract void concreteNodes(ArrayList<String> elementNames);

    protected abstract void nNodes(ArrayList<String> elementNames);

    public abstract void wordElements(HashMap<String, String> words);


    public void displayScreen() {
        this.gui.setLabels(this.screenLabels, this.frame);
        this.gui.setTextBoxes(this.screenTextAreas, this.frame);
        this.gui.setButtons(this.screenButtons, this.frame);
    }
    
    public void clearScreen() {
        this.gui.clearLabels(this.screenLabels, this.frame);
        this.gui.clearTextBoxes(this.screenTextAreas, this.frame);
        this.gui.clearButtons(this.screenButtons, this.frame);
    }


}