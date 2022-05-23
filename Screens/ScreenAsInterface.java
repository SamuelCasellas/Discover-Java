// IDK

package Screens;
import javax.swing.*;

public interface ScreenAsInterface {

    int getScreenNumber();
    Object gui;
    JLabel[] screenLabels;
    JTextArea[] screenTextAreas;
    JButton[] screenButtons;

    public void displayScreen();
    public void clearScreen();


}