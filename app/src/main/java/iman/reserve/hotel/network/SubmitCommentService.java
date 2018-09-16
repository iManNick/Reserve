package iman.reserve.hotel.network;

import iman.reserve.hotel.model.Message;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubmitCommentService {
    @GET("submitcomments.php")
    Call<Message> getSubmitData(
            @Query("hotelname") String hotelname,
            @Query("username") String username,
            @Query("comment") String comment,
            @Query("rating") float rating
    );
}