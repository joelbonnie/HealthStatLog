package persistence;

import model.HealthLog;
import model.HealthProgress;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
REFERENCES:
Workroom Project - CPSC210
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

 */


// Class to read from the JSON file
public class JsonRead {
    private String source;

    // EFFECTS: constructs a JsonRead object to read from json file at specified source
    public JsonRead(String source) {
        this.source = source;
    }

    // EFFECTS: reads the JSON file at source and returns corresponding HealthProgress object
    //          throws IOException if an error occurs while reading from the file
    public HealthProgress read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObj = new JSONObject(jsonData);
        return parseHealthProgress(jsonObj);
    }

    // EFFECTS: reads file and returns the content of the file as a string
    //          throws IOException if any error occurs while reading from the file
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }

    // EFFECTS: parses the HealthProgress object from the JSON object
    private HealthProgress parseHealthProgress(JSONObject jsonObj) {
        String name = jsonObj.getString("name");
        HealthProgress myProgress = new HealthProgress(name);
        addHealthLogs(myProgress,jsonObj);
        return myProgress;
    }

    // MODIFIES: healthProgressObj
    // EFFECTS: parses the HealthLogs from the JSON object and adds it to the HealthProgress Object
    private void addHealthLogs(HealthProgress healthProgressObj, JSONObject jsonObj) {
        JSONArray jsonArray = jsonObj.getJSONArray("health logs");
        for (Object jsonLog: jsonArray) {
            JSONObject nextHealthLog = (JSONObject) jsonLog;
            addHealthLog(healthProgressObj,nextHealthLog);
        }
    }

    // MODIFIES: healthProgressObj
    // EFFECTS: parses individual HealthLog from the JSON object and adds it to the HealthProgress Object
    private void addHealthLog(HealthProgress healthProgressObj, JSONObject jsonObj) {
        String date = jsonObj.getString("date");
        double bodyMass = jsonObj.getDouble("body mass");
        double musclePercent = jsonObj.getDouble("muscle percent");
        double fatPercent = jsonObj.getDouble("fat percent");
        double waterPercent = jsonObj.getDouble("water percent");
        int waterGlassesDrank = jsonObj.getInt("water glasses");
        HealthLog currentLog = new HealthLog(date,bodyMass,musclePercent,fatPercent,waterPercent,waterGlassesDrank);
        healthProgressObj.addDailyLog(currentLog);
    }

}
