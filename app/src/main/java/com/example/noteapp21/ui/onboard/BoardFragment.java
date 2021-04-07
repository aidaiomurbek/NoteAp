package com.example.noteapp21.ui.onboard;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.noteapp21.R;
import com.example.noteapp21.prefs.Prefs;
import com.example.noteapp21.ui.OnItemClickListener;

public class BoardFragment extends Fragment {
    Button button;
    int pos;
    TextView skip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.btnStart);
        skip = view.findViewById(R.id.skip);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);
        BoardAdapter adapter = new BoardAdapter();
        viewPager2.setAdapter(adapter);

        button.setOnClickListener(v -> {
            if (pos == 2) {
                Prefs prefs = new Prefs(getContext());
                prefs.saveIsShown();
                close();
            } else {
                viewPager2.setCurrentItem(pos + 1);
            }
        });
        skip.setOnClickListener(v -> {
            Prefs prefs = new Prefs(getContext());
            prefs.saveIsShown();
            close();
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                pos = position;
                if (position == 2) {
                    button.setText("Finish");
                } else {
                    button.setText("Next");
                }
                Log.e("TAG", "onPageSelected: " + position);
            }
        });
        requireActivity()
                .getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });

    }

    private void close() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveIsShown();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }
}