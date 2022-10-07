package ui;


import java.sql.SQLOutput;
import java.util.Scanner;

// command line interface for running the logger
public class LoggerApp {

    private Scanner scanner;

    // EFFECTS: creates a LoggerApp instance and
    public LoggerApp() {
        takeInput();
    }

    // MODIFIES: this
    // EFFECTS: method to take input from user
    public void takeInput() {
        Boolean continueProgram = true;
        String inputGiven = "";

        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        while (continueProgram) {
            displayInputOptions();
            inputGiven = scanner.nextLine();
            if (inputGiven.equals("Q")) {
                continueProgram = false;
            } else {
                processInput(inputGiven);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: determines which method to call depending on input given
    public void processInput(String inputGiven) {

    }

    // EFFECTS: shows the options available to the user for selection
    public void displayInputOptions() {
        System.out.println("\nSelect Your Option!");
        System.out.println("1  ->  Input daily log");
        System.out.println("2  ->  Calculate BMI");
        System.out.println("3  ->  Calculate BMI class");
        System.out.println("4  ->  See the number of glasses of water drank each day over the past 7 days ");
        System.out.println("5  ->  See how body mass has varied over the past month");
        System.out.println("6  ->  See how muscle percentage has varied over the past month");
        System.out.println("7  ->  See how fat percentage has varied over the past month");
        System.out.println("8  ->  See how water percentage has varied over the past month");
        System.out.println("Q  ->  Quit");
    }

}
