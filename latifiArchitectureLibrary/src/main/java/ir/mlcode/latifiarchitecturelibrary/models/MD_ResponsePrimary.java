package ir.mlcode.latifiarchitecturelibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MD_ResponsePrimary {

    @SerializedName("messages")
    ArrayList<MD_Message> messages;

    public MD_ResponsePrimary(ArrayList<MD_Message> messages) {
        this.messages = messages;
    }

    public ArrayList<MD_Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MD_Message> messages) {
        this.messages = messages;
    }
}
