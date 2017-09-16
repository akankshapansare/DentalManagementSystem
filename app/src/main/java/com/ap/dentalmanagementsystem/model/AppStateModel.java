package com.ap.dentalmanagementsystem.model;

import com.ap.dentalmanagementsystem.data.Patient;
import com.ap.dentalmanagementsystem.data.User;

public class AppStateModel {

    private User currentUser;
    private Patient currentPatient;

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
