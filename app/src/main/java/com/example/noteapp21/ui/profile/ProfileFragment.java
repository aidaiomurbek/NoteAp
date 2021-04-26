package com.example.noteapp21.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.noteapp21.R;


public class ProfileFragment extends Fragment {

    private NavController navController;
    ImageView  imageView;
    Button button;
    EditText editText;
    EditText editText2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
        // Inflate the layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.imageSelect);
        imageView.setOnClickListener(v -> {
            getImage();
        });
        editText = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);
        button = view.findViewById(R.id.btnSave);
        button.setOnClickListener(v -> {
            YoYo.with(Techniques.DropOut).duration(1000).repeat(1).playOn(editText);
        });
        click();
    }

    private void getImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT).setType("image/*");
        someActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        Uri uri = data.getData();
                        Glide.with(getContext())
                                .load(uri)
                                .circleCrop()
                                .into(imageView);
                    }
                }
            });
    private  void  click(){
        View CustomToast = requireActivity().getLayoutInflater().inflate(R.layout.toast,null);
        Toast toast = new Toast(requireContext());
        toast.setDuration(toast.LENGTH_SHORT);
        toast.setView(CustomToast);
        toast.getGravity();
        toast.show();

    }
}