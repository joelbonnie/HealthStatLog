package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;



// collection of logged health statistics (List of HeathLogs)
public class HealthProgress implements Writable {

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


    // MODIFIES: this
    // EFFECTS: if addEvent is true, adds a health log to the HealthProgress list
    //          and adds an Event stating that a new health log has been added
    //          else adds the health log to the HealthProgress list
    public void addDailyLog(HealthLog currentLog, Boolean addEvent) {
        if (addEvent) {
            EventLog.getInstance().logEvent(new Event("New Health Log Added!"));
        }
        healthLogList.add(currentLog);
    }

    // REQUIRES: listIndex not out of bounds
    // MODIFIES: this
    // EFFECTS: removes the daily log corresponding to the array index provided
    //          adds an Event stating that the specified health log has been removed.
    public void removeDailyLog(int listIndex) {
        EventLog.getInstance().logEvent(new Event("Specified Health Log Removed!"));
        this.healthLogList.remove(listIndex);
    }


    // EFFECTS: if addEvent is true returns the list of the Health Logs added to the HealthProgress object
    //          and adds an Event stating that health logs have been viewed
    //          else just returns the list of HealthLogs added to the HealthProgress Object
    public ArrayList<HealthLog> getHealthLogList(boolean addEvent) {
        if (addEvent) {
            EventLog.getInstance().logEvent(new Event("Health Logs Viewed!"));
        }
        return healthLogList;
    }

    // EFFECTS: returns the list of the Health Logs added to the HealthProgress object
    public ArrayList<HealthLog> getHealthLogList() {
        return healthLogList;
    }

    // MODIFIES: this
    // EFFECTS: sets the healthLogList as the parameter provided.
    public void setHealthLogList(ArrayList<HealthLog> healthLogList) {
        this.healthLogList = healthLogList;
    }

    // EFFECTS: returns the name provided in the HealthProgress Object
    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name",name);
        jsonObj.put("health logs",healthLogToJson());
        return jsonObj;
    }

    // EFFECTS: returns the HealthLogs in healthLogList as a JSON array
    public JSONArray healthLogToJson() {
        JSONArray healthLogsJson = new JSONArray();
        for (HealthLog currentLog: healthLogList) {
            healthLogsJson.put(currentLog.toJson());
        }
        return healthLogsJson;
    }
}
