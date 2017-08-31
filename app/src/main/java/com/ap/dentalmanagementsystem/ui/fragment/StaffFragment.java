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
import com.ap.dentalmanagementsystem.ui.activity.LoginActivity;
import com.ap.dentalmanagementsystem.ui.activity.ManageProfileActivity;
import com.ap.dentalmanagementsystem.ui.activity.StaffAddAppointmentActivity;
import com.ap.dentalmanagementsystem.ui.adapter.StaffFragmentAdapter;

import java.util.ArrayList;


public class StaffFragment extends Fragment implements StaffFragmentAdapter.StaffFragmentAdapterListener {

    public static StaffFragment newInstance() {
        return new StaffFragment();
    }

    public enum StaffDrawerItem {
        ADD_PATIENT("Add Patient"),
        ADD_APPOINTMENT("Add Appointment"),
        MANAGE_PROFILE("Manage Profile"),
        LOG_OUT("Logout");

        private String title;

        private StaffDrawerItem(String title) {
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
        View view = inflater.inflate(R.layout.fragment_staff, container, false);
        RecyclerView recylerView = (RecyclerView) view.findViewById(R.id.recyler_view_staff_fragment);
        ArrayList<StaffFragment.StaffDrawerItem> optionList = new ArrayList<>();
        optionList.add(StaffFragment.StaffDrawerItem.ADD_PATIENT);
        optionList.add(StaffFragment.StaffDrawerItem.ADD_APPOINTMENT);
        optionList.add(StaffFragment.StaffDrawerItem.MANAGE_PROFILE);
        optionList.add(StaffFragment.StaffDrawerItem.LOG_OUT);
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerView.setAdapter(new StaffFragmentAdapter(getActivity(), optionList, this));
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
    public void onOptionSelected(StaffFragment.StaffDrawerItem option) {
        switch (option) {
            case ADD_PATIENT:
                AddPatientActivity.start(getActivity());
                break;
            case ADD_APPOINTMENT:
                StaffAddAppointmentActivity.start(getActivity());
                break;
            case MANAGE_PROFILE:
                ManageProfileActivity.start(getActivity());
                break;
            case LOG_OUT:
                LoginActivity.start(getActivity());
                getActivity().finish();
                break;
        }
    }

}
