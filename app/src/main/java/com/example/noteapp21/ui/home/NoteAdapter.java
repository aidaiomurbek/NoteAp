

package com.example.noteapp21.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp21.App;
import com.example.noteapp21.R;
import com.example.noteapp21.ui.OnItemClickListener;
import com.example.noteapp21.ui.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<Note> list;
    private OnItemClickListener onItemClickListener;

    public NoteAdapter() {
        list = new ArrayList<>();
       list.addAll(App.getDataBase().noteDao().getAll());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note, parent, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Note note) {
        list.add(0, note);
        notifyItemInserted(0);
    }

    public Note getItem(int position) {
        return list.get(position);
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void updateItem(Note note, int position) {
        list.set(position,note);
        notifyDataSetChanged();
    }

    public void addList(List<Note> getsortedList) {
        list.clear();
        list.addAll(getsortedList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            textTitle = itemView.findViewById(R.id.nameItem);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(getAdapterPosition());

                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
        }


        public void bind(Note note) {
            textTitle.setText(note.getTitle());
        }
    }
}

