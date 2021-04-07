package com.example.noteapp21.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp21.App;
import com.example.noteapp21.R;
import com.example.noteapp21.ui.OnItemClickListener;
import com.example.noteapp21.ui.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class HomeFragment extends Fragment implements OnItemClickListener {
    public static final String KEY_NOTE = "keyNote";
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private boolean isUpdating;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        adapter = new NoteAdapter();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        initList();

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNote(null);
                isUpdating =false;
            }
        });
        getParentFragmentManager().setFragmentResultListener(
                "rk_note", getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = (Note) result.getSerializable("note");
//                        String text = result.getString("text");
                        if (isUpdating) adapter.updateItem(note,position);
                        else adapter.addItem(note);

                    }
                });
        initList();
    }

    private void initList() {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

            }

     private void openNote(Note note) {

        Bundle bundle = new Bundle();

        bundle.putSerializable(KEY_NOTE,note);

        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.noteFragment, bundle);

    }

    @Override
    public void onClick(int position) {
                Note  note = adapter.getItem(position);
                Log.e("TAG", "onClick: "+ note.getTitle());
                openNote(note);
                this.position = position;
        isUpdating = true;

    }

    @Override
    public void onLongClick(int position) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.dialog_item, null);
                Button delete = view.findViewById(R.id.delete);
                Button cancel = view.findViewById(R.id.cancel);
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                        .setView(view); // this function shows the xml file типа маленькое окошко
                final AlertDialog dialog = alert.create(); // чтобы вызвать дисмисс
                delete.setOnClickListener(v -> {
                    adapter.remove(position);//метод ремув типа удалить из адаптера
                    App.getDataBase().noteDao().delete(adapter.getItem(position));
                    dialog.dismiss();    //убирает окно после удаления
                });

                cancel.setOnClickListener(v -> dialog.dismiss());
                dialog.show();


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sort:
                adapter.addList( App.getDataBase().noteDao().getsortedList());
                break;
        }
        return true;
    }
}