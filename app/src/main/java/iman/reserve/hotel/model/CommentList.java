
package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentList {
    @SerializedName("commentList")
    private ArrayList<Comment> commentList;

    public ArrayList<Comment> getCommentArrayList() {
        return commentList;
    }

    public void setCommentArrayList(ArrayList<Comment> commentArrayList) {
        this.commentList = commentArrayList;
    }
}
