package com.ap.dentalmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.model.AppStateModel;
import com.ap.dentalmanagementsystem.network.FirebaseService;


public class LoginActivity extends AppCompatActivity {

    // The Idling Resource which will be null in production.
    @Nullable private CountingIdlingResource mIdlingResource = (CountingIdlingResource) getIdlingResource();
    private FirebaseService firebaseService = FirebaseService.getInstance();
    private AppStateModel appStateModel = AppStateModel.getInstance();

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * Only called from test, creates and returns a new {@link CountingIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new CountingIdlingResource("Login");
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailEditText = (EditText) findViewById(R.id.text_email);
        final EditText passwordEditText = (EditText) findViewById(R.id.text_password);
        TextView goToRegisterTextView = (TextView) findViewById(R.id.text_go_to_register);
        goToRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.start(LoginActivity.this);
                finish();
            }
        });

        final Spinner LoginTypeSpinner = (Spinner) findViewById(R.id.spinner_type_login);
        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIdlingResource.increment();
                firebaseService.signIn(emailEditText.getText().toString(), passwordEditText.getText().toString(), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID, String role) {
                        if (role.equals("Doctor")) {
                            HomeScreenActivity.start(LoginActivity.this, "DOCTOR");
                            finish();
                        } else if (role.equals("Staff")) {
                            HomeScreenActivity.start(LoginActivity.this, "STAFF");
                            finish();
                        } else {
                            HomeScreenActivity.start(LoginActivity.this, "ADMIN");
                            finish();
                        }
                        mIdlingResource.decrement();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        mIdlingResource.decrement();
                    }
                });
            }
        });
    }
}
