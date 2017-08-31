package com.ap.dentalmanagementsystem.data;


public class Prescription {

    private String prescriptionNumber;
    private String drugName;
    private double quantity;
    private int frequencyInDay;
    private String moreInformation;

    public Prescription(String prescriptionNumber, String drugName, double quantity, int frequencyInDay, String moreInformation) {
        this.prescriptionNumber = prescriptionNumber;
        this.drugName = drugName;
        this.quantity = quantity;
        this.frequencyInDay = frequencyInDay;
        this.moreInformation = moreInformation;
    }

    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(String prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getFrequencyInDay() {
        return frequencyInDay;
    }

    public void setFrequencyInDay(int frequencyInDay) {
        this.frequencyInDay = frequencyInDay;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }
}
