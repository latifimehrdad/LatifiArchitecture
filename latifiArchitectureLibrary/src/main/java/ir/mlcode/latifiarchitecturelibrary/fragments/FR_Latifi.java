package ir.mlcode.latifiarchitecturelibrary.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import ir.mlcode.latifiarchitecturelibrary.R;
import ir.mlcode.latifiarchitecturelibrary.dialogs.DialogMessage;
import ir.mlcode.latifiarchitecturelibrary.utility.ApplicationUtility;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;

public class FR_Latifi extends Fragment {

    private DisposableObserver<Byte> disposableObserver;
    private Activity context;
    private View view;
    private getActionFromObservable getActionFromObservable;
    private VM_Latifi vm_latifi;
    private int svg_error;
    private int svg_ok;


    //______________________________________________________________________________________________ getActionFromObservable
    public interface getActionFromObservable {
        void getActionFromObservable(Byte action);

        void actionWhenFailureRequest();
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
        ButterKnife.bind(this, view);
    }
    //______________________________________________________________________________________________ setView


    //______________________________________________________________________________________________ setPublishSubjectFromObservable
    public void setPublishSubjectFromObservable(
            getActionFromObservable getActionFromObservable,
            PublishSubject<Byte> publishSubject,
            VM_Latifi vm_latifi,
            int svg_error,
            int svg_ok) {
        this.getActionFromObservable = getActionFromObservable;
        if (disposableObserver != null)
            disposableObserver.dispose();
        disposableObserver = null;
        this.vm_latifi = vm_latifi;
        this.svg_error = svg_error;
        this.svg_ok = svg_ok;
        setObserverToObservable(publishSubject);
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
                getActionFromObservable.getActionFromObservable(action);

                if (action.equals(StaticValues.ML_DialogClose))
                    return;

                if (vm_latifi.getResponseMessage() == null)
                    return;

                if (vm_latifi.getResponseMessage().equalsIgnoreCase(""))
                    return;

                if ((action.equals(StaticValues.ML_RequestCancel))
                        || (action.equals(StaticValues.ML_ResponseError))
                        || (action.equals(StaticValues.ML_ResponseFailure))
                        || (action.equals(StaticValues.ML_InternetAccessFailed))) {
                    showMessageDialog(vm_latifi.getResponseMessage(),
                            getResources().getColor(R.color.mlHarmony),
                            getResources().getDrawable(svg_error),
                            getResources().getColor(R.color.mlCollectRight1));
                    getActionFromObservable.actionWhenFailureRequest();
                    return;
                } else {
                    showMessageDialog(vm_latifi.getResponseMessage()
                            , getResources().getColor(R.color.mlWhite),
                            getResources().getDrawable(svg_ok),
                            getResources().getColor(R.color.colorPrimaryDark));
                }
            });
        }
    }
    //______________________________________________________________________________________________ actionHandler


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

        return VM_Latifi.utilityComponent.getApplicationUtility();
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



}