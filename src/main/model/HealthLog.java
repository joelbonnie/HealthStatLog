package model;


import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;



// Class representing daily health statistics log
public class HealthLog implements Writable {

    private String date;
    private double bodyMass;
    private double musclePercentage;
    private double fatPercentage;
    private double waterPercentage;
    private int waterGlassesDrank;

    /*
       REQUIRES:
                 non-zero body mass in kg
                 non-zero muscle percentage in percent
                 non-zero fat percentage in percent
                 non-zero water percentage in percent
       MODIFIES: this
       EFFECTS:  creates a HealthLog object, represents one day's log

     */
    public HealthLog(String date, double bodyMass, double musclePercentage, double fatPercentage,
                     double waterPercentage, int waterGlassesDrank) {
        this.date = date;
        this.bodyMass = bodyMass;
        this.musclePercentage = musclePercentage;
        this.fatPercentage = fatPercentage;
        this.waterPercentage = waterPercentage;
        this.waterGlassesDrank = waterGlassesDrank;


    }

    // REQUIRES: height is non-zero and in m
    // EFFECTS: returns BMI for the current log
    public double determineBodyMassIndex(double height) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(bodyMass / (height * height)));

    }

    // REQUIRES: height is non-zero and in m
    // EFFECTS: returns BMI class for the current log
    public String determineBodyMassIndexClass(double height) {
        double bodyMassIndex = determineBodyMassIndex(height);
        if (bodyMassIndex < 18.50) {
            return "Below Ideal";
        } else if (bodyMassIndex < 25.00) {
            return "Ideal";
        } else {
            return "Above Ideal";

        }

    }


    // EFFECTS: returns the date in format DDMMYYYY
    public String getDate() {
        return date;
    }

    // EFFECTS: returns the body mass in kg
    public double getBodyMass() {
        return bodyMass;
    }

    // EFFECTS: returns the muscle percentage
    public double getMusclePercentage() {
        return musclePercentage;
    }

    // EFFECTS: returns the fat percentage
    public double getFatPercentage() {
        return fatPercentage;
    }

    // EFFECTS: returns the water percentage
    public double getWaterPercentage() {
        return waterPercentage;
    }

    // EFFECTS: returns the number of glasses of water drank
    public int getWaterGlassesDrank() {
        return waterGlassesDrank;
    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("date",date);
        jsonObj.put("body mass",bodyMass);
        jsonObj.put("muscle percent",musclePercentage);
        jsonObj.put("fat percent",fatPercentage);
        jsonObj.put("water percent", waterPercentage);
        jsonObj.put("water glasses",waterGlassesDrank);
        return jsonObj;
    }
}
