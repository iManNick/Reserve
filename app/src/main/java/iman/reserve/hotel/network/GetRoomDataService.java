package iman.reserve.hotel.network;

import iman.reserve.hotel.model.RoomList;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetRoomDataService {
    @POST("room.php")
    Call<RoomList> getRoomData(@Query("hotelname") String  hotelName);
}
