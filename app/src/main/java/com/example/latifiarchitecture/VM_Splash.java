package com.example.latifiarchitecture;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;


import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Splash extends VM_Latifi {

    private MD_Hi md_hi;


    public VM_Splash(Activity context) {
        setContext(context);
    }


    //______________________________________________________________________________________________ callHiService
    public void callHiService() {


        RetrofitApiInterface retrofitApiInterface = testApplication.getApplicationWMS(getContext()).getRetrofitApiInterface();

        String client_id_value = "vRIzEFYjpzYwHHSUbx/ODg==";
        String client_secret_value = "n5r+sej/lFv7xVhM9F7+kOG9yI64d/JIkGzl0NvgwMM=";

        setPrimaryCall(retrofitApiInterface.getHi(client_id_value, client_secret_value, "Samtam_Citizen"));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_Hi>() {
            @Override
            public void onResponse(Call<MR_Hi> call, Response<MR_Hi> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_hi = response.body().getResult();
                    setResponseMessage("");
                    checkUpdate();
                } else
                    sendActionToObservable(StaticValues.ML_ResponseError);
            }

            @Override
            public void onFailure(Call<MR_Hi> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ callHiService



    //______________________________________________________________________________________________ checkUpdate
    private void checkUpdate() {
        PackageInfo pInfo;
        float versionName = 0;
        try {
            pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            versionName = Float.valueOf(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        String v = md_hi.getVersion();
        v = v.replaceAll("v", "");
/*        Home.downloadAppLink = md_hi.getIntroduceToFriendLink();*/
        float lastVersion = Float.valueOf(v);


        if (versionName < lastVersion)
            sendActionToObservable((byte) 10);

    }
    //______________________________________________________________________________________________ checkUpdate


    public MD_Hi getMd_hi() {
        return md_hi;
    }
}
