package persistence;



/*
REFERENCES:
Workroom Project - CPSC 210
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

 */

import model.HealthProgress;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Class to write to the JSON file the JSON representation of HealthProgress
public class JsonWrite {
    public static final int TAB = 4;
    private String destinationFile;
    private PrintWriter writer;

    // EFFECTS: constructs a JsonWrite object to write to the JSON file
    public JsonWrite(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer object
    //          throws FileNotFoundException if the file cannot be opened
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of current HealthProgress obj into the file
    public void write(HealthProgress healthProgress) {
        JSONObject healthProgressJson = healthProgress.toJson();
        writeStringIntoFile(healthProgressJson.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer object
    public void closeWriter() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes given string into the file
    public void writeStringIntoFile(String healthProgressString) {
        writer.print(healthProgressString);
    }


}
