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
import android.widget.TimePicker;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.network.FirebaseService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StaffAddAppointmentActivity extends AppCompatActivity {


    private Calendar calendar = Calendar.getInstance();
    private FirebaseService firebaseService = FirebaseService.getInstance();

    public static void start(Context context) {
        Intent intent = new Intent(context, StaffAddAppointmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add_appointment);

        final EditText patientName = (EditText) findViewById(R.id.text_name_patient);
        final EditText patientMobileNumber = (EditText) findViewById(R.id.number_mobile_patient);
        final EditText doctorName = (EditText) findViewById(R.id.text_name_doctor);
        final EditText appointmentDescription = (EditText) findViewById(R.id.text_appointment_description);
        final EditText startDate = (EditText) findViewById(R.id.text_appointment_start_date);
        final EditText startTime = (EditText) findViewById(R.id.text_appointment_start_time);
        final EditText endTime = (EditText) findViewById(R.id.text_appointment_end_time);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(StaffAddAppointmentActivity.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(StaffAddAppointmentActivity.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(StaffAddAppointmentActivity.this,
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
                firebaseService.addAppointments(patientName.getText().toString(), patientMobileNumber.getText().toString(), doctorName.getText().toString(), appointmentDescription.getText().toString(), startDate.getText().toString(), startTime.getText().toString(), endTime.getText().toString(), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID, String role) {
                        Toast.makeText(StaffAddAppointmentActivity.this, "Appointment Added", Toast.LENGTH_SHORT).show();
                        HomeScreenActivity.start(StaffAddAppointmentActivity.this, "STAFF");
                        finish();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(StaffAddAppointmentActivity.this, "Treatment Added Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
