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

public class JsonWriteTest extends JsonTest{

    @Test
    public void testJsonWriterInvalidFile() {
        HealthProgress testProgress = new HealthProgress("Joel");
        try {
            JsonWrite writer = new JsonWrite("./data/my\0illegal:fileName.json");
            writer.openWriter();
            fail("IOException is Expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testJsonWriteEmpty() {
        try {
            HealthProgress emptyProgress = new HealthProgress("Joel");
            JsonWrite writer = new JsonWrite("./data/jsonWriteTestEmpty.json");
            writer.openWriter();
            writer.write(emptyProgress);
            writer.closeWriter();

            JsonRead reader = new JsonRead("./data/jsonWriteTestEmpty.json");
            emptyProgress = reader.read();
            assertEquals("Joel", emptyProgress.getName());
            assertEquals(0,emptyProgress.getHealthLogList().size());

        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }

    @Test
    public void testJsonWriteBasic() {
        try {
            HealthProgress basicProgress = new HealthProgress("Joel");
            HealthLog testHealthLog = new HealthLog("03102022", 80.0,
                    40.0, 22.0,
                    50.0, 10);
            basicProgress.addDailyLog(testHealthLog);
            JsonWrite writer = new JsonWrite("./data/jsonWriteTestBasic.json");
            writer.openWriter();
            writer.write(basicProgress);
            writer.closeWriter();

            JsonRead reader = new JsonRead("./data/jsonWriteTestBasic.json");
            basicProgress = reader.read();
            assertEquals("Joel",basicProgress.getName());
            List<HealthLog> logs = basicProgress.getHealthLogList();
            assertEquals(1,logs.size());
            checkHealthLog(logs.get(0),"03102022",80.0,40.0,22.0,
                    50.0,10);


        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }



}
