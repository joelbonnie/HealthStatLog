package ui;


import model.HealthLog;
import model.HealthProgress;
import persistence.JsonRead;
import persistence.JsonWrite;

import java.io.FileNotFoundException;
import java.util.Scanner;


// command line interface for running the logger
public class LoggerApp {
    private static final String JSON_STORE = "./data/healthProgress.json";
    private HealthProgress progressList;
    private Scanner scanner;
    private JsonRead jsonRead;
    private JsonWrite jsonWrite;

    // EFFECTS: creates a LoggerApp instance and runs application
    public LoggerApp() throws FileNotFoundException {
        progressList = new HealthProgress("Joel");
        jsonRead = new JsonRead(JSON_STORE);
        jsonWrite = new JsonWrite(JSON_STORE);
        takeInput();
    }

    // MODIFIES: this
    // EFFECTS: method to take input from user
    public void takeInput() {
        boolean continueProgram = true;
        String inputGiven;
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        while (continueProgram) {
            displayInputOptions();
            inputGiven = scanner.next();

            if (inputGiven.equals("Q")) {
                continueProgram = false;
            } else {
                processInput(inputGiven);
                System.out.println("Do you want to continue? (type N to quit/any character to continue)");
                String checkContinueCondition = scanner.next();
                if (checkContinueCondition.equals("N")) {
                    continueProgram = false;
                }
            }
        }
        System.out.println("Thank You!");
    }


    // MODIFIES: this
    // EFFECTS: determines which method to call depending on input given
    public void processInput(String inputGiven) {
        if (inputGiven.equals("1")) {
            inputLog();
        } else if (inputGiven.equals("2")) {
            calculateBodyMassIndex();
        } else if (inputGiven.equals("3")) {
            calculateBodyMassIndexClass();
        } else if (inputGiven.equals("4")) {
            calculateWaterGlassProgress();
        } else if (inputGiven.equals("5")) {
            calculateBodyMassProgress();
        } else if (inputGiven.equals("6")) {
            calculateMusclePercentProgress();
        } else if (inputGiven.equals("7")) {
            calculateFatPercentProgress();
        } else if (inputGiven.equals("8")) {
            calculateWaterPercentProgress();
        } else if (inputGiven.equals("L")) {
            loadData();
        } else if (inputGiven.equals("S")) {
            saveData();
        }

    }


    // EFFECTS: shows the options available to the user for selection
    public void displayInputOptions() {
        System.out.println("\nSelect Your Option!");
        System.out.println("****************************************************************************************");
        System.out.println("*   [1]   ->  Input daily log                                                          *");
        System.out.println("*   [2]   ->  Calculate BMI                                                            *");
        System.out.println("*   [3]   ->  Calculate BMI class                                                      *");
        System.out.println("*   [4]   ->  See the number of glasses of water drank each day over the past 7 days   *");
        System.out.println("*   [5]   ->  See how body mass has varied over the past month                         *");
        System.out.println("*   [6]   ->  See how muscle percentage has varied over the past month                 *");
        System.out.println("*   [7]   ->  See how fat percentage has varied over the past month                    *");
        System.out.println("*   [8]   ->  See how water percentage has varied over the past month                  *");
        System.out.println("*   [L]   ->  Load                                                                     *");
        System.out.println("*   [S]   ->  Save                                                                     *");
        System.out.println("*   [Q]   ->  Quit                                                                     *");
        System.out.println("****************************************************************************************");
        System.out.println("Your option: ");

    }


    // EFFECTS: creates a HealthLog Object with the specified values
    public void inputLog() {

        System.out.println("Enter the date (in format DDMMYYYY): ");
        String date = scanner.next();
        System.out.println("Enter Body Mass (in kg): ");
        double bodyMass = scanner.nextDouble();
        System.out.println("Enter Muscle Percentage (in percent): ");
        double musclePercentage = scanner.nextDouble();
        System.out.println("Enter Fat Percentage (in percent): ");
        double fatPercentage = scanner.nextDouble();
        System.out.println("Enter Water Percentage (in percent): ");
        double waterPercentage = scanner.nextDouble();
        System.out.println("Enter the number of water glasses: ");
        int waterGlassesDrank = scanner.nextInt();

        HealthLog todaysLog = new HealthLog(date, bodyMass, musclePercentage,
                fatPercentage, waterPercentage, waterGlassesDrank);
        progressList.addDailyLog(todaysLog);
        System.out.println("Log Added!");

    }

    // EFFECTS: calculates body mass index with inputted height and most recent Health Log
    public void calculateBodyMassIndex() {
        System.out.println("Enter height (in metres): ");
        double height = scanner.nextDouble();

        int numberOfHealthLogs = progressList.getHealthLogList().size();
        if (numberOfHealthLogs == 0) {
            System.out.println("You have no health logs yet. Add a health log!");
        } else {
            HealthLog mostRecentHealthLog = progressList.getHealthLogList().get(numberOfHealthLogs - 1);
            double currentBMI = mostRecentHealthLog.determineBodyMassIndex(height);
            System.out.println("Your current BMI is " + currentBMI);
        }

    }

    // EFFECTS: calculates body mass index class with inputted height and most recent Health Log
    public void calculateBodyMassIndexClass() {
        System.out.println("Enter height (in metres): ");
        double height = scanner.nextDouble();

        int numberOfHealthLogs = progressList.getHealthLogList().size();
        if (numberOfHealthLogs == 0) {
            System.out.println("You have no health logs yet. Add a health log!");
        } else {
            HealthLog mostRecentHealthLog = progressList.getHealthLogList().get(numberOfHealthLogs - 1);
            String currentBodyMassIndexClass = mostRecentHealthLog.determineBodyMassIndexClass(height);
            System.out.println("Your current BMI Class is " + currentBodyMassIndexClass);
        }
    }

    // EFFECTS: shows variation in glasses of water drank daily over the most recent 7 Health Logs
    public void calculateWaterGlassProgress() {
        int numberOfHealthLogs = progressList.getHealthLogList().size();
        if (numberOfHealthLogs == 0) {
            System.out.println("You have no health logs yet. Add a health log!");
        } else {
            if (numberOfHealthLogs < 7) {
                System.out.println("You have less than 7 health logs! Showing progress for those available! ");
            }
            for (int healthLogCounter = 0; (healthLogCounter < 7 && healthLogCounter < numberOfHealthLogs);
                    healthLogCounter++) {
                int currentWaterGlasses = progressList.getHealthLogList().get(healthLogCounter).getWaterGlassesDrank();
                System.out.println(currentWaterGlasses);

            }
        }
    }


    // EFFECTS: prints variation in Body Mass over the most recent 30 Health Logs
    public void calculateBodyMassProgress() {
        int numberOfHealthLogs = progressList.getHealthLogList().size();
        if (numberOfHealthLogs == 0) {
            System.out.println("You have no health logs yet. Add a health log!");
        } else {
            printLogs(numberOfHealthLogs,"BodyMass");
        }
    }

    // EFFECTS: prints variation in Muscle Percentage over the most recent 30 Health Logs
    public void calculateMusclePercentProgress() {
        int numberOfHealthLogs = progressList.getHealthLogList().size();
        if (numberOfHealthLogs == 0) {
            System.out.println("You have no health logs yet. Add a health log!");
        } else {
            printLogs(numberOfHealthLogs,"MusclePercent");
        }
    }

    // EFFECTS: prints variation in Fat Percentage over the most recent 30 Health Logs
    public void calculateFatPercentProgress() {
        int numberOfHealthLogs = progressList.getHealthLogList().size();
        if (numberOfHealthLogs == 0) {
            System.out.println("You have no health logs yet. Add a health log!");
        } else {
            printLogs(numberOfHealthLogs,"FatPercent");
        }
    }

    // EFFECTS: prints variation in Water Percentage over the most recent 30 Health Logs
    public void calculateWaterPercentProgress() {
        int numberOfHealthLogs = progressList.getHealthLogList().size();
        if (numberOfHealthLogs == 0) {
            System.out.println("You have no health logs yet. Add a health log!");
        } else {
            printLogs(numberOfHealthLogs, "WaterPercent");
        }
    }

    // REQUIRES: quantity is one of the following: BodyMass MusclePercent FatPercent WaterPercent
    // EFFECTS: prints variation for the given quantity
    public void printLogs(int numHealthLog,String quantity) {
        if (numHealthLog < 30) {
            System.out.println("You have less than 30 health logs! Showing progress for those available! ");
        }
        for (int healthLogCounter = 0; (healthLogCounter < 30 && healthLogCounter < numHealthLog);
                healthLogCounter++) {
            double currentProgress = 0.0;
            if (quantity.equals("BodyMass")) {
                currentProgress = progressList.getHealthLogList().get(healthLogCounter).getBodyMass();
            } else if (quantity.equals("MusclePercent")) {
                currentProgress = progressList.getHealthLogList().get(healthLogCounter).getMusclePercentage();
            } else if (quantity.equals("FatPercent")) {
                currentProgress = progressList.getHealthLogList().get(healthLogCounter).getFatPercentage();
            } else if (quantity.equals("WaterPercent")) {
                currentProgress = progressList.getHealthLogList().get(healthLogCounter).getWaterPercentage();
            }
            System.out.println(currentProgress);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads HealthProgress object from JSON file
    public void loadData() {
        try {
            progressList = jsonRead.read();
            System.out.println("Health Logs for " + progressList.getName() + " have been loaded!");
        } catch (Exception e) {
            System.out.println("Error loading logs from file" + JSON_STORE);
        }
    }

    // EFFECTS: saves the current logs in progressList to the JSON file
    public void saveData() {
        try {
            jsonWrite.openWriter();
            jsonWrite.write(progressList);
            jsonWrite.closeWriter();
            System.out.println("Health Logs for " + progressList.getName() + " have been saved!");

        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file" + JSON_STORE);
        }
    }


}
