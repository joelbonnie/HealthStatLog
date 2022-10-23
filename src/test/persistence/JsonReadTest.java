package persistence;

import model.HealthLog;
import model.HealthProgress;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
REFERENCES:
Workroom Project - CPSC210
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

 */

public class JsonReadTest extends JsonTest {

    @Test
    public void testJsonReadNonExistentFile() {
        JsonRead readerObject = new JsonRead("./data/nonExistentFile.json");
        try {
            HealthProgress testProgress = readerObject.read();
            fail("IOException is Expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testJsonReadEmptyHealthProgress() {
        JsonRead readerObject = new JsonRead("./data/jsonReadTestEmpty.json");
        try {
            HealthProgress emptyProgress = readerObject.read();
            assertEquals("Joel",emptyProgress.getName());
            assertEquals(0,emptyProgress.getHealthLogList().size());
        } catch (IOException e) {
            fail("Error in reading the JSON file");
        }
    }

    @Test
    public void testJsonReadBasicHealthProgress() {
        JsonRead readerObject = new JsonRead("./data/jsonReadTestBasic.json");
        try {
            HealthProgress basicProgress = readerObject.read();
            assertEquals("Joel",basicProgress.getName());
            List<HealthLog> currentLogs = basicProgress.getHealthLogList();
            assertEquals(2,currentLogs.size());
            checkHealthLog(currentLogs.get(0),"03102022",80.0,50.0,22.0,
                        45.0,10);
            checkHealthLog(currentLogs.get(1),"04102022",70.0,40.0,17.0,
                    50.0,12);
        } catch (IOException e) {
            fail("Error in reading the JSON file");
        }
    }

}
