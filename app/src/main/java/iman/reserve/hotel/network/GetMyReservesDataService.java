package iman.reserve.hotel.network;


import iman.reserve.hotel.model.MyReserveList;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetMyReservesDataService {
    @POST("getmyreserves.php")
    Call<MyReserveList> getMyReservesData(@Query("username") String username);
}
