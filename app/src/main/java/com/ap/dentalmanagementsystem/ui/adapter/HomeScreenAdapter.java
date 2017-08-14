package com.ap.dentalmanagementsystem.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.data.Appointment;

import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Appointment> appointmentList;
    private LayoutInflater layoutInflater;

    public HomeScreenAdapter(Context context, List<Appointment> appointmentList) {
        this.context = context;
        this.appointmentList = appointmentList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_layout_home_screen, parent, false);
        return new HomeScreenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HomeScreenAdapter.ViewHolder) holder).textView.setText(appointmentList.get(position).getStartDate().toString());
        ((HomeScreenAdapter.ViewHolder) holder).textView1.setText(appointmentList.get(position).getPataientName());
        ((HomeScreenAdapter.ViewHolder) holder).textView2.setText(appointmentList.get(position).getStartTime().toString() + " - " + appointmentList.get(position).getEndTime().toString());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public void setAppointment(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_start_date);
            textView1 = (TextView) itemView.findViewById(R.id.text_patient_name);
            textView2 = (TextView) itemView.findViewById(R.id.text_appointment_time_period);

        }
    }
}
