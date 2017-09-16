package com.ap.dentalmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.DMSApplication;
import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.network.FirebaseService;

import javax.inject.Inject;


public class RegisterActivity extends AppCompatActivity {

    @Inject
    FirebaseService firebaseService;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DMSApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_register);
        final EditText firstNameEditText = (EditText) findViewById(R.id.text_first_name);
        final EditText lastNameEditText = (EditText) findViewById(R.id.text_last_name);
        final EditText emailEditText = (EditText) findViewById(R.id.text_email);
        final EditText passwordEditText = (EditText) findViewById(R.id.text_password);
        final Spinner roleSpinner = (Spinner) findViewById(R.id.spinner_type_login);

        TextView goToLoginTextView = (TextView) findViewById(R.id.text_go_to_login);
        goToLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.start(RegisterActivity.this);
                finish();
            }
        });

        Button button = (Button) findViewById(R.id.button_sign_up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.signUp(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString(), roleSpinner.getSelectedItem().toString(), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID, String role) {
                        Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                        LoginActivity.start(RegisterActivity.this);
                        finish();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(RegisterActivity.this, "User Create Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginActivity.start(this);
    }
}
