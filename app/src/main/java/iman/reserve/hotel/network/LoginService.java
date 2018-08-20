package iman.reserve.hotel.network;


import iman.reserve.hotel.model.LoginResult;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {
    @POST("login.php")
    @FormUrlEncoded
    Call<LoginResult> getLoginData(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone
    );
}