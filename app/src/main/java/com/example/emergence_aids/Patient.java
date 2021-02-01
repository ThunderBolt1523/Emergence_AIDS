package com.example.emergence_aids;

import java.io.Serializable;

public class Patient implements Serializable {
    private String patientID;
    private String cameraID;
    private String fullName;
    private String gender;
    private int userID;
    private  String iDpatient;
    private int level;

    public Patient(String patientID, String cameraID, String fullName, String gender, int level) {
        this.patientID = patientID;
        this.cameraID = cameraID;
        this.fullName = fullName;
        this.gender = gender;
        this.level = level;
    }

    public Patient(String patientID, String cameraID, String fullName, String gender) {
        this.patientID = patientID;
        this.cameraID = cameraID;
        this.fullName = fullName;
        this.gender = gender;
    }

    public Patient() {}

    public Patient(String patientID) {
        this.patientID = patientID;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getCameraID() {
        return cameraID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public int getLevel() {
        return level;
    }

    public boolean HasLevel(){
        if(this.level != 0){
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Patient: " + patientID  + " CameraID: " + cameraID + " Full Name: " + fullName + " Gender: " + gender + " is level " + level;
    }

    public String beforeView(){
        return "Patient: " + patientID  + " CameraID: " + cameraID + " Full Name: " + fullName + " Gender: " + gender;
    }
}
