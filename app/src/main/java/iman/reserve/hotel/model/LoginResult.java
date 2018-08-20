package iman.reserve.hotel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResult {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;


    public String getLoginMessage() {
        return message;
    }

    public void setLoginMessage(String message) {
        this.message = message;
    }


    public String getLoginSuccess() {
        return success;
    }

    public void setLoginSuccess(String success) {
        this.success = success;
    }

}