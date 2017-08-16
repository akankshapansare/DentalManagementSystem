package com.ap.dentalmanagementsystem.ui.fragment;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.ui.activity.DoctorAddTreatmentActivity;

public class ToothDialogFragment extends DialogFragment {

    public static final String TAG = ToothDialogFragment.class.getName();

    public static void show(FragmentManager fragmentManager) {
        ToothDialogFragment toothDialogFragment = new ToothDialogFragment();
        toothDialogFragment.show(fragmentManager, TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tooth_dialog, container, false);
        getDialog().setTitle("Tooth Image Dialog");

        final CheckBox tooth1 = (CheckBox) rootView.findViewById(R.id.tooth_1);
        final CheckBox tooth2 = (CheckBox) rootView.findViewById(R.id.tooth_2);
        final CheckBox tooth3 = (CheckBox) rootView.findViewById(R.id.tooth_3);
        final CheckBox tooth4 = (CheckBox) rootView.findViewById(R.id.tooth_4);
        final CheckBox tooth5 = (CheckBox) rootView.findViewById(R.id.tooth_5);
        final CheckBox tooth6 = (CheckBox) rootView.findViewById(R.id.tooth_6);
        final CheckBox tooth7 = (CheckBox) rootView.findViewById(R.id.tooth_7);
        final CheckBox tooth8 = (CheckBox) rootView.findViewById(R.id.tooth_8);
        final CheckBox tooth9 = (CheckBox) rootView.findViewById(R.id.tooth_9);
        final CheckBox tooth10 = (CheckBox) rootView.findViewById(R.id.tooth_10);
        final CheckBox tooth11 = (CheckBox) rootView.findViewById(R.id.tooth_11);
        final CheckBox tooth12 = (CheckBox) rootView.findViewById(R.id.tooth_12);
        final CheckBox tooth13 = (CheckBox) rootView.findViewById(R.id.tooth_13);
        final CheckBox tooth14 = (CheckBox) rootView.findViewById(R.id.tooth_14);
        final CheckBox tooth15 = (CheckBox) rootView.findViewById(R.id.tooth_15);
        final CheckBox tooth16 = (CheckBox) rootView.findViewById(R.id.tooth_16);
        final CheckBox tooth17 = (CheckBox) rootView.findViewById(R.id.tooth_17);
        final CheckBox tooth18 = (CheckBox) rootView.findViewById(R.id.tooth_18);
        final CheckBox tooth19 = (CheckBox) rootView.findViewById(R.id.tooth_19);
        final CheckBox tooth20 = (CheckBox) rootView.findViewById(R.id.tooth_20);
        final CheckBox tooth21 = (CheckBox) rootView.findViewById(R.id.tooth_21);
        final CheckBox tooth22 = (CheckBox) rootView.findViewById(R.id.tooth_22);
        final CheckBox tooth23 = (CheckBox) rootView.findViewById(R.id.tooth_23);
        final CheckBox tooth24 = (CheckBox) rootView.findViewById(R.id.tooth_24);
        final CheckBox tooth25 = (CheckBox) rootView.findViewById(R.id.tooth_25);
        final CheckBox tooth26 = (CheckBox) rootView.findViewById(R.id.tooth_26);
        final CheckBox tooth27 = (CheckBox) rootView.findViewById(R.id.tooth_27);
        final CheckBox tooth28 = (CheckBox) rootView.findViewById(R.id.tooth_28);
        final CheckBox tooth29 = (CheckBox) rootView.findViewById(R.id.tooth_29);
        final CheckBox tooth30 = (CheckBox) rootView.findViewById(R.id.tooth_30);
        final CheckBox tooth31 = (CheckBox) rootView.findViewById(R.id.tooth_31);
        final CheckBox tooth32 = (CheckBox) rootView.findViewById(R.id.tooth_32);

        Button saveButton = (Button) rootView.findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toothSaved = "";

                if (tooth1.isChecked()) {
                    toothSaved = toothSaved + "1, ";
                }
                if (tooth2.isChecked()) {
                    toothSaved = toothSaved + "2, ";
                }
                if (tooth3.isChecked()) {
                    toothSaved = toothSaved + "3, ";
                }
                if (tooth4.isChecked()) {
                    toothSaved = toothSaved + "4, ";
                }
                if (tooth5.isChecked()) {
                    toothSaved = toothSaved + "5, ";
                }
                if (tooth6.isChecked()) {
                    toothSaved = toothSaved + "6, ";
                }
                if (tooth7.isChecked()) {
                    toothSaved = toothSaved + "7, ";
                }
                if (tooth8.isChecked()) {
                    toothSaved = toothSaved + "8, ";
                }
                if (tooth9.isChecked()) {
                    toothSaved = toothSaved + "9, ";
                }
                if (tooth10.isChecked()) {
                    toothSaved = toothSaved + "10, ";
                }
                if (tooth11.isChecked()) {
                    toothSaved = toothSaved + "11, ";
                }
                if (tooth12.isChecked()) {
                    toothSaved = toothSaved + "12, ";
                }
                if (tooth13.isChecked()) {
                    toothSaved = toothSaved + "13, ";
                }
                if (tooth14.isChecked()) {
                    toothSaved = toothSaved + "14, ";
                }
                if (tooth15.isChecked()) {
                    toothSaved = toothSaved + "15, ";
                }
                if (tooth16.isChecked()) {
                    toothSaved = toothSaved + "16, ";
                }
                if (tooth17.isChecked()) {
                    toothSaved = toothSaved + "17, ";
                }
                if (tooth18.isChecked()) {
                    toothSaved = toothSaved + "18, ";
                }
                if (tooth19.isChecked()) {
                    toothSaved = toothSaved + "19, ";
                }
                if (tooth20.isChecked()) {
                    toothSaved = toothSaved + "20, ";
                }
                if (tooth21.isChecked()) {
                    toothSaved = toothSaved + "21, ";
                }
                if (tooth22.isChecked()) {
                    toothSaved = toothSaved + "22, ";
                }
                if (tooth23.isChecked()) {
                    toothSaved = toothSaved + "23, ";
                }
                if (tooth24.isChecked()) {
                    toothSaved = toothSaved + "24, ";
                }
                if (tooth25.isChecked()) {
                    toothSaved = toothSaved + "25, ";
                }
                if (tooth26.isChecked()) {
                    toothSaved = toothSaved + "26, ";
                }
                if (tooth27.isChecked()) {
                    toothSaved = toothSaved + "27, ";
                }
                if (tooth28.isChecked()) {
                    toothSaved = toothSaved + "28, ";
                }
                if (tooth29.isChecked()) {
                    toothSaved = toothSaved + "29,";
                }
                if (tooth30.isChecked()) {
                    toothSaved = toothSaved + "30, ";
                }
                if (tooth31.isChecked()) {
                    toothSaved = toothSaved + "31, ";
                }
                if (tooth32.isChecked()) {
                    toothSaved = toothSaved + "32, ";
                }

                if (!TextUtils.isEmpty(toothSaved)) {
                    toothSaved = toothSaved.substring(0, toothSaved.length() - 2);
                }
                ((DoctorAddTreatmentActivity) getActivity()).onToothSaved(toothSaved);
                dismiss();
            }
        });


        return rootView;
    }

}
