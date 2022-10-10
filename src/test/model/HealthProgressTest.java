package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthProgressTest {
    HealthProgress testHealthProgress;
    HealthLog firstLog = new HealthLog("01102022", 80.0,
            40.0, 20.0,
            50.0, 10);

    @BeforeEach
    public void runBefore() {
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

}
