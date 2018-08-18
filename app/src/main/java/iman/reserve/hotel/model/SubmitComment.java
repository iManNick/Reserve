package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;


public class SubmitComment {
    @SerializedName("message")
    private String message;


    public String getSubmitResult() {
        return message;
    }

    public void setSubmitResult(String result) {
        this.message = result;
    }
}