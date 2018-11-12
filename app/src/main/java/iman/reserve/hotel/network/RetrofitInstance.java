package iman.reserve.hotel.network;

import android.content.res.Resources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.43.147/hotelPHP/";

    //"http://192.168.43.147/hotelPHP/";
    //"http://10.0.3.2/hotelPHP/";
    //"http://155.94.190.234/hotelPHP/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}