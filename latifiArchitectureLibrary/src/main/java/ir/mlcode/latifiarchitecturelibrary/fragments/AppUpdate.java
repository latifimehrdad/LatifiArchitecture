package ir.mlcode.latifiarchitecturelibrary.fragments;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.yangp.ypwaveview.YPWaveView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ir.mlcode.latifiarchitecturelibrary.R;
import ir.mlcode.latifiarchitecturelibrary.databinding.FrUpdateBinding;
import ir.mlcode.latifiarchitecturelibrary.utility.DownloadTask;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Update;

public class AppUpdate extends FR_Latifi implements FR_Latifi.getActionFromObservable{


    private VM_Update vm_update;
    private String fileName;
    private Handler handlerDownload;
    private String appName;
    private String applicationId;
    private String fileUrl;

    TextView TextViewProgress;
    Button ButtonInstall;
    YPWaveView yPWaveView;


    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        if (getView() == null) {
            vm_update = new VM_Update(getContext());
            FrUpdateBinding binding = DataBindingUtil.inflate(
                    inflater, R.layout.fr_update, container, false);
            binding.setUpdate(vm_update);
            setView(binding.getRoot());
            TextViewProgress = getView().findViewById(R.id.TextViewProgress);
            ButtonInstall = getView().findViewById(R.id.ButtonInstall);
            yPWaveView = getView().findViewById(R.id.yPWaveView);

            if (getContext() != null)
                TextViewProgress.setText(getContext().getResources().getString(R.string.PleaseWait));
            yPWaveView.setProgress(0);
            ButtonInstall.setVisibility(View.GONE);
            setOnClick();
            init();
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onStart
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(
                AppUpdate.this,
                vm_update.getPublishSubject(),
                vm_update);
        List<String> list = new ArrayList<>();
        list.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        setPermission(list);
    }
    //______________________________________________________________________________________________ onStart


    //______________________________________________________________________________________________ init
    private void init() {
        if (getContext() != null && getArguments() != null) {
            fileUrl = getArguments().getString(getContext().getResources().getString(R.string.ML_UpdateUrl), "");
            fileName = getArguments().getString(getContext().getResources().getString(R.string.ML_UpdateFileName), "");
            appName = getArguments().getString(getContext().getResources().getString(R.string.ML_AppName), "");
            applicationId = getArguments().getString(getContext().getResources().getString(R.string.ML_ApplicationId), "");


        }

    }
    //______________________________________________________________________________________________ init


    private void setOnClick() {//___________________________________________________________________ setOnClick

        ButtonInstall.setOnClickListener(v -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri uri = vm_update.getTempUri(fileName , appName, applicationId);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //dont forget add this line
                if (getContext() != null)
                    getContext().startActivity(intent);
            } else {
                File apkFile;
                apkFile = new File(Environment.getExternalStorageDirectory()
                        + "/" + appName + "/", fileName);
                Uri apkUri = Uri.fromFile(apkFile);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }

        });

    }
    //______________________________________________________________________________________________ setOnClick


    //______________________________________________________________________________________________ getMessageFromObservable
    @Override
    public void getActionFromObservable(Byte action) {

        handlerDownload = null;

        if (action.equals(StaticValues.ML_CheckPermission)) {
            if (!fileUrl.equalsIgnoreCase(""))
                if (!fileName.equalsIgnoreCase("")) {
                    setProgress();
                    vm_update.setFileUrl(fileUrl);
                    vm_update.setFileName(fileName);
                    vm_update.setAppName(appName);
                    vm_update.downloadFile();
                }
            return;
        }

        if (action.equals(StaticValues.ML_FileDownloading)) {
            yPWaveView.setProgress(0);
            if (getContext() != null)
                TextViewProgress.setText(getContext().getResources().getString(R.string.FileDownloaded));
            return;
        }

        if (action.equals(StaticValues.ML_FileDownloaded)) {
            yPWaveView.setProgress(0);
            ButtonInstall.setVisibility(View.VISIBLE);
            TextViewProgress.setVisibility(View.GONE);
            yPWaveView.setVisibility(View.GONE);
            if (getContext() != null)
                TextViewProgress.setText(getContext().getResources().getString(R.string.FileDownloaded));
        }

    }
    //______________________________________________________________________________________________ getMessageFromObservable




    //______________________________________________________________________________________________ setProgress
    private void setProgress() {
        handlerDownload = new Handler();
        handlerDownload.postDelayed(new Runnable() {
            @Override
            public void run() {
                yPWaveView.setProgress(DownloadTask.progressDownload);
                if (DownloadTask.progressDownload < 100)
                    handlerDownload.postDelayed(this, 500);
            }
        }, 500);
    }
    //______________________________________________________________________________________________ setProgress


    //______________________________________________________________________________________________ actionWhenFailureRequest
    @Override
    public void actionWhenFailureRequest() {
    }
    //______________________________________________________________________________________________ actionWhenFailureRequest



}
