package com.ap.dentalmanagementsystem.network.API;

import com.ap.dentalmanagementsystem.data.Patient;

import java.util.List;

public interface IPatientListCallBack {
    public void onCallBackComplete(List<Patient> patientList);

    public void onCallBackFailed();
}
