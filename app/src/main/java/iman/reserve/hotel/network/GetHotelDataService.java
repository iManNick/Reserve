package iman.reserve.hotel.network;

import iman.reserve.hotel.model.HotelList;
import iman.reserve.hotel.model.RoomList;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetHotelDataService {
    @POST("hotels.php")
    Call<HotelList> getHotelData(@Query("city") String city);
}
