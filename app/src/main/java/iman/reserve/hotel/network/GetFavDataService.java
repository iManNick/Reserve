package iman.reserve.hotel.network;

import iman.reserve.hotel.model.HotelList;
import iman.reserve.hotel.model.Message;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetFavDataService {
    @POST("getfav.php")
    Call<HotelList> getFavData(@Query("username") String username);
    @POST("setfav.php")
    Call<Message> setFavData(@Query("hotelname") String hotelname, @Query("username") String username);
}
