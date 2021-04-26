package com.example.noteapp21.ui.onboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.noteapp21.R;
import com.example.noteapp21.ui.OnItemClickListener;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] titles = new String[]{"Fast", "Free", "Powerful"};
    Integer[] gif = new Integer[]{R.raw.pic1,R.raw.pic2,R.raw.pic3};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private LottieAnimationView lottieAnimationView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottieAnimationView = itemView.findViewById(R.id.imageView);
            textTitle = itemView.findViewById(R.id.textTitle);

        }

        public void bind(int position) {
            lottieAnimationView.setAnimation(gif[position]);
            textTitle.setText(titles[position]);
        }
    }
}
