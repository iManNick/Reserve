package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;


public class Message {
    @SerializedName("message")
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String result) {
        this.message = result;
    }
}