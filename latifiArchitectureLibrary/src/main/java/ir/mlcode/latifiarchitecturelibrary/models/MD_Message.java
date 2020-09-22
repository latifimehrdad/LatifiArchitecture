package ir.mlcode.latifiarchitecturelibrary.models;

import com.google.gson.annotations.SerializedName;

public class MD_Message {

    @SerializedName("type")
    private Byte type;

    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;



    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
