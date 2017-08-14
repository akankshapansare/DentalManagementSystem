package com.ap.dentalmanagementsystem.data;


public class Appointment {

    private String pataientName;
    private String patientMobileNumber;
    private String doctorName;
    private String appointmentDescription;
    private String startDate;
    private String startTime;
    private String endTime;

    public Appointment() {

    }

    public Appointment(String pataientName, String patientMobileNumber, String doctorName, String appointmentDescription, String startDate, String startTime, String endTime) {
        this.pataientName = pataientName;
        this.patientMobileNumber = patientMobileNumber;
        this.doctorName = doctorName;
        this.appointmentDescription = appointmentDescription;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPataientName() {
        return pataientName;
    }

    public void setPataientName(String pataientName) {
        this.pataientName = pataientName;
    }

    public String getPatientMobileNumber() {
        return patientMobileNumber;
    }

    public void setPatientMobileNumber(String patientMobileNumber) {
        this.patientMobileNumber = patientMobileNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDateTime) {
        this.startDate = startDateTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
