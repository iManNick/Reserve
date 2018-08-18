package iman.reserve.hotel.network;

import iman.reserve.hotel.model.CommentList;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetCommentDataService {
    @POST("getcomments.php")
    Call<CommentList> getCommentData(@Query("h_id") int hotelNo);
}
