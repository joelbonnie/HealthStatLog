package ui;

import model.HealthLog;
import model.HealthProgress;

import javax.swing.*;
import java.awt.*;

/*
REFERENCES:
https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
 */

// Class to calculate BMI and BMI class
public class CalculateBodyMassIndexInterface {
    private HealthProgress progressList;
    private JFrame calculateFrame;

    private JPanel inputPanel;
    private JLabel heightLabel;
    private JTextField heightField;
    private JButton calculateButton;

    private JPanel outputPanel;
    private JScrollPane outputScrollPane;
    private JList<String> outputList;
    private DefaultListModel<String> defaultListModel;


    // EFFECTS: creates a new frame for calculating BMI
    public CalculateBodyMassIndexInterface(HealthProgress progressList) {
        calculateFrame = new JFrame();
        this.progressList = progressList;
        calculateFrame.setTitle("Calculate BMI");
        calculateFrame.setSize(700,300);
        calculateFrame.setResizable(false);
        calculateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calculateFrame.setLayout(null);
        calculateFrame.getContentPane().setBackground(new Color(0x34495E));

        insertInputPanel();
        insertOutputPanel();
        calculateFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: inserts the inputPanel JPanel with input options
    public void insertInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBackground(new Color(0x34495E));
        inputPanel.setBounds(0,0,250,200);
        calculateFrame.add(inputPanel);

        heightLabel = new JLabel("Enter Height (metres):");
        heightLabel.setForeground(new Color(0xecf0f1));
        heightLabel.setBounds(40,40,200,30);
        inputPanel.add(heightLabel);

        heightField = new JTextField();
        heightField.setBounds(40,80,150,30);
        inputPanel.add(heightField);

        calculateButton = new JButton();
        calculateButton.setBounds(40,150,150,50);
        calculateButton.setText("Calculate BMI");
        calculateButton.setFocusable(false);
        calculateButton.setBackground(new Color(0x74b9ff));
        calculateButton.addActionListener(e -> calculateBMI());
        inputPanel.add(calculateButton);
    }



    // MODIFIES: this
    // EFFECTS: inserts the outputPanel JPanel and corresponding components
    public void insertOutputPanel() {
        outputPanel = new JPanel();
        outputPanel.setLayout(null);
        outputPanel.setBounds(250,40,400,200);
        calculateFrame.add(outputPanel);

        defaultListModel = new DefaultListModel<>();
        outputList = new JList<>(defaultListModel);

        outputScrollPane = new JScrollPane(outputList);
        outputScrollPane.setBounds(0,0,400,200);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        outputPanel.add(outputScrollPane);

    }

    // MODIFIES: this
    // EFFECTS: calculates BMI and BMI class with the given height
    public void calculateBMI() {
        String inputtedHeight = heightField.getText();
        defaultListModel.addElement(" [IN] Inputted " + inputtedHeight);
        Double height;
        try {
            height = Double.parseDouble(inputtedHeight);
            int numberOfHealthLogs = progressList.getHealthLogList().size();
            if (numberOfHealthLogs == 0) {
                defaultListModel.addElement(" [OUT] You have no health logs yet. Add a health log!");
            } else {
                HealthLog mostRecentHealthLog = progressList.getHealthLogList().get(numberOfHealthLogs - 1);
                double currentBMI = mostRecentHealthLog.determineBodyMassIndex(height);
                String currentBodyMassIndexClass = mostRecentHealthLog.determineBodyMassIndexClass(height);

                defaultListModel.addElement(" [OUT] Your Current BMI is " + currentBMI);
                defaultListModel.addElement(" [OUT] Your Current BMI Class is " + currentBodyMassIndexClass);
            }

        } catch (NumberFormatException e) {
            defaultListModel.addElement(" [OUT] <ERROR> Error Parsing Number from Input");
        }
    }
}
