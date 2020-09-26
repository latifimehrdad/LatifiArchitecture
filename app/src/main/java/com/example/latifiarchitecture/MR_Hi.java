package com.example.latifiarchitecture;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ir.mlcode.latifiarchitecturelibrary.models.MD_Message;
import ir.mlcode.latifiarchitecturelibrary.models.MD_ResponsePrimary;

public class MR_Hi extends MD_ResponsePrimary {

    @SerializedName("result")
    MD_Hi result;


    public MR_Hi(ArrayList<MD_Message> messages) {
        super(messages);
    }

    public MD_Hi getResult() {
        return result;
    }

    public void setResult(MD_Hi result) {
        this.result = result;
    }
}
