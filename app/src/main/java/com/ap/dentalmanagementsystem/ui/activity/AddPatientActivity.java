package com.ap.dentalmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.network.FirebaseService;

public class AddPatientActivity extends AppCompatActivity {

    private FirebaseService firebaseService = FirebaseService.getInstance();

    public static void start(Context context) {
        Intent intent = new Intent(context, AddPatientActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        final EditText firstNameText = (EditText) findViewById(R.id.text_first_name);
        final EditText lastNameText = (EditText) findViewById(R.id.text_last_name);
        final EditText dateOfBirthText = (EditText) findViewById(R.id.text_date_of_birth);
        final EditText sexText = (EditText) findViewById(R.id.text_sex);
        final EditText addressText = (EditText) findViewById(R.id.text_address);
        final EditText insuranceProviderText = (EditText) findViewById(R.id.text_insurance_provider);
        final EditText emailText = (EditText) findViewById(R.id.text_email);
        final EditText mobileNumber = (EditText) findViewById(R.id.number_mobile);

        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.addPatient(firstNameText.getText().toString(), lastNameText.getText().toString(), dateOfBirthText.getText().toString(), sexText.getText().toString(), addressText.getText().toString(), insuranceProviderText.getText().toString(), emailText.getText().toString(), mobileNumber.getText().toString(), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID, String role) {
                        Toast.makeText(AddPatientActivity.this, "Patient Created", Toast.LENGTH_SHORT).show();
                        HomeScreenActivity.start(AddPatientActivity.this, "DOCTOR");
                        finish();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(AddPatientActivity.this, "Patient Created Failed", Toast.LENGTH_SHORT).show();
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
}
