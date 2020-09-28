package com.example.latifiarchitecture;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;

import ir.mlcode.latifiarchitecturelibrary.application.APP_Latifi;

public class testApplication extends APP_Latifi {

    private Context context;
    public String Host = "http://You Api Link";
/*    private RetrofitApiInterface retrofitApiInterface;*/


    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
/*
        Fresco.initialize(
                context,
                ImagePipelineConfig.newBuilder(context)
                        .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                        .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                        .experiment().setNativeCodeDisabled(true)
                        .build());*/

        setContext(this.context);
        setHost(Host);
/*        configurationRetrofitComponent();*/
    }

    //______________________________________________________________________________________________ getApplication
    public static testApplication getApplicationWMS(Context context) {
        return (testApplication) context.getApplicationContext();
    }
    //______________________________________________________________________________________________ getApplication



/*
    //______________________________________________________________________________________________ configurationRetrofitComponent
    private void configurationRetrofitComponent() {
        retrofitApiInterface = getRetrofitComponent().getRetrofit().create(RetrofitApiInterface.class);
    }
    //______________________________________________________________________________________________ configurationRetrofitComponent



    //______________________________________________________________________________________________ getRetrofitApiInterface
    public RetrofitApiInterface getRetrofitApiInterface() {
        return retrofitApiInterface;
    }
    //______________________________________________________________________________________________ getRetrofitApiInterface
*/

}