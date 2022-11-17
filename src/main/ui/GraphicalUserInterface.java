package ui;

import model.HealthLog;
import model.HealthProgress;
import persistence.JsonRead;
import persistence.JsonWrite;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/*
REFERENCES:

 */

// Class for the Main Graphical User Interface window
public class GraphicalUserInterface {
    private JFrame mainFrame;

    private static final String JSON_STORE = "./data/healthProgress.json";
    protected HealthProgress progressList;

    private JsonRead jsonRead;
    private JsonWrite jsonWrite;

    private JPanel greetPanel;
    private JPanel persistencePanel;
    private JPanel calculationPanel;
    private JPanel logPanel;
    private JPanel addNewLogPanel;

    private JButton loadButton;
    private JButton saveButton;
    private JButton graphButton;
    private JButton calculateButton;
    private JButton viewLogsButton;
    private JButton addLogButton;

    private JTextField dateField;
    private JTextField bodyMassField;
    private JTextField musclePercentageField;
    private JTextField fatPercentageField;
    private JTextField waterPercentageField;
    private JTextField waterGlassesDrankField;

    private JLabel dateLabel;
    private JLabel bodyMassLabel;
    private JLabel musclePercentageLabel;
    private JLabel fatPercentageLabel;
    private JLabel waterPercentageLabel;
    private JLabel waterGlassesDrankLabel;

    private JScrollPane statusPane;
    private JList<String> statusList;
    private DefaultListModel<String> defaultListModel;



    // MODIFIES: this
    // EFFECTS: creates a GraphicalUserInterface object
    public GraphicalUserInterface() {
        mainFrame = new JFrame();

        progressList = new HealthProgress("Joel");
        jsonRead = new JsonRead(JSON_STORE);
        jsonWrite = new JsonWrite(JSON_STORE);

        createMainFrame();
        insertGreetPanel();
        insertPersistencePanel();
        insertStatusPanel();
        insertCalculationPanel();
        insertViewLogPanel();
        insertAddNewLogPanel();

        mainFrame.setVisible(true);



    }

    // MODIFIES: this
    // EFFECTS: creates the frame and sets values
    public void createMainFrame() {
        mainFrame.setTitle("Health Stat Logger");
        mainFrame.setSize(1200,500);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(new Color(0x34495E));
    }


    // MODIFIES: this
    // EFFECTS: inserts a greeting panel to the frame
    public void insertGreetPanel() {
        greetPanel = new JPanel();
        greetPanel.setLayout(null);
        greetPanel.setBounds(20,10,400,100);
        greetPanel.setBackground(new Color(0x34495E));
        mainFrame.add(greetPanel);

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
        mainFrame.add(persistencePanel);

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
        statusPane.setBounds(850,20,300,420);
        statusPane.setBackground(new Color(0xecf0f1));
        statusPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        statusPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainFrame.getContentPane().add(statusPane);

    }

    // MODIFIES: this
    // EFFECTS: inserts a panel to view existing HealthLogs
    public void insertViewLogPanel() {
        logPanel = new JPanel();
        logPanel.setLayout(null);
        logPanel.setBounds(10,350,440,120);
        logPanel.setBackground(new Color(0x34495E));
        mainFrame.add(logPanel);

        viewLogsButton = new JButton();
        viewLogsButton.addActionListener(e -> viewHealthLogs());
        viewLogsButton.setBounds(130,20,175,80);
        viewLogsButton.setText("View Logs");
        viewLogsButton.setFocusable(false);
        viewLogsButton.setBackground(new Color(0x74b9ff));

        logPanel.add(viewLogsButton);

    }

    // MODIFIES: this
    // EFFECTS: inserts a panel for adding a new log
    public void insertAddNewLogPanel() {
        addNewLogPanel = new JPanel();
        addNewLogPanel.setLayout(null);
        addNewLogPanel.setBounds(480,20,350,420);
        mainFrame.add(addNewLogPanel);

        createFields();
        createLabels();

        addLogButton = new JButton();
        addLogButton.addActionListener(e -> analysisLogInputs());
        addLogButton.setBounds(100,340,150,50);
        addLogButton.setText("Add Log");
        addLogButton.setFocusable(false);
        addLogButton.setBackground(new Color(0x74b9ff));

        addNewLogPanel.add(addLogButton);

    }

    // MODIFIES: this
    // EFFECTS: creates JFields for adding a new HealthLog and adds to the JPanel
    public void createFields() {
        dateField = new JTextField();
        dateField.setBounds(180,30,150,30);
        bodyMassField = new JTextField();
        bodyMassField.setBounds(180,80,150,30);
        musclePercentageField = new JTextField();
        musclePercentageField.setBounds(180,130,150,30);
        fatPercentageField = new JTextField();
        fatPercentageField.setBounds(180,180,150,30);
        waterPercentageField = new JTextField();
        waterPercentageField.setBounds(180,230,150,30);
        waterGlassesDrankField = new JTextField();
        waterGlassesDrankField.setBounds(180,280,150,30);

        addNewLogPanel.add(dateField);
        addNewLogPanel.add(bodyMassField);
        addNewLogPanel.add(musclePercentageField);
        addNewLogPanel.add(fatPercentageField);
        addNewLogPanel.add(waterPercentageField);
        addNewLogPanel.add(waterGlassesDrankField);

    }

    // MODIFIES: this
    // EFFECTS: creates JLabels for adding a new HealthLog and adds to the JPanel
    public void createLabels() {
        dateLabel = new JLabel("Date (DDMMYYYY):");
        dateLabel.setBounds(30,30,150,30);
        bodyMassLabel = new JLabel("Body Mass:");
        bodyMassLabel.setBounds(30,80,150,30);
        musclePercentageLabel = new JLabel("Muscle Percent:");
        musclePercentageLabel.setBounds(30,130,150,30);
        fatPercentageLabel = new JLabel("Fat Percent:");
        fatPercentageLabel.setBounds(30,180,150,30);
        waterPercentageLabel = new JLabel("Water Percent:");
        waterPercentageLabel.setBounds(30,230,150,30);
        waterGlassesDrankLabel = new JLabel("Water Glasses:");
        waterGlassesDrankLabel.setBounds(30,280,150,30);

        addNewLogPanel.add(dateLabel);
        addNewLogPanel.add(bodyMassLabel);
        addNewLogPanel.add(musclePercentageLabel);
        addNewLogPanel.add(fatPercentageLabel);
        addNewLogPanel.add(waterPercentageLabel);
        addNewLogPanel.add(waterGlassesDrankLabel);
    }


    // MODIFIES: this
    // EFFECTS: inserts a panel for creating progress graphs and calculating BMI
    public void insertCalculationPanel() {
        calculationPanel = new JPanel();
        calculationPanel.setLayout(null);
        calculationPanel.setBounds(250,120,190,200);
        mainFrame.add(calculationPanel);

        graphButton = new JButton();
        graphButton.addActionListener(e -> loadData());
        graphButton.setBounds(20,20,150,70);
        graphButton.setText("Create Graph");
        graphButton.setFocusable(false);
        graphButton.setBackground(new Color(0xbdc3c7));

        calculateButton = new JButton();
        calculateButton.addActionListener(e -> calculateBMI());
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

    // MODIFIES: this
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
    // EFFECTS: opens a new window to view current HealthLogs
    public void viewHealthLogs() {
        defaultListModel.addElement(" [IN] Open View Health Log Window");
        ViewHealthLogWindow newLogViewer = new ViewHealthLogWindow(progressList);
        defaultListModel.addElement(" [OUT] Opened View Health Log Window");

    }

    // MODIFIES: this
    // EFFECTS: opens a new window to view calculate BMI
    public void calculateBMI() {
        defaultListModel.addElement(" [IN] Open Calculate BMI Window");
        CalculateBodyMassIndexInterface calculateWindow  = new CalculateBodyMassIndexInterface(progressList);
        defaultListModel.addElement(" [OUT] Opened Calculate BMI Window");

    }

    // MODIFIES: this
    // EFFECTS: checks inputs and adds the new HealthLog
    public void analysisLogInputs() {
        defaultListModel.addElement(" [IN] Inputted values");
        String inputtedDate = dateField.getText();
        String inputtedBodyMass = bodyMassField.getText();
        String inputtedMusclePercent = musclePercentageField.getText();
        String inputtedFatPercent = fatPercentageField.getText();
        String inputtedWaterPercent = waterPercentageField.getText();
        String inputtedWaterGlasses = waterGlassesDrankField.getText();
        addLog(inputtedDate,inputtedBodyMass,inputtedMusclePercent,inputtedFatPercent,
                inputtedWaterPercent,inputtedWaterGlasses);

    }

    // MODIFIES: this
    // EFFECTS: tries to parse numbers from inputs and add HealthLog
    public void addLog(String dateString,String bodyMassString,String musclePercentString,
                       String fatPercentString,String waterPercentString,String waterGlassesString) {
        double bodyMass;
        double musclePercent;
        double fatPercent;
        double waterPercent;
        int waterGlasses;
        try {
            if (dateString.equals("")) {
                throw new NullDateException();
            }
            bodyMass = Double.parseDouble(bodyMassString);
            musclePercent = Double.parseDouble(musclePercentString);
            fatPercent = Double.parseDouble(fatPercentString);
            waterPercent = Double.parseDouble(waterPercentString);
            waterGlasses = Integer.parseInt(waterGlassesString);
            HealthLog newLog = new HealthLog(dateString,bodyMass,musclePercent,fatPercent,waterPercent,waterGlasses);
            progressList.addDailyLog(newLog);
            defaultListModel.addElement(" [OUT] Successfully added HealthLog!");

        } catch (NumberFormatException e) {
            defaultListModel.addElement(" [OUT] <ERROR> Error Parsing Number from Input");
        } catch (NullDateException e) {
            defaultListModel.addElement(" [OUT] <ERROR> Date input is empty");
        }

    }





}