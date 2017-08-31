package com.ap.dentalmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.data.Patient;
import com.ap.dentalmanagementsystem.network.FirebaseService;


public class DoctorAddPrescriptionActivity extends AppCompatActivity {

    private FirebaseService firebaseService = FirebaseService.getInstance();
    private Patient patient;

    public static void start(Context context, Patient patient) {
        Intent intent = new Intent(context, DoctorAddPrescriptionActivity.class);
        intent.putExtra("INTENT_EXTRA_PATIENT", patient);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add_prescription);
        if (getIntent() != null) {
            patient = (Patient) getIntent().getSerializableExtra("INTENT_EXTRA_PATIENT");
        } else {
            patient = (Patient) savedInstanceState.getSerializable("INTENT_EXTRA_PATIENT");
        }

        final TextView patientName = (TextView) findViewById(R.id.text_patient_name);
        patientName.setText(patient.getFirstName() + " " + patient.getLastName());

        final EditText prescriptionNumber = (EditText) findViewById(R.id.number_prescription);
        final EditText drugName = (EditText) findViewById(R.id.text_drug_name);
        final EditText quantity = (EditText) findViewById(R.id.number_quntity);
        final EditText frequencyInDay = (EditText) findViewById(R.id.number_frequency_in_day);
        final EditText moreInformation = (EditText) findViewById(R.id.text_more_information);

        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.addPrescription(prescriptionNumber.getText().toString(), drugName.getText().toString(), Double.valueOf(quantity.getText().toString()), Integer.valueOf(frequencyInDay.getText().toString()), moreInformation.getText().toString(), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID, String role) {
                        Toast.makeText(DoctorAddPrescriptionActivity.this, "Prescription Added", Toast.LENGTH_SHORT).show();
                        HomeScreenActivity.start(DoctorAddPrescriptionActivity.this, "DOCTOR");
                        finish();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(DoctorAddPrescriptionActivity.this, "Prescription Added Failed", Toast.LENGTH_SHORT).show();
                    }
                });
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
}
