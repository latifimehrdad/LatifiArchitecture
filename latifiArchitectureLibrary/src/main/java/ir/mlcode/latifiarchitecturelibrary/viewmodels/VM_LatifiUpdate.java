package ir.mlcode.latifiarchitecturelibrary.viewmodels;

import android.app.Activity;

import ir.mlcode.latifiarchitecturelibrary.utility.DownloadTask;

public class VM_LatifiUpdate extends VM_Latifi {


    private String fileUrl;
    private String fileName;
    private String appName;



    //______________________________________________________________________________________________ VM_Update
    public VM_LatifiUpdate(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Update


    //______________________________________________________________________________________________ downloadFile
    public void downloadFile() {

        DownloadTask downloadTask = new DownloadTask(getContext(), getFileName(), getAppName(), getPublishSubject());
        downloadTask.execute(getFileUrl());
    }
    //______________________________________________________________________________________________ downloadFile



    //______________________________________________________________________________________________ getFileUrl
    public String getFileUrl() {
        return fileUrl;
    }
    //______________________________________________________________________________________________ getFileUrl



    //______________________________________________________________________________________________ setFileUrl
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    //______________________________________________________________________________________________ setFileUrl




    //______________________________________________________________________________________________ getFileName
    public String getFileName() {
        return fileName;
    }
    //______________________________________________________________________________________________ getFileName



    //______________________________________________________________________________________________ setFileName
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    //______________________________________________________________________________________________ setFileName




    //______________________________________________________________________________________________ getAppName
    public String getAppName() {
        return appName;
    }
    //______________________________________________________________________________________________ getAppName



    //______________________________________________________________________________________________ setAppName
    public void setAppName(String appName) {
        this.appName = appName;
    }
    //______________________________________________________________________________________________ setAppName


}
