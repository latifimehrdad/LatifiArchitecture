package ir.mlcode.latifiarchitecturelibrary.viewmodels;

import android.app.Activity;

import ir.mlcode.latifiarchitecturelibrary.utility.DownloadTask;

public class VM_Update extends VM_Latifi {

    //______________________________________________________________________________________________ VM_Update
    public VM_Update(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Update


    //______________________________________________________________________________________________ downloadFile
    public void downloadFile(String url, String filePath) {

        DownloadTask downloadTask = new DownloadTask(getContext(), filePath, getPublishSubject());
        downloadTask.execute(url);
    }
    //______________________________________________________________________________________________ downloadFile


}
