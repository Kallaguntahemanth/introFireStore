package com.example.introfirestore;

public class information {
    private String Name;
    private String Breath;
    private String Hungry;
    private String Medication;
    private String Pulse;

    public information() {
    }

    public information(String name, String breath, String hungry, String medication, String pulse) {
        Name = name;
        Breath = breath;
        Hungry = hungry;
        Medication = medication;
        Pulse = pulse;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBreath() {
        return Breath;
    }

    public void setBreath(String breath) {
        Breath = breath;
    }

    public String getHungry() {
        return Hungry;
    }

    public void setHungry(String hungry) {
        Hungry = hungry;
    }

    public String getMedication() {
        return Medication;
    }

    public void setMedication(String medication) {
        Medication = medication;
    }

    public String getPulse() {
        return Pulse;
    }

    public void setPulse(String pulse) {
        Pulse = pulse;
    }
}
