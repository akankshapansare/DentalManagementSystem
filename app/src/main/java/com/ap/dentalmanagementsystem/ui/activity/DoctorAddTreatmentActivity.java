package com.ap.dentalmanagementsystem.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.DMSApplication;
import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.data.Patient;
import com.ap.dentalmanagementsystem.network.FirebaseService;
import com.ap.dentalmanagementsystem.ui.fragment.ToothDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;


public class DoctorAddTreatmentActivity extends AppCompatActivity {

    @Inject
    FirebaseService firebaseService;

    private Patient patient;
    private Calendar calendar = Calendar.getInstance();
    private EditText toothNumber;

    public static void start(Context context, Patient patient) {
        Intent intent = new Intent(context, DoctorAddTreatmentActivity.class);
        intent.putExtra("INTENT_EXTRA_PATIENT", patient);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DMSApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_doctor_add_treatment);

        if (getIntent() != null) {
            patient = (Patient) getIntent().getSerializableExtra("INTENT_EXTRA_PATIENT");
        } else {
            patient = (Patient) savedInstanceState.getSerializable("INTENT_EXTRA_PATIENT");
        }

        final TextView patientName = (TextView) findViewById(R.id.text_patient_name);
        patientName.setText(patient.getFirstName() + " " + patient.getLastName());
        final EditText treatmentName = (EditText) findViewById(R.id.text_treatment_name);
        toothNumber = (EditText) findViewById(R.id.number_tooth);
        final EditText labName = (EditText) findViewById(R.id.text_lab_name);
        final EditText moreInformation = (EditText) findViewById(R.id.text_more_information);
        final EditText cost = (EditText) findViewById(R.id.number_cost);
        final EditText startDate = (EditText) findViewById(R.id.text_appointment_start_date);
        final EditText startTime = (EditText) findViewById(R.id.text_appointment_start_time);
        final EditText endTime = (EditText) findViewById(R.id.text_appointment_end_time);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DoctorAddTreatmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                String myFormat = "dd MMM yyyy";
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                                startDate.setText(sdf.format(calendar.getTime()));
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(DoctorAddTreatmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                String myFormat = "HH:mm";
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                                startTime.setText(sdf.format(calendar.getTime()));
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true);

                timePickerDialog.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(DoctorAddTreatmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                String myFormat = "HH:mm";
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                                endTime.setText(sdf.format(calendar.getTime()));
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true);

                timePickerDialog.show();
            }
        });

        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.addTreatment(patient, treatmentName.getText().toString(), toothNumber.getText().toString(), labName.getText().toString(), moreInformation.getText().toString(), startDate.getText().toString(), startTime.getText().toString(), endTime.getText().toString(), Double.valueOf(cost.getText().toString()), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID, String role) {
                        Toast.makeText(DoctorAddTreatmentActivity.this, "Treatment Added", Toast.LENGTH_SHORT).show();
                        HomeScreenActivity.start(DoctorAddTreatmentActivity.this, "DOCTOR");
                        finish();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(DoctorAddTreatmentActivity.this, "Treatment Added Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        toothNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToothDialogFragment.show(getFragmentManager());
            }
        });

        Button cancelButton = (Button) findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("INTENT_EXTRA_PATIENT", patient);
        super.onSaveInstanceState(outState);
    }

    public void onToothSaved(String toothSaved) {
        toothNumber.setText(toothSaved);
    }
}
