package dialogs;

import view.GUI;

import javax.swing.*;

public class NamesDialog {

    public NamesDialog() {
        JFrame playersFrame = new JFrame();

        GUI gui = new GUI(JOptionPane.showInputDialog(playersFrame, "Player 1 name : "),
                JOptionPane.showInputDialog(playersFrame, "Player 2 name : "));

    }
}
