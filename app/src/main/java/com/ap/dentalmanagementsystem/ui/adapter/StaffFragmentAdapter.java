package com.ap.dentalmanagementsystem.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ap.dentalmanagementsystem.R;
import com.ap.dentalmanagementsystem.ui.fragment.StaffFragment;

import java.util.List;

public class StaffFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<StaffFragment.StaffDrawerItem> optionList;
    private StaffFragmentAdapter.StaffFragmentAdapterListener listener;
    private LayoutInflater layoutInflater;

    public interface StaffFragmentAdapterListener {
        public void onOptionSelected(StaffFragment.StaffDrawerItem option);
    }

    public StaffFragmentAdapter(Context context, List<StaffFragment.StaffDrawerItem> optionList, StaffFragmentAdapter.StaffFragmentAdapterListener listener) {
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
        View view = layoutInflater.inflate(R.layout.row_layout_staff_fragment, parent, false);
        return new StaffFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((StaffFragmentAdapter.ViewHolder) holder).textView.setText(optionList.get(position).getTitle());
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
