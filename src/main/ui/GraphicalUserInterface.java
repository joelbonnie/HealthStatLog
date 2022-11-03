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
    private JPanel optionPanel;

    private JButton loadButton;
    private JButton saveButton;

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
        insertOptionPanel();
        insertStatusPanel();

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
    // EFFECTS: inserts an option panel to the frame
    public void insertOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(null);
        optionPanel.setBounds(10,120,400,300);
        optionPanel.setBackground(new Color(0x34495E));
        this.add(optionPanel);

        loadButton = new JButton();
        loadButton.addActionListener(e -> loadData());
        loadButton.setBounds(20,20,150,75);
        loadButton.setText("Load");
        loadButton.setFocusable(false);
        loadButton.setBackground(new Color(0xbdc3c7));

        saveButton = new JButton();
        saveButton.addActionListener(e -> saveData());
        saveButton.setBounds(20,110,150,75);
        saveButton.setText("Save");
        saveButton.setFocusable(false);
        saveButton.setBackground(new Color(0xbdc3c7));

        optionPanel.add(loadButton);
        optionPanel.add(saveButton);

    }

    // MODIFIES: this
    // EFFECTS: inserts an option panel to the frame
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



}