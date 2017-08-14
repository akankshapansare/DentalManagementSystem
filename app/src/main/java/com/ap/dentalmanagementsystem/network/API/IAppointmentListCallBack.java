package com.ap.dentalmanagementsystem.network.API;

import com.ap.dentalmanagementsystem.data.Appointment;

import java.util.List;

public interface IAppointmentListCallBack {

    public void onCallBackComplete(List<Appointment> appointmentList);

    public void onCallBackFailed();

}
