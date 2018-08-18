package iman.reserve.hotel.network;

import iman.reserve.hotel.model.ReserveResult;
import iman.reserve.hotel.model.SubmitComment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubmitCommentService {
    @GET("submitcomments.php")
    Call<SubmitComment> getSubmitData(
            @Query("h_id") String hotelId,
            @Query("username") String username,
            @Query("comment") String comment,
            @Query("rating") float rating
    );
}