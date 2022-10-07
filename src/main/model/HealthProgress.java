package model;

import java.util.ArrayList;
import java.util.List;


// collection of logged health statistics (List of HeathLogs)
public class HealthProgress {

    private String name;
    private ArrayList<HealthLog> healthLogList;


    // EFFECTS: creates a list of healthlogs for multiple days for the given name
    public HealthProgress(String name) {
        this.name = name;
        healthLogList = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds a health log to the HealthProgress list
    public void addDailyLog(HealthLog currentLog) {
        healthLogList.add(currentLog);
    }


    // EFFECTS: returns the list of the Health Logs added to the HealthProgress object
    public List<HealthLog> getHealthLogList() {
        return healthLogList;
    }


    // EFFECTS: returns the name provided in the HealthProgress Object
    public String getName() {
        return name;
    }

}
