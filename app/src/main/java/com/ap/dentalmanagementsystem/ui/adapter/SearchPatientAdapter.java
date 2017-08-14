package com.ap.dentalmanagementsystem.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.data.Patient;

import java.util.List;


public class SearchPatientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final SearchPatientAdapterListener listener;
    private Context context;
    private List<Patient> patientList;
    private LayoutInflater layoutInflater;

    public interface SearchPatientAdapterListener {
        void onPatientSelected(Patient patient);
    }

    public SearchPatientAdapter(Context context, List<Patient> patientList, SearchPatientAdapterListener listener) {
        this.context = context;
        this.patientList = patientList;
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_layout_search_patient, parent, false);
        return new SearchPatientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textView.setText(patientList.get(position).getFirstName() + " " + patientList.get(position).getLastName());
        ((ViewHolder) holder).initialsText.setText(String.valueOf(patientList.get(position).getFirstName().charAt(0)));
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView initialsText;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            initialsText = (TextView) itemView.findViewById(R.id.initials_text);
            textView = (TextView) itemView.findViewById(R.id.row_layout_search_patient);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPatientSelected(patientList.get(getAdapterPosition()));
                }
            });
        }
    }
}
