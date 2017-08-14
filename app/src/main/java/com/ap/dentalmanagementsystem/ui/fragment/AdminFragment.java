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
import com.ap.dentalmanagementsystem.ui.activity.AdminAddDoctorActivity;
import com.ap.dentalmanagementsystem.ui.activity.AdminAddStaffActivity;
import com.ap.dentalmanagementsystem.ui.activity.ManageProfileActivity;
import com.ap.dentalmanagementsystem.ui.adapter.AdminFragmentAdapter;

import java.util.ArrayList;

public class AdminFragment extends Fragment implements AdminFragmentAdapter.AdminFragmentAdapterListener {

    public static AdminFragment newInstance() {
        return new AdminFragment();
    }

    public enum AdminDrawerItem {
        ADD_DOCTOR("Add Doctor"),
        ADD_STAFF("Add Staff"),
        ADD_PATIENT("Add Patient"),
        MANAGE_PROFILE("Manage Profile");

        private String title;

        private AdminDrawerItem(String title) {
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
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        RecyclerView recylerView = (RecyclerView) view.findViewById(R.id.recyler_view_admin_fragment);
        ArrayList<AdminFragment.AdminDrawerItem> optionList = new ArrayList<>();
        optionList.add(AdminDrawerItem.ADD_DOCTOR);
        optionList.add(AdminDrawerItem.ADD_STAFF);
        optionList.add(AdminDrawerItem.ADD_PATIENT);
        optionList.add(AdminDrawerItem.MANAGE_PROFILE);
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerView.setAdapter(new AdminFragmentAdapter(getActivity(), optionList, this));
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
    public void onOptionSelected(AdminFragment.AdminDrawerItem option) {
        switch (option) {
            case ADD_DOCTOR:
                AdminAddDoctorActivity.start(getActivity());
                break;
            case ADD_STAFF:
                AdminAddStaffActivity.start(getActivity());
                break;
            case ADD_PATIENT:
                AddPatientActivity.start(getActivity());
                break;
            case MANAGE_PROFILE:
                ManageProfileActivity.start(getActivity());
                break;
        }
    }
}
