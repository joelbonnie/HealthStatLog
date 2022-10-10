package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthLogTest {

    HealthLog firstTestHealthLog;
    HealthLog secondTestHealthLog;

    @BeforeEach
    public void runBefore() {
        firstTestHealthLog = new HealthLog("03102022", 80.0,
                40.0, 22.0,
                50.0, 10);
        secondTestHealthLog = new HealthLog("04102022", 70.0,
                40.0, 17.0,
                50.0, 12);
    }

    @Test
    public void testConstructor() {
        assertEquals(firstTestHealthLog.getDate(), "03102022");
        assertEquals(firstTestHealthLog.getBodyMass(), 80);
        assertEquals(firstTestHealthLog.getMusclePercentage(), 40);
        assertEquals(firstTestHealthLog.getFatPercentage(), 22);
        assertEquals(firstTestHealthLog.getWaterPercentage(), 50);
        assertEquals(firstTestHealthLog.getWaterGlassesDrank(), 10);
    }

    @Test
    public void testDetermineBodyMassIndexBasic() {
        double height = 1.80;
        assertEquals(firstTestHealthLog.determineBodyMassIndex(height), 24.69);

    }

    @Test
    public void testDetermineBodyMassIndexMultiple() {
        double height = 1.75;
        assertEquals(firstTestHealthLog.determineBodyMassIndex(height), 26.12);
        assertEquals(secondTestHealthLog.determineBodyMassIndex(height), 22.86);

        double anotherHeight = 1.95;
        assertEquals(firstTestHealthLog.determineBodyMassIndex(anotherHeight), 21.04);
        assertEquals(secondTestHealthLog.determineBodyMassIndex(anotherHeight), 18.41);

        // edge cases
        assertEquals(secondTestHealthLog.determineBodyMassIndex(1.945), 18.50);
        assertEquals(secondTestHealthLog.determineBodyMassIndex(1.6733), 25.00);
    }

    @Test
    public void testDetermineBodyMassIndexClassBasic() {
        double height = 1.80;
        assertEquals(firstTestHealthLog.determineBodyMassIndexClass(height), "Ideal");

    }

    @Test
    public void testDetermineBodyMassIndexClassMultiple() {
        double height = 1.75;
        assertEquals(firstTestHealthLog.determineBodyMassIndexClass(height), "Above Ideal");
        assertEquals(secondTestHealthLog.determineBodyMassIndexClass(height), "Ideal");

        double anotherHeight = 1.95;
        assertEquals(firstTestHealthLog.determineBodyMassIndexClass(anotherHeight), "Ideal");
        assertEquals(secondTestHealthLog.determineBodyMassIndexClass(anotherHeight), "Below Ideal");


    }
}