package com.ap.dentalmanagementsystem.data;

public class Treatment {

    private String patientName;
    private String treatmentName;
    private String toothNumber;
    private String labName;
    private String moreInformation;
    private Double cost;

    public Treatment(String patientName, String treatmentName, String toothNumber, String labName, String moreInformation, Double cost) {
        this.patientName = patientName;
        this.treatmentName = treatmentName;
        this.toothNumber = toothNumber;
        this.labName = labName;
        this.moreInformation = moreInformation;
        this.cost = cost;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public String getToothNumber() {
        return toothNumber;
    }

    public void setToothNumber(String toothNumber) {
        this.toothNumber = toothNumber;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
