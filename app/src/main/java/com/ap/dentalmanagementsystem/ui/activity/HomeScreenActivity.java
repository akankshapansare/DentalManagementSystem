package com.ap.dentalmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.data.Appointment;
import com.ap.dentalmanagementsystem.network.API.IAppointmentListCallBack;
import com.ap.dentalmanagementsystem.network.FirebaseService;
import com.ap.dentalmanagementsystem.ui.adapter.HomeScreenAdapter;
import com.ap.dentalmanagementsystem.ui.fragment.AdminFragment;
import com.ap.dentalmanagementsystem.ui.fragment.DoctorFragment;
import com.ap.dentalmanagementsystem.ui.fragment.StaffFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    private FirebaseService firebaseService = FirebaseService.getInstance();
    private String role;

    public static void start(Context context, String role) {
        Intent intent = new Intent(context, HomeScreenActivity.class);
        intent.putExtra("INTENT_EXTRA_ROLE", role);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        if (getIntent() != null) {
            role = getIntent().getStringExtra("INTENT_EXTRA_ROLE");
        } else {
            role = savedInstanceState.getString("INTENT_EXTRA_ROLE");
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_view_home_screen);
        final List<Appointment> appointmentList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(this, appointmentList);
        recyclerView.setAdapter(homeScreenAdapter);

        ActionBar ab = this.getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);  //Back Arrow
        ab.setHomeButtonEnabled(true);      //Three Horizontal lines
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        Fragment leftDrawerFragment = getSupportFragmentManager().findFragmentById(R.id.left_fragment_container);
        switch (role) {
            case "DOCTOR":
                if (leftDrawerFragment == null || !(leftDrawerFragment instanceof DoctorFragment)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.left_fragment_container, DoctorFragment.newInstance(), "DoctorFragment")
                            .commit();
                }
                break;
            case "STAFF":
                if (leftDrawerFragment == null || !(leftDrawerFragment instanceof StaffFragment)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.left_fragment_container, StaffFragment.newInstance(), "StaffFragment")
                            .commit();
                }
                break;
            default:
                if (leftDrawerFragment == null || !(leftDrawerFragment instanceof AdminFragment)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.left_fragment_container, AdminFragment.newInstance(), "AdminFragment")
                            .commit();
                }
                break;
        }

        if (role.equals("DOCTOR")) {

            firebaseService.getAppointmentsDoctor(new IAppointmentListCallBack() {
                @Override
                public void onCallBackComplete(List<Appointment> appointmentList) {
                    if (role.equals("DOCTOR")) {
                        //TODO: Filter doctors appointment.
                    }

                    homeScreenAdapter.setAppointment(appointmentList);
                    Toast.makeText(HomeScreenActivity.this, "Appointments Displayed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCallBackFailed() {
                    Toast.makeText(HomeScreenActivity.this, "Appointments not displayed", Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            firebaseService.getAppointments(new IAppointmentListCallBack() {
                @Override
                public void onCallBackComplete(List<Appointment> appointmentList) {
                    homeScreenAdapter.setAppointment(appointmentList);
                    Toast.makeText(HomeScreenActivity.this, "Appointments Displayed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCallBackFailed() {
                    Toast.makeText(HomeScreenActivity.this, "Appointments not displayed", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("INTENT_EXTRA_ROLE", role);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}