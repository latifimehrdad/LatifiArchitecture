package com.example.latifiarchitecture;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.latifiarchitecture.databinding.FrABinding;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.fragments.FR_Latifi;

public class FR_A extends FR_Latifi implements FR_Latifi.fragmentActions {


    private VM_A vm_a;
    private NavController navController;

    @BindView(R.id.buttonNext)
    Button buttonNext;

    @BindView(R.id.my_image_view)
    SimpleDraweeView simpleDraweeView;

    //______________________________________________________________________________________________ Splash
    public FR_A() {
    }
    //______________________________________________________________________________________________ Splash


    //______________________________________________________________________________________________ onCreateView
    @Override
    public View onCreateView(
            @NotNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        if (getView() == null) {
            FrABinding binding = DataBindingUtil.inflate(
                    inflater, R.layout.fr_a, container, false);
            vm_a = new VM_A(getContext());
            binding.setFra(vm_a);
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
                FR_A.this,
                vm_a.getPublishSubject(),
                vm_a);
        if (getView() != null)
            navController = Navigation.findNavController(getView());





        simpleDraweeView.setImageURI("https://geographical.co.uk/media/k2/items/cache/8e4e30c8fc08507de1b0b5afc7d32a85_XL.jpg");



//        my_image_view.setImageURI("https://images.wheels.ca/wp-content/uploads/2017/06/Latifi-family-main-808x455.jpg");








        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
        progressBarDrawable.setColor(getResources().getColor(R.color.colorAccent));
        progressBarDrawable.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        progressBarDrawable.setRadius(
                getResources().getDimensionPixelSize(R.dimen._5sdp));

        simpleDraweeView.getHierarchy().setProgressBarImage(progressBarDrawable);


    }


    private void setOnClick() {

        buttonNext.setOnClickListener(view -> navController.navigate(R.id.action_FR_A_to_FR_B));
    }


    @Override
    public void getActionFromObservable(Byte action) {

    }

    @Override
    public void actionWhenFailureRequest() {

    }

    @Override
    public void OnBackPress() {
        Log.i("meri", "back A");
    }
    //______________________________________________________________________________________________ onStart


}
