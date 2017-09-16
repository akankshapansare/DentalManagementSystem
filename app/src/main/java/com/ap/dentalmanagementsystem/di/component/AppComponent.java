package com.ap.dentalmanagementsystem.di.component;

import com.ap.dentalmanagementsystem.di.module.AppModule;
import com.ap.dentalmanagementsystem.ui.activity.AddPatientActivity;
import com.ap.dentalmanagementsystem.ui.activity.AdminAddDoctorActivity;
import com.ap.dentalmanagementsystem.ui.activity.AdminAddStaffActivity;
import com.ap.dentalmanagementsystem.ui.activity.DoctorAddPrescriptionActivity;
import com.ap.dentalmanagementsystem.ui.activity.DoctorAddTreatmentActivity;
import com.ap.dentalmanagementsystem.ui.activity.HomeScreenActivity;
import com.ap.dentalmanagementsystem.ui.activity.LoginActivity;
import com.ap.dentalmanagementsystem.ui.activity.RegisterActivity;
import com.ap.dentalmanagementsystem.ui.activity.SearchPatientActivity;
import com.ap.dentalmanagementsystem.ui.activity.StaffAddAppointmentActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,})
public interface AppComponent {

    void inject(AddPatientActivity addPatientActivity);

    void inject(AdminAddDoctorActivity adminAddDoctorActivity);

    void inject(AdminAddStaffActivity adminAddStaffActivity);

    void inject(DoctorAddPrescriptionActivity doctorAddPrescriptionActivity);

    void inject(DoctorAddTreatmentActivity doctorAddTreatmentActivity);

    void inject(HomeScreenActivity homeScreenActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(SearchPatientActivity searchPatientActivity);

    void inject(StaffAddAppointmentActivity staffAddAppointmentActivity);
}
