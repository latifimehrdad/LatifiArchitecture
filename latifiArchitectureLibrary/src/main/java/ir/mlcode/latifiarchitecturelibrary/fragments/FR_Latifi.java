package ir.mlcode.latifiarchitecturelibrary.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import ir.mlcode.latifiarchitecturelibrary.R;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Toast;
import ir.mlcode.latifiarchitecturelibrary.dialogs.DialogMessage;
import ir.mlcode.latifiarchitecturelibrary.utility.ApplicationUtility;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;

public class FR_Latifi extends Fragment {

    private DisposableObserver<Byte> disposableObserver;
    private Activity context;
    private View view;
    private fragmentActions fragmentActions;
    private VM_Latifi vm_latifi;
    private int svg_error;
    private int svg_ok;
    private OnBackPressedCallback pressedCallback;
    private NavController navController;
    public static ConstraintLayout constraintLayout;


    //______________________________________________________________________________________________ getActionFromObservable
    public interface fragmentActions {

        void getActionFromObservable(Byte action);

        void actionWhenFailureRequest();

        void OnBackPress();

    }
    //______________________________________________________________________________________________ getActionFromObservable


    //______________________________________________________________________________________________ FragmentPrimary
    public FR_Latifi() {
    }
    //______________________________________________________________________________________________ FragmentPrimary


    //______________________________________________________________________________________________ onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        pressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                fragmentActions.OnBackPress();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, pressedCallback);
    }
    //______________________________________________________________________________________________ onCreate


    //______________________________________________________________________________________________ onDestroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableObserver != null)
            disposableObserver.dispose();
        disposableObserver = null;
    }
    //______________________________________________________________________________________________ onDestroy


    //______________________________________________________________________________________________ onStop
    @Override
    public void onStop() {
        super.onStop();
        if (disposableObserver != null)
            disposableObserver.dispose();
        disposableObserver = null;
    }
    //______________________________________________________________________________________________ onStop


    //______________________________________________________________________________________________ getContext
    @Override
    public Activity getContext() {
        return context;
    }
    //______________________________________________________________________________________________ getContext


    //______________________________________________________________________________________________ getView
    @Override
    public View getView() {
        return view;
    }
    //______________________________________________________________________________________________ getView


    //______________________________________________________________________________________________ setView
    public void setView(View view) {
        this.view = view;
/*        this.view.setFocusableInTouchMode(true);
        this.view.requestFocus();
        this.view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP)
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.i("meri", "back");
                    return true;
                }
            return false;
        });*/
    }
    //______________________________________________________________________________________________ setView


    //______________________________________________________________________________________________ setPublishSubjectFromObservable
    public void setPublishSubjectFromObservable(
            fragmentActions fragmentActions,
            VM_Latifi vm_latifi,
            int svg_error,
            int svg_ok) {
        this.fragmentActions = fragmentActions;
        if (disposableObserver != null)
            disposableObserver.dispose();
        disposableObserver = null;
        this.vm_latifi = vm_latifi;
        this.svg_error = svg_error;
        this.svg_ok = svg_ok;
        navController = Navigation.findNavController(getView());
        setObserverToObservable(vm_latifi.getPublishSubject());
    }
    //______________________________________________________________________________________________ setPublishSubjectFromObservable


    //______________________________________________________________________________________________ setPublishSubjectFromObservable
    public void setPublishSubjectFromObservable(
            fragmentActions fragmentActions,
            VM_Latifi vm_latifi) {
        this.fragmentActions = fragmentActions;
        if (disposableObserver != null)
            disposableObserver.dispose();
        disposableObserver = null;
        this.vm_latifi = vm_latifi;
        this.svg_error = R.drawable.svg_warning;
        this.svg_ok = R.drawable.svg_checked;
        navController = Navigation.findNavController(getView());
        setObserverToObservable(vm_latifi.getPublishSubject());
    }
    //______________________________________________________________________________________________ setPublishSubjectFromObservable


    //______________________________________________________________________________________________ setObserverToObservable
    public void setObserverToObservable(PublishSubject<Byte> publishSubject) {

        disposableObserver = new DisposableObserver<Byte>() {
            @Override
            public void onNext(Byte aByte) {
                actionHandler(aByte);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        publishSubject
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }
    //______________________________________________________________________________________________ setObserverToObservable


    //______________________________________________________________________________________________ actionHandler
    private void actionHandler(Byte action) {
        if (getContext() != null) {
            getContext().runOnUiThread(() -> {


                if (action.equals(StaticValues.ML_DialogClose)) {
                    fragmentActions.getActionFromObservable(action);
                    return;
                }

                if (vm_latifi.getResponseMessage() == null) {
                    fragmentActions.getActionFromObservable(action);
                    return;
                }

                if (vm_latifi.getResponseMessage().equalsIgnoreCase("")) {
                    fragmentActions.getActionFromObservable(action);
                    return;
                }

                if ((action.equals(StaticValues.ML_RequestCancel))
                        || (action.equals(StaticValues.ML_ResponseError))
                        || (action.equals(StaticValues.ML_ResponseFailure))
                        || (action.equals(StaticValues.ML_InternetAccessFailed))) {
                    showToast(vm_latifi.getResponseMessage(),
                            getResources().getColor(R.color.mlHarmony),
                            getResources().getDrawable(svg_error),
                            getResources().getColor(R.color.mlCollectRight1));
                    fragmentActions.actionWhenFailureRequest();
                    return;
                } else {
                    showToast(vm_latifi.getResponseMessage()
                            , getResources().getColor(R.color.mlWhite),
                            getResources().getDrawable(svg_ok),
                            getResources().getColor(R.color.colorPrimaryDark));
                    fragmentActions.getActionFromObservable(action);
                }
            });
        }
    }
    //______________________________________________________________________________________________ actionHandler


    //______________________________________________________________________________________________ showToast
    public void showToast(String message, int color, Drawable icon, int tintColor) {

        ML_Toast.showToast(getContext(), constraintLayout, message, icon, tintColor);
    }
    //______________________________________________________________________________________________ showToast




    //______________________________________________________________________________________________ showMessageDialog
    public void showMessageDialog(String message, int color, Drawable icon, int tintColor) {

        if (getFragmentManager() != null) {
            DialogMessage dialogMessage = new DialogMessage(getContext(), message, color, icon, tintColor, vm_latifi.getPublishSubject());
            dialogMessage.setCancelable(false);
            dialogMessage.show(getFragmentManager(), NotificationCompat.CATEGORY_PROGRESS);
        }
    }
    //______________________________________________________________________________________________ showMessageDialog



    //______________________________________________________________________________________________ hideKeyboard
    public void hideKeyboard() {
        if (getContext() != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = getContext().getCurrentFocus();
            if (view == null) {
                view = new View(getContext());
            }
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    //______________________________________________________________________________________________ hideKeyboard


    //______________________________________________________________________________________________ getApplicationUtility
    public ApplicationUtility getApplicationUtility() {

        return vm_latifi.getUtility();
    }
    //______________________________________________________________________________________________ getApplicationUtility


    //______________________________________________________________________________________________ textChangeForChangeBack
    public TextWatcher textChangeForChangeBack(EditText editText, int dw_edit_back) {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setBackgroundResource(dw_edit_back);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

    }
    //______________________________________________________________________________________________ textChangeForChangeBack


    //______________________________________________________________________________________________ turnOnLocation
    public void turnOnLocation() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }
    //______________________________________________________________________________________________ turnOnLocation


    //______________________________________________________________________________________________ setPermission
    public void setPermission(List<String> permissions) {

        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String permission : permissions) {
            int check = ContextCompat.checkSelfPermission(getContext(), permission);
            if (check != PackageManager.PERMISSION_GRANTED)
                listPermissionsNeeded.add(permission);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            int REQUEST_PERMISSIONS_CODE_WRITE_STORAGE = 7126;
            requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_PERMISSIONS_CODE_WRITE_STORAGE);
        } else
            vm_latifi.sendActionToObservable(StaticValues.ML_CheckPermission);

    }
    //______________________________________________________________________________________________ setPermission


    //______________________________________________________________________________________________ onRequestPermissionsResult
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean accessPermission = true;
        for (String permission : permissions) {
            int check = ContextCompat.checkSelfPermission(getContext(), permission);
            if (check != PackageManager.PERMISSION_GRANTED) {
                accessPermission = false;
                break;
            }
        }

        if (accessPermission)
            vm_latifi.sendActionToObservable(StaticValues.ML_CheckPermission);
        else
            getContext().onBackPressed();

    }
    //______________________________________________________________________________________________ onRequestPermissionsResult


    //______________________________________________________________________________________________ setVariableToNavigation
    public void setVariableToNavigation(String saveKey, String saveValue) {
        if (getView() != null)
            Navigation.findNavController(getView()).getPreviousBackStackEntry().getSavedStateHandle().set(saveKey, saveValue);
    }
    //______________________________________________________________________________________________ setVariableToNavigation


    //______________________________________________________________________________________________ getVariableFromNavigation
    public String getVariableFromNavigation(String saveKey) {
        if (getView() != null)
            return Navigation.findNavController(getView()).getCurrentBackStackEntry().getSavedStateHandle().get(saveKey);
        else
            return null;
    }
    //______________________________________________________________________________________________ getVariableFromNavigation


    //______________________________________________________________________________________________ removeCallBackAndBack
    public void removeCallBackAndBack() {
        pressedCallback.remove();
        assert getContext() != null;
        getContext().onBackPressed();
    }
    //______________________________________________________________________________________________ removeCallBackAndBack



    //______________________________________________________________________________________________ getNavController
    public NavController getNavController() {
        return navController;
    }
    //______________________________________________________________________________________________ getNavController


}