package com.ap.dentalmanagementsystem.ui.activity;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.data.Patient;
import com.ap.dentalmanagementsystem.network.API.IPatientListCallBack;
import com.ap.dentalmanagementsystem.network.FirebaseService;
import com.ap.dentalmanagementsystem.ui.adapter.SearchPatientAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchPatientActivity extends AppCompatActivity implements SearchPatientAdapter.SearchPatientAdapterListener {

    private FirebaseService firebaseService = FirebaseService.getInstance();
    private SearchPatientAdapter searchPatientAdapter;
    private String activityName;

    public static void start(Context context, String activityName) {
        Intent intent = new Intent(context, SearchPatientActivity.class);
        intent.putExtra("INTENT_EXTRA_ACTIVITY_NAME", activityName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);

        if (getIntent() != null) {
            activityName = getIntent().getStringExtra("INTENT_EXTRA_ACTIVITY_NAME");
        } else {
            activityName = savedInstanceState.getString("INTENT_EXTRA_ACTIVITY_NAME");
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_search_patient);
        final List<Patient> patientList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchPatientAdapter = new SearchPatientAdapter(this, patientList, this);
        recyclerView.setAdapter(searchPatientAdapter);

        firebaseService.showAllPatients(new IPatientListCallBack() {
            @Override
            public void onCallBackComplete(List<Patient> patientList) {
                searchPatientAdapter.setPatientList(patientList);
                Toast.makeText(SearchPatientActivity.this, "Patients Displayed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCallBackFailed() {
                Toast.makeText(SearchPatientActivity.this, "Patients not displayed", Toast.LENGTH_SHORT).show();
            }
        });

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data
            firebaseService.getPatients(new IPatientListCallBack() {
                @Override
                public void onCallBackComplete(List<Patient> patientList) {
                    searchPatientAdapter.setPatientList(patientList);
                    Toast.makeText(SearchPatientActivity.this, "Patients Displayed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCallBackFailed() {
                    Toast.makeText(SearchPatientActivity.this, "Patients not displayed", Toast.LENGTH_SHORT).show();

                }
            }, query);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_patient, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("INTENT_EXTRA_ACTIVITY_NAME", activityName);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPatientSelected(Patient patient) {
        switch (activityName) {
            case "ADD_TREATMENT":
                DoctorAddTreatmentActivity.start(this, patient);
                break;
            case "ADD_PRESCRIPTION":
                DoctorAddPrescriptionActivity.start(this,patient);
                break;
        }
    }
}
