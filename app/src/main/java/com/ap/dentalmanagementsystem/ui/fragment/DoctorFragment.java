package com.ap.dentalmanagementsystem.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.ui.activity.AddPatientActivity;
import com.ap.dentalmanagementsystem.ui.activity.ManageProfileActivity;
import com.ap.dentalmanagementsystem.ui.activity.SearchPatientActivity;
import com.ap.dentalmanagementsystem.ui.adapter.DoctorFragmentAdapter;

import java.util.ArrayList;

public class DoctorFragment extends Fragment implements DoctorFragmentAdapter.DoctorFragmentAdapterListener {

    public static DoctorFragment newInstance() {
        return new DoctorFragment();
    }

    public enum DoctorDrawerItem {
        ADD_PATIENT("Add Patient"),
        ADD_TREATMENT("Add Treatment"),
        ADD_PRESCRIPTION("Add Prescription"),
        MANAGE_PROFILE("Manage Profile"),
        LOG_OUT("Logout");

        private String title;

        private DoctorDrawerItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);
        RecyclerView recylerView = (RecyclerView) view.findViewById(R.id.recyler_view_doctor_fragment);
        ArrayList<DoctorDrawerItem> optionList = new ArrayList<>();
        optionList.add(DoctorDrawerItem.ADD_PATIENT);
        optionList.add(DoctorDrawerItem.ADD_TREATMENT);
        optionList.add(DoctorDrawerItem.ADD_PRESCRIPTION);
        optionList.add(DoctorDrawerItem.MANAGE_PROFILE);
        optionList.add(DoctorDrawerItem.LOG_OUT);
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerView.setAdapter(new DoctorFragmentAdapter(getActivity(), optionList, this));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onOptionSelected(DoctorDrawerItem option) {
        switch (option) {
            case ADD_PATIENT:
                AddPatientActivity.start(getActivity());
                break;
            case ADD_TREATMENT:
                SearchPatientActivity.start(getActivity(), "ADD_TREATMENT");
                break;
            case ADD_PRESCRIPTION:
                SearchPatientActivity.start(getActivity(), "ADD_PRESCRIPTION");
                break;
            case MANAGE_PROFILE:
                ManageProfileActivity.start(getActivity());
                break;
            case LOG_OUT:
                break;
        }
    }
}
