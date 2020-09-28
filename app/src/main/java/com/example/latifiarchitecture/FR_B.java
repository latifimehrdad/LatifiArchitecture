package com.example.latifiarchitecture;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.latifiarchitecture.databinding.FrBBinding;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;

public class FR_B extends FR_Latifi implements FR_Latifi.fragmentActions {


    private VM_B vm_b;
    private NavController navController;

    @BindView(R.id.buttonSave)
    Button buttonSave;

    //______________________________________________________________________________________________ Splash
    public FR_B() {
    }
    //______________________________________________________________________________________________ Splash


    //______________________________________________________________________________________________ onCreateView
    @Override
    public View onCreateView(
            @NotNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        if (getView() == null) {
            FrBBinding binding = DataBindingUtil.inflate(
                    inflater, R.layout.fr_b, container, false);
            vm_b = new VM_B(getContext());
            binding.setFrb(vm_b);
            setView(binding.getRoot());
            ButterKnife.bind(this, getView());
            setOnClick();

        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onStart
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(
                FR_B.this,
                vm_b.getPublishSubject(),
                vm_b);
        if (getView() != null)
            navController = Navigation.findNavController(getView());
    }


    private void setOnClick() {

        buttonSave.setOnClickListener(view -> {

        });
    }

    @Override
    public void getActionFromObservable(Byte action) {

    }

    @Override
    public void actionWhenFailureRequest() {

    }

    @Override
    public void OnBackPress() {
        Log.i("meri", "back B");
        removeCallBackAndBack();
    }
    //______________________________________________________________________________________________ onStart


}
