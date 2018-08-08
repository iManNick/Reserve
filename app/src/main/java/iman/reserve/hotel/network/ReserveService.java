package iman.reserve.hotel.network;

import iman.reserve.hotel.model.ReserveResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReserveService {
    @GET("Reservation.php")
    Call<ReserveResult> getReserveData(
            @Query("cid") String cid ,
            @Query("cod") String cod,
            @Query("txtroom") int noroom,
            @Query("txttype") String type,
            @Query("txtspanyreq") String req,
            @Query("username") String username
            );
}