package com.example.latifiarchitecture;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.latifiarchitecture.databinding.FrSplashBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ir.mlcode.latifiarchitecturelibrary.daggers.imageloader.ImageLoaderComponent;
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;
import ir.mlcode.latifiarchitecturelibrary.utility.ApplicationUtility;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;

public class FR_Splash extends FR_Latifi implements FR_Latifi.getActionFromObservable {


    private VM_Splash vm_splash;
    private NavController navController;

    //______________________________________________________________________________________________ FR_Splash
    public FR_Splash() {
    }
    //______________________________________________________________________________________________ FR_Splash


    //______________________________________________________________________________________________ onCreateView
    @Override
    public View onCreateView(
            @NotNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        if (getView() == null) {
            FrSplashBinding binding = DataBindingUtil.inflate(
                    inflater, R.layout.fr_splash, container, false);
            vm_splash = new VM_Splash(getContext());
            binding.setSplash(vm_splash);
            setView(binding.getRoot());
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onStart
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(
                FR_Splash.this,
                vm_splash.getPublishSubject(),
                vm_splash);
        if (getView() != null)
            navController = Navigation.findNavController(getView());

//        vm_splash.callHiService();
    }
    //______________________________________________________________________________________________ onStart




    @Override
    public void getActionFromObservable(Byte action) {

        if (action.equals((byte)10)) {

            Bundle bundle = new Bundle();
            bundle.putString(getContext().getResources().getString(ir.mlcode.latifiarchitecturelibrary.R.string.ML_ApplicationId), "com.example.latifiarchitecture");
            bundle.putString(getContext().getResources().getString(ir.mlcode.latifiarchitecturelibrary.R.string.ML_AppName), "LatifiArchitecture");
            bundle.putString(getContext().getResources().getString(ir.mlcode.latifiarchitecturelibrary.R.string.ML_UpdateFileName),
                    vm_splash.getMd_hi().getFileName());
            bundle.putString(getContext().getResources().getString(ir.mlcode.latifiarchitecturelibrary.R.string.ML_UpdateUrl),
                    vm_splash.getMd_hi().getApplicationUrl());
            navController.navigate(R.id.action_FR_Splash_to_appUpdate, bundle);
            return;
        }

        if (action.equals(StaticValues.ML_CheckPermission)) {
            vm_splash.callHiService();
        }

    }

    @Override
    public void actionWhenFailureRequest() {

    }
}
