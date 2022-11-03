package ui;

import model.HealthProgress;

import javax.swing.*;
import java.awt.*;

// Class for adding a new HealthLog using a GUI
public class AddHealthLogInterface extends JFrame {
    HealthProgress progressList;

    // EFFECTS: creates a AddHealthLogInterface object to add a new HealthLog
    public AddHealthLogInterface(HealthProgress progressList) {
        this.progressList = progressList;

        this.setTitle("Add Health Log");
        this.setSize(900,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x34495E));

    }

    // EFFECTS: returns the current progressList
    public HealthProgress getProgressList() {
        return progressList;
    }

}
