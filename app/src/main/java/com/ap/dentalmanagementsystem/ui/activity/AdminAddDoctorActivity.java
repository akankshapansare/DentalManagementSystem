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

public class AdminAddDoctorActivity extends AppCompatActivity {

    private FirebaseService firebaseService = FirebaseService.getInstance();

    public static void start(Context context) {
        Intent intent = new Intent(context, AdminAddDoctorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_doctor);

        final EditText firstName = (EditText) findViewById(R.id.text_first_name);
        final EditText lastName = (EditText) findViewById(R.id.text_last_name);
        final EditText dateOfBirth = (EditText) findViewById(R.id.text_date_of_birth);
        final EditText sex = (EditText) findViewById(R.id.text_sex);
        final EditText address = (EditText) findViewById(R.id.text_address);
        final EditText speciality = (EditText) findViewById(R.id.text_speciality);
        final EditText mobileNumber = (EditText) findViewById(R.id.number_mobile);
        final EditText email = (EditText) findViewById(R.id.text_email);
        final EditText password = (EditText) findViewById(R.id.text_password);

        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.addDoctor(firstName.getText().toString(), lastName.getText().toString(), dateOfBirth.getText().toString(), sex.getText().toString(), address.getText().toString(), speciality.getText().toString(), Double.parseDouble(mobileNumber.getText().toString()), email.getText().toString(), password.getText().toString(), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID, String role) {
                        Toast.makeText(AdminAddDoctorActivity.this, "Doctor Created", Toast.LENGTH_SHORT).show();
                        HomeScreenActivity.start(AdminAddDoctorActivity.this, "ADMIN");
                        finish();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(AdminAddDoctorActivity.this, "Doctor Created Failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
