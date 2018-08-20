package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("h_id")
    private String h_id;
    @SerializedName("username")
    private String username;
    @SerializedName("comment")
    private String comment;
    @SerializedName("rating")
    private Float rating;

    public Comment(String username, String comment, Float rating,String h_id) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.h_id = h_id;
    }


    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public String getUN() {
        return username;
    }

    public void setUN(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

}
