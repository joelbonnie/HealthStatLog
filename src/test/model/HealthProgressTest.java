package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthProgressTest {
    HealthProgress testHealthProgress;
    HealthLog firstLog = new HealthLog("01102022", 80.0,
            40.0, 20.0,
            50.0, 10);

    @BeforeEach
    public void runBefore() {
        EventLog.getInstance().clear();
        testHealthProgress = new HealthProgress("Joel");
        HealthLog firstLog = new HealthLog("01102022", 80.0,
                40.0, 20.0,
                50.0, 10);
        testHealthProgress.addDailyLog(firstLog);
    }

    @Test
    public void testConstructor() {
        assertEquals(testHealthProgress.getName(), "Joel");


    }

    @Test
    public void testAddDailyLogBasic() {
        assertEquals(testHealthProgress.getHealthLogList().get(0).getDate(), firstLog.getDate());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getBodyMass(), firstLog.getBodyMass());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getMusclePercentage(),
                firstLog.getMusclePercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getFatPercentage(),
                firstLog.getFatPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterPercentage(),
                firstLog.getWaterPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterGlassesDrank(),
                firstLog.getWaterGlassesDrank());
    }

    @Test
    public void testAddDailyLogMultiple() {
        HealthLog secondLog = new HealthLog("02102022", 81.0,
                41.0, 19.0,
                50.0, 8);
        testHealthProgress.addDailyLog(secondLog);

        assertEquals(testHealthProgress.getHealthLogList().get(0).getDate(), firstLog.getDate());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getBodyMass(), firstLog.getBodyMass());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getMusclePercentage(),
                firstLog.getMusclePercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getFatPercentage(),
                firstLog.getFatPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterPercentage(),
                firstLog.getWaterPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterGlassesDrank(),
                firstLog.getWaterGlassesDrank());

        assertEquals(testHealthProgress.getHealthLogList().get(1).getDate(), secondLog.getDate());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getBodyMass(), secondLog.getBodyMass());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getMusclePercentage(),
                secondLog.getMusclePercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getFatPercentage(),
                secondLog.getFatPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getWaterPercentage(),
                secondLog.getWaterPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getWaterGlassesDrank(),
                secondLog.getWaterGlassesDrank());
    }

    @Test
    public void addDailyLogWithEvent() {
        HealthLog secondLog = new HealthLog("02102022", 81.0,
                41.0, 19.0,
                50.0, 8);
        testHealthProgress.addDailyLog(secondLog,true);

        assertEquals(testHealthProgress.getHealthLogList().get(1).getDate(), secondLog.getDate());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getBodyMass(), secondLog.getBodyMass());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getMusclePercentage(),
                secondLog.getMusclePercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getFatPercentage(),
                secondLog.getFatPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getWaterPercentage(),
                secondLog.getWaterPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getWaterGlassesDrank(),
                secondLog.getWaterGlassesDrank());

        ArrayList<Event> events = new ArrayList<>();
        for (Event currentEvent: EventLog.getInstance()) {
            events.add(currentEvent);
        }
        assertEquals("Event log cleared.",events.get(0).getDescription());
        assertEquals("New Health Log Added!", events.get(1).getDescription());
    }

    @Test
    public void addDailyLogWithoutEvent() {
        HealthLog secondLog = new HealthLog("02102022", 81.0,
                41.0, 19.0,
                50.0, 8);
        testHealthProgress.addDailyLog(secondLog,false);

        assertEquals(testHealthProgress.getHealthLogList().get(1).getDate(), secondLog.getDate());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getBodyMass(), secondLog.getBodyMass());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getMusclePercentage(),
                secondLog.getMusclePercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getFatPercentage(),
                secondLog.getFatPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getWaterPercentage(),
                secondLog.getWaterPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(1).getWaterGlassesDrank(),
                secondLog.getWaterGlassesDrank());
    }



    @Test
    public void testRemoveDailyLogRemoveOneWithEvent() {
        HealthLog secondLog = new HealthLog("02102022", 81.0,
                41.0, 19.0,
                50.0, 8);
        testHealthProgress.addDailyLog(secondLog);
        testHealthProgress.removeDailyLog(0);
        assertEquals(1,testHealthProgress.getHealthLogList().size());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getDate(), secondLog.getDate());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getBodyMass(), secondLog.getBodyMass());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getMusclePercentage(),
                secondLog.getMusclePercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getFatPercentage(),
                secondLog.getFatPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterPercentage(),
                secondLog.getWaterPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterGlassesDrank(),
                secondLog.getWaterGlassesDrank());

        ArrayList<Event> events = new ArrayList<>();
        for (Event currentEvent: EventLog.getInstance()) {
            events.add(currentEvent);
        }
        assertEquals("Event log cleared.",events.get(0).getDescription());
        assertEquals("Specified Health Log Removed!", events.get(1).getDescription());
    }

    @Test
    public void testRemoveDailyLogRemoveMultipleWithEvent() {
        HealthLog secondLog = new HealthLog("02102022", 81.0,
                41.0, 19.0,
                50.0, 8);
        testHealthProgress.addDailyLog(secondLog);
        testHealthProgress.removeDailyLog(0);
        testHealthProgress.removeDailyLog(0);
        assertEquals(0,testHealthProgress.getHealthLogList().size());

        ArrayList<Event> events = new ArrayList<>();
        for (Event currentEvent: EventLog.getInstance()) {
            events.add(currentEvent);
        }
        assertEquals("Event log cleared.",events.get(0).getDescription());
        assertEquals("Specified Health Log Removed!", events.get(1).getDescription());
        assertEquals("Specified Health Log Removed!", events.get(2).getDescription());
    }


    @Test
    public void testGetHealthLogListWithoutEvent() {
        ArrayList<HealthLog> currentList = testHealthProgress.getHealthLogList(false);
        ArrayList<Event> events = new ArrayList<>();
        for (Event currentEvent: EventLog.getInstance()) {
            events.add(currentEvent);
        }
        assertEquals(1, events.size());
        assertEquals("Event log cleared.",events.get(0).getDescription());

    }

    @Test
    public void testGetHealthLogListWithEvent() {
        ArrayList<HealthLog> currentList = testHealthProgress.getHealthLogList(true);
        ArrayList<Event> events = new ArrayList<>();
        for (Event currentEvent: EventLog.getInstance()) {
            events.add(currentEvent);
        }
        assertEquals(2, events.size());
        assertEquals("Event log cleared.",events.get(0).getDescription());
        assertEquals("Health Logs Viewed!",events.get(1).getDescription());

    }

    @Test
    public void testSetHealthLogList() {
        ArrayList<HealthLog> healthLogList = new ArrayList<>();
        healthLogList.add(firstLog);
        testHealthProgress.setHealthLogList(healthLogList);

        assertEquals(testHealthProgress.getHealthLogList().get(0).getDate(), firstLog.getDate());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getBodyMass(), firstLog.getBodyMass());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getMusclePercentage(),
                firstLog.getMusclePercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getFatPercentage(),
                firstLog.getFatPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterPercentage(),
                firstLog.getWaterPercentage());
        assertEquals(testHealthProgress.getHealthLogList().get(0).getWaterGlassesDrank(),
                firstLog.getWaterGlassesDrank());


    }



    @Test
    public void testHealthLogToJson(){
        JSONArray jsonArray = testHealthProgress.healthLogToJson();
        JSONObject jsonObj = jsonArray.getJSONObject(0);
        assertEquals("01102022", jsonObj.getString("date"));
        assertEquals(80.0,jsonObj.getDouble("body mass"));
        assertEquals(40.0,jsonObj.getDouble("muscle percent"));
        assertEquals(20.0,jsonObj.getDouble("fat percent"));
        assertEquals(50.0,jsonObj.getDouble("water percent"));
        assertEquals(10,jsonObj.getInt("water glasses"));
    }

    @Test
    public void testToJson(){
        JSONObject jsonObjHealthProgress = testHealthProgress.toJson();
        assertEquals("Joel",jsonObjHealthProgress.getString("name"));

        JSONArray jsonArrayHealthLogs = jsonObjHealthProgress.getJSONArray("health logs");
        JSONObject jsonObjHealthLog = jsonArrayHealthLogs.getJSONObject(0);
        assertEquals("01102022", jsonObjHealthLog.getString("date"));
        assertEquals(80.0,jsonObjHealthLog.getDouble("body mass"));
        assertEquals(40.0,jsonObjHealthLog.getDouble("muscle percent"));
        assertEquals(20.0,jsonObjHealthLog.getDouble("fat percent"));
        assertEquals(50.0,jsonObjHealthLog.getDouble("water percent"));
        assertEquals(10,jsonObjHealthLog.getInt("water glasses"));
    }

}
