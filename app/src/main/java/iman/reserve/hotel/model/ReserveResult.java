package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;


public class ReserveResult {
    @SerializedName("message")
    private String message;


    public String getReserveResult() {
        return message;
    }

    public void setReserveResult(String result) {
        this.message = result;
    }
}