package com.example.latifiarchitecture;

import android.content.Context;
import ir.mlcode.latifiarchitecturelibrary.application.APP_Latifi;

public class testApplication extends APP_Latifi {

    private Context context;
    public String Host = "http://2.144.243.200:70";
    private RetrofitApiInterface retrofitApiInterface;


    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        setContext(this.context);
        setHost(Host);
        ConfigurationRetrofitComponent();
    }


    public static testApplication getApplicationWMS(Context context) {//_____________________________ getApplicationWMS
        return (testApplication) context.getApplicationContext();
    }//_____________________________________________________________________________________________ getApplicationWMS



    private void ConfigurationRetrofitComponent() {//________________________________________________ ConfigurationRetrofitComponent
        retrofitApiInterface = getRetrofitComponent().getRetrofit().create(RetrofitApiInterface.class);
    }//_____________________________________________________________________________________________ ConfigurationRetrofitComponent



    public RetrofitApiInterface getRetrofitApiInterface() {
        return retrofitApiInterface;
    }
}
