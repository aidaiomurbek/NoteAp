package com.example.noteapp21.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp21.R;
import com.example.noteapp21.ui.models.Task;

import java.util.ArrayList;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashViewHolder> {

    private List<Task> list= new ArrayList<>();

    public void addItems(List<Task>list2){
        list = list2;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dashboard,parent,false);
        return new DashViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DashViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public DashViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_dash);
        }

        public void onBind(Task s) {
            textView.setText(s.getName());
        }
    }
}
