package ui;

import model.HealthProgress;
import persistence.JsonRead;
import persistence.JsonWrite;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;



// Class for the Graphical User Interface
public class GraphicalUserInterface extends JFrame {
    private static final String JSON_STORE = "./data/healthProgress.json";
    private HealthProgress progressList;
    private JsonRead jsonRead;
    private JsonWrite jsonWrite;

    private JPanel greetPanel;
    private JPanel persistencePanel;
    private JPanel calculationPanel;
    private JPanel logPanel;

    private JButton loadButton;
    private JButton saveButton;
    private JButton graphButton;
    private JButton calculateButton;

    private JScrollPane statusPane;
    private JList<String> statusList;
    private DefaultListModel<String> defaultListModel;



    // MODIFIES: this
    // EFFECTS: creates a GraphicalUserInterface object
    public GraphicalUserInterface() {
        progressList = new HealthProgress("Joel");
        jsonRead = new JsonRead(JSON_STORE);
        jsonWrite = new JsonWrite(JSON_STORE);

        createMainFrame();
        insertGreetPanel();
        insertPersistencePanel();
        insertStatusPanel();
        insertCalculationPanel();

        this.setVisible(true);



    }

    // MODIFIES: this
    // EFFECTS: creates the frame and sets values
    public void createMainFrame() {
        this.setTitle("Health Stat Logger");
        this.setSize(900,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x34495E));
    }


    // MODIFIES: this
    // EFFECTS: inserts a greeting panel to the frame
    public void insertGreetPanel() {
        greetPanel = new JPanel();
        greetPanel.setLayout(null);
        greetPanel.setBounds(20,10,400,100);
        greetPanel.setBackground(new Color(0x34495E));
        this.add(greetPanel);

        JLabel greetLabel = new JLabel();
        greetLabel.setText("Welcome, Joel!");
        greetLabel.setForeground(new Color(0xFFFDD0));
        greetLabel.setFont(new Font("Corbel",Font.PLAIN,25));
        greetLabel.setBounds(0,0,400,50);


        JLabel optionLabel = new JLabel();
        optionLabel.setText("What do you want to do today?");
        optionLabel.setForeground(new Color(0xecf0f1));
        optionLabel.setFont(new Font("Corbel",Font.PLAIN,16));
        optionLabel.setBounds(0,35,400,50);

        greetPanel.add(greetLabel);
        greetPanel.add(optionLabel);

    }

    // MODIFIES: this
    // EFFECTS: inserts a persistence panel to the frame
    public void insertPersistencePanel() {
        persistencePanel = new JPanel();
        persistencePanel.setLayout(null);
        persistencePanel.setBounds(10,120,190,200);
        persistencePanel.setBackground(new Color(0xFFFDD0));
        this.add(persistencePanel);

        loadButton = new JButton();
        loadButton.addActionListener(e -> loadData());
        loadButton.setBounds(20,20,150,70);
        loadButton.setText("Load");
        loadButton.setFocusable(false);
        loadButton.setBackground(new Color(0xbdc3c7));

        saveButton = new JButton();
        saveButton.addActionListener(e -> saveData());
        saveButton.setBounds(20,110,150,70);
        saveButton.setText("Save");
        saveButton.setFocusable(false);
        saveButton.setBackground(new Color(0xbdc3c7));

        persistencePanel.add(loadButton);
        persistencePanel.add(saveButton);

    }

    // MODIFIES: this
    // EFFECTS: inserts a status panel to the frame
    public void insertStatusPanel() {
        defaultListModel = new DefaultListModel<>();
        defaultListModel.addElement("Program Status:");
        defaultListModel.addElement(" [OUT] Started Program");
        statusList = new JList<>(defaultListModel);

        statusPane = new JScrollPane(statusList);
        statusPane.setBounds(550,20,300,400);
        statusPane.setBackground(new Color(0xecf0f1));
        statusPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        statusPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(statusPane);

    }

    // MODIFIES: this
    // EFFECTS: inserts a panel to add a HealthLog or view existing HealthLogs
    public void insertLogPanel() {

    }


    // MODIFIES: this
    // EFFECTS: inserts a panel for creating progress graphs and calculating BMI
    public void insertCalculationPanel() {
        calculationPanel = new JPanel();
        calculationPanel.setLayout(null);
        calculationPanel.setBounds(250,120,190,200);
        this.add(calculationPanel);

        graphButton = new JButton();
        graphButton.addActionListener(e -> loadData());
        graphButton.setBounds(20,20,150,70);
        graphButton.setText("Create Graph");
        graphButton.setFocusable(false);
        graphButton.setBackground(new Color(0xbdc3c7));

        calculateButton = new JButton();
        calculateButton.addActionListener(e -> saveData());
        calculateButton.setBounds(20,110,150,70);
        calculateButton.setText("Calculate BMI");
        calculateButton.setFocusable(false);
        calculateButton.setBackground(new Color(0xbdc3c7));

        calculationPanel.add(graphButton);
        calculationPanel.add(calculateButton);

    }



    // MODIFIES: this
    // EFFECTS: loads HealthProgress object from JSON file
    public void loadData() {
        try {
            defaultListModel.addElement(" [IN] Load Health Logs");
            progressList = jsonRead.read();
            defaultListModel.addElement(" [OUT] Health Logs Loaded!");
        } catch (Exception e) {
            defaultListModel.addElement(" [OUT] <ERROR> Error loading logs from file");
        }
    }

    // EFFECTS: saves the current logs in progressList to the JSON file
    public void saveData() {
        try {
            defaultListModel.addElement(" [IN] Save Health Logs");
            jsonWrite.openWriter();
            jsonWrite.write(progressList);
            jsonWrite.closeWriter();
            defaultListModel.addElement(" [OUT] Health Logs Saved!");

        } catch (FileNotFoundException e) {
            defaultListModel.addElement(" [OUT] <ERROR> Error writing to file");
        }
    }

    // MODIFIES: this
    // EFFECTS: opens a new window to add a HealthLog and updates progressList
    public void addNewHealthLog() {
        AddHealthLogInterface newLogAdder = new AddHealthLogInterface(progressList);
        this.progressList = newLogAdder.getProgressList();
    }



}