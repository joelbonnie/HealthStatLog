package persistence;

import model.HealthLog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkHealthLog(HealthLog testLog,String date, double bodyMass, double musclePercentage,
                              double fatPercentage, double waterPercentage, int waterGlassesDrank) {
        assertEquals(date, testLog.getDate());
        assertEquals(bodyMass, testLog.getBodyMass());
        assertEquals(musclePercentage,testLog.getMusclePercentage());
        assertEquals(fatPercentage,testLog.getFatPercentage());
        assertEquals(waterPercentage,testLog.getWaterPercentage());
        assertEquals(waterGlassesDrank,testLog.getWaterGlassesDrank());

    }
}
