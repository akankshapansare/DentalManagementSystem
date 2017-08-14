package com.ap.dentalmanagementsystem.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.ui.fragment.DoctorFragment;

import java.util.List;

public class DoctorFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DoctorFragment.DoctorDrawerItem> optionList;
    private DoctorFragmentAdapterListener listener;
    private LayoutInflater layoutInflater;

    public interface DoctorFragmentAdapterListener {
        public void onOptionSelected(DoctorFragment.DoctorDrawerItem option);
    }

    public DoctorFragmentAdapter(Context context, List<DoctorFragment.DoctorDrawerItem> optionList, DoctorFragmentAdapterListener listener) {
        this.context = context;
        this.optionList = optionList;
        this.listener = listener;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_layout_doctor_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textView.setText(optionList.get(position).getTitle());
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_layout_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onOptionSelected(optionList.get(getAdapterPosition()));
                }
            });
        }
    }
}
