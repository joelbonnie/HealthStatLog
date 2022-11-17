package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


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


    // EFFECTS: returns the list of the Health Logs added to the HealthProgress object
    public ArrayList<HealthLog> getHealthLogList() {
        return healthLogList;
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
