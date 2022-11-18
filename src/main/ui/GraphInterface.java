package ui;

import model.HealthProgress;
import ui.graphs.*;

import javax.swing.*;
import java.awt.*;



// class to select and display graphs
public class GraphInterface {
    HealthProgress progressList;
    JFrame graphFrame;
    JPanel optionPanel;
    JPanel createPanel;

    JRadioButton bodyMassOption;
    JRadioButton musclePercentOption;
    JRadioButton fatPercentOption;
    JRadioButton waterPercentOption;
    JRadioButton waterGlassesOption;
    ButtonGroup options;

    JButton createButton;



    // EFFECTS: creates a new frame for creating graphs
    public GraphInterface(HealthProgress progressList) {
        graphFrame = new JFrame();
        this.progressList = progressList;
        graphFrame.setTitle("Create Graph");
        graphFrame.setSize(700,300);
        graphFrame.setResizable(false);
        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        graphFrame.setLayout(null);
        graphFrame.getContentPane().setBackground(new Color(0x34495E));
        insertOptionPanel();
        insertCreatePanel();

        graphFrame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: inserts a JPanel for selecting which graphs to create
    public void insertOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(null);
        optionPanel.setBackground(new Color(0x34495E));
        optionPanel.setBounds(0,0,400,300);
        graphFrame.add(optionPanel);

        JLabel greetLabel = new JLabel("Select Graph Option:");
        greetLabel.setBounds(20,5,400,50);
        greetLabel.setForeground(new Color(0xFFFDD0));
        greetLabel.setFont(new Font("Corbel",Font.PLAIN,25));

        optionPanel.add(greetLabel);

        createRadioButtons();

        optionPanel.add(bodyMassOption);
        optionPanel.add(musclePercentOption);
        optionPanel.add(fatPercentOption);
        optionPanel.add(waterPercentOption);
        optionPanel.add(waterGlassesOption);

        createButtonGroup();

    }

    // MODIFIES: this
    // EFFECTS: creates a new Create JPanel with a "Create Graph" Button
    public void insertCreatePanel() {
        createPanel = new JPanel();
        createPanel.setLayout(null);
        createPanel.setBackground(new Color(0x34495E));
        createPanel.setBounds(400,0,300,300);
        graphFrame.add(createPanel);

        createButton = new JButton("Create Graph");
        createButton.setBounds(50,100,200,80);
        createButton.setBackground(new Color(0x74b9ff));
        createButton.addActionListener(e -> createGraph());
        createPanel.add(createButton);
    }


    // MODIFIES: this
    // EFFECTS: creates the radio button for selecting which graph to display
    public void createRadioButtons() {

        bodyMassOption = new JRadioButton(" Body Mass over past 30 logs");
        bodyMassOption.setBounds(15,70,400,20);
        bodyMassOption.setBackground(new Color(0x34495E));
        bodyMassOption.setForeground(new Color(0xecf0f1));

        musclePercentOption = new JRadioButton(" Muscle Percent over past 30 logs");
        musclePercentOption.setBounds(15,100,400,20);
        musclePercentOption.setBackground(new Color(0x34495E));
        musclePercentOption.setForeground(new Color(0xecf0f1));

        fatPercentOption = new JRadioButton(" Fat Percent over past 30 logs");
        fatPercentOption.setBounds(15,130,400,20);
        fatPercentOption.setBackground(new Color(0x34495E));
        fatPercentOption.setForeground(new Color(0xecf0f1));

        waterPercentOption = new JRadioButton(" Water Percent over past 30 logs");
        waterPercentOption.setBounds(15,160,400,20);
        waterPercentOption.setBackground(new Color(0x34495E));
        waterPercentOption.setForeground(new Color(0xecf0f1));

        waterGlassesOption = new JRadioButton(" Water Glasses drank over past 7 logs");
        waterGlassesOption.setBounds(15,190,400,20);
        waterGlassesOption.setBackground(new Color(0x34495E));
        waterGlassesOption.setForeground(new Color(0xecf0f1));

    }

    // MODIFIES: this
    // EFFECTS: creates a button group with the various radio buttons
    public void createButtonGroup() {
        options = new ButtonGroup();
        options.add(bodyMassOption);
        options.add(musclePercentOption);
        options.add(fatPercentOption);
        options.add(waterPercentOption);
        options.add(waterGlassesOption);
    }


    // EFFECTS: calls respective method to create graph depending on button selected
    public void createGraph() {
        if (bodyMassOption.isSelected()) {
            createBodyMassGraph();
        } else if (musclePercentOption.isSelected()) {
            createMusclePercentGraph();
        } else if (fatPercentOption.isSelected()) {
            createFatPercentGraph();
        } else if (waterPercentOption.isSelected()) {
            createWaterPercentGraph();
        } else if (waterGlassesOption.isSelected()) {
            createWaterGlassesGraph();
        }
    }

    // EFFECTS: creates a new body mass scatter-plot
    public void createBodyMassGraph() {
        new BodyMassGraph(progressList);
    }

    // EFFECTS: creates a new muscle percent scatter-plot
    public void createMusclePercentGraph() {
        new MusclePercentGraph(progressList);
    }

    // EFFECTS: creates a new fat percent scatter-plot
    public void createFatPercentGraph() {
        new FatPercentGraph(progressList);
    }

    // EFFECTS: creates a new water percent scatter-plot
    public void createWaterPercentGraph() {
        new WaterPercentGraph(progressList);
    }

    // EFFECTS: creates a new water-glasses scatter-plot
    public void createWaterGlassesGraph() {
        new WaterGlassesGraph(progressList);
    }




}
