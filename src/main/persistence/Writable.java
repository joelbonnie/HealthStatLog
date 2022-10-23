package persistence;

import org.json.JSONObject;

public interface Writable {

    // EFFECTS: returns the contents to be written as a JSON file
    JSONObject toJson();
}
