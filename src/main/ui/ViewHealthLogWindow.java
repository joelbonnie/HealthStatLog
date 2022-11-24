package ui;

import model.HealthLog;
import model.HealthProgress;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/*
REFERENCES:
https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html
https://stackoverflow.com/questions/3549206/how-to-add-row-in-jtable

 */

// class to view current health logs
public class ViewHealthLogWindow {
    private JFrame viewLogFrame;
    private HealthProgress progressList;
    private DefaultTableModel defaultTableModel;
    private JTable healthLogTable;
    private JScrollPane tablePane;



    // EFFECTS: creates a new frame for viewing HealthLogs
    public ViewHealthLogWindow(HealthProgress progressList) {
        viewLogFrame = new JFrame();
        this.progressList = progressList;
        viewLogFrame.setTitle("View Health Logs");
        viewLogFrame.setSize(750,500);
        viewLogFrame.setResizable(false);
        viewLogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewLogFrame.setLayout(null);
        viewLogFrame.getContentPane().setBackground(new Color(0x34495E));
        initialiseValues();
        addPanels();
        viewLogFrame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: creates a JTable and adds rows
    private void initialiseValues() {
        defaultTableModel = new DefaultTableModel();
        healthLogTable = new JTable(defaultTableModel);
        defaultTableModel.addColumn("Date");
        defaultTableModel.addColumn("Body Mass");
        defaultTableModel.addColumn("Muscle Percentage");
        defaultTableModel.addColumn("Fat Percentage");
        defaultTableModel.addColumn("Water Percentage");
        defaultTableModel.addColumn("Water Glasses");


        ArrayList<HealthLog> allLogs = progressList.getHealthLogList(true);
        for (HealthLog currentLog: allLogs) {
            defaultTableModel.addRow(new Object[]{currentLog.getDate(),currentLog.getBodyMass(),
                    currentLog.getMusclePercentage(),currentLog.getFatPercentage(),
                    currentLog.getWaterPercentage(),currentLog.getWaterGlassesDrank()});
        }

    }

    // MODIFIES: this
    // EFFECTS: adds the JTable to the JFrame
    public void addPanels() {
        tablePane = new JScrollPane(healthLogTable);
        tablePane.setBounds(0,0,750,500);
        tablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        viewLogFrame.getContentPane().add(tablePane);
    }

}
