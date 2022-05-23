package Screens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import FileService.FileService;
import Gui.Gui;



// Screen # 1

public class MenuScreen extends Screen {

    public MenuScreen(FileService fileService, Gui theGui, int screenNumber) throws Exception {
        super(fileService, theGui);
        this.screenNumber = screenNumber;

        this.concreteNodes(null);
        ArrayList<String> langArrayList = this.fService.getLangsInHeader();
        langArrayList.remove(0); // Remove English
        this.nNodes(langArrayList); // Holds the next event...?

   }

    // Labels: 4
    // Text Field: 1
    // Buttons: n + 1
    
   @Override
   protected void concreteNodes(ArrayList<String> none) {
        JLabel titleTag = new JLabel("Language Learning");
        titleTag.setBounds(325, 50, 400, 25);
        this.screenLabels.add(titleTag);

        JLabel authorTag = new JLabel("Designed by Samuel Casellas");
        authorTag.setBounds(297, 90, 400, 25);
        this.screenLabels.add(authorTag);
        
        JLabel langTag = new JLabel("Languages:");
        langTag.setBounds(210, 235, 100, 25);
        this.screenLabels.add(langTag);
   }

   @Override
   protected void nNodes(ArrayList<String> foreignLangsStarted) {
        // original numbers based off of "Languages:" label.
        int x = 210 + 50;
        int y_seperator = 40;
        int y = 235 + y_seperator;

        for (int i = 0; i < foreignLangsStarted.size(); i++) {
            JButton langbutton = new JButton(foreignLangsStarted.get(i)); // Set button text to lang name.
            langbutton.setBounds(x, y, 150, 25);
            langbutton.addActionListener(this);
            this.screenButtons.add(langbutton);
            y = y + y_seperator;
        }

        // Concrete nodes dependent on location of n nodes
        JLabel newLangTag = new JLabel("New language: ");
        newLangTag.setBounds(x, y, 100, 25);
        this.screenLabels.add(newLangTag);

        JTextField newLangField = new JTextField(20);
        newLangField.setBounds(x + 100, y, 150, 25);
        this.screenTextAreas.add(newLangField);

        JButton newLangButton = new JButton("Begin");
        newLangButton.setBounds(x + 250, y, 100, 25);
        this.screenButtons.add(newLangButton);
        newLangButton.addActionListener(this);
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
   public void wordElements(HashMap<String, String> words) {
       // TODO Auto-generated method stub
       
   }

@Override
public void actionPerformed(ActionEvent e) {
    this.clearScreen();
    String actionRequested = e.getActionCommand();
    this.gui

}

@Override
public synchronized String requestedAction(String actionType) {
    // TODO Auto-generated method stub

    if (actionType.equals("Begin")) {
        // The language the user typed in
        return this.screenTextAreas.get(0).getText(); 
    }
    return actionType;
}





}