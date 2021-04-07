package com.example.noteapp21.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.noteapp21.App;
import com.example.noteapp21.R;
import com.example.noteapp21.ui.models.Note;

public class NoteFragment extends Fragment {
    private EditText editText;
    private Button btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceSate) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//здесь мы принимаем бандл из хомФрагмент(name)
        initView();
        if (getArguments().getSerializable(HomeFragment.KEY_NOTE) != null) getData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                close();
            }

            private void save() {
                String text = editText.getText().toString();
                Note note = new Note(text);
                note.setCreatedAt(System.currentTimeMillis());
                App.getDataBase().noteDao().insert(note);
                Bundle bundle = new Bundle();
                bundle.putSerializable("note", note);
                bundle.putString("text", text);
                getParentFragmentManager().setFragmentResult("rk_note", bundle);
            }
        });
    }

    private void initView() {
        btnSave = getView().findViewById(R.id.btnSave);
        editText = getView().findViewById(R.id.editText);
    }

    private void getData() {
        Note note = (Note) getArguments().getSerializable(HomeFragment.KEY_NOTE);
        editText.setText(note.getTitle());
    }



    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }
}