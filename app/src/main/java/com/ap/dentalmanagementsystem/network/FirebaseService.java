package com.ap.dentalmanagementsystem.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ap.dentalmanagementsystem.data.Appointment;
import com.ap.dentalmanagementsystem.data.Doctor;
import com.ap.dentalmanagementsystem.data.Patient;
import com.ap.dentalmanagementsystem.data.Prescription;
import com.ap.dentalmanagementsystem.data.Staff;
import com.ap.dentalmanagementsystem.data.Treatment;
import com.ap.dentalmanagementsystem.data.User;
import com.ap.dentalmanagementsystem.model.AppStateModel;
import com.ap.dentalmanagementsystem.network.API.IAppointmentListCallBack;
import com.ap.dentalmanagementsystem.network.API.IPatientListCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

//Singleton Class
public class FirebaseService {
    private final AppStateModel appStateModel;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    public FirebaseService(FirebaseDatabase firebaseDatabase,
                           FirebaseAuth firebaseAuth,
                           AppStateModel appStateModel) {
        this.firebaseDatabase = firebaseDatabase;
        this.firebaseAuth = firebaseAuth;
        this.appStateModel = appStateModel;
    }

    // Registration
    public void signUp(final String firstName, final String lastName, final String email, final String password, final String role, final CallBackI callBackI) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    User u = new User(user.getUid(), firstName, lastName, user.getEmail(), role, user.getPhotoUrl(), user.getProviderId());
                    storeUserProfileInFirebase(u);
                    callBackI.onCallBackComplete(user.getUid(), role);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    callBackI.onCallBackFailed();
                }
            }
        });
    }

    // Login
    public void signIn(String email, String password, final CallBackI callBackI) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            final FirebaseUser user = firebaseAuth.getCurrentUser();
                            firebaseDatabase.getReference("users/" + user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User myUser = dataSnapshot.getValue(User.class);
                                    appStateModel.setCurrentUser(myUser);
                                    callBackI.onCallBackComplete(user.getUid(), myUser.getRole());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", databaseError.toException());
                                    callBackI.onCallBackFailed();
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            callBackI.onCallBackFailed();
                        }
                    }
                });
    }

    private void storeUserProfileInFirebase(User user) {
        DatabaseReference databaseReference = firebaseDatabase.getReference("users").child(user.getUid());
        databaseReference.setValue(user);
    }

    //Check if user is logged in or not


    //Doctor Methods

    public void addPatient(String firstName, String lastName, String dateOfBirth, String sex, String address, String insuranceProvider, String emailID, String mobileNumber, final CallBackI callBackI) {
        Patient p = new Patient(firstName, lastName, dateOfBirth, sex, address, insuranceProvider, emailID, mobileNumber);
        firebaseDatabase.getReference("patients").push().setValue(p, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callBackI.onCallBackComplete("", "");
                } else {
                    callBackI.onCallBackFailed();
                }
            }
        });
    }

    public void addTreatment(Patient patient, String treatmentName, String toothNumber, String labName, String moreInformation, String startDate, String startTime, String endTime, Double cost, final CallBackI callBackI) {
        String patientName = patient.getFirstName() + " " + patient.getLastName();
        final Treatment t = new Treatment(patientName, treatmentName, toothNumber, labName, moreInformation, cost);
        Appointment appointment = new Appointment(patientName, patient.getMobileNumber(), firebaseAuth.getCurrentUser().getEmail(), treatmentName, startDate, startTime, endTime);
        firebaseDatabase.getReference("appointments").push().setValue(appointment, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    firebaseDatabase.getReference("treatment").push().setValue(t, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                callBackI.onCallBackComplete("", "");
                            } else {
                                callBackI.onCallBackFailed();
                            }
                        }
                    });
                } else {
                    callBackI.onCallBackFailed();
                }
            }
        });

    }

    public void addPrescription(String prescriptionNumber, String drugName, double quantity, int frequencyInDay, String moreInformation, final CallBackI callBackI) {
        Prescription p = new Prescription(prescriptionNumber, drugName, quantity, frequencyInDay, moreInformation);
        firebaseDatabase.getReference("prescription").push().setValue(p, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callBackI.onCallBackComplete("", "");
                } else {
                    callBackI.onCallBackFailed();
                }
            }
        });
    }

    //Admin Methods

    public void addStaff(String firstName, String lastName, String dateOfBirth, String sex, String address, String speciality, double mobileNumber, String email, String password, final CallBackI callBackI) {
        Staff staff = new Staff(firstName, lastName, dateOfBirth, sex, address, speciality, mobileNumber, email, password);
        firebaseDatabase.getReference("staff").push().setValue(staff, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callBackI.onCallBackComplete("", "");
                } else {
                    callBackI.onCallBackFailed();
                }
            }
        });
    }

    public void addDoctor(String firstName, String lastName, String dateOfBirth, String sex, String address, String speciality, double mobileNumber, String email, String password, final CallBackI callBackI) {
        Doctor doctor = new Doctor(firstName, lastName, dateOfBirth, sex, address, speciality, mobileNumber, email, password);
        firebaseDatabase.getReference("doctor").push().setValue(doctor, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callBackI.onCallBackComplete("", "");
                } else {
                    callBackI.onCallBackFailed();
                }
            }
        });

    }

    //DoctorHomeScreen getAppointmentsDoctor() Method for Doctor

    public void getAppointmentsDoctor(final IAppointmentListCallBack iAppointmentListCallBack) {
        firebaseDatabase.getReference("appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Appointment> appointmentList = new ArrayList<Appointment>();
                User myUser = appStateModel.getCurrentUser();
                String userName = myUser.getFirstName() + " " + myUser.getLastName();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = childSnapshot.getValue(Appointment.class);
                    if (userName.equals(appointment.getDoctorName())) {
                        // if (appointment.getStartDateTime() >= System.currentTimeMillis()) {
                        appointmentList.add(appointment);
                        //}
                    }
                }
                iAppointmentListCallBack.onCallBackComplete(appointmentList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadAppointment:onCancelled", databaseError.toException());
                iAppointmentListCallBack.onCallBackFailed();
            }
        });
    }

    //Staff and Admin HomeScreen getAppointments

    public void getAppointments(final IAppointmentListCallBack iAppointmentListCallBack) {
        firebaseDatabase.getReference("appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Appointment> appointmentList = new ArrayList<Appointment>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = childSnapshot.getValue(Appointment.class);
                    // if (appointment.getStartDateTime() >= System.currentTimeMillis()) {
                    appointmentList.add(appointment);
                    //}
                }
                iAppointmentListCallBack.onCallBackComplete(appointmentList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadAppointment:onCancelled", databaseError.toException());
                iAppointmentListCallBack.onCallBackFailed();
            }
        });
    }


    // Staff Methods

    public void addAppointments(String patientName, String patientMobileNumber, String doctorName, String appointmentDescription, String startDate, String startTime, String endTime, final CallBackI callBackI) {
        Appointment appointment = new Appointment(patientName, patientMobileNumber, doctorName, appointmentDescription, startDate, startTime, endTime);
        firebaseDatabase.getReference("appointments").push().setValue(appointment, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callBackI.onCallBackComplete("", "");
                } else {
                    callBackI.onCallBackFailed();
                }
            }
        });
    }

    //Search Patients

    public void getPatients(final IPatientListCallBack iPatientListCallBack, final String query) {
        firebaseDatabase.getReference("patients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Patient> patientList = new ArrayList<Patient>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Patient patient = childSnapshot.getValue(Patient.class);
                    String patientName = patient.getFirstName() + " " + patient.getLastName();
                    if (patientName.equals(query)) {
                        patientList.add(patient);
                    }
                }
                iPatientListCallBack.onCallBackComplete(patientList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPatient:onCancelled", databaseError.toException());
                iPatientListCallBack.onCallBackFailed();
            }
        });
    }

    //Show All Patients

    public void getAllPatients(final IPatientListCallBack iPatientListCallBack) {
        firebaseDatabase.getReference("patients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Patient> patientList = new ArrayList<Patient>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Patient patient = childSnapshot.getValue(Patient.class);
                    patientList.add(patient);
                }
                iPatientListCallBack.onCallBackComplete(patientList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPatient:onCancelled", databaseError.toException());
                iPatientListCallBack.onCallBackFailed();
            }
        });
    }

    // Manage Profile

    public void manageProfile(String firstName, String lastName, String dateOfBirth, String sex, String address, String speciality, String mobileNumber, String emailId, final CallBackI callBackI) {
        firebaseDatabase.getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface CallBackI {

        public void onCallBackComplete(String userID, String role);

        public void onCallBackFailed();

    }

}